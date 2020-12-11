package server;

import adapter.*;
import model.Automobile;

import java.io.*;
import java.net.*;

public class DefaultServerSocket extends Thread implements Debuggable {

	////////// PROPERTIES //////////

	private int port;
	private ServerSocket server;
	private Automobile a1;

	////////// CONSTRUCTORS //////////

	public DefaultServerSocket(int port, Automobile a1) {
		this.port = port;
		try {
			this.server = new ServerSocket(port);
		} catch (IOException e) {
			System.err.printf("Could not listen on port " + port + " ... \n");
		}
		this.a1 = a1;
	}

	////////// GETTERS & SETTERS //////////

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	public Automobile g_Automobiles() {
		return a1;
	}

	public void s_Automobiles(Automobile a1) {
		this.a1 = a1;
	}

	////////// INSTANCE METHODS //////////

	public void serve() {
		Socket clientSocket = null;
		if (DEBUG)
			System.out.printf("Server start! \n");
		while (true) {
			// Accept client
			try {
				clientSocket = server.accept();
			} catch (IOException e) {
				System.err.printf("Error establishing client connection ... \n");
			}

			// Handle client-server interaction
			if (DEBUG)
				System.out.printf("%s\n", clientSocket.getLocalAddress());

			new DefaultSocketClient(clientSocket, a1).start();
		}
	}

}
