package dao;

import dto.DeliveryDTO;
import entity.Employee;
import enums.EmployeeQualification;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class EmployeeDAO {

    public static void saveEmployee(Employee employee) {
        // if (employee != null) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
        // }
    }

    public static void saveOrUpdateEmployee(Employee employee) {
        // if (employee != null) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
        // }
    }

    public static List<Employee> readEmployees() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        }
    }

    public static Employee getEmployee(long employeeId) {
        Employee employee;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.get(Employee.class, employeeId);
            transaction.commit();
        }
        return employee;
    }

    public static void deleteEmployee(Employee employee) {
        // if (employee != null) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
        // }
    }

    public static void saveEmployees(List<Employee> employees) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees.stream().forEach(e -> session.save(e));
            transaction.commit();
        }
    }

    public static List<Employee> sortEmployeesByQualificationAndSalary() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).orderBy(cb.asc(root.get("employeeQualification")), cb.desc(root.get("employee_salary")));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> sortEmployeesByQualification() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).orderBy(cb.asc(root.get("employeeQualification")));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> sortEmployeesBySalary() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).orderBy(cb.desc(root.get("employee_salary")));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> filterEmployeesByQualification(EmployeeQualification employeeQualification) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).where(cb.equal(root.get("employeeQualification"), employeeQualification));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> filterEmployeesBySalary(BigDecimal employeeSalary) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).where(cb.equal(root.get("employee_salary"), employeeSalary));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<DeliveryDTO> getEmployeeDeliveries(long id) {
        List<DeliveryDTO> deliveries;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            deliveries = session.createQuery(
                    "select new dto.DeliveryDTO(d.delivery_id, " +
                              "d.departing_address, d.arrival_address, " +
                              "d.departing_date, d.arrival_date, " +
                              "d.transportation_type, d.delivery_weight, d.delivery_price) from Delivery d" +
                              "join d.delivery_employee e " +
                              "where e.employee_id = :id",
                    DeliveryDTO.class)
                    .setParameter("employee_id", id)
                    .getResultList();
            transaction.commit();
        }

        return deliveries;
    }
}