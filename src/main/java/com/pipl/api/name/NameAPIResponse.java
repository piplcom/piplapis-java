package com.pipl.api.name;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.fields.Name;

/**
 * A response from Pipl's search API.
 * <p/>
 * A response contains the name from the query (parsed), and when available the
 * gender, nicknames, fullNames, spelling options, translations, common
 * locations and common ages for the name. It also contains an estimated number
 * of people in the world with this name.
 */
public class NameAPIResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private Name name;
	@Expose
	@SerializedName("gender")
	private ArrayList<Object> genderList;
	@Expose
	@SerializedName("full_names")
	private AltNames fullNames;
	@Expose
	private AltNames nicknames;
	@Expose
	private AltNames spellings;
	@Expose
	private HashMap<String, AltNames> translations = new HashMap<String, AltNames>();
	@Expose
	@SerializedName("top_locations")
	private ArrayList<LocationStats> topLocations = new ArrayList<LocationStats>();
	@Expose
	@SerializedName("top_ages")
	private ArrayList<AgeStats> topAges = new ArrayList<AgeStats>();
	@Expose
	@SerializedName("estimated_world_persons_count")
	private Integer estimatedWorldPersonsCount;
	@Expose
	private ArrayList<String> warnings = new ArrayList<String>();

	/**
	 * @param name
	 *            A piplapis.data.fields.Name object - the name from the query.
	 * @param gender
	 *            string, "male" or "female".
	 * @param genderConfidence
	 *            float between 0.0 and 1.0, represents how confidence Pipl is
	 *            that `gender` is the correct one. (Unisex names will get low
	 *            confidence score).
	 * @param fullNames
	 *            An AltNames object.
	 * @param nicknames
	 *            An AltNames object.
	 * @param spellings
	 *            An AltNames object.
	 * @param translations
	 *            A Map of language_code -> AltNames object for this language.
	 * @param topLocations
	 *            A list of LocationStats objects.
	 * @param topAges
	 *            A list of AgeStats objects.
	 * @param estimatedWorldPersonsCount
	 *            int, estimated number of people in the world with the name
	 *            from the query.
	 * @param warnings
	 *            A list of strings. A warning is returned when the query
	 *            contains a non-critical error.
	 */
	public NameAPIResponse(Name name, ArrayList<Object> gender,
			AltNames fullNames, AltNames nicknames, AltNames spellings,
			HashMap<String, AltNames> translations,
			ArrayList<LocationStats> topLocations, ArrayList<AgeStats> topAges,
			Integer estimatedWorldPersonsCount, ArrayList<String> warnings) {
		super();
		this.name = name;
		this.genderList = gender;
		this.fullNames = fullNames;
		this.nicknames = nicknames;
		this.spellings = spellings;
		this.translations = translations;
		this.topLocations = topLocations;
		this.topAges = topAges;
		this.estimatedWorldPersonsCount = estimatedWorldPersonsCount;
		this.warnings = warnings;
	}

	public Name getName() {
		return name;
	}

	public String getGender() {
		return (String)genderList.get(0);
	}

	public Double getGenderConfidence() {
		return (Double)genderList.get(1);
	}

	public AltNames getFullNames() {
		return fullNames;
	}

	public AltNames getNicknames() {
		return nicknames;
	}
	
	public AltNames getSpellings() {
		return spellings;
	}

	public HashMap<String, AltNames> getTranslations() {
		return translations;
	}
	
	public ArrayList<LocationStats> getTopLocations() {
		return topLocations;
	}

	public ArrayList<AgeStats> getTopAges() {
		return topAges;
	}

	public Integer getEstimatedWorldPersonsCount() {
		return estimatedWorldPersonsCount;
	}

	public ArrayList<String> getWarnings() {
		return warnings;
	}
}
