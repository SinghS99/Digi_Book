package com.author.jwt.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.author.entitys.AuthorDetails;
import com.author.publish.AuthorDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AuthorDao repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthorDetails user = repository.findByEmailId(username).get(0);
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),
				new ArrayList<>());
	}
}
