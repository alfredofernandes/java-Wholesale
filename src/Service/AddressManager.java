package Service;

import People.Address;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressManager {

    final private String FILENAME = "data/address.txt";
    private File file;
    private Scanner readFile;
    private FileWriter writeFile;

    public AddressManager() {
        this.file = new File(FILENAME);
        try {
            this.readFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Address> loadFile()  {

        ArrayList<Address> addresses = new ArrayList<Address>();

        while(this.readFile.hasNext())
        {
            String line = this.readFile.nextLine();
            String[] column = line.split("/");

            Address address = new Address(column[1], column[2], column[3], column[4], column[5]);
            addresses.add(address);
        }

        this.readFile.close();

        return addresses;
    }

    public void addAddress(Address address) throws IOException {

        this.writeFile = new FileWriter(file);

        String data = address.getAddressId() + "/" + address.getStreet() + "/" + address.getCity() + "/" + address.getProvince() + "/" + address.getZipCode() + "/" + address.getCountry();
        data += "\n";

        this.writeFile.write(data);
        this.writeFile.close();
    }

}
