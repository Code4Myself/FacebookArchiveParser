package jp.utokyo.shibalab.facebookarchiveparser.like;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PageLike extends ALike {
	
	
	public static String getCsvHeader(String delim) { 
		 String[] columns = new String[] {
			"title",
			"timestamp"
		 };
		 
		 return StringUtils.join(columns, delim);
	 }
	
	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization 
	 * @param name name
	 * @param timestamp timestamp in unixtime
	 */
	@JsonCreator
	protected PageLike( 
		@JsonProperty("name")      String name, 
		@JsonProperty("timestamp") Long  timestamp
	)
	{
		super(name, timestamp);
	}
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get Name 
	 * @return name
	 */
	public String getName() {
		return getTitle();
	}
	
	
	/**
	 * to Csv String 
	 * @return csv String
	 */
	public String toCsvString() {
		return toCsvString("\t");
	}
	
	/**
	 * to Csv String with the indicated delimiter 
	 * @param delim delimiter 
	 * @return csv String
	 */
	public String toCsvString(String delim) {
		Date ts = getTimestamp();
	
		String[] tokens = new String[] {
			getTitle(),
			ts != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts) : ""
		};
		
		return StringUtils.join(tokens, delim);
	}
}
