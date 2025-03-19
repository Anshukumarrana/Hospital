package ee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class addAppointment {
     Connection con;
     Scanner s1;
     PreparedStatement ps;
     int appointmentid, patientid, doctorid;

     // Constructor to initialize connection, scanner, and prepared statement
     public addAppointment(Connection con, Scanner s1, PreparedStatement ps) {
             this.con = con;
             this.s1 = s1;
             this.ps = ps;
             AddAppointment(); // Calls method to add an appointment
     }

     // Method to take user input and add an appointment
     public void AddAppointment() {
             s1.nextLine();

             // Validate appointment_id
             while (true) {
                     System.out.print("Enter the appointment_id :- ");
                     if (s1.hasNextInt()) {
                             appointmentid = s1.nextInt();
                             if (appointmentid > 0) break;
                             else System.out.println("Appointment ID must be a positive integer.");
                     } else {
                             System.out.println("Invalid input. Please enter a valid integer.");
                             s1.next(); // Clear the invalid input
                     }
             }

             // Validate patient_id
             while (true) {
                     System.out.print("Enter the patient_id :- ");
                     if (s1.hasNextInt()) {
                             patientid = s1.nextInt();
                             if (patientid > 0) break;
                             else System.out.println("Patient ID must be a positive integer.");
                     } else {
                             System.out.println("Invalid input. Please enter a valid integer.");
                             s1.next(); // Clear the invalid input
                     }
             }

             // Validate doctor_id
             while (true) {
                     System.out.print("Enter the doctor_id :- ");
                     if (s1.hasNextInt()) {
                             doctorid = s1.nextInt();
                             if (doctorid > 0) break;
                             else System.out.println("Doctor ID must be a positive integer.");
                     } else {
                             System.out.println("Invalid input. Please enter a valid integer.");
                             s1.next(); // Clear the invalid input
                     }
             }

             try {
                     // SQL query to insert the appointment details
                     String query = "INSERT INTO appointments (appointment_id, patient_id, doctor_id) VALUES (?, ?, ?)";
                     ps = con.prepareStatement(query);
                     ps.setInt(1, appointmentid);
                     ps.setInt(2, patientid);
                     ps.setInt(3, doctorid);

                     // Execute the query
                     int i = ps.executeUpdate();
                     if (i > 0) {
                             System.out.println("Appointment added successfully!");
                     } else {
                             System.out.println("Failed to add Appointment.");
                     }
             } catch (SQLException e) {
                     System.out.println("Error: " + e.getMessage());
             }
     }
}