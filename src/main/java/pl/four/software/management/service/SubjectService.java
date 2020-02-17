package pl.four.software.management.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.four.software.management.model.Student;
import pl.four.software.management.model.Subject;
import pl.four.software.management.util.HibernateUtils;

import java.util.List;

public class SubjectService {

    private SessionFactory sessionFactory;

    public SubjectService() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    public Subject findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Subject subject = (Subject) session.get(Subject.class, id);
        trx.commit();
        session.close();
        return subject;
    }

    public List<Subject> findAllByStudentSection(String section) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        String hql = "SELECT DISTINCT sb FROM Subject sb, Student st " +
                     "WHERE st.section=:s1";
        Query query = session.createQuery(hql);
        query.setParameter("s1", section);
        List<Subject> subjects = query.list();
        trx.commit();
        session.close();
        return subjects;
    }

    public int create(String name, String description) {
        Subject subject = new Subject(name, description);

        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        int id = (int) session.save(subject);
        trx.commit();
        session.close();
        return id;
    }

    public void update(Subject subject) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.update(subject);
        trx.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Subject subject = (Subject) session.get(Subject.class, id);
        session.delete(subject);

        trx.commit();
        session.close();
    }

    // TODO: 8  Zaimplementuj metody w SubjectService:
    //    public void addStudents(Subject subject, List<Student> students) {
    //    public void printStudentsAndSubject(String pesel) {}

    // TODO: 8a Stworzyć test sprawdzający powyższe metody

    public void addStudents(Subject subject, List<Student> students) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        for (Student student : students) {
            student.getSubjects()
                   .add(subject);
            session.persist(student);
        }

        trx.commit();
        session.close();
    }

    public void printStudentsAndSubject(String pesel) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        Query query = session.createQuery("SELECT s FROM Student s WHERE s.pesel=:pesel");
        query.setParameter("pesel", pesel);
        List<Student> students = query.list();

        for (Student student : students) {
            System.out.println("Pobrany student: " + student);
            System.out.println("Subject pobranego studenta: " + student.getSubjects());
        }

        trx.commit();
        session.close();
    }

}
