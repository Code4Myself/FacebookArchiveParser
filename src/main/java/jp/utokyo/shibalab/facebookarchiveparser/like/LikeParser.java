package jp.utokyo.shibalab.facebookarchiveparser.like;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import jp.utokyo.shibalab.facebookarchiveparser.FBStringDeserializer;

/**
 *
 */
public class LikeParser {
	/* ==============================================================
	 * static fields
	 * ============================================================== */
	/** */
	public static final String FILE_LIKE_POSTS_AND_COMMENTS = "posts_and_comments.json";
	
	/** */
	public static final String FILE_LIKE_PAGES              = "pages.json";
	
	public static final String FILE_LIKE_EXTERNAL_SITES     = "likes_on_external_sites.json";
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	

	public List<PageLike> parsePages(File likeJsonFile) {
		System.out.println("start parsing like pages ... ");
		
		// parse all Like pages ////////////////////////////
		List<PageLike> likePages = new ArrayList<>();
	
		// load configuration JSON file ///////////////////
		try {
			// preparation to parse JSON ::::::::::::::::::
			SimpleModule module = new SimpleModule().addDeserializer(String.class, new FBStringDeserializer());
			ObjectMapper mapper = new ObjectMapper().registerModule(module);
			
			// parse JSON and mapping contents :::::::::::::
			PageLikes result = mapper.readValue(likeJsonFile,PageLikes.class);
			likePages = result.getPageLikes();
			
			// sort message in time order ::::::::::::::::::
			Collections.sort(likePages);
		}
		// exception for JSON parsing /////////////////////
		catch(JsonMappingException|JsonParseException exp) {
			System.err.println(":::" + likeJsonFile.getAbsolutePath());
			exp.printStackTrace();
		}
		// IO exception ///////////////////////////////////
		catch(IOException exp) {
			exp.printStackTrace();
		}
		
		System.out.printf("done(%d likes)\n\n", likePages.size());
		
		return likePages;
	}		

	
	public List<PostLike> parsePosts(File likeJsonFile) {
		System.out.println("start parsing like posts ... ");
		
		// parse all Like Posts ////////////////////////////
		List<PostLike> reactions = new ArrayList<>();
	
		// load configuration JSON file ///////////////////
		try {
			// preparation to parse JSON ::::::::::::::::::
			SimpleModule module = new SimpleModule().addDeserializer(String.class, new FBStringDeserializer());
			ObjectMapper mapper = new ObjectMapper().registerModule(module);
			
			// parse JSON and mapping contents :::::::::::::
			Reactions result = mapper.readValue(likeJsonFile,Reactions.class);
			reactions = result.getReactions();
			
			// sort message in time order ::::::::::::::::::
			Collections.sort(reactions);
		}
		// exception for JSON parsing /////////////////////
		catch(JsonMappingException|JsonParseException exp) {
			System.err.println(":::" + likeJsonFile.getAbsolutePath());
			exp.printStackTrace();
		}
		// IO exception ///////////////////////////////////
		catch(IOException exp) {
			exp.printStackTrace();
		}
		
		System.out.printf("done(%d likes)\n\n", reactions.size());
		return reactions;
	}

	public List<OtherLike> parseOthers(File likeJsonFile) {
		System.out.println("start parsing like others ... ");
		
		// parse all Like pages ////////////////////////////
		List<OtherLike> likeOthers = new ArrayList<>();
	
		// load configuration JSON file ///////////////////
		try {
			// preparation to parse JSON ::::::::::::::::::
			SimpleModule module = new SimpleModule().addDeserializer(String.class, new FBStringDeserializer());
			ObjectMapper mapper = new ObjectMapper().registerModule(module);
			
			// parse JSON and mapping contents :::::::::::::
			OtherLikes result = mapper.readValue(likeJsonFile,OtherLikes.class);
			likeOthers = result.getOtherLikes();
			
			// sort message in time order ::::::::::::::::::
			Collections.sort(likeOthers);
		}
		// exception for JSON parsing /////////////////////
		catch(JsonMappingException|JsonParseException exp) {
			System.err.println(":::" + likeJsonFile.getAbsolutePath());
			exp.printStackTrace();
		}
		// IO exception ///////////////////////////////////
		catch(IOException exp) {
			exp.printStackTrace();
		}
		
		System.out.printf("done(%d likes)\n\n", likeOthers.size());
		
		return likeOthers;
	}		}
