package system.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import system.model.Technology;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static Session session;

    private HibernateSessionFactoryUtil() {
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            try {
                if (session != null) {
                    return sessionFactory.getCurrentSession();
                }
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Technology.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                session = sessionFactory.openSession();

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return session;
    }
}
