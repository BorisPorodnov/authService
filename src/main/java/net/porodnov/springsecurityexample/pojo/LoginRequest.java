package net.porodnov.springsecurityexample.pojo;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String login;
    private String password;
}
