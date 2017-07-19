/**
 DetailTransaction.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

package Transactions;
import Product.Product;

public class DetailTransaction {

    static int numID = 0;

    private int detailID;
    private Product product;
    private int quantity;
    private double priceEach;
    private double discount;

    public DetailTransaction(Product product, int quantity, double priceEach, double discount) {

        DetailTransaction.numID += 1;
        this.detailID = DetailTransaction.numID;

        this.product = product;
        this.quantity = quantity;
        this.priceEach = priceEach;
        this.discount = discount;
    }

    public int getDetailID() {
        return detailID;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPriceEach() {
        return priceEach;
    }

    public double getDiscount() {
        return discount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPriceEach(double priceEach) {
        this.priceEach = priceEach;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double totalPrice(){

        double quantity = this.priceEach * (double)this.quantity;
        return  quantity;
    }

    public double totalPriceDiscount() {

        double quantity = this.priceEach * (double)this.quantity;
        double disc = (100 - this.discount)/100;

        return quantity*disc;
    }
}
