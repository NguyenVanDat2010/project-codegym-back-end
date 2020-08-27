package com.airbnb.clone.service;

import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;
@Service
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findByUsername(username);

        AppUser appUser = appUserOptional.orElseThrow(() -> new UsernameNotFoundException("No " +
                "user " + "Found with username : " + username));

        return new User(appUser.getUsername(), appUser.getPassword(),appUser.isEnabled(), true,
                true,true, getAuthorities("USER"));


    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
