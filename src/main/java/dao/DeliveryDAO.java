package dao;

import configuration.SessionFactoryUtil;
import dto.CustomerDTO;
import entity.Delivery;
import entity.Employee;
import entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DeliveryDAO {
    public static void saveDelivery(Delivery delivery) {
        // if (delivery != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(delivery);
                transaction.commit();
            }
        // }
    }

    public static void saveOrUpdateDelivery(Delivery delivery) {
        // if (delivery != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.saveOrUpdate(delivery);
                transaction.commit();
            }
        // }
    }

    public static void deleteDelivery(Delivery delivery) {
        // if (delivery != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(delivery);
                transaction.commit();
            }
        // }
    }

    public static List<Delivery> readDeliveries() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT d FROM Delivery d", Delivery.class).getResultList();
        }
    }

    public static Delivery getDelivery(int deliveryId) {
        Delivery delivery;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            delivery = session.get(Delivery.class, deliveryId);
            transaction.commit();
        }
        return delivery;
    }

    public static void saveDeliveries(List<Delivery> deliveries) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            deliveries.stream().forEach(d -> session.save(d));
            transaction.commit();
        }
    }

    public static List<Delivery> sortDeliveriesByDestination(){
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Delivery> cr = cb.createQuery(Delivery.class);
            Root<Delivery> root = cr.from(Delivery.class);
            cr.select(root).orderBy(cb.asc(root.get("arrival_address")));

            Query<Delivery> query = session.createQuery(cr);
            List<Delivery> deliveries = query.getResultList();
            return deliveries;
        }
    }

    public static List<Delivery> filterDeliveriesByArrivalAddress(String arrivalAddress) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Delivery> cr = cb.createQuery(Delivery.class);
            Root<Delivery> root = cr.from(Delivery.class);
            cr.select(root).where(cb.equal(root.get("arrival_address"), arrivalAddress));

            Query<Delivery> query = session.createQuery(cr);
            List<Delivery> deliveries = query.getResultList();
            return deliveries;
        }
    }

    public static BigDecimal employeeIncomes(Employee deliveryEmployee){
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> query = cb.createQuery(BigDecimal.class);

            Root<Delivery> root = query.from(Delivery.class);
            query.select(cb.sum(root.get("delivery_price"))).where(cb.equal(root.get("delivery_employee"), deliveryEmployee));

            Query<BigDecimal> qr = session.createQuery(query);
            BigDecimal income = qr.getSingleResult();
            return income;
        }
    }

    public static Long employeeNumberOfDeliveries(Employee deliveryEmployee) {
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Delivery> root = query.from(Delivery.class);
            query.select(cb.count(root)).where(cb.equal(root.get("delivery_employee"), deliveryEmployee));

            Query<Long> qr = session.createQuery(query);
            Long number = qr.getSingleResult();
            return number;
        }
    }

    public static void fileCreator(String fileName, List<Delivery> deliveries) {
        try (FileWriter file = new FileWriter(fileName)) {
            for (Delivery d : deliveries) {
                file.write(d + System.lineSeparator());
            }
        } catch (IOException fileNot) {
            fileNot.printStackTrace();
        }
    }

    public static void readFile(String fileName) {
        String currentLine;
        try (FileReader fis = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fis);
            while ((currentLine = bufferedReader.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BigDecimal companyIncomesFromDeliveries(TransportCompany transportCompany) {
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> query = cb.createQuery(BigDecimal.class);
            Root<Delivery> root = query.from(Delivery.class);

            query.where(cb.equal(root.get("delivery_company"),
                    transportCompany)).select(cb.sum(root.get("delivery_price"))).getSelection();
            Query<BigDecimal> qr = session.createQuery(query);
            BigDecimal number = qr.getSingleResult();
            return number;
        }
    }

    public static Long companyNumberOfDeliveries(TransportCompany transportCompany){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Delivery> root = query.from(Delivery.class);
            query.select(cb.count(root)).where(cb.equal(root.get("delivery_company"), transportCompany));

            Query<Long> qr = session.createQuery(query);
            Long numberOfDeliveries = qr.getSingleResult();
            return numberOfDeliveries;
        }
    }

    public static List<Delivery> companyDeliveries(TransportCompany transportCompany) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Delivery> cr = cb.createQuery(Delivery.class);
                Root<Delivery> root = cr.from(Delivery.class);
                cr.select(root).where(cb.equal(root.get("delivery_company"), transportCompany));

                Query<Delivery> query = session.createQuery(cr);
                List<Delivery> numberOfDeliveries = query.getResultList();
                return numberOfDeliveries;
            }
    }

    public static List<CustomerDTO> deliveryCustomers(long deliveryId) {
        List<CustomerDTO> customers;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            customers = session.createQuery(
                    "select new dto.CustomerDTO(c.customer_id, c.customer_name) from Customer c" +
                            " join c.customer_delivery d " +
                            "where d.delivery_id = :deliveryId",
                    CustomerDTO.class)
                    .setParameter("delivery_id", deliveryId)
                    .getResultList();
            transaction.commit();
        }
        return customers;
    }

    public static List<Delivery> deliveriesWithArrivalDateBetween(LocalDate from, LocalDate to) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Delivery> cr = cb.createQuery(Delivery.class);
            Root<Delivery> root = cr.from(Delivery.class);
            cr.select(root).where(cb.between(root.get("arrival_date"), to, from));

            Query<Delivery> query = session.createQuery(cr);
            List<Delivery> deliveries = query.getResultList();
            return deliveries;
        }
    }

    public static List<Delivery> deliveriesWithArrivalDateGreaterThan(LocalDate localDate) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Delivery> cr = cb.createQuery(Delivery.class);
            Root<Delivery> root = cr.from(Delivery.class);
            cr.select(root).where(cb.greaterThan(root.get("arrival_date"), localDate));

            Query<Delivery> query = session.createQuery(cr);
            List<Delivery> deliveries = query.getResultList();
            return deliveries;
        }
    }

    public static List<TransportCompany> companiesWithIncomeBetween(double top, double bottom) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> cr = cb.createQuery(TransportCompany.class);
            Root<TransportCompany> root = cr.from(TransportCompany.class);
            cr.select(root).where(cb.between(root.get("company_income"), top, bottom));

            Query<TransportCompany> query = session.createQuery(cr);
            List<TransportCompany> companies = query.getResultList();
            return companies;
        }
    }
}