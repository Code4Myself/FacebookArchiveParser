package jp.utokyo.shibalab.facebookarchiveparser.friends;

import java.util.List;

/**
 * class for friend list
 */
public abstract class AFriends {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** friend list */
	private List<Friend> _friends;
	
	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param friends friend list
	 */
	protected AFriends(List<Friend> friends) {
		_friends = friends;
	}
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get friend list
	 * @return friend list
	 */
	public List<Friend> getFriends() {
		return _friends;
	}
}
