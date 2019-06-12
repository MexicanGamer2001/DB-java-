/**
 * File: StaffGUI.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 3 April, 2018
 *
 * Capstone model object
 */
package view;

import model.Faculty;
import model.MyDatabase;
import model.Staff;
import model.User;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class StaffGUI extends JFrame implements ActionListener{


   JButton login, exit;//The button for login, exit
   JLabel jlEmail, jlPass;//the Label for email and password
   JTextField jtfEmail;//the Text feild for email
   JPasswordField jtfPass;//password field to hide the password
   JCheckBox jcReme;//to remember the password
   //for mian page gui
   JPanel  north, northInfo;
   JLabel jlname,jlposition, jlOffice,jlemail, jlphone;
   JButton ViewStudentJB, plagiarismJB,ChangeStatusJB, CompleteJB, viewCompleteInfo, plagSubmit;
   JTextField projectTitle;
   JLabel jlAProject;
   JTextArea jtaProject;
   JComboBox plagStudents;
   JTextField PlagiarismJTF;
   JComboBox statusCombo;
   JComboBox setStatusCombo;


   JTextField tField;
   JPasswordField pwField;
   JTextArea tArea;
   JFormattedTextField formattedField;
   JPanel south;
   JPanel southPanel;
   JPanel rightContain;

   ButtonGroup group;
   ArrayList<String> studentHolder = new ArrayList<>();

   Staff user = null;
   MyDatabase db = null;


   ArrayList<JRadioButton> myList;
   
   //for speicfic name method
   String specificName       ;
   String specifiCapstoneid  ;
   String specificTitle      ;
   String specificGrade      ;
   String specificDate       ;
   String specificStatus     ;
   String specificDefenseDate;
   String studentHistoryTitle;
   String specificScore;
   Object selected ;
   String capstoneId;
   
   
   JButton updatestatusJB;
   
   boolean updatePremit = false;
   public StaffGUI(User s)  {
      db = new MyDatabase();
      this.user = new Staff(s, db);
      
      //Layout for BorderLayout North
      JPanel north = new JPanel();//for north Panel
      north.setPreferredSize(new Dimension(800,200));
     // north.setBackground(Color.red);
      
      JPanel northInfo = new JPanel();//the panel for information
   
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
      //add togeter
   
      
      northInfo.add(containInfo,BorderLayout.EAST);
      northInfo.setPreferredSize(new Dimension(800,200));
      containInfo.setPreferredSize(new Dimension(750,200));
      containInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
   
   
      north.add(northInfo);
      this.add(north,BorderLayout.NORTH);
      
   
   
      /*
       * JLabel ritJL with String RIT
       * set backgroud color of JLabel white
      */
      JPanel center = new JPanel();
      center.setBackground(Color.black);
      JLabel ritJL = new JLabel("R I T");
      ritJL.setForeground (Color.white);
      center.setPreferredSize(new Dimension(800,40));
      ritJL.setFont(new Font("Serif", Font.BOLD , 25));
      center.add(ritJL);
      
      /*
       * JButtons for the methods
       */  
      JPanel leftTopContain = new JPanel(new GridLayout(4,1,5,5)); 
      ViewStudentJB = new JButton("View Student");
      plagiarismJB = new JButton("Plagiarism");
      CompleteJB = new JButton("Completed Capstone");
      updatestatusJB = new JButton("Update Status");
      
      
      leftTopContain.add(ViewStudentJB);
      leftTopContain.add(plagiarismJB);
      leftTopContain.add(CompleteJB);
      leftTopContain.add(updatestatusJB);
             
      
      
   
      JPanel leftBotContain = new JPanel(new GridLayout(2,1,5,5));
   
      leftBotContain.setBorder(BorderFactory.createEmptyBorder(290, 0, 0, 0));
          
      ViewStudentJB.setPreferredSize(new Dimension(148, 30));
      plagiarismJB.setPreferredSize(new Dimension(148, 30));
      CompleteJB.setPreferredSize(new Dimension(148, 30));
      updatestatusJB.setPreferredSize(new Dimension(148, 30));
      
      southPanel = new JPanel();
      southPanel.setPreferredSize(new Dimension(800,530));
      JPanel leftContain = new JPanel();
      
      leftContain.add(leftTopContain);
      leftContain.add(leftBotContain);
      
      
      rightContain = new JPanel();
      leftContain.setPreferredSize(new Dimension(150,500));
      rightContain.setPreferredSize(new Dimension(600,500));
      rightContain.setBorder(BorderFactory.createLineBorder(Color.red, 3));
      
      
      southPanel.add(leftContain);
      southPanel.add(rightContain); 
   
      south = new JPanel();
      south.setPreferredSize(new Dimension(800,530));
    /*
       * set the style of GUI JFrame
       * set size 750,800
       * set visible = ture
       * set resizabel = false
       * set Title
       */
      this.add(center,BorderLayout.CENTER);
      this.add(southPanel,BorderLayout.SOUTH);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setSize(750,800);
      this.setResizable(false);
      this.setVisible(true);
      this.setTitle("Staff");
         /*
       * activity Jbuttons
      */
      ViewStudentJB.addActionListener(this);
      plagiarismJB.addActionListener(this);
      CompleteJB.addActionListener(this);
      updatestatusJB.addActionListener(this);
      
      
      
      allStory();
      pack();
   }

   String plagInfo;
   /*
    *to choice the project
    *to enter the plagiarism score for each project
    *@param
   */
   public void PlligiForStaff(){
   
      JPanel searchJP = new JPanel();//display the title of north JPanel of content area
      JLabel searchTitle = new JLabel("Plagiarism Score");//the page title name
      searchTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-location the project name 
      searchTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
      //make a line to split the project name and content
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      searchJP.setPreferredSize(new Dimension(590,79));
      searchJP.add(searchTitle, BorderLayout.NORTH);
      searchJP.add(aLine, BorderLayout.CENTER);
     
   
      //for contain JPanel of project name
      JPanel nameContain = new JPanel(new GridLayout(1,2,0,0));
     
      //2D arraylist to call the method from staff, StaffStudentList method
      ArrayList<ArrayList<String>> staffStudent = user.StaffStudentList();
     
      //JCombobox to display project names 
      plagStudents = new JComboBox();
           
      
      //arraylist to store the informations of project
      listUsernameArr = new ArrayList<String>();
      listFullnameArr = new ArrayList<String>();
      listCapstoneidArr  = new ArrayList<String>();
      listSidArr  = new ArrayList<String>();
      listTitleArr  = new ArrayList<String>();
      listGradeArr   = new ArrayList<String>();
      listDateArr   = new ArrayList<String>();
      listNameArr    = new ArrayList<String>();
      listDefenseDateArr    = new ArrayList<String>();
      listDescArr    = new ArrayList<String>();
      listScoreArr = new ArrayList<String>();
     
      
      //get the information from project
      if(staffStudent.size()>0)
      {
         for (int x=0; x<staffStudent.size(); x++){
            for (int y=0; y<staffStudent.get(x).size(); y++)
            {
               listUsernameArr   .add(staffStudent.get(x).get(0));
               listFullnameArr   .add(staffStudent.get(x).get(1));
               listCapstoneidArr .add(staffStudent.get(x).get(2));
               listSidArr        .add(staffStudent.get(x).get(3));
               listTitleArr      .add(staffStudent.get(x).get(4));
               listGradeArr      .add(staffStudent.get(x).get(5));
               listDateArr       .add(staffStudent.get(x).get(6));
               listNameArr       .add(staffStudent.get(x).get(7));
               listDefenseDateArr.add(staffStudent.get(x).get(8));
               listDescArr       .add(staffStudent.get(x).get(9));
               listScoreArr       .add(staffStudent.get(x).get(10));
                         
               break;     
            }                  
         }
      }
     
      //add project name into jComboBox
      if(staffStudent.size() > 1) {
         for(int i = 1; i < staffStudent.size(); i++) {
         
            plagStudents.insertItemAt(staffStudent.get(i).get(4), i -1);
         }
      }
      nameContain.add(plagStudents);
      
      searchJP.add(nameContain, BorderLayout.CENTER);
      
      rightContain.add(searchJP, BorderLayout.NORTH);      
   
      //JTextArea  to display description of project
      JTextArea tArea = new JTextArea(plagInfo);
      tArea.setEditable(false);//enable to edit
      tArea.setFont(new Font("Serif", Font.ITALIC, 19));
      tArea.setLineWrap(true);       // wrap line
      tArea.setWrapStyleWord(true);  // wrap line at word boundary
      tArea.setBackground(new Color(204, 238, 241)); // light blue color  
      
      //to contain of JTextarea tArea
      JScrollPane js = new JScrollPane(tArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);                                  
      js.setPreferredSize(new Dimension(590,300));//set the size of js JSrollPane
      rightContain.add(js, BorderLayout.CENTER);//add into border layout center of content area
     
      //JPanel southContain to for contain JPanel and JLable
      JPanel southContain = new JPanel(new GridLayout(1,2,100,0));
      JPanel PlagiarismJP = new JPanel();//for contain the Jlabel and JPanel
      JLabel PlagiarismJL = new JLabel("Plagiarism Score:");
      PlagiarismJP.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));
      PlagiarismJTF = new JTextField(3);//enter the score
      PlagiarismJP.add(PlagiarismJL);//add into JPanel
      PlagiarismJP.add(PlagiarismJTF);//add into JPanel
      PlagiarismJL.setFont(new Font("Serif", Font.BOLD , 25));
      PlagiarismJP.setPreferredSize(new Dimension(590,50));
      plagSubmit = new JButton();
      plagSubmit.setText("Submit");//for submit button
      plagSubmit.addActionListener(this);
      PlagiarismJP.add(plagSubmit);//add into JPanel
      southContain.add(PlagiarismJP);
   
      plagStudents.setSelectedItem(selected);
   
      plagStudents.addActionListener(//the actionLiser
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               plagStudents = (JComboBox) e.getSource();
               selected = plagStudents.getSelectedItem();
                       
               String command = e.getActionCommand();//to get what value
               int index = plagStudents.getSelectedIndex();//the get the position
               plagInfo =  listDescArr.get(index+1);//to get inforamtion of project information
               capstoneId = listCapstoneidArr.get(index+1);// the gte id of project
               
               
               plagStudents.setSelectedItem(selected);//to set the select item
               //re-display the page
               rightContain.removeAll();
               PlligiForStaff();
               rightContain.validate();
               repaint();
            }
         });       
      rightContain.add(southContain,BorderLayout.SOUTH);
   }
 
   JComboBox statusnameJC;
   ArrayList<String> StatusIdArr   ;
   ArrayList<String> StatusNameArr ; 
   ArrayList<String> StatusDescArr ;
   
   String statusSid;
   JTextField statusJTF;
   JButton statusSubmit;
   String UpdateCapstoneID ;
   /*
    *change the current status of project
    *@param
   */
   public void ChangeStatus(){
   
      JPanel searchJP = new JPanel();//display the title of north JPanel of content area
      JLabel searchTitle = new JLabel("Change Status");//the page title name
      searchTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-location the project name
      searchTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
      //make a line to split the project name and content
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      searchJP.setPreferredSize(new Dimension(590,79));
      
      searchJP.add(searchTitle, BorderLayout.NORTH);
      searchJP.add(aLine, BorderLayout.CENTER);
     
      //for contain JPanel of project name
      JPanel nameContain = new JPanel(new GridLayout(1,2,0,0));
     
      JPanel nameJP = new JPanel();
      ArrayList<String> studentArr = new ArrayList<>();
   
     //2D arraylist to call the method from staff, StaffStudentList method
      ArrayList<ArrayList<String>> list = user.statusList();
      
      //JCombobox to display status list
      statusnameJC = new JComboBox();
      
      //to store the status informaitons
      StatusIdArr   = new ArrayList<String>();
      StatusNameArr = new ArrayList<String>(); 
      StatusDescArr = new ArrayList<String>();
      
      //get the information from status list
      if(list.size()>0)
      {
         for (int x=0; x<list.size(); x++){
            for (int y=0; y<list.get(x).size(); y++)
            {
               StatusIdArr  .add(list.get(x).get(0));
               StatusNameArr.add(list.get(x).get(1));
               StatusDescArr.add(list.get(x).get(3));
               break;
               
            }                  
         }
      }
   
      //set the get itme
      if(list.size() > 1) {
         for(int i = 1; i < list.size(); i++) {
         
            statusnameJC.insertItemAt(list.get(i).get(1), i -1);
         }
      }
      
      nameContain.add(statusnameJC);
      
      searchJP.add(nameContain, BorderLayout.CENTER);
      
      rightContain.add(searchJP, BorderLayout.NORTH);      
   
      //JTextArea  to display stutus been selected
      JTextArea tArea = new JTextArea(plagInfo);
      tArea.setEditable(false);//enable to edit
      tArea.setFont(new Font("Serif", Font.ITALIC, 19));
      tArea.setLineWrap(true);       // wrap line
      tArea.setWrapStyleWord(true);  // wrap line at word boundary
      tArea.setBackground(new Color(204, 238, 241)); // light blue color
      
      //to contain of JTextarea tArea
      JScrollPane js = new JScrollPane(tArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);                                   
      js.setPreferredSize(new Dimension(590,300));//set the size of js JSrollPane
      rightContain.add(js, BorderLayout.CENTER);//add into boreder layout  of content area JPanel, center  
     
      
      JPanel southContain = new JPanel(new GridLayout(1,1,100,0));// for store summbit button
      JPanel statusJP = new JPanel();
     
      statusJP.setBorder(BorderFactory.createEmptyBorder(-5, 500, 0, 0));//re-location the button
      
      statusJTF = new JTextField(3);//set the size if JTextField
    
      statusJP.setPreferredSize(new Dimension(590,50));//
      statusSubmit = new JButton();
      statusSubmit.setText("Submit");
      statusSubmit.addActionListener(this);
      statusJP.add(statusSubmit);
      southContain.add(statusJP);
   
      statusnameJC.setSelectedItem(selected);
   
      statusnameJC.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {//for the mouse event
               statusnameJC = (JComboBox) e.getSource();
               selected = statusnameJC.getSelectedItem();
                       
               String command = e.getActionCommand();//get the command
               
               int index = statusnameJC.getSelectedIndex();//get the click position
               plagInfo =  StatusDescArr.get(index+1);//get the description from descrprion arraylist
               statusSid =  StatusIdArr.get(index+1);//get the sid from sid arraylist
               
               statusnameJC.setSelectedItem(selected);//sge the select  item
               //repaint the page
               rightContain.removeAll();
               ChangeStatus();
               rightContain.validate();
               repaint();
            }
         });      
   
   
      rightContain.add(southContain,BorderLayout.SOUTH);
   }
   
   JButton updateSubmit;
   JTextArea statusArea;
   JTextField statusNameJtf ;
   String statusName;
   /*
    *edit or change the status of status list
    *@param
   */
   public void EditStatus(){
   
      JPanel searchJP = new JPanel();//display the title of north JPanel of content area
      JLabel searchTitle = new JLabel("Edit Status");//the page title name
      searchTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-location the project name
      searchTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
      //make a line to split the project name and content
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      searchJP.setPreferredSize(new Dimension(590,85));
      
      searchJP.add(searchTitle, BorderLayout.NORTH);
      searchJP.add(aLine, BorderLayout.CENTER);
     
        
      JPanel nameJP = new JPanel();
      ArrayList<String> studentArr = new ArrayList<>();
   
      ArrayList<ArrayList<String>> list = user.statusList();
     
      statusnameJC = new JComboBox();
      StatusIdArr   = new ArrayList<String>();
      StatusNameArr = new ArrayList<String>(); 
      StatusDescArr = new ArrayList<String>();
      //to get the information and add into arraylist
      if(list.size()>0)
      {
         for (int x=0; x<list.size(); x++){
            for (int y=0; y<list.get(x).size(); y++)
            {
            
               StatusIdArr  .add(list.get(x).get(0));
               StatusNameArr.add(list.get(x).get(1));
               StatusDescArr.add(list.get(x).get(3));
               break;
               
            }                  
         }
      }
    
      //to add into JcomboBox    
      if(list.size() > 1) {
         for(int i = 1; i < list.size(); i++) {
         
            statusnameJC.insertItemAt(list.get(i).get(1), i -1);
         }
      }
      statusnameJC .setPreferredSize(new Dimension(300,25));
      //set the size
      JPanel someNameContain = new JPanel();
      someNameContain.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 190));
   
      JPanel nameContain = new JPanel();
      //the label for selct proposal status name
      JLabel nameJL = new JLabel("Select Proposal Status: ");
      someNameContain.add(nameContain);
      
      nameJL.setFont(new Font("Serif", Font.PLAIN , 21));
      nameContain.add(nameJL,FlowLayout.LEFT);
      nameContain.add(statusnameJC);
      
      searchJP.add(someNameContain, BorderLayout.CENTER);
      
      rightContain.add(searchJP, BorderLayout.NORTH);      
   
      /////////////////////////////////for title
      JPanel centerContainJP = new JPanel();
      JPanel jlContainJP = new JPanel();
      jlContainJP.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 240));
   
      JLabel statusNameJL = new JLabel("Proposal Name: ");
      statusNameJtf = new JTextField();
      statusNameJtf.setText(statusName);
      statusNameJL.setFont(new Font("Serif", Font.PLAIN , 21));
      statusNameJtf.setPreferredSize(new Dimension(200,25));
   
      jlContainJP.add(statusNameJL,FlowLayout.LEFT);
      jlContainJP.add(statusNameJtf);
      
      centerContainJP.add(jlContainJP);
      
      statusArea = new JTextArea();
    
      statusArea.setText(plagInfo);
      statusArea.setFont(new Font("Serif", Font.ITALIC, 19));
      statusArea.setLineWrap(true);       // wrap line
      statusArea.setWrapStyleWord(true);  // wrap line at word boundary
      statusArea.setBackground(new Color(204, 238, 241));   
      
      //JTextArea  to display status 
      JScrollPane js = new JScrollPane(statusArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      //set the size for JscrollPane and JtextArea
      js.setPreferredSize(new Dimension(590,220));
      centerContainJP.setPreferredSize(new Dimension(590,270));
      centerContainJP.add(js);
      
      rightContain.add(centerContainJP, BorderLayout.CENTER);//add into content
      
      JPanel southContain = new JPanel(new GridLayout(1,1,100,0));//container for content area border layout south
      JPanel statusJP = new JPanel();
     
      statusJP.setBorder(BorderFactory.createEmptyBorder(-5, 500, 0, 0));
      
      statusJTF = new JTextField(3);
      statusJTF.setText(statusName);
      
      statusJP.setPreferredSize(new Dimension(590,50));
      updateSubmit = new JButton();//up sumit the update informations
      updateSubmit.setText("Update");
      updateSubmit.addActionListener(this);
      statusJP.add(updateSubmit);
      southContain.add(statusJP);
   
      statusnameJC.setSelectedItem(selected);//set the  select item
   
      statusnameJC.addActionListener(    
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  statusnameJC = (JComboBox) e.getSource();
                  selected = statusnameJC.getSelectedItem();
                          
                  String command = e.getActionCommand();
                  
                  int index = statusnameJC.getSelectedIndex();//get current position been select
                  plagInfo =  StatusDescArr.get(index+1);//get selected the description
                  statusSid =  StatusIdArr.get(index+1);//get selected the sid
                  statusName =  StatusNameArr.get(index+1);//get selected status name
                  
                  
                  statusnameJC.setSelectedItem(selected);
               
                  //repaint the content page
                  rightContain.removeAll();
                  EditStatus();
                  rightContain.validate();
                  repaint();
               }
            });      
   
   
      rightContain.add(southContain,BorderLayout.SOUTH);
   }

   
   /*
    *specificInfo to display speific informaiton of project
    *@param
   */
   public void specificInfo(){
   
      JPanel studentInfo = new JPanel(new GridLayout(7,1));//to contain the information need to display
      studentInfo.setLayout(new BoxLayout(studentInfo, BoxLayout.Y_AXIS));
      studentInfo.setPreferredSize(new Dimension(570,450));
      //print informaton in JtextArea
      JTextArea jta = new JTextArea();
         
      jta.setPreferredSize(new Dimension(560,450));
      jta.setBackground(Color.green);
      //JLabels to display the information of project                        
      JLabel something0 = new JLabel("<html><p>"+ specificName+ "</p><html>");
      JLabel something1 = new JLabel("<html><p>Capstone Id: "+ specifiCapstoneid + "</p><html>");
      JLabel something2 = new JLabel("<html><p>Title: "+ specificTitle+ "</p><html>");
      JLabel something3 = new JLabel("<html><p>Grade: "+ specificGrade+ "</p><html>");
      JLabel something4 = new JLabel("<html><p>Status: "+ specificStatus+ "</p><html>");
      JLabel something6 = new JLabel("<html><p>Date: "+ specificDate+ "</p><html>");
      JLabel something5 = new JLabel("<html><p>DefenseDate: "+ specificDefenseDate+ "</p><html>");
      JLabel something7 = new JLabel("<html><p>Plagerism Score: "+ specificScore+ "</p><html>");
      
      //add JLabels into JPanel container
      studentInfo.add(something0);   
      studentInfo.add(something1);
      studentInfo.add(something2);
      studentInfo.add(something3);
      studentInfo.add(something4);
      studentInfo.add(something6);
      studentInfo.add(something5);
      studentInfo.add(something7);
      
      
      //set font size and style
      something0.setFont(new Font("Serif", Font.BOLD, 25));
      something1.setFont(new Font("Serif", Font.ITALIC , 17));
      something2.setFont(new Font("Serif", Font.ITALIC , 17));
      something3.setFont(new Font("Serif", Font.ITALIC , 17));
      something4.setFont(new Font("Serif", Font.ITALIC , 17));
      something5.setFont(new Font("Serif", Font.ITALIC , 17));
      something6.setFont(new Font("Serif", Font.ITALIC , 17));
      something7.setFont(new Font("Serif", Font.ITALIC , 17));
       
      rightContain.add(studentInfo,BorderLayout.SOUTH);//add content area border layout south
   }
   
   ArrayList<String> listUsernameArr ;
   ArrayList<String> listFullnameArr;
   ArrayList<String> listCapstoneidArr ;
   ArrayList<String> listSidArr ;
   ArrayList<String> listTitleArr;
   ArrayList<String> listGradeArr;
   ArrayList<String> listDateArr;
   ArrayList<String> listNameArr;
   ArrayList<String> listDefenseDateArr;
   ArrayList<String> listDescArr;
   ArrayList<String> listScoreArr;
   
   JButton StudentHisJB;
   JButton specInfoJB;
   ArrayList<JRadioButton> StudentListButonnArr;
   /*
    *home page
    *display projects
    *display buttons choice
    *@param
   */
   public void allStory(){
   
   
      JPanel trackJP = new JPanel();//display the title of north JPanel of content area
      JLabel trackTitle = new JLabel("Student List");//the page title
      trackTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-location the project name
      trackTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
       //make a line to split the project name and content
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      trackJP.setPreferredSize(new Dimension(590,44));
      trackJP.add(trackTitle, BorderLayout.NORTH);
      trackJP.add(aLine, BorderLayout.CENTER);
      rightContain.add(trackJP, BorderLayout.NORTH);  
      
   
      //2D arraylist to call the method from staff, StaffStudentList method
      ArrayList<ArrayList<String>> list = user.StaffStudentList();
      //arraylist to store the informations of project
      listUsernameArr = new ArrayList<String>();
      listFullnameArr = new ArrayList<String>();
      listCapstoneidArr  = new ArrayList<String>();
      listSidArr  = new ArrayList<String>();
      listTitleArr  = new ArrayList<String>();
      listGradeArr   = new ArrayList<String>();
      listDateArr   = new ArrayList<String>();
      listNameArr    = new ArrayList<String>();
      listDefenseDateArr    = new ArrayList<String>();
      listDescArr    = new ArrayList<String>();
      listScoreArr    =new ArrayList<String>();
      
      //get the information from project
      if(list.size()>0)
      {
         for (int x=0; x<list.size(); x++){
            for (int y=0; y<list.get(x).size(); y++)
            {
               listUsernameArr   .add(list.get(x).get(0));
               listFullnameArr   .add(list.get(x).get(1));
               listCapstoneidArr .add(list.get(x).get(2));
               listSidArr        .add(list.get(x).get(3));
               listTitleArr      .add(list.get(x).get(4));
               listGradeArr      .add(list.get(x).get(5));
               listDateArr       .add(list.get(x).get(6));
               listNameArr       .add(list.get(x).get(7));
               listDefenseDateArr.add(list.get(x).get(8));
               listDescArr       .add(list.get(x).get(9));
               listScoreArr       .add(list.get(x).get(10));
               
               break;     
            }                  
         }
      }
     
      /*
      *display student who are doing the project
      *display student name
      */
      JPanel middleJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
      middleJP.setPreferredSize(new Dimension(570,450));
      JScrollPane js = new JScrollPane(middleJP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      js.setPreferredSize(new Dimension(590,300));
      //buttonGroup for only choice one student at once   
      ButtonGroup group = new ButtonGroup();
      //button arraylis store button
      StudentListButonnArr = new ArrayList<>();
      
      for(int i = 1; i < listFullnameArr.size(); i++){
         
         String output = String.format(listFullnameArr.get(i));
         if(!this.StudentListButonnArr.contains(new JRadioButton(output))) {
            StudentListButonnArr.add(new JRadioButton(output));//add student name into button 
         }
      }
      
      //set style of button
      for(int i = 0; i < StudentListButonnArr.size(); i++){
      
         StudentListButonnArr.get(i).setFont(new Font("Serif", Font.BOLD , 25));
         StudentListButonnArr.get(i).setPreferredSize(new Dimension(450,45));
      
         group.add(StudentListButonnArr.get(i));// method for only click once of jradiobutton
         middleJP.add(StudentListButonnArr.get(i));
      }
      rightContain.add(js, BorderLayout.CENTER);  
          
      //the buttons for specific information, view all story and change status
      specInfoJB = new JButton("Specific Information");
      StudentHisJB = new JButton("All History");
      ChangeStatusJB = new JButton("Change Status");
      
      JPanel southsouth = new JPanel(new GridLayout(1,3,10,10));
      southsouth .setBorder(BorderFactory.createEmptyBorder(0,110, 0,0));
      //add into JPanel southsouth container
      southsouth.add(StudentHisJB);
      southsouth.add(specInfoJB);
      southsouth.add(ChangeStatusJB);
      
      //activities jbutton
      StudentHisJB.addActionListener(this);
      specInfoJB.addActionListener(this);
      ChangeStatusJB.addActionListener(this);
      
      
         
      rightContain.add(southsouth,BorderLayout.SOUTH);//add into content area JPanel south
   }
   String studentHistoryUsername;
   String studentHistoryCapstoneId;
   /*
    *view all history of this project
    *view all faculty with this committees
   */
   public void StudentHis(){
   
      JPanel studentInfo = new JPanel(new GridLayout(7,1));//contain for display the information of project
      studentInfo.setLayout(new BoxLayout(studentInfo, BoxLayout.Y_AXIS));
      studentInfo.setPreferredSize(new Dimension(570,450));
      
      JTextArea jta = new JTextArea();
         
      jta.setPreferredSize(new Dimension(560,450));//set the size
      jta.setBackground(Color.green);//set tbe backgrou color
         
   
      ArrayList<JLabel> jlArr = new ArrayList<>();//to store the jLabels
      ArrayList<ArrayList<String>> list = user.ViewAllHistory(studentHistoryUsername);
      
      ArrayList<ArrayList<String>> nameList = user.FacultyName(studentHistoryCapstoneId);//get all faculty, call method form Staff
      
      jlArr.add(new JLabel("Faculty in this capstone"));
      //get faculty name from committes
      if(list.size()>0)
      {
         for (int x=1; x<nameList.size(); x++){
            for (int y=0; y<nameList.get(x).size(); y++)
            {
               String input = "Facult#"+ (x) + ">>> " +nameList.get(x).get(y);
               String output = input.substring(0, 1).toUpperCase() + input.substring(1);
               jlArr.add(new JLabel(output));
                    
            }
         
         }
      }
      jlArr.add(new JLabel(" "));     
      //to get the all story of project                
      if(list.size()>0)
      {
         for (int x=1; x<list.size(); x++){
            for (int y=2; y<list.get(x).size(); y++)
            {
               String input = list.get(0).get(y)+" :"+list.get(x).get(y);
               String output = input.substring(0, 1).toUpperCase() + input.substring(1);
               jlArr.add(new JLabel(output));
                    
            }
            jlArr.add(new JLabel(" "));                     
         
         } 
      }
      
      for (int i = 0; i < jlArr.size(); i++)
      {
         studentInfo.add(jlArr.get(i));      
      }     
      rightContain.add(studentInfo,BorderLayout.SOUTH);//add into arraylist
      
   }
   /*
    *to show complete capstones list
    *@param
   */
   public void completeCap(){
      JPanel searchJP = new JPanel();
      JLabel searchTitle = new JLabel("Complete Capstones List");//display title of the page
      searchTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//
      searchTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      searchJP.setPreferredSize(new Dimension(590,44));
      
      searchJP.add(searchTitle, BorderLayout.NORTH);
      searchJP.add(aLine, BorderLayout.CENTER);
     
   
      rightContain.add(searchJP, BorderLayout.NORTH);      
   
     //for title
   
      JPanel middleJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
      middleJP.setPreferredSize(new Dimension(570,450));
      JScrollPane js = new JScrollPane(middleJP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                         
      js.setPreferredSize(new Dimension(590,300));   
      
      StudentListButonnArr = new ArrayList<>();
   
      group = new ButtonGroup();// method for only click once of jradiobutton
      myList = new ArrayList<>();
      
      //2D arraylist to call the method from staff, StaffStudentList method
      ArrayList<ArrayList<String>> list = user.ViewCompleteCapstone();
      //arraylist to store the information fo complete coapstone
      listUsernameArr = new ArrayList<String>();
      listFullnameArr = new ArrayList<String>();
      listCapstoneidArr  = new ArrayList<String>();
      listSidArr  = new ArrayList<String>();
      listTitleArr  = new ArrayList<String>();
      listGradeArr   = new ArrayList<String>();
      listDateArr   = new ArrayList<String>();
      listNameArr    = new ArrayList<String>();
      listDefenseDateArr    = new ArrayList<String>();
      listDescArr    = new ArrayList<String>();
      listScoreArr   = new ArrayList<String>();
      //get and add the value of capstone into arraylist
      if(list.size()>0)
      {
         for (int x=0; x<list.size(); x++){
            for (int y=0; y<list.get(x).size(); y++)
            {
               listUsernameArr   .add(list.get(x).get(0));
               listFullnameArr   .add(list.get(x).get(1));
               listCapstoneidArr .add(list.get(x).get(2));
               listSidArr        .add(list.get(x).get(3));
               listTitleArr      .add(list.get(x).get(4));
               listGradeArr      .add(list.get(x).get(5));
               listDateArr       .add(list.get(x).get(6));
               listNameArr       .add(list.get(x).get(7));
               listDefenseDateArr.add(list.get(x).get(8));
               listDescArr       .add(list.get(x).get(9));
               listScoreArr       .add(list.get(x).get(10));
            
               break;     
            }                  
         }
      }
      //add button display in the page
      for(int i = 1; i < listFullnameArr.size(); i++){
         
         String output = String.format(listFullnameArr.get(i));
         if(!this.StudentListButonnArr.contains(new JRadioButton(output))) {
            StudentListButonnArr.add(new JRadioButton(output)); 
         }
      }
      //style Jradios buttons
      for(int i = 0; i < StudentListButonnArr.size(); i++){
      
         StudentListButonnArr.get(i).setPreferredSize(new Dimension(400,45));
         StudentListButonnArr.get(i).setFont(new Font("Serif", Font.BOLD , 25));
         group.add( StudentListButonnArr.get(i));
         middleJP.add( StudentListButonnArr.get(i));
      }
      rightContain.add(js, BorderLayout.CENTER);//add into content area borderlayout center
      
      viewCompleteInfo = new JButton("Specific Information");//to show the complete informaiton
      viewCompleteInfo.addActionListener(this);//activity of button
      
      JPanel southsouth = new JPanel(new GridLayout(1,2,10,10));
      southsouth .setBorder(BorderFactory.createEmptyBorder(0, 440, 0,0));
      southsouth.add(viewCompleteInfo);
    
   
      rightContain.add(southsouth,BorderLayout.SOUTH);//add into content area borderlayout south
   }

 
   @Override
   public void actionPerformed(ActionEvent e) {
      //return to studentList page
      if(e.getSource().equals(ViewStudentJB))
      {
         rightContain.removeAll();
         allStory();
         rightContain.validate();
         repaint();
      }
      //return to enter plagiarism score page
      if(e.getSource().equals(plagiarismJB))
      {
         rightContain.removeAll();
         PlligiForStaff();
         rightContain.validate();
         repaint();
      }
      //return to change change status page when click  "Change Status" button
      if(e.getSource().equals(ChangeStatusJB))
      {
         for(int i = 0; i < StudentListButonnArr.size(); i++){
            
            if(StudentListButonnArr.get(i).isSelected())
            {
               rightContain.removeAll();
               
               UpdateCapstoneID = listCapstoneidArr.get(i+1);
               ChangeStatus();
               rightContain.validate();
               repaint();
            }
         }
      }
      //return to completed capstone page
      if(e.getSource().equals(CompleteJB))
      {
         rightContain.removeAll();
         
         completeCap();
      
         rightContain.validate();
         repaint();
      }
     // submit the  plagiarism score
      if(e.getSource().equals(plagSubmit)) {
         user.SetScore(PlagiarismJTF.getText(), capstoneId); 
         JOptionPane.showMessageDialog(null,"Sumbit for plagiarism score");
      
      }
      //submit the status score 
      if(e.getSource().equals(statusSubmit)) {
         user.ChangeStatus( UpdateCapstoneID,  statusSid);
         JOptionPane.showMessageDialog(null,"Sumbit for new status");
      
      }
      //return to specific information page
      if(e.getSource().equals(specInfoJB))
      {
      
         for(int i = 0; i < StudentListButonnArr.size(); i++){
            
            if(StudentListButonnArr.get(i).isSelected())
            {
               rightContain.removeAll();
               
               specificName       =listFullnameArr   .get(i+1);
               specifiCapstoneid  =listCapstoneidArr .get(i+1);
               specificTitle      =listTitleArr      .get(i+1);
               specificGrade      =listGradeArr      .get(i+1);
               specificDate       =listDateArr       .get(i+1);
               specificStatus     =listNameArr       .get(i+1);
               specificDefenseDate=listDefenseDateArr.get(i+1);
               specificScore= listScoreArr.get(i+1);
            
               
               specificInfo();
            
               rightContain.validate();
               repaint();
            }
         }
      } 
      //reuturn to student history page
      if(e.getSource().equals(StudentHisJB))
      {
        
         
         for(int i = 0; i < StudentListButonnArr.size(); i++){
            
            if(StudentListButonnArr.get(i).isSelected())
            {
               rightContain.removeAll();
               
               studentHistoryUsername = listUsernameArr.get(i+1);
               studentHistoryCapstoneId = listCapstoneidArr.get(i+1);
            
               
               StudentHis();
               rightContain.validate();
               repaint();
            }
         }
      }
      //sumbit the update history button
      if(e.getSource().equals(updateSubmit))
      {        
         user.UpdateProposal(statusSid, statusNameJtf.getText(),statusArea.getText());
         JOptionPane.showMessageDialog(null,"Updated student history");
      
      }
   
      //sumbit the update status
      if(e.getSource().equals(updatestatusJB))
      {
         rightContain.removeAll();
         EditStatus();
         rightContain.validate();
         repaint();
      }
      //view complete infotmation when click the button  
      if(e.getSource().equals(viewCompleteInfo))
      {
         for(int i = 0; i < StudentListButonnArr.size(); i++){
            
            if(StudentListButonnArr.get(i).isSelected())
            {
               rightContain.removeAll();
               
               specificName       =listFullnameArr   .get(i+1);
               specifiCapstoneid  =listCapstoneidArr .get(i+1);
               specificTitle      =listTitleArr      .get(i+1);
               specificGrade      =listGradeArr      .get(i+1);
               specificDate       =listDateArr       .get(i+1);
               specificStatus     =listNameArr       .get(i+1);
               specificDefenseDate=listDefenseDateArr.get(i+1);
               specificScore=listScoreArr.get(i+1);
               specificInfo();
               rightContain.validate();
               repaint();
            }
         }
      } 
   }
}
