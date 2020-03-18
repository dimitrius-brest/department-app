package by.derevitsky.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Model of "Department" entity
 * @author Dmitry Derevitsky
 */
@Entity
@Table(name = "departments")
//@Table(name = "DEPARTMENTS")
public class Department {

    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Name may not be blank")
    @Size(max=100)
    @Column(name = "name")
    private String name;

    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString(){
        return "id=" + this.id + "  name: " + this.name;
    }

}
