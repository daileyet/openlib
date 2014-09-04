package encryption;
/**
 * algorithm for entry or decryption
 * @author dailey_dai
 *
 * Jan 3, 2012
 */
public interface Algorithm {
	
	/**
	 * transform from original byte array by key to target byte array
	 * @param orignalArray byte[]
	 * @param keyArray byte[]
	 * @return byte[]
	 */
	public byte[] transform(byte[] orignalArray, byte[] keyArray);
	
}
