import dao.*;
import entity.*;
import enums.EmployeeQualification;
import enums.TransportationType;
import enums.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Task 1.
        TransportCompany company1 = new TransportCompany();
        company1.setCompanyId(1);
        company1.setCompanyName("TravelWell");
        company1.setCompanyIncome(BigDecimal.valueOf(45000));
        TransportCompanyDAO.saveTransportCompany(company1);

        TransportCompany company2 = new TransportCompany();
        company2.setCompanyName("QualityTravel");
        TransportCompanyDAO.saveOrUpdateTransportCompany(company2);

        TransportCompany company3 = new TransportCompany("Best", BigDecimal.valueOf(23500));
        TransportCompanyDAO.saveOrUpdateTransportCompany(company3);

        TransportCompanyDAO.deleteTransportCompany(company2);

        TransportCompanyDAO.readTransportCompanies();
        TransportCompanyDAO.getTransportCompany(5);

        // Task 4.
        /*
        List<Delivery> firstEmployeeDeliveries = new ArrayList(10);
        firstEmployeeDeliveries.add(delivery1);

        List<Delivery> secondEmployeeDeliveries = new ArrayList(10);
        secondEmployeeDeliveries.add(delivery1);

        List<Delivery> thirdEmployeeDeliveries = new ArrayList(10);
        thirdEmployeeDeliveries.add(delivery1);
        */

        Employee kaloyan = new Employee(1, "Kaloyan", company1, EmployeeQualification.FLAMMABLE_LOAD, BigDecimal.valueOf(10000));
        EmployeeDAO.saveEmployee(kaloyan);

        EmployeeDAO.getEmployee(1);

        Employee stefan = new Employee(34, "Stefan", company1, EmployeeQualification.FLAMMABLE_LOAD, BigDecimal.valueOf(10000));
        EmployeeDAO.saveEmployee(stefan);

        Employee yavor = new Employee(35, "Yavor", company1, EmployeeQualification.MORE_THAN_15_PEOPLE, BigDecimal.valueOf(12000));
        EmployeeDAO.saveEmployee(yavor);

        Employee milen = new Employee(36, "Milen", company1, EmployeeQualification.SPECIAL_MACHINERY, BigDecimal.valueOf(14000));
        EmployeeDAO.saveEmployee(milen);

        EmployeeDAO.readEmployees();
        EmployeeDAO.deleteEmployee(yavor);

        // Task 2. & Task 5.
        // Employee nikolay = new Employee("Nikolay", company1, EmployeeQualification.FLAMMABLE_LOAD, BigDecimal.valueOf(10000));
        Customer atanas = new Customer(1, "Atanas", true);
        List<Customer> customers1 = new ArrayList(10);
        customers1.add(atanas);

        Delivery delivery1 = new Delivery("Sofia", "Samokov", LocalDate.of( 2022 , 1 , 13 ), LocalDate.of( 2022 , 1 , 14 ), TransportationType.SPECIAL_GOODS, stefan, company1, customers1, BigDecimal.valueOf(700), BigDecimal.valueOf(14050));
        Delivery delivery2 = new Delivery("Varna", "Kyustendil", LocalDate.of( 2022 , 1 , 14 ), LocalDate.of( 2022 , 1 , 15 ), TransportationType.SPECIAL_GOODS, stefan, company1, customers1, BigDecimal.valueOf(32), BigDecimal.valueOf(12040));
        Delivery delivery3 = new Delivery("Burgas", "Varna", LocalDate.of( 2022 , 1 , 15 ), LocalDate.of( 2022 , 1 , 16 ), TransportationType.GOODS, stefan, company1, customers1, BigDecimal.valueOf(104), BigDecimal.valueOf(10500));

        DeliveryDAO.saveDelivery(delivery1);
        DeliveryDAO.saveDelivery(delivery2);
        //DeliveryDAO.saveOrUpdateDelivery(delivery3);
        DeliveryDAO.readDeliveries();
        //DeliveryDAO.deleteDelivery(delivery2);

        Customer alexander = new Customer(34, "Alexander", true);
        CustomerDAO.saveCustomer(alexander);
        CustomerDAO.getCustomer(34);

        Customer slav = new Customer("Slav", true);
        CustomerDAO.saveOrUpdateCustomer(slav);

        Customer yoan = new Customer("Yoan", true);
        CustomerDAO.saveOrUpdateCustomer(yoan);

        CustomerDAO.readCustomers();
        CustomerDAO.deleteCustomer(slav);

        // Task 3.
        Vehicle vehicle1 = new Vehicle(1, VehicleType.BUS, company1);
        VehicleDAO.getVehicle(1);
        Vehicle vehicle2 = new Vehicle(2, VehicleType.VAN, company1);
        Vehicle vehicle3 = new Vehicle(3, VehicleType.CISTERN, company1);

        VehicleDAO.saveVehicle(vehicle1);
        VehicleDAO.saveVehicle(vehicle2);
        VehicleDAO.saveVehicle(vehicle3);
        // VehicleDAO.saveOrUpdateVehicle(vehicle2);
        VehicleDAO.readVehicles();
        // VehicleDAO.deleteVehicle(vehicle2);

        // Task 6.
        yoan.setIsPaid(false);
        CustomerDAO.saveOrUpdateCustomer(yoan);

        // Task 7.
        List<TransportCompany> sortingCompanies = new ArrayList(10);
        sortingCompanies.add(company1);
        sortingCompanies.add(company2);
        sortingCompanies.add(company3);

        sortingCompanies = TransportCompanyDAO.sortTransportCompaniesByNameAndIncome();
        sortingCompanies.stream().forEach(System.out::println);

        sortingCompanies = TransportCompanyDAO.sortTransportCompaniesByName();
        sortingCompanies.stream().forEach(System.out::println);

        sortingCompanies = TransportCompanyDAO.sortTransportCompaniesByIncome();
        sortingCompanies.stream().forEach(System.out::println);

        // Task 8.
        List<Delivery> deliveries = new ArrayList(10);
        deliveries.add(delivery1);
        deliveries.add(delivery2);
        deliveries.add(delivery3);
        String fileName = "files/Deliveries.txt";
        DeliveryDAO.fileCreator(fileName, deliveries);
        DeliveryDAO.readFile(fileName);

        for(TransportCompany com: sortingCompanies){
            System.out.println(com.getCompanyName() + " = " + DeliveryDAO.companyNumberOfDeliveries(com) + " deliveries.");
        }

    }
}