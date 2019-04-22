package com.system.springmvc.controller;

import com.system.springmvc.model.LastUsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.system.springmvc.service.LastUsedService;

@RestController
@RequestMapping(value = "/lastused", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
public class LastUsedController {

    @Autowired
    private LastUsedService lastUsedService;

    @RequestMapping(value = "/update")
    public String updateInterval() {
        return lastUsedService.update();
    }

    @RequestMapping("/add")
    public String addInterval(@RequestParam String name) {
        return lastUsedService.save(new LastUsed(name));
    }

    @RequestMapping("/delete")
    public String deleteInterval(@RequestParam String id) {
        return lastUsedService.delete(id);
    }

    @RequestMapping("/updateData")
    public String updateIntervalData(@RequestParam String name, String id) {
        return lastUsedService.updateData(name, id);
    }

}
