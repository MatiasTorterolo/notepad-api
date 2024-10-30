package com.mta.notepad_api.notepad_api.services;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mta.notepad_api.notepad_api.dtos.UserDTO;
import com.mta.notepad_api.notepad_api.entities.UserEntity;
import com.mta.notepad_api.notepad_api.repositories.IUserRepository;

@Service
public class ValidationUserService {

    @Autowired
    private IUserRepository iUserRepository;

    public boolean isValidEmail(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean isValidPassword(String password) {

        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,30}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    @Transactional(readOnly = true)
    public boolean isValidUsername(String username) {

        if (username.isBlank() || username.length() < 3) {

            return false;
        }

        Optional<UserEntity> userEntity = iUserRepository.findByUsername(username);

        if (userEntity.isPresent()) {

            return false;
        }

        return true;
    }

    public boolean executeValidations(UserDTO userDTO) {

        try {

            if (!isValidEmail(userDTO.getEmail())) {

                System.err.println("Invalid Email");
            } else {

                System.out.println("Valid Email");
            }

            if (!isValidPassword(userDTO.getPassword())) {

                System.err.println("Invalid Password");
            } else {

                System.out.println("Valid Password");
            }

            if (!isValidUsername(userDTO.getUsername())) {

                System.err.println("Username exists");
            } else {

                System.out.println("Valid Username");
            }

            return (isValidEmail(userDTO.getEmail()) && isValidPassword(userDTO.getPassword())
                    && isValidUsername(userDTO.getUsername()));

        } catch (Exception e) {

            System.err.println("unexpected error" + e.getMessage());
            return false;
        }

    }

}
