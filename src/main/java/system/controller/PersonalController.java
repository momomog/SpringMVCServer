package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import system.service.PersonalService;

@Controller
@RequestMapping(value = "/personals", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
@SuppressWarnings("all")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateUser(@RequestParam String data) {
        return personalService.update(data);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addUser(@RequestParam String data) {
        return personalService.add(data);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteUser(@RequestParam String data) {
        return personalService.delete(data);
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public String updateUserData(@RequestParam String data) {
        return personalService.updateData(data);
    }
}
