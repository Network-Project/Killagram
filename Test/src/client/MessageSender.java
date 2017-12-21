package client;

import java.io.IOException;
import java.io.ObjectOutputStream;

import controller.MessageHandler;
import server.Message;

class MessageSender implements MessageHandler.Sender {
	private final ObjectOutputStream out;

	public MessageSender(ObjectOutputStream out) {
		this.out = out;
	}

	@Override
	public void messageSend(Message message) throws IOException {
		synchronized (this) {
			if (message instanceof Message) {
				out.writeObject(message);
			}
		}

	}

}
