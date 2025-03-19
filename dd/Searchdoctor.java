
package dd;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Searchdoctor {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs;
    
    int doctorid;
 // Constructor initializes the search operation
    public Searchdoctor(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;

        searchRecord();
    }
    // Method to search for a doctor by ID
    public void searchRecord() {
        System.out.print("Enter the doctor Id: ");
       // doctorid = s1.nextInt();// Read doctor ID from user
  //  checkRecord();// Check if the doctor exists
      if (s1.hasNextInt()) {
            doctorid = s1.nextInt();
          
        } else {
            System.out.println("Invalid input! Please enter a valid integer for doctorid.");
            s1.next(); // Clear the invalid input
        }
        try {
               // Prepare SQL query to fetch doctor details
            ps = con.prepareStatement("SELECT * FROM doctors WHERE doctor_id = ?");
            ps.setInt(1, doctorid);
            rs = ps.executeQuery();
           // Check if a doctor record is found
            if (rs.next()) {
                System.out.println("\n Doctor Details:");
                System.out.println("Doctor ID: " + rs.getInt("doctor_id"));
                System.out.println("First Name: " + rs.getString("first_name"));
                System.out.println("Last Name: " + rs.getString("last_name"));
                System.out.println("specialization: " + rs.getString("specialization"));
               
            } else {
                System.out.println("Doctor not found.");
            }
        } catch (Exception ee) {
            System.out.println("Error: " + ee.getMessage());
        }
    }
}



