package com.user.userservice.domain.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLogin {

    @NotBlank(message = "이메일은 필수 값 입니다.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호호은 필 값 입니다.")
    private String password;

}
