package com.visualpath.UIMicroservice.Services;

import org.springframework.stereotype.Component;

import com.visualpath.UIMicroservice.Model.User;

@Component
class UserClientFallback implements UserRemoteCallService{

	@Override
	public User getUserByID(Long userId) {
		return new User(userId,"Unavailable","Unavailable");
	}

}