package pl.four.software.management.model;

import javax.persistence.*;

@Entity
@Table
public class University {

    // TODO: 5 Zaimplementować Encję University(id, name, country),
    // oddać odpowiednie adnotacje oraz relację 1:* z encją Student
    // Encja Student użyć adnotacji  @ManyToOne oraz @JoinColumn
    // Należy pamiętać o dodaniu mapping'u w pliku hibernate.cfg.xml

    // TODO: 5a Zaimplementować service wraz z następującymi metodami:
    // UniversityService (findById, findByCountry, create, update, delete)

    // TODO: 5b Stworzyć test sprawdzający powyższe metody

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String country;

    public University() {

    }

    public University(String name, String country) {
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "University [id=" + id + ", name=" + name + ", country=" + country + "]";
    }

}
