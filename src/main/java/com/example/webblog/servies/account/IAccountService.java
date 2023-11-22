package com.example.webblog.servies.account;

import com.example.webblog.models.Account;
import com.example.webblog.servies.IGeneralService;

import java.text.ParseException;
import java.util.Date;

public interface IAccountService extends IGeneralService<Account> {
    Account login(String email, String password);

    Account getAccountByEmail(String email);

    Boolean checkEmailExists(String email);

    boolean register(String fullname, String email, String password, String birthday, int gender) throws ParseException;
}
