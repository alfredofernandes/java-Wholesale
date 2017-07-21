package Service;

import People.Address;
import People.Buyer;
import People.Customer;
import Products.Category;
import Products.Product;
import Products.Stock;

import java.util.ArrayList;

public class LoadManager {

    private ArrayList<Category> categories;
    private ArrayList<Product> products;
    private ArrayList<Stock> stocks;
    private ArrayList<Address> addresses;
    private ArrayList<Buyer> buyers;
    private ArrayList<Customer> customers;

    public LoadManager() {

        CategoryManager category = new CategoryManager();
        categories = category.loadFile();

        ProductManager product = new ProductManager();
        products = product.loadFile(categories);

        StockManager stock = new StockManager();
        stocks = stock.loadFile(products);

        AddressManager address = new AddressManager();
        addresses = address.loadFile();

        CustomerManager customer = new CustomerManager();
        customers = customer.loadFile(addresses);

        BuyerManager buyer = new BuyerManager();
        buyers = buyer.loadFile(products, addresses);

    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
