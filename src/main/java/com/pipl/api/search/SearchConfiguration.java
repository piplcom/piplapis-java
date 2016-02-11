/**
 * 
 */
package com.pipl.api.search;

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
	public static final String ALL_SOURCES = "all";
	public static final String MATCHING_SOURCES = "matching";
	public String protocol = "http"; // https is also supported.
	public String host = "api.pipl.com";
	public String path = "/search/";
	public String apiKey = "sample_key";
	public Float minimumProbability;
	public Float minimumMatch;
	public String showSources;
	public Boolean hideSponsored;
	public Boolean liveFeeds;
	public String matchRequirements;
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
			Expression matchRequirements) {
		this(protocol, host, path, apiKey, minimumProbability, showSources, hideSponsored, minimumMatch, liveFeeds);
		setMatchRequirements(matchRequirements);
	}
	
	public SearchConfiguration(Builder builder) {
		setProtocol(builder.protocol);
		setHost(builder.host);
		setPath(builder.path);
		setApiKey(builder.apiKey);
		setMinimumProbability(builder.minimumProbability);
		setShowSources(builder.showSources);
		setHideSponsored(builder.hideSponsored);
		setMinimumMatch(builder.minimumMatch);
		setLiveFeeds(builder.liveFeeds);
		setMatchRequirements(builder.matchRequirements);
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
	 * @return the URL path of the API  ("/search/v4/" by default)
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
			return 1;
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
	 * "email & (phone | name)".
	 */
	public void setMatchRequirements(String matchRequirements) {
		this.matchRequirements = matchRequirements;
	}
	
	/**
	 * @param expression an Expression that describes a match requirement
	 * This is a helper method that allows you to build and set a
	 * match requirement programmatically.
	 * See the documentation for 
	 * {@link com.pipl.api.search.SearchConfiguration.Expression}
	 * for more details.
	 */
	public void setMatchRequirements(Expression expression) {
		this.matchRequirements = expression.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(protocol).append("://").append(host).append(path)
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
		if (matchRequirements!=null) {
			sb.append("&match_requirements=").append(matchRequirements);
		}
		if (extraParams!=null) {
			sb.append(extraParams);
		}
		return sb.toString();
	}

	public static class Builder {
		private String protocol ="http";
		private String host = "api.pipl.com";
		private String path = "/search/v4/";
		private String apiKey = "samplekey";
		private Float minimumProbability;
		private String showSources;
		private Boolean hideSponsored;
		private Float minimumMatch;
		private Boolean liveFeeds;
		private String matchRequirements;
		
		public Builder protocol(String protocol) {
			this.protocol = protocol;
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
		
		public Builder minimumProbability(float minimumProbability) {
			this.minimumProbability = minimumProbability;
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
		
		public Builder matchRequirements(Expression expression) {
			this.matchRequirements = expression.toString();
			return this;
		}

	}

	/**
	 * You can use the Expression interface and its implementation, 
	 * AtomicExpression to programmatically create a matchRequirment
	 * Simply start form AtomicExpressions, use the or/and methods on
	 * them, and then use the resulting expressions to build even more
	 * complex expressions.
	 * Every and/or is considered to be in parenthesis if needed.
	 * Example:
	 * AtomicExpression.NAME.or(AtomicExpression.ADDRESS, AtomicExpression.PHONE).and(AtomicExpression.EMAIL)
	 * is equivalent to the matchRequiremnet String: (NAME | ADDRESS | PHONE) & EMAIL.
	 * You can find more samples in {@link com.pipl.api.ExpressionTest}.
	 */
	public static interface Expression {
		public enum Type {ATOMIC, AND, OR};
		public Type type();
		public Expression and(Expression... expressions);
		public Expression or(Expression... expressions);
	}
	
	public static enum AtomicExpression implements Expression {
		NAME, ADDRESS, EMAIL, PHONE, JOB, EDUCATION, IMAGE, USERNAME, USER_ID, DOB, URL, GENDER, ETHNICITY, LANGUAGE, ORIGIN_COUNTRY;

		@Override
		public Type type() {
			return Type.ATOMIC;
		}

		@Override
		public Expression and(Expression... expressions) {
			return LogicalExpression.and(this, expressions);
		}

		@Override
		public Expression or(Expression... expressions) {
			return LogicalExpression.or(this, expressions);
		}
	
	private static class LogicalExpression implements Expression {
		private Type type;
		private String asString;
		
		public LogicalExpression(Type type, String asString) {
			this.type = type;
			this.asString = asString;
		}
		
		public Type type() {
			return type;
		}
		
		public Expression and(Expression... expressions) {
			return and(this, expressions);
		}
		
		public Expression or(Expression... expressions) {
			return or(this, expressions);
		}

		@Override
		public String toString() {
			return asString;
		}
		
		public static Expression and(Expression expression, Expression... expressions) {
			StringBuilder sb = new StringBuilder();
			if (expression.type()==Type.OR) {
				sb.append("(");
			}
			sb.append(expression.toString());
			if (expression.type()==Type.OR) {
				sb.append(")");
			}
			for (Expression e : expressions) {
				sb.append(" & ");
				if (e.type()==Type.OR) {
					sb.append("(");
				}
				sb.append(e.toString());
				if (e.type()==Type.OR) {
					sb.append(")");
				}
			}
			return new LogicalExpression(Type.AND, sb.toString());
			
		}
		
		public static Expression or(Expression expression, Expression... expressions) {
			StringBuilder sb = new StringBuilder();
			sb.append(expression.toString());
			for (Expression e : expressions) {
				sb.append(" | ");
				sb.append(e.toString());
			}
			return new LogicalExpression(Type.OR, sb.toString());
		}
	}
	};
	
}
