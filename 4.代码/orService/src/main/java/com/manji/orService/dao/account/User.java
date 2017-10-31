package com.manji.orService.dao.account;

import lombok.Data;

@Data
public class User {
    private String id;

    private String username;

    private String vsername;

    private String email;

    private String job;//工号

    private String mobile;
}
