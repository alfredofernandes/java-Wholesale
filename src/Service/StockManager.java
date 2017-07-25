package Service;

import Products.Product;
import Products.Stock;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StockManager {

    final private String FILENAME = "data/stock.txt";
    private File file;
    private Scanner readFile;
    private FileWriter writeFile;

    private ArrayList<Product> products;

    public StockManager() {
        this.file = new File(FILENAME);
        try {
            this.readFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Stock> loadFile(ArrayList<Product> products)  {

        this.products = products;
        ArrayList<Stock> stocks = new ArrayList<Stock>();

        while(this.readFile.hasNext())
        {
            String line = this.readFile.nextLine();
            String[] column = line.split("/");

            Product product = checkProduct(Integer.parseInt(column[1]));
            Stock stock = new Stock(Integer.parseInt(column[2]), Double.parseDouble(column[3]), product);
            stocks.add(stock);
        }

        this.readFile.close();

        return stocks;
    }

    public Product checkProduct(int id){

        for (Product product:products) {
            if (product.getProductId() == id) {
                return product;
            }
        }

        return null;
    }

    public void addStock(Stock stock) throws IOException {

        this.writeFile = new FileWriter(file);

        Product product = stock.getProduct();
        String data = stock.getStockId() +"/"+ product.getProductId() +"/"+ stock.getQuantity() +"/" + stock.getPriceEach();
        data += "\n";

        this.writeFile.write(data);
        this.writeFile.close();
    }
}
