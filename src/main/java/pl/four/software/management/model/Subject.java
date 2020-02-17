package pl.four.software.management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Subject {

    // TODO: 6 Zaimplementować Encję Subject(id, name, description),
    // oddać odpowiednie adnotacje oraz relację *:* z encją Student
    // Encja Student użyć adnotacji @ManyToMany oraz @JoinTable
    // Należy pamiętać o dodaniu mapping'u w pliku hibernate.cfg.xml

    // TODO: 6a Zaimplementować service wraz z następującymi metodami:
    // SubjectService (findById, findAllByStudentSection, create, update, delete)

    // TODO: 6b Stworzyć test sprawdzający powyższe metody

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    public Subject() {
    }

    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Subject{" +
               "id=" + id +
               ", name='" + name +
               ", description='" + description +
               '}';
    }
}
