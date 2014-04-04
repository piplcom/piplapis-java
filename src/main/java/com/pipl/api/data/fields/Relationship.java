package com.pipl.api.data.fields;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * Name of another person related to this person.
 */
public class Relationship extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashSet<String> types = new HashSet<String>(Arrays.asList(
			"friend", "family", "work", "other"));
	@Expose
	private Name name;
	@Expose
	@SerializedName("@type")
	private String type;
	@Expose
	@SerializedName("@subtype")
	private String subtype;

	/**
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 * @param name
	 *            name
	 * @param type
	 *            type is a String and one of "friend", "family", "work",
	 *            "other"
	 * @param subtype
	 *            `subtype` is a String and is not restricted to a specific list
	 *            of possible values (for example, if type_ is "family" then
	 *            subtype can be "Father", "Mother", "Son" and many other
	 *            things).
	 */
	public Relationship(Name name, String type, String subtype, Date validSince) {
		super(validSince);
		setName(name);
		setType(type);
		setSubtype(subtype);
	}

	private Relationship(Builder builder) {
		this(builder.name, builder.type, builder.subtype, builder.validSince);
	}

	public static class Builder {
		private Name name;
		private String type;
		private String subtype;
		private Date validSince;

		public Relationship build() {
			return new Relationship(this);
		}

		public Builder name(Name name) {
			this.name = name;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder subtype(String subtype) {
			this.subtype = subtype;
			return this;
		}

		public Builder validSince(Date validSince) {
			this.validSince = validSince;
			return this;
		}
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (!Utils.isNullOrEmpty(type)) {
			validateType(type,types);
			this.type = type;
		}
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
}