package com.pipl.api.data.containers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.fields.Address;
import com.pipl.api.data.fields.DOB;
import com.pipl.api.data.fields.Education;
import com.pipl.api.data.fields.Email;
import com.pipl.api.data.fields.Ethnicity;
import com.pipl.api.data.fields.Field;
import com.pipl.api.data.fields.Gender;
import com.pipl.api.data.fields.Image;
import com.pipl.api.data.fields.Job;
import com.pipl.api.data.fields.Language;
import com.pipl.api.data.fields.Name;
import com.pipl.api.data.fields.OriginCountry;
import com.pipl.api.data.fields.Phone;
import com.pipl.api.data.fields.Url;
import com.pipl.api.data.fields.UserID;
import com.pipl.api.data.fields.Username;

/**
 * The base class of Record and Person, made only for inheritance.
 */
public class FieldsContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	public ArrayList<Name> names;
	@Expose
	public ArrayList<Address> addresses;
	@Expose
	public ArrayList<Phone> phones;
	@Expose
	public ArrayList<Email> emails;
	@Expose
	public ArrayList<Job> jobs;
	@Expose
	public ArrayList<Education> educations;
	@Expose
	public ArrayList<Image> images;
	@Expose
	public ArrayList<Username> usernames;
	@Expose
	@SerializedName("user_ids")
	public ArrayList<UserID> userIds;
	@Expose
	public ArrayList<Language> languages;
	@Expose
	public ArrayList<Ethnicity> ethnicities;
	@Expose
	@SerializedName("origin_countries")
	public ArrayList<OriginCountry> originCountries;
	@Expose
	public ArrayList<Url> urls;
	@Expose
	public ArrayList<Relationship> relationships;
	@Expose
	public DOB dob;
	@Expose
	public Gender gender;
	
	public FieldsContainer() {
		names = new ArrayList<Name>();
		addresses = new ArrayList<Address>();
		phones = new ArrayList<Phone>();
		emails = new ArrayList<Email>();
		jobs = new ArrayList<Job>();
		educations = new ArrayList<Education>();
		images = new ArrayList<Image>();
		usernames = new ArrayList<Username>();
		userIds = new ArrayList<UserID>();
		languages = new ArrayList<Language>();
		ethnicities = new ArrayList<Ethnicity>();
		originCountries = new ArrayList<OriginCountry>();
		urls = new ArrayList<Url>();
		relationships = new ArrayList<Relationship>();
	}

	public FieldsContainer(Collection<Field> fields) {
		this();
		addFields(fields);
	}

	public FieldsContainer(Field... fields) {
		this(Arrays.asList(fields));
	}

	public void addFields(Collection<Field> fields) {
		// Add the fields to their corresponding container.
		for (Field field : fields) {
			if (field instanceof Name) {
				names.add((Name) field);
			} else if (field instanceof Address) {
				addresses.add((Address) field);
			} else if (field instanceof Phone) {
				phones.add((Phone) field);
			} else if (field instanceof Email) {
				emails.add((Email) field);
			} else if (field instanceof Job) {
				jobs.add((Job) field);
			} else if (field instanceof Education) {
				educations.add((Education) field);
			} else if (field instanceof Image) {
				images.add((Image) field);
			} else if (field instanceof Username) {
				usernames.add((Username) field);
			} else if (field instanceof UserID) {
				userIds.add((UserID) field);
			} else if (field instanceof Language) {
				languages.add((Language) field);
			} else if (field instanceof Ethnicity) {
				ethnicities.add((Ethnicity) field);
			} else if (field instanceof OriginCountry) {
				originCountries.add((OriginCountry) field);
			} else if (field instanceof DOB) {
				dob = (DOB) field;
			} else if (field instanceof Gender) {
				gender = (Gender) field;
			} else if (field instanceof Url) {
				urls.add((Url) field);
			} else if (field instanceof Relationship) {
				relationships.add((Relationship) field);
			}
		}
	}

	/**
	 * @return A list with all the fields contained in this object.
	 */
	public ArrayList<Field> allFields() {
		ArrayList<Field> fields = new ArrayList<Field>();
		fields.addAll(names);
		fields.addAll(addresses);
		fields.addAll(phones);
		fields.addAll(emails);
		fields.addAll(jobs);
		fields.addAll(educations);
		fields.addAll(images);
		fields.addAll(usernames);
		fields.addAll(userIds);
		fields.addAll(languages);
		fields.addAll(ethnicities);
		fields.addAll(originCountries);
		if (dob!=null)
			fields.add(dob);
		if (gender!=null)
			fields.add(gender);
		fields.addAll(urls);
		fields.addAll(relationships);
		return fields;
	}

	public ArrayList<Name> getNames() {
		return names;
	}

	public ArrayList<Address> getAddresses() {
		return addresses;
	}

	public ArrayList<Phone> getPhones() {
		return phones;
	}

	public ArrayList<Email> getEmails() {
		return emails;
	}

	public ArrayList<Job> getJobs() {
		return jobs;
	}

	public ArrayList<Education> getEducations() {
		return educations;
	}

	public ArrayList<Image> getImages() {
		return images;
	}

	public ArrayList<Username> getUsernames() {
		return usernames;
	}

	public ArrayList<UserID> getUserIds() {
		return userIds;
	}
	
	public ArrayList<Language> getLanguages() {
		return languages;
	}
	
	public ArrayList<Ethnicity> getEthnicities() {
		return ethnicities;
	}
	
	public ArrayList<OriginCountry> getOriginCountries() {
		return originCountries;
	}

	public DOB getDob() {
		return dob;
	}
	
	public Gender getGender() {
		return gender;
	}

	public ArrayList<Url> getUrls() {
		return urls;
	}

	public ArrayList<Relationship> getRelationships() {
		return relationships;
	}

	public void setNames(ArrayList<Name> names) {
		this.names = names;
	}

	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}

	public void setPhones(ArrayList<Phone> phones) {
		this.phones = phones;
	}

	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}

	public void setJobs(ArrayList<Job> jobs) {
		this.jobs = jobs;
	}

	public void setEducations(ArrayList<Education> educations) {
		this.educations = educations;
	}

	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}

	public void setUsernames(ArrayList<Username> usernames) {
		this.usernames = usernames;
	}

	public void setUserIds(ArrayList<UserID> userIds) {
		this.userIds = userIds;
	}
	
	public void setLanguages(ArrayList<Language> languages) {
		this.languages = languages;
	}
	
	public void setEthnicities(ArrayList<Ethnicity> ethnicities) {
		this.ethnicities = ethnicities;
	}
	
	public void setOriginCountries(ArrayList<OriginCountry> originCountries) {
		this.originCountries = originCountries;
	}

	public void setDob(DOB dob) {
		this.dob = dob;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setUrls(ArrayList<Url> urls) {
		this.urls = urls;
	}

	public void setRelationships(ArrayList<Relationship> relationships) {
		this.relationships = relationships;
	}

	/**
	 * @return whether the container has enough data
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


}
