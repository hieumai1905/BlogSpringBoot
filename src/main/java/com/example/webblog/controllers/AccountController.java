package com.example.webblog.controllers;

import com.example.webblog.models.Account;
import com.example.webblog.models.ImageSlide;
import com.example.webblog.requestmodel.AccountLogin;
import com.example.webblog.requestmodel.AccountRegister;
import com.example.webblog.servies.account.IAccountService;
import com.example.webblog.servies.imageslide.IImageSlideService;
import com.example.webblog.validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IImageSlideService imageSlideService;

    @GetMapping("/login")
    public String showFormLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showFormRegister() {
        return "register";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("account-login");
        request.getSession().removeAttribute("user-role");
        return "login";
    }

    @GetMapping("/blog")
    public String blog(HttpServletRequest request) {
        if(request.getSession().getAttribute("account-login") == null){
            return "login";
        }
        List<ImageSlide> imageSlides = (List<ImageSlide>) imageSlideService.findAll();
        request.setAttribute("imageSlides", imageSlides);
        return "/blog/index";
    }

    @GetMapping("/blog/single-post")
    public String singlePost(HttpServletRequest request, @RequestParam("id") Integer id) {
        if(request.getSession().getAttribute("account-login") == null){
            return "login";
        }
        if(id == null){
            return "/blog/index";
        }
        request.setAttribute("postId", id);
        return "/blog/single-post";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, @ModelAttribute AccountLogin accountLogin) {
        String email = accountLogin.getEmail();
        String password = accountLogin.getPassword();
        if (Validation.isNullOrEmpty(email) || Validation.isNullOrEmpty(password)) {
            request.setAttribute("error", "Email and password is required");
            return "login";
        }
        Account loginSuccess = accountService.login(email, password);
        if (loginSuccess != null) {
            request.getSession().setAttribute("account-login", loginSuccess);
            String userRole = loginSuccess.getRole() == 1 ? "ROLE_ADMIN" : "ROLE_USER";
            request.getSession().setAttribute("user-role", userRole);
            List<ImageSlide> imageSlides = (List<ImageSlide>) imageSlideService.findAll();
            request.setAttribute("imageSlides", imageSlides);
            return "blog/index";
        }
        request.setAttribute("error", "Email and password incorrect");
        return "login";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request, @ModelAttribute AccountRegister accountRegister) throws ParseException {
        String fullname = accountRegister.getFullname();
        String email = accountRegister.getEmail();
        String password = accountRegister.getPassword();
        String confirmPassword = accountRegister.getConfirmPassword();
        String birthday = accountRegister.getBirthday();
        int gender = accountRegister.getGender();
        if(accountService.checkEmailExists(email)){
            request.setAttribute("error", "Email already exists");
            return "register";
        }
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Password and confirm password not match");
            return "register";
        }
        Account accountExists = accountService.getAccountByEmail(email);
        if (accountExists != null) {
            request.setAttribute("error", "Email already exists");
            return "register";
        }
        boolean registerSuccess = accountService.register(fullname, email, password, birthday, gender);
        if (registerSuccess) {
            return "login";
        } else {
            request.setAttribute("error", "Register failed");
        }
        return "register";
    }
}
