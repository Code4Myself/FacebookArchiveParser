package jp.utokyo.shibalab.facebookarchiveparser.messages;

import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import jp.utokyo.shibalab.facebookarchiveparser.FBStringDeserializer;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Sticker;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.UriEntry;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Video;

/**
 * Class for Facebook message thread parser
 */
public class ThreadParser {
	/* ==============================================================
	 * static fields
	 * ============================================================== */
	/** stickers_userd: exception for message directory */
	public static final String STICKER_USED_DIR = "stickers_used";
	
	/** message.json: message log file in JSON format in individual thread directory */
	public static final String MESSAGE_JSON     = "message_1.json";
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * parse all message threads 
	 * @param messageDir root directory of messages
	 * @return parsed threads
	 */
	public List<Thread> parseAllMessages(java.io.File messageDir) {
		System.out.println("start parsing messages ... ");
		// list directories of individual thread ///////////
		java.io.File[] threadDirs = messageDir.listFiles(new FileFilter() {
			public boolean accept(java.io.File f) {
				return f.isDirectory() && !f.getName().startsWith(".");
//				return f.isDirectory() && !f.getName().startsWith(".") && !f.getName().equalsIgnoreCase(STICKER_USED_DIR);
			}
		});
		
		// parse all message threads ///////////////////////
		List<Thread> threads = new ArrayList<>();
		int N = 0;
		for(java.io.File threadDir:threadDirs) {
			// specify the message.json file :::::::::::::::
			java.io.File messageFile = new java.io.File(threadDir,MESSAGE_JSON);
//			System.out.println("\t\t" + messageFile.getName());
			// parse message and add list ::::::::::::::::::
			Thread thread = parseMessage(messageFile);
			if( thread != null ) {
				threads.add(thread);
				N += thread.getMessages().size();
			}
//			else {
//				System.err.println(messageFile.getAbsolutePath());
//			}
		}
		System.out.printf("done(%d threads, %d messages)\n\n", threads.size(), N);
		return threads;
	}
	
	/**
	 * parse message thread 
	 * @param messageFile message file in JSON format
	 * @return thread instance
	 */
	public Thread parseMessage(java.io.File messageFile) {
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
				_updateUris(msg.getPhotos(),homeDir);
				_updateUris(msg.getFiles(), homeDir);
				_updateUris(msg.getAudios(),homeDir);

				List<Video> videos = msg.getVideos();
				if( videos != null ) {
					for(Video video:videos) {
						video.setUri(homeDir + video.getUri());
						UriEntry thumbnail = video.getThumbnail();
						if( thumbnail != null ) {
							thumbnail.setUri( homeDir + thumbnail.getUri() );
						}
					}
				}
				Sticker sticker = msg.getSticker();
				if( sticker != null ) {
					sticker.setUri(homeDir + sticker.getUri());
				}
			}
			// sort message in time order ::::::::::::::::::
			Collections.sort(thread.getMessages());
			
			return thread;
		}
		// exception for JSON parsing /////////////////////
		catch(JsonMappingException|JsonParseException exp) {
//			System.err.println(":::" + messageFile.getAbsolutePath());
			exp.printStackTrace();
		}
		// IO exception ///////////////////////////////////
		catch(IOException exp) {
			exp.printStackTrace();
		}
		return null;
	}
	
	/**
	 * update URI to append home directory
	 * @param uris UriEntry items
	 * @param homeDir home dir
	 */
	private void _updateUris(List<? extends UriEntry> uris,String homeDir) {
		if( uris != null ) {
			for(UriEntry uri:uris) {
				uri.setUri(homeDir + uri.getUri());
			}
		}	
	}
}
