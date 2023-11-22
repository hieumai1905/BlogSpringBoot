package com.example.webblog.requestmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountLogin {
    private String email;
    private String password;
    public AccountLogin() {
    }
}
