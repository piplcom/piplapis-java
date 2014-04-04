package com.pipl.api.data.fields;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A phone number of a person.
 */
public class Phone extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashSet<String> types = new HashSet<String>(Arrays.asList(
			"mobile", "home_phone", "home_fax", "work_phone", "work_fax",
			"pager"));
	@Expose
	@SerializedName("country_code")
	private Integer countryCode;
	@Expose
	private Long number;
	@Expose
	private Integer extension;
	@Expose
	@SerializedName("@type")
	private String type;
	@Expose
	private String display;
	@Expose
	@SerializedName("display_international")
	private String displayInternational;

	/**
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 * @param countryCode
	 *            countryCode
	 * @param number
	 *            number
	 * @param extension
	 *            extension
	 * @param type
	 *            type is one of "mobile", "home_phone", "home_fax",
	 *            "work_phone" , "pager".
	 */
	public Phone(Integer countryCode, Long number, Integer extension,
			String type, Date validSince) {
		super(validSince);
		setCountryCode(countryCode);
		setNumber(number);
		setExtension(extension);
		setType(type);
	}

	private Phone(Builder builder) {
		this(builder.countryCode, builder.number, builder.extension,
				builder.type, builder.validSince);
	}

	public static class Builder {
		private Integer countryCode;
		private Long number;
		private Integer extension;
		private String type;
		private Date validSince;

		public Phone build() {
			return new Phone(this);
		}

		public Builder countryCode(Integer countryCode) {
			this.countryCode = countryCode;
			return this;
		}

		public Builder number(Long number) {
			this.number = number;
			return this;
		}

		public Builder extension(Integer extension) {
			this.extension = extension;
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
	}

	/**
	 * Strip `text` from all non-digit chars and return a new Phone object with
	 * the number from text.
	 * 
	 * @param text
	 *            String to be parsed
	 * @return <code>Phone</code> object example :
	 *         <p/>
	 *         <blockquote>
	 * 
	 *         <pre>
	 * Phone phone = Phone.fromText(&quot;(888) 777-666&quot;);
	 * </pre>
	 * 
	 *         </blockquote>
	 *         <p/>
	 *         phone.number will be - 888777666
	 */
	public static Phone fromText(String text) {
		Long number = Long.parseLong(text.replaceAll("\\D", ""));
		return new Phone.Builder().number(number).build();
	}

	/**
	 * A bool value that indicates whether the address is a valid address to
	 * search by.
	 * 
	 * @return boolean
	 */
	public boolean isSearchable() {
		return number != null && (countryCode == null || countryCode == 1);
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Integer getExtension() {
		return extension;
	}

	public void setExtension(Integer extension) {
		this.extension = extension;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type != null && !type.isEmpty()) {
			validateType(type, types);
			this.type = type;
		}
	}

	public String getDisplay() {
		if (display == null) {
			return "";
		}
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getDisplayInternational() {
		if (displayInternational == null) {
			return "";
		}
		return displayInternational;
	}

	public void setDisplayInternational(String displayInternational) {
		this.displayInternational = displayInternational;
	}

}