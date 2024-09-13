package br.com.divinecode.restfullapiudemy.data.vo.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountCredentialsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String password;

    public AccountCredentialsDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
