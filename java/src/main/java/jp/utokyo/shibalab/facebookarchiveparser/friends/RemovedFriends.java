package jp.utokyo.shibalab.facebookarchiveparser.friends;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for removed friends
 */
public class RemovedFriends extends AFriends {
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param friends friend list
	 */
	@JsonCreator
	protected RemovedFriends( @JsonProperty("deleted_friends") List<Friend> friends) {
		super(friends);
	}
}
