package system.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import system.model.*;
import system.util.HibernateSessionFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings("all")
public class PersonalDao {
    private Session session;
    private Transaction transaction;
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, String> map = new HashMap<>();

    {
        session = HibernateSessionFactoryUtil.getCurrentSession();
    }

    public String update() {
        StringBuilder sb = new StringBuilder();
        transaction = session.beginTransaction();
        List<Personal> personals = (List<Personal>) session.createQuery("From Personal").list();
        sb.append("{\"personals\":[");
        for (Personal personal : personals) {
            map.put("id", Integer.toString(personal.getId()));
            map.put("name", personal.getUserObject().getName());
            map.put("technology", personal.getTechnologyObject().getName());
            map.put("skill", personal.getSkillObject().getName());
            map.put("used", personal.getLastUsedObject().getName());
            map.put("commentary", personal.getCommentary());
            try {
                sb.append(mapper.writeValueAsString(map)).append(",");
            } catch (JsonProcessingException | NullPointerException e) {
                e.printStackTrace();
            }
            map.clear();
        }
        sb.setLength(sb.length() - 1);
        sb.append("], \"success\": true,\"message\": \"Данные обновлены!\" }");
        transaction.commit();

        if (sb.toString().contains("id")) {
            return sb.toString();
        } else {
            return "{\"success\": true,\"message\": \"Данные обновлены!\"}";
        }
    }

    public String save(String name, String technology, String skill, String used, String commentary) {
        Transaction transaction = session.beginTransaction();
        List<Personal> personals = (List<Personal>) session.createQuery("From Personal").list();

        for (Personal personal : personals) {
            if (personal.getName() == Integer.parseInt(name) && personal.getTechnology() == Integer.parseInt(technology)) {
                transaction.commit();
                return "{\"success\": false,\"message\": \"Данная технология для пользователя уже зарегестрирована!\"}";
            }
        }

        Personal pers = new Personal();
        pers.setName(Integer.valueOf(name));
        pers.setTechnology(Integer.valueOf(technology));
        pers.setSkill(Integer.valueOf(skill));
        pers.setUsed(Integer.valueOf(used));
        pers.setCommentary(commentary);

        session.save(pers);
        transaction.commit();
        session.refresh(pers);
        return "{\"success\": true,\"message\": \"Пользователь добавлен!\"}";
    }

    public String delete(String id) {
        transaction = session.beginTransaction();
        Personal pers = session.load(Personal.class, Integer.parseInt(id));
        session.delete(pers);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Технология удалена!\"}";
    }

    public String updateData(String id, String tech, String skill, String used, String commentary) {
        transaction = session.beginTransaction();
        Personal personal = session.get(Personal.class, Integer.parseInt(id));
        personal.setTechnology(Integer.parseInt(tech));
        personal.setSkill(Integer.parseInt(skill));
        personal.setUsed(Integer.parseInt(used));
        personal.setCommentary(commentary);
        session.update(personal);
        session.flush();
        transaction.commit();
        session.refresh(personal);
        return "{\"success\": true,\"message\": \"Данные изменены!\"}";
    }

}
