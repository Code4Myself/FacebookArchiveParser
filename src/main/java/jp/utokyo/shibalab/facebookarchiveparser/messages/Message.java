package jp.utokyo.shibalab.facebookarchiveparser.messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for individual message 
 */
public class Message implements Comparable<Message> {

	private String _senderName;
	
	private Date _timestamp;

	private String _content;
	
	private String _type;
	
	private List<String> _photoUris;
	
	private List<String> _fileUris;
	
	private String _stickerUri;
	
	private List<Reaction> _reactions;
	

	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param senderName sender name
	 * @param timestamp timestamp in unixtime
	 * @param content message content
	 * @param type message type(generic only?)
	 * @param photos photo URIs
	 * @param files file URIs
	 * @param sticker sticker URI
	 * @param reactions reactions
	 */
	@JsonCreator
	protected Message(	@JsonProperty("sender_name")	String  senderName,
						@JsonProperty("timestamp")		Long    timestamp,
						@JsonProperty("content")		String  content,
						@JsonProperty("type")			String  type,
						@JsonProperty("photos")			List<UriEntry> photos,
						@JsonProperty("files")			List<UriEntry> files,
						@JsonProperty("sticker")		UriEntry sticker,
						@JsonProperty("reactions")      List<Reaction> reactions
						)
	{
		_senderName = senderName; 
		_timestamp  = timestamp != null ? new Date(timestamp*1000L) : null;
		_content    = content;
		_type       = type;
		
		// extract URI strings /////////////////////////////
		if(photos != null) {
			_photoUris = new ArrayList<>();
			for(UriEntry entry:photos) {
				_photoUris.add(entry.getUri());
			}
		}
		// extract URI strings /////////////////////////////
		if(files != null) {
			_fileUris = new ArrayList<>();
			for(UriEntry entry:files) {
				_fileUris.add(entry.getUri());
			}
		}
		
		_stickerUri = sticker != null ? sticker.getUri() : null;
		
		_reactions = reactions;
	}
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get sender name
	 * @return sender name
	 */
	public String getSenderName() {
		return _senderName;
	}
	
	/**
	 * get time stamp
	 * @return time stamp
	 */
	public Date getTimestamp() {
		return _timestamp;
	}
	
	/**
	 * get message content
	 * @return message content
	 */
	public String getContent() {
		return _content;
	}
	
	/**
	 * get type
	 * @return type
	 */ 
	public String getType() {
		return _type;
	}
	
	/**
	 * get photo list attached to this message
	 * @return photo list
	 */
	public List<String> getPhotos() {
		return _photoUris;
	}
	
	/**
	 * get file list attached to this message
	 * @return file list
	 */
	public List<String> getFiles() {
		return _fileUris;
	}
	
	/**
	 * get sticker URI
	 * @return sticker URI
	 */
	public String getStickerUri() {
		return _stickerUri;
	}
	
	/**
	 * set sticker URI
	 * @param uri sticker URI
	 */
	protected void setStickerUri(String uri) {
		_stickerUri = uri;
	}
	
	/**
	 * get reactions
	 * @return reactions
	 */
	public List<Reaction> getReactions() {
		return _reactions;
	}
	
	/* @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Message msg) {
		return getTimestamp().compareTo(msg.getTimestamp());
	}
}
