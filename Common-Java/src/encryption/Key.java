package encryption;
/**
 * the key for encrypt and decryption 
 * @author dailey_dai
 *
 * Jan 3, 2012
 */
public interface Key {
	
	public byte[] encry();
	
	public byte[] decry();
	
}
