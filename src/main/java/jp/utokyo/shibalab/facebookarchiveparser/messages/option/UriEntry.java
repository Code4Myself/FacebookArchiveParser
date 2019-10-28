package jp.utokyo.shibalab.facebookarchiveparser.messages.option;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	/** timestamp */
	@JsonProperty("creation_timestamp")
	private Long    _creation;
	

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
	
	/**
	 * get creation time-stamp
	 * @return creation time-stamps
	 */
	public Date getCreationTimestamp() {
		return _creation != null ? new Date(_creation*1000L) : null;
	}
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() { 
		if( _creation != null ) { 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return String.format("%s|%s", _uri, sdf.format(getCreationTimestamp()));
		}
		else {
			return String.format("%s", _uri);			
		}
	}
}