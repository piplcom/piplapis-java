package com.pipl.api.data.fields;


import java.util.Date;

import com.google.gson.annotations.Expose;
import com.pipl.api.data.Utils;

/**
 * A URL of an image of a person.
 */
public class Image extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private String url;

	/**
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 * @param url
	 *            url
	 */
	public Image(Date validSince, String url) {
		super(validSince);
		setUrl(url);
	}

	private Image(Builder builder) {
		this(builder.validSince, builder.url);
	}

	public static class Builder {
		private Date validSince;
		private String url;

		public Builder validSince(Date validSince) {
			this.validSince = validSince;
			return this;
		}

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		public Image build() {
			return new Image(this);
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isValidUrl() {
		return Utils.isValidUrl(getUrl());
	}

}