package net.socket;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Socket Control
 * 
 * @author dailey_dai Feb 16, 2011
 */
public class SocketControl implements SocketBean {

	private static Object initObject = new Object();

	private SocketActionListener socketActionListener;
	private ServerSocket server = null;
	private Socket client = null;

	private int port = HOST_UNSET_PORT;
	private String host;

	private int controlType = CLIENT_TYPE;

	public boolean isServer() {
		return controlType == SERVER_TYPE;
	}

	public void asServer() {
		controlType = SERVER_TYPE;
	}

	public void asClient() {
		controlType = CLIENT_TYPE;
	}

	public SocketControl() {
		initSocketActionListener();
	}

	public SocketControl(int type) {
		this();
		if (type == SERVER_TYPE) {
			asServer();
		} else {
			asClient();
		}
	}

	private void initSocketActionListener() {
		this.socketActionListener = new SocketActionListenerAdapter();
	}

	@Override
	public void addSocketActionListener(SocketActionListener sal) {
		setSocketActionListener(sal);
	}

	private void setSocketActionListener(SocketActionListener sal) {
		if (sal == null)
			initSocketActionListener();
		else
			this.socketActionListener = sal;
	}

	@Override
	public synchronized void closeConnection() {
		if (isServer()) {
			if (server != null && !server.isClosed()) {
				try {
					server.close();
					server = null;
				} catch (IOException e) {
					getSocketActionListener().onGetException(e);
				}
			}
		} else {
			if (client != null && !client.isClosed()) {
				try {
					client.close();
					client = null;
				} catch (IOException e) {
					getSocketActionListener().onGetException(e);
				}
			}
		}
		getSocketActionListener().onConnectionClosed();

	}

	@Override
	public String getHost() {
		if (host == null)
			return HOST_DEFAULT_ADDRESS;
		else
			return host;
	}

	@Override
	public int getPort() {
		if (port == HOST_UNSET_PORT || port <= 0)
			return HOST_DEFAULT_PORT;
		else
			return port;
	}

	@Override
	public ServerSocket getServer() {
		if (isServer()) {
			if (server == null) {
				synchronized (initObject) {// ensure single
					if (server == null) {
						try {
							server = new ServerSocket(getPort());
							getSocketActionListener().onStart();
						} catch (UnknownHostException e) {
							getSocketActionListener().onGetException(e);
						} catch (IOException e) {
							getSocketActionListener().onGetException(e);
						}
					}
				}
			}
			return server;
		} else {
			return null;
		}

	}

	@Override
	public Socket getClient() {
		if (!isServer()) {
			if (client == null) {
				synchronized (initObject) {
					if (client == null) {
						try {
							client = new Socket(getHost(), getPort());
							getSocketActionListener().onStart();
							new SocketListenerThread(this).start();
						} catch (UnknownHostException e) {
							getSocketActionListener().onGetException(e);
						} catch (IOException e) {
							getSocketActionListener().onGetException(e);
						}
					}
				}
			}
		} else {
			try {
				client = getServer().accept();
				getSocketActionListener().onConnectionEstablished(client);
			} catch (IOException e) {
				getSocketActionListener().onGetException(e);
			}
		}
		return client;
	}

	@Override
	public SocketActionListener getSocketActionListener() {
		return socketActionListener;
	}

	@Override
	public boolean isAlive() {
		if (!isServer()) {
			if (client != null)
				return !client.isClosed();
		} else {
			if (server != null)
				return !getServer().isClosed();
		}
		return false;
	}

	@Override
	public void openConnection() {
		if (isServer()) {
			getServer();
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (isAlive()) {
						Socket client = getClient();
						new SocketListenerThread(SocketControl.this, client)
								.start();
					}
				}
			}).start();
		} else {
			getClient();
			new SocketListenerThread(this).start();
		}

	}

	@Override
	public boolean sendData(Socket socket, String data) {
		PrintWriter pout = null;
		if (data == null)
			return false;
		try {
			pout = new PrintWriter(socket.getOutputStream());
			pout.println(data);
			pout.flush();
			return true;
		} catch (IOException e) {
			getSocketActionListener().onGetException(e);
		}
		return false;
	}
	
	@Override
	public boolean sendData(Socket socket, byte[] data) {
		BufferedOutputStream bos=null;
		if (data == null)
			return false;
		try {
			bos=new BufferedOutputStream(socket.getOutputStream());
			bos.write(data,0,data.length);
			bos.flush();
			return true;
		} catch (IOException e) {
			getSocketActionListener().onGetException(e);
		}
		return false;
	}

	@Override
	public void setHost(String host) {
		if (this.host == null && host == null)
			host = HOST_DEFAULT_ADDRESS;
		this.host = host;
	}

	@Override
	public void setPort(int port) {
		if (port == HOST_UNSET_PORT && port <= 0) {
			port = HOST_DEFAULT_PORT;
		} else
			this.port = port;
	}

	/**
	 * get formated date and time string
	 * 
	 * @return
	 */
	public String getFormatTime() {
		return FORMAT.format(CALENDAR.getTime());
	}

}
