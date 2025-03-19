
package aa;

import cc.PatientManagement;
import dd.Doctor;
import ee.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class menu
{
    Connection con;// Database connection object
    Scanner s1;
    PreparedStatement ps; // PreparedStatement for executing SQL queries
    int ch;
   // Constructor 1: Initializes menu with only Scanner object
   public menu(Scanner s1)
   {
       this.s1=s1;
       this.show_menu(s1);
   }
    // Constructor 2: Initializes menu with database connection and prepared statement
 public menu(Connection con,Scanner s1,PreparedStatement ps)
 {
     this.s1=s1;
     this.con=con;
     this.ps=ps;
        System.out.println("Login Page Successful");
        show_menu(s1);
 }
 // Method to display the loginpage management menu
 public void show_menu(Scanner s1)
 {
            System.out.println("----//----//----//-----//-----//-----//-----");
            System.out.println("|--      Hospital Management System       --|");
            System.out.println("----//----//----//-----//-----//-----//-----");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Management");
            System.out.println("4. Log Out");
            System.out.println("5. Exit");
            System.out.print("Select your choice :- ");
     
   
     ch=s1.nextInt();
     
     switch(ch)
     {
        case 1:
        new PatientManagement(con, s1, ps); 
        break;
    case 2:
        new Doctor(con, s1, ps); 
        break;
    case 3:
         new Appointment(con, s1, ps); 
        break;
    case 4:
        System.out.println("Log Out");
        return;
    case 5:
        System.out.println("Exit");
        System.exit(0);
            
         default:System.out.println("Invalid choice! Please try again");        
     }
 } 
}
