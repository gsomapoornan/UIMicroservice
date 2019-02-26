package com.visualpath.UIMicroservice.config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.visualpath.UIMicroservice.Model.Authuser;
import com.visualpath.UIMicroservice.Repository.AuthuserRepo;
import com.visualpath.UIMicroservice.Services.UIServiceImpl;

import sun.util.logging.resources.logging;

@Service
public class AuthUserDetailsService implements UserDetailsService{

	private static final Logger LOG = Logger.getLogger(UIServiceImpl.class.getName());
	@Autowired
	AuthuserRepo authuserRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Authuser authuser=authuserRepo.findByName(username);
		LOG.info("Authuser"+authuser);
		return new AuthuserDetails(authuser);
	}
	

}
