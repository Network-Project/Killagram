package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Vector;

import controller.MessageHandler;
import server.Exercise;
import server.ExerciseInfo;
import server.Food;
import server.IntakeInfo;
import server.Message;
import server.Server;
import server.User;
import view.Login;
import view.Login.LoginListener;
import view.Main;
import view.Main.MainListener;

public class Client extends Thread implements MessageHandler, LoginListener, MainListener {
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Login login;
	private Main main;
	private User user = null;
	private MessageReceiver receiver;
    private MessageSender sender;
    
	public Client() {
		try {
			socket = new Socket(Server.ADDRESS, Server.PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		login = new Login(this);
	}

	private void startMain() {
		receiver = new MessageReceiver(in);
		receiver.setClient(this);
		receiver.start();
		sender = new MessageSender(out);
		main = new Main(this);
	}
	
	@Override
	public void messageHandle(Message message) throws IOException {
		System.out.println("handler = " + message.getWhat());
		switch (message.getWhat()) {
		case Message.GET_FOOD_LIST: {
			Object o = message.getObject();
			if ((o instanceof Vector<?>)) {
				main.getIntakeTab().getFoodFrame().setFoodList(message.getArg(), (Vector<Food>) o);
			}
			break;
		}
		case Message.GET_INTAKE_LIST:{
			Object o = message.getObject();
			if((o instanceof Vector<?>)) {
				main.getIntakeTab().setIntakeList((Vector<IntakeInfo>)o);
				main.getIntakeTab().showIntakeList();
			}
			break;
		}
		case Message.GET_EXERCISEINFO_LIST:{
			Object o = message.getObject();
			if((o instanceof Vector<?>)) {
				main.getSportTab().setExerciseInfoList((Vector<ExerciseInfo>)o);
				main.getSportTab().showExerciseInfoList();
			}
			break;
		}
		case Message.GET_EXERCISE_LIST:{
			Object o = message.getObject();
			if((o instanceof Vector<?>)) {
				main.getSportTab().getExerciseFrame().setExerciseList((Vector<Exercise>)o);
				main.getSportTab().getExerciseFrame().showExerciseList();
			}
			break;
		}
		case Message.INSERT_EXERCISEINFO:{
			if(message.isValid()) {
				System.out.println("insert success");
			}
			break;
		}
		case Message.GET_RANK_LIST:{
			Object o = message.getObject();
			if((o instanceof HashMap<?,?>)) {
				main.getRankTab().setRank((HashMap<String,Float>)o);
				main.getRankTab().showRankList();
			}
			break;
		}
		}

	}

	@Override
	public boolean onClickLogin(String id, String password) {
		System.out.println("Login");
		try {
			Message message = new Message(Message.LOGIN, id, password);
			out.writeObject(message);
			Object o = null;
			o = in.readObject();
			if (!(o instanceof Message)) {
				return false;
			}
			Message reply = (Message) o;
			if (!(reply.getObject() == null)) {
				login.dispose();
				user = (User) reply.getObject();
				startMain();
				return true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onClickSignUp(User user) {
		System.out.println("SignUP");
		try {
			Message message = new Message(Message.SIGN_UP);
			message.setObject(user);
			out.writeObject(message);
			Object o = null;
			o = in.readObject();
			if (!(o instanceof Message)) {
				return false;
			}
			Message reply = (Message) o;
			if (reply.isValid())
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean onCheckDuplicate(String id) {
		System.out.println("Check Duplication");
		try {
			out.writeObject(new Message(Message.ID_CHECK, id));
			Object o = null;
			o = in.readObject();
			if (!(o instanceof Message)) {
				return false;
			}
			Message reply = (Message) o;
			if (reply.isValid())
				return true; // 중복임
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;// 중복아님

	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void requestFoodList(String tableName) {
		try {
			sender.messageSend(new Message(Message.GET_FOOD_LIST, tableName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void requestIntakeList() {
		try {
			sender.messageSend(new Message(Message.GET_INTAKE_LIST, user.getId()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Vector<String> insertExerciseInfo(Exercise exercise, float time) {
		float kal = Server.calculateKal(user.getWeight(), exercise.getMET(), time);
		Vector<String> vec = new Vector<String>();
		vec.addElement(exercise.getName());
		vec.addElement(String.valueOf(time));
		vec.addElement(String.valueOf(kal));
		try {
			Message message = new Message(Message.INSERT_EXERCISEINFO);
			message.setObject(new ExerciseInfo(user.getId(),Server.getNowDate(), exercise.getName(),
					time,kal));
			sender.messageSend(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vec;
	}

	@Override
	public void requestExerciseInfoList() {
		try {
			sender.messageSend(new Message(Message.GET_EXERCISEINFO_LIST, user.getId()));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void insertIntakeInfo(Food food, int quantity) {
		try {
			Message message = new Message(Message.INSERT_INTAKE);
			message.setObject(new IntakeInfo(user.getId(),Server.getNowDate(),food.getType(),
					food.getName(),food.getUnit(),quantity,quantity*food.getKal()));
			sender.messageSend(message);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void requestExerciseList() {
		try {
			sender.messageSend(new Message(Message.GET_EXERCISE_LIST));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void requestRankList() {
		try {
			sender.messageSend(new Message(Message.GET_RANK_LIST));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
