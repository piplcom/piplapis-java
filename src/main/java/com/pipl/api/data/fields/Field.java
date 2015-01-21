package com.pipl.api.data.fields;

import java.util.Date;

public interface Field {

	/**
	 * method to check if a field is searchable
	 * 
	 * @return boolean
	 */
	public abstract boolean isSearchable();

	public abstract Date getValidSince();

	public abstract boolean isInferred();

}