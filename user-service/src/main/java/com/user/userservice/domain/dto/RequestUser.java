package com.user.userservice.domain.dto;


import com.user.userservice.domain.entity.UserEntity;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RequestUser {

    @NotBlank(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, message = "닉네임은 최소 2자리 이상이어야됩니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자리 이상이어야됩니다.")
    private String pwd;

    public UserEntity toUser(String encodePwd) {
        return UserEntity.builder()
                .name(name)
                .email(email)
                .pw(encodePwd)
                .build();
    }
}
