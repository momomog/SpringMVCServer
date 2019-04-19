package system.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import system.model.Personal;
import system.model.User;
import system.util.HibernateSessionFactoryUtil;

import java.util.*;

@Repository
public class UserDao {
    private Session session;
    private Transaction transaction;
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, String> map = new HashMap<>();
    private User user;

    {
        session = HibernateSessionFactoryUtil.getCurrentSession();
    }

    public String update() {
        StringBuilder sb = new StringBuilder();
        Transaction transaction = session.beginTransaction();
        try {
            List<User> users = (List<User>) session.createQuery("From User u order by u.id").list();
            sb.append("{\"users\":[");

            for (User currentUser : users) {
                user = currentUser;
                map.put("id", String.valueOf(user.getId()));
                map.put("name", user.getName());
                map.put("email", user.getEmail());
                map.put("phone", user.getPhone());
                sb.append(mapper.writeValueAsString(map)).append(",");
                map.clear();
            }

            sb.setLength(sb.length() - 1);
            sb.append("], \"success\": true,\"message\": \"Данные обновлены!\" }");
            transaction.commit();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (sb.toString().contains("name")) {
            return sb.toString();
        } else {
            return "{\"success\": true,\"message\": \"Данные обновлены!\"}";
        }
    }

    public String save(User currUser, String email, String phone) {
        Transaction transaction = session.beginTransaction();
        List<User> users = (List<User>) session.createQuery("From User").list();

        for (User currentUser : users) {
            user = currentUser;

            if (email.equals(user.getEmail())) {
                transaction.rollback();
                return "{\"success\": false,\"message\": \"Пользователь с данной почтой уже зарегестрирован!\"}";
            }

            if (phone.equals(user.getPhone())) {
                transaction.rollback();
                return "{\"success\": false,\"message\": \"Пользователь с данным телефоном уже зарегестрирован!\"}";
            }
        }

        session.save(currUser);
        transaction.commit();
        session.refresh(currUser);
        return "{\"success\": true,\"message\": \"Пользователь добавлен!\"}";
    }

    public String delete(String id) {
        List<Personal> personals = (List<Personal>) session.createQuery("From Personal").list();
        for (Personal personal : personals) {
            if(personal.getName() == Integer.parseInt(id)){
                session.delete(personal);
            }
        }

        transaction = session.beginTransaction();
        User user = session.load(User.class, Integer.parseInt(id));
        session.delete(user);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Пользователь удален!\"}";
    }

    public String updateData(String name, String email, String phone, String id) {
        transaction = session.beginTransaction();
        User user = session.get(User.class, Integer.parseInt(id));
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        session.update(user);
        transaction.commit();
        session.refresh(user);
        return "{\"success\": true,\"message\": \"Данные изменены!\"}";
    }
}
