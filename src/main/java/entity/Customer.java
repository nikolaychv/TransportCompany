package entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customer_id;

    @Column(name="customer_name", nullable = false)
    @NotBlank(message = "Customer name cannot be blank!")
    @Size(max = 35, message = "Customer name has to be with up to 35 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Customer name has to start with capital letter!")
    private String customer_name;

    @Column(name = "is_paid", nullable = false)
    private boolean is_paid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Delivery customer_delivery;

    @ManyToMany(mappedBy = "customers")
    private List<TransportCompany> customer_companies;

    public Customer() {
        customer_companies = new ArrayList<>();
    }

    public Customer(long customer_id,
                    @NotBlank(message = "Customer name cannot be blank!")
                    @Size(max = 35, message = "Customer name has to be with up to 35 characters!")
                    @Pattern(regexp = "^([A-Z]).*", message = "Customer name has to start with capital letter!") String customer_name,
                    boolean is_paid, Delivery customer_delivery, List<TransportCompany> customer_companies) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.is_paid = is_paid;
        this.customer_delivery = customer_delivery;
        this.customer_companies = customer_companies;
    }

    public Customer(@NotBlank(message = "Customer name cannot be blank!") @Size(max = 35, message = "Customer name has to be with up to 35 characters!") @Pattern(regexp = "^([A-Z]).*", message = "Customer name has to start with capital letter!") String customer_name, boolean is_paid) {
        this.customer_name = customer_name;
        this.is_paid = is_paid;
    }

    public Customer(long customer_id, String customer_name, boolean is_paid) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.is_paid = is_paid;
    }

    public long getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public void setCustomerName(String customer_name) {
        this.customer_name = customer_name;
    }

    public boolean isIsPaid() {
        return is_paid;
    }

    public void setIsPaid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public Delivery getCustomerDelivery() {
        return customer_delivery;
    }

    public void setCustomerDelivery(Delivery customer_delivery) {
        this.customer_delivery = customer_delivery;
    }

    public List<TransportCompany> getCustomerCompanies() {
        return customer_companies;
    }

    public void setCustomerCompanies(List<TransportCompany> customer_companies) {
        this.customer_companies = customer_companies;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", is_paid=" + is_paid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customer_id == customer.customer_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id);
    }
}
