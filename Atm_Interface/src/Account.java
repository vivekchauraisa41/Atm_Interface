import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

    public class Account {
        private int CustomerNumber;
        private int PinNumber;
        private double BalanceChecking = 0;
        private double SavingBalance = 0;

        Scanner input = new Scanner(System.in);
        DecimalFormat Moneyformat = new DecimalFormat("'₹'###,##0.00");

        public Account() {
        }

        public Account(int customerNumber, int pinNumber) {
            this.CustomerNumber = customerNumber;
            this.PinNumber = pinNumber;
        }

        public Account(int customerNumber, int pinNumber, double checkingBalance, double savingBalance) {
            this.CustomerNumber = customerNumber;
            this.PinNumber = pinNumber;
            this.BalanceChecking = checkingBalance;
            this.SavingBalance = savingBalance;
        }

        public int setCustomerNo(int customerNumber) {
            this.CustomerNumber = customerNumber;
            return customerNumber;
        }

        public int getCustomerNo() {
            return CustomerNumber;
        }

        public int SetPinNumber(int pinNumber) {
            this.PinNumber = pinNumber;
            return pinNumber;
        }

        public int getPinNumber() {
            return PinNumber;
        }

        public double getCheckingBalance() {
            return BalanceChecking;
        }

        public double getSavingBalance() {
            return SavingBalance;
        }

        public double calcCheckingWithdraw(double amount) {
            BalanceChecking = (BalanceChecking - amount);
            return BalanceChecking;
        }

        public double calcSavingWithdraw(double amount) {
            SavingBalance = (SavingBalance - amount);
            return SavingBalance;
        }

        public double calcCheckingDeposit(double amount) {
            BalanceChecking = (BalanceChecking + amount);
            return BalanceChecking;
        }

        public double calcSavingDeposit(double amount) {
            SavingBalance = (SavingBalance + amount);
            return SavingBalance;
        }

        public void calcCheckTransfer(double amount) {
            BalanceChecking = BalanceChecking - amount;
            SavingBalance = SavingBalance + amount;
        }

        public void calcSavingTransfer(double amount) {
            SavingBalance = SavingBalance - amount;
            BalanceChecking = BalanceChecking + amount;
        }

        public void getCheckingWithdrawInput() {
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\n Current checking account balance: " + Moneyformat.format(BalanceChecking));
                    System.out.print("\n Amount you want to withdraw from checking account: ");
                    double amount = input.nextDouble();
                    if ((BalanceChecking - amount) >= 0 && amount >= 0) {
                        calcCheckingWithdraw(amount);
                        System.out.println("\n Current checking account balance: " + Moneyformat.format(BalanceChecking));
                        end = true;
                    } else {
                        System.out.println("\n Balance Cannot be negative.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid choice.");
                    input.next();
                }
            }
        }

        public void getsavingWithdrawInput() {
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\n Current savings account balance: " + Moneyformat.format(SavingBalance));
                    System.out.print("\n Amount you want to withdraw from savings account: ");
                    double amount = input.nextDouble();
                    if ((SavingBalance - amount) >= 0 && amount >= 0) {
                        calcSavingWithdraw(amount);
                        System.out.println("\n Current savings account balance: " + Moneyformat.format(SavingBalance));
                        end = true;
                    } else {
                        System.out.println("\n Balance cannot Be negative.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid choice.");
                    input.next();
                }
            }
        }

        public void getCheckingDepositInput() {
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\n Current checking account balance: " + Moneyformat.format(BalanceChecking));
                    System.out.print("\n Amount you want to deposit: ");
                    double amount = input.nextDouble();
                    if ((BalanceChecking + amount) >= 0 && amount >= 0) {
                        calcCheckingDeposit(amount);
                        System.out.println("\n Current checking account balance: " + Moneyformat.format(BalanceChecking));
                        end = true;
                    } else {
                        System.out.println("\n Balance cannot Be negative.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid choice.");
                    input.next();
                }
            }
        }

        public void getSavingDepositInput() {
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\n Current savings account balance: " + Moneyformat.format(SavingBalance));
                    System.out.print("\n Amount you want to deposit in savings account: ");
                    double amount = input.nextDouble();

                    if ((SavingBalance + amount) >= 0 && amount >= 0) {
                        calcSavingDeposit(amount);
                        System.out.println("\n Current savings account balance: " + Moneyformat.format(SavingBalance));
                        end = true;
                    } else {
                        System.out.println("\n Balance cannot Be negative.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid choice.");
                    input.next();
                }
            }
        }

        public void getTransferInput(String accType) {
            boolean end = false;
            while (!end) {
                try {
                    if (accType.equals("Checking")) {
                        System.out.println("\n Select an account you want to transfer funds to:");
                        System.out.println("1. Savings");
                        System.out.println("2. Exit");
                        System.out.print("\n Choice: ");
                        int choice = input.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println("\n Current checking account balance: " + Moneyformat.format(BalanceChecking));
                                System.out.print("\n Amount you want to deposit into Savings Account: ");
                                double amount = input.nextDouble();
                                if ((SavingBalance + amount) >= 0 && (BalanceChecking - amount) >= 0 && amount >= 0) {
                                    calcCheckTransfer(amount);
                                    System.out.println("\n Current savings account balance: " + Moneyformat.format(SavingBalance));
                                    System.out.println(
                                            "\n Current checking account balance: " + Moneyformat.format(BalanceChecking));
                                    end = true;
                                } else {
                                    System.out.println("\n Balance cannot be negative.");
                                }
                                break;
                            case 2:
                                return;
                            default:
                                System.out.println("\n Invalid Choice.");
                                break;
                        }
                    } else if (accType.equals("Savings")) {
                        System.out.println("\n Select an account you want to transfers funds to: ");
                        System.out.println("1. Checking");
                        System.out.println("2. Exit");
                        System.out.print("\n Choice: ");
                        int choice = input.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println("\n Current savings account balance: " + Moneyformat.format(SavingBalance));
                                System.out.print("\n Amount you want to deposit into your savings account: ");
                                double amount = input.nextDouble();
                                if ((BalanceChecking + amount) >= 0 && (SavingBalance - amount) >= 0 && amount >= 0) {
                                    calcSavingTransfer(amount);
                                    System.out.println("\n Current checking account balance: " + Moneyformat.format(BalanceChecking));
                                    System.out.println("\n Current savings account balance: " + Moneyformat.format(SavingBalance));
                                    end = true;
                                } else {
                                    System.out.println("\n Balance cannot be negative.");
                                }
                                break;
                            case 2:
                                return;
                            default:
                                System.out.println("\n Invalid Choice.");
                                break;
                        }}
                } catch (InputMismatchException e) {
                    System.out.println("\n Invalid Choice.");
                    input.next();
                }
            }}}
