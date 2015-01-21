package com.pipl.api.data.containers;


import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A source objects holds the data retrieved from a specific source.
 * <p/>
 * Every source object is based on the URL of the page where the
 * data is available, and the data itself that comes as field
 * objects (Name, Address, Email etc).
 * <p/>
 * Each type of field has its own container (note that Source is a subclass of
 * FieldsContainer).
 * <p/>
 * Sources come as results for a query and therefore they have attributes that
 * indicate if and how much they match the query. They also have a validity
 * timestamp available as an attribute.
 */

public class Source extends FieldsContainer {
	private static final long serialVersionUID = 1L;
	@Expose
	@SerializedName("@id")
	public String id;
	@Expose
	@SerializedName("@category")
	public String category;
	@Expose
	@SerializedName("@name")
	public String name;
	@Expose
	@SerializedName("@origin_url")
	public String origin_url;
	@Expose
	@SerializedName("@domain")
	public String domain;
	@Expose
	@SerializedName("@person_id")
	public String person_id;
	@Expose
	@SerializedName("@match")
	public float match;
	@Expose
	@SerializedName("@sponsored")
	public boolean sponsored;
	@Expose
	@SerializedName("@premium")
	public boolean premium;
	@Expose
	@SerializedName("@valid_since")
	public Date validSince;

	public String getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public String getOrigin_url() {
		return origin_url;
	}

	public String getDomain() {
		return domain;
	}

	public String getPerson_id() {
		return person_id;
	}

	public float getMatch() {
		return match;
	}

	public boolean isSponsored() {
		return sponsored;
	}

	public boolean isPremium() {
		return premium;
	}

	public Date getValidSince() {
		return validSince;
	}

}
