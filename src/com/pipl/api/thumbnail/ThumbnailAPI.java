package com.pipl.api.thumbnail;


import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.pipl.api.data.Utils;
import com.pipl.api.data.fields.Image;

/**
 * JAVA wrapper for Pipl's Thumbnail API.
 * <p/>
 * Pipl's thumbnail API provides a thumbnailing service for presenting images in
 * your application. The images can be from the results you got from our Search
 * API but it can also be any web URI of an image.
 * <p/>
 * The thumbnails returned by the API are in the height/width defined in the
 * request. Additional features of the API are: - Detect and Zoom-in on human
 * faces (in case there's a human face in the image). - Optionally adding to the
 * thumbnail the favicon of the website where the image is from (for
 * attribution, recommended for copyright reasons).
 * <p/>
 * This module contains only one function - generateThumbnailUrl() that can be
 * used for transforming an image URL into a thumbnail API URL.
 */
public class ThumbnailAPI {
	public static String defaultApiKey;
	public static String BASE_URL = "http://api.pipl.com/thumbnail/v2/?";
	// HTTPS is also supported:
	//public static String BASE_URL = "https://api.pipl.com/thumbnail/v2/?";
	public static int MAX_PIXELS = 500;

	/**
	 * Take an image URL and generate a thumbnail URL for that image. * Example
	 * (thumbnail URL from an image URL):
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 *  >>> ThumbnailAPI.generateThumbnailUrl("http://a7.twimg.com/a/ab76f.jpg", 100, 100, "twitter.com", true, "samplekey");
	 * "http://api.pipl.com/thumbnail/v2/?key=samplekey&favicon_domain=twitter.com&height=100&width=100&zoom_face=True&image_url=http%3A%2F%2Fa7.twimg.com%2Fa%2Fab76f.jpg"
	 * <p/>
	 * </pre>
	 * 
	 * </blockquote>
	 * <p/>
	 * Example (thumbnail URL from a record that came in the response of our
	 * Search API):
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * >>> ThumbnailAPI.generateThumbnailUrl(record.images.get(0).url, 100, 100, record.source.domain, true, "samplekey");
	 * "http://api.pipl.com/thumbnail/v2/?key=samplekey&favicon_domain=twitter.com&height=100&width=100&zoom_face=True&image_url=http%3A%2F%2Fa7.twimg.com%2Fa%2Fab76f.jpg"
	 * <p/>
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param imageUrl
	 *            String, the URL of the image you want to thumbnail.
	 * @param height
	 *            int, requested thumbnail height in pixels, maximum 500
	 * @param width
	 *            int, requested thumbnail width in pixels, maximum 500.
	 * @param faviconDomain
	 *            optional, the domain of the website where the image came from,
	 *            the favicon will be added to the corner of the thumbnail,
	 *            recommended for copyright reasones. IMPORTANT: Don't assume
	 *            that the domain of the website is the domain from `image_url`,
	 *            it's possible that domain1.com hosts its images on
	 *            domain2.com.
	 * @param zoomFace
	 *            boolean, indicates whether you want the thumbnail to zoom on
	 *            the face in the image (in case there is a face) or not.
	 * @param apiKey
	 *            String, a valid API key (use "samplekey" for experimenting).
	 *            Note that you can set a default API key
	 *            (piplapis.thumbnail.defaultApiKey = '<your_key>') instead of
	 *            passing your key in each call.
	 * @return
	 * @throws IllegalArgumentException
	 *             is thrown in case of illegal parameters.
	 * @throws UnsupportedEncodingException
	 *             is thrown in case of URL encoding failure
	 * @throws URISyntaxException
	 */
	public static String generateThumbnailUrl(String imageUrl, int height,
			int width, String faviconDomain, boolean zoomFace, String apiKey)
			throws IllegalArgumentException, UnsupportedEncodingException,
			URISyntaxException {
		if (Utils.isNullOrEmpty(apiKey) && Utils.isNullOrEmpty(defaultApiKey)) {
			throw new IllegalArgumentException("A valid API key is required");
		}
		if (!new Image(null, imageUrl).isValidUrl()) {
			throw new IllegalArgumentException("imageUrl is not a valid URL");
		}

		if (!(0 < height && height <= MAX_PIXELS && 0 < width && width <= MAX_PIXELS)) {
			throw new IllegalArgumentException(
					"height/width must be between 0 and " + MAX_PIXELS);
		}
		String key = Utils.isNullOrEmpty(apiKey) ? defaultApiKey : apiKey;
		if (faviconDomain == null) {
			faviconDomain = "";
		}
		URIBuilder ub;
		ub = new URIBuilder(BASE_URL);
		ub.addParameter("key", key);
		ub.addParameter("image_url", imageUrl);
		ub.addParameter("height", String.valueOf(height));
		ub.addParameter("width", String.valueOf(width));
		ub.addParameter("favicon_domain", faviconDomain);
		ub.addParameter("zoom_face", String.valueOf(zoomFace));
		return ub.toString();
	}

	private static String generateThumbnailUrl(Generator generator)
			throws IllegalArgumentException, UnsupportedEncodingException,
			URISyntaxException {
		return ThumbnailAPI.generateThumbnailUrl(generator.imageUrl,
				generator.height, generator.width, generator.faviconDomain,
				generator.zoomFace, generator.apiKey);
	}

	public static class Generator {
		private String imageUrl;
		private int height;
		private int width;
		private String faviconDomain;
		private boolean zoomFace=true;
		private String apiKey;

		public String generate() throws IllegalArgumentException, UnsupportedEncodingException, URISyntaxException {
			return ThumbnailAPI.generateThumbnailUrl(this);
		}
		
		public Generator imageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}

		public Generator height(int height) {
			this.height = height;
			return this;
		}

		public Generator width(int width) {
			this.width = width;
			return this;
		}

		public Generator faviconDomain(String faviconDomain) {
			this.faviconDomain = faviconDomain;
			return this;
		}

		public Generator zoomFace(boolean zoomFace) {
			this.zoomFace = zoomFace;
			return this;
		}

		public Generator apiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
	}

}
