package com.example.demo.controller;

import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Company;
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
import java.util.Objects;
import java.util.Random;

//@WebServlet("/adm/login")
@RestController
@RequestMapping("/adm")
public class LoginController{
    public static Random random;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @PostMapping("/login")
    public String Login(@RequestBody User user){
        String uname;
        String utype;
//        return user.getUname();
        List<User> Users = userRepository.findByUname(user.getUname());
        String pass= user.getUpassword();

        if(Users.isEmpty()|| !Objects.equals(pass, Users.get(0).getUpassword())){
            return "失败";
        }else{
            uname = Users.get(0).getUname();
            utype = Users.get(0).getUtype();
            return utype;
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        String cinf;
        String ccode;
        if(Objects.equals(user.getUtype(), "idi")){
            user.setUauth(null);
            user.setCname(null);
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
        }else{
            cinf = user.getUauth();
            user.setUauth(null);
            Company com = new Company();
            com.setCname(user.getCname());
            com.setCinf(cinf);
            int max=1000000;
            int min=100000;
            random=new Random();
            ccode = String.valueOf(random.nextInt(max)%(max-min+1)+min);
            com.setCcode(ccode);
            companyRepository.save(com);
            if(userRepository.findByUname(user.getUname()).isEmpty()) {
                userRepository.save(user);
                return "注册成功！";
            }else{
                return "已有该用户！";
            }
        }
    }


}
