package com.user.userservice.domain.service;

import com.user.userservice.domain.dto.request.RequestUser;
import com.user.userservice.domain.dto.response.ResponseAllUser;
import com.user.userservice.domain.dto.response.ResponseFindOneUser;
import com.user.userservice.domain.dto.response.ResponseUser;
import com.user.userservice.domain.entity.UserEntity;
import com.user.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(userEntity.getEmail(), userEntity.getPw(),
                true, true, true ,true,
                new ArrayList<>());
    }

    public ResponseUser getUserDetailsByEmail(String email) {

        UserEntity byEmail = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

            return new ResponseUser(byEmail.getId(), byEmail.getEmail(), byEmail.getName());
    }
}
