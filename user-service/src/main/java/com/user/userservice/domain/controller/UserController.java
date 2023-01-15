package com.user.userservice.domain.controller;


import com.user.userservice.domain.dto.request.RequestUser;
import com.user.userservice.domain.dto.response.ResponseAllUser;
import com.user.userservice.domain.dto.response.ResponseFindOneUser;
import com.user.userservice.domain.entity.UserEntity;
import com.user.userservice.domain.service.UserService;
import com.user.userservice.domain.dto.response.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseFindOneUser> getUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<ResponseAllUser>> getUserList() {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(userService.getUserByAll());
    }

    @PostMapping
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser userRequest) {
        UserEntity user = userService.createUser(userRequest);

        ResponseUser responseUser = new ResponseUser(user.getId(), user.getEmail(), user.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseUser);
    }
}
