package jp.utokyo.shibalab.facebookarchiveparser.messages;

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
 * Class for Facebook message thread parser
 */
public class ThreadParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File inputFile = new File(args[0]);
		
		Thread thread = new ThreadParser().parse(inputFile);
		
		System.out.println(thread.getTitle());
		System.out.println(thread.getStatus());
		System.out.println(thread.getThreadPath());
		System.out.println(thread.getThreadType());
		System.out.println(thread.getParticipants());
		for(Message msg:thread.getMessages()) {
			System.out.println("\t" + msg.getSenderName());
			System.out.println("\t" + msg.getTimestamp());
			System.out.println("\t" + msg.getContent());
//			System.out.println("\t" + msg.getPhotos());
//			System.out.println("\t" + msg.getFiles());
//			System.out.println("\t" + msg.getStickerUri());
//			System.out.println("\t" + msg.getReactions());
		}
		
	}
	
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	
	/**
	 * parse message thread 
	 * @param messageFile message file in JSON format
	 * @return thread instance
	 */
	public Thread parse(File messageFile) {
		// load configuration JSON file ///////////////////
		try {
			// preparation to parse JSON ::::::::::::::::::
			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule().addDeserializer(String.class, new FBStringDeserializer());
			mapper.registerModule(module);
			
			// parse JSON and mapping contents :::::::::::::
			Thread thread = mapper.readValue(messageFile,Thread.class);
			
			// update file path to absolute ::::::::::::::::
			String homeDir = messageFile.getParent() + "/";
			for(Message msg:thread.getMessages()) {
				List<String> photos = msg.getPhotos();
				if( photos != null ) {
					for(int i=photos.size()-1;i>=0;i--) {
						photos.set(i,homeDir+photos.get(i));
					}
				}
				List<String> files = msg.getFiles();
				if( files != null ) {
					for(int i=files.size()-1;i>=0;i--) {
						files.set(i,homeDir+files.get(i));
					}
				}
				String stickerUri = msg.getStickerUri();
				if( stickerUri != null ) {
					msg.setStickerUri(homeDir + stickerUri);
				}
			}
			// sort message in time order ::::::::::::::::::
			Collections.sort(thread.getMessages());
			
			return thread;
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
