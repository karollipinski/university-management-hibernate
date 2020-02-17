package pl.four.software.management.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.four.software.management.model.Student;
import pl.four.software.management.model.Teacher;
import pl.four.software.management.util.HibernateUtils;

import java.util.List;

public class TeacherService {

    SessionFactory sessionFactory;

    public TeacherService() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    // TODO: 11a Zaimplementować service TeacherService wraz z następującymi metodami:
    // findById, save, update, delete, printAllTeacherAndStudents, printTeacherAndStudents(int teacherId)

    // TODO: 11b Zaimplementuj metodę w StudentService:
    // printStudentAddTeachers(String studentId)

    // TODO: 11c Stworzyć test sprawdzający powyższe metod

    public Teacher findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Teacher teacher = (Teacher) session.get(Teacher.class, id);

        trx.commit();
        session.close();
        return teacher;
    }

    public int save(Teacher teacher) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        int id = (int) session.save(teacher);
        trx.commit();
        session.close();
        return id;
    }

    public void update(Teacher teacher) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        session.update(teacher);
        trx.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Teacher teacher = (Teacher) session.get(Teacher.class, id);
        session.delete(teacher);
        trx.commit();
        session.close();
    }

    public void printAllTeacherAndStudents() {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        String select = "SELECT t FROM Teacher t";
        Query query = session.createQuery(select);
        List<Teacher> teachers = query.list();
        for (Teacher teacher : teachers) {
            System.out.println("Teacher : " + teacher);
            List<Student> students = teacher.getStudents();
            System.out.println("Teacher students : " + students);
        }
        trx.commit();
        session.close();
    }

    public void printTeacherAndStudents(int teacherId) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Teacher teacher = (Teacher) session.get(Teacher.class, teacherId);
        System.out.println("Teacher : " + teacher);
        List<Student> students = teacher.getStudents();
        System.out.println("Teacher " + teacherId + " students : " + students);

        trx.commit();
        session.close();
    }

}
