package system.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import system.model.LastUsed;
import system.util.HibernateSessionFactoryUtil;

import java.util.*;

@Repository
@SuppressWarnings("all")
public class LastUsedDao {
    private Session session;
    private Transaction transaction;
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, String> map = new HashMap<>();
    private LastUsed lastUsed;

    {
        session = HibernateSessionFactoryUtil.getCurrentSession();
    }

    public String update() {
        StringBuilder sb = new StringBuilder();
        Transaction transaction = session.beginTransaction();
        try {
            List<LastUsed> lastUseds = (List<LastUsed>) session.createQuery("From LastUsed").list();
            sb.append("{\"useds\":[");

            for (LastUsed interval : lastUseds) {
                lastUsed = interval;
                map.put("id", String.valueOf(lastUsed.getId()));
                map.put("name", lastUsed.getName());
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

    public String save(LastUsed time) {
        Transaction transaction = session.beginTransaction();
        List<LastUsed> lastUseds = (List<LastUsed>) session.createQuery("From LastUsed").list();

        for (LastUsed interval : lastUseds) {
            lastUsed = interval;

            if (time.getName().equals(lastUsed.getName())) {
                transaction.rollback();
                return "{\"success\": false,\"message\": \"Данный интервал уже зарегестрирован!\"}";
            }
        }

        session.save(time);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Интервал добавлен!\"}";
    }

    public String delete(String id) {
        try {
            transaction = session.beginTransaction();
            LastUsed lUsed = session.load(LastUsed.class, Integer.parseInt(id));
            session.delete(lUsed);
            transaction.commit();
            return "{\"success\": true,\"message\": \"Интервал удален!\"}";
        } catch (Exception e) {
            transaction.rollback();
            return "{\"success\": false,\"message\": \"Вы не можете удалить данный интервал, так как он используется в таблице знаний сотрудников!\"}";
        }
    }

    public String updateData(String name, String id) {
        transaction = session.beginTransaction();
        LastUsed lUsed = session.get(LastUsed.class, Integer.parseInt(id));
        lUsed.setName(name);
        session.update(lUsed);
        transaction.commit();
        return "{\"success\": true,\"message\": \"Данные изменены!\"}";
    }

}
