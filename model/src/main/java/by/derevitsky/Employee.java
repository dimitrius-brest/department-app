package by.derevitsky;

import java.util.Date;

public class Employee {
    private int id;
    private int id_department;
    private String first_name;
    private String middle_name;
    private String last_name;
    private Date birth_date; /* Формат даты - уточнить*/
    private int salary;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getId_department() { return id_department; }

    public void setId_department(int id_department) { this.id_department = id_department; }

    public String getFirst_name() { return first_name; }

    public void setFirst_name(String first_name) { this.first_name = first_name; }

    public String getMiddle_name() { return middle_name; }

    public void setMiddle_name(String middle_name) { this.middle_name = middle_name; }

    public String getLast_name() { return last_name; }

    public void setLast_name(String last_name) { this.last_name = last_name; }

    public Date getBirth_date() { return birth_date; }

    public void setBirth_date(Date birth_date) { this.birth_date = birth_date; }

    public int getSalary() { return salary; }

    public void setSalary(int salary) { this.salary = salary; }

}
