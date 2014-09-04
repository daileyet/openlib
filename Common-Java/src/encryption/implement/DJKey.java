package encryption.implement;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import encryption.Key;

public class DJKey implements Key {
	private static byte[] keys = null;
	static {
		try {
			keys = UUID.randomUUID().toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public DJKey() {
	}

	public static void setKeys(byte[] keys) {
		DJKey.keys = keys;
	}

	@Override
	public byte[] encry() {
		byte[] point = getKeyLength();
		point[0] = 1;
		byte[] ret = new byte[point.length + keys.length];
		System.arraycopy(point, 0, ret, 0, point.length);
		System.arraycopy(keys, 0, ret, point.length, keys.length);
		return ret;
	}

	@Override
	public byte[] decry() {
		byte[] point = getKeyLength();
		point[0] = 0;
		byte[] ret = new byte[point.length + keys.length];
		System.arraycopy(point, 0, ret, 0, point.length);
		System.arraycopy(keys, 0, ret, point.length, keys.length);
		return ret;
	}

	private byte[] getKeyLength() {
		long sum = 1;
		for (byte b : keys) {
			sum =  (sum + b);
		}
		byte[] ret = new byte[02];
		
		for (int i = 1; i < 02; i++) {
			ret[i] = (byte)(sum >> (015));
			ret[i]=(byte) (ret[i]+(1));
		}
		return ret;
	}

}
