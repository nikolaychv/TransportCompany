package dto;

import entity.TransportCompany;
import enums.EmployeeQualification;
import java.math.BigDecimal;

public class EmployeeDTO {
    private long employee_id;

    private String employee_name;

    private TransportCompany employee_company;

    private EmployeeQualification employeeQualification;

    private BigDecimal employee_salary;

    public EmployeeDTO() {
    }

    public EmployeeDTO(long employee_id, String employee_name, TransportCompany employee_company,
                       EmployeeQualification employeeQualification, BigDecimal employee_salary) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_company = employee_company;
        this.employeeQualification = employeeQualification;
        this.employee_salary = employee_salary;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employee_id=" + employee_id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_company=" + employee_company +
                ", employeeQualification=" + employeeQualification +
                ", employee_salary=" + employee_salary +
                '}';
    }
}
