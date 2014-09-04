package encryption;

import encryption.decryption.Decry;
import encryption.implement.DJDecry;
import encryption.implement.DJEncry;
import encryption.implement.DJKey;

public class EncryTest {

	public static void main(String[] args) {
		Encry encry = new DJEncry();
		Decry decry = new DJDecry();
		DJKey.setKeys("A>b?0:1".getBytes());
		byte[] array = encry.encry("mimahellworld".getBytes());
		System.out.println(new String(array));

		array = decry.decry(array);
		System.out.println(new String(array));
	}

}
