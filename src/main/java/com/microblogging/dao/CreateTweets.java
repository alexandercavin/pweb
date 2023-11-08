package com.microblogging.dao;

public class CreateTweets {
	private String username;
	private String content;

	public CreateTweets() {

	}
	public CreateTweets(String username, String content) {
		super();
		this.username = username;
		this.content = content;
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
	
	
}
