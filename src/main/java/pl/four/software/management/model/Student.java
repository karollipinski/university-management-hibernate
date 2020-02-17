package pl.four.software.management.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_db")
public class Student {

    //TODO: 3 Zaimplementować Encję Student(id, firstName, lastName, section, pesel),
    // dodać odpowiednie adnotacje
    // Należy pamiętać o dodaniu mapping'u w pliku hibernate.cfg.xml

    // TODO: 3a Zaimplementować service wraz z następującymi metodami:
    // findById, findAll, findById, findByPESEL, create, update, delete, findAllByAddressCountry, findAllByUniversityName

    // TODO: 3b Stworzyć test sprawdzający powyższe metody

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String pesel;

    private String section;

    @OneToOne()
    @JoinColumn(name = "home_address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_subject")
    private List<Subject> subjects = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_teacher")
    private List<Teacher> teachers = new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String pesel, String section) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.section = section;
    }

    public Student(String firstName, String lastName, String pesel, String section, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.section = section;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Student{" +
               "id=" + id +
               ", firstName='" + firstName +
               ", lastName='" + lastName +
               ", pesel='" + pesel +
               ", section='" + section +
               '}';
    }
}
