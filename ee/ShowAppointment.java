
package ee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowAppointment {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs;
 // Constructor to initialize connection, scanner, and prepared statement
    public ShowAppointment(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;
        showAll(); // Call method to display all appointment records
    }
// Method to fetch and display all appointment records
    public void showAll() {
        try {
        
            ps = con.prepareStatement("SELECT * FROM appointments");
            rs = ps.executeQuery();

            System.out.println("\n===== Appointment Records =====");

            int i = 1;  // Counter to track records
            while (rs.next()) {
               int appointmentId = rs.getInt("appointment_id");
                int patientId = rs.getInt("patient_id");
                int doctorId = rs.getInt("doctor_id");
                String appointmentDate = rs.getString("appointment_date");
                

               
                System.out.println(i + ". Doctor Details:");
                System.out.println("   Appointment ID: " + appointmentId);
                System.out.println("   patient Id: " + patientId);
                System.out.println("   doctorIde: " + doctorId);
                System.out.println("  appointmentDate: " + appointmentDate);
                System.out.println("------------------------");
                i++;
            }
// Check if no records were found
            if (i == 1) {
                System.out.println("No Doctor records found.");
            }
        } catch (SQLException ee) {
            System.out.println("Error: " + ee.getMessage());
        }
    }
}
