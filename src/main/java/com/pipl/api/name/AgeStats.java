package com.pipl.api.name;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Helper class for NameAPIResponse, holds an age range and the estimated
 * percent of people with the name that their age is within the range.
 */
public class AgeStats implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	@SerializedName("from_age")
	private Integer fromAge;
	@Expose
	@SerializedName("to_age")
	private Integer toAge;
	@Expose
	@SerializedName("estimated_percent")
	private Integer estimatedPercent;

	public AgeStats(Integer fromAge, Integer toAge, Integer estimatedPercent) {
		setFromAge(fromAge);
		setToAge(toAge);
		setEstimatedPercent(estimatedPercent);
	}

	public int getFromAge() {
		return fromAge;
	}

	public void setFromAge(Integer fromAge) {
		this.fromAge = fromAge;
	}

	public int getToAge() {
		return toAge;
	}

	public void setToAge(Integer toAge) {
		this.toAge = toAge;
	}

	public int getEstimatedPercent() {
		return estimatedPercent;
	}

	public void setEstimatedPercent(Integer estimatedPercent) {
		this.estimatedPercent = estimatedPercent;
	}
}
