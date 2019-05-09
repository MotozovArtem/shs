package ru.rienel.shs.headcontroller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.rienel.shs.headcontroller.repository.CustomUserRepository;
import ru.rienel.shs.headcontroller.domain.CustomUser;

@Service("CustomUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

	private final CustomUserRepository customUserRepository;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CustomUserDetailService(CustomUserRepository customUserRepository, PasswordEncoder passwordEncoder) {
		this.customUserRepository = customUserRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUser customUser = customUserRepository.findByUsername(username);
		if (customUser == null) {
			throw new UsernameNotFoundException("User with name: " + username + " not found");
		}
		return new CustomSecurityUser(customUser.getUsername(), passwordEncoder.encode(customUser.getPassword()));
	}
}
