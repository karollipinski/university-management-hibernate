package pl.four.software.management.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.four.software.management.model.Student;
import pl.four.software.management.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectServiceTest {

    private static SubjectService subjectService = new SubjectService();

    private static StudentService studentService = new StudentService();

    private static int javaId;
    private static int sqlId;

    @BeforeClass
    public static void przygotowanieDanych() {
        javaId = subjectService.create("Java", "Programowanie w Javie");
        sqlId = subjectService.create("SQL", "Programowanie SQL");
    }

    @Test
    public void verifySubjectService() {

        Subject subjectJava = subjectService.findById(javaId);
        System.out.println(subjectJava);

        Subject subjectSql = subjectService.findById(sqlId);
        System.out.println(subjectSql);

        Student student1 = new Student();
        student1.setFirstName("Adam");
        student1.setPesel("63426757654");
        student1.setSection("IT");
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subjectJava);
        subjects.add(subjectSql);
        student1.setSubjects(subjects);
        int studentId = studentService.save(student1);
        System.out.println("Zapisany student id: " + studentId);

        int student2Id = studentService.create("Jan", "Kowalski", "85675674563", "Rachunkowość");
        System.out.println("Dodano studenta2 id:" + student2Id);
        Student student2 = studentService.findById(student2Id);
        student2.setSubjects(subjects);
        studentService.update(student2);

        System.out.println("Szukamy po section = Rachunkowość");
        List<Subject> subject2List = subjectService.findAllByStudentSection("Rachunkowość");
        subject2List.forEach(System.out::println);

        System.out.println("Szukamy po section = IT");
        List<Subject> subjectList = subjectService.findAllByStudentSection("IT");
        subjectList.forEach(System.out::println);

        studentService.delete(studentId);
        studentService.delete(student2Id);
    }

    @Test
    public void testAddStudents() {

        Subject subject = new Subject("Java", "Programowanie obiektowe");
        List<Student> students = new ArrayList<>();
        students.add(new Student("Anna", "Kowalska", "2523525", "IT"));
        students.add(new Student("Tomasz", "Kowalski", "75675567", "IT"));

        subjectService.addStudents(subject, students);

    }

    @AfterClass
    public static void czyszczenieDanych() {
        subjectService.delete(javaId);
        subjectService.delete(sqlId);
    }

}
