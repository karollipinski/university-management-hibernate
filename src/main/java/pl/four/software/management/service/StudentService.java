package pl.four.software.management.service;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import pl.four.software.management.filter.StudentFilter;
import pl.four.software.management.model.*;
import pl.four.software.management.util.HibernateUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StudentService {

    // TODO: 3a Zaimplementować service wraz z następującymi metodami:
    //findById, findAll, findById, findByPESEL, create, update, saveOrUpdate, delete

    // TODO: 3b Stworzyć test sprawdzający powyższe metody

    private SessionFactory sessionFactory;

    public StudentService() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Query query = session.createQuery("SELECT s FROM Student s Order by s.lastName DESC");
        List<Student> students = query.list();

        trx.commit();
        session.close();
        return students;
    }

    public Student findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Student student = (Student) session.get(Student.class, id);
        trx.commit();
        session.close();
        return student;
    }

    public Student findByPesel(String pesel) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Query query = session.createQuery("SELECT s FROM Student s WHERE s.pesel=:pesel");
        query.setParameter("pesel", pesel);
        List<Student> students = query.list();
        trx.commit();
        session.close();
        return students.isEmpty() ? null : students.get(0);
    }

    public int create(String firstName, String lastName, String pesel, String section) {
        Student student = new Student(firstName, lastName, pesel, section);
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        int id = (int) session.save(student);
        trx.commit();
        session.close();
        return id;

    }

    public int save(Student student) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        int id = (int) session.save(student);
        trx.commit();
        session.close();
        return id;
    }

    public void update(Student student) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        session.update(student);
        trx.commit();
        session.close();
    }

    public void saveOrUpdate(Student student) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        session.saveOrUpdate(student);
        trx.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Student student = (Student) session.get(Student.class, id);
        session.delete(student);
        trx.commit();
        session.close();
    }

    // TODO: 7 Zaimplementuj metody w StudentService:
    //    public List<Student> findAllByAddressCountry(String country) {}
    //    public List<Student> findByUniversityName(String name) {}
    //    public int saveStudentAndAddress(Student student, Address address) {}
    //    public void addUniversity(int id, University university) {}
    //    public void printAllStudentsAndAddress() {}
    //    public void printStudentsAndUniversity(String firstName) { }

    // TODO: 7a Stworzyć test sprawdzający powyższe metody

    public List<Student> findAllByAddressCountry(String country) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        //String query = "Select s FROM Student s, Address a " +
        //          "WHERE a.country =:c1 AND s.address.id=a.id";

        String query2 = "SELECT s FROM Student s " +
                        "INNER JOIN s.address a WHERE " +
                        " a.country =:c1";

        Query query = session.createQuery(query2);
        query.setParameter("c1", country);
        List<Student> students = query.list();
        trx.commit();
        session.close();
        return students;
    }

    public List<Student> findByUniversityName(String name) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        String query0 = "SELECT s FROM Student s, University u " +
                        "WHERE s.university.id = u.id AND u.name =:b";

//      String query = "Select s from Student s INNER JOIN " +
//                       "s.university u WHERE u.name = :b";

        Query query = session.createQuery(query0);
        query.setParameter("b", name);
        List<Student> students = query.list();
        trx.commit();
        session.close();
        return students;
    }

    public List<Student> findByUniversityNameV2(String name) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        // pobranie encji Student wraz z relacjami typu EAGER (@OneToOne,@ManyToOne)
        // Query query = session.createQuery("SELECT s FROM Student s, University u
        // WHERE u.name=:name");

        // chcę pobrać tylko encję student bez relacji wówczas mam tylko 1 zapytanie na bazie danych te które napisaliśmy
        // bez zachłannego dociąganie relacji co jest kosztowne gdy tych wszystkcih danych nie potrzebujemy
        String select = "SELECT new  Student(s.id, s.firstName, s.lastName, s.pesel, s.section) "
                        + "FROM Student s, University u WHERE u.name=:name";
        Query query = session.createQuery(select);
        query.setParameter("name", name);
        List<Student> students = query.list();

        trx.commit();
        session.close();
        return students.isEmpty() ? Collections.emptyList() : students;
    }

    public int saveStudentAndAddress(Student student, Address address) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        session.persist(address);
        student.setAddress(address);
        session.persist(student);

        trx.commit();
        session.close();
        return student.getId();
    }

    public void addUniversity(int studentId, University university) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Student student = (Student) session.get(Student.class, studentId);
        session.saveOrUpdate(university);
        student.setUniversity(university);

        //session.update(student);
        //nadmiarowe gdy w tej samej sesji pobieramy
        // Studenta i robimy set na jego polach

        trx.commit();
        session.close();
    }

    public void printAllStudentsAndAddress() {
        List<Student> students = findAll();
        for (Student student : students) {
            System.out.println(student);
            System.out.println(student.getAddress());
        }
    }

    public void printStudentsAndUniversity(String firstName) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        String query = "Select s From Student s where s.firstName=:fn and s.university is NOT NULL";
        Query sessionQuery = session.createQuery(query);
        sessionQuery.setParameter("fn", firstName);
        List<Student> students = sessionQuery.list();
        trx.commit();
        session.close();

        for (Student student : students) {
            System.out.println(student);
            System.out.println(student.getUniversity());
        }
    }

    // TODO: 10b Zaimplementuj metodę w StudentService:
    // printStudentAddExam(int studentId)
    public void printStudentAddExam(int studentId) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Student student = (Student) session.get(Student.class, studentId);
        System.out.println("Student : " + student);
        Exam exam = student.getExam();
        System.out.println("Student exam : " + exam);

        trx.commit();
        session.close();
    }

    // TODO: 11b Zaimplementuj metodę w StudentService:
    // printStudentAddTeachers(String studentId)

    public void printStudentAddTeachers(String studentId) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Student student = (Student) session.get(Student.class, studentId);
        System.out.println("Student : " + student);
        List<Teacher> teachers = student.getTeachers();
        System.out.println("Student teachers : " + teachers);

        trx.commit();
        session.close();
    }

    // TODO: 13 Filtrowanie po zadanym kryterium klasa StudentFilter (firstName, lastName, pesel) w filtrze może być podane 0 -3 parametrów:
    // - lub StudentFilter filter = new StudentFilter("Anna")
    // - lub StudentFilter filter = new StudentFilter("Anna", "Kowalska");
    // - lub StudentFilter filter = new StudentFilter("86121204567");
    // - lub StudentFilter filter = new StudentFilter("Anna", "Kowalska", "86121204567");
    // - lub StudentFilter filter = new StudentFilter();

    //    public List<Student> findByFilter(StudentFilter filter){
    //
    //        // TODO: zaimplementuj mnie przy pomocy Criteria
    //        return Collections.emptyList();
    //    }

    public List<Student> findByFilter(StudentFilter filter) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Student.class);

        if (Objects.nonNull(filter.getFirstName()) && !filter.getFirstName()
                                                             .isEmpty()) {
            criteria.add(Restrictions.eq("firstName", filter.getFirstName()));
        }
        if (Objects.nonNull(filter.getLastName()) && !filter.getLastName()
                                                            .isEmpty()) {
            criteria.add(Restrictions.eq("lastName", filter.getLastName()));
        }
        if (Objects.nonNull(filter.getPesel()) && !filter.getPesel()
                                                         .isEmpty()) {
            criteria.add(Restrictions.eq("pesel", filter.getPesel()));
        }

        List<Student> students = criteria.list();
        session.close();
        return students;
    }

}