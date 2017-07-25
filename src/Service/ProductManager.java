package Service;

import Products.Category;
import Products.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {

    final private String FILENAME = "data/product.txt";
    private File file;
    private Scanner readFile;
    private PrintWriter writeFile;

    private ArrayList<Category> categories;

    public ProductManager() {
        this.file = new File(FILENAME);
        try {
            this.readFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> loadFile(ArrayList<Category> categories)  {

        this.categories = categories;
        ArrayList<Product> products = new ArrayList<Product>();

        while(this.readFile.hasNext())
        {
            String line = this.readFile.nextLine();
            String[] column = line.split("/");

            Category category = checkCategory(Integer.parseInt(column[3]));
            Product product = new Product(column[1], column[2], category);
            products.add(product);
        }

        this.readFile.close();

        return products;
    }

    public Category checkCategory(int id){

        for (Category category:categories) {
            if (category.getCategoryId() == id) {
                return category;
            }
        }

        return null;
    }

    public void addProduct(Product product) {

        try {
            this.writeFile = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Category category = product.getCategory();
        String data = product.getProductId() +"/"+ product.getName() +"/"+ product.getDescription() +"/" + category.getCategoryId();

        this.writeFile.println(data);
        this.writeFile.close();
    }

}
