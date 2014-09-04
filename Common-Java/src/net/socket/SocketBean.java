package net.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * client/server socket component interface
 * 
 * @author dailey_dai Feb 12, 2011
 */
public interface SocketBean {
	public static final int HOST_UNSET_PORT = -1000;
	public static final int HOST_DEFAULT_PORT = 7788;
	public static final String HOST_DEFAULT_ADDRESS = "127.0.0.1";

	public static final int SERVER_TYPE = 1;
	public static final int CLIENT_TYPE = 0;

	// relate date object
	public static final Calendar CALENDAR = Calendar.getInstance();
	// format the date time
	public static final DateFormat FORMAT = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	/**
	 * open the connection. client established socket,connect to host. server
	 * established serversocket,hold and listen its port.
	 */
	public void openConnection();

	public void closeConnection();

	public String getHost();

	public void setHost(String host);

	public int getPort();

	public void setPort(int port);

	/**
	 * get current client socket
	 * 
	 * @return Socket
	 */
	public Socket getClient();

	/**
	 * get server socket
	 * 
	 * @return ServerSocket
	 */
	public ServerSocket getServer();

	/**
	 * the socket component if is closed or alive
	 * 
	 * @return
	 */
	public boolean isAlive();

	/**
	 * send data line to socket
	 * 
	 * @param socket
	 * @param line
	 * @return
	 */
	public boolean sendData(Socket socket, String data);

	
	public boolean sendData(Socket socket,byte[] data);
	/**
	 * get socketactionlistener
	 * 
	 * @return
	 */
	public SocketActionListener getSocketActionListener();

	/**
	 * add a socketactionlistener
	 * 
	 * @param sal
	 */
	public void addSocketActionListener(SocketActionListener sal);
}
