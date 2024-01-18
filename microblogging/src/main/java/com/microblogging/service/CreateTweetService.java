package com.microblogging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microblogging.dao.CreateTweets;
import com.microblogging.dao.TweetsDao;

	@Service("createTweetService")
	public class CreateTweetService {
		private TweetsDao tweetsDao;
		
		@Autowired
		public void setTweetsDao(TweetsDao tweetsDao) {
			this.tweetsDao = tweetsDao;
		}
		
		public void createTweet(CreateTweets createTweets) {
			tweetsDao.createTweets(createTweets);
		}

		
}
