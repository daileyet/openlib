package mail;

public interface EmailDataBuilder {
	public EmailData buildEmailData(String mailfile) throws Exception;

	public void storeEmailData(EmailData data);
}
