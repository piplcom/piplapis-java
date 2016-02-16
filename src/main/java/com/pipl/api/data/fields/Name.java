package com.pipl.api.data.fields;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * Name of a person.
 */
public class Name extends AbstractField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	public String prefix;
	@Expose
	public String first;
	@Expose
	public String middle;
	@Expose
	public String last;
	@Expose
	public String suffix;
	@Expose
	public String raw;
	@Expose
	public String display;
	@Expose
	@SerializedName("@type")
	public String type;
	
	public Name() {
	}

	/**
	 * @param raw
	 *            `raw` is an unparsed name like "Eric T Van Cartman", usefull
	 *            when you want to search by name and don't want to work hard to
	 *            parse it. Note that in response data there's never name.raw,
	 *            the names in the response are always parsed, this is only for
	 *            querying with an unparsed name.
	 */
	public Name(String raw) {
		setRaw(raw);
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
	 */
	public Name(String prefix, String first, String middle, String last,
			String suffix) {
		setPrefix(prefix);
		setFirst(first);
		setMiddle(middle);
		setLast(last);
		setSuffix(suffix);
	}

	private Name(Builder builder) {
		this(builder.prefix, builder.first, builder.middle, builder.last,
				builder.suffix);
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

	public String getDisplay() {
		return display;
	}

	@Override
	public String toString() {
		if (display!=null) {
			return display;
		}
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

	/**
	 * @return true if the name is a valid name to search by
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

		public Name build() {
			return new Name(this);
		}
	}
}