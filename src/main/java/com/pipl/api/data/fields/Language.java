/**
 * 
 */
package com.pipl.api.data.fields;

import com.google.gson.annotations.Expose;


/**
 * A language the person is familiar with.
 *
 */
public class Language extends AbstractField {
	private static final long serialVersionUID = 3795355670770916571L;
	@Expose
	public String language;
	@Expose
	public String region;
	@Expose
	public String display;

	public Language() {
	}

	public String getLanguage() {
		return language;
	}

	public String getRegion() {
		return region;
	}
	
	public String getDisplay() {
		return display;
	}
}
