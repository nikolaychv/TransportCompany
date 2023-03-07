package dto;

import entity.Delivery;

public class CustomerDTO {
        private long customer_id;

        private String customer_name;

        private boolean is_paid;

        private Delivery customer_delivery;

    public CustomerDTO() {
    }

    public CustomerDTO(long customer_id, String customer_name, boolean is_paid, Delivery customer_delivery) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.is_paid = is_paid;
        this.customer_delivery = customer_delivery;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", is_paid=" + is_paid +
                ", customer_delivery=" + customer_delivery +
                '}';
    }
}
