package com.user.userservice.domain.service;

import com.user.userservice.domain.dto.RequestUser;
import com.user.userservice.domain.entity.UserEntity;
import com.user.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity createUser(RequestUser userRequest) {

        String encodePwd = passwordEncoder.encode(userRequest.getPwd());
        UserEntity userEntity = userRequest.toUser(encodePwd);

       return userRepository.save(userEntity);
    }
}
