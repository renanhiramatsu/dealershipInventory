
package client;

import java.net.*;
import java.io.*;
import adapter.Debuggable;
import model.Automobile;

public class DefaultSocketClient extends Thread implements Debuggable, Connect {

	////////// PROPERTIES //////////

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private BufferedReader stdIn;
	private Socket sock;
	private String strHost;
	private int iPort;
	private CarModelOptionsIO clientFTP;
	private SelectCarOptions clientProtocol;

	////////// CONSTRUCTORS //////////

	public DefaultSocketClient(String strHost, int iPort) {
		this.strHost = strHost;
		this.iPort = iPort;
	}

	////////// GETTERS & SETTERS //////////

	public ObjectOutputStream g_Out() {
		return out;
	}

	public ObjectInputStream g_In() {
		return in;
	}

	public BufferedReader g_StdIn() {
		return stdIn;
	}

	public void s_StdIn(BufferedReader stdIn) {
		this.stdIn = stdIn;
	}

	public Socket g_Sock() {
		return sock;
	}

	public void s_Sock(Socket sock) {
		this.sock = sock;
	}

	public String s_StrHost() {
		return strHost;
	}

	public void s_StrHost(String strHost) {
		this.strHost = strHost;
	}

	public int s_iPort() {
		return iPort;
	}

	public void s_iPort(int iPort) {
		this.iPort = iPort;
	}

	public CarModelOptionsIO s_ClientFTP() {
		return clientFTP;
	}

	public void s_ClientFTP(CarModelOptionsIO clientFTP) {
		this.clientFTP = clientFTP;
	}

	public SelectCarOptions g_ClientProtocol() {
		return clientProtocol;
	}

	public void s_ClientProtocol(SelectCarOptions clientProtocol) {
		this.clientProtocol = clientProtocol;

	}

	////////// INSTANCE METHODS //////////

	public void connect() {
		establishConnection();
		handleConnection();
		try {
			if (DEBUG)
				System.out.printf("Closing client output stream.. \n");
			out.close();

			if (DEBUG)
				System.out.printf("Closing client console input stream.. \n");
			stdIn.close();

			if (DEBUG)
				System.out.printf("Closing client socket.. \n");
			sock.close();
		} catch (IOException e) {
			System.err.printf("Error ending client connection.. \n");
		}
	}

	public void establishConnection() {
		try {
			if (DEBUG)
				System.out.printf("Connecting to host ... \n");
			this.sock = new Socket(strHost, iPort);

			if (DEBUG)
				System.out.printf("Connected to host, creating object streams ... \n");
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			stdIn = new BufferedReader(new InputStreamReader(System.in));

			clientFTP = new CarModelOptionsIO();
			clientProtocol = new SelectCarOptions();
		} catch (IOException e) {
			System.err.printf("Error obtaining I/O for connection to host ... \n");
		}
	}

	public void handleConnection() {
		Object fromServer, toServer = null;
		try {
			if (DEBUG)
				System.out.printf("Communicating with host ... \n");

			while ((fromServer = in.readObject()) != null) {
				if (DEBUG)
					System.out.printf("Received server response ... \n");
				System.out.printf(fromServer.toString() + "\n");

				if (fromServer instanceof Automobile) {
					clientProtocol.configureAuto(fromServer);
					System.out.printf("Press any key to return to main menu\n");
				}

				System.out.printf("Response to server: \n");
				toServer = stdIn.readLine();
				if (toServer.toString().contains(".prop")) {
					toServer = clientFTP.loadPropsFile(toServer.toString());
				}

				if (DEBUG)
					System.out.printf("Sending " + toServer + " to server ... \n");
				sendOutput(toServer);
				System.out.printf("\n");

				if (toServer.equals("0")) {
					break;
				}
			}

			if (DEBUG)
				System.out.printf("Closing client input stream ... \n");
			in.close();

		} catch (ClassNotFoundException e) {
			System.err.printf("Error in downloaded object, object may be corrupted ... \n");
		} catch (IOException e) {
			System.err.printf("Error in I/O stream ... \n");
		}
	}

	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			System.err.println("Error in I/O stream while sending object to host ... ");
			System.exit(1);
		}
	}

	public void print() {
		System.out.printf("out : %s\n", out.toString());
		System.out.printf("in : %s\n", in.toString());
		System.out.printf("stdIn : %s\n", stdIn.toString());
		System.out.printf("sock : %s\n", sock.toString());
		System.out.printf("strHost : %s\n", strHost);
		System.out.printf("iPort : %d\n", iPort);
		System.out.printf("clientFTP :\n");
		clientFTP.print();
		System.out.printf("clientProtocol :\n");
		clientProtocol.print();
	}

	// @Override
	// public void run() {
	// establishConnection();
	// handleConnection();
	// try {
	// if (DEBUG)
	// System.out.println("Closing client output stream ... ");
	// out.close();

	// if (DEBUG)
	// System.out.println("Closing client console input stream ... ");
	// stdIn.close();

	// if (DEBUG)
	// System.out.println("Closing client socket ... ");
	// sock.close();
	// } catch (IOException e) {
	// System.err.println("Error ending client connection ... ");
	// System.exit(1);
	// }
	// }

}
