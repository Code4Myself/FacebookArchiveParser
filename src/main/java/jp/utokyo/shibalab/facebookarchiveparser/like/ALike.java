package jp.utokyo.shibalab.facebookarchiveparser.like;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class ALike implements Comparable<ALike> {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** title */
	private String  _title;
	
	/** time-stamp */
	private Date    _timestamp;
	

	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param title title 
	 * @param timestamp timestamp in unixtime
	 */
	protected ALike(String title, Long timestamp) {
		_title     = title;
		_timestamp = timestamp != null ? new Date(timestamp*1000L) : null;
	}
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get title
	 * @return title
	 */
	public String getTitle() {
		return _title;
	}
	
	/**
	 * get time-stamp
	 * @return time-stamps
	 */
	public Date getTimestamp() {
		return _timestamp;
	}
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() { 
		if( _timestamp != null ) { 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return String.format("%s, %s", _title, sdf.format(_timestamp));
		}
		else {
			return String.format("%s,",_title);			
		}
	}
	
	/* @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(ALike msg) {
		return getTimestamp().compareTo(msg.getTimestamp());
	}
}
