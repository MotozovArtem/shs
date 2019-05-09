package ru.rienel.shs.headcontroller.security;

import org.springframework.security.core.authority.AuthorityUtils;

public class CustomSecurityUser extends org.springframework.security.core.userdetails.User {

	public CustomSecurityUser(String username, String password) {
		super(username, password, AuthorityUtils.createAuthorityList("USER"));
	}
}
