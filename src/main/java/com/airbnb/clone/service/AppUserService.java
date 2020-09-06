package com.airbnb.clone.service;

import com.airbnb.clone.dto.UpdateUserRequest;
import com.airbnb.clone.exception.AppUserNotFoundException;
import com.airbnb.clone.exception.HouseCategoryNotFoundException;
import com.airbnb.clone.mapper.UpdateUserMapper;
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

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
@Service
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UpdateUserMapper updateUserMapper;

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

    public Boolean existsByEmail(String email){
        return appUserRepository.existsByEmail(email);
    }

    public Boolean existsByPhoneNumber(String phoneNumber){
        return appUserRepository.existsByPhoneNumber(phoneNumber);
    }

    public Boolean existsByUserName(String username){
        return appUserRepository.existsByUsername(username);
    }

    public Iterable<AppUser> getAllUsers(){
        return appUserRepository.findAll();
    }

    public UpdateUserRequest getUserById(Long id){
        AppUser user =  appUserRepository.findById(id).orElseThrow(() -> new AppUserNotFoundException(id.toString()));
        return updateUserMapper.mapToDo(user);
    }

    public AppUser save(AppUser user){
        return appUserRepository.save(user);
    }

    public UpdateUserRequest getUserByUsername(String username){
        AppUser user =  appUserRepository.findByUsername(username).orElseThrow(() -> new AppUserNotFoundException(username.toString()));
        return updateUserMapper.mapToDo(user);
    }

    public Boolean updateUser(UpdateUserRequest updateUserRequest){
        List<AppUser> users = appUserRepository.getAllByUsernameAndEmailAndPhoneNumber(updateUserRequest.getId(), updateUserRequest.getUsername(),updateUserRequest.getEmail(),updateUserRequest.getPhoneNumber());
        if (users.size() == 0){
            AppUser oldUser = appUserRepository.findById(updateUserRequest.getId()).get();
            AppUser user = updateUserMapper.map(updateUserRequest);
            if (!oldUser.getEmail().equals(user.getEmail()) || !oldUser.getUsername().equals(user.getUsername())){
                return false;
            }
            user.setCreated(oldUser.getCreated());
            user.setEnabled(oldUser.isEnabled());
            user.setPassword(oldUser.getPassword());
            appUserRepository.save(user);
            return true;
        }
        return false;
    }
}
