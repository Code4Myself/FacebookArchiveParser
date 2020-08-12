package jp.utokyo.shibalab.facebookarchiveparser.messages.option;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Plan Object (details are unknown)
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Plan {

	
	@JsonProperty("timestamp")
	private Long _timestamp;
	
	
	public Date getTimestamp() {
		if( _timestamp != null ) {
			return new Date(_timestamp*1000L);
		}
		else {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getTimestamp());
	}
}
