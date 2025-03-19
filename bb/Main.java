
package bb;

import aa.Loginpage;
import java.util.Scanner; // Imports Scanner class to take user input.

public class Main {
    public static void main(String[] args){
        Scanner s1=new  Scanner (System.in);
        Loginpage obj=new Loginpage(s1);// Creating an instance of the Loginpage class and passing the Scanner object
        
       obj.insert(); // Calling the insert() method, which is likely used to take user login details
       obj.validate();// Calling the validate() method, which is probably used to check login credentials
        
    }
}