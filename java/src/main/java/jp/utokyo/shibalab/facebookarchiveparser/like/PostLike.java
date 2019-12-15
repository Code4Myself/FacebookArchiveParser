package jp.utokyo.shibalab.facebookarchiveparser.like;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Reaction;

/**
 *
 */@JsonIgnoreProperties(ignoreUnknown=true)
public class PostLike extends ALike {
	 
	 public static String getCsvHeader(String delim) { 
		 String[] columns = new String[] {
			"title",
			"timestamp",
			"data(actor|reaction)"
		 };
		 
		 return StringUtils.join(columns, delim);
	 }

	 
	 private Data[] _data;
	 
	 @JsonCreator
	 protected PostLike(
		@JsonProperty("timestamp") Long   timestamp, 
		@JsonProperty("title")     String title, 
		@JsonProperty("data")      Data[] data
	 )
	 {
		 super(title, timestamp);
		 
		 _data = data;
		 
	 }
	 
	 
	 public Data[] getData() {
		 return _data;
	 }
	 
	 public String toCsvString() {
		 return toCsvString("\t");
	 }
	 
	 public String toCsvString(String delim) {
		 Date ts = getTimestamp();
		 
		 String       subDelim    = ";";
		 String       subsubDelim = "|";
		 StringBuffer buf      = new StringBuffer();
		 for(Data d:getData()) {
			 buf.append(subDelim).append(d.toCsvString(subsubDelim));
		 }
		 
		 String[] tokens = new String[] { 
			getTitle() != null ? getTitle() : "", 
			ts != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts) : "", 
			buf.length() > 0 ? buf.substring(1) : ""
		 };
		 
		 return StringUtils.join(tokens,delim);
	 }
	 
	 
	 
	 

	/* ==============================================================
	 * static inner class
	 * ============================================================== */
	 @SuppressWarnings("unused")
	 private static class Data {
		 
		 @JsonProperty("reaction")
		 private Reaction _reaction;
		 
		 		 
		 public Reaction getReaction() {
			 return _reaction;
		 }
		 
		 public String toCsvString() {
			 return toCsvString("\t");
		 }
		 
		 public String toCsvString(String delim) { 
			 return String.format("%s%s%s", _reaction.getActor(), delim, _reaction.getReaction());
		 }
		 
		 @Override
		 public String toString() {
			 return _reaction.toString();
		 }
	 }
}
