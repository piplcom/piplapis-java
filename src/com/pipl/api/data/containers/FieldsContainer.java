package com.pipl.api.data.containers;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.fields.Address;
import com.pipl.api.data.fields.DOB;
import com.pipl.api.data.fields.Education;
import com.pipl.api.data.fields.Email;
import com.pipl.api.data.fields.Field;
import com.pipl.api.data.fields.Image;
import com.pipl.api.data.fields.Job;
import com.pipl.api.data.fields.Name;
import com.pipl.api.data.fields.Phone;
import com.pipl.api.data.fields.RelatedURL;
import com.pipl.api.data.fields.Relationship;
import com.pipl.api.data.fields.Tag;
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
	private ArrayList<Name> names;
	@Expose
	private ArrayList<Address> addresses;
	@Expose
	private ArrayList<Phone> phones;
	@Expose
	private ArrayList<Email> emails;
	@Expose
	private ArrayList<Job> jobs;
	@Expose
	private ArrayList<Education> educations;
	@Expose
	private ArrayList<Image> images;
	@Expose
	private ArrayList<Username> usernames;
	@Expose
	@SerializedName("user_ids")
	private ArrayList<UserID> userIds;
	@Expose
	private ArrayList<DOB> dobs;
	@Expose
	@SerializedName("related_urls")
	private ArrayList<RelatedURL> relatedUrls;
	@Expose
	private ArrayList<Relationship> relationships;
	@Expose
	private ArrayList<Tag> tags;

	public FieldsContainer(ArrayList<Field> fields) {
		names = new ArrayList<Name>();
		addresses = new ArrayList<Address>();
		phones = new ArrayList<Phone>();
		emails = new ArrayList<Email>();
		jobs = new ArrayList<Job>();
		educations = new ArrayList<Education>();
		images = new ArrayList<Image>();
		usernames = new ArrayList<Username>();
		userIds = new ArrayList<UserID>();
		dobs = new ArrayList<DOB>();
		relatedUrls = new ArrayList<RelatedURL>();
		relationships = new ArrayList<Relationship>();
		tags = new ArrayList<Tag>();
		if (fields == null) {
			addFields(new ArrayList<Field>());
		} else {
			addFields(fields);
		}

	}

	public FieldsContainer() {
		this(null);
	}

	public void addFields(ArrayList<Field> fields) {
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
			} else if (field instanceof DOB) {
				dobs.add((DOB) field);
			} else if (field instanceof RelatedURL) {
				relatedUrls.add((RelatedURL) field);
			} else if (field instanceof Relationship) {
				relationships.add((Relationship) field);
			} else if (field instanceof Tag) {
				tags.add((Tag) field);
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
		fields.addAll(dobs);
		fields.addAll(relatedUrls);
		fields.addAll(relationships);
		fields.addAll(tags);
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

	public ArrayList<DOB> getDobs() {
		return dobs;
	}

	public ArrayList<RelatedURL> getRelatedUrls() {
		return relatedUrls;
	}

	public ArrayList<Relationship> getRelationships() {
		return relationships;
	}

	public ArrayList<Tag> getTags() {
		return tags;
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

	public void setDobs(ArrayList<DOB> dobs) {
		this.dobs = dobs;
	}

	public void setRelatedUrls(ArrayList<RelatedURL> relatedUrls) {
		this.relatedUrls = relatedUrls;
	}

	public void setRelationships(ArrayList<Relationship> relationships) {
		this.relationships = relationships;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

}
