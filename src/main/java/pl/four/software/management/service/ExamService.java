package pl.four.software.management.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.four.software.management.model.Exam;
import pl.four.software.management.model.Student;
import pl.four.software.management.util.HibernateUtils;

import java.util.List;

public class ExamService {

    SessionFactory sessionFactory;

    public ExamService() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    // TODO: 10a Zaimplementować service ExamService wraz z następującymi metodami:
    // findById, save, update, delete, printAllExamAndStudents,

    // TODO: 10b Zaimplementuj metodę w StudentService:
    //printStudentAndExam(int studentId)

    // TODO: 10c Stworzyć test sprawdzający powyższe metody

    public Exam findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Exam exam = (Exam) session.get(Exam.class, id);

        trx.commit();
        session.close();
        return exam;
    }

    public int save(Exam exam) {

        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        int id = (int) session.save(exam);
        trx.commit();
        session.close();
        return id;
    }

    public void update(Exam exam) {

        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        session.update(exam);
        trx.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        Exam exam = (Exam) session.get(Exam.class, id);
        session.delete(exam);
        trx.commit();
        session.close();
    }

    public void printAllExamAndStudents() {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();

        String select = "SELECT e FROM Exam e";
        Query query = session.createQuery(select);
        List<Exam> exams = query.list();
        for (Exam exam : exams) {
            System.out.println("Exam : " + exam);
            List<Student> students = exam.getStudents();
            System.out.println("Exam students : " + students);
        }
        trx.commit();
        session.close();

    }

}
