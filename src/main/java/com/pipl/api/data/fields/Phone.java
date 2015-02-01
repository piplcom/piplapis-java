package com.pipl.api.data.fields;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A phone number of a person.
 */
public class Phone extends AbstractField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	@SerializedName("country_code")
	public Integer countryCode;
	@Expose
	public Long number;
	@Expose
	public Integer extension;
	@Expose
	public String raw;
	@Expose
	@SerializedName("@type")
	public String type;
	@Expose
	public String display;
	@Expose
	@SerializedName("display_international")
	public String displayInternational;

	public Phone() {
	}
	
	/**
	 * @param raw
	 *            This value will be parsed by Pipl. 
	 */
	public Phone(String raw) {
		setRaw(raw);
	}
	
	/**
	 * @param countryCode
	 *            countryCode
	 * @param number
	 *            number
	 * @param extension
	 *            extension
	 */
	public Phone(Integer countryCode, Long number, Integer extension) {
		setCountryCode(countryCode);
		setNumber(number);
		setExtension(extension);
	}

	private Phone(Builder builder) {
		this(builder.countryCode, builder.number, builder.extension);
	}

	public static class Builder {
		private Integer countryCode;
		private Long number;
		private Integer extension;

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
	}

	/**
	 * @return is this number a valid input to search by. 
	 */
	public boolean isSearchable() {
		return number!=null || (raw!=null && !raw.isEmpty());
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

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getType() {
		return type;
	}

	public String getDisplay() {
		return display;
	}

	public String getDisplayInternational() {
		return displayInternational;
	}

	@Override
	public String toString() {
		if (displayInternational!=null)
			return displayInternational;
		if (display!=null)
			return display;
		if (raw!=null)
			return raw;
		StringBuilder sb = new StringBuilder();
		if (countryCode!=null)
			sb.append("+").append(countryCode).append(" ");
		if (number!=null)
			sb.append(number);
		if (extension!=null)
			sb.append(" x").append(extension);
		return sb.toString();
	}
}