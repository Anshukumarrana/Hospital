
package ee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Appointment {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    int ch;

  // Constructor to initialize connection, scanner, and prepared statement
    public Appointment(Connection con, Scanner s1, PreparedStatement ps) {
        this.s1 = s1;
        this.con = con;
        this.ps = ps;
        showdoctor();  // Display the menu
    }

     // Method to display the appointment management menu
    public void showdoctor() {
        System.out.println("**------**------**------**-------**");
        System.out.println("**--   Appointment Management  --**");
        System.out.println("**------**------**------**-------**");
        System.out.println("1. Add Appointment");
        System.out.println("2. Update Record");
        System.out.println("3. Delete Record");
        System.out.println("4. Search Record");
        System.out.println("5. Show Record");
        System.out.println("6. Back");
        System.out.print("Select Your Choice:- ");
        
        ch = s1.nextInt();

        switch (ch) {
            case 1:
                new addAppointment(con ,s1,ps);
                break;
            case 2:
               new UpdateAppointment(con ,s1,ps);
                break;
            case 3:
                new DeleteAppointment(con, s1,ps);
                break;
            case 4:
               new SearchAppointment (con, s1,ps);
                break;
            case 5:
              new  ShowAppointment(con, s1,ps);
                break;
            case 6:
                System.out.println("Going back to previous menu...");
                break;
            default:
                System.out.println("Please select a valid option!");
        }
    }
}
