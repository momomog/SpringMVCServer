package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.LastUsedDao;
import system.model.LastUsed;

@Service
public class LastUsedService {

    @Autowired
    private LastUsedDao lastUsedDao;

    public String update(){
        return lastUsedDao.update();
    }

    public String save(LastUsed lastUsed, String name){
        return lastUsedDao.save(lastUsed, name);
    }

    public String delete(String id){
        return lastUsedDao.delete(id);
    }

    public String updateData(String name, String id){
        return lastUsedDao.updateData(name, id);
    }
}
