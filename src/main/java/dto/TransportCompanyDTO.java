package dto;

import java.math.BigDecimal;

public class TransportCompanyDTO {
    private long company_id;

    private String company_name;

    private BigDecimal company_income;

    public TransportCompanyDTO() {
    }

    public TransportCompanyDTO(long company_id, String company_name, BigDecimal company_income) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_income = company_income;
    }

    @Override
    public String toString() {
        return "TransportCompanyDTO{" +
                "company_id=" + company_id +
                ", company_name='" + company_name + '\'' +
                ", company_income=" + company_income +
                '}';
    }
}
