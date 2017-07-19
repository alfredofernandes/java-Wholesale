/**
 Category.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

package Product;

public class Category {

    static int numID = 0;
    private String name;
    private int categoryId;

    public Category(String name) {
        Category.numID += 1;
        this.categoryId = Category.numID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

}