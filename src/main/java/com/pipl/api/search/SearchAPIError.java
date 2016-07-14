package com.pipl.api.search;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import com.pipl.api.APIError;
import com.pipl.api.data.Utils;

/**
 * An exception raised when the response from the search API contains an error.
 */

public class SearchAPIError extends APIError implements Serializable {
	private static final long serialVersionUID = 1L;
	public String json;
	public int qpsAllotted;
	public int qpsCurrent;
	public int quotaAllotted;
	public int quotaCurrent;
	public Date quotaReset;

	public SearchAPIError(String message, Integer httpStatusCode) {
		super(message, httpStatusCode);
	}

	public SearchAPIError(String message, Integer httpStatusCode, Throwable cause) {
		super(message, httpStatusCode, cause);
	}

	public static SearchAPIError fromJson(String json) throws IOException {
		SearchAPIError res = (SearchAPIError) Utils.fromJson(json, SearchAPIError.class);
		res.json = json;
		return res;
	}
	
	/**
	 * @return the number of queries you are allowed to do per second.
	 */
	public int getQpsAllotted() {
		return qpsAllotted;
	}

	/**
	 * @return the number of queries you have run this second.
	 */
	public int getQpsCurrent() {
		return qpsCurrent;
	}

	/**
	 * @return your quota limit.
	 */
	public int getQuotaAllotted() {
		return quotaAllotted;
	}

	/**
	 * @return how much of your quota you already used.
	 */
	public int getQuotaCurrent() {
		return quotaCurrent;
	}

	/**
	 * @return The time (in UTC) that your quota will be reset.
	 */
	public Date getQuotaReset() {
		return quotaReset;
	}
}
