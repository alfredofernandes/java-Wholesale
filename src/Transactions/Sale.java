package Transactions;

import People.Customer;

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
}
