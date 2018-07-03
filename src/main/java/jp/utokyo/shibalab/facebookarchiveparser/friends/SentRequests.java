package jp.utokyo.shibalab.facebookarchiveparser.friends;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for sent friend requests
 */
public class SentRequests extends AFriends {
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param friends friend list
	 */
	@JsonCreator
	protected SentRequests( @JsonProperty("sent_requests") List<Friend> friends) {
		super(friends);
	}
}
