package com.pipl.api.data.containers;


import java.util.ArrayList;
import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Source;
import com.pipl.api.data.fields.Field;

/**
 * A record is all the data available in a specific source.
 * <p/>
 * Every record object is based on a source which is basically the URL of the
 * page where the data is available, and the data itself that comes as field
 * objects (Name, Address, Email etc. see piplapis.data.fields).
 * <p/>
 * Each type of field has its own container (note that Record is a subclass of
 * FieldsContainer). For example:
 * <p/>
 * Records come as results for a query and therefore they have attributes that
 * indicate if and how much they match the query. They also have a validity
 * timestamp available as an attribute.
 */

public class Record extends FieldsContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private Source source;
	@SerializedName("@query_params_match")
	private Boolean queryParamsMatch;
	@SerializedName("@query_person_match")
	private Float queryPersonMatch;
	@SerializedName("@valid_since")
	private Date validSince;

	/**
	 * @param fields
	 *            An ArrayList of <code>Field</code> objects
	 * @param source
	 *            A Source object
	 * @param queryParamsMatch
	 *            A bool value that indicates whether the record contains all
	 *            the params from the query or not.
	 * @param queryPersonMatch
	 *            A float between 0.0 and 1.0 that indicates how likely it is
	 *            that this record holds data about the person from the query.
	 *            Higher value means higher likelihood, value of 1.0 means
	 *            "this is definitely him". This value is based on Pipl's
	 *            statistical algorithm that takes into account many parameters
	 *            like the popularity of the name/address (if there was a
	 *            name/address in the query) etc.
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 */
	public Record(ArrayList<Field> fields, Source source,
			Boolean queryParamsMatch, Float queryPersonMatch, Date validSince) {
		super(fields);
		setSource(source);
		setQueryParamsMatch(queryParamsMatch);
		setQueryPersonMatch(queryPersonMatch);
		setValidSince(validSince);
	}

	private Record(Builder builder) {
		this(builder.fields, builder.source, builder.queryParamsMatch,
				builder.queryPersonMatch, builder.validSince);
	}

	public static class Builder {
		private ArrayList<Field> fields;
		private Source source;
		private Boolean queryParamsMatch;
		private Float queryPersonMatch;
		private Date validSince;

		public Record build() {
			return new Record(this);
		}

		public Builder fields(ArrayList<Field> fields) {
			this.fields = fields;
			return this;
		}

		public Builder source(Source source) {
			this.source = source;
			return this;
		}

		public Builder queryParamsMatch(Boolean queryParamsMatch) {
			this.queryParamsMatch = queryParamsMatch;
			return this;
		}

		public Builder queryPersonMatch(Float queryPersonMatch) {
			this.queryPersonMatch = queryPersonMatch;
			return this;
		}

		public Builder validSince(Date validSince) {
			this.validSince = validSince;
			return this;
		}
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		if (source == null) {
			this.source = new Source.Builder().build();
		} else {
			this.source = source;
		}
	}

	public void setQueryParamsMatch(Boolean queryParamsMatch) {
		this.queryParamsMatch = queryParamsMatch;
	}

	public Date getValidSince() {
		return validSince;
	}

	public void setValidSince(Date valid_since) {
		this.validSince = valid_since;
	}

	public Boolean isQueryParamsMatch() {
		return queryParamsMatch;
	}

	public Float getQueryPersonMatch() {
		return queryPersonMatch;
	}

	public void setQueryPersonMatch(Float queryPersonMatch) {
		this.queryPersonMatch = queryPersonMatch;
	}

}
