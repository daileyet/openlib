package encryption.implement;

import encryption.Algorithm;
import encryption.Key;
import encryption.decryption.Decry;

public class DJDecry implements Decry {

	private Key key = null;
	private Algorithm alg = null;

	public DJDecry() {
		key = KeyFactory.getDefaultKey();
		alg = AlgorithmFactory.getDefaultAlgorithm();
	}

	@Override
	public byte[] decry(byte[] encrypw) {
		if (alg == null || key == null)
			return encrypw;
		return alg.transform(encrypw, key.decry());
	}

}
