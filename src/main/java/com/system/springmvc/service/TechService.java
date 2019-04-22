package com.system.springmvc.service;

import com.system.springmvc.dao.TechDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.system.springmvc.model.Technology;

@Service
public class TechService {

    @Autowired
    private TechDao techDao;

    public String update(){
        return techDao.update();
    }

    public String save(Technology technology){
        return techDao.save(technology);
    }

    public String delete(String id){
        return techDao.delete(id);
    }

    public String updateData(String name, String id){
        return techDao.updateData(name, id);
    }
}
