package jp.utokyo.shibalab.facebookarchiveparser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

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
        // output text /////////////////////////////////////
		StringBuffer buf = new StringBuffer();

		// prepare regular expression ////////////////////// 
        Pattern pattern = Pattern.compile(".u(.{4}).u(.{4}).u(.{4})");
        Matcher matcher = pattern.matcher(text);
        int     endIdx  = 0;
        while (matcher.find()) {
        	// extract text except for decoding ::::::::::::
        	int    startIdx = matcher.start();
        	String expt  = (endIdx == matcher.start()) ? "" : text.substring(endIdx,startIdx);
        	// extract text for decoding :::::::::::::::::::
            String group = matcher.group();
            String a     = group.replaceAll("u00","").replace("\\","");
            int    c     = Integer.decode("0x"+a).intValue();
            byte[] bytes = ByteBuffer.allocate(4).putInt(c).array();
            // compose decoded text ::::::::::::::::::::::::
            buf.append(expt).append(new String(bytes));
            endIdx = matcher.end();
        }
        // append if text remains //////////////////////////
        if( endIdx != text.length() ) {
        	buf.append(text.substring(endIdx));
        }
        
        return buf.toString();
	}
}
