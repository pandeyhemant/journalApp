package com.hemantPandey.journalApp.controller;

import com.hemantPandey.journalApp.entity.User;
import com.hemantPandey.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public  void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
