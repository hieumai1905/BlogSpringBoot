package com.example.webblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    HttpServletRequest request;
    @GetMapping
    public String showDashboard() {
        if(request.getSession().getAttribute("account-login") == null){
            return "login";
        }
        String role = (String)request.getSession().getAttribute("user-role");
        if(role == null || role.equals("ROLE_USER")){
            return "blog";
        }
        request.setAttribute("content-in-layout", "dash-board");
        return "admin/layout";
    }

    @GetMapping("/categories")
    public String showManagementCategory() {
        if(request.getSession().getAttribute("account-login") == null){
            return "login";
        }
        String role = (String)request.getSession().getAttribute("user-role");
        if(role == null || role.equals("ROLE_USER")){
            return "blog";
        }
        request.setAttribute("content-in-layout", "category");
        return "admin/layout";
    }

    @GetMapping("/users")
    public String showManagementUser() {
        if(request.getSession().getAttribute("account-login") == null){
            return "login";
        }
        String role = (String)request.getSession().getAttribute("user-role");
        if(role == null || role.equals("ROLE_USER")){
            return "blog/index";
        }
        request.setAttribute("content-in-layout", "user");
        return "admin/layout";
    }

    @GetMapping("/types")
    public String showManagementType() {
        if(request.getSession().getAttribute("account-login") == null){
            return "login";
        }
        String role = (String)request.getSession().getAttribute("user-role");
        if(role == null || role.equals("ROLE_USER")){
            return "blog/index";
        }
        request.setAttribute("content-in-layout", "type");
        return "admin/layout";
    }

    @GetMapping("/posts")
    public String showManagementPost() {
        if(request.getSession().getAttribute("account-login") == null){
            return "login";
        }
        request.setAttribute("content-in-layout", "post");
        return "admin/layout";
    }

    @GetMapping("/create-post")
    public String showCreatePost() {
        if(request.getSession().getAttribute("account-login") == null){
            return "login";
        }
        request.setAttribute("content-in-layout", "create-post");
        return "admin/layout";
    }

    @GetMapping("/edit-post")
    public String showEditPost(@RequestParam("id") Integer id) {
        if(request.getSession().getAttribute("account-login") == null){
            return "login";
        }
        if(id == null){
            request.setAttribute("content-in-layout", "post");
            return "admin/layout";
        }
        request.setAttribute("content-in-layout", "edit-post");
        request.setAttribute("postId", id);
        return "admin/layout";
    }

    @GetMapping("/image-slides")
    public String showManagementImageSlide() {
        if(request.getSession().getAttribute("account-login") == null){
            return "login";
        }
        String role = (String)request.getSession().getAttribute("user-role");
        if(role == null || role.equals("ROLE_USER")){
            return "blog";
        }
        request.setAttribute("content-in-layout", "image-slide");
        return "admin/layout";
    }
}
