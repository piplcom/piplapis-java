package com.pipl.api.search;

import com.pipl.api.APIError;
import com.pipl.api.data.Utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * An exception raised when the response from the search API contains an error.
 */

public class SearchAPIError extends APIError implements Serializable {
    private static final long serialVersionUID = 1L;
    public String json;
    public int qpsAllotted;
    public int qpsCurrent;
    public int qpsLiveAllotted;
    public int qpsLiveCurrent;
    public int qpsDemoAllotted;
    public int qpsDemoCurrent;
    public int quotaAllotted;
    public int quotaCurrent;
    public Date quotaReset;
    public int demoUsageAllotted;
    public int demoUsageCurrent;
    public Date demoUsageExpiry;

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
     * @return the number of queries with live feeds you are allowed to do per second.
     */
    public int getQpsLiveAllotted() {
        return qpsLiveAllotted;
    }

    /**
     * @return the number of queries with live feeds you have run this second.
     */
    public int getQpsLiveCurrent() {
        return qpsLiveCurrent;
    }

    /**
     * @return the number of demo queries you are allowed to do per second.
     */
    public int getQpsDemoAllotted() {
        return qpsDemoAllotted;
    }

    /**
     * @return the number of demo queries you have run this second.
     */
    public int getQpsDemoCurrent() {
        return qpsDemoCurrent;
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

    /**
     * @return your demo usage limit.
     */
    public int getDemoUsageAllotted() {
        return demoUsageAllotted;
    }

    /**
     * @return how much of your demo usage you already used.
     */
    public int getDemoUsageCurrent() {
        return demoUsageCurrent;
    }

    /**
     * @return The time (in UTC) that your demo usage will expire.
     */
    public Date getDemoUsageExpiry() {
        return demoUsageExpiry;
    }
}
