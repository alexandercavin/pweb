package com.microblogging.vclass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microblogging.dao.Tweets;
import com.microblogging.dao.User;
import com.microblogging.service.CreateTweetService;
import com.microblogging.service.TweetsService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

@RestController

public class vclasssJSON {
		private TweetsService tweetsService;
	private CreateTweetService createTweetService;
	
	@Autowired
	public void setTweetsService(TweetsService tweetsService) {
		this.tweetsService = tweetsService;
	}

	
	@RequestMapping(value = "/getAllUserAPI", method = RequestMethod.GET)
	public @ResponseBody List<Tweets> getAllUser() {
		List<Tweets> tweets = tweetsService.getCurrent();
		return tweets;
	}
	
}

