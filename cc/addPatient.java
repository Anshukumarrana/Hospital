
package cc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class addPatient {
    Connection con;
    Scanner s1;
    PreparedStatement ps;

    // Constructor initializes the class and calls AddPatient()
    public addPatient(Connection con, Scanner s1, PreparedStatement ps) {
        this.con = con;
        this.s1 = s1;
        this.ps = ps;
        AddPatient();
    }

    // Method to insert a new patient into the database
    public void AddPatient() {
        s1.nextLine();

        // Collect patient details from user input
        System.out.print("Enter patient's first name:- ");
        String firstName = s1.nextLine();
        if (!firstName.matches("^[a-zA-Z]{2,}$")) {
            System.out.println("Invalid name! Only letters allowed, minimum 2 characters.");
            return;
        }

        System.out.print("Enter patient's last name:- ");
        String lastName = s1.nextLine();
        if (!lastName.matches("^[a-zA-Z]{2,}$")) {
            System.out.println("Invalid name! Only letters allowed, minimum 2 characters.");
            return;
        }

        System.out.print("Enter patient's gender :- ");
        String gender = s1.nextLine().trim();
        if (!(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other"))) {
            System.out.println("Invalid gender! Please enter Male, Female, or Other.");
            return;
        }

        System.out.print("Enter date of birth :- ");
        String dobInput = s1.nextLine();
        if (!isValidDate(dobInput)) {
            System.out.println("Invalid date! Please enter in format yyyy-MM-dd.");
            return;
        }

        System.out.print("Enter phone number :- ");
        String phone = s1.nextLine();
        if (!phone.matches("^[0-9]{10}$")) {
            System.out.println("Invalid phone number! Must be exactly 10 digits.");
            return;
        }

        System.out.print("Enter email:- ");
        String email = s1.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format! Example: example@mail.com");
            return;
        }

        System.out.print("Enter address:- ");
        String address = s1.nextLine();
        if (address.length() < 5) {
            System.out.println("Invalid address! Minimum 5 characters required.");
            return;
        }

        System.out.print("Enter doctor ID :- ");
        int doctorid = s1.nextInt();
        System.out.print("Enter bed number :- ");
        int bednumber = s1.nextInt();

        try {
            // SQL query to insert a new patient record
            String query = "INSERT INTO patients (first_name, last_name, gender, dob, phone, email, address, doctor_id, bed_number) VALUES (?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);

            // Setting the values for the prepared statement
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, gender);
            ps.setString(4, dobInput);
            ps.setString(5, phone);
            ps.setString(6, email);
            ps.setString(7, address);
            ps.setInt(8, doctorid);
            ps.setInt(9, bednumber);

            // Execute the query
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Patient added successfully!");
            } else {
                System.out.println("Failed to add patient.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Validate email format
    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    // Validate date format (yyyy-MM-dd)
    private boolean isValidDate(String dobInput) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(dobInput, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
