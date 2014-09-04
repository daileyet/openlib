package mail.app;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import mail.AddressData;
import mail.EmailData;
import mail.EmailDataBuilder;
import mail.app.backup.EmailDataStore;
import mail.app.backup.StorePath;
import mail.utility.MailUtility;

public final class EmailDataManager implements EmailDataBuilder {
	public static final String FROM = "from";
	public static final String TO = "to";
	public static final String CC = "cc";
	public static final String BCC = "bcc";
	public static final String DATE = "senddate";
	public static final String TITLE = "title";
	public static final String ATTACH_LINK = "atc_lk";
	public static final String BACK_LINK = "bak_lk";
	public static final String ISHTML = "ishtml";
	public static final String CONTEXT = "ctx";
	public static final String MAILS_BACK_ENABLE = "bak_enable";
	public static final String MAILS_SEND_EXPIRATIONDAY = "expirationday";
	public static final String MAILS_SEND_UNIQUE = "unique";

	private Properties mailPros = null;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private EmailDataStore dataStore = new EmailDataStore();

	private String mailfile;

	
	public static EmailDataManager getInstance(){
		return new EmailDataManager();
	}
	
	@SuppressWarnings("deprecation")
	public EmailData buildEmailData(String mailfile) throws IOException,
			ParseException {
		this.mailfile=mailfile;
		mailPros = MailUtility.getConfigProperties(mailfile, true);
		Date now = new Date();
		String ctxpath = mailPros.getProperty(CONTEXT);
		File ctxfile = new File(ctxpath);
		if (ctxfile.exists()) {
			Date modifyDate = new Date(ctxfile.lastModified());
			int expiration = getExpirationday();
			mailPros.setProperty(MAILS_SEND_EXPIRATIONDAY, String.valueOf(expiration));
			int different = MailUtility.diffDay(now, modifyDate);
			if (expiration!=-1 && different > expiration) {
				return null;
			}
		}
		EmailData data = new EmailData();
		data.setFrom(AddressData.createAddressData(mailPros.getProperty(FROM)));
		for (String to : getAddress(TO)) {
			data.addAddressTo(AddressData.createAddressData(to));
		}
		for (String cc : getAddress(CC)) {
			data.addAddressCc(AddressData.createAddressData(cc));
		}
		for (String bcc : getAddress(BCC)) {
			data.addAddressBcc(AddressData.createAddressData(bcc));
		}
		if ("YES".equalsIgnoreCase(mailPros.getProperty(ISHTML))) {
			data.setHtmlContent();
		} else {
			data.setTextContent();
		}
		data.setSubject(new String(mailPros.getProperty(TITLE).getBytes(
				"ISO8859-1")));
		data.setContent(getContent());
		for (String atc : getAddress(ATTACH_LINK)) {
			data.addAttachFile(new File(atc));
		}
		String dateStr = mailPros.getProperty(DATE);

		if (dateStr == null || "".equals(dateStr.trim())) {
			data.setSendDate(now);
			mailPros.setProperty(DATE, dateFormat.format(now));
			MailUtility.storeConfigProperties(mailPros, mailfile, true);
		} else {
			Date date = dateFormat.parse(dateStr);
			boolean unique = MailUtility.getConfigBoolean(MAILS_SEND_UNIQUE,
					mailPros);
			mailPros.setProperty(MAILS_SEND_UNIQUE,String.valueOf(unique));
			if (unique && now.getYear() == date.getYear()
					&& now.getMonth() == date.getMonth()
					&& now.getDay() == date.getDay()) {
				data = null;
			} else {
				data.setSendDate(now);
				mailPros.setProperty(DATE, dateFormat.format(now));
				MailUtility.storeConfigProperties(mailPros, mailfile, true);
			}
		}

		return data;
	}

	private String[] getAddress(String type) {
		return MailUtility.getConfigString(mailPros.getProperty(type));
	}

	private EmailDataManager() {
	}

	protected int getExpirationday() {
		try {
			String value = (String) mailPros.get(MAILS_SEND_EXPIRATIONDAY);
			int expiration = Integer.valueOf(value);
			return expiration >= 0 ? expiration : -1;
		} catch (NumberFormatException e) {
		}
		return -1;
	}

	protected String getContent() throws IOException {
		StringBuilder builder = new StringBuilder();
		char[] buf = new char[1];
		String ctx = mailPros.getProperty(CONTEXT);
		FileReader reader = new FileReader(ctx);
		while (reader.read(buf) != -1) {
			builder.append(buf);
		}
		reader.close();
		return builder.toString();
	}

	@Override
	public void storeEmailData(EmailData data) {
		String storePath = mailPros.getProperty(BACK_LINK);
		mailPros.setProperty(BACK_LINK, StorePath.getStorePath(storePath).getStorePath());
		boolean isStore=MailUtility.getConfigBoolean(MAILS_BACK_ENABLE, mailPros);
		mailPros.setProperty(MAILS_BACK_ENABLE, String.valueOf(isStore));
		if (isStore) {
			dataStore.setStorePath(StorePath.getStorePath(storePath));
			dataStore.store(data);
		}
		MailUtility.storeConfigProperties(mailPros, mailfile, true);
	}
}
