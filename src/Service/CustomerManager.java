package Service;

import People.Address;
import People.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManager {

    final private String FILENAME = "customer.txt";
    private File file;
    private Scanner readFile;
    private PrintWriter writeFile;

    private ArrayList<Address> addresses;

    public CustomerManager() {
        this.file = new File(FILENAME);
        try {
            this.readFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> loadFile(ArrayList<Address> addresses)  {

        this.addresses = addresses;
        ArrayList<Customer> customers = new ArrayList<Customer>();

        while(this.readFile.hasNext())
        {
            String line = this.readFile.nextLine();
            String[] column = line.split("/");

            Address address = checkAddress(Integer.parseInt(column[2]));
            ArrayList<Address> listAddress = new ArrayList<Address>();
            listAddress.add(address);

            Customer customer = new Customer(column[1], listAddress);
            customers.add(customer);
        }

        this.readFile.close();

        return customers;
    }

    public Address checkAddress(int id){

        for (Address address:addresses) {
            if (address.getAddressId() == id) {
                return address;
            }
        }

        return null;
    }

    public void addCustomer(Customer customer) {

        try {
            this.writeFile = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Address> listAddress = customer.getAddress();
        Address address = listAddress.get(0);
        String data = customer.getCustomerId() +"/"+ customer.getName() +"/"+ address.getAddressId();

        this.writeFile.println(data);
        this.writeFile.close();
    }
}
