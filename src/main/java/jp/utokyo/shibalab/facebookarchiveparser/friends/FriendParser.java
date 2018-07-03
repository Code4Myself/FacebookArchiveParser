package jp.utokyo.shibalab.facebookarchiveparser.friends;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import jp.utokyo.shibalab.facebookarchiveparser.FBStringDeserializer;

/**
 * Class for Facebook Friend parser
 */
public class FriendParser {
	/* ==============================================================
	 * static fields
	 * ============================================================== */
	/** friends.json: friends list file in JSON format */
	public static final String FRIEND_JSON = "friends.json";
	
	/** received_friend_request.json: received friend request list in JSON format */
	public static final String RECEIVED_FRIEND_REQUESTS_JSON = "received_friend_requests.json";

	/** rejected_friend_request.json: rejected friend request list in JSON format */
	public static final String REJECTED_FRIEND_REQUESTS_JSON = "rejected_friend_requests.json";

	/** removed_friends.json: removed friend list in JSON format */
	public static final String REMOVED_FRIENDS_JSON          = "removed_friends.json";

	/** sent_friend_request.json: sent friend request list in JSON format */
	public static final String SENT_FRIEND_REQUESTS_JSON     = "sent_friend_requests.json";


	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */


	
	public List<Friend> parseFriend(File jsonFile) {
		return parseFriends(jsonFile,Friends.class);
	}
	
	public List<Friend> parseRemovedFriends(File jsonFile) {
		return parseFriends(jsonFile,RemovedFriends.class);
	}

	public List<Friend> parseRejectedRequests(File jsonFile) {
		return parseFriends(jsonFile,RejectedRequests.class);
	}

	public List<Friend> parseReceivedRequests(File jsonFile) {
		return parseFriends(jsonFile,ReceivedRequests.class);
	}

	public List<Friend> parseSentRequests(File jsonFile) {
		return parseFriends(jsonFile,SentRequests.class);
	}

	
	/**
	 * 
	 * @param jsonFile
	 * @param clazz
	 * @return
	 */
	private List<Friend> parseFriends(File jsonFile, Class<? extends AFriends> clazz) {
		// load configuration JSON file ///////////////////
		try {
			// preparation to parse JSON ::::::::::::::::::
			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule().addDeserializer(String.class, new FBStringDeserializer());
			mapper.registerModule(module);
			
			// parse JSON and mapping contents :::::::::::::
			AFriends     friends = mapper.readValue(jsonFile,clazz);
			List<Friend> list    = friends.getFriends();
			Collections.sort(list);
			
			return list;
		}
		// exception for JSON parsing /////////////////////
		catch(JsonMappingException|JsonParseException exp) { 
			exp.printStackTrace();
		}
		// IO exception ///////////////////////////////////
		catch(IOException exp) {
			exp.printStackTrace();
		}
		return null;
	}
	
}
