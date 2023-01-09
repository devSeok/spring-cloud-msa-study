package com.user.userservice.domain.service;

import com.user.userservice.domain.dto.request.RequestUser;
import com.user.userservice.domain.dto.response.ResponseAllUser;
import com.user.userservice.domain.dto.response.ResponseFindOneUser;
import com.user.userservice.domain.entity.UserEntity;
import com.user.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseFindOneUser getUserByUserId(Long userId) {
        UserEntity byId = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다."));


        return new ResponseFindOneUser(byId.getName(), byId.getEmail(), new ArrayList<>());
    }

    public List<ResponseAllUser> getUserByAll() {
       return userRepository.findAll()
                .stream()
                .map(ResponseAllUser::new)
                .collect(Collectors.toList());
    }

}
