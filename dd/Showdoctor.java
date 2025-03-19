
package dd;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Showdoctor {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs; // Stores query results

    // Constructor initializes the process
    public Showdoctor(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;
        showAll();// Call method to display doctors
    }
 // Method to retrieve and display all doctors
    public void showAll() {
        try {
         // Prepare and execute SQL query to fetch all doctor records
            ps = con.prepareStatement("SELECT * FROM doctors");
            rs = ps.executeQuery();

            System.out.println("\n===== Doctor Records =====");

            int i = 1;
            while (rs.next()) {
               int doctorId = rs.getInt("doctor_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String specialization = rs.getString("specialization");
                

                 // Display doctor details
                System.out.println(i + ". Doctor Details:");
                 System.out.println("   Doctor ID: " + doctorId);
                System.out.println("   First Name: " + firstName);
                System.out.println("   Last Name: " + lastName);
                System.out.println("  specialization: " + specialization);
                System.out.println("------------------------");
                i++;
            }
             // If no records were found
            if (i == 1) {
                System.out.println("No Doctor records found.");
            }
        } catch (SQLException ee) {
            System.out.println("Error: " + ee.getMessage());
        }
    }
}
