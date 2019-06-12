/**
 * File: student.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 14 April, 2018
 *
 * Student object model
 */

 /**
    * Constructor for student
    * Sets role, fullname, email, phone, and department on succesful login
    * Then calls studentFetch
    * @param username
    * @param password
    * @param db MySQL database object
    */
    
package model;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
public class Student{
   private Capstone capstone = null;
   private String capstoneID; 

   User fuser;
   MyDatabase sdb;
   ArrayList<ArrayList<String>> list;
   ArrayList<String> setlist;
   public Student (User s, MyDatabase db){
      fuser=s;
      sdb=db; 
      //test();
   }
   
   
  /**
   * Retrive capstoneid, sid, title, grade, date, name, and defense date
   */
   public ArrayList<ArrayList<String>> ViewCurrent(){
      list=new ArrayList<ArrayList<String>>();
      String sql=("select users.fullname, capstone.capstoneid, statushistory.sid, capstone.title, capstone.grade, statushistory.date, status.name, capstone.defensedate, capstone.desc, capstone.type from (((users "
                 +"inner join committee on users.username=committee.username and committee.username='"+fuser.username+"') inner join capstone on capstone.capstoneid=committee.capstoneid) "
                 +"inner join statushistory on statushistory.capstoneid=capstone.capstoneid and date in (select max(date) from statushistory where prove=0 group by capstoneid)) inner join status on status.sid=statushistory.sid;");
     
      list=sdb.getData(sql);
      return list;  
   }
   
   /**
   View the List of faculty
   Output:
   |username                 
   |fullname                 
   |email                       
   **/
  /**
   * View list of faculty
   * @return {username, fullname, email}
   */
   public ArrayList<ArrayList<String>> FacultyList(){
      list=new ArrayList<ArrayList<String>>();
      String sql="Select username, fullname, email from users where role='faculty';";
      list=sdb.getData(sql);       
      return list;
   }
   
   /**
    * Set defense date for capstone
    * @param date defense date
    * @param ID capstoneid
    */
   public void setDefenseDate(String date, String ID)
   {  
      setlist= new ArrayList<>(Arrays.asList(date,ID));
      String query = "UPDATE Capstone SET defensedate= ?  WHERE capstoneid=?;";
      boolean complete = sdb.setData(query, setlist);
      System.out.println(complete);
   }
   
   
  /**
   * Create a new project
   * @param title project tile
   * @param desc project description
   * @param type thesis or capstone
   * @param facultyusername name of faculty member
   */
   public void createProject(String title, String desc, String type, String facultyusername) {     
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
      LocalDateTime now = LocalDateTime.now(); 
      String time=dtf.format(now);
      String query = "SELECT MAX(capstoneid) FROM Capstone";
      ArrayList<ArrayList<String>> rs = sdb.getData(query);
   
      capstoneID = Integer.toString(Integer.parseInt(rs.get(1).get(0)) + 1);
      
      String newCapstone = "INSERT INTO Capstone(capstoneid, title, Capstone.desc, type) VALUES (?, ?, ?, ?);";
      
      setlist = new ArrayList<>(Arrays.asList(capstoneID, title, desc, type));
       
      boolean complete = sdb.setData(newCapstone, setlist);
      System.out.println(title+" "+desc+" "+type+" "+facultyusername+" "+time);
      sdb.startTrans();
      if(complete) {
         String updateUser = "INSERT INTO committee VALUES (?, ?, 0, 0,'Student', 0);";
         setlist = new ArrayList<>(Arrays.asList(fuser.username, capstoneID));
         complete = sdb.setData(updateUser, setlist);
         System.out.println(1);
      
         if(complete)
         {
         
            System.out.println("Create Succeefully");
           
            String newHistory="insert into statushistory(capstoneid, sid, date, prove) values(?,100,?, 0);";
            setlist = new ArrayList<>(Arrays.asList(capstoneID, time));
            complete=sdb.setData(newHistory, setlist);
            if(complete)
            {
             
               ApplyFaculty(facultyusername, capstoneID);
               System.out.println("Success on status history");
                                              
            }
            else{
               sdb.rollbackTrans();
               System.out.println("Fail on status history");   
            }
         }
                    
         else {
           
            System.out.println("Fail to Inset committee");
            sdb.rollbackTrans();
         }
      } 
      else {
         sdb.rollbackTrans();
         System.out.println("Fail to insert faculty");
      }
      sdb.endTrans();
   }
   
   /**
    * Select a faculty member for committee
    * @param username username of faculty member
    * @param id capstone id
    */
   public void ApplyFaculty(String username, String ID){ 
      String sql="Insert Into committee(username, capstoneid, position) VALUES (?,?,'Faculty');";
      setlist = new ArrayList<>(Arrays.asList(username, ID));
      boolean success = sdb.setData(sql,setlist);
      
      if(success){
         System.out.println("Apply Success");
      }
      else 
      {
         System.out.println("Fail to Apply");
      }
   }

  /**
   * Change faculty
   * @param username username of faculty
   * @param id capstone id
   * @param type thesis or capstone
   */
   public void ChangeFaculty(String username, String ID, String type)
   {
      setlist = new ArrayList<>(Arrays.asList(ID));
      String sql="select * from committee where position !='student' and capstoneid =?;";
      list=sdb.getData(sql, setlist);
      if(type.equals("Project"))
      {
         if (list.size()<=3)
         {
            ApplyFaculty(username, ID);
         }
         else
         {
            JOptionPane.showMessageDialog(null,"You Can't add more than tree faculty for this "+type+".");
         }    
      }
      else 
      {
         if (list.size()<=5)
         {
            ApplyFaculty(username, ID);
         }
         else
         {
            JOptionPane.showMessageDialog(null,"You Can't add more than four faculty for "+type+".");
         } 
      
      }
   
   }
   
  /**
   * Edit project description
   * @param id capstone id
   * @param desc new project description
   */
   public void StudentEdit(String ID, String desc)
   {
      String sql="update capstone set capstone.desc=? where capstoneid=?;";
      setlist = new ArrayList<>(Arrays.asList(desc, ID));
      if(sdb.setData(sql, setlist)==true)
      {
         System.out.println("Success");
      }
      else
      {
         System.out.println("Fail");
      }
   }
   
   
   /**
    * Change password
    */
   public void change(){
      Scanner sc = new Scanner(System.in);
      System.out.println("old ");
      String old=sc.nextLine();
      
      System.out.println("new one");
      String newone=sc.nextLine();
      System.out.println("new two");
      String newtwo=sc.nextLine();
      
      fuser.changePassword(old, newone, newtwo);
   
   }

   /**
    * Delete project
    * @param capstoneid capstone id
    */
   public void deleteProject(String capstoneid){
      String sql="delete from committee where capstoneid=?;";
      setlist = new ArrayList<>(Arrays.asList(capstoneid));
      boolean rc=sdb.setData(sql, setlist);
      sdb.startTrans();
      if(rc == true)
      {  
         sql="delete from statushistory where capstoneid=?;";
         rc=sdb.setData(sql, setlist);
         if(rc==true)
         {
            sql="delete from capstone where capstoneid=?;";
            rc=sdb.setData(sql, setlist);
            System.out.println("Success");
         }
         else
         {
            sdb.rollbackTrans();
         }
      }
      else
      {
         sdb.rollbackTrans();
      }
      sdb.endTrans();
   
   }

   
   /**
    * Retrieve faculty
    * @param capstoneid capstone id
    * @return {fullname, username}
    */
   public ArrayList<ArrayList<String>> FacultyName(String capstoneid){
      list=new ArrayList<ArrayList<String>>();
      String sql=("select fullname, Committee.username from users inner join Committee on users.username=Committee.username and capstoneid=? and position !='student';");
      setlist = new ArrayList<>(Arrays.asList(capstoneid));
      list=sdb.getData(sql, setlist);
      return list;  
   }
   
   /**
    * Remove a faculty member from a project
    * @param id capstone id
    * @param oldFacultyName name of faculty member to remove
    * @param newFacultyName replacement faculty
    */
   public void deleteFaculty(String id, String oldFacultyName, String newFacultyName)
   {
   String sql="delete from committee where username=? and capstoneid=?;";
   setlist = new ArrayList<>(Arrays.asList(oldFacultyName, id)); 
   boolean success = sdb.setData(sql,setlist);
      
      if(success){
         ApplyFaculty(newFacultyName, id);
         System.out.println("Delete Success");
      }
      else 
      {
         System.out.println("Fail to Delete");
      }  
   
   }
   
   
   
}