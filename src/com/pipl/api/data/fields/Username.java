package com.pipl.api.data.fields;

import java.util.Date;
import com.google.gson.annotations.Expose;
import com.pipl.api.data.Utils;

/**
 * A username/screen-name associated with the person.
 * <p/>
 * Note that even though in many sites the username uniquely identifies one
 * person it's not guaranteed, some sites allow different people to use the same
 * username.
 */
public class Username extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private String content;

	/**
	 * Constructor
	 * 
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 * @param content
	 *            `content` is the username itself.
	 */
	public Username(Date validSince, String content) {
		super(validSince);
		setContent(content);
	}

	private Username(Builder builder) {
		this(builder.validSince, builder.content);
	}

	public static class Builder {
		private Date validSince;
		private String content;

		public Builder validSince(Date validSince) {
			this.validSince = validSince;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Username build() {
			return new Username(this);
		}
	}

	/**
	 * A bool value that indicates whether the username is a valid username to
	 * search by.
	 * 
	 * @return boolean
	 */
	public boolean isSearchable() {
		return content != null && Utils.alnumChars(content).length() >= 4;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}