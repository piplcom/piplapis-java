package com.pipl.api.data.fields;

import com.google.gson.annotations.Expose;
import com.pipl.api.data.Utils;


/**
 * An origin country of the person
 * The value is a ISO 3166 alpha-2 country code.
 */
public class OriginCountry extends AbstractField {
	private static final long serialVersionUID = -3594566552580966053L;
	@Expose
	public String country;

	public OriginCountry() {
	}

	public String getCountry() {
		return country;
	}

	public String toString() {
		return Utils.COUNTRIES.get(country);
	}

}
