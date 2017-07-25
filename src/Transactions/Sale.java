/**
 Sale.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

package Transactions;

import People.Customer;
import Products.Product;

import java.text.ParseException;
import java.util.ArrayList;

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

    //Methods

    public void orderDetail(Product product, int quantity, double priceEach, double discount){

        boolean exist = false;
        ArrayList<DetailTransaction> details = this.getDetails();

        for (int i=0; i < details.size(); i++) {
            DetailTransaction detail = details.get(i);
            if (detail.getProduct().getProductId() == product.getProductId()) {

                detail.setQuantity(quantity);
                detail.setPriceEach(priceEach);
                detail.setDiscount(discount);

                this.addDetail(detail);
                exist = true;
            }
        }

        if (!exist) {
            DetailTransaction detail = new DetailTransaction(product, quantity, priceEach, discount);
            this.addDetail(detail);
        }
    }

    public void finishOrder() throws ParseException {
        this.generateShippedDate();
        this.setStatus(StatusTransaction.CLOSED);
    }

    public void payOrder() {
        this.setPayment(true);
        this.setStatus(StatusTransaction.DELIVERED);
    }
}
