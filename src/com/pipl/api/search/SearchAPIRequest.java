package com.pipl.api.search;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonSyntaxException;
import com.pipl.api.data.Source;
import com.pipl.api.data.Utils;
import com.pipl.api.data.containers.Person;
import com.pipl.api.data.fields.Address;
import com.pipl.api.data.fields.DOB;
import com.pipl.api.data.fields.Email;
import com.pipl.api.data.fields.Field;
import com.pipl.api.data.fields.Name;
import com.pipl.api.data.fields.Phone;
import com.pipl.api.data.fields.Username;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A request to Pipl's Search API.
 * <p/>
 * The request also supports prioritizing/filtering the type of records you
 * prefer to get in the response (see the appendPriorityRule and
 * addRecordsFilter methods).
 * <p/>
 * Sending the request and getting the response is very simple and can be done
 * by either making a blocking call to request.send() or by making a
 * non-blocking call to request.sendAsync(callback) which sends the request
 * asynchronously.
 */
public class SearchAPIRequest {
	public static String defaultApiKey;
	public static String USER_AGENT = "piplapis/java";
	public static String BASE_URL = "http://api.pipl.com/search/v3/json/?";
	// HTTPS is also supported:
	//public static String BASE_URL = "https://api.pipl.com/search/v3/json/?";
	private String apiKey;
	private Person person;
	private String queryParamsMode;
	private Boolean exactName;
	private ArrayList<String> filterRecordsBy;
	private ArrayList<String> prioritizeRecordsBy;

	/**
	 * Initiate a new request object with given query params. Each request must
	 * have at least one searchable parameter, meaning a name (at least first
	 * and last name), email, phone or username. Multiple query params are
	 * possible (for example querying by both email and phone of the person).
	 * 
	 * @param apiKey
	 *            String, a valid API key (use "samplekey" for experimenting).
	 *            Note that you can set a default API key
	 *            (SearchAPIRequest.defaultApiKey = '<your_key>') instead of
	 *            passing it to each request object.
	 * @param firstName
	 *            firstName, minimum 2 chars
	 * @param middleName
	 *            middleName
	 * @param lastName
	 *            lastName, minimum 2 chars
	 * @param rawName
	 *            an unparsed name containing at least a first name and a last
	 *            name.
	 * @param email
	 *            email
	 * @param phone
	 *            a String that will be striped from all non-digit characters
	 *            and converted to Long. IMPORTANT: Currently only US/Canada
	 *            phones can be searched by so country code is assumed to be 1,
	 *            phones with different country codes are considered invalid and
	 *            will be ignored.
	 * @param username
	 *            username, minimum 4 chars
	 * @param country
	 *            a 2 letter country code from:
	 *            http://en.wikipedia.org/wiki/ISO_3166-2
	 * @param state
	 *            a state code from:
	 *            http://en.wikipedia.org/wiki/ISO_3166-2%3AUS
	 *            http://en.wikipedia.org/wiki/ISO_3166-2%3ACA
	 * @param city
	 *            city
	 * @param rawAddress
	 *            An unparsed address
	 * @param fromAge
	 *            fromAge
	 * @param toAge
	 *            toAge
	 * @param person
	 *            A Person object (available at
	 *            piplapis.data.containers.Person). The person can contain every
	 *            field allowed by the data-model (see piplapis.data.fields) and
	 *            can hold multiple fields of the same type (for example: 2
	 *            email addresses, 3 addresses etc.)
	 * @param queryParamsMode
	 *            String, one of "and"/"or" (default "and"). Advanced parameter,
	 *            use only if you care about the value of
	 *            record.query_params_match in the response records. Each record
	 *            in the response has an attribute "query_params_match" which
	 *            indicates whether the record has the all fields from the query
	 *            or not. When set to "and" all query params are required in
	 *            order to get query_params_match=True, when set to "or" it's
	 *            enough that the record has at least one of each field type (so
	 *            if you search with a name and two addresses, a record with the
	 *            name and one of the addresses will have
	 *            query_params_match=True)
	 * @param exactName
	 *            boolean (default False). If set to True the names in the query
	 *            will be matched "as is" without compensating for nicknames or
	 *            multiple family names. For example "Jane Brown-Smith" won't
	 *            return results for "Jane Brown" in the same way
	 *            "Alexandra Pitt" won't return results for "Alex Pitt".
	 */
	public SearchAPIRequest(String apiKey, String firstName, String middleName,
			String lastName, String rawName, String email, String phone,
			String username, String country, String state, String city,
			String rawAddress, Integer fromAge, Integer toAge, Person person,
			String queryParamsMode, boolean exactName) {
		ArrayList<Field> fields = new ArrayList<Field>();
		if (person == null) {
			person = new Person.Builder().build();
		}
		if (!Utils.isNullOrEmpty(firstName) || !Utils.isNullOrEmpty(middleName)
				|| !Utils.isNullOrEmpty(lastName)) {
			fields.add(new Name.Builder().first(firstName).middle(middleName)
					.last(lastName).build());
		}
		if (!Utils.isNullOrEmpty(rawName)) {
			fields.add(new Name.Builder().raw(rawName).build());
		}
		if (!Utils.isNullOrEmpty(email)) {
			fields.add(new Email.Builder().address(email).build());
		}
		if (!Utils.isNullOrEmpty(phone)) {
			fields.add(Phone.fromText(phone));
		}
		if (!Utils.isNullOrEmpty(username)) {
			fields.add(new Username(null, username));
		}
		if (!Utils.isNullOrEmpty(country) || !Utils.isNullOrEmpty(state)
				|| !Utils.isNullOrEmpty(city)) {
			fields.add(new Address.Builder().country(country).state(state)
					.city(city).build());
		}
		if (!Utils.isNullOrEmpty(rawAddress)) {
			fields.add(new Address.Builder().raw(rawAddress).build());
		}
		if (fromAge != null || toAge != null) {
			fields.add(DOB.fromAgeRange(fromAge == null ? 0 : fromAge,
					toAge == null ? 1000 : fromAge));
		}
		person.addFields(fields);

		setApiKey(apiKey);
		setPerson(person);
		setQueryParamsMode(queryParamsMode);
		setExactName(exactName);
		this.filterRecordsBy = new ArrayList<String>();
		this.prioritizeRecordsBy = new ArrayList<String>();
	}

	private SearchAPIRequest(Builder builder) {
		this(builder.apiKey, builder.firstName, builder.middleName,
				builder.lastName, builder.rawName, builder.email,
				builder.phone, builder.username, builder.country,
				builder.state, builder.city, builder.rawAddress,
				builder.fromAge, builder.toAge, builder.person,
				builder.queryParamsMode, builder.exactName);
	}

	/****
	 * Same as the "regular constructor, but enables default values. If not
	 * passed: all params will get their java default value except for
	 * queryParamsMode that is defaulted to "and"
	 */
	public static class Builder {
		private String apiKey;
		private String firstName;
		private String middleName;
		private String lastName;
		private String rawName;
		private String email;
		private String phone;
		private String username;
		private String country;
		private String state;
		private String city;
		private String rawAddress;
		private Integer fromAge;
		private Integer toAge;
		private Person person;
		private String queryParamsMode = "and";
		private boolean exactName;

		public Builder city(String city) {
			this.city = city;
			return this;
		}

		public Builder rawAddress(String rawAddress) {
			this.rawAddress = rawAddress;
			return this;
		}

		public Builder fromAge(int fromAge) {
			this.fromAge = fromAge;
			return this;
		}

		public Builder toAge(int toAge) {
			this.toAge = toAge;
			return this;
		}

		public Builder person(Person person) {
			this.person = person;
			return this;
		}

		public Builder queryParamsMode(String queryParamsMode) {
			this.queryParamsMode = queryParamsMode;
			return this;
		}

		public Builder exactName(boolean exactName) {
			this.exactName = exactName;
			return this;
		}

		public Builder apiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder middleName(String middleName) {
			this.middleName = middleName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder rawName(String rawName) {
			this.rawName = rawName;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder state(String state) {
			this.state = state;
			return this;
		}

		public SearchAPIRequest build() {
			return new SearchAPIRequest(this);
		}

	}

	/**
	 * Transform the params to the API format, return a list of params.
	 * 
	 * @param domain
	 * @param category
	 * @param sponsoredSource
	 * @param hasField
	 * @param hasFields
	 * @param queryParamsMatch
	 * @param queryPersonMatch
	 * @return List of params
	 * @throws Exception
	 */
	public static ArrayList<String> prepareFilteringParams(String domain,
			String category, Boolean sponsoredSource,
			Class<? extends Field> hasField,
			ArrayList<Class<? extends Field>> hasFields,
			Boolean queryParamsMatch, Boolean queryPersonMatch)
			throws IllegalArgumentException {

		ArrayList<String> params = new ArrayList<String>();
		if (domain != null) {
			params.add("domain:" + domain);
		}
		if (category != null) {
			HashSet<String> set = new HashSet<String>();
			set.add(category);
			Source.validateCategories(set);
			params.add("category:" + category);
		}
		if (sponsoredSource != null) {
			params.add("sponsored_source:" + sponsoredSource);
		}
		if (queryParamsMatch != null) {
			params.add("query_params_match");
		}
		if (queryPersonMatch != null) {
			params.add("query_person_match");
		}
		if (hasFields == null) {
			hasFields = new ArrayList<Class<? extends Field>>();
		}
		if (hasField != null) {
			hasFields.add(hasField);
		}
		for (Class<? extends Field> gg : hasFields)
			params.add("has_field:" + gg.getSimpleName());
		return params;
	}

	/**
	 * Add a new "and" filter for the records returned in the response.
	 * <p/>
	 * IMPORTANT: This method can be called multiple times per request for
	 * adding multiple "and" filters, each of these "and" filters is interpreted
	 * as "or" with the other filters. For example:
	 * <p/>
	 * Examples:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * SearchAPIRequest apiRequest = new SearchAPIRequest.Builder().apiKey("samplekey").username("eric123").build();
	 * ArrayList<Field> hasFields = new ArrayList<Field>();
	 * hasFields.add(Phone.class);
	 * apiRequest.addRecordsFilter(new RecordsFilter.Builder().domain("linkedin").hasFields(hasFields).build());
	 * hasFields.add(Job.class);
	 * apiRequest.addRecordsFilter(new RecordsFilter.Builder().hasFields(hasFields).build());
	 * <p/>
	 * </pre>
	 * 
	 * </blockquote>
	 * <p/>
	 * The above request is only for records that are: (from LinkedIn AND has a
	 * phone) OR (has a phone AND has a job). Records that don't match this rule
	 * will not come back in the response.
	 * <p/>
	 * Please note that in case there are too many results for the query, adding
	 * filters to the request can significantly improve the number of useful
	 * results; when you define which records interest you, you'll get records
	 * that would have otherwise be cut-off by the limit on the number of
	 * records per query.
	 * 
	 * @param recordsFilter
	 *            a class instance that holds the params: domain, category,
	 *            sponsoredSource, hasFields, queryParamsMatch, queryPersonMatch
	 * @throws Exception
	 */
	public void addRecordsFilter(RecordsFilter recordsFilter) throws Exception {
		ArrayList<String> params = prepareFilteringParams(
				recordsFilter.getDomain(), recordsFilter.getCategory(),
				recordsFilter.getSponsoredSource(), null,
				recordsFilter.getHasFields(),
				recordsFilter.getQueryParamsMatch(),
				recordsFilter.getQueryPersonMatch());
		if (params != null) {
			filterRecordsBy.add(Utils.join(" AND ", params));
		}
	}

	/**
	 * Append a new priority rule for the records returned in the response.
	 * <p/>
	 * IMPORTANT: This method can be called multiple times per request for
	 * adding multiple priority rules, each call can be with only one argument
	 * and the order of the calls matter (the first rule added is the highest
	 * priority, the second is second priority etc). For example:
	 * <p/>
	 * Examples:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * SearchAPIRequest apiRequest = new SearchAPIRequest.Builder().apiKey("samplekey").username("eric123").build();
	 * PriorityRule priorityRule = new PriorityRule.Builder().domain("linkedin").hasField(Phone.class).build(); 
	 * apiRequest.appendPriorityRule(priorityRule);
	 * <p/>
	 * </pre>
	 * 
	 * </blockquote>
	 * <p/>
	 * In the response to the above request records from LinkedIn will be
	 * returned before records that aren't from LinkedIn and records with phone
	 * will be returned before records without phone.
	 * <p/>
	 * Please note that in case there are too many results for the query, adding
	 * priority rules to the request does not only affect the order of the
	 * records but can significantly improve the number of useful results; when
	 * you define which records interest you, you'll get records that would have
	 * otherwise be cut-off by the limit on the number of records per query.
	 * 
	 * @param priorityRule
	 *            a class instance that holds the params: domain, category,
	 *            sponsoredSource, hasField, queryParamsMatch, queryPersonMatch
	 */
	public void appendPriorityRule(PriorityRule priorityRule) {
		ArrayList<String> params = SearchAPIRequest.prepareFilteringParams(
				priorityRule.getDomain(), priorityRule.getCategory(),
				priorityRule.getSponsoredSource(), priorityRule.getHasField(),
				null, priorityRule.getQueryParamsMatch(),
				priorityRule.getQueryPersonMatch());
		if (params.size() > 1) {
			throw new IllegalArgumentException(
					"The function should be called with one argument");
		}
		if (params.size() > 0) {
			prioritizeRecordsBy.add(params.get(0));
		}
	}

	/**
	 * Check if the request is valid and can be sent, raise ValueError if not.
	 * 
	 * @param strict
	 *            `strict` is a boolean argument that defaults to True which
	 *            means an exception is raised on every invalid query parameter,
	 *            if set to False an exception is raised only when the search
	 *            request cannot be performed because required query params are
	 *            missing.
	 */
	public void validateQueryParams(boolean strict)
			throws IllegalArgumentException {
		if (Utils.isNullOrEmpty(this.apiKey)
				&& Utils.isNullOrEmpty(defaultApiKey)) {
			throw new IllegalArgumentException("API key is missing");
		}
		if (strict
				&& (!"and".equals(queryParamsMode) && !"or"
						.equals(queryParamsMode))) {
			throw new IllegalArgumentException(
					"queryParamsMode should be one of and/or");
		}
		if (!person.isSearchable()) {
			throw new IllegalArgumentException(
					"No valid name/username/phone/email in request");
		}
		if (strict && !person.unsearchableFields().isEmpty()) {
			throw new IllegalArgumentException("Some fields are unsearchable: "
					+ person.unsearchableFields());
		}
	}

	/**
	 * The URL of the request (str).
	 * 
	 * @return encoded url
	 * @throws IOException
	 *             thrown if Person object serialization fails
	 * @throws URISyntaxException
	 *             thrown if URL build fails
	 */
	public String url() throws IOException, URISyntaxException {
		URIBuilder ub;
		ub = new URIBuilder(BASE_URL);
		ub.addParameter("key", apiKey == null ? defaultApiKey : apiKey);
		ub.addParameter("person", Utils.toJson(person));
		ub.addParameter("query_params_mode", queryParamsMode);
		ub.addParameter("exact_name", String.valueOf(exactName));
		ub.addParameter("prioritize_records_by",
				Utils.join(",", prioritizeRecordsBy));
		ub.addParameter("filter_records_by", Utils.join(",", filterRecordsBy));
		return ub.toString();
	}

	/**
	 * Send the request and return the response or raise SearchAPIError.
	 * <p/>
	 * Calling this method blocks the program until the response is returned, if
	 * you want the request to be sent asynchronously please refer to the
	 * sendAsync method.
	 * 
	 * @param strictValidation
	 *            `strictValidation` is a bool argument that's passed to the
	 *            validateQueryParams method.
	 * @return The response is returned as a SearchAPIResponse object.
	 * @throws IllegalArgumentException
	 *             Raises IllegalArgumentException (raised from
	 *             validateQueryParams)
	 * @throws IOException
	 *             IOException
	 * @throws SearchAPIError
	 *             SearchAPIError (when the response is returned but contains an
	 *             error).
	 * @throws URISyntaxException
	 */
	public SearchAPIResponse send(boolean strictValidation)
			throws SearchAPIError, IOException, URISyntaxException {
		validateQueryParams(strictValidation);
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = null;
		String responseBody = null;
		try {
			HttpGet httpGet = new HttpGet(url());
			httpGet.setHeader("User-Agent", USER_AGENT);
			httpResponse = httpClient.execute(httpGet);
			responseBody = EntityUtils
					.toString(httpResponse.getEntity());
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return (SearchAPIResponse) Utils.fromJson(responseBody,
						SearchAPIResponse.class);
			} else {
				SearchAPIError searchAPIError = (SearchAPIError) Utils
						.fromJson(responseBody, SearchAPIError.class);
				throw searchAPIError;
			}
		} catch (JsonSyntaxException e) {
			throw new SearchAPIError(responseBody, httpResponse.getStatusLine().getStatusCode());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public SearchAPIResponse send() throws SearchAPIError, IOException,
			URISyntaxException {
		return send(true);
	}

	/**
	 * Same as send() but in a non-blocking way.
	 * <p/>
	 * Use this method if you want to send the request asynchronously so your
	 * program can do other things while waiting for the response.
	 * 
	 * @param strictValidation
	 *            `strictValidation` is a bool argument that's passed to the
	 *            validateQueryParams method.
	 * @param searchAPICallBack
	 *            a callback that will receive the response once the response is
	 *            returned
	 * @throws IllegalArgumentException
	 *             Raises IllegalArgumentException (raised from
	 *             validateQueryParams)
	 * @throws IOException
	 *             IOException
	 * @throws URISyntaxException
	 */
	public void sendAsync(boolean strictValidation,
			SearchAPICallBack searchAPICallBack) throws IOException,
			URISyntaxException {
		new ResponseThread(searchAPICallBack, strictValidation).start();
	}

	/**
	 * Same as sendAsync(boolean strictValidation,SearchAPICallBack
	 * searchAPICallBack) but with default strictValidation==true
	 * 
	 * @param searchAPICallBack
	 *            a callback that will receive the response once the response is
	 *            returned
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void sendAsync(SearchAPICallBack searchAPICallBack)
			throws IOException, URISyntaxException {
		sendAsync(true, searchAPICallBack);
	}

	private class ResponseThread extends Thread {
		private SearchAPICallBack searchAPICallBack;
		private boolean strictValidation;

		private ResponseThread(SearchAPICallBack searchAPICallBack,
				boolean strictValidation) {
			this.searchAPICallBack = searchAPICallBack;
			this.strictValidation = strictValidation;
		}

		@Override
		public void run() {
			try {
				searchAPICallBack.callback(send(strictValidation));
			} catch (SearchAPIError | IOException | URISyntaxException e) {
				searchAPICallBack.errback(e);
			}
		}
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getQueryParamsMode() {
		return queryParamsMode;
	}

	public void setQueryParamsMode(String queryParamsMode) {
		this.queryParamsMode = queryParamsMode;
	}

	public boolean isExactName() {
		return exactName;
	}

	public void setExactName(Boolean exactName) {
		this.exactName = exactName;
	}

}
