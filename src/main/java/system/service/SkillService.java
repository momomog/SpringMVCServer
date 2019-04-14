package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.SkillDao;
import system.model.Skill;

@Service
public class SkillService {

    @Autowired
    private SkillDao skillDao;

    public String update() {
        return skillDao.update();
    }

    public String save(Skill skill, String name) {
        return skillDao.save(skill, name);
    }

    public String delete(String id) {
        return skillDao.delete(id);
    }

    public String updateData(String name, String id) {
        return skillDao.updateData(name, id);
    }
}
