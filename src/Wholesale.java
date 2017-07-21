/**
 Wholesale.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

import People.Buyer;
import People.Customer;
import Products.Stock;
import Service.LoadManager;
import java.util.ArrayList;

public class Wholesale {

    private LoadManager loadData;

    private ArrayList<Stock> stocks;
    private ArrayList<Buyer> buyers;
    private ArrayList<Customer> customers;

    public Wholesale() {

        loadData = new LoadManager();

        stocks = loadData.getStocks();
        buyers = loadData.getBuyers();
        customers = loadData.getCustomers();
    }

    public void orderSale(String nameCustomer) {

    }

    public boolean existCustomer(String name) {

        Customer customer = searchCustomer(name);

        if (customer != null) {
            return true;
        } else {
            return false;
        }
    }

    public Customer searchCustomer(String name) {

        for(Customer customer:customers) {
            if (customer.getName().compareTo(name) == 0) {
                return customer;
            }
        }

        return null;
    }

}
