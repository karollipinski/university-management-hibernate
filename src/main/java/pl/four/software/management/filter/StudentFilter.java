package pl.four.software.management.filter;

public class StudentFilter {

    private String firstName;

    private String lastName;

    private String pesel;

    public StudentFilter() {
    }

    public StudentFilter(String firstName) {
        this.firstName = firstName;
    }

    public StudentFilter(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentFilter(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
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

    @Override
    public String toString() {
        return "StudentFilter{" +
               ", firstName='" + firstName +
               ", lastName='" + lastName +
               ", pesel='" + pesel +
               '}';
    }
}
