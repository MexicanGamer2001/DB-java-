/**
 * File: staff.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 14 April, 2018
 *
 * Staff model object
 */

package model;
import java.util.Scanner;
import javax.swing.*;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
public class Staff {
   private String capstoneID;

   /**
    * Constructor for staff
    * Sets role, fullname, email, phone, and department on succesful login
    * Then calls staffFetch
    * @param username
    * @param password
    * @param db MySQL database object
    */
    
    
   User fuser;
   MyDatabase sdb;
   ArrayList<String> value; 
   ArrayList<ArrayList<String>> List;
   public Staff (User s, MyDatabase db){
      fuser=s;
      sdb=db;
   }
   /**
    * Retrieve list of students
    * @return {username, fullname, capstoneid, sid, title, grade, date, name, defensedate, description, plagiarismscore}
    */
   public ArrayList<ArrayList<String>> StaffStudentList(){
      List =new ArrayList<ArrayList<String>>();
     
      String sql=("select users.username, users.fullname, capstone.capstoneid, statushistory.sid, capstone.title, capstone.grade, statushistory.date, status.name, capstone.defensedate, capstone.desc, capstone.plagerismscore from "
                   +"(((users inner join committee on users.username=committee.username and committee.position='student') inner join capstone "
                   +"on capstone.capstoneid=committee.capstoneid) inner join statushistory on statushistory.capstoneid=capstone.capstoneid and date in (select max(date) from statushistory where prove=0 group by capstoneid)) inner join status "
                   +"on status.sid=statushistory.sid;");
      List=sdb.getData(sql);
      
      return List;  
   }

   /**
    * View history of user
    * @param username username
    * @return {capstoneid, fullname, title, grade, date, name, stepcode, description}
    */
   public ArrayList<ArrayList<String>> ViewAllHistory(String username)
   {
      List =new ArrayList<ArrayList<String>>();
      String sql=("select committee.capstoneid, users.fullname, capstone.title, capstone.grade, statushistory.date, status.name, status.stepcode, " 
               +"status.description from (((users inner join committee "
               +"on users.username=committee.username and committee.username= ? ) inner join capstone "
               +"on committee.capstoneid=capstone.capstoneid) inner join statushistory on capstone.capstoneid=statushistory.capstoneid and prove=0) "
               +"inner join status on statushistory.sid=status.sid;");
      value=new ArrayList<>(Arrays.asList(username));
      List=sdb.getData(sql, value);
      return List;
   
   }
   /**
    * Set plagiarism score for project
    * @param score plagiarism score
    * @param id capstone id
    */
   public void SetScore(String score, String ID)
   {
      String sql="update capstone set plagerismscore=? where capstoneid=?;";
      value=new ArrayList<>(Arrays.asList(score, ID));
      if (sdb.setData(sql,value)==true)
      {
         System.out.println("S");
      }
      else
      {
         System.out.println("F");
      }
   
   }
   
   /**
    * Retrieve completed capstones
    * @return {username, fullname, capstoneid, sid, title, grade, date, name, defensedate, description, plagiarismscore}
    */
   public ArrayList<ArrayList<String>> ViewCompleteCapstone(){
      List =new ArrayList<ArrayList<String>>();
      String sql=("select users.username, users.fullname, capstone.capstoneid, statushistory.sid, capstone.title, capstone.grade, statushistory.date, status.name, capstone.defensedate, capstone.desc, capstone.plagerismscore from"
                +" ((users inner join committee on users.username=committee.username and committee.position='student') inner join capstone "
                +"on capstone.capstoneid=committee.capstoneid) inner join statushistory on statushistory.capstoneid=capstone.capstoneid and sid=1700 and date in (select max(date) from statushistory where prove=0 group by capstoneid) inner join status"
                +" on status.sid=statushistory.sid;");
      List=sdb.getData(sql);
      
      return List;  
   }

  /**
   * Change student status
   * @param id capstone id
   * @param sid student id
   */
   public void ChangeStatus(String ID, String sid){
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
      LocalDateTime now = LocalDateTime.now(); 
      String time=dtf.format(now);
      String sql;
      if((sid.equals("400"))||(sid.equals("900"))||(sid.equals("1200"))||(sid.equals("1700")))
      {
         sql="Insert INTO statushistory(capstoneid, sid, date, prove) values (?, ?, ?, 1) ;";
         JOptionPane.showMessageDialog(null,"You Apply will sent to Dr.YU.");
      }
      else
      { 
         sql="Insert INTO statushistory(capstoneid, sid, date, prove) values (?, ?, ?, 0);";
      }
      value=new ArrayList<>(Arrays.asList(ID, sid, time));
      boolean rc=sdb.setData(sql, value);
      if(rc)
      {
         System.out.println("Change success");
      }
      else
      {
         System.out.println("Fail to Change");
      }
   
   
   }
   /**
    * Retrieve name of faculty
    * @param capstoneid capstone id
    * @return {fullname}
    */
   public ArrayList<ArrayList<String>> FacultyName(String capstoneid)
   {
      List =new ArrayList<ArrayList<String>>();
      String sql=("select fullname from users inner join Committee on users.username=Committee.username and capstoneid=? and position !='student';");
      value=new ArrayList<>(Arrays.asList(capstoneid));
      List=sdb.getData(sql, value);
      return List;  
   }
   
   
  /**
   * Update proposal
   * @param sid student id
   * @param name updated name
   * @param description updated description
   */
   public void UpdateProposal(String sid, String name,String description)
   {
   
      String sql ="update status set name =? , description =? where sid=?;";
      
      value=new ArrayList<>(Arrays.asList(name,description, sid));
      
      boolean rc=sdb.setData(sql, value);
   
      if(rc)
      {
         System.out.println("Change success");
      }
      else
      {
         System.out.println("Fail to Change");
      }
   
   
   
   }
   /**
    * Retrieves full status list
    */
   public ArrayList<ArrayList<String>> statusList()
   {
      List =new ArrayList<ArrayList<String>>();
   
      String sql=("select * from status;");
      List=sdb.getData(sql);
      
      return List;  
   
   
   }


  
   
 

}