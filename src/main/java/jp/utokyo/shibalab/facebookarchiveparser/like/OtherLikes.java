package jp.utokyo.shibalab.facebookarchiveparser.like;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class OtherLikes {

	@JsonProperty("other_likes")
	private List<OtherLike> _otherLikes;
	
	public List<OtherLike> getOtherLikes() {
		return _otherLikes;
	}
}
