/**
 * File: Login.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 3 April, 2018
 *
 * Capstone model object
 */
package view;

import model.MyDatabase;
import model.User;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Login extends JFrame implements ActionListener{          
   JButton login, exit;//The button for login, exit
   JLabel jlEmail, jlPass;//the Label for email and password
   JTextField jtfEmail;//the Text feild for email
   JPasswordField jtfPass;//password field to hide the password
   JCheckBox jcReme;//to remember the password
      
   public static void main(String[] args)  
   {  
      Login lo = new Login();
   }  
      
   public Login()  
   {  
      
      JPanel jp2 = new JPanel(new GridLayout(2,2,15,15));//to store the password and email
      //email label
      jlEmail = new JLabel("Username: ",SwingConstants.LEFT);
      jlEmail.setBorder(BorderFactory.createEmptyBorder(0, 73, 0, 0));
      //password label
      jlPass = new JLabel("Password: ",SwingConstants.LEFT);
      jlPass.setBorder(BorderFactory.createEmptyBorder(0, 73, 0, 0));
      
      
      jtfEmail = new JTextField(12);//email text field set 12
      jtfPass = new JPasswordField(12);//password text field
      
      //to add the jp2
      JPanel jp1 = new JPanel();
      jp1. setPreferredSize(new Dimension(400, 75));
      //add to Borderlayout center
      jp2.add(jlEmail);
      jp2.add(jtfEmail);
      jp2.add(jlPass);
      jp2.add(jtfPass);
      
      jp1.add(jp2);
      //check box to remember password
      jcReme = new JCheckBox(" Remember Password");
      //loginbutton
      login = new JButton("Log In");
      login.setPreferredSize(new Dimension(140, 25));
      //exit button
      exit = new JButton("Exit");
      exit.setPreferredSize(new Dimension(70, 25));
      //jp3 to add check box
      JPanel jp3 = new JPanel();
      jp3.add(jcReme);
      
      JPanel jp4 = new JPanel();
      
      jp4.add(login);
      jp4.add(exit);
      
      jp4.setBorder(BorderFactory.createEmptyBorder(0, 73, 0, 0));
      JPanel jp5 = new JPanel(new GridLayout(0,1));
      jp5.add(jp3);
      jp5.add(jp4);
      jp5.setPreferredSize(new Dimension(50, 90));
      JPanel jp6 = new JPanel();
      jp6.setPreferredSize(new Dimension(20, 37));
      jp6.setBackground(new Color(255,153,0));
      JLabel RITJL = new JLabel("R I T");
      jp6.add(RITJL);
      RITJL.setFont(new Font("Serif", Font.BOLD , 25));
      jp1.setBorder(BorderFactory.createEmptyBorder(0, -75, 0, 0));
      jp5.setBorder(BorderFactory.createEmptyBorder(0, -75, 0, 0));
      this.add(jp1,BorderLayout.CENTER);
      this.add(jp6,BorderLayout.NORTH);
      this.add(jp5,BorderLayout.SOUTH);
      
      this.setVisible(true);
      this.setBounds(300, 600, 300, 250);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);   
      this.setTitle("Log"); 
      this.setResizable(false);
      login.addActionListener(this);
      exit.addActionListener(this);
      
      checkSavePassword();
      pack();
   }  
   File file = new File("save.txt");
   @Override  
    public void actionPerformed(ActionEvent e) { 
      //choice to exit 
      if(e.getActionCommand()=="Exit")  
      {  
         System.exit(0);  
      }   
      //choice to login
      if(e.getActionCommand()=="Log In")  
      {  
         
         try {
            if(!file.exists()){ 
               file.createNewFile();  
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
         
            if(jcReme.isSelected())
            {
               //add into remember password file
               bw.write(jtfEmail.getText()); 
               bw.newLine(); 
               bw.write(jtfPass.getPassword()); 
               bw.close(); 
            } 
            else{
               bw.write(""); 
               bw.close(); 
            }
            
         }
         catch(IOException ioe){
            ioe.printStackTrace();
         }  
         
         if( checkEmpty())
         {
         
         }
         else{
            loginIntoSystem();
         }
      }  
   }  
   /*
   *login to system
   *define by user roles
   */
   public void loginIntoSystem()
   {
      String passwordString = String.valueOf(jtfPass.getPassword()).toString();
      String userName = jtfEmail.getText();
      
      
      MyDatabase mySQLdb=new MyDatabase(); 
    
      if (mySQLdb.connection()==true)
      {
         System.out.println("Database Open");        
      }
      else
      {
         System.out.println("Could Not find");
      }
      
      User s=new User(userName,passwordString, mySQLdb);
      String positionName =  s.getRole();
      //login to diiferenet interface
      if(s.isLogin()!=false)
      {
         if(positionName.equals("faculty"))
         {
            this.dispose(); 
            FacultyGUI ftyGUI = new FacultyGUI(s);
         }
         else if(positionName.equals("student"))
         {
            this.dispose(); 
            StudentGUI stGUI = new StudentGUI(s);
         }
         else if(positionName.equals("staff"))
         {
            this.dispose(); 
             StaffGUI sfGUI = new StaffGUI(s);
         
         }
      }
      else
      {
         JOptionPane.showMessageDialog(this,
                  "Can not find Username or password not match",
                  "Login error",
                  JOptionPane.ERROR_MESSAGE);
      }   
   }  
   /*
    *check for save password
   */
   public void checkSavePassword()
   {
   
      String line ="";
      ArrayList<String> aList = new ArrayList<String>();
      try {
         if(file.exists()){//if this file exists
            
            BufferedReader br = 
                new BufferedReader(new FileReader(file));
            
            while((line = br.readLine()) != null) {
               aList.add(line);
            }   
            if(aList.size() >=1 )
            {
               jcReme.setSelected(true);
               jtfEmail.setText(aList.get(0));  //append the text to name field
               jtfPass.setText(aList.get(1)); //append the text to password field
            }
         }
      
      } catch (FileNotFoundException e) {         
         e.printStackTrace();
      }   
      catch (IOException e) {         
         e.printStackTrace();
      }        
   }
   /*
    *check user enter 
   */
   public boolean checkEmpty()
   {
      String passwordString = String.valueOf(jtfPass.getPassword()).toString();
      if(passwordString.equals("") && jtfEmail.getText().equals(""))
      {
         JOptionPane.showMessageDialog(this,
            "Username and password Cannot be null",
            "Login error",
            JOptionPane.ERROR_MESSAGE);
         return true;
      }
      else if(jtfEmail.getText().equals(""))
      {
         JOptionPane.showMessageDialog(this,
            "Username Cannot be null",
            "Username error",
            JOptionPane.ERROR_MESSAGE);
         return true;
      }
      else if(passwordString.equals(""))
      {
         JOptionPane.showMessageDialog(this,
            "Password Cannot be null",
            "Password error",
            JOptionPane.ERROR_MESSAGE);
         return true;
      }
      else
      {
         return false;
      }
   }        
}  
