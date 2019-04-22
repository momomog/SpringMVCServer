package com.system.springmvc.controller;

import com.system.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.system.springmvc.service.UserService;

@RestController
@RequestMapping(value = "/users", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/update")
    public String updateTechnology() {
        return userService.update();
    }

    @RequestMapping("/add")
    public String addTechnology(@RequestParam String name, String email, String phone) {
        return userService.save(new User(name, email, phone), email, phone);
    }

    @RequestMapping("/delete")
    public String deleteTechnology(@RequestParam String id) {
        return userService.delete(id);
    }

    @RequestMapping("/updateData")
    public String updateTechnologyData(@RequestParam String name, String email, String phone, String id) {
        return userService.updateData(name, email, phone, id);
    }
}
