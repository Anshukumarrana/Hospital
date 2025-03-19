
package dd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Updatedoctor {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs;
    String firstName, lastName, specialization , col, value;
    int doctorid;
     // Constructor to initialize the update process
    public Updatedoctor(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;
        updaterecord();// Call method to start update
    }
 // Method to get doctor ID and validate record existence
    public void  updaterecord() {
        System.out.print("Enter doctor id of the record you want to update:-");
      
        //  checkRecord();// Check if the doctor exists
      if (s1.hasNextInt()) {
            doctorid = s1.nextInt();
            checkRecord(); // Check if the doctor exists
        } else {
            System.out.println("Invalid input! Please enter a valid integer for doctorid.");
            s1.next(); // Clear the invalid input
        }
        //checkRecord();
    }
 // Method to check if the doctor record exists
    public void checkRecord() {
        try {
            ps = con.prepareStatement("SELECT * FROM doctors WHERE doctor_id = ?");
            ps.setInt(1, doctorid);
           
            rs = ps.executeQuery();

            if (rs.next()) {   // If record exists, fetch details
                doctorid = rs.getInt("doctor_id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                specialization = rs.getString("specialization");
                  // Display current details
                System.out.println("Doctors Details:");
                System.out.println("Email:- " +doctorid);
                System.out.println("First Name:- " + firstName);
                System.out.println("Last Name:- " + lastName);
                System.out.println("specialization:- " + specialization);
               
              
                updateRecord();
            } else {
                System.out.println("Record not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
         // Method to update doctor details
    public void updateRecord() {
        System.out.println("Enter column name to update (first_name, last_name,specialization):");
        col = s1.next();
         // Validate column name
        if (!col.matches("doctor_id|first_name|last_name|specialization")) {
            System.out.println("Invalid column name!");
            return;
        }
        
        System.out.println("Enter new value for " + col + ":");
        value = s1.next();
        
        try {
              // Update query based on user input
            ps = con.prepareStatement("UPDATE doctors SET " + col + " = ? WHERE doctor_id= ?");
            ps.setString(1, value);
            ps.setInt(2, doctorid);
           
            
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("Record not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
