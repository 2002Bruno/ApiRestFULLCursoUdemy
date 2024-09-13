package br.com.divinecode.restfullapiudemy.data.vo.security;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private Boolean authenticated;

    private Date created;

    private Date expiration;

    private String accessToken;

    private String refreshToken;

    public TokenDTO() {}

    public TokenDTO(String userName, Boolean authenticated, Date created, Date expiration, String accessToken, String refreshToken) {
        this.userName = userName;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
