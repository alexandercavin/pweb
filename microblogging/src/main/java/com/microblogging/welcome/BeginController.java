package com.microblogging.welcome;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microblogging.config.JwtTokenChecker;
import com.microblogging.dao.CreateTweets;
import com.microblogging.dao.Tweets;
import com.microblogging.service.CreateTweetService;
import com.microblogging.service.TweetsService;
import com.microblogging.service.UsersService;

@Controller
public class BeginController {
	
	private TweetsService tweetsService;
	private CreateTweetService createTweetService;
	
	@Autowired
	public void setTweetsService(TweetsService tweetsService) {
		this.tweetsService = tweetsService;
	}
	
	@Autowired
	public void createTweetService(CreateTweetService createTweetService) {
		this.createTweetService = createTweetService;
	}
	
	JwtTokenChecker jwtTokenChecker = new JwtTokenChecker();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showBeginPage(Model model) {
		//model.put("name", getLoggedInUserName());
		//System.out.println(getLoggedInUserName());
		List<Tweets> tweets = tweetsService.getCurrent();
		model.addAttribute("tweets", tweets);
		
		return "index";
	}
	
	@CrossOrigin(
		    allowCredentials = "true",
		    origins = "*", 
		    allowedHeaders = "*", 
		    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
		)
	@RequestMapping(value = "/getTweetJWT", method = RequestMethod.POST)
	public @ResponseBody List<Tweets> getAllTweetJWT(@RequestHeader("Authorization") String jwtToken, @RequestParam String inputDate, Model model) {

		jwtTokenChecker.doTokenChecker(jwtToken);
			List<Tweets> tweets = tweetsService.getTweetSelected(inputDate);
			model.addAttribute("tweets", tweets);
		return tweets;
	}
	
	
	
	@RequestMapping(value = "/createtweet", method = RequestMethod.POST)
	public String createNewTweet(CreateTweets createTweets) {
		
		createTweets.setUsername(getLoggedInUserName());
		
		createTweetService.createTweet(createTweets);
		
		return "redirect:/";
	}

	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

}