package com.pipl.api.search;


import java.io.Serializable;
import java.util.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.containers.Person;
import com.pipl.api.data.containers.Source;
import com.pipl.api.data.fields.Address;
import com.pipl.api.data.fields.DOB;
import com.pipl.api.data.fields.Education;
import com.pipl.api.data.fields.Email;
import com.pipl.api.data.fields.Ethnicity;
import com.pipl.api.data.fields.Gender;
import com.pipl.api.data.fields.Job;
import com.pipl.api.data.fields.Language;
import com.pipl.api.data.fields.OriginCountry;
import com.pipl.api.data.fields.Phone;


/**
 * A response from Pipl's Search API.
 * <p/>
 * A response comprises the three things returned as a result to your query:
 * <p/>
 * - A person that is the data object.
 * representing all the information available for the person you were
 * looking for.
 * This object will only be returned when our identity-resolution engine is
 * convinced that the information is of the person represented by your query.
 * Obviously, if the query was for "John Smith" there's no way for our
 * identity-resolution engine to know which of the hundreds of thousands of
 * people named John Smith you were referring to, therefore you can expect
 * that the response will not contain a person object.
 * On the other hand, if you search by a unique identifier such as email or
 * a combination of identifiers that only lead to one person, such as
 * "Eric Cartman, Age 22, From South Park, CO, US", you can expect to get
 * a response containing a single person object.
 * <p/>
 * - A list of possible persons.
 * If our identity-resolution engine did not find a definite match, you can 
 * use this list to further drill down using the persons' search_pointer field.
 * <p/>
 * - A list of sources.
 * Sources that fully/partially match the person from your query, if the query 
 * was for "Eric Cartman from colorado US" the response might also contain 
 * sources of "Eric Cartman from US" (without Colorado), if you need to 
 * differentiate between sources with full match to the query and partial match
 * or if you want to get a score on how likely is that source to be related to 
 * the person you are searching please refer to the source's "match" field.
 * <p/>
 * The response also contains the query as it was interpreted by Pipl. This
 * part is useful for verification and debugging, if some query parameters
 * were invalid you can see in response.query that they were ignored, you can
 * also see how the name/address from your query were parsed in case you
 * passed raw_name/raw_address in the query.
 * <p/>
 */

public class SearchAPIResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	public Person query;
	@Expose
	public Person person;
	@Expose
    @SerializedName("possible_persons")
	public ArrayList<Person> possiblePersons;
	@Expose
	public ArrayList<Source> sources;
	@Expose
	public ArrayList<String> warnings;

    /**
     * @return Sources that match the person from the query.
     *         Note that the meaning of "match the person from the query" means "Pipl
     *         is convinced that these records hold data about the person you're
     *         looking for".
     *         Essentially, these are the sources that make up the Person object.
     */
    public List<Source> matchingSources() {
        ArrayList<Source> matching = new ArrayList<Source>();
        for (Source s : this.sources) {
            if (s.match==1.0) {
                matching.add(s);
            }
        }
        return matching;
    }

    /**
     * @return the sources grouped by the domain they came from.
     *         <p/>
     *         The return value is a Map, a key in this Map is a domain
     *         and the value is a list of all the sources with this domain.
     */
    public Map<String, List<Source>> groupSourcesByDomain() {
        Map<String, List<Source>> map = new HashMap<String, List<Source>>();
        if (sources!=null) {
	        for (Source source : sources) {
	            String key = source.getDomain();
	            List<Source> group = map.get(key);
	            if (group == null) {
	            	group = new ArrayList<Source>();
	            	map.put(key, group);
	            }
	            group.add(source);
	        }
        }
        return map;
    }

    /**
     * @return the sources grouped by their category.
     *         <p/>
     *         The return value is a Map, a key in this Map is a category
     *         and the value is a list of all the sources with this category.
     */
    public Map<String, List<Source>> groupSourcesByCategory() {
        Map<String, List<Source>> map = new HashMap<String, List<Source>>();
        if (sources!=null) {
	        for (Source source : sources) {
	            String key = source.getCategory();
	            List<Source> group = map.get(key);
	            if (group == null) {
	            	group = new ArrayList<Source>();
	            	map.put(key, group);
	            }
	            group.add(source);
	        }
        }
        return map;
    }

    /**
     * @return the records grouped by their match attribute.
     *         <p/>
     *         The return value is a Map, a key in this Map is a match
     *         Float and the value is a list of all the records with this
     *         match value.
     */
    public Map<Float, List<Source>> groupSourcesByMatch() {
        Map<Float, List<Source>> map = new HashMap<Float, List<Source>>();
        if (sources!=null) {
	        for (Source source : sources) {
	            Float key = source.getMatch();
	            List<Source> group = map.get(key);
	            if (group == null) {
	            	group = new ArrayList<Source>();
	            	map.put(key, group);
	            }
	            group.add(source);
	        }
        }
        return map;
    }

	/**
	 * @return A Person object with the query as interpreted by Pipl.
	 */
	public Person getQuery() {
		return query;
	}

	/**
	 * @return A Person object with data about the person in the query.
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @return A list of Person objects, each of these is an expansion of
	 *         the original query, giving additional query parameters to 
	 *         zoom in on the right person.
	 */
	public List<Person> getPossiblePersons() {
		return possiblePersons;
	}

	/**
	 * @return A list of Source objects with full/partial match to the query
	 */
	public List<Source> getSources() {
		return sources;
	}

	/**
	 * @return A list of warnings. A warning is returned when the query
	 *         contains a non-critical error and the search can still run.
	 */
	public List<String> getWarnings() {
		return warnings;
	}

	/**
	 * A shortcut method to get the person's gender from the result.
	 * @return the person's gender.
	 */
	public Gender gender() {
		if (person!=null)
			return person.gender;
		return null;
	}
    
	/**
	 * A shortcut method to get the person's age from the result.
	 * @return the person's age.
	 */
	public DOB dob() {
		if (person!=null)
			return person.dob;
		return null;
	}
    
	/**
	 * A shortcut method to get the person's job from the result.
	 * @return the person's job.
	 */
	public Job job() {
		if (person!=null && !person.jobs.isEmpty())
			return person.jobs.get(0);
		return null;
	}
    
	/**
	 * A shortcut method to get the person's address from the result.
	 * @return the person's address.
	 */
	public Address address() {
		if (person!=null && !person.addresses.isEmpty())
			return person.addresses.get(0);
		return null;
	}
    
	/**
	 * A shortcut method to get the person's education from the result.
	 * @return the person's education.
	 */
	public Education education() {
		if (person!=null && !person.educations.isEmpty())
			return person.educations.get(0);
		return null;
	}
    
	/**
	 * A shortcut method to get the person's language from the result.
	 * @return the person's language.
	 */
	public Language language() {
		if (person!=null && !person.languages.isEmpty())
			return person.languages.get(0);
		return null;
	}
    
	/**
	 * A shortcut method to get the person's ethnicity from the result.
	 * @return the person's ethnicity.
	 */
	public Ethnicity ethnicity() {
		if (person!=null && !person.ethnicities.isEmpty())
			return person.ethnicities.get(0);
		return null;
	}
    
	/**
	 * A shortcut method to get the person's origin country from the result.
	 * @return the person's origin country.
	 */
	public OriginCountry originCountry() {
		if (person!=null && !person.originCountries.isEmpty())
			return person.originCountries.get(0);
		return null;
	}
    
	/**
	 * A shortcut method to get the person's phone from the result.
	 * @return the person's phone.
	 */
	public Phone phone() {
		if (person!=null && !person.phones.isEmpty())
			return person.phones.get(0);
		return null;
	}
    
	/**
	 * A shortcut method to get the person's email from the result.
	 * @return the person's email.
	 */
	public Email email() {
		if (person!=null && !person.emails.isEmpty())
			return person.emails.get(0);
		return null;
	}
    
}
