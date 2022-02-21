package com.revature;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;


public class Main {

    static String url = "jdbc:mysql://localhost:3306/proj1";
    static String username = "root";
    static String password = "T0gether4!";
    //private static Dao<uzer> userDao;

    private static final String CREATE_TABLE_SQL = "CREATE TABLE customer (" + "ACCNT_ID INT AUTO_INCREMENT," + "OWNERNAME VARCHAR(50)," +
            "EMAIL VARCHAR(50)," + "CBALANCE DOUBLE," + "SBALANCE DOUBLE," + "PRIMARY KEY (ACCNT_ID))";


    //bank info
    interface CstmrBankAccount {
        boolean deposit(int amount);

        boolean withdraw(int amount);

        void getBalance();


    }

    //bank stufff

    static class SavingAccount implements CstmrBankAccount {


        Scanner scan = new Scanner(System.in);
        private int _balance;
        private int _perDayLimit;


        @Override
        public boolean deposit(int amount) {
            _balance += amount;
            System.out.println("How much will you be depositing today?");
            amount = scan.nextInt();

            System.out.println("Successfully deposited:" + amount);
            //System.out.println("Would you like to do another transaction?");

            return true;
        }

        @Override
        public boolean withdraw(int amount) {
            if (_balance < amount) {
                System.out.println("Insufficient fund");
                return false;
            }
            // limit 5000
            else if (_perDayLimit + amount > 5000) {
                System.out.println("Withdraw attempt has failed");
                return false;
            } else {
                _balance -= amount;
                System.out.println("Successfully withdraw: " + amount);
                return true;
            }
        }

        @Override
        public void getBalance() {
            System.out.println("Savings account balance: " + _balance);
        }
    }

    static class CurrentAccount implements CstmrBankAccount {
        private int _balance;

        @Override
        public boolean deposit(int amount) {
            _balance += amount;
            System.out.println("Successfully deposited: " + amount);
            return true;
        }

        @Override
        public boolean withdraw(int amount) {
            if (_balance < amount) {
                System.out.println("Insufficient fund");
                return false;
            } else {
                _balance -= amount;
                System.out.println("Successfully withdraw: " + amount);
                return true;
            }
        }

        @Override
        public void getBalance() {
            System.out.println("Current account balance: " + _balance);
            System.out.print("Your available balance is " + _balance);
        }
    }

    public static void main(String[] args) throws SQLException {
        // write your code here

       //create customer table

        Statement stmnt = null;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            stmnt = conn.createStatement();

            stmnt.executeUpdate(CREATE_TABLE_SQL);
            System.out.println("Table created successfully");
            String sql = "INSERT INTO customer VALUES (1040, 'Amber Lewis', 'a@aol.com', 2000.00, 0.00)";
            stmnt.executeUpdate(sql);
            sql = "INSERT INTO customer VALUES (001099, 'James Brown', 'jb@aol.com', 500.00, 1700.00)";
            stmnt.executeUpdate(sql);
            sql = "INSERT INTO customer VALUES (000711, 'Marc Anthony', 'm@aol.com', 200000.00, 96000.00)";
            stmnt.executeUpdate(sql);
            sql = "INSERT INTO customer VALUES (001041, 'Kai-Lynn Harris', 'kai@gmail.com', 5100.00, 25000.00)";
            stmnt.executeUpdate(sql);
            sql = "ALTER TABLE customer ADD FOREIGN KEY (OWNERNAME) REFERENCES customer(EMAIL)";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connection conn = null;
            try {
                if (stmnt != null) {
                    stmnt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Scanner scan = new Scanner(System.in);

            System.out.println("Welcome to Bank App USA");
            System.out.println("\n");
            System.out.println("Please select from the menu below: \n");
            System.out.println("1-Create an account\n 2-I am a current customer\n 3-I am an employee");
            int num = scan.nextInt();

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "T0gether4!");

            } catch (SQLException e) {
                System.out.println(e);
            }

            //Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            //con = connection.connects();

            switch (num) {
                case 3: {
                    System.out.println("Welcome back. Let's see if there are any transactions to approve today.");
//employee username
                    System.out.println("Please enter your username");
                    String cuid = scan.next();
                    //employee password
                    System.out.println("Please enter your password");
                    String cpass = scan.next();

                    //sql junk

                    try {
                        String sql = "select * from cstlogin where user_type='emp' and username=? and password=?";

                        //sql command for stored data in string form
                        p = conn.prepareStatement(sql);
                        p.setString(1, cuid);
                        p.setString(2, cpass);


                        rs = p.executeQuery();
                        if (!rs.next() ) {
                            System.out.println("Oops! try again");
                        } else {
                            System.out.println("Yayyy");
                            System.out.println("Here are your actions for today: ");
                        }

                    //fetch info

                    } catch (SQLException | NullPointerException e) {
                        e.printStackTrace();
                    }
                    //uzer dao
                    UzerDao dao = UzerDaoFactory.getUzerDao();
                    Bankuser uzers = new Bankuser(00256, "Rich Darius", "richied@aol.com");
                    dao.addUzer(uzers);
                    break;
                }
                case 2: {
                    System.out.println("Welcome back");
                    //cstmr username
                    System.out.println("Please enter your username");
                    String cuid = scan.next();
                    //cstmr password
                    System.out.println("Please enter your password");
                    String cpass = scan.next();

                    //sql stuff

                    try {
                        String sql = "select * from cstlogin";

                        //sql command for stored data in string form

                        p.setString(1, cuid);
                        p.setString(2, cpass);
                        rs = p.executeQuery();
                        if (!rs.next() ) {
                            System.out.println("Oops! Try again");
                        } else {
                            System.out.println("Yayyy");
                        }

                    } catch (SQLException | NullPointerException e) {
                        e.printStackTrace();
                    }

                    //access account
                    char cont  ='y';
                    do {

                    scan = new Scanner(System.in);
                    System.out.println("Would you like to make a deposit or a withdrawal? Select 1 for deposit or 2 for withdrawal.");
                    int option = scan.nextInt();


                    System.out.println("You selected " + option);
                    System.out.println("Please select checking (c) or savings (s) ->");
                    char a = scan.next().charAt(0);

                    //transactions
                        CstmrBankAccount savingAccount = new SavingAccount();
                        CstmrBankAccount currentAccount = new CurrentAccount();

                        savingAccount.deposit(6000); // 7000
                        savingAccount.withdraw(6000); // x
                        savingAccount.getBalance(); // 7000

                        System.out.println();

                        currentAccount.deposit(4000);
                        currentAccount.withdraw(6000);
                        currentAccount.getBalance();

                        //dao
                        UzerDao dao = UzerDaoFactory.getUzerDao();
                        Bankuser uzers = new Bankuser(00323, "Rich Darius", "richied@aol.com");
                        dao.updateUzer(uzers);

                        System.out.println("\nWould you like to do another transaction? \n Please type 'y' for yes or 'n' for no");
                        cont = scan.next().charAt(0);

                    } while (cont != 'n');


                    break;
                }
                default:
                    System.out.println("You will need to create a login");
                    //create username for new user
                    System.out.println("Please create a username");
                    String cuid = scan.next();
//create password for new user
                    System.out.println("Please enter your password");
                    String cpass = scan.next();

                    //sql stuff

                    try {
                        //String sql = "select * from cstlogin";

                        //sql command for stored data in string form
                        stmnt = conn.createStatement();

                        stmnt.executeUpdate("INSERT INTO cstlogin  (username, password) VALUES (?,?)");
                        p.setString(1, cuid);
                        p.setString(2, cpass);

                    } catch (SQLException | NullPointerException e) {
                        e.printStackTrace();
                    }
            }
            /*System.out.println("Please enter your login details: ");
            System.out.println("Please create aur username");
            String cuid = scan.next();
            System.out.println("Please enter your password");
            String cpass = scan.next();*/

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "T0gether4!");

            } catch (SQLException e) {
                System.out.println(e);
            }

             conn = null;
             p = null;
             rs = null;
            //con = connection.connects();


        }
    }
}