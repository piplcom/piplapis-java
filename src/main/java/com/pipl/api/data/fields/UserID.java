package com.pipl.api.data.fields;

import com.google.gson.annotations.Expose;

/**
 * An ID associated with a person.
 * <p/>
 * The ID is a string that's used by the site to uniquely identify a person,
 * it's guaranteed that in the site this string identifies exactly one person.
 */
public class UserID extends AbstractField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	public String content;
	
	public UserID() {
	}

	public UserID(String content) {
		setContent(content);
	}

	/**
	 * @return true if UserID is a valid UserID@service to search by.
	 */
	public boolean isSearchable() {
		if (content==null) {
			return false;
		}
		int indexOfAt = content.indexOf('@');
		return indexOfAt>0 && indexOfAt<content.length()-1; // Must have something before and after the @
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		if (content!=null) {
			return content;
		}
		return "";
	}
}