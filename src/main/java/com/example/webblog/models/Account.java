package com.example.webblog.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "create_at", nullable = false)
    private Date createAt;

    @Column(name = "role", nullable = false)
    private int role;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "gender", nullable = false)
    private int gender;

    public Account(String fullname, String password, String email, Date birthday, int gender) {
        this.fullname = fullname;
        this.password = password;
        this.createAt = new Date();
        this.role = 0;
        this.email = email;
        this.status = 1;
        this.birthday = birthday;
        this.gender = gender;
    }

}