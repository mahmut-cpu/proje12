package com.example.iste.controller;

import com.example.iste.entity.User;
import com.example.iste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/message-page")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);  // Kullanıcıları model ile thymeleaf'e gönderiyoruz
        return "message";  // users.html sayfasını döndürüyoruz
    }

}

