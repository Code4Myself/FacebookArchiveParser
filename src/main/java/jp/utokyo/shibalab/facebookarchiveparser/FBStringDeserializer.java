package jp.utokyo.shibalab.facebookarchiveparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Class for custom deserializer of unescape string<br/>
 * 
 * reference: https://stackoverflow.com/questions/46573452/jackson-how-to-prevent-objectmapper-from-converting-escaped-unicode
 */
public class FBStringDeserializer extends JsonDeserializer<String> {
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	
	/* @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext) */
	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return decode( StringEscapeUtils.escapeJava( p.getValueAsString() ) );
	}
	
	/**
	 * conduct decoding from Facebook encoding(?)
	 * @param text text in facebook encoding
	 * @return decoded string
	 */
	public String decode(String text) {
		Pattern p = Pattern.compile("((\\\\u[0-F]{4})+)");
    	Matcher m = p.matcher(text);
    	
    	while (m.find()) {
    		String     org   = m.group(1); 
    		Pattern    p2    = Pattern.compile("\\\\u([0-F]{4})");
    		Matcher    m2    = p2.matcher(org);
    		List<Byte> bytes = new ArrayList<>();
    		while (m2.find()) {
    			String group = m2.group(1);
    			bytes.add( Integer.decode("0x"+group).byteValue() );
    		}
    		byte[] exb = toArray(bytes);
    		String ns  = new String( exb );
    		text = StringUtils.replaceOnce(text,org,ns);
    	}
    	return text;
	}
	
	/**
	 * convert object list to primitive array
	 * @param bytes Byte object list
	 * @return byte primitive array
	 */
	private byte[] toArray(List<Byte> bytes) {
		int    len   = bytes.size();
		byte[] array = new byte[len];
		for(int i=0;i<len;i++) {
			array[i] = bytes.get(i);
		}
		return array;
	}
}
