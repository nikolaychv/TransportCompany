package entity;

import enums.EmployeeQualification;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {
    TransportCompany company = new TransportCompany(14, "Best", BigDecimal.valueOf(34000.00));
    Employee employee = new Employee(46, "Ivan", company, EmployeeQualification.FLAMMABLE_LOAD, BigDecimal.valueOf(10000.00));

    @Test
    void getEmployeeId() {
        assertEquals(employee.getEmployeeId(), 46);
    }

    @Test
    void setEmployeeId() {
        employee.setEmployeeId(6);
        assertEquals(employee.getEmployeeId(), 6);
    }

    @Test
    void getEmployeeName() {
        assertEquals(employee.getEmployeeName(), "Ivan");
    }

    @Test
    void setEmployeeName() {
        employee.setEmployeeName("Rosen");
        assertEquals(employee.getEmployeeName(), "Rosen");
    }

    @Test
    void getEmployeeCompany() {
        assertEquals(employee.getEmployeeCompany(), company);
    }

    @Test
    void setEmployeeCompany() {
        TransportCompany company = new TransportCompany();
        employee.setEmployeeCompany(company);
        assertEquals(employee.getEmployeeCompany(), company);
    }

    @Test
    void getEmployeeQualification() {
        assertEquals(employee.getEmployeeQualification(), EmployeeQualification.FLAMMABLE_LOAD);
    }

    @Test
    void setEmployeeQualification() {
        employee.setEmployeeQualification(EmployeeQualification.MORE_THAN_15_PEOPLE);
        assertEquals(employee.getEmployeeQualification(), EmployeeQualification.MORE_THAN_15_PEOPLE);
    }

    @Test
    void getEmployeeSalary() {
        assertEquals(employee.getEmployeeSalary(), BigDecimal.valueOf(10000.00));
    }

    @Test
    void setEmployeeSalary() {
        employee.setEmployeeSalary(BigDecimal.valueOf(20000));
        assertEquals(employee.getEmployeeSalary(), BigDecimal.valueOf(20000));
    }

    @Test
    void testToString() {
        assertEquals(employee.toString(), "Employee{" +
                "employee_id=" + employee.getEmployeeId() +
                ", employee_name='" + employee.getEmployeeName() + '\'' +
                ", employee_company=" + employee.getEmployeeCompany() +
                '}');
    }
}
