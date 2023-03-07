package entity;

import enums.VehicleType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {
    TransportCompany transportCompany = new TransportCompany();
    Vehicle vehicle = new Vehicle(4, VehicleType.BUS, transportCompany);

    @Test
    void getVehicleId() {
        assertEquals(vehicle.getVehicleId(), 4);
    }

    @Test
    void setVehicleId() {
        vehicle.setVehicleId(15);
        assertEquals(vehicle.getVehicleId(), 15);
    }

    @Test
    void getVehicleType() {
        assertEquals(vehicle.getVehicleType(), VehicleType.BUS);
    }

    @Test
    void setVehicleType() {
        vehicle.setVehicleType(VehicleType.VAN);
        assertEquals(vehicle.getVehicleType(), VehicleType.VAN);
    }

    @Test
    void getVehicleCompany() {
        assertEquals(vehicle.getVehicleCompany(), transportCompany);
    }

    @Test
    void setVehicleCompany() {
        TransportCompany company = new TransportCompany();
        vehicle.setVehicleCompany(company);
        assertEquals(vehicle.getVehicleCompany(), company);
    }

    @Test
    void testToString() {
        assertEquals(vehicle.toString(), "Vehicle{" +
                "vehicle_id=" + vehicle.getVehicleId() +
                ", vehicle_type=" + vehicle.getVehicleType() +
                ", vehicle_company=" + vehicle.getVehicleCompany() +
                '}');
    }
}
