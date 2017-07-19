package Transactions;

/**
 Sale.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

public class Sale extends Transaction {

    private Customer customer;

    public Sale(Customer customer) {
        this.customer = customer;
        super(Transaction.TypeTransaction.SALE);
    }

    public
}
