package system.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import system.model.Technology;
import system.util.HibernateSessionFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
//@SuppressWarnings("all")
public class TechDao {
    private Session session;
    private Transaction transaction;
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, String> map = new HashMap<>();
    private Technology tech;

    {
        session = HibernateSessionFactoryUtil.getSession();
    }

    public String update() {
        StringBuilder sb = new StringBuilder();
        Transaction transaction = session.beginTransaction();
        try {
            List<Technology> technologies = (List<Technology>) session.createQuery("From Technology").list();
            sb.append("{\"technologies\":[");

            for (Technology technology : technologies) {
                tech = technology;
                map.put("id", String.valueOf(tech.getId()));
                map.put("name", tech.getName());
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

    public String save(Technology technology, String name) {
        Transaction transaction = session.beginTransaction();
        List<Technology> technologies = (List<Technology>) session.createQuery("From Technology").list();

        for (Technology technology1 : technologies) {
            tech = technology1;

            if (name.equals(tech.getName())) {
                transaction.commit();
                return "{\"success\": false,\"message\": \"Данная технология уже зарегестрирована!\"}";
            }
        }

        session.save(technology);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Технология добавлена!\"}";
    }

    public String delete(String id) {

        //необходима проверка занятности в результирующей таблице!

        transaction = session.beginTransaction();
        Technology technology = session.load(Technology.class, Integer.parseInt(id));
        session.delete(technology);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Технология удалена!\"}";
    }

    public String updateData(String name, String id) {
        transaction = session.beginTransaction();
        Technology technology = (Technology) session.get(Technology.class, Integer.parseInt(id));
        technology.setName(name);
        session.update(technology);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Данные изменены!\"}";
    }
}
