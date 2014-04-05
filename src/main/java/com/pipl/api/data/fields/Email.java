package com.pipl.api.data.fields;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * An email address of a person with the md5 of the address, might come in some
 * cases without the address itself and just the md5 (for privacy reasons)
 */
public class Email extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String regex = "^[\\w.%\\-+]+@[\\w.%\\-]+\\.[a-zA-Z]{2,6}$";
	private static HashSet<String> types = new HashSet<String>(Arrays.asList(
			"personal", "work"));
	@Expose
	private String address;
	@Expose
	@SerializedName("address_md5")
	private String addressMd5;
	@Expose
	@SerializedName("@type")
	private String type;

	/**
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 * @param address
	 *            email Address
	 * @param addressMd5
	 *            addressMd5
	 * @param type
	 *            type is one of "work", "personal".
	 */
	public Email(Date validSince, String address, String addressMd5, String type) {
		super(validSince);
		setAddress(address);
		setAddressMd5(addressMd5);
		setType(type);
	}

	private Email(Builder builder) {
		this(builder.validSince, builder.address, builder.address_md5,
				builder.type);
	}

	public static class Builder {
		private String address;
		private String address_md5;
		private String type;
		private Date validSince;

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder address_md5(String address_md5) {
			this.address_md5 = address_md5;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder validSince(Date validSince) {
			this.validSince = validSince;
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
		return isValidEmail();
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
			return address
					.substring(address.indexOf("@") + 1, address.length());
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

	public void setType(String type) {
		if(!Utils.isNullOrEmpty(type))
		{
			validateType(type,types);
			this.type = type;
		}
	}

}