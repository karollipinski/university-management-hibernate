package pl.four.software.management.service;

import org.junit.Test;
import pl.four.software.management.model.Exam;
import pl.four.software.management.model.Student;

import java.util.Date;

public class ExamServiceTest {

    private ExamService examService = new ExamService();
    private StudentService studentService = new StudentService();

    @Test
    public void addAndPrintAllExamAndStudentsTest() {

        Exam exam = new Exam(new Date(), false);
        int savedExamId = examService.save(exam);

        Student student = new Student();
        student.setFirstName("Adam");
        student.setPesel("23242364234");
        student.setExam(exam);
        int s1Id = studentService.save(student);

        Student student1 = new Student();
        student1.setFirstName("Jan");
        student1.setPesel("2412134623");
        student1.setExam(exam);
        int s2Id = studentService.save(student1);

        examService.printAllExamAndStudents();

        studentService.delete(s1Id);
        studentService.delete(s2Id);
        examService.delete(savedExamId);
    }

}
