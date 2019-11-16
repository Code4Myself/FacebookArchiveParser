package jp.utokyo.shibalab.facebookarchiveparser.like;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class PageLikes {

	@JsonProperty("page_likes")
	private List<PageLike> _pageLikes;
	
	public List<PageLike> getPageLikes() {
		return _pageLikes;
	}
}
