package Service;

import People.Address;
import People.Buyer;
import Products.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class BuyerManager {

    final private String FILENAME = "data/buyer.txt";
    private File file;
    private Scanner readFile;
    private FileWriter writeFile;

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
            String[] infos = line.split("-");

            String[] column = infos[0].split("/");
            String[] productsBuyer = infos[1].split(";");

            Address address = checkAddress(Integer.parseInt(column[2]));
            ArrayList<Address> listAddress = new ArrayList<Address>();
            listAddress.add(address);

            HashMap<String, Product> listProduct = new HashMap<String, Product>();

            for (int i=0; i < productsBuyer.length; i++) {

                String[] product = productsBuyer[i].split("/");
                Product prod = checkProduct(Integer.parseInt(product[0]));
                listProduct.put(product[1], prod);
            }

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

    public void addBuyer(Buyer buyer) throws IOException {

        this.writeFile = new FileWriter(file);

        ArrayList<Address> listAddress = buyer.getAddress();
        Address address = listAddress.get(0);

        HashMap<String, Product> listProduct = buyer.getProducts();
        Iterator<String> keySetIterator = listProduct.keySet().iterator();

        String data = buyer.getBuyerId() +"/"+ buyer.getName() +"/"+ address.getAddressId() +"-";

        while(keySetIterator.hasNext()) {
            String price = keySetIterator.next();
            Product prod = listProduct.get(price);

            data += prod.getProductId() + "/" + price + ";";
        }

        data += "\n";

        this.writeFile.write(data);
        this.writeFile.close();
    }

}
