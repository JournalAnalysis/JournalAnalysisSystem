package com.example.demo.controller;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.User;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/adm/login")
@RestController
@RequestMapping("/adm")
public class LoginController{

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public String Login(@RequestBody User user){
        String uname;
        String utype;
//        return user.getUname();
        List<User> Users = userRepository.findByUname(user.getUname());
        if(Users.isEmpty()){
            return "失败";
        }else{
            uname = Users.get(0).getUname();
            utype = Users.get(0).getType();
            return utype;
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        if(userRepository.findByUname(user.getUname()).isEmpty()) {
            userRepository.save(user);
            if (!userRepository.findByUname(user.getUname()).isEmpty()) {
                return "注册成功";
            } else {
                return "注册失败";
            }
        }else{
            return "已有该用户！";
        }
    }


}
