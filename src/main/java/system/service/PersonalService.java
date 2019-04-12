package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PersonalDao;

@Service
public class PersonalService {

    @Autowired
    PersonalDao personalDao;

    public String update(String data){
        return personalDao.personalsUpdate(data);
    }

    public String add(String data){
        return personalDao.addPersonalToDB(data);
    }

    public String delete(String data){
        return personalDao.deletePersonalFromDB(data);
    }

    public String updateData(String data){
        return personalDao.updatePersonaldataToDB(data);
    }
}
