import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Sbi {

    Scanner sc = new Scanner(System.in);

    public void createAccount() {
        Configuration c = new Configuration();
        SessionFactory sf = c.configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter Account Number");
        while (!sc.hasNextLong()) {
            System.out.println("Invalid input! Please Enter In Number");
            sc.next();
        }
        long acn = sc.nextLong();

        Account ac = (Account) s.get(Account.class, acn);
        
        if (ac != null) {
            System.out.println("Entered account is already exist");
        } else {
            System.out.println("Enter Name ");
            while (!sc.hasNext("[A-Za-z]+")) {
                System.out.println("Invalid Input! Please Enter In Letters");
                sc.next();
            }
            String name = sc.next();

            System.out.println("Enter Mobile Number ");
            while (!sc.hasNextLong()) {
                System.out.println("Invalid input! Please Enter In Number");
                sc.next();
            }
            long mn = sc.nextLong();

            System.out.println("Enter your age");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please Enter in Number");
                sc.next();
            }
            int age = sc.nextInt();
            
            System.out.println("Enter last 4 digits of Adhar Number");
            while (!sc.hasNextLong()) {
                System.out.println("Invalid input! Please Enter In Number");
                sc.next();
            }
            long an = sc.nextLong();

            System.out.println("Enter your Gender");
            String gender = sc.next();

            System.out.println("Enter your password (maximum 6 characters): ");
            String password = sc.next();

            
            Account a = new Account(acn, name, mn, an, gender, age);
            a.setPassword(password);
            s.save(a);
            t.commit();
            s.close();
            sf.close();
        }
    }

    
    public void displayAllDetails() {
        Configuration c = new Configuration();
        SessionFactory sf = c.configure().buildSessionFactory();
        Session s = sf.openSession();

        System.out.println("Enter Account Number");
        long acn = sc.nextLong();

        // Password validation
        System.out.println("Enter your password: ");
        String inputPassword = sc.next();

        try {
            Account ac = (Account) s.get(Account.class, acn);
            if (ac != null && ac.getPassword().equals(inputPassword)) {
                System.out.println("Account No :"+ac.getAccNO());
                System.out.println("Account holder name :"+ac.getName());
                System.out.println("Mobile no :"+ac.getMobNO());
                System.out.println("Adharcard number :"+"**** **** "+ac.getAdharNO());
                System.out.println("Gender :"+ac.getGender());
                System.out.println("Age :"+ac.getAge());
                System.out.println("Balance :"+ac.getBalance());
            } else {
                System.out.println("Account not found or incorrect password.");
            }
        } catch (Exception e) {
            System.out.println("Error retrieving account details. Please try again.");
            e.printStackTrace();
        } finally {
            s.close();
            sf.close();
        }
    }


    public void depositeMoney() {
        Configuration c = new Configuration();
        SessionFactory sf = c.configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter Account number");
        long acn = sc.nextLong();

        System.out.println("Enter your password: ");
        String inputPassword = sc.next();

        try {
            Account ac = (Account) s.get(Account.class, acn);
            if (ac != null && ac.getPassword().equals(inputPassword)) {
                System.out.println("Enter Amount For Deposit");
                double depositAmount = sc.nextDouble();

                String hql = "UPDATE Account SET balance = balance + :depositAmount WHERE accNO = :acn";
                Query q = s.createQuery(hql);
                q.setParameter("depositAmount", depositAmount);
                q.setParameter("acn", acn);

                int result = q.executeUpdate();
                
                System.out.println(depositAmount + " credited to your account!");
                t.commit();
                
                Account updatedAccount = (Account) s.get(Account.class, acn);
                System.out.println("New Balance is: " + (updatedAccount.getBalance() + depositAmount));
            } else {
                System.out.println("Account not found or incorrect password.");
            }
        } catch (Exception e) {
            System.out.println("Error processing deposit. Please try again.");
            e.printStackTrace();
        } finally {
            s.close();
            sf.close();
        }
    }

    
    public void withdrawlMoney() {
        Configuration c = new Configuration();
        SessionFactory sf = c.configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter Account number");
        long acn = sc.nextLong();

        // Password validation
        System.out.println("Enter your password: ");
        String inputPassword = sc.next();

        try {
            Account ac = (Account) s.get(Account.class, acn);
            if (ac != null && ac.getPassword().equals(inputPassword)) {
                System.out.println("Enter Amount For Withdrawal");
                double withdrawalAmount = sc.nextDouble();
                
                if (ac.getBalance() >= withdrawalAmount) {
                    String hql = "UPDATE Account SET balance = balance - :amount WHERE accNO = :acn";
                    Query q = s.createQuery(hql);
                    q.setParameter("amount", withdrawalAmount);
                    q.setParameter("acn", acn);

                    int result = q.executeUpdate();
                    
                    t.commit();
                    System.out.println(withdrawalAmount + " withdrawn from your account.");
                    
                    Account updatedAccount = (Account) s.get(Account.class, acn);
                    System.out.println("New Balance is: " + (updatedAccount.getBalance() - withdrawalAmount));
                } else {
                    System.out.println("Insufficient balance for the withdrawal.");
                }
            } else {
                System.out.println("Account not found or incorrect password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred. Please try again.");
        } finally {
            s.close();
            sf.close();
        }
    }

    
    public void checkBalance() {
        Configuration c = new Configuration();
        SessionFactory sf = c.configure().buildSessionFactory();
        Session s = sf.openSession();

        System.out.println("Enter Account number");
        long acn = sc.nextLong();

        System.out.println("Enter your password: ");
        String inputPassword = sc.next();

        try {
            Account ac = (Account) s.get(Account.class, acn);
            if (ac != null && ac.getPassword().equals(inputPassword)) {
                System.out.println("The current balance in the account is: " + ac.getBalance());
            } else {
                System.out.println("Account not found or incorrect password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching the balance.");
        } finally {
            s.close();
            sf.close();
        }
    }
}
