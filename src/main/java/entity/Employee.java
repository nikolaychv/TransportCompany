package entity;

import enums.EmployeeQualification;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employee_id;

    @Column(name="employee_name", nullable = false)
    @NotBlank(message = "Employee name cannot be blank!")
    @Size(max = 35, message = "Employee name has to be with up to 35 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Employee name has to start with capital letter!")
    private String employee_name;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransportCompany employee_company;

    @Column(name="employee_qualification", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeQualification employeeQualification;

    @Positive
    @DecimalMin(value = "10000.00", message = "Salary has to be more than or equal to 10000.00", inclusive = true)
    @DecimalMax(value = "99999.00", message = "Salary has to be less than or equal to 99999.00")
    @Digits(integer = 5, fraction = 2, message = "Salary has to be with 5 digits before and 2 digits after the decimal separator")
    @Column(name="employee_salary", nullable = false)
    private BigDecimal employee_salary;

    @OneToMany(mappedBy = "delivery_employee")
    private List<Delivery> employee_deliveries;

    public Employee() {
        employee_deliveries = new ArrayList<>();
    }

    public Employee(long employee_id,
                    @NotBlank(message = "Employee name cannot be blank!")
                    @Size(max = 35, message = "Employee name has to be with up to 35 characters!")
                    @Pattern(regexp = "^([A-Z]).*", message = "Employee name has to start with capital letter!") String employee_name,
                    TransportCompany employee_company, EmployeeQualification employeeQualification,
                    @Positive @DecimalMin(value = "10000.00", message = "Salary has to be more than or equal to 10000.00", inclusive = true)
                    @DecimalMax(value = "99999.00", message = "Salary has to be less than or equal to 99999.00")
                    @Digits(integer = 5, fraction = 2, message = "Salary has to be with 5 digits before and 2 digits after the decimal separator")
                            BigDecimal employee_salary, List<Delivery> employee_deliveries) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_company = employee_company;
        this.employeeQualification = employeeQualification;
        this.employee_salary = employee_salary;
        this.employee_deliveries = employee_deliveries;
    }

    public Employee(long employee_id, @NotBlank(message = "Employee name cannot be blank!")
    @Size(max = 35, message = "Employee name has to be with up to 35 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Employee name has to start with capital letter!") String employee_name,
                    TransportCompany employee_company, EmployeeQualification employeeQualification,
                    @Positive
                    @DecimalMin(value = "10000.00", message = "Salary has to be more than or equal to 10000.00",
                            inclusive = true)
                    @DecimalMax(value = "99999.00", message = "Salary has to be less than or equal to 99999.00")
                    @Digits(integer = 5, fraction = 2, message = "Salary has to be with 5 digits before and 2 digits after the decimal separator")
                            BigDecimal employee_salary) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_company = employee_company;
        this.employeeQualification = employeeQualification;
        this.employee_salary = employee_salary;
    }

    public Employee(@NotBlank(message = "Employee name cannot be blank!") @Size(max = 35, message = "Employee name has to be with up to 35 characters!") @Pattern(regexp = "^([A-Z]).*", message = "Employee name has to start with capital letter!") String employee_name, TransportCompany employee_company, EmployeeQualification employeeQualification, @Positive @DecimalMin(value = "10000.00", message = "Salary has to be more than or equal to 10000.00", inclusive = true) @DecimalMax(value = "99999.00", message = "Salary has to be less than or equal to 99999.00") @Digits(integer = 5, fraction = 2, message = "Salary has to be with 5 digits before and 2 digits after the decimal separator") BigDecimal employee_salary) {
        this.employee_name = employee_name;
        this.employee_company = employee_company;
        this.employeeQualification = employeeQualification;
        this.employee_salary = employee_salary;
    }

    public long getEmployeeId() {
        return employee_id;
    }

    public void setEmployeeId(long employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployeeName() {
        return employee_name;
    }

    public void setEmployeeName(String employee_name) {
        this.employee_name = employee_name;
    }

    public TransportCompany getEmployeeCompany() {
        return employee_company;
    }

    public void setEmployeeCompany(TransportCompany employee_company) {
        this.employee_company = employee_company;
    }

    public EmployeeQualification getEmployeeQualification() {
        return employeeQualification;
    }

    public void setEmployeeQualification(EmployeeQualification employeeQualification) {
        this.employeeQualification = employeeQualification;
    }

    public BigDecimal getEmployeeSalary() {
        return employee_salary;
    }

    public void setEmployeeSalary(BigDecimal employee_salary) {
        this.employee_salary = employee_salary;
    }

    public List<Delivery> getEmployeeDeliveries() {
        return employee_deliveries;
    }

    public void setEmployeeDeliveries(List<Delivery> employee_deliveries) {
        this.employee_deliveries = employee_deliveries;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_company=" + employee_company +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employee_id == employee.employee_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id);
    }
}
