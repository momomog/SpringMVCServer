package com.system.springmvc.service;

import com.system.springmvc.dao.SkillDao;
import com.system.springmvc.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SkillService {

    @Autowired
    private SkillDao skillDao;

    public String update() {
        return skillDao.update();
    }

    public String save(Skill skill) {
        return skillDao.save(skill);
    }

    public String delete(String id) {
        return skillDao.delete(id);
    }

    public String updateData(String name, String id) {
        return skillDao.updateData(name, id);
    }
}
