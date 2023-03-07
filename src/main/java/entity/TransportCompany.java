package entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "transport_company")
public class TransportCompany {
    @Id
    @Column(name = "company_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long company_id;

    @Column(name="company_name", nullable = false)
    @NotBlank(message = "Transport company name cannot be blank!")
    @Size(max = 35, message = "Transport company name has to be with up to 35 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Transport company name has to start with capital letter!")
    private String company_name;

    @OneToMany(mappedBy = "vehicle_company")
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "delivery_company")
    private List<Delivery> deliveries;

    @OneToMany(mappedBy = "employee_company")
    private List<Employee> employees;

    // @Positive
    @Column(name = "company_income", nullable = false)
    private BigDecimal company_income;

    @ManyToMany
    private List<Customer> customers;

    public TransportCompany() {
        company_income = BigDecimal.ZERO;
        vehicles = new ArrayList<>();
        deliveries = new ArrayList<>();
        employees = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public TransportCompany(long company_id, String company_name) {
        this.company_id = company_id;
        this.company_name = company_name;
    }

    public TransportCompany(String company_name, BigDecimal company_income) {
        this.company_name = company_name;
        this.company_income = company_income;
    }

    public TransportCompany(long company_id, String company_name, BigDecimal company_income) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_income = company_income;
    }

    public TransportCompany(long company_id,
                            @NotBlank(message = "Transport company name cannot be blank!")
                            @Size(max = 35, message = "Transport company name has to be with up to 35 characters!")
                            @Pattern(regexp = "^([A-Z]).*", message = "Transport company name has to start with capital letter!") String company_name,
                            List<Vehicle> vehicles, List<Delivery> deliveries, List<Employee> employees, BigDecimal company_income, List<Customer> customers) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.vehicles = vehicles;
        this.deliveries = deliveries;
        this.employees = employees;
        this.company_income = company_income;
        this.customers = customers;
    }

    public long getCompanyId() {
        return company_id;
    }

    public void setCompanyId(long company_id) {
        this.company_id = company_id;
    }

    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public BigDecimal getCompanyIncome() {
        return company_income;
    }

    public void setCompanyIncome(BigDecimal company_income) {
        this.company_income = company_income;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "TransportCompany{" +
                "company_id=" + company_id +
                ", company_name='" + company_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportCompany that = (TransportCompany) o;
        return company_id == that.company_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(company_id);
    }
}