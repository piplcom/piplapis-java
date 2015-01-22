package com.pipl.api.data.fields;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A URL that's related to a person. Can either be a source of data
 * about the person, or a URL otherwise related to the person.
 */
public class Url extends AbstractField {
	private static final long serialVersionUID = 1L;
	@Expose
	public String url;
	@Expose
	@SerializedName("@category")
	public String category;
	@Expose
	@SerializedName("@sponsored")
	public Boolean sponsored;
	@Expose
	@SerializedName("@domain")
	public String domain;
	@Expose
	@SerializedName("@name")
	public String name;

	public Url() {
	}

	public String getUrl() {
		return url;
	}

	public String getCategory() {
		return category;
	}

	public boolean getSponsored() {
		if (sponsored==null)
			return false;
		return sponsored;
	}

	public String getDomain() {
		return domain;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		if (url!=null)
			return url;
		return "";
	}
}
