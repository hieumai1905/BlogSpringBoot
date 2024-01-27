package com.example.webblog.controllers;

import com.example.webblog.models.Account;
import com.example.webblog.models.ImageSlide;
import com.example.webblog.models.Post;
import com.example.webblog.requestmodel.AccountLogin;
import com.example.webblog.requestmodel.AccountRegister;
import com.example.webblog.requestmodel.PostRequest;
import com.example.webblog.servies.account.IAccountService;
import com.example.webblog.servies.imageslide.IImageSlideService;
import com.example.webblog.servies.post.IPostService;
import com.example.webblog.validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IImageSlideService imageSlideService;

    @Autowired
    private IPostService postService;
    @Autowired
    private HttpServletRequest request;

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

    @GetMapping("/info")
    public String showInfo() {
        if (request.getSession().getAttribute("account-login") == null) {
            return "login";
        }
        Account account = (Account) request.getSession().getAttribute("account-login");
        request.setAttribute("account", account);

        List<PostRequest> posts = postService.getAllPostByAccountId(account.getAccountId());
        request.setAttribute("postCount", posts.size());
        request.setAttribute("content-in-layout", "profile");
        return "admin/layout";
    }

    @PostMapping("/account/update")
    public String updateAccount(@ModelAttribute AccountRegister accountRegister) throws ParseException {
        if (request.getSession().getAttribute("account-login") == null) {
            return "login";
        }
        Account account = (Account) request.getSession().getAttribute("account-login");
        String fullname = accountRegister.getFullname();
        String password = accountRegister.getPassword();
        String birthday = accountRegister.getBirthday();
        int gender = accountRegister.getGender();
        account.setFullname(fullname);
        account.setPassword(password);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDayDate = dateFormat.parse(birthday);
        account.setBirthday(birthDayDate);
        account.setGender(gender);
        Account accountUpdate = accountService.save(account);
        if (accountUpdate == null) {
            request.setAttribute("error", "Update failed");
        } else {
            request.setAttribute("account", accountUpdate);
            request.getSession().setAttribute("account-login", accountUpdate);
            request.setAttribute("message", "Update account success");
        }
        List<PostRequest> posts = postService.getAllPostByAccountId(account.getAccountId());
        request.setAttribute("postCount", posts.size());
        request.setAttribute("content-in-layout", "profile");
        return "admin/layout";
    }

    @GetMapping("/blog")
    public String blog(HttpServletRequest request) {
        if (request.getSession().getAttribute("account-login") == null) {
            return "login";
        }
        List<ImageSlide> imageSlides = (List<ImageSlide>) imageSlideService.findAll();
        request.setAttribute("imageSlides", imageSlides);
        return "/blog/index";
    }

    @GetMapping("/users")
    public String blogUser(HttpServletRequest request, @RequestParam Long id) {
        if (request.getSession().getAttribute("account-login") == null) {
            return "login";
        }
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            request.setAttribute("account", account.get());
        }
        List<ImageSlide> imageSlides = (List<ImageSlide>) imageSlideService.findAll();
        request.setAttribute("imageSlides", imageSlides);
        return "/blog/blog-of-user";
    }

    @GetMapping("/blog/single-post")
    public String singlePost(HttpServletRequest request, @RequestParam("id") Integer id) {
        if (request.getSession().getAttribute("account-login") == null) {
            return "login";
        }
        if (id == null) {
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
        if (accountService.checkEmailExists(email)) {
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
