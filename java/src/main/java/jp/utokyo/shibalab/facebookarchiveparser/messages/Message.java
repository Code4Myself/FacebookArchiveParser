package jp.utokyo.shibalab.facebookarchiveparser.messages;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Audio;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.File;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Gif;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Photo;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Plan;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Reaction;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Share;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Sticker;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.User;
import jp.utokyo.shibalab.facebookarchiveparser.messages.option.Video;

/**
 * class for individual message 
 */
public class Message implements Comparable<Message> {
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/** sender name */
	private String         _senderName;
	
	/** timestamp   */
	private Date           _timestamp;

	/** message body */
	private String	       _content;
	
	/** message type */
	private String         _type;
	
	/** photo list   */
	private List<Photo>    _photos;
	
	/** file list    */
	private List<File>     _files;
	
	/** sticker item */
	private Sticker        _sticker;
	
	/** reaction list */
	private List<Reaction> _reactions;
	
	/** video list    */
	private List<Video>    _videos;
	
	/** audio list    */
	private List<Audio>    _audios;
	
	/** Gif list */
	private List<Gif>      _gifs;
	
	/** Users */
	private List<User>     _users;
	
	/** shared item */
	private Share          _share;
	
	/** call duration (unit is unknown) */
	private Integer        _callDuration;
	
	/** missed flag */
	private Boolean        _missed;
	
	/** plan */
	private Plan           _plan;
	

	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param senderName sender name
	 * @param timestamp timestamp in unixtime
	 * @param content message content
	 * @param type message type(generic/share/call)
	 * @param photos photo URIs
	 * @param files file URIs
	 * @param sticker sticker URI
	 * @param reactions reactions
	 * @param videos video contents
	 * @param audio audio contents
	 * @param gifs  GIF contents
	 * @param users users
	 * @param share share item
	 * @param callDuration callDuration
	 * @param missed missed flag
	 * @param plan plan 
	 */
	@JsonCreator
	protected Message(	@JsonProperty("sender_name")	String         senderName,
						@JsonProperty("timestamp_ms")	Long           timestamp,
						@JsonProperty("content")		String         content,
						@JsonProperty("type")			String         type,
						@JsonProperty("photos")			List<Photo>    photos,
						@JsonProperty("files")			List<File>     files,
						@JsonProperty("sticker")		Sticker        sticker,
						@JsonProperty("reactions")      List<Reaction> reactions,
						@JsonProperty("videos")			List<Video>    videos,
						@JsonProperty("audio_files")	List<Audio>    audios,
						@JsonProperty("gifs")           List<Gif>      gifs,
						@JsonProperty("users")          List<User>     users,
						@JsonProperty("share")          Share          share,
						@JsonProperty("call_duration")	Integer        callDuration,
						@JsonProperty("missed")         Boolean        missed,
						@JsonProperty("plan")           Plan           plan
						)
	{
		// common contents /////////////////////////////////
		_senderName = senderName; 
		_timestamp  = timestamp != null ? new Date(timestamp) : null;
		_content    = content;
		_type       = type;
		
		// extract URI strings /////////////////////////////
		_photos  = photos;
		_files   = files;
		_videos  = videos;
		_audios  = audios;
		_gifs    = gifs;
		_users   = users;
		_sticker = sticker;
		
		_reactions = reactions;
		_share     = share;
		_missed    = missed;
		
		_callDuration = callDuration != null ? callDuration : 0;
		_plan         = plan;
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
	public List<Photo> getPhotos() {
		return _photos;
	}
	
	/**
	 * get file list attached to this message
	 * @return file list
	 */
	public List<File> getFiles() {
		return _files;
	}
	
	/**
	 * get video list 
	 * @return vidio list
	 */
	public List<Video> getVideos() {
		return _videos;
	}
	
	/**
	 * get audio list
	 * @return audio list
	 */
	public List<Audio> getAudios() {
		return _audios;
	}
	
	/**
	 * get GIF list
	 * @return gif list
	 */
	public List<Gif> getGifs() {
		return _gifs;
	}
	
	/**
	 * get user list
	 * @return user list
	 */
	public List<User> getUsers() {
		return _users;
	}
	
	/**
	 * get sticker URI
	 * @return sticker URI
	 */
	public Sticker getSticker() {
		return _sticker;
	}
	
	/**
	 * get reactions
	 * @return reactions
	 */
	public List<Reaction> getReactions() {
		return _reactions;
	}
	
	/**
	 * get shared link
	 * @return shared link
	 */
	public Share getShare() {
		return _share;
	}
	
	/**
	 * get call duration(second?)
	 * @return call duration
	 */
	public Integer getCallDuration() {
		return _callDuration;
	}
	
	/**
	 * check if missed or not
	 * @return result
	 */
	public Boolean isMissed() {
		return _missed;
	}
	
	/**
	 * get plan 
	 * @return  plan 
	 */
	public Plan getPlan() {
		return _plan;
	}
	
	/* @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Message msg) {
		return getTimestamp().compareTo(msg.getTimestamp());
	}
}
