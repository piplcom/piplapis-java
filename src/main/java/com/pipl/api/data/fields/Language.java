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

	@Override
	public String toString() {
		if (display!=null)
			return display;
		if (language!=null && region!=null)
			return language + '_' + region;
		if (language!=null)
			return language;
		return "";
	}
}
