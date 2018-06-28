package jp.utokyo.shibalab.facebookarchiveparser.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for reaction 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Reaction {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** reaction */
	private String  _reaction;
	
	/** actor    */
	private String _actor;
	

	/* ==============================================================
	 * constructor
	 * ============================================================== */
	/**
	 * initialization 
	 * @param reaction reaction
	 * @param actor actor
	 */
	@JsonCreator
	protected Reaction(	@JsonProperty("reaction")	String reaction,
						@JsonProperty("actor")		String actor
						)
	{
		_reaction = reaction;
		_actor    = actor;
	}
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get reaction
	 * @return reaction
	 */
	public String getReaction() {
		return _reaction;
	}
	
	/**
	 * get actor
	 * @return actor
	 */
	public String getActor() {
		return _actor;
	}
}
