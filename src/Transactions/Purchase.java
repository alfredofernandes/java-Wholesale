/**
 Purchase.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

package Transactions;

import People.Buyer;
import Product.Product;

import java.text.ParseException;
import java.util.ArrayList;

public class Purchase extends Transaction {

    private Buyer buyer;

    public Purchase(Buyer buyer) {
        super(TypeTransaction.PURCHASE);
        this.buyer = buyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void generateOrder(Product product, int quantity, double priceEach) {

        boolean exist = false;
        ArrayList<DetailTransaction> details = this.getDetails();

        for (int i=0; i < details.size(); i++) {
            DetailTransaction detail = details.get(i);
            if (detail.getProduct().getProductId() == product.getProductId()) {

                detail.setQuantity(quantity);
                detail.setPriceEach(priceEach);
                detail.setDiscount(0.0);

                this.addDetail(detail);
                exist = true;
            }
        }

        if (!exist) {
            DetailTransaction detail = new DetailTransaction(product, quantity, priceEach, 0.0);
            this.addDetail(detail);
        }
    }

    public void finishOrder() throws ParseException {
        this.generateShippedDate();
        this.setStatus(StatusTransaction.DELIVERED);
        this.setPayment(true);
    }
}
