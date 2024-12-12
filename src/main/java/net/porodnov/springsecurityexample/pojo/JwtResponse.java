package net.porodnov.springsecurityexample.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;

    public JwtResponse(String token, Long id, String login, String email) {
        this.token = token;
        this.id = id;
        this.username = login;
        this.email = email;
    }

}
