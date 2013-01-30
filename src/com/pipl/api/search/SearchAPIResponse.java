package com.pipl.api.search;


import java.io.Serializable;
import java.util.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.containers.Person;
import com.pipl.api.data.containers.Record;


/**
 * A response from Pipl's Search API.
 * <p/>
 * A response comprises the two things returned as a result to your query:
 * <p/>
 * - A person (piplapis.data.containers.Person) that is the data object
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
 * - A list of records (piplapis.data.containers.Record) that fully/partially
 * match the person from your query, if the query was for "Eric Cartman from
 * Colorado US" the response might also contain records of "Eric Cartman
 * from US" (without Colorado), if you need to differentiate between records
 * with full match to the query and partial match or if you want to get a
 * score on how likely is that record to be related to the person you are
 * searching please refer to the record's attributes
 * record.query_params_match and record.query_person_match.
 * <p/>
 * The response also contains the query as it was interpreted by Pipl. This
 * part is useful for verification and debugging, if some query parameters
 * were invalid you can see in response.query that they were ignored, you can
 * also see how the name/address from your query were parsed in case you
 * passed raw_name/raw_address in the query.
 * <p/>
 * In some cases when the query isn't focused enough and can't be matched to
 * a specific person, such as "John Smith from US", the response also contains
 * a list of suggested searches. This is a list of Record objects, each of
 * these is an expansion of the original query, giving additional query
 * parameters so the you can zoom in on the right person.
 */

public class SearchAPIResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private Person query;
	@Expose
    private Person person;
	@Expose
    private ArrayList<Record> records;
	@Expose
    @SerializedName("suggested_searches")
    private ArrayList<Record> suggestedSearches;
	@Expose
    private ArrayList<String> warnings;

    /**
     * @param query              A Person object with the query as interpreted by Pipl.
     * @param person             A Person object with data about the person in the query.
     * @param records            A list of Record objects with full/partial match to the
     *                           query.
     * @param suggestedSearches A list of Record objects, each of these is an
     *                           expansion of the original query, giving additional
     *                           query parameters to zoom in on the right person.
     * @param warnings          A list of String objects. A warning is returned when the query
     *                           contains a non-critical error and the search can still run.
     */
    public SearchAPIResponse(Person query, Person person, ArrayList<Record> records,
                             ArrayList<Record> suggestedSearches, ArrayList<String> warnings) {
        this.query = query;
        this.person = person;
        if (records == null) {
            this.records = new ArrayList<Record>();
        } else {
            this.records = records;
        }
        if (suggestedSearches == null) {
            this.suggestedSearches = new ArrayList<Record>();
        } else {
            this.suggestedSearches = suggestedSearches;
        }
        if (warnings == null) {
            this.warnings = new ArrayList<String>();
        } else {
            this.warnings = warnings;
        }
    }
    
    /**
     * @return Records that match all the params in the query.
     */
    public ArrayList<Record> queryParamsMatchedRecords() {
        ArrayList<Record> matched = new ArrayList<Record>();
        for (Record r : this.records) {
            if (r.isQueryParamsMatch()) {
                matched.add(r);
            }
        }
        return matched;
    }

    /**
     * @return Records that match the person from the query.
     *         Note that the meaning of "match the person from the query" means "Pipl
     *         is convinced that these records hold data about the person you're
     *         looking for".
     *         Remember that when Pipl is convinced about which person you're looking
     *         for, the response also contains a Person object. This person is
     *         created by merging all the fields and sources of these records.
     */
    public ArrayList<Record> queryPersonMatchedRecords() {
        ArrayList<Record> matched = new ArrayList<Record>();
        for (Record r : this.records) {
            if (r.getQueryPersonMatch() == 1.) {
                matched.add(r);
            }
        }
        return matched;
    }
    
    /**
     * @return Return the records grouped by the domain they came from.
     *         <p/>
     *         The return value is a Map, a key in this Map is a domain
     *         and the value is a list of all the records with this domain.
     */
    public Map<String, List<Record>> groupRecordsByDomain() {
        Map<String, List<Record>> map = new HashMap<String, List<Record>>();
        for (Record record : records) {
            String key = record.getSource().getDomain();
            if (map.get(key) == null) {
                map.put(key, new ArrayList<Record>());
            }
            map.get(key).add(record);
        }
        return map;
    }

    /**
     * @return Return the records grouped by the category of their source.
     *         <p/>
     *         The return value is a Map, a key in this Map is a category
     *         and the value is a list of all the records with this category.
     */
    public Map<String, List<Record>> groupRecordsByCategory() {
        Map<String, List<Record>> map = new HashMap<String, List<Record>>();
        for (Record record : records) {
            String key = record.getSource().getCategory();
            if (map.get(key) == null) {
                map.put(key, new ArrayList<Record>());
            }
            map.get(key).add(record);
        }
        return map;
    }

    /**
     * @return Return the records grouped by their query_params_match attribute.
     *         <p/>
     *         The return value is a Map, a key in this Map is a queryParamsMatch
     *         Boolean (so the keys can be just True or False) and the value is a list
     *         of all the records with this queryParamsMatch value.
     */
    public Map<Boolean, List<Record>> groupRecordsByQueryParamsMatch() {
        Map<Boolean, List<Record>> map = new HashMap<Boolean, List<Record>>();
        for (Record record : records) {
            Boolean key = record.isQueryParamsMatch();
            if (map.get(key) == null) {
                map.put(key, new ArrayList<Record>());
            }
            map.get(key).add(record);
        }
        return map;
    }

    /**
     * @return Return the records grouped by their queryPersonMatch attribute.
     *         <p/>
     *         The return value is a Map, a key in this Map is a queryPersonMatch
     *         Float and the value is a list of all the records with this
     *         queryPersonMatch value.
     */
    public Map<Float, List<Record>> groupRecordsByQueryPersonMatch() {
        Map<Float, List<Record>> map = new HashMap<Float, List<Record>>();
        for (Record record : records) {
            Float key = record.getQueryPersonMatch();
            if (map.get(key) == null) {
                map.put(key, new ArrayList<Record>());
            }
            map.get(key).add(record);
        }
        return map;
    }

    public Person getQuery() {
        return query;
    }

    public Person getPerson() {
        return person;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }
    
    public ArrayList<Record> getSuggestedSearches() {
        return suggestedSearches;
    }

    public ArrayList<String> getWarnings() {
        return warnings;
    }

}
