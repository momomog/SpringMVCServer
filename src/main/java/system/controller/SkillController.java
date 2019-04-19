package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.model.Skill;
import system.service.SkillService;

@RestController
@RequestMapping(value = "/skills", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/update")
    public String updateSkill() {
        return skillService.update();
    }

    @RequestMapping("/add")
    public String addTechnology(@RequestParam String name) {
        return skillService.save(new Skill(name));
    }

    @RequestMapping("/delete")
    public String deleteTechnology(@RequestParam String id) {
        return skillService.delete(id);
    }

    @RequestMapping("/updateData")
    public String updateTechnologyData(@RequestParam String name, String id) {
        return skillService.updateData(name, id);
    }
}
