package pl.four.software.management.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO: 11 Zaimplementować Encję Teacher(id, firstName, lastName, e-mail, phone),
// oddaj odpowiednie adnotacje oraz relację Student <-> Teacher *:* (dwukierunkowe) z encją Student

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    @ManyToMany(mappedBy = "teachers")
    private List<Student> students = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Teacher{" +
               "id=" + id +
               ", firstName='" + firstName +
               ", lastName='" + lastName +
               ", email='" + email +
               ", phone='" + phone +
               '}';
    }
}
