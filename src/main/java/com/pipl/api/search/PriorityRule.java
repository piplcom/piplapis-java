package com.pipl.api.search;

import com.pipl.api.data.fields.Field;

public class PriorityRule extends BaseFilter {

	private Class<? extends Field> hasField;

	/***
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
	 * @param hasField
	 *            A field class from piplapis.data.fields. For example:
	 *            hasField=Phone means you want to give a priority to records
	 *            that has at least one phone.
	 * @param queryParamsMatch
	 *            True is the only possible value and it means you want to give
	 *            a priority to records that match all the params you passed in
	 *            the query.
	 * @param queryPersonMatch
	 *            True is the only possible value and it means you want to give
	 *            a priority to records with higher queryPersonMatch (see the
	 *            documentation of record.queryPersonMatch for more details)
	 */
	public PriorityRule(String domain, String category,
			Boolean sponsoredSource, Class<? extends Field> hasField,
			Boolean queryParamsMatch, Boolean queryPersonMatch) {
		this.setDomain(domain);
		this.setCategory(category);
		this.setSponsoredSource(sponsoredSource);
		this.setHasField(hasField);
		this.setQueryParamsMatch(queryParamsMatch);
		this.setQueryPersonMatch(queryPersonMatch);
	}

	private PriorityRule(Builder builder) {
		this(builder.domain, builder.category, builder.sponsoredSource,
				builder.hasField, builder.queryParamsMatch,
				builder.queryPersonMatch);
	}

	public Class<? extends Field> getHasField() {
		return hasField;
	}

	public void setHasField(Class<? extends Field> hasField) {
		this.hasField = hasField;
	}

	/***
	 * A builder, to enable default Java values for all params that are not
	 * initiated explicitly
	 * 
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
	 * @param hasField
	 *            A field class from piplapis.data.fields. For example:
	 *            hasField=Phone means you want to give a priority to records
	 *            that has at least one phone.
	 * @param queryParamsMatch
	 *            True is the only possible value and it means you want to give
	 *            a priority to records that match all the params you passed in
	 *            the query.
	 * @param queryPersonMatch
	 *            True is the only possible value and it means you want to give
	 *            a priority to records with higher queryPersonMatch (see the
	 *            documentation of record.queryPersonMatch for more details)
	 */
	public static class Builder {
		private String domain;
		private String category;
		private Boolean sponsoredSource;
		private Class<? extends Field> hasField;
		private Boolean queryParamsMatch;
		private Boolean queryPersonMatch;

		public Builder domain(String domain) {
			this.domain = domain;
			return this;
		}

		public Builder category(String category) {
			this.category = category;
			return this;
		}

		public Builder sponsoredSource(Boolean sponsoredSource) {
			this.sponsoredSource = sponsoredSource;
			return this;
		}

		public Builder hasField(Class<? extends Field> hasField) {
			this.hasField = hasField;
			return this;
		}

		public Builder queryParamsMatch(Boolean queryParamsMatch) {
			this.queryParamsMatch = queryParamsMatch;
			return this;
		}

		public Builder queryPersonMatch(Boolean queryPersonMatch) {
			this.queryPersonMatch = queryPersonMatch;
			return this;
		}

		public PriorityRule build() {
			return new PriorityRule(this);
		}
	}

}
