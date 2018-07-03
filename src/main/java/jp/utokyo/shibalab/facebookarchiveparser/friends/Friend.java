package jp.utokyo.shibalab.facebookarchiveparser.friends;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for Friend 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Friend implements Comparable<Friend> {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** name */
	private String _name;
	
	/** time stamp */
	private Date   _timestamp;
	
	/** contact info (email) */
	private String _contactInfo;
	

	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param name name
	 * @param timestamp time stamp
	 * @param contactInfo contact info
	 */
	@JsonCreator
	protected Friend (	@JsonProperty("name")			String name,
						@JsonProperty("timestamp")		Long   timestamp,
						@JsonProperty("contact_info")	String contactInfo )
	{	
		_name        = name;
		_timestamp   = timestamp != null ? new Date(timestamp*1000L) : null;
		_contactInfo = contactInfo;
	}
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get name
	 * @return name
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * get time-stamp when being friend
	 * @return time-stamp
	 */
	public Date getTimestamp() {
		return _timestamp;
	}
	
	/**
	 * get contact info
	 * @return contact info
	 */
	public String getContactInfo() {
		return _contactInfo;
	}
	
	public String toCsvString() {
		return toCsvString("\t");
	}
	
	public String toCsvString(String delim) {
		String[] tokens = new String[]{
			getName(),
			getTimestamp().toString(),
			getContactInfo()
		};
		return StringUtils.join(tokens,delim);
	}
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() {
		return toCsvString();
	}
	
	/* @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Friend friend) {
		return getTimestamp().compareTo(friend.getTimestamp());
	}
}
