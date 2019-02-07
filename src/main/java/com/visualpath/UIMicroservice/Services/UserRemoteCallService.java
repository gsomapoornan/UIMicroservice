package com.visualpath.UIMicroservice.Services;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.visualpath.UIMicroservice.Model.User;

@FeignClient(name="userservice")
public interface UserRemoteCallService {
	@RequestMapping(method=RequestMethod.GET, value="/user/{Id}")
	public User getUserByID(@PathVariable("Id") Long userId);

}