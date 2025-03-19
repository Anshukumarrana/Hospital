package cc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DeletePatients {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs;
    String firstName, lastName;

    // Constructor to initialize database connection and scanner
    public DeletePatients(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;
        delMethod();// Call method to start deletion process
    }
// Method to take user input for patient details
    public void delMethod() {
        System.out.print("Enter first name of the patient to delete: ");
         firstName = s1.next(); // Read first name input
          if (!firstName.matches("^[a-zA-Z]{2,}$")) {
            System.out.println("Invalid name! Only letters allowed, minimum 2 characters.");
            return;
        }
        System.out.print("Enter last name: ");
         lastName = s1.next();// Read last name input
          if (!lastName.matches("^[a-zA-Z]{2,}$")) {
            System.out.println("Invalid name! Only letters allowed, minimum 2 characters.");
            return;
        }

        checkRecord(); // Check if patient exists before deletion
    }
// Method to check if the patient record exists in the database
    public void checkRecord() {
        try {
            ps = con.prepareStatement("SELECT * FROM patients WHERE first_name = ? AND last_name = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            rs = ps.executeQuery();

            if (rs.next()) { // If record exists
                System.out.println("Patient found. Deleting record...");
                deleteRecord();
            } else {
                System.out.println("Record not found.");
            }
        } catch (Exception e) {  // Handle SQL errors
            System.out.println("Error: " + e.getMessage());
        }
    }
  // Method to delete patient record from database
    public void deleteRecord() {
        try {
            ps = con.prepareStatement("DELETE FROM patients WHERE first_name = ? AND last_name = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);

            int i = ps.executeUpdate();  // Execute deletion query
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
