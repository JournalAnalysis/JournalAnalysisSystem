package com.example.demo.controller;

import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Company;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping("/inf")
    public List<User> Inf(@RequestBody User user){
        List<User> Users = userRepository.findByUname(user.getUname());
        return Users;
    }

    @PostMapping("/changeinf")
    public String ChangeInf(@RequestBody User user){
        List<User> Users = userRepository.findByUname(user.getUname());
        User user2 = Users.get(0);
        user2.setPassword(user.getPassword());
        List<Company> companies = companyRepository.findByCcode(user.getCcode());
        user2.setCname(companies.get(0).getCname());
        userRepository.save(user2);
        return "修改成功！";
    }
}
