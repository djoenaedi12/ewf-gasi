package gasi.ewf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gasi.ewf.entity.User;
import gasi.ewf.entity.UserPrincipal;
import gasi.ewf.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
        		.orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );
        return UserPrincipal.create(user);
	}

}
