package com.goparty.webservice.impl;

import org.springframework.stereotype.Service;

import com.goparty.model.User;
import com.goparty.webservice.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public User getUser(String id) {
		User ret = new User();
		ret.setId(id);
		return ret;
	}

	@Override
	public User addUser(User user) {
		user.setId(java.util.UUID.randomUUID().toString());
		return user;
	}

	@Override
	public User updateUser(User user) {
		return user;
	}

	@Override
	public boolean deleteUser(String id) {
		return true;
	}

}
