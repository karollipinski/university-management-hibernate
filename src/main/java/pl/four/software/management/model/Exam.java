package pl.four.software.management.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// TODO: 10 Zaimplementować Encję Exam(id, date, (boolean) passed),
// oddaj odpowiednie adnotacje oraz relację Student <-> Exam *:1 (dwukierunkowe) z encją Student

@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue
    private int id;

    private Date date;

    private boolean passed;

    @OneToMany(mappedBy = "exam")
    private List<Student> students;

    public Exam() {
    }

    public Exam(Date date, boolean passed) {
        this.date = date;
        this.passed = passed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Exam{" +
               "id=" + id +
               ", date=" + date +
               ", passed=" + passed +
               '}';
    }
}
