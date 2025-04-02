package net.porodnov.springsecurityexample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    private String login;
    private String password;
    private String email;
}
