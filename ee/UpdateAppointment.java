
package ee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UpdateAppointment{
Connection con;
Scanner s1;
PreparedStatement ps;
ResultSet rs;
String  col, value;
int appointmentid, patientid ,doctorid;
 // Constructor initializes the class and starts the update process
         public UpdateAppointment(Connection con, Scanner s1, PreparedStatement ps) {
         this.con = con;
         this.s1 = s1;
         this.ps = ps;
         
        updaterecord();// Calls the method to start the update process
            }
         
         
// Method to take appointment ID input from the user
            public void  updaterecord() {
            System.out.println("Enter appointment id of the record you want to update:");
            //appointmentid = s1.nextInt();

           // checkRecord();  // Calls method to check if the record exists
             if (s1.hasNextInt()) {
               appointmentid  = s1.nextInt();
              checkRecord(); // Check if the doctor exists
        } else {
            System.out.println("Invalid input! Please enter a valid integer for doctorid.");
            s1.next(); // Clear the invalid input
        }
           }


  // Method to check if the appointment exists
               public void checkRecord() {
                   
                  try {
                           ps = con.prepareStatement("SELECT * FROM appointments WHERE appointment_id = ?");
                           ps.setInt(1, appointmentid);

                            rs = ps.executeQuery();

                    if (rs.next()) {// If appointment exists, retrieve details

                              appointmentid= rs.getInt("appointment_id");
                              patientid = rs.getInt("patient_id");
                              doctorid = rs.getInt("doctor_id");

                             System.out.println("Appointment Details:");
                             System.out.println("Appointmentid:- " +appointmentid);
                             System.out.println("Patientid:- " +patientid);
                             System.out.println("Poctorid:- " +doctorid);


         updateRecord();// Proceed with update
             } else {
                  System.out.println("Record not found.");
           }
                 } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
    }
     }
        // Method to update the appointment details
             public void updateRecord() {
           System.out.println("Enter column name to update (appointment_id,patient_id,doctor_id):");
              col = s1.next();
  // Validate the column name
              if (!col.matches("appointment_id|patient_id|doctor_id")) {
                  System.out.println("Invalid column name!");
                  return;
                  }

                       System.out.println("Enter new value for " + col + ":");
                       value = s1.next();

                      try {
                            ps = con.prepareStatement("UPDATE appointments SET " + col + " = ? WHERE appointment_id= ?");
                           ps.setString(1, value);
                          ps.setInt(2, appointmentid);


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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         