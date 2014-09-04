package mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
	private EmailData email = null;
	private Properties mailProes = null;

	public MailSender() {
	}

	public MailSender(EmailData email) {
		setEmail(email);
	}

	public void setEmail(EmailData email) {
		this.email = email;
	}

	public EmailData getEmail() {
		return email;
	}

	protected void setMailProes(Properties mailProes) {
		this.mailProes = mailProes;
	}

	public Properties getMailProes() {
		return mailProes;
	}

	public Properties configMail(String protocol, String server, boolean auth,
			String mailAddress, String loginPwd) {
		Properties pro = new Properties();
		pro.setProperty("mail.transport.protocol", protocol);
		pro.setProperty("mail.host", server);
		pro.setProperty("mail.smtp.auth", Boolean.toString(auth));
		pro.setProperty("mail.smtp.user", mailAddress);
		pro.setProperty("mail.smtp.password", loginPwd);
		setMailProes(pro);
		return pro;
	}

	public Properties configMail(String server, String mailAddress,
			String loginPwd) {
		return configMail("smtp", server, true, mailAddress, loginPwd);
	}

	public void sendMail() {
		sendMail(getMailProes());
	}

	public void sendMail(EmailData email) {
		setEmail(email);
		sendMail();
	}

	public void sendMail(Properties props) {
		try {
			Session session = Session.getDefaultInstance(props);
			Message msg = createMail(session);
			Transport transport = session.getTransport();
			transport.connect(props.getProperty("mail.smtp.user"),
					props.getProperty("mail.smtp.password"));
			transport.sendMessage(msg,
					msg.getRecipients(Message.RecipientType.TO));
			Address[] address = msg.getRecipients(Message.RecipientType.CC);
			if (address != null && address.length > 0)
				transport.sendMessage(msg, address);
			address = msg.getRecipients(Message.RecipientType.BCC);
			if (address != null && address.length > 0)
				transport.sendMessage(msg, address);
			transport.close();
		} catch (AddressException e) {
			throw new IllegalArgumentException(e);
		} catch (NoSuchProviderException e) {
			throw new IllegalArgumentException(e);
		} catch (MessagingException e) {
			throw new IllegalArgumentException(e);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	private Message createMail(Session session) throws AddressException,
			MessagingException, UnsupportedEncodingException {
		Message msg = new MimeMessage(session);
		msg.setFrom(email.getFrom().getInternetAddress());
		InternetAddress[] address = email.getToInternetAddress();
		msg.setRecipients(Message.RecipientType.TO, address);
		address = email.getCcInternetAddress();
		if (address != null)
			msg.setRecipients(Message.RecipientType.CC, address);
		address = email.getBccInternetAddress();
		if (address != null)
			msg.setRecipients(Message.RecipientType.BCC, address);
		msg.setSubject(email.getSubject());
		if (email.getSendDate() == null) {
			email.setSendDate(new Date());
		}
		msg.setSentDate(email.getSendDate());
		MimeMultipart mp = new MimeMultipart();

		if (!email.isText()) {
			mp.addBodyPart(createBody(email.getContent()));
			for (File attach : email.getAttachList()) {
				mp.addBodyPart(createAttachment(attach));
			}
			msg.setContent(mp);
		} else {
			msg.setText(email.getContent());
		}
		msg.saveChanges();
		return msg;
	}

	/**
	 * create mail body
	 * 
	 * @param content
	 * @return
	 */
	private BodyPart createBody(String content) {
		BodyPart bp = new MimeBodyPart();
		try {
			bp.setContent(
					"<meta http-equiv=Content-Type content=text/html; charset=gb2312>"
							+ content, "text/html;charset=GB2312");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return bp;
	}

	/**
	 * build attachment
	 * 
	 * @param file
	 * @param path
	 * @return
	 */
	private BodyPart createAttachment(File file) {
		BodyPart bp = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(file.getAbsolutePath());
		DataHandler dh = new DataHandler(fds);
		try {
			bp.setFileName(new String(file.getName()));
			bp.setDataHandler(dh);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return bp;
	}

}
