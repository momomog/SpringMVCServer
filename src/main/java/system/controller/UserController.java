package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.service.UserService;

@Controller
@RequestMapping(value = "/users", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
@SuppressWarnings("all")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateUser() {
        return userService.update();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addUser(@RequestParam String data) {
        return userService.add(data);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteUser(@RequestParam String data) {
        return userService.delete(data);
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public String updateUserData(@RequestParam String data) {
        return userService.updateData(data);
    }
}
