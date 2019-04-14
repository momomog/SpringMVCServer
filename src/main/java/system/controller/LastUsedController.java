package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.model.LastUsed;
import system.service.LastUsedService;

@Controller
@RequestMapping(value = "/lastused", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
public class LastUsedController {

    @Autowired
    private LastUsedService lastUsedService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateInterval() {
        return lastUsedService.update();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addInterval(@RequestParam String name) {
        return lastUsedService.save(new LastUsed(name), name);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteInterval(@RequestParam String id) {
        return lastUsedService.delete(id);
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public String updateIntervalData(@RequestParam String name, String id) {
        return lastUsedService.updateData(name, id);
    }

}
