package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.service.UserService;

@Controller
@RequestMapping(value = "/users", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateUser() {
        System.out.println("____");
        System.out.println(userService.update());
        System.out.println("____");
        return userService.update();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addUser(@RequestParam String data) {
        System.out.println("____");
        System.out.println(userService.add(data));
        System.out.println("____");
        return userService.add(data);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteUser(@RequestParam String data) {
        System.out.println("____");
        System.out.println(userService.delete(data));
        System.out.println("____");
        return userService.delete(data);
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public String updateUserData(@RequestParam String data) {
        System.out.println("____");
        System.out.println(userService.updateData(data));
        System.out.println("____");
        return userService.updateData(data);
    }
}
