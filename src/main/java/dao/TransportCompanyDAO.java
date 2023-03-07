package dao;

import dto.DeliveryDTO;
import dto.EmployeeDTO;
import dto.VehicleDTO;
import entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TransportCompanyDAO {

    public static void saveTransportCompany(TransportCompany transportCompany) {
        // if (transportCompany != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(transportCompany);
                transaction.commit();
            }
        // }
    }

    public static void saveOrUpdateTransportCompany(TransportCompany transportCompany) {
        // if (transportCompany != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.saveOrUpdate(transportCompany);
                transaction.commit();
            }
        // }
    }

    public static void deleteTransportCompany(TransportCompany transportCompany) {
        // if (company != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(transportCompany);
                transaction.commit();
            }
        // }
    }

    public static List<TransportCompany> readTransportCompanies() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT c FROM TransportCompany c", TransportCompany.class).getResultList();
        }
    }

    public static TransportCompany getTransportCompany(long companyId) {
        TransportCompany transportCompany;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompany = session.get(TransportCompany.class, companyId);
            transaction.commit();
        }
        return transportCompany;
    }

    public static void saveTransportCompanies(List<TransportCompany> companies) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies.stream().forEach((c -> session.save(c)));
            transaction.commit();
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByNameAndIncome(){
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> cr = cb.createQuery(TransportCompany.class);
            Root<TransportCompany> root = cr.from(TransportCompany.class);
            cr.select(root).orderBy(cb.asc(root.get("company_name")), cb.desc(root.get("company_income")));

            Query<TransportCompany> query = session.createQuery(cr);
            List<TransportCompany> transportCompanies = query.getResultList();
            return transportCompanies;
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByName() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> cr = cb.createQuery(TransportCompany.class);
            Root<TransportCompany> root = cr.from(TransportCompany.class);
            cr.select(root).orderBy(cb.asc(root.get("company_name")));

            Query<TransportCompany> query = session.createQuery(cr);
            List<TransportCompany> transportCompanies = query.getResultList();
            return transportCompanies;
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByIncome() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> cr = cb.createQuery(TransportCompany.class);
            Root<TransportCompany> root = cr.from(TransportCompany.class);
            cr.select(root).orderBy(cb.asc(root.get("company_income")));

            Query<TransportCompany> query = session.createQuery(cr);
            List<TransportCompany> transportCompanies = query.getResultList();
            return transportCompanies;
        }
    }

    public static List<VehicleDTO> getTransportCompanyVehicles(long companyId) {
        List<VehicleDTO> companyVehicles;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companyVehicles = session.createQuery(
                    "select new dto.VehicleDTO(v.vehicle_id, v.vehicle_type) from Vehicle v" +
                            " join v.vehicle_company c " +
                            "where c.company_id = :companyId",
                    VehicleDTO.class)
                    .setParameter("company_id", companyId)
                    .getResultList();
            transaction.commit();
        }

        return companyVehicles;
    }

    public static List<DeliveryDTO> getTransportCompanyDeliveries(long companyId) {
        List<DeliveryDTO> companyDeliveries;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companyDeliveries = session.createQuery(
                    "select new dto.DeliveryDTO(d.delivery_id, d.departing_address, d.arrival_address, " +
                              "d.departing_date, d.arrival_date, d.transportation_type, d.delivery_weight, d.delivery_price) " +
                              "from Delivery d" +
                              " join d.delivery_company c " +
                              "where c.company_id = :companyId",
                    DeliveryDTO.class)
                    .setParameter("company_id", companyId)
                    .getResultList();
            transaction.commit();
        }
        return companyDeliveries;
    }

    public static List<EmployeeDTO> getTransportCompanyEmployees(long companyId) {
        List<EmployeeDTO> companyEmployees;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companyEmployees = session.createQuery(
                    "select new dto.EmployeeDTO(e.employee_id, e.employee_name, " +
                            "e.employeeQualification, e.employee_salary) " +
                            "from Employee e" +
                            " join e.employee_company c " +
                            "where c.company_id = :companyId",
                    EmployeeDTO.class)
                    .setParameter("company_id", companyId)
                    .getResultList();
            transaction.commit();
        }
        return companyEmployees;
    }

}
