package mail.app;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import mail.EmailData;
import mail.EmailDataBuilder;
import mail.MailSender;
import mail.utility.MailUtility;

public class AutoMail {
	public static final String SMTP = "smtp-server";
	public static final String ACCOUNT = "account";
	public static final String PASS = "pass";
	public static final String MAILS_SEND_LINK = "mails_lk";
	private static String CONDIR = System.getProperty("user.dir");
	public static final String CONFILE = "mail.ini";

	private Properties mailPros = null;
	private MailSender sender = null;
	private EmailDataBuilder emailManager = null;

	public AutoMail() {
		initMail();
	}

	public void setEmailDataManager(EmailDataBuilder emailManager) {
		this.emailManager = emailManager;
	}

	private void initMail() {
		String conpath = CONDIR + File.separator + CONFILE;
		initMailPro(conpath);
		sender = new MailSender();
		sender.configMail(mailPros.getProperty(SMTP),
				mailPros.getProperty(ACCOUNT), mailPros.getProperty(PASS));
		emailManager = EmailDataManager.getInstance();
	}

	public AutoMail(String condir) {
		if (new File(condir).exists()) {
			CONDIR = condir;
		}
		initMail();
	}

	protected void generateConfigFile() {
		String conpath = CONDIR + File.separator + CONFILE;
		File file = new File(conpath);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		try {
			file.createNewFile();
			PrintStream pout = new PrintStream(file);
			pout.println(SMTP + "=");
			pout.println(ACCOUNT + "=");
			pout.println(PASS + "=");
			// pout.println(FROM + "=");
			// pout.println(TO + "=");
			// pout.println(CC + "=");
			// pout.println(BCC + "=");
			// pout.println(TITLE + "=");
			// pout.println(MAILS_LINK + "=");
			// pout.println(DATE + "=");
			// pout.println(ATTACH_LINK + "=");
			// pout.println(BACK_LINK + "=");
			pout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendMail() throws Exception {
		for (String file : MailUtility.getConfigString(mailPros
				.getProperty(MAILS_SEND_LINK))) {
			try {
				EmailData data = emailManager.buildEmailData(file);
				if (data == null)
					continue;
				sender.setEmail(data);
				sender.sendMail();
				emailManager.storeEmailData(data);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	private void initMailPro(String conpath) {
		mailPros = MailUtility.getConfigProperties(conpath);
	}

	public static void main(String[] args) {
		String condir = "";
		if (args.length >= 2) {
			if ("-c".equalsIgnoreCase(args[0])) {
				condir = args[1];
			} else {
				System.out.println("Invalid Command.");
				System.exit(0);
			}
		} else if (args.length == 1) {
			if ("/?".equalsIgnoreCase(args[0])
					|| "-?".equalsIgnoreCase(args[0])
					|| "-help".equalsIgnoreCase(args[0])) {
				System.out.println("COM -c [config file dir]");
			} else if ("-c".equalsIgnoreCase(args[0])) {

			} else {
				System.out.println("Invalid Command.");
				System.exit(0);
			}
		} else {
			System.out.println("Invalid Command.");
			System.exit(0);
		}

		AutoMail auto = new AutoMail(condir);
		try {
			auto.sendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
