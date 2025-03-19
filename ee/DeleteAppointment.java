
package ee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DeleteAppointment {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs;
    int appointmentid;
    // Constructor to initialize connection, scanner, and prepared statement
    public DeleteAppointment(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;
        delappointment();// Call method to start deletion process
    }
 // Method to get appointment ID from the user and initiate the delete process
    public void delappointment() {
        System.out.print("Enter appointment id of the appointment id to delete: ");

      if (s1.hasNextInt()) {
            appointmentid = s1.nextInt();
            checkRecord(); // Check if the doctor exists
        } else {
            System.out.println("Invalid input! Please enter a valid integer for doctorid.");
            s1.next(); // Clear the invalid input
        }
        //checkRecord();// Check if the record exists before deleting
    }
  // Method to check if the appointment record exists in the database
    public void checkRecord() {
        try {
            ps = con.prepareStatement("SELECT * FROM appointments WHERE appointment_id = ?");
            ps.setInt(1, appointmentid);
      
            rs = ps.executeQuery();

            if (rs.next()) { 
                System.out.println("appointment found. Deleting record...");
                deleteRecord(); // If record exists, proceed with deletion
            } else {
                System.out.println("Record not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 // Method to delete the appointment record from the database
    public void deleteRecord() {
        try {
            ps = con.prepareStatement("DELETE FROM appointments WHERE appointment_id = ?");
            ps.setInt(1, appointmentid);

            int i = ps.executeUpdate();
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
