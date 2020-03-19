package com.pipl.api.data.containers;


import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.fields.Field;
import com.pipl.api.search.SearchAPIRequest;

/**
 * A Person object is all the data available on an individual.
 *
 * The Person object is essentially very similar in its structure to the Source
 * object, the main difference is that data about an individual can come from
 * multiple sources while a record is data from one source.
 *
 * The person's data comes as field objects (Name, Address, Email etc).
 * Each type of field has its on container (note that
 * Person is a subclass of FieldsContainer). For example:
 *
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
 *
 *
 * Note that a person object is used in the Search API in two ways: - It might
 * come back as a result for a query (see SearchAPIResponse). - It's possible to
 * build a person object with all the information you already have about the
 * person you're looking for and send this object as the query (see
 * SearchAPIRequest).
 */
public class Person extends FieldsContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	@SerializedName("@id")
	public String id;
	@Expose
	@SerializedName("@match")
	public Float match;
	@Expose
	@SerializedName("@search_pointer")
	public String searchPointer;
	@Expose
	@SerializedName("@inferred")
	public Boolean inferred;

	public Person() {
	}
	
	/**
	 * @param fields
	 *            An Collection of <code>Field</code> objects
	 */
	public Person(Collection<Field> fields) {
		super(fields);
	}
	
	/**
	 * @param fields
	 *            One or more <code>Field</code> objects
	 */
	public Person(Field... fields) {
		super(fields);
	}
	
	@Override
	public boolean isSearchable() {
		if (searchPointer!=null) {
			return true;
		}
		return super.isSearchable();
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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the match
	 */
	public float getMatch() {
		return match;
	}

	/**
	 * @return the searchPointer
	 */
	public String getSearchPointer() {
		return searchPointer;
	}

	/**
	 * @param searchPointer the searchPointer to set
	 */
	public void setSearchPointer(String searchPointer) {
		this.searchPointer = searchPointer;
	}
	
	public boolean isInferred() {
		if (inferred==null)
			return false;
		return inferred;
	}

	/**
	 * A shortcut method to generate a search request
	 * from a possible person that contained a search pointer.
	 * sending this request will result in a more detailed
	 * response for this specific person.
	 * @return a SearchAPIRequest ready to be sent.
	 */
	public SearchAPIRequest fromSearchPointer() {
		if (searchPointer==null)
			return null;
		return new SearchAPIRequest(searchPointer);
	}

}
