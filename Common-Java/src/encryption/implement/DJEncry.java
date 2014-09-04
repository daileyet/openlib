package encryption.implement;

import encryption.Algorithm;
import encryption.Encry;
import encryption.Key;

public class DJEncry implements Encry {
	private Key key = null;
	private Algorithm alg = null;

	public DJEncry() {
		key = KeyFactory.getDefaultKey();
		alg = AlgorithmFactory.getDefaultAlgorithm();
	}

	@Override
	public byte[] encry(final byte[] original) {
		if (alg == null || key == null)
			return original;
		return alg.transform(original, key.encry());
	}

}
