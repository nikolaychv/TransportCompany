package entity;

import enums.VehicleType;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @Column(name = "vehicle_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vehicle_id;

    @Column(name="vehicle_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicle_type;

    @ManyToOne (fetch = FetchType.LAZY)
    private TransportCompany vehicle_company;

    public Vehicle() {
    }

    public Vehicle(long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Vehicle(VehicleType vehicle_type, TransportCompany vehicle_company) {
        this.vehicle_type = vehicle_type;
        this.vehicle_company = vehicle_company;
    }

    public Vehicle(long vehicle_id, VehicleType vehicle_type, TransportCompany vehicle_company) {
        this.vehicle_id = vehicle_id;
        this.vehicle_type = vehicle_type;
        this.vehicle_company = vehicle_company;
    }

    public long getVehicleId() {
        return vehicle_id;
    }

    public void setVehicleId(long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public VehicleType getVehicleType() {
        return vehicle_type;
    }

    public void setVehicleType(VehicleType vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public TransportCompany getVehicleCompany() {
        return vehicle_company;
    }

    public void setVehicleCompany(TransportCompany vehicle_company) {
        this.vehicle_company = vehicle_company;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id=" + vehicle_id +
                ", vehicle_type=" + vehicle_type +
                ", vehicle_company=" + vehicle_company +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicle_id == vehicle.vehicle_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle_id);
    }
}
