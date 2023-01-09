package com.user.userservice.domain.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.user.userservice.domain.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseAllUser {
    private Long id;
    private String email;
    private String name;

    private List<ResponseOrder> orders;

    public ResponseAllUser(UserEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
