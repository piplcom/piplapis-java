package com.pipl.api.data.fields;


import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * Job information of a person.
 */
public class Job extends DisplayField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private String title;
	@Expose
	private String organization;
	@Expose
	private String industry;
	@Expose
	@SerializedName("date_range")
	private DateRange dateRange;

	private Job(Builder builder) {
		this(builder.validSince, builder.title, builder.organization,
				builder.industry, builder.dateRange);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/**
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 * @param title
	 *            title
	 * @param organization
	 *            organization
	 * @param industry
	 *            industry
	 * @param dateRange
	 *            `dateRange` is A <code>DateRange</code> object
	 *            (piplapis.data.fields.DateRange), that's the time the person
	 *            held this job.
	 */
	public Job(Date validSince, String title, String organization,
			String industry, DateRange dateRange) {
		super(validSince);
		setTitle(title);
		setOrganization(organization);
		setIndustry(industry);
		setDateRange(dateRange);
	}

	@Override
	public String display() {
		String result = "";
		if (!Utils.isNullOrEmpty(getTitle()) && !Utils.isNullOrEmpty(getOrganization())) {
			result = result + getTitle() + " at " + getOrganization();
		} else {
			result = result + (getTitle() == null ? "" : getTitle())
					+ (getOrganization() == null ? "" : getOrganization());
		}
		if (!Utils.isNullOrEmpty(getIndustry())) {
			if (getDateRange() != null) {
				if (Utils.isNullOrEmpty(result)) {
					result = getIndustry() + " ("
							+ getDateRange().yearsRange().get(0) + "-"
							+ getDateRange().yearsRange().get(1) + ")";
				} else {
					result = result + " (" + getIndustry() + ", "
							+ getDateRange().yearsRange().get(0) + "-"
							+ getDateRange().yearsRange().get(1) + ")";
				}
			} else {
				if (Utils.isNullOrEmpty(result)) {
					result = getIndustry();
				} else {
					result = result + " (" + getIndustry() + ")";
				}
			}
		} else {
			if (getDateRange() != null && !Utils.isNullOrEmpty(result)) {
				result = result + " (" + getDateRange().yearsRange().get(0) + "-"
						+ getDateRange().yearsRange().get(1) + ")";
			}
		}
		return result;
	}

	public static class Builder {
		private String title;
		private String organization;
		private String industry;
		private DateRange dateRange;
		private Date validSince;

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder organization(String organization) {
			this.organization = organization;
			return this;
		}

		public Builder industry(String industry) {
			this.industry = industry;
			return this;
		}

		public Builder dateRange(DateRange dateRange) {
			this.dateRange = dateRange;
			return this;
		}

		public Builder validSince(Date validSince) {
			this.validSince = validSince;
			return this;
		}

		public Job build() {
			return new Job(this);
		}

	}

}