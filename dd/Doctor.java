
package dd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Doctor {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    int ch;

   // Constructor initializes the Doctor Management menu
    public Doctor(Connection con, Scanner s1, PreparedStatement ps) {
        this.s1 = s1;
        this.con = con;
        this.ps = ps;
        showdoctor();  // Display the menu
    }

    // Method to display doctor management options
    public void showdoctor() {
        System.out.println("**------**------**------**-------**");
        System.out.println("**--    Doctor Management     --**");
        System.out.println("**------**------**------**-------**");
        System.out.println("1. Add Doctor");
        System.out.println("2. Update Record");
        System.out.println("3. Delete Record");
        System.out.println("4. Search Record");
        System.out.println("5. Show Record");
        System.out.println("6. Back");
        System.out.print("Select Your Choice:- ");
        
        ch = s1.nextInt(); // Read user choice

        switch (ch) {
            case 1:
                new adddoctor(con ,s1,ps);
                break;
            case 2:
               new Updatedoctor(con ,s1,ps);
                break;
            case 3:
                new Deletedoctor (con, s1,ps);
                break;
            case 4:
               new Searchdoctor (con, s1,ps);
                break;
            case 5:
               new Showdoctor(con, s1,ps);
                break;
            case 6:
                System.out.println("Going back to previous menu...");
                break;
            default:
                System.out.println("Please select a valid option!");
        }
    }
}
