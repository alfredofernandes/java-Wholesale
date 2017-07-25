/**
 Wholesale.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

import People.Buyer;
import People.Customer;
import Products.Product;
import Products.Stock;
import Service.LoadManager;
import Transactions.DetailTransaction;
import Transactions.Purchase;
import Transactions.Sale;
import Transactions.Transaction;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Wholesale {

    private LoadManager loadData;

    private ArrayList<Stock> stocks;
    private ArrayList<Buyer> buyers;
    private ArrayList<Customer> customers;

    private ArrayList<Sale> historySale = new ArrayList<>();
    private ArrayList<Purchase> historyPurchase = new ArrayList<>();

    public Wholesale() {

        loadData = new LoadManager();

        stocks = loadData.getStocks();
        buyers = loadData.getBuyers();
        customers = loadData.getCustomers();
    }

    // ORDER SALE

    public int orderSale(String nameCustomer) {

        Customer customer = searchCustomer(nameCustomer);
        Sale sale = new Sale(customer);

        historySale.add(sale);
        return  sale.getOrderID();
    }

    public void removeOrder(int numberOrder) {

        for(int i = 0; i < historySale.size(); i++) {

            Sale sale = historySale.get(i);
            if (sale.getOrderID() == numberOrder) {
                historySale.remove(i);
            }
        }
    }

    public Sale getOrderSale(int numberOrder) {

        for(Sale sale: historySale) {
            if (sale.getOrderID() == numberOrder) {
                return sale;
            }
        }

        return  null;
    }

    public void addProductInOrderSale(Product product, int quantity, int orderNumber) {

        Sale sale = getOrderSale(orderNumber);

        if (sale != null) {

            Stock productInStock = getProductInStock(product);
            if (productInStock != null) {

                if (productInStock.checkQuantity(quantity)) {

                    double price = productInStock.getPriceEach();
                    double discount = generateDiscount(quantity);

                    sale.orderDetail(product, quantity, price, discount);
                    // update sale in history

                } else {

                    //ORDER PURCHASE
                    orderPurchase(product, quantity);
                    addProductInOrderSale(product, quantity, orderNumber);
                }
            }
        }

    }

    public void finishOrderSale(int orderNumber) {

        Sale sale = getOrderSale(orderNumber);

        if (sale != null) {

            for (DetailTransaction detail:sale.getDetails()) {
                updateStock(detail.getProduct(), detail.getQuantity(), false);
            }

            try {
                sale.finishOrder();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    public void payOrderSale(int orderNumber) {

        Sale sale = getOrderSale(orderNumber);

        if (sale != null) {
            finishOrderSale(orderNumber);
            sale.payOrder();
            // update sale in history
        }
    }

    // ORDER PURCHASE

    public void orderPurchase(Product product, int quantity) {

        for (Buyer buyer: buyers) {

            double price = buyer.checkProduct(product);
            if (price > 0.0) {

                Purchase purchase = new Purchase(buyer);
                purchase.generateOrder(product, quantity, price);

                updateStock(product, quantity, true);
                historyPurchase.add(purchase);
            }
        }
    }

    // STOCK

    private Stock getProductInStock(Product product) {

        int exist = checkProductStock(product);

        if (exist != -1) {
            return stocks.get(exist);
        }

        return null;

    }

    private int checkProductStock(Product product) {

        int position = -1;

        for(int i = 0; i < stocks.size(); i++) {
            Stock stock = stocks.get(i);

            if (stock.getProduct().getProductId() == product.getProductId()) {
                position = i;
            }
        }

        return position;
    }

    private void updateStock(Product product, int quantity, boolean increment) {

        for (Stock stock:stocks) {
            if (stock.getProduct().getProductId() == product.getProductId()) {
                if (increment) {
                    stock.addQuantity(quantity);
                } else {
                    stock.removeQuantity(quantity);
                }
            }
        }
    }

    // CUSTOMER

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

    // GETTERS AND SETTERS

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    // GENERATE DISCOUNT

    private double generateDiscount(int amount) {

        if (amount > 8)  {
            return 10.0;

        } else if (amount > 6) {
            return 8.0;

        } else if (amount > 3) {
            return 5.0;

        } else {
            return 0.0;
        }
    }
}
