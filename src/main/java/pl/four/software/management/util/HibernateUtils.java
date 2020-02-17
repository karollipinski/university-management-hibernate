package pl.four.software.management.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    // TODO: 0. Utworzyć bazę dla projektu na MySql
    //           np. university-managment

    // TODO: 1. Skonfigurować w hibernate.cfg.xml połączenie do utworzonej bazy danych

    // TODO: 2. Zaimplementować wczytanie konfiguracji z pliku hibernate.cfg.xml SessionFactory

    private static final SessionFactory sessionFactory;

    static {

        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.err.println("Session Factory could not be created.\n" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
