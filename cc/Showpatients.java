package cc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Showpatients {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs; // Stores the result of SQL queries
// Constructor initializes the class and calls showAll()
    public Showpatients(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;
        showAll(); // Call method to display all patient records
    }
 // Method to retrieve and display all patients from the database
    public void showAll() {
        try {
          // Prepare SQL query to fetch all records from the 'patients' table
            ps = con.prepareStatement("SELECT * FROM patients");
            rs = ps.executeQuery(); // Execute query

            System.out.println("\n===== Patient Records =====");

            int i = 1;// Counter to number the patient records
            while (rs.next()) {
               // Fetching details from the database
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String gender = rs.getString("gender");
                String dob = rs.getString("dob");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int doctorId = rs.getInt("doctor_id");
                int bedNumber = rs.getInt("bed_number");

               
                System.out.println(i + ". Patient Details:");
                System.out.println("   First Name: " + firstName);
                System.out.println("   Last Name: " + lastName);
                System.out.println("   Gender: " + gender);
                System.out.println("   Date of Birth: " + dob);
                System.out.println("   Phone: " + phone);
                System.out.println("   Email: " + email);
                System.out.println("   Address: " + address);
                System.out.println("   Doctor ID: " + doctorId);
                System.out.println("   Bed Number: " + bedNumber);
                System.out.println("------------------------");
                i++;
            }

            if (i == 1) {
                System.out.println("No patient records found.");
            }
        } catch (SQLException ee) {
            System.out.println("Error: " + ee.getMessage());
        }
    }
}
