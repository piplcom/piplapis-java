package com.pipl.api.data.containers;


import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Source;
import com.pipl.api.data.fields.Email;
import com.pipl.api.data.fields.Field;
import com.pipl.api.data.fields.Name;
import com.pipl.api.data.fields.Phone;
import com.pipl.api.data.fields.Username;

/**
 * A Person object is all the data available on an individual.
 * <p/>
 * The Person object is essentially very similar in its structure to the Record
 * object, the main difference is that data about an individual can come from
 * multiple sources while a record is data from one source.
 * <p/>
 * The person's data comes as field objects (Name, Address, Email etc. see
 * piplapis.data.fields). Each type of field has its on container (note that
 * Person is a subclass of FieldsContainer). For example:
 * <p/>
 * <p>
 * <blockquote>
 * 
 * <pre>
 * name = new Name(&quot;ccc&quot;, &quot;ddd&quot;);
 * email = new Email(&quot;t@v.com&quot;);
 * phone = new Phone(Long.parseLong(&quot;888888888&quot;));
 * username = new Username(&quot;dddd&quot;);
 * address = new Address(&quot;fr&quot;, null, null);
 * dob = DOB.fromAgeRange(10, 55);
 * relationship = new Relationship(new Name(&quot;kkk&quot;, &quot;ggg&quot;));
 * fields = new ArrayList&lt;Field&gt;();
 * fields.add(name);
 * fields.add(email);
 * fields.add(phone);
 * fields.add(username);
 * fields.add(address);
 * fields.add(dob);
 * fields.add(relationship);
 * person = new Person(fields);
 * </pre>
 * 
 * </blockquote>
 * <p/>
 * <p/>
 * Note that a person object is used in the Search API in two ways: - It might
 * come back as a result for a query (see SearchResponse). - It's possible to
 * build a person object with all the information you already have about the
 * person you're looking for and send this object as the query (see
 * SearchRequest).
 */
public class Person extends FieldsContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private ArrayList<Source> sources;
	@SerializedName("@query_params_match")
	private Boolean queryParamsMatch;

	/**
	 * @param fields
	 *            An ArrayList of <code>Field</code> objects
	 * @param sources
	 *            A list of Source objects
	 * @param queryParamsMatch
	 *            A bool value that indicates whether the record contains all
	 *            the params from the query or not.
	 */
	public Person(ArrayList<Field> fields, ArrayList<Source> sources,
			Boolean queryParamsMatch) {
		super(fields);
		setSources(sources);
		this.queryParamsMatch = queryParamsMatch;
	}

	private Person(Builder builder) {
		this(builder.fields, builder.sources, builder.queryParamsMatch);
	}

	public static class Builder {
		private ArrayList<Field> fields;
		private ArrayList<Source> sources;
		private Boolean queryParamsMatch;

		public Person build() {
			return new Person(this);
		}

		public Builder fields(ArrayList<Field> fields) {
			this.fields = fields;
			return this;
		}

		public Builder sources(ArrayList<Source> sources) {
			this.sources = sources;
			return this;
		}

		public Builder queryParamsMatch(Boolean queryParamsMatch) {
			this.queryParamsMatch = queryParamsMatch;
			return this;
		}
	}

	/**
	 * @return A bool value that indicates whether the person has enough data
	 *         and can be sent as a query to the API.
	 */
	public boolean isSearchable() {

		for (Name name : getNames()) {
			if (name.isSearchable()) {
				return true;
			}
		}
		for (Email email : getEmails()) {
			if (email.isSearchable()) {
				return true;
			}
		}
		for (Phone phone : getPhones()) {
			if (phone.isSearchable()) {
				return true;
			}
		}
		for (Username username : getUsernames()) {
			if (username.isSearchable()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return A list of all the fields that can't be searched by. For example:
	 *         names/usernames that are too short, emails that are invalid etc.
	 */
	public ArrayList<Field> unsearchableFields() {
		ArrayList<Field> unSearchableList = new ArrayList<Field>();
		for (Field field : allFields()) {
			if (!field.isSearchable()) {
				unSearchableList.add(field);
			}
		}
		return unSearchableList;

	}

	public ArrayList<Source> getSources() {
		return sources;
	}

	public void setSources(ArrayList<Source> sources) {
		if (null == sources) {
			this.sources = new ArrayList<Source>();
		} else {
			this.sources = sources;
		}
	}

	public Boolean isQueryParamsMatch() {
		return queryParamsMatch;
	}

	public void setQueryParamsMatch(Boolean queryParamsMatch) {
		this.queryParamsMatch = queryParamsMatch;
	}
}
