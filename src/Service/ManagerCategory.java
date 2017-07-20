package Service;

import Product.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerCategory {

    private String fileName = "category.txt";

    public ArrayList<Category> loadFile() throws FileNotFoundException {

        File file = new File(fileName);
        Scanner readFile = new Scanner(file);

        ArrayList<Category> categories = new ArrayList<Category>();

        while(readFile.hasNext())
        {
            String line = readFile.nextLine();
            String[] column = line.split("/");

            Category category = new Category(column[1]);
            categories.add(category);
        }

        return  categories;
    }
}
