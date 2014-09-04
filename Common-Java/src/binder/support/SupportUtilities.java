package binder.support;

public class SupportUtilities {

	public static String convertToString(Object object) {
		String val = null;
		if (object == null)
			val = "";
		else
			val = object.toString();

		return val;
	}

	public static boolean equals(Object oldVal, Object newVal) {
		if (oldVal == newVal)
			return true;

		if (oldVal != null && oldVal.equals(newVal))
			return true;

		return false;
	}

}
