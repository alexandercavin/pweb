package com.microblogging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.microblogging.dao.User;
import com.microblogging.dao.UserDao;


@Service("usersService")
public class UsersService {
	
	private UserDao usersDao;
	
	@Autowired
	public void setOffersDao(UserDao usersDao) {
		this.usersDao = usersDao;
	}

	
	public void create(User user) {
		usersDao.create(user);
	}

}
