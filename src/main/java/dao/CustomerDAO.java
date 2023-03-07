package dao;

import entity.Customer;

import org.hibernate.Session;
import configuration.SessionFactoryUtil;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDAO {
    public static void saveCustomer(Customer customer){
        // if (customer != null) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        }
        // }
    }

    public static void saveOrUpdateCustomer(Customer customer) {
        // if (customer != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.saveOrUpdate(customer);
                transaction.commit();
            }
        // }
    }

    public static List<Customer> readCustomers() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        }
    }

    public static Customer getCustomer(long customerId) {
        Customer customer;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            customer = session.get(Customer.class, customerId);
            transaction.commit();
        }
        return customer;
    }

    public static void deleteCustomer(Customer customer) {
        // if (customer != null) {
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(customer);
                transaction.commit();
            }
        // }
    }

    public static void saveCustomers(List<Customer> customers) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            customers.stream().forEach(customer -> session.save(customer));
            transaction.commit();
        }
    }
}