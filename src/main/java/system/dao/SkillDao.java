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
@SuppressWarnings("all")
public class SkillDao {
    private Session session;
    private Transaction transaction;
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, String> map = new HashMap<>();
    private Skill skill;


    {
        session = HibernateSessionFactoryUtil.getCurrentSession();
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

    //@Transactional
    public String save(Skill skillType) {
        Transaction transaction = session.beginTransaction();
        List<Skill> skills = (List<Skill>) session.createQuery("From Skill").list();

        for (Skill skillObject : skills) {
            skill = skillObject;

            if (skillType.getName().equals(skill.getName())) {
                transaction.rollback();
                return "{\"success\": false,\"message\": \"Данный навык уже зарегестрирован!\"}";
            }
        }

        session.save(skillType);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Навык добавлен!\"}";
    }

    public String delete(String id) {
        try {
            transaction = session.beginTransaction();
            Skill skill = session.load(Skill.class, Integer.parseInt(id));
            session.delete(skill);
            transaction.commit();
            return "{\"success\": true,\"message\": \"Навык удален!\"}";
        } catch (Exception e) {
            transaction.rollback();
            return "{\"success\": false,\"message\": \"Вы не можете удалить данный навык, так как он используется в таблице знаний сотрудников!\"}";
        }
    }

    public String updateData(String name, String id) {
        transaction = session.beginTransaction();
        skill = session.get(Skill.class, Integer.parseInt(id));
        skill.setName(name);
        session.update(skill);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Данные изменены!\"}";
    }
}
