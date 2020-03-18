package by.derevitsky.dao;

import by.derevitsky.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("JpaEmployeeDAO")
@Profile("jpa")
public class EmployeeDAOJpa implements EmployeeDAO {
    // Stub
//    List<Employee> employees = new ArrayList<Employee>(
//        Arrays.asList(
//            new Employee(1, 1, "Alexandr", "Sergeevich", "Pushkin",
//                    LocalDate.parse("2020-02-02"), 500),
//            new Employee(2, 2, "Mihail", "Yurjevich", "Lermontov",
//                    LocalDate.parse("2020-02-02"), 1000),
//            new Employee(3, 2, "Lev", "Nikolaevich", "Tolstoy",
//                    LocalDate.parse("2020-02-02"), 1500)
//        )
//    );

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public List<Employee> getAll() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            List allEmployees = em
                    .createQuery("select e from Employee e order by e.id")
                    .getResultList();
        tx.commit();
        em.close();
        return allEmployees;
    }

    @Override
    public Employee getById(int id) {
        EntityManager em = emf.createEntityManager();
            Employee searchedEmployee = em.find(Employee.class, id);
        em.close();

//        Employee searchedEmployee = new Employee();
//        for (Employee employee : employees) {
//            if (id == employee.getId()) {
//                searchedEmployee = employee;
//                break;
//            }
//        }
        return searchedEmployee;
    }

    @Override
    public List<Employee> getByDepartmentId(int departmentId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            List<Employee> searchedEmployees = em
                    .createQuery("select e from Employee e where e.idDepartment = " + departmentId)
                    .getResultList();
        tx.commit();
        em.close();

//        List<Employee> searchedEmployees = new ArrayList<Employee>();
//        for (Employee employee : employees) {
//            if (employee.getIdDepartment() == departmentId) {
//                searchedEmployees.add(employee);
//            }
//        }
        return searchedEmployees;
    }

    @Override
    public void insert(Employee employee) {
        // employees.add(employee);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            em.persist(employee);
        tx.commit();
        em.close();
    }

    @Override
    public void update(Employee employee) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
            Employee updatedEmployee = em.find(Employee.class, employee.getId());
        tx.begin();
            em.persist(updatedEmployee);
            updatedEmployee.setIdDepartment(employee.getIdDepartment());
            updatedEmployee.setFirstName(   employee.getFirstName());
            updatedEmployee.setMiddleName(  employee.getMiddleName());
            updatedEmployee.setLastName(    employee.getLastName());
            updatedEmployee.setBirthDate(   employee.getBirthDate());
            updatedEmployee.setSalary(      employee.getSalary());
        tx.commit();
        em.close();

//        for (Employee updatedEmployee : employees) {
//            if (updatedEmployee.getId() == employee.getId()) {
//                updatedEmployee.setIdDepartment(employee.getIdDepartment());
//                updatedEmployee.setFirstName(   employee.getFirstName());
//                updatedEmployee.setMiddleName(  employee.getMiddleName());
//                updatedEmployee.setLastName(    employee.getLastName());
//                updatedEmployee.setBirthDate(   employee.getBirthDate());
//                updatedEmployee.setSalary(      employee.getSalary());
//                break;
//            }
//        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
            Employee employee = em.find(Employee.class, id);
        tx.begin();
            em.remove(employee);
        tx.commit();
        em.close();

//        for (Employee employee : employees) {
//            if (employee.getId() == id) {
//                employees.remove(employee);
//                break;
//            }
//        }
    }
}
