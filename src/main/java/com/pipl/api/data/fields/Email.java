package com.pipl.api.data.fields;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * An email address of a person with the md5 of the address, might come in some
 * cases without the address itself and just the md5 (for privacy reasons)
 */
public class Email extends AbstractField {
	private static final long serialVersionUID = 1L;
	private static String regex = "^[\\w.%\\-+]+@[\\w.%\\-]+\\.[a-zA-Z]{2,6}$";
	@Expose
	public String address;
	@Expose
	@SerializedName("address_md5")
	public String addressMd5;
	@Expose
	@SerializedName("@type")
	public String type;
	@Expose
	public Boolean displosable;
	@Expose
	@SerializedName("@email_provider")
	public Boolean emailProvider;

	public Email() {
	}
	
	/**
	 * @param address
	 *            email Address
	 * @param addressMd5
	 *            addressMd5
	 */
	public Email(String address, String addressMd5) {
		setAddress(address);
		setAddressMd5(addressMd5);
	}

	private Email(Builder builder) {
		this(builder.address, builder.address_md5);
	}

	public static class Builder {
		private String address;
		private String address_md5;

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder address_md5(String address_md5) {
			this.address_md5 = address_md5;
			return this;
		}

		public Email build() {
			return new Email(this);
		}

	}

	/**
	 * @return A bool value that indicates whether the address is a valid email
	 *         address.
	 */
	public boolean isValidEmail() {
		return address != null && address.matches(regex);
	}

	/**
	 * @return A bool value that indicates whether the address is a valid
	 *         address to search by.
	 */
	public boolean isSearchable() {
		return isValidEmail() || (addressMd5!=null && addressMd5.length()==32);
	}

	//

	/**
	 * @return The username part of the email or null if the email is invalid.
	 *         if address is 'eric@cartman.com' the this method will return
	 *         'eric'
	 */
	public String username() {
		if (isValidEmail()) {
			return address.substring(0, address.indexOf("@"));
		} else {
			return null;
		}
	}

	/**
	 * The domain part of the email or null if the email is invalid. if address
	 * is 'eric@cartman.com' then output will be 'cartman.com'
	 * 
	 * @return domain to which this email address belongs.
	 */
	public String domain() {
		if (isValidEmail()) {
			return address.substring(address.indexOf("@") + 1, address.length());
		} else {
			return null;
		}

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressMd5() {
		return addressMd5;
	}

	public void setAddressMd5(String addressMd5) {
		this.addressMd5 = addressMd5;
	}

	public String getType() {
		return type;
	}

	public boolean isDisplosable() {
		if (displosable==null)
			return false;
		return displosable;
	}

	public boolean getEmailProvider() {
		if (emailProvider==null)
			return false;
		return emailProvider;
	}

	@Override
	public String toString() {
		if (address!=null)
			return address;
		if (addressMd5!=null)
			return addressMd5;
		return "";
	}
}