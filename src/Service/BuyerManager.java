package Service;

import People.Address;
import People.Buyer;
import Products.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class BuyerManager {

    final private String FILENAME = "data/buyer.txt";
    private File file;
    private Scanner readFile;
    private PrintWriter writeFile;

    private ArrayList<Address> addresses;
    private ArrayList<Product> products;

    public BuyerManager() {
        this.file = new File(FILENAME);
        try {
            this.readFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Buyer> loadFile(ArrayList<Product> products, ArrayList<Address> addresses)  {

        this.addresses = addresses;
        this.products = products;

        ArrayList<Buyer> buyers = new ArrayList<Buyer>();

        while(this.readFile.hasNext())
        {
            String line = this.readFile.nextLine();
            String[] column = line.split("/");

            Address address = checkAddress(Integer.parseInt(column[2]));
            ArrayList<Address> listAddress = new ArrayList<Address>();
            listAddress.add(address);

            HashMap<String, Product> listProduct = new HashMap<String, Product>();

            Product prod1 = checkProduct(Integer.parseInt(column[3]));
            listProduct.put(column[4], prod1);

            Product prod2 = checkProduct(Integer.parseInt(column[5]));
            listProduct.put(column[6], prod2);

            Buyer buyer = new Buyer(column[1], listAddress, listProduct);
            buyers.add(buyer);

        }

        this.readFile.close();

        return buyers;
    }

    public Product checkProduct(int id){

        for (Product product:products) {
            if (product.getProductId() == id) {
                return product;
            }
        }

        return null;
    }

    public Address checkAddress(int id){

        for (Address address:addresses) {
            if (address.getAddressId() == id) {
                return address;
            }
        }

        return null;
    }

    public void addBuyer(Buyer buyer) {

        try {
            this.writeFile = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Address> listAddress = buyer.getAddress();
        Address address = listAddress.get(0);

        HashMap<String, Product> listProduct = buyer.getProducts();
        Iterator<String> keySetIterator = listProduct.keySet().iterator();

        String data = buyer.getBuyerId() +"/"+ buyer.getName() +"/"+ address.getAddressId();

        while(keySetIterator.hasNext()) {
            String price = keySetIterator.next();
            Product prod = listProduct.get(price);

            data += "/" + prod.getProductId() + "/" + price;
        }

        this.writeFile.println(data);
        this.writeFile.close();
    }

}
