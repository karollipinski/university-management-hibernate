package pl.four.software.management.service;

import org.junit.Assert;
import org.junit.Test;
import pl.four.software.management.model.Address;
import pl.four.software.management.model.University;

import java.util.List;

public class UniversityServiceTest {

    private static UniversityService universityService = new UniversityService();

    @Test
    public void verifyUniversityService() {

//        Student student1 = new Student("Sam", "Disilva","23453456788", "Maths" );
//        Student student2 = new Student("Joshua", "Brill","67853456788", "Science");
//        Student student3 = new Student("Peter", "Pan", "87654556788","Physics");

        Address address = new Address("Kolorowa", "Warszawa", "Polska");
        University university1 = new University("CAMBRIDGE", "ENGLAND");
        University university2 = new University("POLITECHNIKA LUBELSKA", "POLSKA");
        University university3 = new University("UMCS", "POLSKA");
        int save1 = universityService.save(university1);
        int save2 = universityService.save(university2);
        int save3 = universityService.save(university3);

        University university4 = universityService.findById(save3);

        university4.setName("KUL");
        universityService.update(university4);

        university4 = universityService.findById(save3);
        Assert.assertEquals("KUL", university4.getName());

        List<University> universities = universityService.findByCountry("POLSKA");

        universities.forEach(System.out::println);

        universityService.delete(save1);
        universityService.delete(save2);
        universityService.delete(save3);
    }

}
