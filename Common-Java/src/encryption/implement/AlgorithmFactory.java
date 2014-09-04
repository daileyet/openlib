package encryption.implement;

import encryption.Algorithm;

public class AlgorithmFactory {

	public static Algorithm getDefaultAlgorithm() {
		return new DJAlgorithm();
	}

}
