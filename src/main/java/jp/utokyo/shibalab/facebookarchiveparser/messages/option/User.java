package jp.utokyo.shibalab.facebookarchiveparser.messages.option;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

	
	@JsonProperty("name")
	private String _name;
	
	
	public String getName() {
		return _name;
	}
	
	@Override
	public String toString() {
		return _name;
	}
}
