package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Vector;

import controller.MessageHandler;

public class Server {
	public final static int PORT = 9000;
	public final static String ADDRESS = "127.0.0.1";

	private ServerSocket listener;
	private DB db;
	private Vector<Handler> clientList;

	public Server() {
		try {
			initServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clientList = new Vector<Handler>();
		db = DB.getInstance();
	}

	public static float calculateKal(float weight, float met, float time) {
		return (met*3.5f*weight*time)*5.0f/1000.0f;
	}
	
	public static String getNowDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = dtf.format(LocalDate.now());
		return date;
	}
	
	private final void initServer() throws IOException {
		listener = new ServerSocket(PORT);
	}

	public final void startServer() {
		System.out.println("The chat server is running");
		try {
			while (true) {
				Handler client = new Handler(listener.accept());
				clientList.add(client);
				client.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 서버가 사용한 자원들을 해제하는 곳
			System.out.println("The chat server isn't running");
			try {
				listener.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void broadcastMessage(Message message) {
		if (message instanceof Message) {
			System.out.println(clientList.size());
			for (Handler client : clientList) {
				try {
					client.getOutStream().writeObject(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private class Handler extends Thread implements MessageHandler {

		private Socket socket;
		private ObjectOutputStream out;
		private ObjectInputStream in;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public ObjectOutputStream getOutStream() {
			return out;
		}

		public void initHandler() {
			try {
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void run() {
			initHandler();
			while (true) {
				Message message;
				try {
					Object o = in.readObject();
					if (!(o instanceof Message)) {
						continue;
					}
					message = (Message) o;
					messageHandle(message);
				} catch (SocketException e) {
					clientList.remove(this);
					break;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					message = null;
				}
			}
		}

		public void refreshRank() {
			Message message = new Message(Message.GET_RANK_LIST);
			HashMap<String,Float> rankList = db.getRank();
			message.setObject(rankList);
			broadcastMessage(message);
		}
		@Override
		public void messageHandle(Message message) throws IOException {
			//System.out.println("handler " + message.getWhat());
			switch (message.getWhat()) {
			case Message.ID_CHECK: {
				message.setValid(db.checkID(message.getArg()));
				out.writeObject(message);
				break;
			}
			case Message.SIGN_UP: {
				if (message.getObject() instanceof User) {
					message.setValid(db.insertUser((User) message.getObject()));
					out.writeObject(message);
				}
				break;
			}
			case Message.LOGIN: {
				message.setObject(db.checkUser(message.getArg(), message.getArg2()));
				out.writeObject(message);
				break;
			}
			case Message.GET_FOOD_LIST: {
				Vector<Food> foodList = db.getFoodList(message.getArg());
				message.setObject(foodList);
				out.writeObject(message);
				break;
			}
			case Message.INSERT_INTAKE: {
				if (message.getObject() instanceof IntakeInfo) {
					message.setValid(db.insertIntakeInfo((IntakeInfo) message.getObject()));
					out.writeObject(message);
					refreshRank();		
				}
				break;
			}
			case Message.INSERT_EXERCISEINFO: {
				if (message.getObject() instanceof ExerciseInfo) {
					ExerciseInfo info = (ExerciseInfo)message.getObject();
					info.setKal(Server.calculateKal(db.getWeight(info.getId()), db.getMET(info.getName()), info.getTime()));
					message.setValid(db.insertExerciseInfo(info));
					out.writeObject(message);
					refreshRank();
				}
				break;
			}
			case Message.GET_INTAKE_LIST: {
				Vector<IntakeInfo> infoList = db.getIntakeInfoList(message.getArg());
				message.setObject(infoList);
				out.writeObject(message);
				break;
			}
			case Message.GET_EXERCISEINFO_LIST: {
				Vector<ExerciseInfo> infoList = db.getExerciseInfoList(message.getArg());
				message.setObject(infoList);
				out.writeObject(message);
				break;
			}
			case Message.GET_EXERCISE_LIST: {
				Vector<Exercise> infoList = db.getExerciseList();
				message.setObject(infoList);
				out.writeObject(message);
				break;
			}
			case Message.GET_RANK_LIST: {
				HashMap<String,Float> rankList = db.getRank();
				message.setObject(rankList);
				out.writeObject(message);
				break;
			}
			}

		}
	}
}
