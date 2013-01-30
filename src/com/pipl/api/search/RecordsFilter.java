package com.pipl.api.search;

import java.util.ArrayList;

import com.pipl.api.data.fields.Field;


public class RecordsFilter extends BaseFilter {

	private ArrayList<Class<? extends Field>> hasFields;

	/**
	 * @param domain
	 *            str, for example "linkedin.com", you may also use "linkedin"
	 *            but note that it'll match "linkedin.*" and "*.linkedin.*" (any
	 *            sub-domain and any TLD).
	 * @param category
	 *            str, any one of the categories defined in
	 *            piplapis.data.source.Source.categories.
	 * @param sponsoredSource
	 *            bool, True means you want just the records that come from a
	 *            sponsored source and False means you don't want these records.
	 * @param hasFields
	 *            A list of fields classes from piplapis.data.fields, records
	 *            must have content in all these fields. For example: [Name,
	 *            Phone] means you only want records that has at least one name
	 *            and at least one phone.
	 * @param queryParamsMatch
	 *            True is the only possible value and it means you want records
	 *            that match all the params you passed in the query.
	 * @param queryPersonMatch
	 *            True is the only possible value and it means you want records
	 *            that are the same person you queried by (only records with
	 *            queryPersonMatch == 1.0, see the documentation of
	 *            record.queryPersonMatch for more details).
	 */
	public RecordsFilter(String domain, String category,
			Boolean sponsoredSource,
			ArrayList<Class<? extends Field>> hasFields,
			Boolean queryParamsMatch, Boolean queryPersonMatch) {
		this.setDomain(domain);
		this.setCategory(category);
		this.setSponsoredSource(sponsoredSource);
		this.setHasFields(hasFields);
		this.setQueryParamsMatch(queryParamsMatch);
		this.setQueryPersonMatch(queryPersonMatch);
	}

	private RecordsFilter(Builder builder) {
		this(builder.domain, builder.category, builder.sponsoredSource,
				builder.hasFields, builder.queryParamsMatch,
				builder.queryPersonMatch);
	}

	public ArrayList<Class<? extends Field>> getHasFields() {
		return hasFields;
	}

	public void setHasFields(ArrayList<Class<? extends Field>> hasFields) {
		this.hasFields = hasFields;
	}

	/**
	 * @param domain
	 *            str, for example "linkedin.com", you may also use "linkedin"
	 *            but note that it'll match "linkedin.*" and "*.linkedin.*" (any
	 *            sub-domain and any TLD).
	 * @param category
	 *            str, any one of the categories defined in
	 *            piplapis.data.source.Source.categories.
	 * @param sponsoredSource
	 *            bool, True means you want just the records that come from a
	 *            sponsored source and False means you don't want these records.
	 * @param hasFields
	 *            A list of fields classes from piplapis.data.fields, records
	 *            must have content in all these fields. For example: [Name,
	 *            Phone] means you only want records that has at least one name
	 *            and at least one phone.
	 * @param queryParamsMatch
	 *            True is the only possible value and it means you want records
	 *            that match all the params you passed in the query.
	 * @param queryPersonMatch
	 *            True is the only possible value and it means you want records
	 *            that are the same person you queried by (only records with
	 *            queryPersonMatch == 1.0, see the documentation of
	 *            record.queryPersonMatch for more details).
	 */
	public static class Builder {
		private String domain;
		private String category;
		private Boolean sponsoredSource;
		private ArrayList<Class<? extends Field>> hasFields;
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
			if(this.hasFields==null){
				this.hasFields = new ArrayList<Class<? extends Field>>(); 
			}
			this.hasFields.add(hasField);
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

		public RecordsFilter build() {
			return new RecordsFilter(this);
		}
	}

}
