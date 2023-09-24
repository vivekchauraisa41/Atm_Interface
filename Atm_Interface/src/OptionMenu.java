import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

    public class OptionMenu {
        Scanner menuInput = new Scanner(System.in);
        DecimalFormat moneyFormat = new DecimalFormat("'₹'###,##0.00");
        HashMap<Integer, Account> data = new HashMap<Integer, Account>();

        public void getLogin() throws IOException {
            boolean end = false;
            int customerNumber = 0;
            int pinNumber = 0;
            while (!end) {
                try {
                    System.out.print("\n Enter your customer number: ");
                    customerNumber = menuInput.nextInt();
                    System.out.print("\n Enter your PIN number: ");
                    pinNumber = menuInput.nextInt();
                    Iterator it = data.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        Account acc = (Account) pair.getValue();
                        if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()) {
                            getAccountType(acc);
                            end = true;
                            break;
                        }
                    }
                    if (!end) {
                        System.out.println("\n Wrong Customer Number or Pin Number");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid Character(s). Only Numbers.");
                    menuInput .nextLine();
                }
            }
        }

        public void getAccountType(Account acc) {
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\n Select the account you want to access: ");
                    System.out.println(" Type 1 - Checking Account");
                    System.out.println(" Type 2 - Savings Account");
                    System.out.println(" Type 3 - Exit");
                    System.out.print("\n Choice: ");

                    int selection = menuInput.nextInt();

                    switch (selection) {
                        case 1:
                            getChecking(acc);
                            break;
                        case 2:
                            getSaving(acc);
                            break;
                        case 3:
                            end = true;
                            break;
                        default:
                            System.out.println("\n Invalid Choice.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid Choice.");
                    menuInput.next();
                }
            }
        }

        public void getChecking(Account acc) {
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\n Checking Account: ");
                    System.out.println(" Type 1 - View Balance");
                    System.out.println(" Type 2 - Withdraw Funds");
                    System.out.println(" Type 3 - Deposit Funds");
                    System.out.println(" Type 4 - Transfer Funds");
                    System.out.println(" Type 5 - Exit");
                    System.out.print("\n Choice: ");

                    int selection = menuInput.nextInt();

                    switch (selection) {
                        case 1:
                            System.out.println("\n Checking Account Balance: " + moneyFormat.format(acc.getCheckingBalance()));
                            break;
                        case 2:
                            acc.getCheckingWithdrawInput();
                            break;
                        case 3:
                            acc.getCheckingDepositInput();
                            break;

                        case 4:
                            acc.getTransferInput("Checking");
                            break;
                        case 5:
                            end = true;
                            break;
                        default:
                            System.out.println("\n Invalid Choice.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid Choice.");
                    menuInput.next();
                }
            }
        }

        public void getSaving(Account acc) {
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\n Savings Account: ");
                    System.out.println(" Type 1 - View Balance");
                    System.out.println(" Type 2 - Withdraw Funds");
                    System.out.println(" Type 3 - Deposit Funds");
                    System.out.println(" Type 4 - Transfer Funds");
                    System.out.println(" Type 5 - Exit");
                    System.out.print("Choice: ");
                    int selection = menuInput.nextInt();
                    switch (selection) {
                        case 1:
                            System.out.println("\n Savings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
                            break;
                        case 2:
                            acc.getsavingWithdrawInput();
                            break;
                        case 3:
                            acc.getSavingDepositInput();
                            break;
                        case 4:
                            acc.getTransferInput("Savings");
                            break;
                        case 5:
                            end = true;
                            break;
                        default:
                            System.out.println("\n Invalid Choice.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid Choice.");
                    menuInput.next();
                }
            }
        }

        public void createAccount() throws IOException {
            int cst_no = 0;
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\n Enter your customer number ");
                    cst_no = menuInput.nextInt();
                    Iterator it = data.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        if (!data.containsKey(cst_no)) {
                            end = true;
                        }
                    }
                    if (!end) {
                        System.out.println("\n This customer number is already registered");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid Choice.");
                    menuInput.next();
                }
            }
            System.out.println("\n Enter PIN to be registered");
            int pin = menuInput.nextInt();
            data.put(cst_no, new Account(cst_no, pin));
            System.out.println("\n Your new account has been successfully registered!");
            System.out.println("\n Redirecting to login.............");
            getLogin();
        }

        public void mainMenu() throws IOException {
            data.put(88407, new Account(88407,38327 , 10000, 50000));
            data.put(123, new Account(123, 123, 20000, 50000));
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\n Type 1 - Login");
                    System.out.println("\n Type 2 - Create Account");
                    System.out.println("\n Choice: ");
                    int choice = menuInput.nextInt();
                    switch (choice) {
                        case 1:
                            getLogin();
                            end = true;
                            break;
                        case 2:
                            createAccount();
                            end = true;
                            break;
                        default:
                            System.out.println("\n Invalid Choice.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid Choice.");
                    menuInput.next();
                }
            }
            System.out.println("\n Thank You for using this ATM. \n ");
            menuInput.close();
            System.exit(0);
        }
    }
