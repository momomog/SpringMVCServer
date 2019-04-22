package com.system.springmvc.service;

import com.system.springmvc.dao.LastUsedDao;
import com.system.springmvc.model.LastUsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LastUsedService {

    @Autowired
    private LastUsedDao lastUsedDao;

    public String update(){
        return lastUsedDao.update();
    }

    public String save(LastUsed lastUsed){
        return lastUsedDao.save(lastUsed);
    }

    public String delete(String id){
        return lastUsedDao.delete(id);
    }

    public String updateData(String name, String id){
        return lastUsedDao.updateData(name, id);
    }
}
