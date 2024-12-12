package net.porodnov.springsecurityexample.pojo;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String login;
    private String password;
    private String email;
}
