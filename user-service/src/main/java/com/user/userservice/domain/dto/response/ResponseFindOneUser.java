package com.user.userservice.domain.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseFindOneUser {

    private String email;
    private String name;
    private List<ResponseOrder> orders = new ArrayList<>();

}
