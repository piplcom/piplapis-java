package com.pipl.api.data.fields;

import com.google.gson.annotations.Expose;
import com.pipl.api.data.Utils;

/**
 * A username/screen-name associated with the person.
 * <p/>
 * Note that even though in many sites the username uniquely identifies one
 * person it's not guaranteed, some sites allow different people to use the same
 * username.
 */
public class Username extends AbstractField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	public String content;

	public Username() {
	}
	
	/**
	 * @param content
	 *            `content` is the username itself.
	 */
	public Username(String content) {
		setContent(content);
	}

	/**
	 * @return whether the username is a valid username to search by.
	 */
	public boolean isSearchable() {
		return content != null && Utils.alnumChars(content).length() >= 4;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}