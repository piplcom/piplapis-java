package com.pipl.api.data.containers;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.fields.Field;

/**
 * Another person related to this person.
 */
public class Relationship extends FieldsContainer implements Field {
	private static final long serialVersionUID = 1L;
	@Expose
	@SerializedName("@valid_since")
	public Date validSince;
	@Expose
	@SerializedName("@inferred")
	public Boolean inferred;
	@Expose
	@SerializedName("@type")
	public String type;
	@Expose
	@SerializedName("@subtype")
	public String subtype;

	public String getType() {
		return type;
	}

	public String getSubtype() {
		return subtype;
	}
	
	@Override
	public Date getValidSince() {
		return validSince;
	}

	@Override
	public boolean isInferred() {
		if (inferred==null)
			return false;
		return inferred;
	}
	
	@Override
	public boolean isSearchable() {
		return true;
	}
}