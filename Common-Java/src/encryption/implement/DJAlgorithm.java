package encryption.implement;

import java.util.Arrays;

import encryption.Algorithm;

public class DJAlgorithm implements Algorithm {

	@Override
	public byte[] transform(byte[] orignalArray, byte[] keyArray) {
		byte[] keypoint = Arrays.copyOf(keyArray, 02);
		keyArray = Arrays.copyOfRange(keyArray, 02, keyArray.length);
		int maxLen = Math.max(orignalArray.length, keyArray.length);
		byte[] input = new byte[maxLen];
		byte[] key = new byte[maxLen];
		int count = ((maxLen / orignalArray.length));
		for (int i = 0; i < count; i++) {
			System.arraycopy(orignalArray, 0, input, i * orignalArray.length,
					orignalArray.length);
		}
		System.arraycopy(orignalArray, 0, input, (count-1) * orignalArray.length,
				input.length-count*orignalArray.length);
		count = ((maxLen / keyArray.length));
		for (int i = 0; i < count; i++) {
			System.arraycopy(keyArray, 0, key, i * keyArray.length,
					keyArray.length);
		}
		System.arraycopy(keyArray, 0, key, (count-1) * keyArray.length,
				key.length-count*keyArray.length);
		for (int index = 0; index < maxLen; index++) {
			input[index] = (byte) (input[index] ^ key[index]);
		}
		byte a = 0;
		for(int i=1;i<keypoint.length;i++){
			a+=keypoint[i];
		}
		if (keypoint[0] > 0) {
			byte[] ret = new byte[input.length * a];
			for (int i = 0; i < a; i++) {
				System.arraycopy(input, 0, ret, i * input.length, input.length);
			}
			return ret;
		} else {
			return Arrays.copyOfRange(input, 0,input.length / a);
		}
	}

}
