package com.user.userservice.global.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.user.userservice.domain.dto.request.RequestLogin;
import com.user.userservice.domain.dto.response.ResponseUser;
import com.user.userservice.domain.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserService userService;


    private final Long expirationTime = 86400000L;
    private final String tokenSecret = "dXNlclRva2VuVGVzdHdkd2Vkd2Vkd2Vkd2Vkd3NkYXNkc2Fkd3FlcXdlcXdkcXdkcXdkcXdkcXdkcXdkcXd3ZWZzZGY=";



    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super.setAuthenticationManager(authenticationManager);
        this.userService = userService;
    }

    // 로그인 시도 하면 첫번째로 오는곳
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            ServletInputStream inputStream = request.getInputStream();
            String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

            RequestLogin requestLogin = objectMapper.readValue(message, RequestLogin.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestLogin.getEmail(),
                            requestLogin.getPassword(),
                            new ArrayList<>()
                    )
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult
    ) throws IOException, ServletException {
            // 성공 했을떄 정확하게 어떤처리를 해줄것인지, 토큰 만료 시간 언제인지? 사용자 로그인 했을때 반환값

        String username = ((User) authResult.getPrincipal()).getUsername();

        ResponseUser userDetailsByEmail = userService.getUserDetailsByEmail(username);
        String userId = String.valueOf(userDetailsByEmail.getId());

        System.out.println(expirationTime);
        System.out.println(tokenSecret);
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, tokenSecret)
                .compact();

        response.addHeader("token", token);
       response.addHeader("userId", userId);
    }
}
