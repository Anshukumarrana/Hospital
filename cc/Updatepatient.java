package cc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Updatepatient {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    ResultSet rs;
    String firstName, lastName, gender, dob, phone ,email,address, col, value;
    int doctorid,bednumber;
// Constructor initializes the class and calls updateMethod()
    public Updatepatient(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;
        updateMethod();
    }
// Method to get user input for record search
    public void updateMethod() {
        System.out.println("Enter first name of the record you want to update:");
        firstName = s1.next();
        if (!firstName.matches("^[a-zA-Z]{2,}$")) {
            System.out.println("Invalid name! Only letters allowed, minimum 2 characters.");
            return;
        }
        System.out.println("Enter last name:");
        lastName = s1.next();
         if (!lastName.matches("^[a-zA-Z]{2,}$")) {
            System.out.println("Invalid name! Only letters allowed, minimum 2 characters.");
            return;
        }
        checkRecord(); // Calls method to verify if the record exists
    }
  // Method to check if the patient record exists
    public void checkRecord() {
        try {// SQL query to find patient details
            ps = con.prepareStatement("SELECT * FROM patients WHERE first_name = ? AND last_name = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            rs = ps.executeQuery();

            if (rs.next()) { // If record is found
                // Fetching patient details
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                gender = rs.getString("gender");
                dob = rs.getString("dob");
                phone = rs.getString("phone");
                email = rs.getString("email");
                address = rs.getString("address");
                doctorid = rs.getInt("doctor_id");
                bednumber = rs.getInt("bed_number");
                 // Displaying patient details
                System.out.println("Patients Details:");
                System.out.println("First Name:- " + firstName);
                System.out.println("Last Name:- " + lastName);
                System.out.println("gender :-" + gender);
                System.out.println("date of birth  " + dob);
                System.out.println("Phone:- " + phone);
                System.out.println("Email:- " + email);
                System.out.println("Address:- " + address);
                System.out.println("Email:- " +doctorid);
                System.out.println("Address:- " + bednumber);
                updateRecord();// Proceed to update
            } else {
                System.out.println("Record not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
// Method to update the selected column of the patient record
    public void updateRecord() {
        System.out.println("Enter column name to update (first_name, last_name,gender,dob,phone, email, address,doctor_id,bed_number):");
        col = s1.next();
         // Validate if the entered column name is valid
        if (!col.matches("first_name|last_name|gender|dob|phone|email|address|doctor_id|bed_number")) {
            System.out.println("Invalid column name!");
            return;
        }
        
        System.out.println("Enter new value for " + col + ":");
        value = s1.next();
        
        try {
            // SQL query to update the specific column of the patient record
            ps = con.prepareStatement("UPDATE patients SET " + col + " = ? WHERE first_name = ? AND last_name = ?");
            ps.setString(1, value);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            
            int i = ps.executeUpdate(); // Execute update query
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
