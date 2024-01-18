package com.microblogging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microblogging.dao.Tweets;
import com.microblogging.dao.TweetsDao;

@Service("tweetsService")
public class TweetsService {
	private TweetsDao tweetsDao;
	
	@Autowired
	public void setTweetsDao(TweetsDao tweetsDao) {
		this.tweetsDao = tweetsDao;
	}
	
	public List<Tweets> getCurrent() {
		return tweetsDao.getTweets();
	}
	
	public List<Tweets> getTweetSelected(String inputDate) {
		return tweetsDao.getTweetsSelected(inputDate);
	}
}
