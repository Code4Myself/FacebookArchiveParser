package jp.utokyo.shibalab.facebookarchiveparser.like;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class Reactions {

	@JsonProperty("reactions")
	private List<PostLike> _reactions;
	
	public List<PostLike> getReactions() {
		return _reactions;
	}
}
