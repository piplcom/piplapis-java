package com.pipl.api.data.fields;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A general purpose element that holds any meaningful string that's related to
 * the person.
 * <p/>
 * Used for holding data about the person that either couldn't be clearly
 * classified or was classified as something different than the available data
 * fields.
 */
public class Tag extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private String content;
	@Expose
	@SerializedName("@classification")
	private String classification;

	/**
	 * Constructor
	 * 
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 * @param content
	 *            `content` is the tag itself
	 * @param classification
	 *            classification of tag
	 */
	public Tag(Date validSince, String content, String classification) {
		super(validSince);
		setContent(content);
		setClassification(classification);
	}

	private Tag(Builder builder) {
		this(builder.validSince, builder.content, builder.classification);
	}

	public static class Builder {
		private Date validSince;
		private String content;
		private String classification;

		public Builder validSince(Date validSince) {
			this.validSince = validSince;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Builder type(String classification) {
			this.classification = classification;
			return this;
		}

		public Tag build() {
			return new Tag(this);
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

}