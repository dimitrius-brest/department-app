package by.derevitsky;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws SQLException, ParseException {

//        // Контекст Spring
//        ApplicationContext context = new ClassPathXmlApplicationContext("dao_context.xml");
//        // Получаем из контекста departmentDAO
//        DepartmentDAO departmentDAO = (DepartmentDAO) context.getBean("h2DepDAO");
//
//
//        // Проверяем метод getAll  (departments)
//        System.out.println("Таблица DEPARTMENTS:");
//        List<Department> departments = departmentDAO.getAll();
//        for (Department department : departments) {
//            System.out.println("id: " + department.getId() + ", name: " + department.getName());
//        }
//        System.out.println();
//
//        // Проверяем метод getById  (departments)
//        int test_id = 2;
//        System.out.println("Извлекаем Department с id=" + test_id);
//        Department department2 = departmentDAO.getById(test_id);
//        System.out.println("id: " + department2.getId() + ", name: " + department2.getName());
//
//        // Проверяем метод delete  -------------  доработать - есть связанные записи в Employees
// /*       departmentDAO.delete(test_id);
//        System.out.println("Удалили....");
// */
//
//        // Проверяем метод insert  (departments)
//        departmentDAO.insert("Workers");
//        System.out.println("Inserting new item...");
//        System.out.println();
//
//        // Проверяем метод update  (departments)
//        test_id = 2;
//        departmentDAO.update(test_id, "Cool Workers");
//        System.out.println("Updated item with id = " + test_id);
//        System.out.println();
//
//        // Снова выводим список
//        departments = departmentDAO.getAll();
//        for (Department department : departments) {
//            System.out.println("id: " + department.getId() + ", name: " + department.getName());
//        }
//        System.out.println();
//
//
//        System.out.println();
//        System.out.println("=======================================================");
//        // Получаем из контекста employeeDAO
//        EmployeeDAO employeeDAO = (EmployeeDAO) context.getBean("stubEmpDAO");
//
//        // Проверяем метод getAll  (employees)
//        System.out.println("Таблица EMPLOYEES:");
//        List<Employee> employees = employeeDAO.getAll();
//        for (Employee employee : employees) {
//            System.out.println(
//                employee.getId() + " " + employee.getId_department() + " " +
//                employee.getFirst_name() + " " + employee.getMiddle_name() + " " + employee.getLast_name() + " " +
//                employee.getBirth_date() + " " + employee.getSalary());
//        }
//        System.out.println();
//
//        // Проверяем метод getById  (employees)
//        test_id = 1;
//        System.out.println("Извлекаем Employee с id=" + test_id);
//        Employee employee = employeeDAO.getById(test_id);
//        System.out.println(
//                employee.getId() + " " + employee.getId_department() + " " +
//                employee.getFirst_name() + " " + employee.getMiddle_name() + " " + employee.getLast_name() + " " +
//                employee.getBirth_date() + " " + employee.getSalary());
//        System.out.println();
//
//        // Проверяем метод insert  (employees)
//        System.out.println("Вставляем ещё одного Employee");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        employeeDAO.insert(1, "Vladimir", "Vladimirovich", "Putin",
//                formatter.parse("2000-01-01"), 500);
//        System.out.println();
//
//        test_id = 5;
//        // Проверяем метод update   (employee)
//        System.out.println("Обновляем employee с id = " + test_id);
//        employeeDAO.update(test_id, 1,
//                "Dmitry", "Anatolyevich", "Medvedev",
//                formatter.parse("2008-08-08"), 150);
//        System.out.println();
//
//        // Проверяем метод delete  ----- работает, всё ОК
//     /*   employeeDAO.delete(test_id);
//        System.out.println("Удалили.... id=" + test_id);
//        System.out.println();
//     */
//
//        // снова выводим список  (employees)
//        employees = employeeDAO.getAll();
//        for (Employee employee2 : employees) {
//            System.out.println(
//                    employee2.getId() + " " + employee2.getId_department() + " " +
//                            employee2.getFirst_name() + " " + employee2.getMiddle_name() + " " + employee2.getLast_name() + " " +
//                            employee2.getBirth_date() + " " + employee2.getSalary());
//        }
//        System.out.println();

    }
}
