package com.pipl.api.data;


import java.util.Arrays;
import java.util.HashSet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.fields.Field;

/**
 * A source of data that's available in a Record/Person object.
 * <p/>
 * The source is simply the URL of the page where the data was found, for
 * convenience it also contains some meta-data about the data-source (like its
 * full name and the category it belongs to).
 * <p/>
 * Note that this class is a subclass of <code>Field</code> even though a source
 * is not exactly a field, it's just because the functionality implemented in
 * <code>Field</code> is useful here too.
 */
public class Source extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashSet<String> categories = new HashSet<String>(Arrays.asList("background_reports",
			"contact_details", "email_address", "media", "personal_profiles",
			"professional_and_business", "public_records", "publications",
			"school_and_classmates", "web_pages"));
	@Expose
	private String name;
	@Expose
	private String category;
	@Expose
	private String url;
	@Expose
	private String domain;
	@Expose
	@SerializedName("@is_sponsored")
	private Boolean isSponsored;

	/**
	 * @param name
	 *            name
	 * @param category
	 *            category is one of Source.categories.
	 * @param url
	 *            url
	 * @param domain
	 *            domain
	 * @param isSponsored
	 *            `is_sponsored` is a bool value that indicates whether the
	 *            source is from one of Pipl's sponsored sources.
	 */
	public Source(String name, String category, String url, String domain,
			Boolean isSponsored) {
		super(null);
		setName(name);
		setCategory(category);
		setUrl(url);
		setDomain(domain);
		setIsSponsored(isSponsored);
	}

	private Source(Builder builder) {
		this(builder.name, builder.category, builder.url, builder.domain,
				builder.isSponsored);
	}

	public static class Builder {
		private String name;
		private String category;
		private String url;
		private String domain;
		private Boolean isSponsored;

		public Source build() {
			return new Source(this);
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder category(String category) {
			this.category = category;
			return this;
		}

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		public Builder domain(String domain) {
			this.domain = domain;
			return this;
		}

		public Builder isSponsored(Boolean isSponsored) {
			this.isSponsored = isSponsored;
			return this;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Boolean isSponsored() {
		return isSponsored;
	}

	public void setIsSponsored(Boolean isSponsored) {
		this.isSponsored = isSponsored;
	}

	public static HashSet<String> getCategories() {
		return categories;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getUrl() {
		return url;
	}

	public String getDomain() {
		return domain;
	}

	/**
	 * A bool that indicates whether the URL is valid.
	 * 
	 * @return <code>true</code> if the provided url is valid;
	 *         <code>false</code> otherwise.
	 */
	public boolean isValidUrl() {
		return Utils.isValidUrl(url);
	}

	/**
	 * Iterates over source categories and throws
	 * <code>IllegalArgumentException</code> if some of them are invalid.
	 * 
	 * @param categories
	 *            Set of categories to be validated
	 * @throws IllegalArgumentException
	 */
	public static void validateCategories(HashSet<String> categories)
			throws IllegalArgumentException {
		for (String cat : categories) {
			if (!Source.categories.contains(cat)) {
				throw new IllegalArgumentException("Invalid categories: '"
						+ cat + "' is invalid");
			}
		}
	}
}