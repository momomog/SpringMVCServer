package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.model.User;
import system.service.UserService;

@Controller
@RequestMapping(value = "/users", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateTechnology() {
        return userService.update();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addTechnology(@RequestParam String name, String email, String phone) {
        return userService.save(new User(name, email, phone), email, phone);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteTechnology(@RequestParam String id) {
        return userService.delete(id);
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public String updateTechnologyData(@RequestParam String name, String email, String phone, String id) {
        return userService.updateData(name, email, phone, id);
    }
}
