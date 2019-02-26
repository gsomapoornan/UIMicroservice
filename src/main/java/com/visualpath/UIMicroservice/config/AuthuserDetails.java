package com.visualpath.UIMicroservice.config;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.visualpath.UIMicroservice.Model.Authuser;

public class AuthuserDetails extends Authuser implements UserDetails{

	public AuthuserDetails(Authuser authuser) {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("****"+getRoles());
		return getRoles().
		stream().
		map(role->new SimpleGrantedAuthority("ROLE_"+role.getRole()))
		.collect(Collectors.toList());
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getName();
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
