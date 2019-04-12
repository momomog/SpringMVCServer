package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.LastUsedDao;

@Service
public class LastUsedService {

    @Autowired
    private LastUsedDao lastUsedDao;

    public String update(){
        return lastUsedDao.usedsUpdate();
    }

    public String add(String data){
        return lastUsedDao.addUsedToDB(data);
    }

    public String delete(String data){
        return lastUsedDao.deleteUsedFromDB(data);
    }

    public String updateData(String data){
        return lastUsedDao.updateUseddataToDB(data);
    }
}
