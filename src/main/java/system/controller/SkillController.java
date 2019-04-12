package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import system.model.Skill;
import system.service.SkillService;

@Controller
@RequestMapping(value = "/skills", produces = "text/plain;charset=UTF-8")
@CrossOrigin(origins = "http://localhost:1841")
@SuppressWarnings("all")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateSkill() {
        return skillService.update();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addTechnology(@RequestParam String name) {
        return skillService.save(new Skill(name), name);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteTechnology(@RequestParam String id) {
        return skillService.delete(id);
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public String updateTechnologyData(@RequestParam String name, String id) {
        return skillService.updateData(name, id);
    }
}
