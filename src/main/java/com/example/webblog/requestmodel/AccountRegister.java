package com.example.webblog.requestmodel;

import lombok.Data;

@Data
public class AccountRegister {
    private String fullname;

    private String password;

    private String confirmPassword;

    private String email;

    private String birthday;

    private int gender;
}
