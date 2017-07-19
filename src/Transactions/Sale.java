package Transactions;

import People.Customer;
import Product.Product;

import java.util.ArrayList;

/**
 Sale.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

public class Sale extends Transaction {

    private Customer customer;

    public Sale(Customer customer) {
        super(TypeTransaction.SALE);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void orderDetail(Product product, int quantity, double priceEach, double discount){

        boolean exist = false;
        ArrayList<DetailTransaction> details = this.getDetails();

        for (int i=0; i < details.size(); i++) {
            DetailTransaction det = details.get(i);

        }
    }
}
