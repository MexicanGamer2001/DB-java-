/**
 * File: Main.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 3 April, 2018
 *
 * Capstone model object
 */
import model.*;
import view.*;

public class Main {
   
   public static void main(String [] args) {
      MyDatabase db =new MyDatabase();
   
      if (db.connection() == true)
      {
         System.out.println("Database Open");
      }
      else
      {
         System.out.println("Could Not find");
      }
      //all password = "password"
      Login login = new Login();
   
   }

}