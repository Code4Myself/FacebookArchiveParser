package jp.utokyo.shibalab.facebookarchiveparser.friends;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for received friend requests
 */
public class ReceivedRequests extends AFriends {
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param friends friend list
	 */
	@JsonCreator
	protected ReceivedRequests( @JsonProperty("received_requests") List<Friend> friends) {
		super(friends);
	}
}
