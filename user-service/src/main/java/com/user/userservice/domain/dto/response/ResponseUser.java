package com.user.userservice.domain.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseUser {
    private String email;
    private String name;
}
