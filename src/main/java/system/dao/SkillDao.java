package system.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import system.model.Skill;
import system.util.HibernateSessionFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
//@SuppressWarnings("all")
public class SkillDao {
    private Session session;
    private Transaction transaction;
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, String> map = new HashMap<>();
    private Skill skill;

    {
        session = HibernateSessionFactoryUtil.getSession();
    }

    public String update() {
        StringBuilder sb = new StringBuilder();
        Transaction transaction = session.beginTransaction();
        try {
            List<Skill> skills = (List<Skill>) session.createQuery("From Skill").list();
            sb.append("{\"skills\":[");

            for (Skill skillObject : skills) {
                skill = skillObject;
                map.put("id", String.valueOf(skill.getId()));
                map.put("name", skill.getName());
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

    public String save(Skill skillType, String name) {
        Transaction transaction = session.beginTransaction();
        List<Skill> skills = (List<Skill>) session.createQuery("From Skill").list();

        for (Skill skillObject : skills) {
            skill = skillObject;

            if (name.equals(skill.getName())) {
                transaction.commit();
                return "{\"success\": false,\"message\": \"Данный навык уже зарегестрирован!\"}";
            }
        }

        session.save(skillType);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Технология добавлена!\"}";
    }

    public String delete(String id) {

        //необходима проверка занятности в результирующей таблице!

        transaction = session.beginTransaction();
        Skill skill = session.load(Skill.class, Integer.parseInt(id));
        session.delete(skill);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Технология удалена!\"}";
    }

    public String updateData(String name, String id) {
        transaction = session.beginTransaction();
        skill = (Skill) session.get(Skill.class, Integer.parseInt(id));
        skill.setName(name);
        session.update(skill);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Данные изменены!\"}";
    }
}