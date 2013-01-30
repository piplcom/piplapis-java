package com.pipl.api.data.fields;

import java.util.Date;
import com.google.gson.annotations.Expose;

/**
 * An ID associated with a person.
 * <p/>
 * The ID is a string that's used by the site to uniquely identify a person,
 * it's guaranteed that in the site this string identifies exactly one person.
 */
public class UserID extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private String content;

	public UserID(Date validSince, String content) {
		super(validSince);
		setContent(content);
	}

	private UserID(Builder builder) {
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

		public UserID build() {
			return new UserID(this);
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}