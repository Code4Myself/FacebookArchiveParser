package jp.utokyo.shibalab.facebookarchiveparser.friends;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for friend list
 */
public class Friends extends AFriends {
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * initialization 
	 * @param friends friend list
	 */
	@JsonCreator
	protected Friends( @JsonProperty("friends") List<Friend> friends) {
		super(friends);
	}
}
