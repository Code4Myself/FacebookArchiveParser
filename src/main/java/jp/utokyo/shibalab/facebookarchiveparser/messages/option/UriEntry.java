package jp.utokyo.shibalab.facebookarchiveparser.messages.option;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for URI entry in message
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class UriEntry {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** URI */
	@JsonProperty("uri")
	private String  _uri;		
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get URI
	 * @return URI
	 */
	public String getUri() {
		return _uri;
	}
	
	/**
	 * set URI
	 * @param uri URI
	 */
	public void setUri(String uri) {
		_uri = uri;
	}
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() { 
		return getUri();
	}
}