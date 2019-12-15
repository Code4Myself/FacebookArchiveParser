package jp.utokyo.shibalab.facebookarchiveparser.messages.option;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for share option in message
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Share {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** link */
	@JsonProperty("link")
	private String  _link;		
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get link
	 * @return link
	 */
	public String getLink() {
		return _link;
	}
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() { 
		return getLink();
	}
}
