package ar.com.kache.model;

/**
 * Created by sperruolo on 7/24/16.
 */
public class Employee {
    public final String id;
    public final String firstName;
    public final String lastName;
    public final String location;

    public Employee(String id, String firstName, String lastName, String location) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "(" + id + ")" + location;
    }

    public static class EmployeeBuilder {
        private String id;
        private String firstName;
        private String lastName;
        private String location;

        public EmployeeBuilder(String id) {
            this.id = id;
        }

        public EmployeeBuilder firstName(String newFirstName) {
            this.firstName = newFirstName;
            return this;
        }

        public EmployeeBuilder lastName(String newLastName) {
            this.lastName = newLastName;
            return this;
        }

        public EmployeeBuilder location(String newLocation) {
            this.location = newLocation;
            return this;
        }

        public Employee build() {
            return new Employee(id, firstName, lastName, location);
        }
    }
}
