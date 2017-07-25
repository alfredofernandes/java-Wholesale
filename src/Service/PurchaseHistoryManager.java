package Service;

import People.Buyer;
import Products.Product;
import Transactions.DetailTransaction;
import Transactions.Purchase;
import Transactions.Transaction;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class PurchaseHistoryManager {

    final private String FILENAME = "data/transactions_purchase.txt";
    private File file;
    private Scanner readFile;
    private FileWriter writeFile;

    private ArrayList<Buyer> buyers;
    private ArrayList<Product> products;

    private SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

    public PurchaseHistoryManager() {
        this.file = new File(FILENAME);
        try {
            this.readFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Purchase> loadFile(ArrayList<Buyer> buyers, ArrayList<Product> products) throws ParseException {

        this.buyers = buyers;
        this.products = products;

        ArrayList<Purchase> purchases = new ArrayList<Purchase>();

        while(this.readFile.hasNext())
        {
            String line = this.readFile.nextLine();
            String[] infos = line.split("-");

            String[] column = infos[0].split("/");
            String[] details = infos[1].split(";");

            Buyer buyer = getBuyer(Integer.parseInt(column[1]));

            Purchase purchase = new Purchase(buyer);
            purchase.setOrderDate(formatter.parse(column[2]));
            purchase.setRequiredDate(formatter.parse(column[3]));
            purchase.setShippedDate(formatter.parse(column[4]));

            int status = Integer.parseInt(column[5]);
            purchase.setStatus(getStatus(status));

            purchase.setType(Transaction.TypeTransaction.PURCHASE);

            purchase.setPayment(Boolean.getBoolean(column[7]));
            purchase.setDiscount(Double.parseDouble(column[8]));

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

            purchase.setDetails(detailsSale);

            // add purchase in array
            purchases.add(purchase);
        }

        this.readFile.close();

        return purchases;
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

    public Buyer getBuyer(int buyerID) {

        for (Buyer buyer: this.buyers) {
            if (buyer.getBuyerId() == buyerID) {
                return buyer;
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

    public void addTransactionPurchase(Purchase purchase) throws IOException {

        this.writeFile = new FileWriter(file, true);

        //orderID/customerID/orderDate(DDMMYYYY)/requiredDate(DDMMYYYY)/shippedDate(DDMMYYYY)/status(0-3)/type(0-1)/payment(0-1)/discount-details[detailID/productID/quantity/priceEach/discount&]

        Buyer buyer = purchase.getBuyer();
        String dateOrder = "01012017";
        String dateRequired = "01012017";
        String dateShipped = "01012017";

        try{
            dateOrder = formatter.format(purchase.getOrderDate());
            dateRequired = formatter.format(purchase.getRequiredDate());
            dateShipped = formatter.format(purchase.getShippedDate());
        }catch (Exception ex ){
            System.out.println(ex);
        }

        String data = purchase.getOrderID() +"/"+ buyer.getBuyerId() +"/"+ dateOrder +"/"+ dateRequired +"/"+ dateShipped +"/"+ getStatusValue(purchase.getStatus()) +"/0/"+ purchase.isPayment() +"/"+ purchase.getDiscount() +"-";

        ArrayList<DetailTransaction> details = purchase.getDetails();
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
