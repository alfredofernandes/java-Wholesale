import Products.Product;
import Products.Stock;
import Transactions.DetailTransaction;
import Transactions.Sale;
import com.sun.org.apache.xalan.internal.xsltc.dom.EmptyFilter;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Main {

    private static Wholesale wholesale;
    private static String currentUser;

    public static void main(String[] args) {
        wholesale = new Wholesale();

        loadData();
        loginScreen();
    }

    private static void loadData(){
        

    }

    // LOGIN SCREEN
    private static void loginScreen() {

        String nameUser = JOptionPane.showInputDialog("WHOLESALE SYSTEM \n\nPlease enter your name:");

        if (nameUser == null) {
            System.exit(0);

        } else if (nameUser.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Please enter your name!");
            loginScreen();

        } else {
            boolean checkExist = wholesale.existCustomer(nameUser);

            if (checkExist) {
                currentUser = nameUser;
                mainMenu();

            } else {
                JOptionPane.showMessageDialog(null, "Sorry, this user doesn't exist. Try again!");
                loginScreen();
            }
        }

    }

    // MAIN MENU
    private static void mainMenu() {

        String inputMenu = JOptionPane.showInputDialog("WHOLESALE SYSTEM \n\nHi, " + currentUser + "! Select an option: " +
                "\n   1. Buy " +
                "\n   2. Reports " +
                "\n   3. Change user " +
                "\n   4. Quit");

        if (inputMenu == null) {
            quit();

        } else {

            char mainMenuChoice = inputMenu.charAt(0);
            switch (mainMenuChoice) {
                case '1':
                    startBuy();
                    break;
                case '2':
                    showReports();
                    break;
                case '3':
                    loginScreen();
                    break;
                case '4':
                    quit();
                    break;
            }
        }
    }

    // 1. BUY
    private static void startBuy() {

        int orderSale = wholesale.orderSale(currentUser);
        selectProductsBuy(orderSale);

    }

    private static void selectProductsBuy(int orderSale){

        ArrayList<Stock> stocks = wholesale.getStocks();
        String showProduct = "WHOLESALE BUY \n\nSelect one product: ";

        for (int i=0; i < stocks.size(); i++){

            Stock stock = stocks.get(i);
            String name = stock.getProduct().getName();
            String price = String.valueOf(stock.getPriceEach());

            showProduct += "\n   " + (i+1) + " - " + name + " - $" + price;
        }

        showProduct += "\n\n 0 - Finish order";

        String inputProducts = JOptionPane.showInputDialog(showProduct);

        if (inputProducts == null) {

            int yesNo = showAlert("Do you want to cancel this order?");
            if (yesNo == 0) {

                wholesale.removeOrder(orderSale);
                JOptionPane.showMessageDialog(null, "Your order has been cancelled.");
                mainMenu();

            } else {
                selectProductsBuy(orderSale);
            }

        } else {

            if (inputProducts.compareTo("0") == 0) {
                finishOrder(orderSale);

            } else {

                int positionProduct = Integer.parseInt(inputProducts) -1;
                Stock stock = stocks.get(positionProduct);
                Product product = stock.getProduct();

                String inputQuantity = JOptionPane.showInputDialog("Please, enter the quantity:");

                addProductInOrder(product.getProductId(), Integer.parseInt(inputQuantity), orderSale);

                int yesNo = showAlert("Do you want add another product?");
                if (yesNo == 0) {
                    selectProductsBuy(orderSale);

                } else {
                    finishOrder(orderSale);
                }

            }

        }

    }

    private static void addProductInOrder(int productId, int quantity, int orderSale) {

        ArrayList<Stock> stocks = wholesale.getStocks();

        for (Stock stock:stocks) {
            Product product = stock.getProduct();
            if (product.getProductId() == productId) {
                wholesale.addProductInOrderSale(product, quantity, orderSale);
            }
        }
    }

    private static void finishOrder(int orderSale) {

        String showOrder = "WHOLESALE - YOUR ORDER \n\n";

        Sale sale = wholesale.getOrderSale(orderSale);
        for (DetailTransaction detail: sale.getDetails()) {

            Product product = detail.getProduct();
            showOrder += " - " + detail.getQuantity() + " " + product.getName() + " $" + detail.getPriceEach() + "\n";
        }

        showOrder += "\nTotal: $" + sale.totalPayment();
        showOrder += "\nTotal with discount: $" + sale.totalPaymentWithDiscount() + "\n\n";

        showOrder += "Please, choose one of the following options:\n";
        showOrder += "1. Pay order \n";
        showOrder += "2. Add another item \n";
        showOrder += "3. Cancel order \n";

        String inputProducts = JOptionPane.showInputDialog(showOrder);

        if (inputProducts == null) {

            int yesNo = showAlert("Do you want cancel this order?");
            if (yesNo == 0) {
                mainMenu();
            } else {
                finishOrder(orderSale);
            }

        } else {

            switch (inputProducts) {
                case "1":
                    wholesale.payOrderSale(orderSale);
                    JOptionPane.showMessageDialog(null, "Your order has been paid.");
                    mainMenu();

                    break;
                case "2":
                    selectProductsBuy(orderSale);
                    break;
                case "3":

                    int yesNo = showAlert("Do you want cancel this order?");
                    if (yesNo == 0) {
                        mainMenu();
                    } else {
                        finishOrder(orderSale);
                    }

                    break;
            }
        }

    }

    // 2. REPORTS
    private static void showReports() {

        String inputMenu = JOptionPane.showInputDialog("WHOLESALE REPORTS \n\nSelect an option: " +
                "\n   1. Defaulters list of Customers who has not paid their pending amount" +
                "\n   2. List of payment paid or pending" +
                "\n   3. List of payment and quantity of orders paid" +
                "\n   4. Product shipped in 2017" +
                "\n   5. Product quantity in stock" +
                "\n   6. Sales by different cities" +
                "\n   7. Back to main menu");

        if (inputMenu == null) {
            mainMenu();

        } else {

            char reportMenuChoice = inputMenu.charAt(0);
            switch (reportMenuChoice) {
                case '1':
                    report1();
                    break;
                case '2':
                    report2();
                    break;
                case '3':
                    report3();
                    break;
                case '4':
                    report4();
                    break;
                case '5':
                    report5();
                    break;
                case '6':
                    report6();
                    break;
                case '7':
                    mainMenu();
                    break;
            }
        }

    }

    // DEFAULTER LIST OF CUSTOMERS WHOSE HAS NOT PAID THEIR PENDING AMOUNT
    private static void report1() {

        System.out.println("This is the report 1.");

    }

    // LIST OF PAYMENT PAID OR PENDING
    private static void report2() {

        System.out.println("This is the report 2.");

    }

    // LIST OF PAYMENT AND QUANTITY OF ORDERS PAID
    private static void report3() {

        System.out.println("This is the report 3.");

    }

    // PRODUCTS SHIPPED IN 2017
    private static void report4() {

        System.out.println("This is the report 4.");

    }

    //PRODUCT QUANTITY IN STOCK
    private static void report5() {

        System.out.println("This is the report 5.");

    }

    // SALES BY DIFFERENT CITIES
    private static void report6() {

        System.out.println("This is the report 6.");

    }

    // ALERT: MAKE SURE USER WANT TO QUIT THE PROGRAM
    private static void quit() {
        int yesNo = showAlert("Are you sure you want to quit?");

        if (yesNo == 0) {
            System.exit(0);
        } else {
            mainMenu();
        }
    }

    private static int showAlert(String message) {

        int yesNo = JOptionPane.showConfirmDialog(null, message, "ALERT", JOptionPane.YES_NO_OPTION);
        return yesNo;
    }
}
