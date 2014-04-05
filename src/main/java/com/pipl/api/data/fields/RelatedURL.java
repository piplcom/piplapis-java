package com.pipl.api.data.fields;


import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * IMPORTANT: This URL is NOT the origin of the data about the person, it's just
 * an extra piece of information available on him.
 */
public class RelatedURL extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashSet<String> types = new HashSet<String>(Arrays.asList(
			"personal", "work", "blog"));
	@Expose
	private String content;
	@Expose
	@SerializedName("@type")
	private String type;

	/**
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 * @param content
	 *            `content` is the URL address itself.
	 * @param type
	 *            type is a string and is one of "personal", "work", "blog".
	 */
	public RelatedURL(Date validSince, String content, String type) {
		super(validSince);
		setContent(content);
		setType(type);
	}

	private RelatedURL(Builder builder) {
		this(builder.validSince, builder.content, builder.type);
	}

	public static class Builder {
		private Date validSince;
		private String content;
		private String type;

		public Builder validSince(Date validSince) {
			this.validSince = validSince;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public RelatedURL build() {
			return new RelatedURL(this);
		}
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isValidUrl() {
		return Utils.isValidUrl(content);
	}

}
