package com.user.userservice.domain.controller;


import com.user.userservice.domain.dto.RequestUser;
import com.user.userservice.domain.entity.UserEntity;
import com.user.userservice.domain.service.UserService;
import com.user.userservice.domain.dto.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser userRequest) {
        UserEntity user = userService.createUser(userRequest);

        ResponseUser responseUser = new ResponseUser(user.getEmail(), user.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseUser);
    }
}
