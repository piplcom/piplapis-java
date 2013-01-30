package com.pipl.api.search;

/***
 * A base class to be inherited by PriorityRule & RecordsFilter
 * @param domain
 *            String, for example "linkedin.com", "linkedin" is also
 *            possible and it'll match "linkedin.*".
 * @param category
 *            String, any one of the categories defined in
 *            piplapis.data.Source.categories.
 * @param sponsoredSource
 *            Boolean, True will bring the records that come from a
 *            sponsored source first and False will bring the non-sponsored
 *            records first.
 * @param queryParamsMatch
 *            True is the only possible value and it means you want to give
 *            a priority to records that match all the params you passed in
 *            the query.
 * @param queryPersonMatch
 *            True is the only possible value and it means you want to give
 *            a priority to records with higher queryPersonMatch (see the
 *            documentation of record.queryPersonMatch for more details)
 */
public class BaseFilter {
	private String domain;
	private String category;
	private Boolean sponsoredSource;
	private Boolean queryParamsMatch;
	private Boolean queryPersonMatch;
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getSponsoredSource() {
		return sponsoredSource;
	}

	public void setSponsoredSource(Boolean sponsoredSource) {
		this.sponsoredSource = sponsoredSource;
	}

	public Boolean getQueryParamsMatch() {
		return queryParamsMatch;
	}

	public void setQueryParamsMatch(Boolean queryParamsMatch) {
		if (queryParamsMatch != null && !queryParamsMatch) {
			throw new IllegalArgumentException(
					"queryParamsMatch can only be True");
		}
		this.queryParamsMatch = queryParamsMatch;
	}

	public Boolean getQueryPersonMatch() {
		return queryPersonMatch;
	}

	public void setQueryPersonMatch(Boolean queryPersonMatch) {
		if (queryPersonMatch != null && !queryPersonMatch) {
			throw new IllegalArgumentException(
					"queryPersonMatch can only be True");
		}
		this.queryPersonMatch = queryPersonMatch;
	}
}
