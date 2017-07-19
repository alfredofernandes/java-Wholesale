package Product;

public class Stock {

    static int numID = 0;
    private int stockId;
    private int quantity;
    private Double priceEach;
    private Product product;

    public Stock(int quantity, Double priceEach, Product product) {
        Stock.numID += 1;
        this.stockId = Stock.numID;
        this.quantity = quantity;
        this.priceEach = priceEach;
        this.product = product;
    }

    public int getStockId() {
        return stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(Double priceEach) {
        this.priceEach = priceEach;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void addQuantity(int amount) {
        this.quantity = quantity + amount;
    }

    public void removeQuantity(int amount) {
        this.quantity = quantity - amount;
    }

    public boolean checkQuantity(int amount) {
        if ((this.quantity - amount) > 0) {
            return true;
        }
        return false;
    }

}