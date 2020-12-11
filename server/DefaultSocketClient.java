
package server;

import java.net.*;
import java.io.*;
import adapter.Debuggable;
import model.Automobile;

public class DefaultSocketClient extends Thread implements Debuggable {

	////////// PROPERTIES //////////

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket clientSocket;
	private BuildCarModelOptions protocol;
	private Automobile autos;

	////////// CONSTRUCTORS //////////

	public DefaultSocketClient(Socket clientSocket, Automobile autos) {
		this.clientSocket = clientSocket;
		this.autos = autos;
	}

	////////// INSTANCE METHODS //////////

	public void handleConnection(Socket sock) {
		if (DEBUG)
			System.out.println("Creating server object streams ... ");
		try {
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e) {
			System.err.println("Error creating server object streams ... ");
			System.exit(1);
		}

		protocol = new BuildCarModelOptions(autos);
		String menu = "\nEnter 1 to upload a new Automobile\n" + "Enter 2 to configure an Automobile\n"
				+ "Enter 0 to terminate connection\n";

		try {
			do {
				if (DEBUG)
					System.out.println("Sending client interaction choices ... ");
				sendOutput(menu);

				if (DEBUG)
					System.out.println("Reading client request ... ");
				int request = Integer.parseInt(in.readObject().toString());
				if (DEBUG)
					System.out.println(request);
				if (request == 0)
					break;

				if (DEBUG)
					System.out.println("Sending client request follow-up ... ");
				sendOutput(protocol.defaultResponse(request));

				if (request >= 1 && request <= 2)
					handleInput(request);

			} while (in.readObject() != null);

			if (DEBUG)
				System.out.println("Closing server input stream for client " + sock.getInetAddress() + " ... ");
			in.close();
		} catch (IOException e) {
			System.err.println("Error handling client connection ... ");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			System.err.println("Error in uploaded object, object may be corrupted ... ");
			System.exit(1);
		}
	}

	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			System.err.println("Error returning output to client ... ");
			System.exit(1);
		}
	}

	public void handleInput(int request) {
		Object fromClient = null;
		Object toClient = null;

		try {
			switch (request) {
				case 1: // TODO: Client request to build Automobile
					if (DEBUG)
						System.out.println("Waiting for client to upload file ... ");
					fromClient = in.readObject();
					if (DEBUG) {
						System.out.println(fromClient);
						System.out.println("Adding new Automobile to database ... ");
					}
					toClient = protocol.processRequest(fromClient);
					sendOutput(toClient);
					break;

				case 2: // Client request to configure Automobile
					if (DEBUG)
						System.out.println("Waiting for client to select Automobile ... ");
					fromClient = Integer.parseInt(in.readObject().toString());
					if (DEBUG)
						System.out.println("Sending Automobile object to client ... ");
					toClient = protocol.processRequest(fromClient);
					sendOutput(toClient);
					break;

				default: // Invalid requests
					;
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Error in uploaded object, file may be corrupted ... ");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error in retrieving object from client ... ");
			System.exit(1);
		}
	}

	// g_ters & s_ters

	public ObjectOutputStream g_Out() {
		return out;
	}

	public ObjectInputStream g_In() {
		return in;
	}

	public Socket g_ClientSocket() {
		return clientSocket;
	}

	public void s_ClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public BuildCarModelOptions g_Protocol() {
		return protocol;
	}

	public void s_Protocol(BuildCarModelOptions protocol) {
		this.protocol = protocol;
	}

	public Automobile g_Automobiles() {
		return autos;
	}

	public void s_Automobiles(Automobile autos) {
		this.autos = autos;
	}

	@Override
	public void run() {
		handleConnection(clientSocket);
		// TODO:
		// Close client output stream
		if (DEBUG)
			System.out.println("Closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
		try {
			out.close();
		} catch (IOException e) {
			System.err.println(
					"Error closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
		}

		// Close client socket
		if (DEBUG)
			System.out.println("Closing client socket " + clientSocket.getInetAddress() + " ... ");
		try {
			clientSocket.close();
		} catch (IOException e) {
			System.err.println("Error closing client socket " + clientSocket.getInetAddress() + " ... ");
		}
	}

}
