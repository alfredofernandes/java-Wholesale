package Transactions;

/**
 Transaction.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

public class Transaction {

    public enum StatusTransaction {
        PENDING, CANCELED, CLOSED, DELIVERED
    }

    public enum TypeTransaction {
        PURCHASE, SALE
    }

    static int numID = 0;

    private int orderID;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;

    private StatusTransaction status;
    private TypeTransaction type;

    private boolean payment;
    private double discount;

    private ArrayList<DetailTransaction> details;

    public Transaction(TypeTransaction type) {

        Transaction.numID += 1;
        this.orderID = Transaction.numID;

        this.orderDate = new Date();
        this.requiredDate = null;
        this.shippedDate = null;

        this.status = StatusTransaction.PENDING;
        this.type = type;

        this.payment = false;
        this.discount = 0.0;
        this.details = new ArrayList<DetailTransaction>();

    }

    //Getter and Setter

    public int getOrderID() {
        return orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public StatusTransaction getStatus() {
        return status;
    }

    public TypeTransaction getType() {
        return type;
    }

    public boolean isPayment() {
        return payment;
    }

    public ArrayList<DetailTransaction> getDetails() {
        return details;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public void setStatus(StatusTransaction status) {
        this.status = status;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    //Methods

    public void addDetail(DetailTransaction detail) {

        int exist = checkDetailExist(detail);

        if (exist == -1) {
            this.details.add(detail);

        } else {

            if (removeDetail(detail)) {
                this.details.add(detail);
            }
        }
    }

    public boolean removeDetail(DetailTransaction detail) {

        int exist = checkDetailExist(detail);

        if (exist != -1) {
            this.details.remove(exist);
            return true;
        }

        return false;
    }

    public int checkDetailExist(DetailTransaction detail) {

        int exist = -1;

        for (int i=0; i < this.details.lenght; i++) {
            DetailTransaction det = this.details[i];
            if (det.getDetailID() == detail.getDetailID()) {
                exist = i;
            }
        }

        return exist;
    }

    public double totalPayment() {

        double total = 0.0;

        for (int i=0; i < this.details.lenght; i++) {
            DetailTransaction detail = this.details[i];
            double price = detail.totalPrice();
            total += price;
        }

        return total;
    }

    public double totalPaymentWithDiscount() {

        double total = 0.0;

        for (int i=0; i < this.details.lenght; i++) {
            DetailTransaction detail = this.details[i];
            double price = detail.totalPriceDiscount();
            total += price;
        }

        return total;
    }


}
