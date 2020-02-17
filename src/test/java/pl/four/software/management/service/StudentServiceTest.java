package pl.four.software.management.service;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.four.software.management.filter.StudentFilter;
import pl.four.software.management.model.Address;
import pl.four.software.management.model.Student;
import pl.four.software.management.model.University;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudentServiceTest {

    private static final String PESEL0 = "76655444333";
    private static final String PESEL1 = "09655442233";

    private static StudentService studentService = new StudentService();

    private static AddressService addressService = new AddressService();

    private static UniversityService universityService = new UniversityService();

    // Run once, e.g. Database connection, connection pool
    @BeforeClass
    public static void runOnceBeforeClass() {
        System.out.println("@BeforeClass - runOnceBeforeClass");

        if (Objects.isNull(studentService.findByPesel(PESEL0))) {
            int id = studentService.create("Jan", "Kowalski", PESEL0, "IT");
            System.out.println("Dodanie studenta o id: " + id);
        }

        if (Objects.isNull(studentService.findByPesel(PESEL1))) {
            Student student = new Student();
            student.setFirstName("Anna");
            student.setLastName("Nowak");
            student.setPesel(PESEL1);
            student.setSection("Mechanika");
            studentService.save(student);
        }
    }

    @Test
    public void findAll() {
        List<Student> students = studentService.findAll();

        students.forEach(s -> System.out.println(s));
    }

    @Test
    public void saveAndFindById() {
        String firstName = "Karol";
        String lastName = "Lipiński";
        String pesel = "98101096939";
        String section = "IT";
        int id = studentService.create(firstName, lastName, pesel, section);

        Student student = studentService.findById(id);

        Assert.assertEquals(firstName, student.getFirstName());
        Assert.assertEquals(lastName, student.getLastName());
        Assert.assertEquals(pesel, student.getPesel());
        Assert.assertEquals(section, student.getSection());

        student.setSection("Rachunkowość");
        studentService.update(student);

        student = studentService.findById(id);
        Assert.assertEquals("Rachunkowość", student.getSection());

        Address address = new Address("Racławickie", "Lublin", "Polska");
        addressService.save(address);
        student.setAddress(address);
        studentService.update(student);

        List<Student> studentsFromPolska = studentService.findAllByAddressCountry("Polska");
        System.out.println("Studenci z Polski");
        studentsFromPolska.forEach(System.out::println);

        University university = new University("Politechnika Lubelska", "Polska");
        universityService.save(university);
        student.setUniversity(university);
        studentService.update(student);

        List<Student> politechnikaLubelska = studentService.findByUniversityName("Politechnika Lubelska");
        System.out.println("Studenci z Politechniki Lubelskiej");
        politechnikaLubelska.forEach(System.out::println);

        System.out.println("Method printStudentsAndUniversity");
        studentService.printStudentsAndUniversity("Karol");

        studentService.delete(student.getId());
    }

    @Test
    public void testSaveStudentAndAddress() {

        Student student = new Student("Adam", "Nowak", "346435734637", "IT");
        Address address = new Address("Polna", "Lublin", "Polska");

        int studentId = studentService.saveStudentAndAddress(student, address);

        Student savedStudent = studentService.findById(studentId);
        System.out.println(savedStudent);
        System.out.println(savedStudent.getAddress());

        int addressId = savedStudent.getAddress()
                                    .getId();

        System.out.println("Method printAllStudentsAndAddress");
        studentService.printAllStudentsAndAddress();

        studentService.delete(studentId);
        addressService.delete(addressId);
    }

    @Test
    public void testStudentFindByFilter() {

        StudentFilter filter = new StudentFilter("Anna");
        List<Student> byFilter = studentService.findByFilter(filter);
        System.out.println("Find StudentFilter(\"Anna\") : " + byFilter);

        filter = new StudentFilter("Anna", "Kowalska");
        byFilter = studentService.findByFilter(filter);
        System.out.println("Find StudentFilter(\"Anna\") : " + byFilter);

        filter = new StudentFilter();
        filter.setPesel("86121204567");
        byFilter = studentService.findByFilter(filter);
        System.out.println("Find StudentFilter(\"Anna\") : " + byFilter);

        filter = new StudentFilter("Anna", "Kowalska", "86121204567");
        byFilter = studentService.findByFilter(filter);
        System.out.println("Find StudentFilter(\"Anna\") : " + byFilter);

        filter = new StudentFilter();
        byFilter = studentService.findByFilter(filter);
        System.out.println("Find StudentFilter(\"Anna\") : " + byFilter);

    }

    // Run once, e.g close connection, cleanup
    @AfterClass
    public static void runOnceAfterClass() {
        System.out.println("@AfterClass - runOnceAfterClass");
        Optional.ofNullable(studentService.findByPesel(PESEL0))
                .ifPresent(s -> studentService.delete(s.getId()));

        Optional.ofNullable(studentService.findByPesel(PESEL1))
                .ifPresent(s -> studentService.delete(s.getId()));

    }

}
