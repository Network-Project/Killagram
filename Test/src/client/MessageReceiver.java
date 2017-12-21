package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;

import controller.MessageHandler;
import server.Message;

class MessageReceiver extends Thread {
	private MessageHandler client;

	public void setClient(MessageHandler client) {
		this.client = client;
	}

	private final ObjectInputStream in;

	public MessageReceiver(ObjectInputStream in) {
		this.in = in;
	}

	public void run() {
		System.out.println("Message receiver run");
		while (true) {
			try {
				Object o = in.readObject();
				if (!(o instanceof Message)) {
					continue;
				}
				Message message = (Message) o;
				client.messageHandle(message);
				message = null;
			} catch (SocketException e) {
				break;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
