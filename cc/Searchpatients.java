package cc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Searchpatients {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs;
    String firstname, lastname;
  // Constructor initializes the class and calls searchRecord()
    public Searchpatients(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;

        searchRecord();// Call method to search for a patient
    }
 // Method to search patient by first and last name
    public void searchRecord() {
        System.out.print("Enter Patient's First Name: ");
        firstname = s1.next();
         if (!firstname.matches("^[a-zA-Z]{2,}$")) {
            System.out.println("Invalid name! Only letters allowed, minimum 2 characters.");
            return;
        }
        System.out.print("Enter Patient's Last Name: ");
        lastname = s1.next();
          if (!lastname.matches("^[a-zA-Z]{2,}$")) {
            System.out.println("Invalid name! Only letters allowed, minimum 2 characters.");
            return;
        }

        try {
             // Prepare SQL query to search for patient by name
            ps = con.prepareStatement("SELECT * FROM patients WHERE first_name = ? AND last_name = ?");
            ps.setString(1, firstname);
            ps.setString(2, lastname); 

            rs = ps.executeQuery(); // Execute query


            if (rs.next()) { // If a record is found, display patient details
                System.out.println("\nPatient Details:");
                System.out.println("First Name: " + rs.getString("first_name"));
                System.out.println("Last Name: " + rs.getString("last_name"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Date of Birth: " + rs.getString("dob"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Doctor ID: " + rs.getInt("doctor_id"));
                System.out.println("Bed Number: " + rs.getInt("bed_number"));
            } else {
                System.out.println("Patient not found.");
            }
        } catch (Exception ee) {
            System.out.println("Error: " + ee.getMessage());
        }
    }
}



