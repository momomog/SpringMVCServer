package system.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import system.model.*;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static Session session;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSession() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Technology.class);
                configuration.addAnnotatedClass(Skill.class);
                configuration.addAnnotatedClass(LastUsed.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Personal.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    public static Session getCurrentSession() {
        if (session == null) {
            return getSession().openSession();
        }
        return getSession().getCurrentSession();
    }

    public static Session getNewSession() {
        return getSession().openSession();
    }
}
