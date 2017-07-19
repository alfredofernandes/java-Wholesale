package People;

import java.util.ArrayList;

/**
 Buyer.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

public class Buyer
{
    static int numID;
    private int buyerId;
    private String name;
    private ArrayList<Address> address;
    private ArrayList<Products> products;
    private boolean active;


    public Buyer(String name, ArrayList<Address> address, ArrayList<Products> products)
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

    public double checkProduct(ArrayList<Product> product)
    {
        for (int i = 0; i < product.size(); i++)
        {
            Product p = product.get(i);

        }
//        for dictionary in self.products
//        {
//            let prod = dictionary["product"] as! Product
//            let price = dictionary["price"] as! Double
//
//            if prod.getProductId() == product.getProductId() {
//                return price
//            }
//        }
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
