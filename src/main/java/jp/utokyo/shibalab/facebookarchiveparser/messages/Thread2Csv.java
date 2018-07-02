package jp.utokyo.shibalab.facebookarchiveparser.messages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * class for data conversion from message thread into CSV
 */
public class Thread2Csv {
	/* ==============================================================
	 * static methods
	 * ============================================================== */
	/**
	 * entry point
	 * @param args 0: message root folder path, 1: output file path
	 */
	public static void main(String[] args) {
		File         messageRootDir = new File(args[0]);
		File         outputFile     = new File(args[1]);
		List<Thread> threads        = new ThreadParser().parseAllMessages(messageRootDir);
		
		try (BufferedWriter bw=Files.newBufferedWriter(outputFile.toPath())) {
			Thread2Csv conv = new Thread2Csv();
			for(Thread thread:threads) {
				System.out.println(thread.getTitle());
				
				List<String> lines = conv.toCsvString(thread);
				for(String line:lines) {
					bw.write(line); bw.newLine();
				}
			}	
		}
		catch(IOException exp) {
			exp.printStackTrace();
		}
	}
	

	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** column delimiter */
	private String _delim;
	
	/** array item delimiter */
	private String _arrayDelim;

	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 */
	public Thread2Csv() {
		this("\t",";");
	}
	
	/**
	 * initialization 
	 * @param delim column delimiter
	 * @param arrayDelim array item delimiter
	 */
	public Thread2Csv(String delim,String arrayDelim) {
		_delim      = delim;
		_arrayDelim = arrayDelim;
	}
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get CSV string from message 
	 * @param msg message 
	 * @return CSV string
	 */
	public String toCsvString(Message msg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String[] tokens = new String[]{
			msg.getSenderName(),
			sdf.format(msg.getTimestamp()),
//			msg.getContent(),	// sometimes include binary data( failed to convert into text)
			msg.getType(),
			msg.getPhotos()    != null ? StringUtils.join(msg.getPhotos(),_arrayDelim)    : "",
			msg.getFiles()     != null ? StringUtils.join(msg.getFiles(), _arrayDelim)    : "",
			msg.getSticker()   != null ? msg.getSticker().getUri()                        : "",
			msg.getReactions() != null ? StringUtils.join(msg.getReactions(),_arrayDelim) : "",
			msg.getVideos()    != null ? StringUtils.join(msg.getVideos(),_arrayDelim)    : "",
			msg.getAudios()    != null ? StringUtils.join(msg.getAudios(),_arrayDelim)    : "",
			msg.getShare()     != null ? msg.getShare().getLink()                         : "",
			String.valueOf(msg.getCallDuration())
		};

		return StringUtils.join(tokens,_delim);
	}
	
	/**
	 * get CSV lines from thread & messages
	 * @param thread message thread 
	 * @return CSV lines
	 */
	public List<String> toCsvString(Thread thread) {
		String[] tokens = new String[] {
			thread.getTitle(),
			thread.getThreadPath(),
			String.valueOf(thread.isStillParticipant()),
			thread.getStatus(),
			thread.getThreadType(),
			StringUtils.join(thread.getParticipants(),_arrayDelim)
		};
		String prefix = StringUtils.join(tokens,_delim);
		
		List<String> lines = new ArrayList<>();
		for(Message msg:thread.getMessages()) {
			String str = prefix + _delim + toCsvString(msg);
			lines.add(str);
		}
		
		return lines;
	}
}