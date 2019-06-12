/**
 * File: user.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 14 April, 2018
 *
 * User adapter for controller
 */

package model;


import java.util.ArrayList;
import java.util.Arrays;

public class User {
   protected String username;
   private String password;
   protected String role;
   protected String fullname;
   protected String email;
   protected String phone;
   protected String department;
   protected boolean login = false;
   MyDatabase sdb;
   public User(String username, String password, MyDatabase db) {
      this.username = username;
      this.password = password;
   
      this.login = login(db);
      sdb=db;
   }

   /**
    * Retrieve generic user data
    * @param db MySQL database object
    */
   public void fetch(MyDatabase db) {
      String query = "SELECT * FROM Users WHERE username=?;";
      ArrayList<String> values = new ArrayList<>(Arrays.asList(username));
   
      ArrayList<ArrayList<String>> rs = db.getData(query, values);
      role = rs.get(1).get(1); 
      fullname = rs.get(1).get(3);
      email = rs.get(1).get(4);
      phone = rs.get(1).get(5);
      department = rs.get(1).get(6);
   }

   /**
    * Login authentication function
    * @param db MySQL database object
    */
   private boolean login(MyDatabase db) {
      String query = "SELECT fullname FROM Users WHERE username=?  AND password= sha1(?);";
      ArrayList<String> values = new ArrayList<>(Arrays.asList(username, password));
   
      ArrayList<ArrayList<String>> result = db.getData(query, values);
   
      if(result.size() == 1) {
         System.out.println("No data found");
         return false;
      }
      fetch(db);
      return true;
   }

   /**
    * Change user password in database
    * @param old old password
    * @param newone new password
    * @param newtwo new typed a second time for confirmation
    */
  public void changePassword(String old, String newone, String newtwo){
      if (old.equals(password))
      {
         if(newone.equals(newtwo))
         {
         
            ArrayList<String> list = new ArrayList<>(Arrays.asList(newone, getUsername()));
            String query="update users set password= ? where username=?;";
            if(sdb.setData(query, list)){
            
               System.out.println("Change successfully");
            }
            else
            {
               System.out.println("Fail to change");
            }
                   
         }
         else
         {
            System.out.println("new password Not match");
         }
      }
      else {
         System.out.println("Wrong password");
      }
   }

   /**
     * Accessor method for username
     */
   public String getUsername() {
      return username;
   }

   /**
    * Accessor method for role
    */
   public String getRole() {
      return role;
   }

   /**
    * Accessor method for fullname
    */
   public String getFullname() {
      return fullname;
   }

   /**
    * Accessor method for email
    */
   public String getEmail() {
      return email;
   }

   /**
    * Accessor method for phone
    */
   public String getPhone() {
      return phone;
   }

   /**
    * Accessor method for department
    */
   public String getDepartment() {
      return department;
   }

   /**
    * Accessor method for login
    */
   public boolean isLogin() {
      return login;
   }

   @Override
   public String toString() {
      return "User{" +
             "username='" + username + '\'' +
             ", role='" + role + '\'' +
             ", fullname='" + fullname + '\'' +
             ", email='" + email + '\'' +
             ", phone='" + phone + '\'' +
             ", department='" + department + '\'' +
             '}';
   }
}