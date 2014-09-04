package mail.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

public class MailUtility {
	public static String[] getConfigString(String str) {
		String addstrs = str;
		if (addstrs == null || "".equals(addstrs)) {
			return new String[] {};
		}
		if (!addstrs.contains(";")) {
			return new String[] { addstrs };
		} else {
			String[] temp = addstrs.split(";");
			List<String> addressList = new ArrayList<String>();
			for (String address : temp) {
				if (validateConfig(address)) {
					addressList.add(address);
				}
			}
			String[] addresss = new String[addressList.size()];
			return addressList.toArray(addresss);
		}
	}

	public static boolean getConfigBoolean(String str, Properties pros) {
		try {
			String value = (String) pros.get(str);
			return Boolean.valueOf(value);
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean validateConfig(String str) {
		if (str == null || "".equals(str.trim())) {
			return false;
		}
		return true;
	}

	public static Properties getConfigProperties(String conpath, boolean isXML) {
		File file = new File(conpath);
		Properties mailPros = new Properties();
		try {
			if (!isXML)
				mailPros.load(new FileInputStream(file));
			else
				mailPros.loadFromXML(new FileInputStream(file));
			return mailPros;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Can not find:" + conpath);
		}
	}

	public static void storeConfigProperties(Properties mailPros,
			String conpath, boolean isXML) {
		if(conpath==null)
			return;
		File file = new File(conpath);
		try {
			if (!isXML)
				mailPros.store(new FileOutputStream(file),
						new Date().toString());
			else
				mailPros.storeToXML(new FileOutputStream(file),
						new Date().toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Can not store:" + conpath);
		}
	}

	public static Properties getConfigProperties(String conpath) {
		return getConfigProperties(conpath, false);
	}

	@SuppressWarnings("deprecation")
	public static int diffDay(Date current, Date latest) {
		long diff = new Date(current.getYear(), current.getMonth(),
				current.getDate()).getTime()
				- new Date(latest.getYear(), latest.getMonth(),
						latest.getDate()).getTime();
		return (int) (diff / (24 * 60 * 60 * 1000));
	}

	public static void main(String[] args) {
		Date current=GregorianCalendar.getInstance().getTime();
		System.out.println(current);
	}
}
