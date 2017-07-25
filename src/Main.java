import com.sun.org.apache.xalan.internal.xsltc.dom.EmptyFilter;

import javax.swing.JOptionPane;

public class Main {

    private static Wholesale wholesale;
    private static String currentUser;

    public static void main(String[] args) {
        wholesale = new Wholesale();
        loginScreen();
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
        int yesNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "ALERT", JOptionPane.YES_NO_OPTION);

        if (yesNo == 1) {
            mainMenu();
        } else {
            System.exit(0);
        }
    }
}
