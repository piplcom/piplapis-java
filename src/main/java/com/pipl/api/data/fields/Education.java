package com.pipl.api.data.fields;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * Education information of a person.
 */
public class Education extends AbstractField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	public String degree;
	@Expose
	public String school;
	@Expose
	@SerializedName("date_range")
	public DateRange dateRange;
	@Expose
	public String display;

	public Education() {
	}
	
	/**
	 * @param degree
	 *            degree
	 * @param school
	 *            school
	 */
	public Education(String degree, String school) {
		setDegree(degree);
		setSchool(school);
	}

	private Education(Builder builder) {
		this(builder.degree, builder.school);
	}

	public static class Builder {
		private String degree;
		private String school;

		public Education build(){
			return new Education(this);
		}
		public Builder degree(String degree) {
			this.degree = degree;
			return this;
		}

		public Builder school(String school) {
			this.school = school;
			return this;
		}

	}

	public String toString() {
		if (display!=null)
			return display;
		String result = "";
		if (!Utils.isNullOrEmpty(getDegree())
				&& !Utils.isNullOrEmpty(getSchool())) {
			result = getDegree() + " from " + getSchool();
		} else if (!Utils.isNullOrEmpty(getDegree())) {
			result = getDegree();
		} else if (!Utils.isNullOrEmpty(getSchool())) {
			result = getSchool();
		}
		if (!Utils.isNullOrEmpty(result) && getDateRange() != null) {
			result = result + " (" + getDateRange().yearsRange().get(0) + "-"
					+ getDateRange().yearsRange().get(1) + ") ";
		}
		return result.trim();
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public String getDisplay() {
		return display;
	}
}