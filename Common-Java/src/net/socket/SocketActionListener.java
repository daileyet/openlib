package net.socket;

import java.net.Socket;

/**
 * Socket listener
 * 
 * @author dailey_dai Feb 12, 2011
 */
public interface SocketActionListener {

	/**
	 * do sth when received data from socket
	 * 
	 * @param socket
	 *            : data from socket
	 * @param data
	 *            : the data received from socket
	 */
	public void onDataReceived(Socket socket, Object data);

	/**
	 * do sth when client and server connected
	 * 
	 * @param socket
	 *            : client socket
	 */
	public void onConnectionEstablished(Socket socket);

	/**
	 * do sth when connection closed
	 */
	public void onConnectionClosed();

	/**
	 * do sth when server/client socket initial
	 */
	public void onStart();

	/**
	 * do sth when encounter a exception
	 * 
	 * @param ex
	 */
	public void onGetException(Exception ex);

	/**
	 * do sth when encounter information
	 * 
	 * @param info
	 */
	public void onGetInoframtion(Object info);
}
