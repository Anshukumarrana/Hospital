
package aa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Loginpage
{
    String un,pass;
    Scanner s1;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int no=1;
    static String coun_name;
    
    // Constructor 1: When database connection is passed as a parameter
    public Loginpage(Connection con,Scanner s1,PreparedStatement ps)
    {
        this.con=con;
        this.s1=s1;
        this.ps=ps;
         // Allow up to 4 login attempts
         do
        {
        insert();// Call insert() method to get login details
        no++;
        
        
        }while(no<=10);
      
    }
     // Constructor 2: When a new database connection is created
    public Loginpage(Scanner s1) 
    {
        
        this.s1 = s1;
        try
        {
        Class.forName("com.mysql.jdbc.Driver");
            
       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "root");
        }
        catch(Exception e)
        {
            System.out.println("Errorr "+e);
        }
         // Allow up to 4 login attempts
        do
        {
        insert();// Get login input from user
        no++;
        }while(no<=10);        
    }

// Method to display the login management menu
    public void insert()
    {
        System.out.println("----//----//----//-----//-----//-----//-----");
        System.out.println("|--       Welcome to the Login Page      --|");
        System.out.println("----//----//----//-----//-----//-----//-----");
        System.out.print("\nEnter the username :-");
        un=s1.next();
        System.out.print("\nEnter the Password :-");
        pass=s1.next();
        validate();// Call validate() method to check credentials
    
    }
      // Method to validate login credentials with the database
    public void validate()
    {
        try
        {
            ps=con.prepareStatement("select * from admin where un=? and pass=?");
            ps.setString(1, un); // Set username
            ps.setString(2, pass); // Set password
            
            rs=ps.executeQuery(); // Execute the query
            
            if(rs.next())
            {
                coun_name=un;
                menu obj=new menu(con,s1,ps);
            }
            else
            {
                System.out.println("\n Invalid Username or Password");
            }
        }
        catch(Exception ee)
        {
            System.out.println("Errorr "+ee);
        }        
    }

  
}
