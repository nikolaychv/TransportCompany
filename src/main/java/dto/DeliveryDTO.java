package dto;

import entity.Employee;
import entity.TransportCompany;
import enums.TransportationType;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DeliveryDTO {
    private long delivery_id;

    private String departing_address;

    private String arrival_address;

    private LocalDate departing_date;

    private LocalDate arrival_date;

    private TransportationType transportation_type;

    private BigDecimal delivery_weight;

    private BigDecimal delivery_price;

    private Employee delivery_employee;

    private TransportCompany delivery_company;

    public DeliveryDTO() {
    }

    public DeliveryDTO(long delivery_id, String departing_address, String arrival_address,
                       LocalDate departing_date, LocalDate arrival_date, TransportationType transportation_type,
                       Employee delivery_employee, TransportCompany delivery_company, BigDecimal delivery_weight,
                       BigDecimal delivery_price) {
        this.delivery_id = delivery_id;
        this.departing_address = departing_address;
        this.arrival_address = arrival_address;
        this.departing_date = departing_date;
        this.arrival_date = arrival_date;
        this.transportation_type = transportation_type;
        this.delivery_employee = delivery_employee;
        this.delivery_company = delivery_company;
        this.delivery_weight = delivery_weight;
        this.delivery_price = delivery_price;
    }

    public DeliveryDTO(long delivery_id, String departing_address, String arrival_address, LocalDate departing_date,
                       LocalDate arrival_date, TransportationType transportation_type, BigDecimal delivery_weight,
                       BigDecimal delivery_price) {
        this.delivery_id = delivery_id;
        this.departing_address = departing_address;
        this.arrival_address = arrival_address;
        this.departing_date = departing_date;
        this.arrival_date = arrival_date;
        this.transportation_type = transportation_type;
        this.delivery_weight = delivery_weight;
        this.delivery_price = delivery_price;
    }

    @Override
    public String toString() {
        return "DeliveryDTO{" +
                "delivery_id=" + delivery_id +
                ", departing_address='" + departing_address + '\'' +
                ", arrival_address='" + arrival_address + '\'' +
                ", departing_date=" + departing_date +
                ", arrival_date=" + arrival_date +
                ", transportation_type=" + transportation_type +
                ", delivery_employee=" + delivery_employee +
                ", delivery_company=" + delivery_company +
                ", delivery_weight=" + delivery_weight +
                ", delivery_price=" + delivery_price +
                '}';
    }
}
