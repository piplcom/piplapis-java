package com.pipl.api.name;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.fields.Address;


/**
 * Helper class for NameAPIResponse, holds a location and the estimated percent
 * of people with the name that lives in this location.
 * <p/>
 * Note that this class inherits from Address and therefore has the methods
 * countryFull, stateFull & display.
 */
public class LocationStats extends Address {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expose
	@SerializedName("estimated_percent")
	private int estimatedPercent;

	public LocationStats(String country, String state, String city,
			int estimatedPercent) {
		super(country, state, city, null, null, null, null, null, null, null);
		setEstimatedPercent(estimatedPercent);
	}

	public int getEstimatedPercent() {
		return estimatedPercent;
	}

	public void setEstimatedPercent(int estimatedPercent) {
		this.estimatedPercent = estimatedPercent;
	}

}
