package com.pipl.api.data.fields;


import java.text.MessageFormat;
import java.util.regex.Pattern;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A URL of an image of a person.
 */
public class Image extends AbstractField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	public String url;
	@Expose
	@SerializedName("thumbnail_token")
	private String thumbnailToken;
	
	private static Pattern dsidParam = Pattern.compile("&dsid=\\d*");

	public Image() {
	}
	
	public String getUrl() {
		return url;
	}

	/**
	 * @param height - the desired height of the thumbnail.
	 * @param width - the desired width of the thumbnail.
	 * @param favicon - if true, adds the favicon of the source site in the bottom right corner of the image.  
	 * @param detectFace - if true, attempts to focus the thumbnail to the face in the image. 
	 * @param useHttps - if true, returns an https URL. Otherwise, an HTTP URL.
	 * @return A URL to be used in an <img src=""> elements. The URL returns a thumbnail image created from the original image URL. 
	 */
	public String getThumbnailUrl(int height, int width, boolean favicon, boolean detectFace, boolean useHttps) {
		return MessageFormat.format("{0}://thumb.pipl.com/image?height={1}&width={2}&favicon={3}&zoom_face={4}&token={5}", useHttps ? "https" : "http", height, width, favicon ? 1 : 0, detectFace ? 1 : 0, thumbnailToken);
	}

	/**
	 * @param height - the desired height of the thumbnail.
	 * @param width - the desired width of the thumbnail.
	 * @param favicon - if true, adds the favicon of the source site in the bottom right corner of the image.  
	 * @param detectFace - if true, attempts to focus the thumbnail to the face in the image. 
	 * @param useHttps - if true, returns an https URL. Otherwise, an HTTP URL.
	 * @param image1 - the intended image for this thumbnail 
	 * @param image2 - the fall-back image for this thumbnail 
	 * @return A URL to be used in an <img src=""> elements. The URL returns a thumbnail image created from the original image URL. 
	 */
	static public String generateRedundantThumbnailUrl(int height, int width, boolean favicon, boolean detectFace, boolean useHttps, Image image1, Image image2) {
		String token1 = dsidParam.matcher(image1.thumbnailToken).replaceAll("");
		String token2 = dsidParam.matcher(image2.thumbnailToken).replaceAll("");
		return MessageFormat.format("{0}://thumb.pipl.com/image?height={1}&width={2}&favicon={3}&zoom_face={4}&tokens={5},{6}", useHttps ? "https" : "http", height, width, favicon ? 1 : 0, detectFace ? 1 : 0, token1, token2);
	}

	@Override
	public String toString() {
		return url;
	}
}