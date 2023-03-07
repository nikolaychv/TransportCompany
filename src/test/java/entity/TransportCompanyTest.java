package entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransportCompanyTest {
    TransportCompany transportCompany = new TransportCompany(12, "Speedy", BigDecimal.valueOf(34514.00));

    @Test
    void getCompanyId() {
        assertEquals(transportCompany.getCompanyId(), 12);
    }

    @Test
    void setCompanyId() {
        transportCompany.setCompanyId(44);
        assertEquals(transportCompany.getCompanyId(), 44);
    }

    @Test
    void getCompanyName() {
        assertEquals(transportCompany.getCompanyName(), "Speedy");
    }

    @Test
    void setCompanyName() {
        transportCompany.setCompanyName("BestTravel");
        assertEquals(transportCompany.getCompanyName(), "BestTravel");
    }

    @Test
    void getCompanyIncome() {
        assertEquals(transportCompany.getCompanyIncome(), BigDecimal.valueOf(34514.00));
    }

    @Test
    void setCompanyIncome() {
        transportCompany.setCompanyIncome(BigDecimal.valueOf(20000));
        assertEquals(transportCompany.getCompanyIncome(), BigDecimal.valueOf(20000));
    }

    @Test
    void testToString() {
        assertEquals(transportCompany.toString(), "TransportCompany{" +
                "company_id=" + transportCompany.getCompanyId() +
                ", company_name='" + transportCompany.getCompanyName() + '\'' +
                '}');
    }
}
