package com.ixaris.twitterlite.webapp.ws.helper;

import java.util.Set;

public class MessageModel {
	
	private String username;
	private String content;
	private Long timestamp;
	private Set<String> mentions;
	private Set<String> hashtags;
	
	public MessageModel(String username, String content, Long timestamp, Set<String> mentions, Set<String> hashtags) {
		super();
		this.username = username;
		this.content = content;
		this.timestamp = timestamp;
		this.mentions = mentions;
		this.hashtags = hashtags;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Set<String> getMentions() {
		return mentions;
	}
	public void setMentions(Set<String> mentions) {
		this.mentions = mentions;
	}
	public Set<String> getHashtags() {
		return hashtags;
	}
	public void setHashtags(Set<String> hashtags) {
		this.hashtags = hashtags;
	}
	
}
