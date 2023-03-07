package dto;

import entity.TransportCompany;
import enums.VehicleType;

public class VehicleDTO {
    private long vehicle_id;

    private VehicleType vehicle_type;

    private TransportCompany vehicle_company;

    public VehicleDTO() {
    }

    public VehicleDTO(long vehicle_id, VehicleType vehicle_type, TransportCompany vehicle_company) {
        this.vehicle_id = vehicle_id;
        this.vehicle_type = vehicle_type;
        this.vehicle_company = vehicle_company;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "vehicle_id=" + vehicle_id +
                ", vehicle_type=" + vehicle_type +
                ", vehicle_company=" + vehicle_company +
                '}';
    }
}
