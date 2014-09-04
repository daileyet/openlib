package encryption.implement;

import encryption.Key;

public class KeyFactory {

	public static Key getDefaultKey() {
		return new DJKey();
	}

}
