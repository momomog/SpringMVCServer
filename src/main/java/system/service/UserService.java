package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.UserDao;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String update(){
        return userDao.usersUpdate();
    }

    public String add(String data){
        return userDao.addUserToDB(data);
    }

    public String delete(String data){
        return userDao.deleteUserFromDB(data);
    }

    public String updateData(String data){
        return userDao.updateUserdataToDB(data);
    }
}
