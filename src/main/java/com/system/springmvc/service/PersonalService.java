package com.system.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.system.springmvc.dao.PersonalDao;

@Service
public class PersonalService {

    @Autowired
    private PersonalDao personalDao;

    public String update() {
        return personalDao.update();
    }

    public String save(String name, String technology, String skill, String used, String commentary) {
        return personalDao.save(name, technology, skill, used, commentary);
    }

    public String delete(String id) {
        return personalDao.delete(id);
    }

    public String updateData(String id, String technology, String skill, String used, String commentary) {
        return personalDao.updateData(id, technology, skill, used, commentary);
    }
}
