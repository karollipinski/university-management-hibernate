package pl.four.software.management.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.four.software.management.model.University;
import pl.four.software.management.util.HibernateUtils;

import java.util.List;

public class UniversityService {

    // TODO: 5a Zaimplementować service wraz z następującymi metodami:
    // UniversityService (findById, findByCountry, create, update, delete)

    // TODO: 5b Stworzyć test sprawdzający powyższe metody

    private SessionFactory sessionFactory;

    public UniversityService() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    public University findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        University university = (University) session.get(University.class, id);
        trx.commit();
        session.close();
        return university;
    }

    public List<University> findByCountry(String country) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Query query = session.createQuery("FROM University WHERE country= :country");
        query.setParameter("country", country);
        List<University> universities = query.list();
        trx.commit();
        session.close();
        return universities;
    }

    public int create(String name, String country) {
        University university = new University(name, country);
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        int id = (int) session.save(university);
        trx.commit();
        session.close();
        return id;
    }

    public int save(University university) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        int id = (int) session.save(university);
        trx.commit();
        session.close();
        return id;
    }

    public void update(University university) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        session.update(university);
        trx.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM University WHERE id= :id");
        query.setInteger("id", id);
        query.executeUpdate();
        trx.commit();
        session.close();
    }
}
