package Service;

import Products.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CategoryManager {

    final private String FILENAME = "data/category.txt";
    private File file;
    private Scanner readFile;
    private FileWriter writeFile;

    public CategoryManager() {
        this.file = new File(FILENAME);
        try {
            this.readFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Category> loadFile()  {

        ArrayList<Category> categories = new ArrayList<Category>();

        while(this.readFile.hasNext())
        {
            String line = this.readFile.nextLine();
            String[] column = line.split("/");

            Category category = new Category(column[1]);
            categories.add(category);
        }

        this.readFile.close();

        return categories;
    }

    public void addCategory(Category category) throws IOException {

        this.writeFile = new FileWriter(file);

        String data = category.getCategoryId() + "/" + category.getName();
        data += "\n";

        this.writeFile.write(data);
        this.writeFile.close();
    }
}
