package com.pipl.api.data.fields;

import java.util.Date;

public interface Field {

	/**
	 * method to check if a field is searchable
	 * 
	 * @return boolean
	 */
	public abstract boolean isSearchable();

	/**
	 * @return the date in which this field first appeared to Pipl's crawlers.
	 */
	public abstract Date getValidSince();

	/**
	 * @return the date in which this field was last seen by Pipl's crawlers.
	 */
	public abstract Date getLastSeen();

	/**
	 * @return is this field is inferred.
	 */
	public abstract boolean isInferred();

}