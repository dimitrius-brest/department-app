package by.derevitsky.dao;

import by.derevitsky.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("JpaDepartmentDAO")
@Profile("jpa")
public class DepartmentDAOImplJpa implements DepartmentDAO {
    // Stub
    List<Department> departments = new ArrayList<Department>(
        Arrays.asList(
            new Department(1, "Stub Department 1"),
            new Department(2, "Stub Department 2")
        )
    );

    //@PersistenceContext
    //private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public List<Department> getAll() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            List allDepartments = em
                    .createQuery("select d from Department d order by d.id")
                    .getResultList();
        tx.commit();
        em.close();

        return allDepartments;
    }

    @Override
    public Department getById(int id) {
        EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();
        //tx.begin();
            Department searchedDepartment = em.find(Department.class, id);
        //tx.commit();
        em.close();

        //----------------
//        Department searchedDepartment = new Department(0, "Department not found");
//        for (Department department : departments){
//            if (id == department.getId()) {
//                searchedDepartment.setId(id);
//                searchedDepartment.setName(department.getName());
//                break;
//            }
//        }
        return searchedDepartment;
    }

    @Override
    public void insert(Department department) {
        //departments.add(department);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            em.persist(department);
        tx.commit();
        em.close();
    }

    @Override
    public void update(Department department) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            Department updatedDepartment = em.find(Department.class, department.getId());
            // TODO make update of Department
        tx.commit();
        em.close();

//        for (Department updatedDepartment : departments){
//            if (updatedDepartment.getId() == department.getId()) {
//                updatedDepartment.setName(department.getName());
//                break;
//            }
//        }

    }

    @Override
    public void delete(int id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            Department department = em.find(Department.class, id);
            em.remove(department);
        tx.commit();
        em.close();

//        for (Department department : departments){
//            if (id == department.getId()) {
//                departments.remove(department);
//                break;
//            }
//        }

    }
}
