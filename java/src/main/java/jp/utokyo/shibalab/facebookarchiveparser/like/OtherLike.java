package jp.utokyo.shibalab.facebookarchiveparser.like;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class OtherLike extends ALike {


	public static String getCsvHeader(String delim) { 
		 String[] columns = new String[] {
			"title",
			"timestamp", 
			"attachements"
		 };
		 
		 return StringUtils.join(columns, delim);
	 }
	
	
	
	private Attachment[] _attachments;
	
	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization 
	 * @param title title
	 * @param timestamp timestamp in unixtime
	 */
	@JsonCreator
	protected OtherLike( 
		@JsonProperty("title")        String title, 
		@JsonProperty("timestamp")    Long  timestamp, 
		@JsonProperty("attachments") Attachment[] attachments
	)
	{
		super(title, timestamp);
		System.out.println(attachments);
		_attachments = attachments;
	}
	
	
	public Attachment[] getAttachments() {
		return _attachments;
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
			ts != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts) : "", 
			getAttachments()[0].toCsvString("|")
		};
		
		return StringUtils.join(tokens, delim);
	}
	
	
	
	private static class Attachment {
		@JsonProperty("data")
		private Data[] _data;
		
		public Data[] getData() {
			return _data;
		}
		
		public String toCsvString(String delim) { 
			Data[] data = getData();
			StringBuffer buf = new StringBuffer();
			for(Data d:data) { 
				buf.append(delim).append(d.toString());
			}
			
			return buf.substring(delim.length());
		}
	}
	
	
//	private static class DataArray {
//		
//		private Data[] _data;
//	}
	
	private static class Data {
		@JsonProperty("external_context")
		private ExternalContext _externalContext;
		
		public ExternalContext getExternalContent() {
			return _externalContext;
		}
		
		public String toString() {
			return getExternalContent().toString();
		} 
	}
	
	private static class ExternalContext {
		private String _url;
		
		@JsonCreator
		private ExternalContext(@JsonProperty("url") String url) {
			try {
				_url = new URLCodec().decode(url);
			}
			catch(DecoderException exp) {
				exp.printStackTrace();
			}
		}
		
		public String getUrl() {
			return _url;
		}
		
		public String toString() {
			return getUrl();
		}
	}
}
