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
	@Expose
	@SerializedName("@source_id")
	public String sourceId;

	public Url() {
	}
	
	public Url(String url) {
		setUrl(url);
	}

	/**
	 * @return whether the URL is a valid URL to search by.
	 */
	public boolean isSearchable() {
		return url!=null && !url.isEmpty();
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
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
	
	public String getSourceId() {
		return sourceId;
	}

	@Override
	public String toString() {
		if (url!=null)
			return url;
		return "";
	}
}
