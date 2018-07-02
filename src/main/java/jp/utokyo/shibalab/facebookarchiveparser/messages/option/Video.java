package jp.utokyo.shibalab.facebookarchiveparser.messages.option;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for video option in message
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Video extends UriEntry {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** thumbnail URL entry */
	@JsonProperty("thumbnail")
	private UriEntry _thumbnail;
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get thumbnail image URI
	 * @return thumbnail
	 */
	public UriEntry getThumbnail() {
		return _thumbnail;
	}
	
	/* @see jp.utokyo.shibalab.facebookarchiveparser.messages.option.UriEntry#toString() */
	@Override
	public String toString() {
		return String.format("uri=%s|thumbnail=%s",getUri(),getThumbnail().getUri());
	}
}
