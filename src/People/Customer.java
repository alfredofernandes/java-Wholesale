package People;

import java.util.ArrayList;

/**
 Customer.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

public class Customer
{
    static int numID = 0;
    private int customerId;
    private String name;
    private ArrayList<Address> address;
    private boolean active;


    public Customer(String name, ArrayList<Address> address)
    {
        Customer.numID += 1;

        this.customerId = Customer.numID;
        this.name = name;
        this.address = address;
        this.active = true;
    }

    public int getCustomerId() {
        return customerId;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
