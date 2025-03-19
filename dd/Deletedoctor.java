
package dd;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Deletedoctor {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs;
    int doctorid;
    // Constructor initializes the class and calls the deletion method
    public Deletedoctor(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;
        deldoctor(); // Calls the method to start deletion process
    }
// Method to get doctor ID from the user
    public void deldoctor() {
        System.out.print("Enter doctorid of the doctors to delete: ");
 
      if (s1.hasNextInt()) {
            doctorid = s1.nextInt();
            checkRecord(); // Check if the doctor exists
        } else {
            System.out.println("Invalid input! Please enter a valid integer for doctorid.");
            s1.next(); // Clear the invalid input
        }
    }
 // Method to check if the doctor exists in the database
    public void checkRecord() {
        try {
            ps = con.prepareStatement("SELECT * FROM doctors WHERE doctor_id = ?");
            ps.setInt(1, doctorid);
      
            rs = ps.executeQuery();

            if (rs.next()) { // If doctor exists, proceed with deletion
                System.out.println("Doctor found. Deleting record...");
                deleteRecord();
            } else {
                System.out.println("Record not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
  // Method to delete the doctor from the database
    public void deleteRecord() {
        try {
            ps = con.prepareStatement("DELETE FROM doctors WHERE doctor_id = ?");
            ps.setInt(1, doctorid);

            int i = ps.executeUpdate(); // Execute deletion query
            if (i > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Failed to delete the record.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
