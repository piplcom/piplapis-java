package com.pipl.api.data.fields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * Name of a person.
 */
public class Name extends DisplayField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashSet<String> types = new HashSet<String>(Arrays.asList(
			"present", "maiden", "former", "alias"));
	@Expose
	private String prefix;
	@Expose
	private String first;
	@Expose
	private String middle;
	@Expose
	private String last;
	@Expose
	private String suffix;
	@Expose
	private String raw;
	@Expose
	@SerializedName("@type")
	private String type;

	private Name(Builder builder) {
		this(builder.prefix, builder.first, builder.middle, builder.last,
				builder.suffix, builder.raw, builder.type, builder.validSince);
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public void setType(String type) {
		if (type != null && !type.isEmpty()) {
			validateType(type, types);
			this.type = type;
		}
	}

	/**
	 * @param prefix
	 *            name prefix
	 * @param first
	 *            first name
	 * @param middle
	 *            middle name
	 * @param last
	 *            last name
	 * @param suffix
	 *            name suffix
	 * @param raw
	 *            `raw` is an unparsed name like "Eric T Van Cartman", usefull
	 *            when you want to search by name and don't want to work hard to
	 *            parse it. Note that in response data there's never name.raw,
	 *            the names in the response are always parsed, this is only for
	 *            querying with an unparsed name.
	 * @param type
	 *            type is one of "present", "maiden", "former", "alias".
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 */
	public Name(String prefix, String first, String middle, String last,
			String suffix, String raw, String type, Date validSince) {
		super(validSince);
		setPrefix(prefix);
		setFirst(first);
		setMiddle(middle);
		setLast(last);
		setSuffix(suffix);
		setRaw(raw);
		setType(type);
	}

	public String getPrefix() {
		return prefix;
	}

	public String getFirst() {
		return first;
	}

	public String getMiddle() {
		return middle;
	}

	public String getLast() {
		return last;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getRaw() {
		return raw;
	}

	public String getType() {
		return type;
	}

	@Override
	public String display() {
		Object val;
		ArrayList<String> existingAttrs = new ArrayList<String>();
		for (String childAttr : Arrays.asList("prefix", "first", "middle",
				"last", "suffix")) {
			try {
				val = getClass().getDeclaredField(childAttr).get(this);
				if (val != null) {
					existingAttrs.add(val.toString());
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		return Utils.join(" ", existingAttrs);
	}

	@Override
	public String toString() {
		return display();
	}

	/**
	 * A bool value that indicates whether the name is a valid name to search
	 * by.
	 * 
	 * @return boolean
	 */
	public boolean isSearchable() {
		String tmpFirst = Utils.getNotNullString(first);
		String tmpLast = Utils.getNotNullString(last);
		String tmpRaw = Utils.getNotNullString(raw);
		return (Utils.alphaChars(tmpFirst).length() >= 2 && Utils.alphaChars(
				tmpLast).length() >= 2)
				|| Utils.alphaChars(tmpRaw).length() >= 4;
	}

	public static class Builder {
		private String prefix;
		private String first;
		private String middle;
		private String last;
		private String suffix;
		private String raw;
		private String type;
		private Date validSince;

		public Builder prefix(String prefix) {
			this.prefix = prefix;
			return this;
		}

		public Builder first(String first) {
			this.first = first;
			return this;
		}

		public Builder middle(String middle) {
			this.middle = middle;
			return this;
		}

		public Builder last(String last) {
			this.last = last;
			return this;
		}

		public Builder suffix(String suffix) {
			this.suffix = suffix;
			return this;
		}

		public Builder raw(String raw) {
			this.raw = raw;
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

		public Name build() {
			return new Name(this);
		}
	}
}