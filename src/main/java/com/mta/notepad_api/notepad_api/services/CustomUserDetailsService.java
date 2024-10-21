package com.mta.notepad_api.notepad_api.services;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mta.notepad_api.notepad_api.entities.UserEntity;
import com.mta.notepad_api.notepad_api.repositories.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userOptional = iUserRepository.findByUsername(username);

        if (userOptional.isEmpty()) {

            throw new UsernameNotFoundException("username or password are incorrect, or username doesn't exists");
        }

        UserEntity userEntity = userOptional.get();

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isAsset(),
                true,
                true,
                true,
                Collections.emptyList());
    }

}
