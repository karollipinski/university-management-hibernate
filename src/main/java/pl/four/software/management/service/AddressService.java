package pl.four.software.management.service;

import org.hibernate.*;
import pl.four.software.management.model.Address;
import pl.four.software.management.util.HibernateUtils;

public class AddressService {

    // TODO: 4a Zaimplementować service wraz z następującymi metodami:
    // findById, create, update, delete

    // TODO: 4b Stworzyć test sprawdzający powyższe metody

    private SessionFactory sessionFactory;

    public AddressService() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    public Address findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Address address = (Address) session.get(Address.class, id);
        trx.commit();
        session.close();
        return address;
    }

    public int create(String street, String city, String country) {
        Address address = new Address(street, city, country);
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        int id = (int) session.save(address);
        trx.commit();
        session.close();
        return id;
    }

    public int save(Address address) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        int id = (int) session.save(address);
        trx.commit();
        session.close();
        return id;
    }

    public void update(Address address) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        session.update(address);
        trx.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Address student = (Address) session.get(Address.class, id);
        session.delete(student);
        trx.commit();
        session.close();
    }

    // TODO: 8a Stworzyć test sprawdzający powyższe metody

    // TODO: 9  Zaimplementuj metody w AddressService:
    // public void updateAddress(int id, String street, String country) {}

    // TODO: 9a Stworzyć test sprawdzający powyższe metody

    public void updateAddress(int id, String street, String country) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Address address = (Address) session.get(Address.class, id);
        address.setStreet(street);
        address.setCountry(country);

        //session.save(address);
        //nadmiarowe gdy w tej samej sesji pobieramy
        // Address i robimy set na jego polach

        trx.commit();
        session.close();
    }

    public void updateAddressHQL(int id, String street, String country) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        String query = "UPDATE Address SET street=:s1, country=:c1 " +
                       "WHERE id =:id";
        Query query1 = session.createQuery(query);
        query1.setString("s1", street);
        query1.setString("c1", country);
        query1.setInteger("id", id);
        query1.executeUpdate();

        trx.commit();
        session.close();
    }

    public void updateAddressSQL(int id, String street, String country) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        String query = "UPDATE address SET street=:s1, country=:c1 " +
                       "WHERE id =:id";
        SQLQuery sqlQuery = session.createSQLQuery(query);
        sqlQuery.setString("s1", street);
        sqlQuery.setString("c1", country);
        sqlQuery.setInteger("id", id);
        sqlQuery.executeUpdate();

        trx.commit();
        session.close();
    }

}
