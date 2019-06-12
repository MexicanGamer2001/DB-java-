/**
 * File: faculty.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 14 April, 2018
 *
 * Faculty model object
 */

package model;
import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
public class Faculty{


    /**
     * Constructor for faculty
     * Sets role, fullname, email, phone, and department on succesful login
     * Then calls facultyFetch
     * @param username
     * @param password
     * @param db MySQL database object
     */
    
   User fuser;
   MyDatabase fdb;
   ArrayList<ArrayList<String>> list;
   ArrayList<String> setList;
   public Faculty(User s, MyDatabase db) {
      fuser=s;
      fdb=db;
   //    test();        
   }

   /**
   * View current student status
   * @return {fullname, capstoneid, sid, title, title, grade, date, name, defensedate}
   */
        
   public ArrayList<ArrayList<String>> ViewStudent() {
      list=new ArrayList<ArrayList<String>>();
      String sql=("select users.username, users.fullname, capstone.capstoneid,  statushistory.sid, capstone.title, capstone.grade, statushistory.date, status.name, capstone.defensedate, capstone.desc  "
                  +"from (((users inner join committee on users.username=committee.username and position='Student' and committee.capstoneid in (select capstoneid from committee where username='"+fuser.getUsername()+"' and committee.has_accepted=1)) "
                  +" inner join capstone on capstone.capstoneid=committee.capstoneid) inner join statushistory  on statushistory.capstoneid=capstone.capstoneid and date in (select max(date) from statushistory where prove=0 group by capstoneid)) inner join status on status.sid=statushistory.sid;"); 
   
      list=fdb.getData(sql);
      
      return list;        
   }
   
  /**
   * View sutdent status
   * @return {fullname, capstoneid, sid, title, grade, date, name, defensedate}
   */
   public ArrayList<ArrayList<String>> ViewTrackingStudent()
   {
      list=new ArrayList<ArrayList<String>>();
      String sql=("select users.fullname, capstone.capstoneid,  statushistory.sid, capstone.title, capstone.grade, statushistory.date, status.name, capstone.defensedate "
                  +"from (((users inner join committee on users.username=committee.username and position='Student' and Tracking=1 and committee.capstoneid in (select capstoneid from committee where username='"+fuser.getUsername()+"')) "
                  +" inner join capstone on capstone.capstoneid=committee.capstoneid) inner join statushistory  on statushistory.capstoneid=capstone.capstoneid and date in (select max(date) from statushistory where prove=0 group by capstoneid)) inner join status on status.sid=statushistory.sid;"); 
   
      list=fdb.getData(sql);
      return list;         
   }
   
  /**
   * View history of a student
   * @param username 
   * @return {capstoneid, fullname, title, grade, date, name, stepcode, description}
   */
   public ArrayList<ArrayList<String>> ViewHistory(String username)
   { 
      setList = new ArrayList<>(Arrays.asList(username, fuser.getUsername()));;
   
      String sql =("select committee.capstoneid, users.fullname, capstone.title, capstone.grade, statushistory.date, status.name, status.stepcode, " 
                    +"status.description from (((users inner join committee on users.username=committee.username and users.username=? and position='student' and committee.capstoneid in "
                    +"(select capstoneid from committee where username=? and committee.has_accepted=1 )) inner join capstone on committee.capstoneid=capstone.capstoneid)"
                    +" inner join statushistory on capstone.capstoneid=statushistory.capstoneid and prove=0) inner join status on statushistory.sid=status.sid;");
                       
      list=fdb.getData(sql,setList);
      return list;        
   }
   
  /**
   * Enter final grade 
   * @param grade 
   * @param id capstone id
   */
   public void enterFinalGrade(String grade, String id){
      
      String sql="Update capstone set grade=? where capstoneid= (select capstoneid from committee where position='Student' and capstoneid=?);";
      setList = new ArrayList<>(Arrays.asList(grade, id));
      boolean rc=fdb.setData(sql, setList);
      System.out.println(rc);
   }
   
 /**
   * Apply list of students
   * @return {username, fullname, email, capstoneid, title, description}
   */  
   public ArrayList<ArrayList<String>> ApplyList(){
      String sql=("select users.username, fullname, email, committee.capstoneid, capstone.title, capstone.desc from users "
              +"inner join committee on users.username=committee.username and position='student' and capstoneid in (select capstoneid from committee where username='"+fuser.getUsername()+"' and has_accepted=0 and has_declined=0) "
              +"inner join capstone on capstone.capstoneid=committee.capstoneid;");
      ArrayList<ArrayList<String>> list=fdb.getData(sql);
      return list; 
   }
   
   
  /**
   * Update committee
   * @param id capstone id
   * @param decide will or will not join committee
   */
   public void decideCommittee(String id, int decide) {
      String sql;
      setList= new ArrayList<>(Arrays.asList(id));
      if(decide==1){
         sql ="update committee set has_accepted=1 where username='"+fuser.getUsername()+"' and capstoneid=?;";
      }
      else
      {
         sql="update committee set has_declined=1 where username='"+fuser.getUsername()+"' and capstoneid=?;";
      }
          
      boolean rc=fdb.setData(sql, setList);
      System.out.println(rc); 
        
   }
  /**
   * @return {capstoneid, sid, fullname, title, name}
   */
   public ArrayList<ArrayList<String>> proveList()
   {
      String sql=("select capstone.capstoneid, statushistory.sid, fullname, capstone.title, status.name from (((users inner join committee on users.username=committee.username and committee.position='student') "
                 +"inner join capstone on committee.capstoneid=capstone.capstoneid) inner join statushistory "
                 +"on statushistory.capstoneid=capstone.capstoneid and date in (select max(date) from statushistory group by capstoneid) and prove=1 ) "
                 +"inner join status on status.sid=statushistory.sid;");
      ArrayList<ArrayList<String>> list=fdb.getData(sql);
      return list; 
   }
   
   /**
    * @param sid student id
    * @param id capstone id
    * @param decide will or will not accept 
    */   public void prove(String sid,String id, int decide)
   {
      String sql;
      setList= new ArrayList<>(Arrays.asList(id, sid));
   //1 for accept
      if(decide==1)
      {
         sql="update statushistory set prove =0 where capstoneid =? and sid=?;";
      }
      else
      {
         sql="delete from statushistory where capstoneid =? and sid=?;";
      }
      boolean rc=fdb.setData(sql, setList);
      System.out.println(rc); 
   }
}