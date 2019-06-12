/**
 * File: StudentGUI.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 3 April, 2018
 *
 * Capstone model object
 */

package view;
import model.User;
import model.Student;
import model.*;

import javax.swing.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StudentGUI extends JFrame implements ActionListener{
   
   JButton login, exit;//The button for login, exit
   JLabel jlEmail, jlPass;//the Label for email and password
   JTextField jtfEmail;//the Text feild for email
   JPasswordField jtfPass;//password field to hide the password
   JCheckBox jcReme;//to remember the password


   JPanel northPc, north, northInfo;
   JLabel pic;
   JLabel jlname,jlposition, jlOffice,jlemail, jlphone;
   JButton jb1, jb2,jb3, viewProjectJB,cancelProjectJB;
 
   JLabel jlAProject;
   JTextArea jtaProject;


   JTextField tField;
   JPasswordField pwField;
   JTextArea CreateProjectInfo;
   JFormattedTextField formattedField;
   JPanel south;
   JPanel southPanel;
   JPanel rightContain;
   JButton  facultyLSJB = new JButton("Faculty List");
         
   String currentCapstoneid ; 
   String currentSid   	    ;
   String currentTitle  	;
   String currentGrade  	;
   String currentDate  	    ;
   String currentName  	    ;
   String currentDefensedate;
   String currentDesc;
   String currentType;
   
   String facultyTitleType;
   String oldFaculty;
   boolean changeFaculty = false;
   boolean forchangesFacutl = false; //actual
   
   int position;
   User sl;
   MyDatabase fdb =  new MyDatabase();
   Student st;
   
   
   JButton changePasswordJB;
   
   
   //left
   JButton createProjectJB;
   public StudentGUI(User s)  {
      sl = s; 
      st = new Student(sl,fdb);
            
      //Layout for BorderLayout North
      JPanel north = new JPanel();//for north Panel
      north.setPreferredSize(new Dimension(800,200));      
   
      //the person information and set font
      jlname = new JLabel(s.getFullname());//JLabel for name
      jlposition = new JLabel(s.getRole());//JLabel for position
      jlemail = new JLabel("Email: " + s.getEmail());//JLabel for email address
      jlphone = new JLabel("Phone: " + s.getPhone());//JLabel for phone number
      jlOffice =  new JLabel("Department: "+ s.getDepartment());//JLabel for department
   
      /*
       *
       *set the font size and font style for personal inforamtion  
       *
       */
      jlname.setFont(new Font("Serif", Font.BOLD, 25));
      jlposition.setFont(new Font("Serif", Font.ITALIC , 17));
      jlemail.setFont(new Font("Serif", Font.ITALIC , 17));
      jlphone.setFont(new Font("Serif", Font.ITALIC , 17));
      jlOffice.setFont(new Font("Serif", Font.ITALIC , 17));
      //add into the container
      
      /*
       *create a JPanel to container the personal inforamtion JLabels
       *add  information Jlabels to information container JPanel
       *set the size and position
       */
      JPanel containInfo = new JPanel(new GridLayout(5,1,0, 0));
      containInfo.add(jlname);
      containInfo.add(jlposition);
      containInfo.add(jlemail);
      containInfo.add(jlphone);
      containInfo.add(jlOffice);
      containInfo.setPreferredSize(new Dimension(750,200));
      containInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
      
      /*
       *Create a Janel to Container the container JPanel
       *To set by using the borderLayout, East
       *set the size and position
       */
      JPanel personInfoJP = new JPanel();
      personInfoJP.add(containInfo,BorderLayout.EAST);
      personInfoJP.setPreferredSize(new Dimension(800,200));
   
      north.add(personInfoJP);
      this.add(north,BorderLayout.NORTH); 
      //Layout of North borderlayout of JFrame      
   
      /////
      JPanel center = new JPanel();
      center.setBackground(Color.black);
      JLabel ritJL = new JLabel("R I T");
      ritJL.setForeground (Color.white);
      center.setPreferredSize(new Dimension(800,40));
      ritJL.setFont(new Font("Serif", Font.BOLD , 25));
      center.add(ritJL);
   
      createProjectJB = new JButton("Create Project");
      jb2 = new JButton("Faculty Search");
      viewProjectJB = new JButton("View Project");
      
      
      createProjectJB.setPreferredSize(new Dimension(148, 30));
      viewProjectJB.setPreferredSize(new Dimension(148, 30));
      jb2.setPreferredSize(new Dimension(148, 30));
      
      JPanel leftTopContain = new JPanel(new GridLayout(3,1,5,5));
   
      leftTopContain.add(createProjectJB);
      leftTopContain.add(viewProjectJB);
      leftTopContain.add(jb2);
      
      
   
      southPanel = new JPanel();
      southPanel.setPreferredSize(new Dimension(800,530));
      JPanel leftContain = new JPanel();
      leftContain.setPreferredSize(new Dimension(150,500));
      
   
   
   
      JPanel leftBotContain = new JPanel(new GridLayout(1,1,5,5));

      leftBotContain.setBorder(BorderFactory.createEmptyBorder(325, 0, 0, 0));
      
      leftContain.add(leftTopContain);
      leftContain.add(leftBotContain);
     
     
      rightContain = new JPanel();
      rightContain.setPreferredSize(new Dimension(600,500));
      rightContain.setBorder(BorderFactory.createLineBorder(Color.red, 3));
      
      southPanel.add(leftContain);
      southPanel.add(rightContain);
      
      this.add(north,BorderLayout.NORTH);
      this.add(center,BorderLayout.CENTER);
      this.add(southPanel,BorderLayout.SOUTH);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setSize(750,800);
      this.setResizable(false);
      this.setVisible(true);
      this.setTitle("Student");
   
      createProjectJB.addActionListener(this);
      jb2.addActionListener(this);
      viewProjectJB.addActionListener(this);
      facultyLSJB.addActionListener(this);
   
      projectList();

      pack();
   }

   JPanel southsouth;
   
   JTextField titleJtf;
   JComboBox<String> ProjectTypeList;
   JTextArea tArea;
   
   JButton facultyJB;
   JButton createJB;
   JButton cancelJB; 
   
   String projectTitle;
   String projectDesc;
   String projectType;
   String projectFaculty;
   
   String ProjectTitle;
   String facultyUsername;
   String facultySelected;
   
   /**
   *This method prove a interface to user for create a project.
   */
   public void createProject(){
      JPanel NorthContain = new JPanel();
      JLabel northTitle = new JLabel("New Project");// faculty _list will be same
      northTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//the hight
      northTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      NorthContain.setPreferredSize(new Dimension(590,44));
      
      NorthContain.add(northTitle, BorderLayout.NORTH);
      NorthContain.add(aLine, BorderLayout.CENTER);
     
      rightContain.add(NorthContain, BorderLayout.NORTH);      
   
      JPanel middleJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
      middleJP.setPreferredSize(new Dimension(570,450));
      JScrollPane js = new JScrollPane(middleJP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      js.setPreferredSize(new Dimension(590,300));  
     
      //Provide a field let user to enter project title.  
      JPanel containTileJP = new JPanel();   
      JLabel titleJL = new JLabel("Project Title: ",FlowLayout.RIGHT);
      titleJL.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 20));
      titleJtf = new JTextField(20); 
      titleJtf.setText(projectTitle);
      titleJtf .setPreferredSize(new Dimension(150,25)); 
      containTileJP.add(titleJL);
      containTileJP.add(titleJtf);
      
      //Make Selection to user choose project or thesis.
      JLabel typeJl = new JLabel("Type:",FlowLayout.RIGHT);
      typeJl.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 20));
      JPanel containTileJP2 = new JPanel();   
      
      ProjectTypeList = new JComboBox<String>();
   
      ProjectTypeList.addItem("Project");
      ProjectTypeList.addItem("Thesis");
      
      containTileJP2.add(typeJl);
      containTileJP2.add(ProjectTypeList);
      
      JPanel centerContainJP = new JPanel();
      centerContainJP.add(containTileJP);
      containTileJP.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,80));
      centerContainJP.add(containTileJP2);
      
      //////////////////////////
      //Provide a field to user enter the description for project 
      JPanel centerJP = new JPanel(new GridLayout(2,1));
      JLabel desc = new JLabel("Decription:");
      desc.setFont(new Font("Serif", Font.PLAIN, 23));
      desc.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 458));
      
      tArea = new JTextArea();
      tArea.setText(projectDesc);
      tArea.setFont(new Font("Serif", Font.ITALIC, 19));
      tArea.setLineWrap(true);       // wrap line
      tArea.setWrapStyleWord(true);  // wrap line at word boundary
      tArea.setBackground(new Color(204, 238, 241)); // light blue
      
      
      //The scroll bar
      JScrollPane scroll = new JScrollPane(tArea);
      scroll.setPreferredSize(new Dimension(560, 200));
      
      JPanel southContainJP = new JPanel();
      
      southContainJP.setPreferredSize(new Dimension(580,350));
      
      southContainJP.add(desc, BorderLayout.SOUTH);
      southContainJP.add(scroll, BorderLayout.CENTER);
      
      
      JPanel containers = new JPanel(new GridLayout(2,1));
      JPanel ButtonJP = new JPanel(new GridLayout(1,3,10,10));
      
      //There are tree bottom: Select Faculty, Create, Cancel
      facultyJB = new JButton("Select Faculty");
      ButtonJP.add(facultyJB);
      
      createJB  = new JButton("Create");
      ButtonJP.add(createJB);
       
      cancelJB  = new JButton("Cancel");
      ButtonJP.add(cancelJB);
      ButtonJP .setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200));
      
      containers.add(ButtonJP);
      
      JLabel facultySeleJL = new JLabel(facultySelected);
      facultySeleJL.setFont(new Font("Serif", Font.PLAIN, 20));
      
      containers.add(facultySeleJL);
      
      
      southContainJP.add(containers, BorderLayout.SOUTH);
      
      
      southContainJP.setBorder(BorderFactory.createEmptyBorder(-13, 0, 0,0));
      
      rightContain.add(centerContainJP, BorderLayout.CENTER); 
      rightContain.add(southContainJP,BorderLayout.SOUTH);
      
      facultyJB.addActionListener(this);
      createJB.addActionListener(this);
      cancelJB.addActionListener(this); 
   
   }

   JButton setDateJB;
   JButton updateJB;
   JTextArea jta;
   /*
   *This method is when the user click
   
   */
   public void currentStatus(){
   
      JPanel studentInfo = new JPanel(new GridLayout(5,1));
         
      JPanel searchJP = new JPanel();
      JLabel searchTitle = new JLabel(" " + currentTitle);
      searchTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//the hight
      searchTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      searchJP.setPreferredSize(new Dimension(590,44));
      
      searchJP.add(searchTitle, BorderLayout.NORTH);
      searchJP.add(aLine, BorderLayout.CENTER);
      rightContain.add(searchJP, BorderLayout.NORTH); 
      
      
      studentInfo.setPreferredSize(new Dimension(570,170));
      
         
      JLabel something0 = new JLabel("<html><p>"+ currentTitle+ "</p><html>",SwingConstants.CENTER);
      JLabel something1 = new JLabel("\nCapstone Id: "+ currentCapstoneid);
      JLabel something2 = new JLabel("\nGrade: "+ currentGrade);
      JLabel something3 = new JLabel("Curretn Status: "+ currentName);
      JLabel something4 = new JLabel("<html><p>Current Date: "+ currentDate+ "</p><html>",SwingConstants.CENTER);
      JLabel something5 = new JLabel("<html><p>Defense  Date: "+ currentDefensedate+ "</p><html>",SwingConstants.CENTER);
      
       
      studentInfo.add(something1);
      studentInfo.add(something2);
      studentInfo.add(something3);
    
      something0.setFont(new Font("Serif", Font.BOLD, 25));
      something1.setFont(new Font("Serif", Font.ITALIC , 17));
      something2.setFont(new Font("Serif", Font.ITALIC , 17));
      something3.setFont(new Font("Serif", Font.ITALIC , 17));
      something4.setFont(new Font("Serif", Font.ITALIC , 17));
      something5.setFont(new Font("Serif", Font.ITALIC , 17));
         
      JPanel grid = new JPanel(new FlowLayout(FlowLayout.LEFT));
      JLabel IdJL = new JLabel("Desense Date: ");
      IdJL.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 20));
      DateFormat format = new SimpleDateFormat("yyyy--MMMM--dd");
   //    DefenseIDJtf = new JFormattedTextField(format);
      DefenseIDJtf  = new JTextField();
   //       DefenseIDJtf.setText();
   
      DefenseIDJtf.setPreferredSize(new Dimension(100,25));
      DefenseIDJtf.setText(currentDefensedate);
      
   
      
      grid.add(IdJL);
      grid.add(DefenseIDJtf);
      JPanel ButonnContain = new JPanel();
      ButonnContain.setPreferredSize(new Dimension(100,25));
      ButonnContain .setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));
      setDateJB = new JButton("Set Date");
      setDateJB.setPreferredSize(new Dimension(100,25));
            
      grid.add(setDateJB);
      
      studentInfo.add(grid);
         
      JPanel contain = new JPanel();
      JLabel editAreaJL =  new JLabel("Edit project: ");
      editAreaJL. setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 25));
      editAreaJL.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,420));
   
      contain.add(editAreaJL);  
      studentInfo.add(contain);
         
      
      rightContain.add(studentInfo, BorderLayout.CENTER); 
      
      
      JPanel jtaJP = new JPanel();
      jtaJP.setPreferredSize(new Dimension(590, 300));
      jta = new JTextArea();  
      jta.setText(currentDesc); 
      jta.setPreferredSize(new Dimension(480,400));
      
      jta.setFont(new Font("Serif", Font.ITALIC, 19));
      jta.setLineWrap(true);       // wrap line
      jta.setWrapStyleWord(true);  // wrap line at word boundary
      jta.setBackground(new Color(204, 238, 241)); // light blue
         
      JScrollPane scroll = new JScrollPane(jta);
      scroll.setPreferredSize(new Dimension(560, 200));
      jtaJP.add(scroll,FlowLayout.LEFT);
      updateJB = new JButton("Update");
      JPanel updateJP = new JPanel();
      updateJP.add(updateJB);
      jtaJP.add(updateJP);
      updateJP.setBorder(BorderFactory.createEmptyBorder(0, 490, 0,0));
      rightContain.add(jtaJP, BorderLayout.SOUTH); 
      setDateJB.addActionListener(this);
      updateJB .addActionListener(this);
        
   
   }
  
   ArrayList<JRadioButton> myList;
   ArrayList<String> FacultyNameList ;
   ArrayList<String> FacultyUsernameList;
   ArrayList<String> FacultyEmailList;
   
   JButton submitFacultyJB ;
   public void facultyList(){
      
      JPanel searchJP = new JPanel();
      JLabel searchTitle = new JLabel("Faculty List");
      searchTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//the hight
      searchTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      searchJP.setPreferredSize(new Dimension(590,77));
      
      searchJP.add(searchTitle, BorderLayout.NORTH);
      searchJP.add(aLine, BorderLayout.CENTER);
     
      JLabel nameApp = new JLabel(facultyTitleType);
      searchJP.add(nameApp, BorderLayout.CENTER);
      nameApp.setFont(new Font("Serif", Font.ITALIC , 23));//set font
      rightContain.add(searchJP, BorderLayout.NORTH);      
   
      /////////////////////////////////for title
   
      JPanel middleJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
       JScrollPane js = new JScrollPane(middleJP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                         
      js.setPreferredSize(new Dimension(590,300));
   
      
      
      FacultyNameList = new ArrayList<String>();
      FacultyUsernameList = new ArrayList<String>();
      FacultyEmailList = new ArrayList<String>();
      //Use arraylist to contain the faculty list.
      ArrayList<ArrayList<String>> list = st.FacultyList();
            
      if(list.size()>0)
      {
         for (int x=1; x<list.size(); x++){
            for (int y=0; y<list.get(x).size(); y++)
            {
               FacultyUsernameList.add(list.get(x).get(0));
               FacultyNameList.add(list.get(x).get(1));
               FacultyEmailList.add(list.get(x).get(2));
               break;     
            }                        
         }
      }
   
      ButtonGroup group = new ButtonGroup();// method for only click once of jradiobutton
      myList = new ArrayList<>();
      for(int i = 0; i < FacultyNameList.size(); i++){//add into button as a list, get studentName and status from databash 
         String output = String.format("%-40s", FacultyNameList.get(i));
         myList.add(new JRadioButton(output));
      }
      for(int i = 0; i < myList.size(); i++){
      
         myList.get(i).setFont(new Font("Serif", Font.BOLD , 25));
         group.add( myList.get(i));
         middleJP.add( myList.get(i));
      }
      
      middleJP.setPreferredSize(new Dimension(570,50*myList.size()));
   
      rightContain.add(js, BorderLayout.CENTER);      
      ///middle button for center 
      submitFacultyJB = new JButton(" OK ");
      southsouth = new JPanel();
      southsouth.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,300));
      southsouth.add(submitFacultyJB);
      rightContain.add(southsouth,BorderLayout.SOUTH);
      submitFacultyJB.addActionListener(this);
   }
   JButton clickForChangeJB;
   ArrayList<String> oldFacultyName;
   ArrayList<String> oldFacultyUsername ;
   public void changeTonewFaculty(){
      
      JPanel searchJP = new JPanel();
      JLabel searchTitle = new JLabel("Faculties in this project");
      searchTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//the hight
      searchTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      searchJP.setPreferredSize(new Dimension(590,77));
      
      searchJP.add(searchTitle, BorderLayout.NORTH);
      searchJP.add(aLine, BorderLayout.CENTER);
     
      JLabel nameApp = new JLabel(facultyTitleType);
      searchJP.add(nameApp, BorderLayout.CENTER);
      nameApp.setFont(new Font("Serif", Font.ITALIC , 23));//set font
      rightContain.add(searchJP, BorderLayout.NORTH);      
   
      /////////////////////////////////for title
   
      JPanel middleJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
   //       middleJP.setPreferredSize(new Dimension(570,450));
      JScrollPane js = new JScrollPane(middleJP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                         
      js.setPreferredSize(new Dimension(590,300));
   
      
      
      oldFacultyName = new ArrayList<String>();
      oldFacultyUsername = new ArrayList<String>();
      
      ArrayList<ArrayList<String>> list = st.FacultyName(currentCapstoneid);
            
      if(list.size()>0)
      {
         for (int x=1; x<list.size(); x++){
            for (int y=0; y<list.get(x).size(); y++)
            {
               oldFacultyName.add(list.get(x).get(0));
               oldFacultyUsername.add(list.get(x).get(1));
               break;         
            }                        
         }
      }
   
      ButtonGroup group = new ButtonGroup();// method for only click once of jradiobutton
      myList = new ArrayList<>();
      for(int i = 0; i < oldFacultyName.size(); i++){//add into button as a list, get studentName and status from databash 
         String output = String.format("%-40s", oldFacultyName.get(i));
         myList.add(new JRadioButton(output));
      }
      for(int i = 0; i < myList.size(); i++){
      
         myList.get(i).setFont(new Font("Serif", Font.BOLD , 25));
         group.add( myList.get(i));
         middleJP.add( myList.get(i));
      }
      
      middleJP.setPreferredSize(new Dimension(570,50*myList.size()));
   
      rightContain.add(js, BorderLayout.CENTER);      
      ///middle button for center 
      clickForChangeJB = new JButton("Changes");
      southsouth = new JPanel();
      southsouth.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,300));
      southsouth.add(clickForChangeJB);
      rightContain.add(southsouth,BorderLayout.SOUTH);
      clickForChangeJB.addActionListener(this);
   }

   JButton EnterJB;
   JTextField DefenseDateJtf ;
   JTextField DefenseIDJtf ;
   /*
   *This method for view the all the faculty on table
   */
   public void tableView(){
   
      JPanel searchJP = new JPanel();
      JLabel searchTitle = new JLabel("Faculty Table List");
      searchTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));
      searchTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      searchJP.setPreferredSize(new Dimension(590,44));
      
      searchJP.add(searchTitle, BorderLayout.NORTH);
      searchJP.add(aLine, BorderLayout.CENTER);
   
      rightContain.add(searchJP, BorderLayout.NORTH);      
   
      /////////////////////////////////for title
      
   
      ArrayList<ArrayList<String>> list = st.FacultyList();
   
      
      String [][] arr = new String[list.size()][list.get(0).size()];
      
      if(arr.length>1)
      {
      
         for(int x = 1; x < arr.length; x++){
            for(int y = 0; y < arr[0].length; y++)
            {
               arr[x][y] = list.get(x).get(y);
            }
         }
    
      
      }
     
      
      String[] columns = new String[] {
            "Username", "FullName", "Email"
         };
        
      JTable table = new JTable(arr, columns);
      
      JScrollPane js = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                         
      js.setPreferredSize(new Dimension(590,440));
      rightContain.add(js, BorderLayout.CENTER);  
      
   }
   
   ArrayList<String> currentCapstoneidArr ;
   ArrayList<String> currentSidArr;
   ArrayList<String> currentTitleArr ;
   ArrayList<String> currentGradeArr ;
   ArrayList<String> currentDateArr;
   ArrayList<String> currentNameArr;
   ArrayList<String> currentDefensedateArr;
   ArrayList<String> currentDescArr;
   ArrayList<String> currentTypeArr;
   
   ArrayList<JRadioButton> projectListButtonArr;
   JButton moreInfoJB;
   JButton EditJB;
   JButton addfacultyJB;
   JButton deleteJB;
   JButton changefacultyJB;
   
   ArrayList<ArrayList<String>> list;
  /**
  *This Method is show project list to user, what they have.
  */
   public void projectList(){
   
      JPanel trackJP = new JPanel();
      JLabel trackTitle = new JLabel("Student List");
      trackTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//the hight
      trackTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      trackJP.setPreferredSize(new Dimension(590,44));
      
      trackJP.add(trackTitle, BorderLayout.NORTH);
      trackJP.add(aLine, BorderLayout.CENTER);
      rightContain.add(trackJP, BorderLayout.NORTH);  
   
      list = st.ViewCurrent();
      currentCapstoneidArr = new ArrayList<String>();
      currentSidArr = new ArrayList<String>();
      currentTitleArr  = new ArrayList<String>();
      currentGradeArr  = new ArrayList<String>();
      currentDateArr  = new ArrayList<String>();
      currentNameArr   = new ArrayList<String>();
      currentDefensedateArr   = new ArrayList<String>();
      currentDescArr = new ArrayList<String>();
      currentTypeArr=new ArrayList<String>();
      if(list.size()>0)
      {
         for (int x=0; x<list.size(); x++){
            for (int y=0; y<list.get(x).size(); y++)
            {
               currentCapstoneidArr.add(list.get(x).get(1));
               currentSidArr.add(list.get(x).get(2));
               currentTitleArr.add(list.get(x).get(3));
               currentGradeArr.add(list.get(x).get(4));
               currentDateArr.add(list.get(x).get(5));
               currentNameArr.add(list.get(x).get(6));
               currentDefensedateArr.add(list.get(x).get(7));
               currentDescArr.add(list.get(x).get(8));
               currentTypeArr.add(list.get(x).get(9));            
               break;     
            }
          //  System.out.printf("| \n");
                  
         }
      }
     
   
      JPanel middleJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
      middleJP.setPreferredSize(new Dimension(570,450));
      JScrollPane js = new JScrollPane(middleJP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      js.setPreferredSize(new Dimension(590,300));   
      ButtonGroup group = new ButtonGroup();// method for only click once of jradiobutton
      projectListButtonArr = new ArrayList<>();
      for(int i = 1; i < currentTitleArr.size(); i++){//add into button as a _list, get studentName and status from databash 
         
         String output = String.format("%-40s", currentTitleArr.get(i));
         
         if(!this.projectListButtonArr.contains(new JRadioButton(output))) {
            projectListButtonArr.add(new JRadioButton(output)); 
         }
      }
      for(int i = 0; i < projectListButtonArr.size(); i++){
      
         projectListButtonArr.get(i).setFont(new Font("Serif", Font.BOLD , 25));
         projectListButtonArr.get(i).setPreferredSize(new Dimension(570,30));
         group.add( projectListButtonArr.get(i));// method for only click once of jradiobutton
         middleJP.add( projectListButtonArr.get(i));
      }
      rightContain.add(js, BorderLayout.CENTER);  
       
      /*
      *There have some bottom in project list page which user can click.
      */
      deleteJB = new JButton("Delete");
      moreInfoJB = new JButton("More Information");
      EditJB = new JButton("Edit");
      addfacultyJB = new JButton("Add Faculty");
      changefacultyJB = new JButton("Change Faculty");
      
    
      JPanel southsouth = new JPanel();
   
      southsouth.add(deleteJB); 
      southsouth.add(EditJB);
      southsouth.add(moreInfoJB);   
      southsouth.add(addfacultyJB);
      southsouth.add(changefacultyJB);
      
      southsouth.setBorder(BorderFactory.createEmptyBorder(0, 80, 0,0));
     
      
      moreInfoJB.addActionListener(this);
      EditJB.addActionListener(this);
      deleteJB.addActionListener(this);
      addfacultyJB.addActionListener(this);
      changefacultyJB.addActionListener(this);
               
      rightContain.add(southsouth,BorderLayout.SOUTH);
   }
   ArrayList<JLabel> facultynameJL;
   ArrayList<String> facultyNameArr;
   /**
      *This method is for when user click more information bottom in the project list page
      *It will show up a page which have more detail about that project. 
   */
   public void moreInfo(){
   
      JPanel studentInfo = new JPanel(new GridLayout(7,1));
      studentInfo.setLayout(new BoxLayout(studentInfo, BoxLayout.Y_AXIS));
      studentInfo.setPreferredSize(new Dimension(570,450));
      
                              
      JLabel something0 = new JLabel("<html><p>"+ currentTitle+ "</p><html>");
      JLabel something1 = new JLabel("<html><p>Capstone Id: "+ currentCapstoneid + "</p><html>");
      JLabel something2 = new JLabel("<html><p>Grade: "+ currentGrade+ "</p><html>");
      JLabel something3 = new JLabel("<html><p>Date: "+ currentDate+ "</p><html>");
      JLabel something4 = new JLabel("<html><p>Status: "+ currentName+ "</p><html>");
      JLabel something5 = new JLabel("<html><p>DefenseDate: "+ currentDefensedate+ "</p><html>");
      
      JLabel something6 = new JLabel("<html><p>Project Describtion: "+ currentDesc+ "</p><html>");
      
      studentInfo.add(something0);   
      studentInfo.add(something1);
      studentInfo.add(something2);
      studentInfo.add(something3);
      studentInfo.add(something4);
      studentInfo.add(something5);
      studentInfo.add(something6);
      
      ArrayList<ArrayList<String>> facultyName = st. FacultyName(currentCapstoneid);
         
      facultyNameArr   = new ArrayList<String> ();
   
      for (int x=1; x<facultyName.size(); x++){
         for (int y=0; y<facultyName.get(x).size(); y++)
         {
            facultyNameArr.add(facultyName.get(x).get(y));   
            
            String input = "Facult#"+ (x) + ">>> " +facultyName.get(x).get(0);
            String output = input.substring(0, 1).toUpperCase() + input.substring(1);
            
            studentInfo.add(new JLabel(output));
            break;
         }
            
      }
      
      something0.setFont(new Font("Serif", Font.BOLD, 25));
      something1.setFont(new Font("Serif", Font.PLAIN , 17));
      something2.setFont(new Font("Serif", Font.PLAIN , 17));
      something3.setFont(new Font("Serif", Font.PLAIN , 17));
      something4.setFont(new Font("Serif", Font.PLAIN , 17));
      something5.setFont(new Font("Serif", Font.PLAIN , 17));
      something6.setFont(new Font("Serif", Font.PLAIN , 17));
      
      rightContain.add(studentInfo,BorderLayout.SOUTH);
   }
   /*
      *This method contain the arrayList
   */
   public void getCuurent(){
      currentCapstoneid    =currentCapstoneidArr .get(position);
      currentSid   		 =currentSidArr   .get(position);
      currentTitle  		=currentTitleArr .get(position);
      currentGrade  			=currentGradeArr .get(position);
      currentDate  			=currentDateArr  .get(position);
      currentName  			=currentNameArr  .get(position);
      currentDefensedate	=currentDefensedateArr.get(position);
      currentDesc      =currentDescArr.get(position);
      currentType = currentTypeArr.get(position);
   }
      
   @Override
  
   public void actionPerformed(ActionEvent e) {
    /**
      *Click Creat project bottm to jump to creat project page. 
   */
      if(e.getSource().equals(createProjectJB))
      {
         rightContain.removeAll();
         createProject();
         rightContain.validate();
         repaint();
      }
      
      /**
      *Jump to the page which show the all faculty.
      */
      if(e.getSource().equals(jb2))
      {
         rightContain.removeAll();
         tableView();
         rightContain.validate();
         repaint();
      }
      
      /**
      *Jump to faculty list where you click select faculty.  
      */
      if(e.getSource().equals(facultyJB))
      {
         
         rightContain.removeAll();
         projectTitle = titleJtf.getText();
         projectDesc = tArea.getText();
         facultyTitleType =  "You may select one or more faculty";
         facultyList();
         rightContain.validate();
         repaint();
      }
      /**
      *Jump to the new page which have more detail about the project.
      */
      
      if(e.getSource().equals(viewProjectJB))
      {
         rightContain.removeAll();
         projectList();
         rightContain.validate();
         repaint();
      }
      /**
      *Set defense date when user enter a date and click set date
      
      */
      if(e.getSource().equals(EnterJB))
      {
         st.setDefenseDate(DefenseDateJtf.getText(),DefenseIDJtf.getText());
      }
   
   
    /*
    *When user choose their faculty and click submit, it will apply faculty for user.
    */
      if(e.getSource().equals(submitFacultyJB))
      {
       
         for(int i = 0; i < myList.size(); i++){
                  
            if(myList.get(i).isSelected())
            {
               rightContain.removeAll();
               
               
               
               if(changeFaculty)
               {
                  facultyUsername = FacultyUsernameList.get(i);
               
                  st.ChangeFaculty(facultyUsername, currentCapstoneid,currentType);
               
                  projectList();
                  changeFaculty = false;
                  facultyUsername= null;  
                  facultySelected = null;
               }
               else if(forchangesFacutl)
               {
                  facultyUsername = FacultyUsernameList.get(i);
                  st.deleteFaculty(currentCapstoneid, oldFaculty, facultyUsername);
                  
                  projectList();
                  facultyUsername= null; 
                  forchangesFacutl = false;
                  facultySelected = null;
               
               }
               else{
                  facultyUsername = FacultyUsernameList.get(i);
                  facultySelected = "Faculty been select >>> " +FacultyNameList.get(i);
               
                  createProject();
               }
      
               rightContain.validate();
               repaint();
            }
         }
      
      }
      /*
      *After user choose their faculty and fill in the project information, it will creat the project.
      */
      if(e.getSource().equals(createJB))
      {
         if(!titleJtf.getText().equals("") && !tArea.getText().trim().equals(""))
         {
            if(facultyUsername != null)
            {
                
               st.createProject( projectTitle,  projectDesc,  ProjectTypeList.getSelectedItem().toString(),  facultyUsername);
               
               projectTitle = "";
               projectDesc = "";
               facultySelected = "";
               rightContain.removeAll();
            
               createProject();
               rightContain.validate();
               repaint();
            }
            else{
               System.out.println("no faculty ");
            }
         }
         else
         {
            System.out.println("Please enter project title, project Desc");
         }
         
      }
     /**
      *Jump to the new page which have more detail about the project when user click more information.
      */
      if(e.getSource().equals(moreInfoJB))
      {
        
         for(int i = 0; i < projectListButtonArr.size(); i++){
            
            if(projectListButtonArr.get(i).isSelected())
            {
               rightContain.removeAll();
               position =i+1;
               getCuurent();
               
               
               moreInfo();
               rightContain.validate();
               repaint();
            }
         }
      }
      /**
      *After user finish edit the describtion and click update, it will upload new describetion. 
      
      */
      if(e.getSource().equals(EditJB))
      {
        
         for(int i = 0; i < projectListButtonArr.size(); i++){
            
            if(projectListButtonArr.get(i).isSelected())
            {
               rightContain.removeAll();
               position =i+1;
               getCuurent();
               
               currentStatus();
               rightContain.validate();
               repaint();
            }
         }
      }
     /*
      *It will upload new defense date to database after user click set defense date bottom.
      */
      if(e.getSource().equals(setDateJB))
      {
         st.setDefenseDate(DefenseIDJtf.getText(), currentCapstoneid);
      }
      
     /*
     *It will upload the new description to database after user click update.
     */
      if(e.getSource().equals(updateJB))
      {
         st.StudentEdit(currentCapstoneid, jta.getText());
        // list = st.ViewCurrent();
      
      }
      /*
      * It will add faculty when user click add
      */
      if(e.getSource().equals(addfacultyJB))
      {
         
         for(int i = 0; i < projectListButtonArr.size(); i++){
            
            if(projectListButtonArr.get(i).isSelected())
            {
               rightContain.removeAll();
               position =i+1;
               changeFaculty = true;
               
               getCuurent();
               
               facultyTitleType = "Change faculty for " + currentTitle;
               
               facultyList();
              
               rightContain.validate();
               repaint();
            }
         }
      
      }
      /*
      * It will delete the project when user click delete
      */
      if(e.getSource().equals(deleteJB))
      {
         
         for(int i = 0; i < projectListButtonArr.size(); i++){
            
            if(projectListButtonArr.get(i).isSelected())
            {
               rightContain.removeAll();
               position =i+1;
               getCuurent();
               st.deleteProject(currentCapstoneid);
               projectList();               
               rightContain.validate();
               repaint();
            }
         }
      
      }
      /*
      It will change the faculty faculty when user click change.
      */
      if(e.getSource().equals(changefacultyJB))
      {
         
         for(int i = 0; i < projectListButtonArr.size(); i++){
            
            if(projectListButtonArr.get(i).isSelected())
            {
               rightContain.removeAll();
            
               position =i+1;
               
               getCuurent();
               changeTonewFaculty();
               forchangesFacutl =true;
               rightContain.validate();
               repaint();
            }
         }
      
      }
      /*
      It will change the faculty faculty when user click change.
      */

      if(e.getSource().equals(clickForChangeJB))
      {
         
         for(int i = 0; i < myList.size(); i++){
            
            if(myList.get(i).isSelected())
            {
               rightContain.removeAll();
            
            
               oldFaculty=oldFacultyUsername.get(i);  
               
               facultyList();
               forchangesFacutl =true;
               rightContain.validate();
               repaint();
            }
         }
      
      }
      /*
      *Cancel bottom will delete every thing user had edit when user create a new project.
      */
      if(e.getSource().equals(cancelJB))
      {
         
         rightContain.removeAll();
            
         createProject();
              
         rightContain.validate();
         repaint();
      
      }
      
      
      
      
   }

}