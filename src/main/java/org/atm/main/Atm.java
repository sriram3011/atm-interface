
package org.atm.main;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.atm.main.dao.AtmOperation;
import org.atm.main.exception.InvalidAmountException;



public class Atm 
{
 static BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in)); 
 
 public static void main(String args[]) throws NullPointerException,NumberFormatException, IOException, ClassNotFoundException, SQLException, InvalidAmountException
 {
  
  
  System.out.println("============================= WELCOME TO ATM !!! ===========================");
  System.out.println("====================================================================================");
  
  boolean status=false;
  do
  {
  
   
   System.out.print("\t\t   Enter your Account Number : ");
   String acc_number=bufferedReader.readLine();
   System.out.println();
   System.out.print("\t\t  Enter your 4 Digit Pin : ");
   String pin_number=bufferedReader.readLine();
   System.out.println("====================================================================================");
   status=AtmOperation.enter(acc_number, pin_number);
  
  
    if(status)
    {
    do
    {
     
     System.out.println("========================= CHOOSE THE GIVEN OPTION BELOW ============================");
     System.out.println("====================================================================================");
     System.out.println("\t\t  Enter 1-> Balance Enquiry");
     System.out.println("\t\t  Enter 2-> Withdraw");
     System.out.println("\t\t  Enter 3-> Deposit");
     System.out.println("\t\t  Enter 4-> Check Account Info");
     System.out.println("\t\t  Enter 5-> Exit");
     System.out.println("====================================================================================");
     System.out.println("\t\t  Enter a valid input between 1 to 5:");
     int choice=Integer.parseInt(bufferedReader.readLine());
     
     
	switch(choice)
     {
     case 1: System.out.println("Enter valid pin number:");
       int pin_number1=Integer.parseInt(bufferedReader.readLine());
       System.out.println( "Current available balance is :"+AtmOperation.balanceCheck(pin_number1));
       break;
       
     case 2: System.out.println("Enter valid pin number:");
       pin_number1=Integer.parseInt(bufferedReader.readLine());
       System.out.println("Enter withdraw amount:");
       double withdrawalAmount=Double.parseDouble(bufferedReader.readLine());
       
       try
       {
        double result=AtmOperation.withdraw(pin_number1, withdrawalAmount);      
        System.out.println("Transaction successfull!!");
        System.out.println("Remaining balance is:"+result);
       }
       catch(InvalidAmountException e)
       {
        System.out.println("Invalid Withdrawal amount!!");
       }
       break;
     
     case 3: System.out.println("Enter valid pin number:");
       pin_number1=Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter deposit amount:");
               double depositAmount=Double.parseDouble(bufferedReader.readLine());
             double result=AtmOperation.deposit(pin_number1, depositAmount);
     
       if(result==0)
       {
        
        System.out.println("Transaction is unsuccessfull!!");
       }
       else
       {
        System.out.println("Transaction successfull!!");
        System.out.println("New balance is:"+result);
       }
       break; 
       
     case 4: System.out.println("Enter valid pin number:");
       pin_number1=Integer.parseInt(bufferedReader.readLine());
       System.out.println("====================================================================================");
       System.out.println("=============================== ACCOUNT DETAIL's ===================================");
       System.out.println("====================================================================================");
       ResultSet accountInfo=AtmOperation.checkAccountInfo(pin_number);
  
             System.out.println("\t\t  Account Number    :"+accountInfo.getLong("acc_number"));
             System.out.println("\t\t  Account Name      :"+accountInfo.getString("acc_name"));
             System.out.println("\t\t  Bank Name         :"+accountInfo.getString("bank_name"));
             System.out.println("\t\t  Available Balance :"+accountInfo.getString("balance"));
             System.out.println("====================================================================================");
       break;
       
     case 5: status=false;
             System.out.println("====================================================================================");
             System.out.println("\t\t\t\t\t\t Please Collect Your Card");
             System.out.println("Thank you for using ATM Machine!!!");
             System.out.println("====================================================================================");
       break;
       
     default: System.out.println("====================================================================================");
        System.out.println("Wrong Choice!!");  
        System.out.println("====================================================================================");
      
     }
    
   }
   while(status);
   }
   
   else
   {
    System.out.println("====================================================================================");
    System.out.println("Incorrect account number or pin!!");
    System.out.println("====================================================================================");
   }
 }
 while(status);
 }
 
 }