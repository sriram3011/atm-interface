package org.atm.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.atm.main.dao.MysqlDBConnection;
import org.atm.main.exception.InvalidAmountException;



public class AtmOperation {
 
 public static boolean enter(String acc_number, String pin_number) throws ClassNotFoundException, SQLException
 {
  try
  {
  Connection connection= MysqlDBConnection.dbconnect();
  PreparedStatement statement=connection.prepareStatement("select * from atminterface where pin_number=?");
  statement.setString(1, pin_number);
  ResultSet result=statement.executeQuery();
  if(result.next())
  {
  
   if(result.getString("pin_number").equals(pin_number))
   {
    return true;
   }
   else
   {
    return false;
   }
  }
  else
  {
   return false;
  }
  }
  catch(ClassNotFoundException e)
  {
   System.out.println("Something went wrong!! "); 
  }
  catch(SQLException e)
  {
   System.out.println("Pin is incorrect!! "); 
  }
  return false;
  
  
 }
 
 public static double balanceCheck(long pin_number1) throws ClassNotFoundException, SQLException
 {  
  double balance;
  try
  {
  Connection connection= MysqlDBConnection.dbconnect();
  PreparedStatement statement=connection.prepareStatement("select * from atminterface where pin_number=?");  
  statement.setLong(1, pin_number1);
  ResultSet result=statement.executeQuery();
  result.next();
  balance=result.getDouble("balance");
  return balance;
  
  }
  catch(SQLException e)
  {
   System.out.println("Wrong password!!");
  }
  catch(Exception e)
  {
   System.out.println("Something went wrong!!");
  }
  return 0;
 }

 
 public static double withdraw(long pin_number1, double withdrawalAmount) throws ClassNotFoundException, SQLException, InvalidAmountException
 {
  Connection connection= MysqlDBConnection.dbconnect();
  PreparedStatement statement=connection.prepareStatement("select * from atminterface where pin_number=?");
  statement.setLong(1, pin_number1);
  
  ResultSet result=statement.executeQuery();
  result.next();
  double availableBalance=result.getDouble("balance");
  
  if(withdrawalAmount<availableBalance)
  {
     double remainingBalance=availableBalance-withdrawalAmount;
     statement=connection.prepareStatement("update atminterface set balance=? where pin_number=?");
     statement.setDouble(1, new Double(remainingBalance));
     statement.setLong(2, pin_number1);
     
     if(statement.executeUpdate()>0)
     {
      return remainingBalance;  
     }
     else
     {
      return 0;
     }
  }
  else
  {
   throw new InvalidAmountException("Invalid Withdrawal amount!!");
  }
 }



 public static double deposit(long pin_number1, double depositAmount) throws ClassNotFoundException, SQLException
 {
  Connection connection= MysqlDBConnection.dbconnect();
  PreparedStatement statement=connection.prepareStatement("select * from atminterface where pin_number=?");
  statement.setLong(1, pin_number1);
  
  ResultSet result=statement.executeQuery();
  result.next();
  double avilableBalance=result.getDouble("balance");
  double newBalance=avilableBalance+depositAmount;
 
  statement=connection.prepareStatement("update atminterface set balance=? where pin_number=?");
  statement.setDouble(1, new Double(newBalance));
  statement.setLong(2, pin_number1);
     
     if(statement.executeUpdate()>0)
     {
      return newBalance;  
     }
     else
     {
      return 0;
        }
 
   }
 
 public static ResultSet checkAccountInfo(String pin_number) throws ClassNotFoundException, SQLException
 {
  Connection connection= MysqlDBConnection.dbconnect();
  PreparedStatement statement=connection.prepareStatement("select * from atminterface where pin_number=?");
  statement.setString(1, pin_number);
  
  ResultSet result=statement.executeQuery();
  if(result.next())
  {
   return result;
  }
  else
  {
   return null;
  }
 } 

}