package com.pipl.api.data.fields;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * Job information of a person.
 */
public class Job extends AbstractField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	public String title;
	@Expose
	public String organization;
	@Expose
	public String industry;
	@Expose
	@SerializedName("date_range")
	public DateRange dateRange;
	@Expose
	public String display;
	
	public Job() {
	}

	/**
	 * @param title
	 *            title
	 * @param organization
	 *            organization
	 * @param industry
	 *            industry
	 */
	public Job(String title, String organization, String industry) {
		setTitle(title);
		setOrganization(organization);
		setIndustry(industry);
	}

	private Job(Builder builder) {
		this(builder.title, builder.organization, builder.industry);
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

	public String getDisplay() {
		return display;
	}

	public String toString() {
		if (display!=null)
			return display;
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

		public Job build() {
			return new Job(this);
		}

	}

}