package com.sinensia.core.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sinensia.core.security.integration.model.UsuarioPL;
import com.sinensia.core.security.integration.repository.UsuarioPLRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UsuarioPLRepository usuarioPLRepository;
	
	public UserDetailsServiceImpl(UsuarioPLRepository usuarioPLRepository) {
		this.usuarioPLRepository = usuarioPLRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsuarioPL usuarioPL = usuarioPLRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username " + username));
		
		return UserDetailsImpl.build(usuarioPL);
	}

}
