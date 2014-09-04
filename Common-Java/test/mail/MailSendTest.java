package mail;

import java.util.Date;

import mail.app.AutoMail;

public class MailSendTest {

	public static void main(String[] args) {
		final EmailData data = new EmailData();
		data.setFrom(AddressData.createAddressData("Dailey Dai[dailey_dai@amaxgs.com]"));
		data.setSubject("Crazy");
		data.setContent("<b>Hi,</b><br>Just for make you crazy!!!");
		//data.addAttachFile(new File("E:\\Prod_log.LDF"));
		data.setHtmlContent();
		data.setSendDate(new Date());
		//data.addAddressTo(AddressData
		//		.createAddressData("steven_chen@amaxgs.com"));
		data.addAddressTo(AddressData
				.createAddressData("daiminjie1987@163.com"));
		AutoMail autoMail = new AutoMail("D:\\AutoMail");
		autoMail.setEmailDataManager(new EmailDataBuilder() {
			@Override
			public EmailData buildEmailData(String mailfile) throws Exception {
				return data;
			}

			@Override
			public void storeEmailData(EmailData data) {
				
			}

		});
		try {
			int i = 49;
			while (i < 50) {
				autoMail.sendMail();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
