package com.pipl.api.data.fields;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A general purpose element that holds any meaningful string that's related to
 * the person.
 *
 * Used for holding data about the person that either couldn't be clearly
 * classified or was classified as something different than the available data
 * fields.
 */
public class Tag extends AbstractField {
	private static final long serialVersionUID = 1L;
	@Expose
	public String content;
	@Expose
	@SerializedName("@classification")
	public String classification;

	public Tag() {
	}
	
	public String getContent() {
		return content;
	}

	public String getClassification() {
		return classification;
	}
	
	@Override
	public String toString() {
		return content;
	}

}