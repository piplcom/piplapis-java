package com.pipl.api.data.fields;


import java.text.MessageFormat;

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
		return MessageFormat.format("{0}://thumb.pipl.com/cgi-bin/fdt.fcgi?height={1}&width={2}&favicon={3}&zoom_face={4}&token={5}", useHttps ? "https" : "http", height, width, favicon ? 1 : 0, detectFace ? 1 : 0, thumbnailToken);
	}

	@Override
	public String toString() {
		return url;
	}
}