package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.UserDao;
import system.model.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String update() {
        return userDao.update();
    }

    public String save(User user, String email, String phone) {
        return userDao.save(user, email, phone);
    }

    public String delete(String id) {
        return userDao.delete(id);
    }

    public String updateData(String name, String email, String phone, String id) {
        return userDao.updateData(name, email, phone, id);
    }

}
