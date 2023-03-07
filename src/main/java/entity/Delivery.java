package entity;

import enums.TransportationType;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @Column(name = "delivery_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long delivery_id;

    @NotBlank(message = "Delivery departing address cannot be blank!")
    @Size(max = 30, message = "Delivery departing address has to be with up to 30 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Delivery departing address has to start with capital letter!")
    @Column(name = "departing_address", nullable = false)
    private String departing_address;

    @NotBlank(message = "Delivery arrival address cannot be blank!")
    @Size(max = 30, message = "Delivery arrival address has to be with up to 30 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Delivery arrival address has to start with capital letter!")
    @Column(name = "arrival_address", nullable = false)
    private String arrival_address;

    @Column(name = "departing_date")
    private LocalDate departing_date;

    @Column(name = "arrival_date")
    private LocalDate arrival_date;

    @Column(name = "transportation_type", nullable = false)
    private TransportationType transportation_type;

    @ManyToOne
    private Employee delivery_employee;

    @ManyToOne
    private TransportCompany delivery_company;

    @OneToMany(mappedBy = "customer_delivery")
    private List<Customer> delivery_customers;

    @Positive
    @Column(name = "delivery_weight")
    private BigDecimal delivery_weight;

    @Positive
    @DecimalMin(value = "10000.00", message = "Delivery price has to be more than or equal to 10000.00", inclusive = true)
    @DecimalMax(value = "99999.00", message = "Delivery price has to be less than or equal to 99999.00")
    @Digits(integer = 5, fraction = 2, message = "Delivery price has to be with 5 digits before and 2 digits after the decimal separator!")
    @Column(name = "delivery_price", nullable = false)
    private BigDecimal delivery_price;

    public Delivery() {
        delivery_customers = new ArrayList<>();
    }

    public Delivery(long delivery_id,
                    @NotBlank(message = "Delivery departing address cannot be blank!")
                    @Size(max = 30, message = "Delivery departing address has to be with up to 30 characters!")
                    @Pattern(regexp = "^([A-Z]).*", message = "Delivery departing address has to start with capital letter!") String departing_address,
                    @NotBlank(message = "Delivery arrival address cannot be blank!")
                    @Size(max = 30, message = "Delivery arrival address has to be with up to 30 characters!")
                    @Pattern(regexp = "^([A-Z]).*", message = "Delivery arrival address has to start with capital letter!") String arrival_address,
                    LocalDate departing_date, LocalDate arrival_date, TransportationType transportation_type, Employee delivery_employee,
                    TransportCompany delivery_company, List<Customer> delivery_customers,
                    @Positive BigDecimal delivery_weight,
                    @Positive @DecimalMin(value = "10000.00", message = "Delivery price has to be more than or equal to 10000.00", inclusive = true)
                    @DecimalMax(value = "99999.00", message = "Delivery price has to be less than or equal to 99999.00")
                    @Digits(integer = 5, fraction = 2, message = "Delivery price has to be with 5 digits before and 2 digits after the decimal separator!") BigDecimal delivery_price) {
        this.delivery_id = delivery_id;
        this.departing_address = departing_address;
        this.arrival_address = arrival_address;
        this.departing_date = departing_date;
        this.arrival_date = arrival_date;
        this.transportation_type = transportation_type;
        this.delivery_employee = delivery_employee;
        this.delivery_company = delivery_company;
        this.delivery_customers = delivery_customers;
        this.delivery_weight = delivery_weight;
        this.delivery_price = delivery_price;
    }

    public Delivery(@NotBlank(message = "Delivery departing address cannot be blank!") @Size(max = 30, message = "Delivery departing address has to be with up to 30 characters!") @Pattern(regexp = "^([A-Z]).*", message = "Delivery departing address has to start with capital letter!") String departing_address, @NotBlank(message = "Delivery arrival address cannot be blank!") @Size(max = 30, message = "Delivery arrival address has to be with up to 30 characters!") @Pattern(regexp = "^([A-Z]).*", message = "Delivery arrival address has to start with capital letter!") String arrival_address, LocalDate departing_date, LocalDate arrival_date, TransportationType transportation_type, Employee delivery_employee, TransportCompany delivery_company, List<Customer> delivery_customers, @Positive BigDecimal delivery_weight, @Positive @DecimalMin(value = "10000.00", message = "Delivery price has to be more than or equal to 10000.00", inclusive = true) @DecimalMax(value = "99999.00", message = "Delivery price has to be less than or equal to 99999.00") @Digits(integer = 5, fraction = 2, message = "Delivery price has to be with 5 digits before and 2 digits after the decimal separator!") BigDecimal delivery_price) {
        this.departing_address = departing_address;
        this.arrival_address = arrival_address;
        this.departing_date = departing_date;
        this.arrival_date = arrival_date;
        this.transportation_type = transportation_type;
        this.delivery_employee = delivery_employee;
        this.delivery_company = delivery_company;
        this.delivery_customers = delivery_customers;
        this.delivery_weight = delivery_weight;
        this.delivery_price = delivery_price;
    }

    public long getDeliveryId() {
        return delivery_id;
    }

    public void setDeliveryId(long delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getDepartingAddress() {
        return departing_address;
    }

    public void setDepartingAddress(String departing_address) {
        this.departing_address = departing_address;
    }

    public String getArrivalAddress() {
        return arrival_address;
    }

    public void setArrivalAddress(String arrival_address) {
        this.arrival_address = arrival_address;
    }

    public LocalDate getDepartingDate() {
        return departing_date;
    }

    public void setDepartingDate(LocalDate departing_date) {
        this.departing_date = departing_date;
    }

    public LocalDate getArrivalDate() {
        return arrival_date;
    }

    public void setArrivalDate(LocalDate arrival_date) {
        this.arrival_date = arrival_date;
    }

    public TransportationType getTransportationType() {
        return transportation_type;
    }

    public void setTransportationType(TransportationType transportation_type) {
        this.transportation_type = transportation_type;
    }

    public Employee getDeliveryEmployee() {
        return delivery_employee;
    }

    public void setDeliveryEmployee(Employee delivery_employee) {
        this.delivery_employee = delivery_employee;
    }

    public TransportCompany getDeliveryCompany() {
        return delivery_company;
    }

    public void setDeliveryCompany(TransportCompany delivery_company) {
        this.delivery_company = delivery_company;
    }

    public List<Customer> getDeliveryCustomers() {
        return delivery_customers;
    }

    public void setDeliveryCustomers(List<Customer> delivery_customers) {
        this.delivery_customers = delivery_customers;
    }

    public BigDecimal getDeliveryWeight() {
        return delivery_weight;
    }

    public void setDeliveryWeight(BigDecimal delivery_weight) {
        this.delivery_weight = delivery_weight;
    }

    public BigDecimal getDeliveryPrice() {
        return delivery_price;
    }

    public void setDeliveryPrice(BigDecimal delivery_price) {
        this.delivery_price = delivery_price;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "delivery_id=" + delivery_id +
                ", departing_address='" + departing_address + '\'' +
                ", arrival_address='" + arrival_address + '\'' +
                ", departing_date=" + departing_date +
                ", arrival_date=" + arrival_date +
                ", transportation_type=" + transportation_type +
                ", delivery_employee=" + delivery_employee +
                ", delivery_company=" + delivery_company +
                ", delivery_customers=" + delivery_customers +
                ", delivery_weight=" + delivery_weight +
                ", delivery_price=" + delivery_price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return delivery_id == delivery.delivery_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(delivery_id);
    }
}