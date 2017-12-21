package controller;

import java.io.IOException;

import server.Message;

public interface MessageHandler {
	void messageHandle(Message message) throws IOException;
	
	interface Sender{
		void messageSend(Message message) throws IOException;
	}
}
