package cc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PatientManagement {
    Connection con;
    Scanner s1;
    PreparedStatement ps;
    int ch;

  // Constructor to initialize database connection and scanner
    public PatientManagement(Connection con, Scanner s1, PreparedStatement ps) {
        this.s1 = s1;
        this.con = con;
        this.ps = ps;
         
        showPatientMenu();  // Call method to display patient management menu 
    }

    // Method to display the patient management menu
    public void showPatientMenu() {
        System.out.println("**------**------**------**-------**");
        System.out.println("**--    Patient Management     --**");
        System.out.println("**------**------**------**-------**");
        System.out.println("1. Add Patient");
        System.out.println("2. Update Record");
        System.out.println("3. Delete Record");
        System.out.println("4. Search Record");
        System.out.println("5. Show Record");
        System.out.println("6. Back");
        System.out.print("Select Your Choice:- ");
        
        ch = s1.nextInt();

        switch (ch) {
            case 1:
                new addPatient(con ,s1,ps);
                break;
            case 2:
               new Updatepatient(con ,s1,ps);
                break;
            case 3:
               new DeletePatients(con ,s1,ps);
                break;
            case 4:
                new Searchpatients(con, s1,ps);
                break;
            case 5:
                new Showpatients(con, s1,ps);
                break;
            case 6:
                System.out.println("Going back to previous menu...");
                break;
            default:
                System.out.println("Please select a valid option!");
        }
    }
     
}
