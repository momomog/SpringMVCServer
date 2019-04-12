package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import system.service.LastUsedService;

@Controller
@RequestMapping(value = "/lastused", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
@SuppressWarnings("all")
public class LastUsedController {

    @Autowired
    private LastUsedService lastUsedService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateUser() {
        return lastUsedService.update();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addUser(@RequestParam String data) {
        return lastUsedService.add(data);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteUser(@RequestParam String data) {
        return lastUsedService.delete(data);
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public String updateUserData(@RequestParam String data) {
        return lastUsedService.updateData(data);
    }
}
