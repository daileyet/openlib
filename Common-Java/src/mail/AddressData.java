package mail;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class AddressData implements java.io.Serializable {
	private static final long serialVersionUID = 3287903336528916633L;
	private String address;
	private String name;

	public static AddressData createAddressData(String addressname) {
		String address = null;
		String name = null;
		if (addressname.indexOf("[") != -1 && addressname.indexOf("]") != -1) {
			address = addressname.substring(addressname.indexOf("[") + 1,
					addressname.indexOf("]"));
			name = addressname.substring(0, addressname.indexOf("["));
		} else {
			address = addressname;
		}
		return new AddressData(address, name);
	}

	public AddressData(String address, String name) {
		super();
		this.address = address;
		this.name = name;
	}

	private String decode(String name) throws UnsupportedEncodingException {
		if (name != null)
			return new String(name.getBytes("ISO8859-1"));
		return null;
	}

	public InternetAddress getInternetAddress()
			throws UnsupportedEncodingException, AddressException {
		if (getName() != null) {
			return new InternetAddress(address, decode(name));
		} else {
			return new InternetAddress(address);
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressData other = (AddressData) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
