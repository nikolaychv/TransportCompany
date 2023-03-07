package dao;

import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VehicleDAO {

    public static void saveVehicle(Vehicle vehicle) {
        // if (vehicle != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(vehicle);
                transaction.commit();
            }
        // }
    }

    public static void saveOrUpdateVehicle(Vehicle vehicle) {
        // if (vehicle != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.saveOrUpdate(vehicle);
                transaction.commit();
            }
        // }
    }

    public static void deleteVehicle(Vehicle vehicle) {
        // if (vehicle != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(vehicle);
                transaction.commit();
            }
        // }
    }

    public static List<Vehicle> readVehicles() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT v FROM Vehicle v", Vehicle.class).getResultList();
        }
    }

    public static Vehicle getVehicle(long vehicleId) {
        Vehicle vehicle;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.get(Vehicle.class, vehicleId);
            transaction.commit();
        }
        return vehicle;
    }

    public static void saveVehicles(List<Vehicle> vehicles) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles.stream().forEach(v -> session.save(v));
            transaction.commit();
        }
    }


}
