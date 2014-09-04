package mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailData implements java.io.Serializable {
	private static final long serialVersionUID = -2056776648137773196L;
	private String subject = null;
	private AddressData from = null;
	private String content = null;
	private Date sendDate = null;
	private boolean text = false;
	private List<AddressData> toAddresses = null;
	private List<AddressData> ccAddresses = null;
	private List<AddressData> bccAddresses = null;
	private List<File> attachList = null;

	public EmailData() {
		toAddresses = new ArrayList<AddressData>();
		ccAddresses = new ArrayList<AddressData>();
		bccAddresses = new ArrayList<AddressData>();
		attachList = new ArrayList<File>();
	}

	public EmailData(String subject, String from, String to) {
		this();
		setSubject(subject);
		setFrom(AddressData.createAddressData(from));
		addAddressTo(AddressData.createAddressData(to));
	}

	public boolean isText() {
		return text;
	}

	public void setTextContent() {
		text = true;
	}

	public void setHtmlContent() {
		text = false;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setFrom(AddressData from) {
		this.from = from;
	}

	public AddressData getFrom() {
		return from;
	}

	public List<File> addAttachFile(File file) {
		attachList.add(file);
		return attachList;
	}

	public void setAttachFile(List<File> list) {
		attachList.clear();
		attachList.addAll(list);
	}

	public void clearAttachFiles() {
		attachList.clear();
	}

	public void addAddressTo(AddressData to) {
		toAddresses.add(to);
	}

	public void addAddressCc(AddressData cc) {
		ccAddresses.add(cc);
	}

	public void addAddressBcc(AddressData bcc) {
		bccAddresses.add(bcc);
	}

	public List<File> getAttachList() {
		return attachList;
	}

	public List<AddressData> getToAddresses() {
		return toAddresses;
	}

	public List<AddressData> getCcAddresses() {
		return ccAddresses;
	}

	public List<AddressData> getBccAddresses() {
		return bccAddresses;
	}

	public InternetAddress[] getToInternetAddress() throws AddressException,
			UnsupportedEncodingException {
		if (toAddresses != null && toAddresses.size() > 0) {
			InternetAddress[] array = new InternetAddress[toAddresses.size()];
			for (int i = 0; i < toAddresses.size(); i++) {
				array[i] = toAddresses.get(i).getInternetAddress();
			}
			return array;
		}
		return null;
	}

	public InternetAddress[] getCcInternetAddress() throws AddressException,
			UnsupportedEncodingException {
		if (ccAddresses != null && ccAddresses.size() > 0) {
			InternetAddress[] array = new InternetAddress[ccAddresses.size()];
			for (int i = 0; i < ccAddresses.size(); i++) {
				array[i] = ccAddresses.get(i).getInternetAddress();
			}
			return array;
		}
		return null;
	}

	public InternetAddress[] getBccInternetAddress() throws AddressException,
			UnsupportedEncodingException {
		if (bccAddresses != null && bccAddresses.size() > 0) {
			InternetAddress[] array = new InternetAddress[bccAddresses.size()];
			for (int i = 0; i < bccAddresses.size(); i++) {
				array[i] = bccAddresses.get(i).getInternetAddress();
			}
			return array;
		}
		return null;
	}

	
	
}
