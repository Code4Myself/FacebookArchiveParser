package jp.utokyo.shibalab.facebookarchiveparser.friends;

import java.io.File;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		File friendDir = new File(args[0]);
		
		File friendFile        = new File(friendDir,FriendParser.FRIENDS_JSON);
		File removedFriendFile = new File(friendDir,FriendParser.REMOVED_FRIENDS_JSON);
		File rejectedFile      = new File(friendDir,FriendParser.REJECTED_FRIEND_REQUESTS_JSON);
		File receivedFile      = new File(friendDir,FriendParser.RECEIVED_FRIEND_REQUESTS_JSON);
		File sentFile          = new File(friendDir,FriendParser.SENT_FRIEND_REQUESTS_JSON);
		
		FriendParser parser = new FriendParser();
		
		if( friendFile.exists() ) {
			List<Friend> friends = parser.parseFriends(friendFile);
			for(Friend friend:friends) {
				System.out.println(friend);
			}
			System.out.println();
		}

		if( removedFriendFile.exists() ) {
			List<Friend> rmfriends = parser.parseRemovedFriends(removedFriendFile);
			for(Friend friend:rmfriends) {
				System.out.println(friend);
			}
			System.out.println();	
		}

		if( rejectedFile.exists() ) {
			List<Friend> rjfriends = parser.parseRejectedRequests(rejectedFile);
			for(Friend friend:rjfriends) {
				System.out.println(friend);
			}
			System.out.println();
		}
		
		if( receivedFile.exists() ) {
			List<Friend> recfriends = parser.parseReceivedRequests(receivedFile);
			for(Friend friend:recfriends) {
				System.out.println(friend);
			}
			System.out.println();
		}
		
		if( sentFile.exists() ) {
			List<Friend> stfriends = parser.parseSentRequests(sentFile);
			for(Friend friend:stfriends) {
				System.out.println(friend);
			}
			System.out.println();
		}
	}

}
