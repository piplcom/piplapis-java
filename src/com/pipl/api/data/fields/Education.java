package com.pipl.api.data.fields;


import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * Education information of a person.
 */
public class Education extends DisplayField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private String degree;
	@Expose
	private String school;
	@Expose
	@SerializedName("date_range")
	private DateRange dateRange;

	/**
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 * @param degree
	 *            degree
	 * @param school
	 *            school
	 * @param dateRange
	 *            `dateRange` is A <code>DateRange</code> object
	 *            (piplapis.data.fields.DateRange), that's the time the person
	 *            was studying.
	 */
	public Education(Date validSince, String degree, String school,
			DateRange dateRange) {
		super(validSince);
		setDegree(degree);
		setSchool(school);
		setDateRange(dateRange);
	}

	private Education(Builder builder) {
		this(builder.validSince, builder.degree, builder.school,
				builder.dateRange);
	}

	public static class Builder {
		private Date validSince;
		private String degree;
		private String school;
		private DateRange dateRange;

		public Education build(){
			return new Education(this);
		}
		public Builder validSince(Date validSince) {
			this.validSince = validSince;
			return this;
		}

		public Builder degree(String degree) {
			this.degree = degree;
			return this;
		}

		public Builder school(String school) {
			this.school = school;
			return this;
		}

		public Builder dateRange(DateRange dateRange) {
			this.dateRange = dateRange;
			return this;
		}
	}

	@Override
	public String display() {
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

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}
}