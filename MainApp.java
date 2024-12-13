//The Bank Management System is a simple Java-based application that facilitates the management of bank accounts using Hibernate for database operations. It allows users to: Create Accounts,View Account Details,Deposit Money,Withdraw Money,Check Balance,Exit
// The application uses Hibernate ORM to manage the database interactions and ensures secure operations through password-based authentication. It also handles common errors such as invalid inputs and insufficient funds for withdrawals.

import java.util.*;
public class MainApp {

	public static void main(String[] args) {
				Scanner sc=new Scanner(System.in);
				Sbi s=new Sbi();
				while(true)
				{
					System.out.println("------------------------------------------------------------------------------------");
					System.out.println("1.Create Account\n2.Display Details\n3.Deposite Money\n4.Withdrawl Money\n5.Check Balance\n6.exit ");
					int choice=sc.nextInt();

					switch(choice)
					{
					case 1:
						s.createAccount();
						break;
					case 2:
						s.displayAllDetails();
						break;
					case 3:
						s.depositeMoney();
						break;
					case 4:
						s.withdrawlMoney();
						break;
					case 5:
						s.checkBalance();
						break;
					case 6:
						 System.out.println("Thank you for using SBI services. Goodbye!");
						 System.exit(0);
					default:
						System.out.println("Wrong Choice......");

					}
				}


			}

}

