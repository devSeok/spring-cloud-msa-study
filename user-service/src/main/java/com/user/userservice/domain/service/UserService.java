package com.user.userservice.domain.service;

import com.user.userservice.domain.dto.request.RequestUser;
import com.user.userservice.domain.dto.response.ResponseAllUser;
import com.user.userservice.domain.dto.response.ResponseFindOneUser;
import com.user.userservice.domain.dto.response.ResponseOrder;
import com.user.userservice.domain.dto.response.ResponseUser;
import com.user.userservice.domain.entity.UserEntity;
import com.user.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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
    private final Environment env;
    private final RestTemplate restTemplate;

    public UserEntity createUser(RequestUser userRequest) {

        String encodePwd = passwordEncoder.encode(userRequest.getPwd());
        UserEntity userEntity = userRequest.toUser(encodePwd);

       return userRepository.save(userEntity);
    }

    public ResponseFindOneUser getUserByUserId(Long userId) {
        UserEntity byId = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("???????????? ????????????."));


        String orderUrl = String.format(env.getProperty("order_service.url"), userId);
        ResponseEntity<List<ResponseOrder>> exchange = restTemplate.exchange(orderUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<ResponseOrder>>() {
            });

        List<ResponseOrder> body = exchange.getBody();

        return new ResponseFindOneUser(byId.getName(), byId.getEmail(), body);
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
