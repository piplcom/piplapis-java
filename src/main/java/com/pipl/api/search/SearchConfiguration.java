package com.pipl.api.search;

import com.pipl.api.data.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Used to define the metadata of a SearchAPIRequest.
 * In most cases the default configuration is good enough,
 * except for the apiKey which needs to contain the key
 * provisioned by Pipl.
 * You may also want to look into setting showSources to true.
 * You can modify the default configuration by using the static
 * SearchAPIRequest.getDefaultConfiguration(), or set a
 * configuration for one specific search by passing a
 * Configuration object to that request, before calling
 * one of the send() methods.
 */
public class SearchConfiguration {

	public static final String DEFAULT_API_VERSION = "5";
	public static final String ALL_SOURCES = "all";
	public static final String MATCHING_SOURCES = "matching";
	public static final String DEFAULT_PROTOCOL = "https";
	public static final String DEFAULT_HOST = "api.pipl.com";
	public static final String DEFAULT_PATH = "/search/";
	public static final String DEFAULT_KEY = null;
	public String protocol = DEFAULT_PROTOCOL; // https is also supported.
	public String host = DEFAULT_HOST;
	public String path = DEFAULT_PATH;
	public String apiKey = DEFAULT_KEY;

	public String apiVersion = DEFAULT_API_VERSION;

	public Float minimumProbability;
	public Float minimumMatch;
	public Boolean topMatch;
	public String showSources;
	public Boolean hideSponsored;
	public Boolean liveFeeds;
	public String matchRequirements;
	public String sourceCategoryRequirements;
	public Boolean inferPersons;
	public String extraParams;

	public SearchConfiguration() {
	}

	public SearchConfiguration(String protocol, String host, String path,
			String apiKey, float minimumProbability, String showSources,
			boolean hideSponsored, float minimumMatch, boolean liveFeeds) {
		setProtocol(protocol);
		setHost(host);
		setPath(path);
		setApiKey(apiKey);
		setMinimumProbability(minimumProbability);
		setShowSources(showSources);
		setHideSponsored(hideSponsored);
		setMinimumMatch(minimumMatch);
		setLiveFeeds(liveFeeds);
	}

	public SearchConfiguration(String protocol, String host, String path,
			String apiKey, float minimumProbability, String showSources,
			boolean hideSponsored, float minimumMatch, boolean liveFeeds,
			String matchRequirements) {
		this(protocol, host, path, apiKey, minimumProbability, showSources, hideSponsored, minimumMatch, liveFeeds);
		setMatchRequirements(matchRequirements);
	}

	public SearchConfiguration(String protocol, String host, String path,
			String apiKey, float minimumProbability, String showSources,
			boolean hideSponsored, float minimumMatch, boolean liveFeeds,
			String matchRequirements, String sourceCategoryRequirements) {
		this(protocol, host, path, apiKey, minimumProbability, showSources, hideSponsored, minimumMatch, liveFeeds,
				matchRequirements);
		setSourceCategoryRequirements(sourceCategoryRequirements);
	}

	public SearchConfiguration(String protocol, String host, String path,
			String apiKey, float minimumProbability, String showSources,
			boolean hideSponsored, float minimumMatch, boolean liveFeeds,
			String matchRequirements, String sourceCategoryRequirements, boolean inferPersons) {
		this(protocol, host, path, apiKey, minimumProbability, showSources, hideSponsored, minimumMatch, liveFeeds,
				matchRequirements, sourceCategoryRequirements);
		setInferPersons(inferPersons);
	}

	public SearchConfiguration(Builder builder) {
		this.protocol = builder.protocol;
		this.host = builder.host;
		this.path = builder.path;
		this.apiKey = builder.apiKey;
		this.minimumProbability = builder.minimumProbability;
		this.showSources = builder.showSources;
		this.topMatch = builder.topMatch;
		this.hideSponsored = builder.hideSponsored;
		this.minimumMatch = builder.minimumMatch;
		this.liveFeeds = builder.liveFeeds;
		this.matchRequirements = builder.matchRequirements;
		this.sourceCategoryRequirements = builder.sourceCategoryRequirements;
		this.inferPersons = builder.inferPersons;

		if (!Utils.isNullOrEmpty(builder.apiVersion))
        	this.apiVersion = builder.apiVersion;
	}

	/**
	 * @return the protocol ("http" by default).
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 * "http" and "https" are the supported values
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the host ("api.pipl.com" by default)
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 * This should never be changed.
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the URL path of the API  ("/search/" by default)
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the URL path to set
	 * This should never be changed.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the apiKey ("samplekey" by default)
	 */
	public String getApiKey() {
		return apiKey;
	}

	public void setTopMatch(Boolean topMatch) {
		this.topMatch = topMatch;
	}

	public Boolean getTopMatch(){
		return this.topMatch;
	}

	/**
	 * @param apiKey the apiKey to set
	 * The default apiKey is very limited and will only work for
	 * a few requests per day. It is important that you get
	 * your own key and set it here.
	 * A free demo key can be obtained from our site.
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @return the minimumProbability (defaults to 1 if not set.)
	 */
	public float getMinimumProbability() {
		if (minimumProbability==null)
			return 0.9f;
		return minimumProbability;
	}

	/**
	 * @param minimumProbability the minimumProbability to set
	 * A probability value (0-1). The minimum required confidence for
	 * inferred data in the response.
	 */
	public void setMinimumProbability(float minimumProbability) {
		this.minimumProbability = minimumProbability;
	}

	/**
	 * @return the minimumMatch (defaults to 0 if not set.)
	 */
	public float getMinimumMatch() {
		if (minimumMatch==null)
			return 0;
		return minimumMatch;
	}

	/**
	 * @param minimumMatch the minimumMatch to set
	 * A probability value (0-1). Possible persons with a match score
	 * less than this value will not be returned.
	 * Set to 1 to only receive full matches.
	 * Defaults to 0.
	 */
	public void setMinimumMatch(float minimumMatch) {
		this.minimumMatch = minimumMatch;
	}

	/**
	 * @return the showSources (if not set, no sources will be returned in the response)
	 */
	public String getShowSources() {
		return showSources;
	}

	/**
	 * @param showSources the showSources to set
	 * Either "all" or "matching".
	 * If null, no sources will be returned.
	 * "all" - all sources will be returned.
	 * "matching" - only sources belonging to a matching person will be returned.
	 */
	public void setShowSources(String showSources) {
		this.showSources = showSources;
	}

	/**
	 * @return the hideSponsored (defaults to false if not set)
	 */
	public boolean getHideSponsored() {
		return hideSponsored;
	}

	/**
	 * @param hideSponsored the hideSponsored to set
	 * Set to true if you don't want to receive sponsored results.
	 */
	public void setHideSponsored(boolean hideSponsored) {
		this.hideSponsored = hideSponsored;
	}

	/**
	 * @return the liveFeeds (defaults to true if not set)
	 */
	public boolean getLiveFeeds() {
		return liveFeeds;
	}

	/**
	 * @param liveFeeds the liveFeeds to set
	 * Whether to use live feeds.
	 * In plans that include live feeds, can be set to
	 * false for a speedier response.
	 */
	public void setLiveFeeds(boolean liveFeeds) {
		this.liveFeeds = liveFeeds;
	}

	public String getMatchRequirements() {
		return matchRequirements;
	}

	/**
	 * @param matchRequirements a match requirements criteria.
	 * This criteria defines what fields must be present in an
	 * API response in order for it to be returned as a match.
	 * For example: "email" or "email or phone", or
	 * "email &amp; (phone | name)".
	 */
	public void setMatchRequirements(String matchRequirements) {
		this.matchRequirements = matchRequirements;
	}

	public String getSourceCategoryRequirements() {
		return this.sourceCategoryRequirements;
	}

	/**
	 * @param sourceCategoryRequirements a source category requirements criteria.
	 * This criteria defines what source categories must be present in an
	 * API response in order for it to be returned as a match.
	 * For example: "email" or "email or phone", or
	 * "media &amp; (personal_profiles | professional_and_business)".
	 */
	public void setSourceCategoryRequirements(String sourceCategoryRequirements) {
		this.sourceCategoryRequirements = sourceCategoryRequirements;
	}

	public boolean getInferPersons() {
		if (this.inferPersons) {
			return false;
		}
		return this.inferPersons;
	}

	/**
	 * @param inferPersons If set, the API may return person responses made up solely from data inferred by statistical analysis.
	 */
	public void setInferPersons(boolean inferPersons) {
		this.inferPersons = inferPersons;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(protocol).append("://").append(host).append(path).append("v" + apiVersion + "/")
		.append("?key=").append(apiKey);
		if (minimumProbability!=null) {
			sb.append("&minimum_probability=").append(String.valueOf(minimumProbability));
		}
		if (minimumMatch!=null) {
			sb.append("&minimum_match=").append(String.valueOf(minimumMatch));
		}
		if (hideSponsored!=null) {
			sb.append("&hide_sponsored=").append(String.valueOf(hideSponsored));
		}
		if (liveFeeds!=null) {
			sb.append("&live_feeds=").append(String.valueOf(liveFeeds));
		}
		if (showSources!=null) {
			sb.append("&show_sources=").append(String.valueOf(showSources));
		}
		if (topMatch!=null) {
			sb.append("&top_match=").append(String.valueOf(topMatch));
		}
		if (matchRequirements!=null) {
//		    from https://stackoverflow.com/questions/6030059/url-decoding-unsupportedencodingexception-in-java
            try {
                sb.append("&match_requirements=").append(URLEncoder.encode(matchRequirements, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError("UTF-8 is unknown");
            }
        }
		if (sourceCategoryRequirements!=null) {
		    try {
                sb.append("&source_category_requirements=").append(URLEncoder.encode(sourceCategoryRequirements, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError("UTF-8 is unknown");
            }
		}

		if (inferPersons!=null) {
			sb.append("&infer_persons=").append(String.valueOf(inferPersons));
		}

		if (extraParams!=null) {
			sb.append(extraParams);
		}

		return sb.toString();
	}

	public static class Builder {
		private String protocol =DEFAULT_PROTOCOL;
		private String host = DEFAULT_HOST;
		private String path = DEFAULT_PATH;
		private String apiKey = DEFAULT_KEY;
		private String apiVersion;
		private Float minimumProbability;
		private Boolean topMatch;
		private String showSources;
		private Boolean hideSponsored;
		private Float minimumMatch;
		private Boolean liveFeeds;
		private String matchRequirements;
		private String sourceCategoryRequirements;
		private Boolean inferPersons;

		public Builder protocol(String protocol) {
			this.protocol = "https";
			return this;
		}

		public Builder host(String host) {
			this.host = host;
			return this;
		}

		public Builder path(String path) {
			this.path = path;
			return this;
		}

		public Builder apiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}

		public Builder apiVersion(String apiVersion) {
			this.apiVersion = apiVersion;
			return this;
		}

		public Builder minimumProbability(float minimumProbability) {
			this.minimumProbability = minimumProbability;
			return this;
		}

		public Builder topMatch(Boolean topMatch) {
			this.topMatch = topMatch;
			return this;
		}

		public Builder minimumMatch(float minimumMatch) {
			this.minimumMatch = minimumMatch;
			return this;
		}

		public Builder showSources(String showSources) {
			this.showSources =showSources;
			return this;
		}

		public Builder hideSponsored(boolean hideSponsored) {
			this.hideSponsored = hideSponsored;
			return this;
		}

		public Builder liveFeeds(boolean liveFeeds) {
			this.liveFeeds = liveFeeds;
			return this;
		}

		public Builder matchRequirements(String matchRequirements) {
			this.matchRequirements = matchRequirements;
			return this;
		}

		public Builder sourceCategoryRequirements(String sourceCategoryRequirements) {
			this.sourceCategoryRequirements = sourceCategoryRequirements;
			return this;
		}

		public Builder inferPersons(boolean inferPersons) {
			this.inferPersons = inferPersons;
			return this;
		}

		public SearchConfiguration build() {
			return new SearchConfiguration(this);
		}
	}

}
