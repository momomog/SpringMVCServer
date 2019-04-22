package com.system.springmvc.controller;

import com.system.springmvc.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/personals", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @RequestMapping(value = "/update")
    public String updateTechnology() {
        return personalService.update();
    }

    @RequestMapping("/add")
    public String addTechnology(@RequestParam String name, String technology, String skill, String used, String commentary) {
        return personalService.save(name, technology, skill, used, commentary);
    }

    @RequestMapping("/delete")
    public String deleteTechnology(@RequestParam String id) {
        return personalService.delete(id);
    }

    @RequestMapping("/updateData")
    public String updateTechnologyData(@RequestParam String id, String technology, String skill, String used, String commentary) {
        return personalService.updateData(id, technology, skill, used, commentary);
    }
}
