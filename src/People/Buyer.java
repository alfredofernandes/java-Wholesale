/**
 Buyer.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

package People;

import Product.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Buyer
{
    static int numID = 0;
    private int buyerId;
    private String name;
    private ArrayList<Address> address;
    private HashMap<String, Product> products = new HashMap<String, Product>();
    private boolean active;

    public Buyer(String name, ArrayList<Address> address, HashMap<String, Product> products)
    {
        Buyer.numID += 1;

        this.buyerId = Buyer.numID;
        this.name = name;
        this.address = address;
        this.products = products;
        this.active = true;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Address> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<Address> address) {
        this.address = address;
    }

    public double checkProduct(Product product)
    {
        Iterator<String> keySetIterator = products.keySet().iterator();

        while(keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            Product prod = products.get(key);

            if (prod.getProductId() == product.getProductId()) {
                return Double.parseDouble(key);
            }
        }

        return 0.0;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private class Products {
    }
}
