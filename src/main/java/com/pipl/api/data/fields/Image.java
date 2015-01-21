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

	public String getThumbnailUrl(int height, int width, boolean favicon, boolean detectFace) {
		return MessageFormat.format("https://thumb.pipl.com/cgi-bin/fdt.fcgi?hg={0}&wd={1}&favicon={2}&th={3}&eurl={4}", height, width, favicon ? 1 : 0, detectFace ? 1 : 0, thumbnailToken);
	}

}