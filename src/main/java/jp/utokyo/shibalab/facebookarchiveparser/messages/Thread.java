package jp.utokyo.shibalab.facebookarchiveparser.messages;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for individual thread data
 */
public class Thread {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** all messages */
	private List<Message> _messages;
	/** thread title */
	private String _title;
	/** participating status          */
	private Boolean _participate;
	/** thread status                 */
	private String  _status;
	/** thread type(regular only?)    */
	private String  _threadType;
	/** thread path(directory name?)  */
	private String  _threadPath;
	/** list of participants          */
	private List<String> _participants;
	

	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param messages message list
	 * @param title thread title
	 * @param participate participating status
	 * @param status thread status (pending?)
	 * @param threadType thread type(regular?)
	 * @param threadPath thread path(directory name)
	 * @param participants list of participants
	 */
	@JsonCreator
	protected Thread(	@JsonProperty("messages")				List<Message> messages,
						@JsonProperty("title")					String        title,
						@JsonProperty("is_still_participant")	Boolean       participate,
						@JsonProperty("status")					String        status,
						@JsonProperty("thread_type")			String        threadType,
						@JsonProperty("thread_path")			String        threadPath,
						@JsonProperty("participants")			List<String>  participants
					)
	{
		_messages    = messages;
		_title       = title; 
		_participate = participate != null ? participate : false;
		_status      = status;
		_threadType  = threadType;
		_threadPath  = threadPath;
		if( participants!= null ) {
			_participants = new ArrayList<>();
			for(String person:participants) {
				_participants.add( person );
			}
		}
	}

	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get message list 
	 * @return message list
	 */
	public List<Message> getMessages() {
		return _messages;
	}
					
	/**
	 * get thread title
	 * @return title
	 */
	public String getTitle() {
		return _title;
	}
	
	/**
	 * check if still participant
	 * @return result
	 */
	public boolean isStillParticipant() {
		return _participate;
	}

	/**
	 * get status
	 * @return status
	 */
	public String getStatus() {
		return _status;
	}
	
	/**
	 * get thread type
	 * @return thread type
	 */
	public String getThreadType() {
		return _threadType;
	}
	
	/**
	 * get thread path
	 * @return thread path
	 */
	public String getThreadPath() {
		return _threadPath;
	}
	
	/**
	 * get participants
	 * @return participants
	 */
	public List<String> getParticipants() {
		return _participants;
	}
}
