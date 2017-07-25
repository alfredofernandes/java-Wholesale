package Service;

import People.Customer;
import Products.Product;
import Transactions.DetailTransaction;
import Transactions.Sale;
import Transactions.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class SaleHistoryManager {

    final private String FILENAME = "data/transactions_sale.txt";
    private File file;
    private Scanner readFile;
    private FileWriter writeFile;

    private ArrayList<Customer> customers;
    private ArrayList<Product> products;

    private SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

    public SaleHistoryManager() {
        this.file = new File(FILENAME);
        try {
            this.readFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sale> loadFile(ArrayList<Customer> customers, ArrayList<Product> products) throws ParseException {

        this.customers = customers;
        this.products = products;

        ArrayList<Sale> sales = new ArrayList<Sale>();

        while(this.readFile.hasNext())
        {
            String line = this.readFile.nextLine();
            String[] infos = line.split("-");

            String[] column = infos[0].split("/");
            String[] details = infos[1].split(";");

            Customer customer = getCustomer(Integer.parseInt(column[1]));

            Sale sale = new Sale(customer);
            sale.setOrderDate(formatter.parse(column[2]));
            sale.setRequiredDate(formatter.parse(column[3]));
            sale.setShippedDate(formatter.parse(column[4]));

            int status = Integer.parseInt(column[5]);
            sale.setStatus(getStatus(status));

            sale.setType(Transaction.TypeTransaction.SALE);

            sale.setPayment(Boolean.valueOf(column[7]));
            sale.setDiscount(Double.parseDouble(column[8]));

            ArrayList<DetailTransaction> detailsSale = new ArrayList<>();
            for (int i=0; i < details.length; i++) {

                String[] detail = details[i].split("/");

                Product product = getProduct(Integer.parseInt(detail[1]));
                int quantity = Integer.parseInt(detail[2]);
                double price = Double.parseDouble(detail[3]);
                double discount = Double.parseDouble(detail[4]);

                DetailTransaction det = new DetailTransaction(product, quantity, price, discount);
                detailsSale.add(det);
            }

            sale.setDetails(detailsSale);

            // add sale in array
            sales.add(sale);
        }

        this.readFile.close();

        return sales;
    }

    public Transaction.StatusTransaction getStatus(int value) {

        // 0 - PENDING, 1 - CANCELED, 2 - CLOSED, 3 - DELIVERED
        if (value == 0) {
            return Transaction.StatusTransaction.PENDING;

        } else if (value == 1) {
            return Transaction.StatusTransaction.CANCELED;

        } else if (value == 2) {
            return Transaction.StatusTransaction.CLOSED;

        } else if (value == 3) {
            return Transaction.StatusTransaction.DELIVERED;
        }

        return Transaction.StatusTransaction.PENDING;
    }

    public Customer getCustomer(int customerID) {

        for (Customer customer: this.customers) {
            if (customer.getCustomerId() == customerID) {
                return customer;
            }
        }

        return null;
    }

    public Product getProduct(int productID) {

        for (Product product: this.products) {
            if (product.getProductId() == productID) {
                return product;
            }
        }

        return null;
    }

    public void addTransactionSale(Sale sale) throws IOException {

        this.writeFile = new FileWriter(file, true);

        //orderID/customerID/orderDate(DDMMYYYY)/requiredDate(DDMMYYYY)/shippedDate(DDMMYYYY)/status(0-3)/type(0-1)/payment(0-1)/discount-details[detailID/productID/quantity/priceEach/discount&]

        Customer customer = sale.getCustomer();
        String dateOrder = "01012017";
        String dateRequired = "01012017";
        String dateShipped = "01012017";

        try{
            dateOrder = formatter.format(sale.getOrderDate());
            dateRequired = formatter.format(sale.getRequiredDate());

            dateShipped = formatter.format(sale.getShippedDate());
        }catch (Exception ex ){
            System.out.println(ex);
        }

        String data = sale.getOrderID() +"/"+ customer.getCustomerId() +"/"+ dateOrder +"/"+ dateRequired +"/"+ dateShipped +"/"+ getStatusValue(sale.getStatus()) +"/1/"+ sale.isPayment() +"/"+ sale.getDiscount() +"-";

        ArrayList<DetailTransaction> details = sale.getDetails();
        for(int i=0; i < details.size(); i++) {
            //detailID/productID/quantity/priceEach/discount

            DetailTransaction detail = details.get(i);
            Product product = detail.getProduct();

            data += detail.getDetailID() +"/"+ product.getProductId() +"/"+ detail.getQuantity() +"/"+ detail.getPriceEach() +"/"+ detail.getDiscount();

            if (i < details.size()-1) {
                data += ";";
            }
        }

        data += "\n";

        this.writeFile.write(data);
        this.writeFile.close();
    }

    public int getStatusValue(Transaction.StatusTransaction status) {

        // 0 - PENDING, 1 - CANCELED, 2 - CLOSED, 3 - DELIVERED
        if (status == Transaction.StatusTransaction.PENDING) {
            return 0;

        } else if (status == Transaction.StatusTransaction.CANCELED) {
            return 1;

        } else if (status == Transaction.StatusTransaction.CLOSED) {
            return 2;

        } else if (status == Transaction.StatusTransaction.DELIVERED) {
            return 3;
        }

        return 0;
    }
}
