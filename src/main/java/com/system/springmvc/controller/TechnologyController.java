package com.system.springmvc.controller;

import com.system.springmvc.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.system.springmvc.model.Technology;

@RestController
@RequestMapping(value = "/technologies", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
public class TechnologyController {

    @Autowired
    private TechService techService;

    @RequestMapping(value = "/update")
    public String updateTechnology() {
        return techService.update();
    }

    @RequestMapping("/add")
    public String addTechnology(@RequestParam String name) {
        return techService.save(new Technology(name));
    }

    @RequestMapping("/delete")
    public String deleteTechnology(@RequestParam String id) {
        return techService.delete(id);
    }

    @RequestMapping("/updateData")
    public String updateTechnologyData(@RequestParam String name, String id) {
        return techService.updateData(name, id);
    }

}
