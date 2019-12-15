package jp.utokyo.shibalab.facebookarchiveparser.friends;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for rejected friend requests
 */
public class RejectedRequests extends AFriends {
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param friends friend list
	 */
	@JsonCreator
	protected RejectedRequests( @JsonProperty("rejected_requests") List<Friend> friends) {
		super(friends);
	}
}
