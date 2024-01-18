package com.microblogging.dao;

public class Tweets {

	private String username;
	private String full_name;
	private String content;
	private String timestamp;
		
	
	
	public Tweets() {
	}

	public Tweets(String username, String full_name, String content, String timestamp) {
		this.username = username;
		this.full_name = full_name;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
