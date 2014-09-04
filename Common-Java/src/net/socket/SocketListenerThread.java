package net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Thread for listening socket
 * 
 * @author dailey_dai Feb 12, 2011
 */
public class SocketListenerThread extends Thread {
	private SocketBean socketBean = null;
	private Socket socket = null;

	// for client
	public SocketListenerThread(SocketBean clientBean) {
		this(clientBean, null);
	}

	// for server
	public SocketListenerThread(SocketBean serverBean, Socket socket) {
		this.socketBean = serverBean;
		this.socket = socket;
	}

	public void setSocketBean(SocketBean socketBean) {
		this.socketBean = socketBean;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * get current socket object
	 * 
	 * @return
	 */
	protected Socket getSocket() {
		if (socket == null) {// client
			return socketBean.getClient();
		} else {// server
			return socket;
		}
	}

	public void run() {
		BufferedReader br = null;// in stream

		while (socketBean.isAlive()) {// if not closed
			if (br == null) {
				try {// client socket's inputstream
					br = new BufferedReader(new InputStreamReader(getSocket()
							.getInputStream()));
				} catch (IOException e) {
					socketBean.getSocketActionListener().onGetException(e);
				}
			}
			String response = null;
			try {
				response = br.readLine();
				// call back
				socketBean.getSocketActionListener().onDataReceived(
						getSocket(), response);
			} catch (IOException e) {
				// call back
				socketBean.getSocketActionListener().onGetException(e);
				break;
			}
		}

	}

}
