
package ee;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchAppointment {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs;
    
    int appointmentid;
 // Constructor to initialize connection, scanner, and prepared statement
    public SearchAppointment(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;

        searchRecord();// Call method to start the search process
    }
    // Method to search for an appointment record by ID
    public void searchRecord() {
        System.out.print("Enter the appointment id: ");
       // appointmentid = s1.nextInt();// Read user input
         if (s1.hasNextInt()) {
            appointmentid = s1.nextInt();
          
        } else {
            System.out.println("Invalid input! Please enter a valid integer for doctorid.");
            s1.next(); // Clear the invalid input
        }

        try {
             // Prepare SQL query to fetch appointment details based on the given ID
            ps = con.prepareStatement("SELECT * FROM appointments WHERE appointment_id = ?");
            ps.setInt(1, appointmentid);
            rs = ps.executeQuery();
                // If record is found, display details
            if (rs.next()) {
                System.out.println("\n appointment Details:");
                System.out.println("Appointment Id: " + rs.getInt("appointment_id"));
                System.out.println("Patient Id: " + rs.getInt("Patient_id"));
                System.out.println("Doctor Id: " + rs.getInt("doctor_id"));
                System.out.println("appointment Date: " + rs.getString("appointment_date"));
               
            } else {
                System.out.println("appointment not found.");
            }
        } catch (Exception ee) {
            System.out.println("Error: " + ee.getMessage());
        }
    }
}



