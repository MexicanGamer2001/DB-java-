/**
 * File: FacultyGUI.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 3 April, 2018
 *
 * Capstone model object
 */
 
package view;

import model.*;
import model.User;


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;


public class FacultyGUI extends JFrame implements ActionListener{

   JButton login, exit;//The button for login, exit
   JLabel jlEmail, jlPass;//the Label for email and password
   JTextField jtfEmail;//the Text feild for email
   JPasswordField jtfPass;//password field to hide the password
   JCheckBox jcReme;//to remember the password


   JPanel northPc, north, northInfo;
   JLabel pic;
   JLabel jlname,jlposition, jlOffice,jlemail, jlphone;
   JButton TrackStudentListJB,studentListJB,ApplicationListJB, EnterGradeJB,jb5;
   JTextField projectTitle;
   JLabel jlAProject;
   JTextArea jtaProject;


   JTextField tField;
   JPasswordField pwField;
   JTextArea tArea;
   JFormattedTextField formattedField;
   JPanel south;
   JPanel southPanel;
   JPanel rightContain;
   
   
   
   ///studentList para
   JButton specInfoJB;
   ArrayList<JRadioButton> StudentListButonnArr;
   ArrayList<String> fullnameArr; 
   ArrayList<String> capstoneidArr; 
   ArrayList<String> titleArr; 
   ArrayList<String> gradeArr; 
   ArrayList<String> dateArr;
   ArrayList<String> statusArr;
   ArrayList<String> defensedatearr;
   ///studentList para end
   
   String studentHisName;
   String Applydesc;
   String ApplyId;
   String enterGradeTitle;
   String studentHistoryTitle;
   String studentHistortyDesc;
   String StudentCaptonesID;
   
   User sl;
   MyDatabase fdb =  new MyDatabase();
   Faculty st;
   
   
   JButton updateApproveStatusJB;
   JButton changePasswordJB;
   boolean updatePremit = false;
   public FacultyGUI(User s)  {
      sl = s; 
      st = new Faculty(sl,fdb);
      
      //Layout for BorderLayout North
      JPanel north = new JPanel();//for north Panel
      north.setPreferredSize(new Dimension(800,230));//to set set of JFrame North of boder layout
      JPanel northInfo = new JPanel();//the panel for information
   
     
      /*
       *
       *set the font size and font style for personal inforamtion  
       *
       */
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
      northInfo.add(containInfo,BorderLayout.EAST);   
      northInfo.setPreferredSize(new Dimension(800,200));
      containInfo.setPreferredSize(new Dimension(750,200));
      containInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
   
   
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
      ApplicationListJB = new JButton("Applications");
      TrackStudentListJB = new JButton("Track Student");
      studentListJB = new JButton("Student List");
      updateApproveStatusJB = new JButton("Approve");
         
           
      /*
       * set same size of JButtons
       */
      ApplicationListJB.setPreferredSize(new Dimension(148, 30));
      TrackStudentListJB.setPreferredSize(new Dimension(148, 30));
      studentListJB.setPreferredSize(new Dimension(148, 30));
      
      
      
      /*
       *display main page 
      */
      southPanel = new JPanel();
      southPanel.setPreferredSize(new Dimension(800,530));
      JPanel leftContain = new JPanel();
      rightContain = new JPanel();
      leftContain.setPreferredSize(new Dimension(150,500));
      rightContain.setPreferredSize(new Dimension(600,500));
      rightContain.setBorder(BorderFactory.createLineBorder(Color.red, 3));
      southPanel.add(leftContain);
      southPanel.add(rightContain);
   
   
      //for style the buttons
      JPanel leftTopContain = new JPanel(new GridLayout(4,1,5,5));
      leftTopContain.add(studentListJB);
      leftTopContain.add(TrackStudentListJB);
      leftTopContain.add(ApplicationListJB);
      leftTopContain.add(updateApproveStatusJB);
      leftContain.add(leftTopContain);
      
      south = new JPanel();
      south.setPreferredSize(new Dimension(800,530));
      north.add(northInfo,BorderLayout.CENTER);
   
      this.add(north,BorderLayout.NORTH);
      this.add(center,BorderLayout.CENTER);
      this.add(southPanel,BorderLayout.SOUTH);
      
      /*
       * set the style of GUI JFrame
       * set size 750,800
       * set visible = ture
       * set resizabel = false
       * set Title
       */
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setSize(750,800);
      this.setResizable(false);
      this.setVisible(true);
      this.setTitle("Faculty");
      /*
       * activity Jbuttons
      */
      ApplicationListJB.addActionListener(this);
      TrackStudentListJB.addActionListener(this);
      studentListJB.addActionListener(this);
      updateApproveStatusJB.addActionListener(this);
   
      studentList();
   
      if(s.getUsername().equals("qyuvks") )
      {
         updateApproveStatusJB.setEnabled(true);
        
      }
      else{
         updateApproveStatusJB.setEnabled(false);
      
      
      }
      
      pack();
      if(st.ApplyList().size()> 1)
      { 
         JOptionPane.showMessageDialog(null,"You have new apply in Applications.");
      }
     
     
   }
   JButton jump1, jump2;
   JPanel south_north;
   JPanel southsouth;
   JPanel cotain;
   JTextField tilteJtf;
   JPanel contain;
   JButton createJb;
   
   JLabel AppNameJL;
   
   JButton ApproveStatusJB;
   JButton denyStatusJB;
   String approveCapstoneID;
   String approveFullname  ;
   String approveTitle     ;
   String approveName      ;
   String approveSid;


   /*
    *to display the Approve from who need to approved
    *select Approve or Deny
    *the method using to create and active create project 
    *@ param
   */
   public void SelectApprove(){
      JPanel NorthContain = new JPanel(new FlowLayout(FlowLayout.LEFT));//display the title of north JPanel of content area
      JLabel northTitle = new JLabel(approveTitle);//the capstone name
      northTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,450));//re-location the project name
      northTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-location the project name 
      northTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
      //make a line to split the project name and content
      JPanel aLine = new JPanel(); 
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      NorthContain.setPreferredSize(new Dimension(595,77));
      NorthContain.add(northTitle, BorderLayout.NORTH);
      NorthContain.add(aLine, BorderLayout.CENTER);
      
     
      //to display the name of student who work in this capstone 
      AppNameJL = new JLabel(approveFullname);
      AppNameJL.setLayout(new FlowLayout(FlowLayout.LEFT));
      NorthContain.add(AppNameJL);
      AppNameJL.setFont(new Font("Serif", Font.ITALIC , 23));//set font
      
   
      rightContain.add(NorthContain, BorderLayout.NORTH);      
   
      //to display status of project
      JTextArea tArea = new JTextArea(approveName);
      tArea.setEditable(false);//set enable to edit
      tArea.setFont(new Font("Serif", Font.ITALIC, 19));//set size and font
      tArea.setLineWrap(true);       // wrap line
      tArea.setWrapStyleWord(true);  // wrap line at word boundary
      tArea.setBackground(new Color(204, 238, 241)); // light blue
      
      
      //The scroll bar
      JScrollPane scroll = new JScrollPane(tArea);
      scroll.setPreferredSize(new Dimension(490, 300));//set size of scroll panel
      JPanel conta = new JPanel();
      conta.add(scroll);
      conta.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,83));
      rightContain.add(conta, BorderLayout.CENTER);      
      
      
      //buttons display in south border layout of content area JPanel
      JPanel butonnContainer  = new JPanel(new GridLayout(1,2,10,0));
      ApproveStatusJB =  new JButton("Approve");//for approce status
      denyStatusJB =  new JButton("Deny");//for deny status
      butonnContainer.add(ApproveStatusJB);
      butonnContainer.add(denyStatusJB);
      butonnContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,420));
      rightContain.add(butonnContainer, BorderLayout.SOUTH);
      
      //activities buttons to action listener
      ApproveStatusJB.addActionListener(this);
      denyStatusJB.addActionListener(this);
   
   }
   
   JButton acceptJb;
   JButton rejectJb;
   /*
    *to display the information of an original project infomation a
    * faculty select if need to accept or reject of this project
    *@ param
   */
   public void SelectApplication(){
      
      JPanel NorthContain = new JPanel(new FlowLayout(FlowLayout.LEFT));//display the title of north JPanel of content area
      JLabel northTitle = new JLabel("Application");//the tile of a page
      northTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,450));//re-location the project name    
      northTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-location the project name 
      northTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
      
      //make a line to split the project name and content
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      NorthContain.setPreferredSize(new Dimension(595,77));
      NorthContain.add(northTitle, BorderLayout.NORTH);
      NorthContain.add(aLine, BorderLayout.CENTER);
      
      //to display the name of student who work in this capstone 
      AppNameJL = new JLabel(studentHisName);
      AppNameJL.setLayout(new FlowLayout(FlowLayout.LEFT));
      NorthContain.add(AppNameJL);
      AppNameJL.setFont(new Font("Serif", Font.ITALIC , 23));//set font
      
      rightContain.add(NorthContain, BorderLayout.NORTH);      
   
   
      //to display description of project      
      JTextArea tArea = new JTextArea(Applydesc);
      tArea.setEditable(false);//set enable to edit
      tArea.setFont(new Font("Serif", Font.ITALIC, 19));
      tArea.setLineWrap(true);       // wrap line
      tArea.setWrapStyleWord(true);  // wrap line at word boundary
      tArea.setBackground(new Color(204, 238, 241)); // light blue
      
      
      //The scroll bar
      JScrollPane scroll = new JScrollPane(tArea);
      scroll.setPreferredSize(new Dimension(490, 300));//set size and font
      JPanel conta = new JPanel();
      conta.add(scroll);
      conta.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,83));
      rightContain.add(conta, BorderLayout.CENTER);      
      
      
      //buttons display in south border layout of content area JPanel
      JPanel butonnContainer  = new JPanel(new GridLayout(1,2,10,0));
      acceptJb =  new JButton("Accept");
      rejectJb =  new JButton("Reject");
      butonnContainer.add(acceptJb);
      butonnContainer.add(rejectJb);
      butonnContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,420));
      rightContain.add(butonnContainer, BorderLayout.SOUTH);
      
      //activities buttons to action listener
      acceptJb.addActionListener(this);
      rejectJb.addActionListener(this);
   }
   

   ArrayList<JRadioButton> myList;
   JButton  ApplyDecideJB;
   
   ArrayList<String>ApplyNameArr;
   ArrayList<String>ApplyUsernameArr;
   ArrayList<String>ApplyEmailArr;
   ArrayList<String>ApplycapstoneidArr;
   ArrayList<String>ApplytitleArr;
   ArrayList<String>ApplydescArr;
   
   /*
    *to display the applicaiton list
    * faculty can select 
    *@ param
   */       
   public void ApplicationList(){
      JPanel NorthContain = new JPanel();//display the title of north JPanel of content area
      JLabel northTitle = new JLabel("Application List");// the page tile
      northTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-location the project name 
      northTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
      //make a line to split the project name and content
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      NorthContain.setPreferredSize(new Dimension(590,44));
      NorthContain.add(northTitle, BorderLayout.NORTH);
      NorthContain.add(aLine, BorderLayout.CENTER);
   
      rightContain.add(NorthContain, BorderLayout.NORTH);// add the to content area Jpanel, border layout north      
   
   
      //middle JPanel to display the list of application who in this faculty
      JPanel middleJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
      middleJP.setPreferredSize(new Dimension(570,450));
      JScrollPane js = new JScrollPane(middleJP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      js.setPreferredSize(new Dimension(590,300));  
      
      //call applist method from faculty class and store in 2D array lsit
      ArrayList<ArrayList<String>> list = st.ApplyList();  
          
      //arraylist to store the inforamtion of student project 
      ApplyUsernameArr = new  ArrayList<String>();
      ApplyNameArr = new  ArrayList<String>();
      ApplyEmailArr = new  ArrayList<String>();
      ApplycapstoneidArr = new  ArrayList<String>();
      ApplytitleArr = new  ArrayList<String>();
      ApplydescArr = new  ArrayList<String>();
   
      //get the value of each and add into arraylist of each
      if(list.size()>0)
      {
         for (int x=1; x<list.size(); x++){
            for (int y=0; y<list.get(x).size(); y++)
            {
               ApplyUsernameArr.add(list.get(x).get(0));
               ApplyNameArr.add(list.get(x).get(1));
               ApplyEmailArr.add(list.get(x).get(2));
               ApplycapstoneidArr.add(list.get(x).get(3));
               ApplytitleArr.add(list.get(x).get(4));
               ApplydescArr.add(list.get(x).get(5));
               break;                
            }                     
         }
      }
   
      
      ButtonGroup group = new ButtonGroup();// method for only click once of jradiobutton
      myList = new ArrayList<>();//arraylist to store radio button
      for(int i = 0; i < ApplyNameArr.size(); i++){//add into button as a _list, get studentName and status from databash 
         String output = String.format("%-40s", ApplyNameArr.get(i));
         myList.add(new JRadioButton(output));//jradiobutton add into jradiobutton array list
      }
      //the style of radio button and add into button group
      for(int i = 0; i < myList.size(); i++){
      
         myList.get(i).setBackground(Color.red);
         myList.get(i).setFont(new Font("Serif", Font.BOLD , 25));
         group.add( myList.get(i));
         middleJP.add( myList.get(i));
      }
      rightContain.add(js, BorderLayout.CENTER);//add into radio content page, border layout center      
      
      ApplyDecideJB = new JButton("Accept/Reject");//the button for jump to decision page
      
      
      /*
       *  JPanel named southsouth for add to content page
       *  lacation in south border layout 
      */
      JPanel southsouth = new JPanel(new GridLayout(1,2,10,10));
      southsouth .setBorder(BorderFactory.createEmptyBorder(0, 440, 0,0));
      southsouth.add(ApplyDecideJB);
      
      ApplyDecideJB .addActionListener(this);//activity button
         
      rightContain.add(southsouth,BorderLayout.SOUTH);//add to content page south border layout
   } 
   ArrayList<JRadioButton> list;  
   
   JButton enterGrade;
   JButton hisJB;
   ArrayList<String> trackNameArr       ;
   ArrayList<String> trackCapstoneIdArr ; 
   ArrayList<String> trackArSidr        ;
   ArrayList<String> trackTitleArr      ;
   ArrayList<String> trackGradeArr      ;
   ArrayList<String> trackDateArr       ;
   ArrayList<String> tracknameArr       ;
   ArrayList<String> trackDefenseDateArr;
   ArrayList<String> trackArr           ;
   ArrayList<JRadioButton> trackStudentButtonArr;
   JButton TrackSpecInfoJB;
   
    /*
    * to display the inforamtion of one project
    * base one (sepecfic info) button
    *@ param
   */ 
   public void TrackStudentList(){
      
      JPanel trackJP = new JPanel();//display the tile of north JPanel of centent area
      JLabel trackTitle = new JLabel("Track Student ");//the page title
      trackTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-loation the page title
      trackTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
      //make a line to split the project name and content
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      trackJP.setPreferredSize(new Dimension(590,44));
      
      trackJP.add(trackTitle, BorderLayout.NORTH);
      trackJP.add(aLine, BorderLayout.CENTER);
      
      rightContain.add(trackJP, BorderLayout.NORTH);// add the to content area Jpanel, border layout north     
   
      
      //call ViewTrackingStudent method from faculty class and store in 2D array lsit
      ArrayList<ArrayList<String>> list = st.ViewTrackingStudent();
      
      //arraylist to store the inforamtion of student project 
      trackNameArr       = new ArrayList<String>();
      trackCapstoneIdArr  = new ArrayList<String>();
      trackArSidr        = new ArrayList<String>();
      trackTitleArr      = new ArrayList<String>();
      trackGradeArr      = new ArrayList<String>();
      trackDateArr       = new ArrayList<String>();
      tracknameArr       = new ArrayList<String>();
      trackDefenseDateArr= new ArrayList<String>();
      //get the value of each and add into arraylist of each
      if(list.size()>0)
      {
         for (int x=1; x<list.size(); x++){
            for (int y=0; y<list.get(x).size(); y++)
            {
            
               trackNameArr       .add(list.get(x).get(0));
               trackCapstoneIdArr .add(list.get(x).get(1));
               trackArSidr        .add(list.get(x).get(2));
               trackTitleArr      .add(list.get(x).get(3));
               trackGradeArr      .add(list.get(x).get(4));
               trackDateArr       .add(list.get(x).get(5));
               tracknameArr       .add(list.get(x).get(6));
               trackDefenseDateArr.add(list.get(x).get(7));
               
               break;
            }
                    
         }
      }
   
      
      JPanel middleJP = new JPanel(new FlowLayout(FlowLayout.LEFT));//the Panel to contain jradio buttons
      middleJP.setPreferredSize(new Dimension(570,450));//set the size 
      JScrollPane js = new JScrollPane(middleJP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      js.setPreferredSize(new Dimension(590,300));   
      ButtonGroup group = new ButtonGroup();// method for only click once of jradiobutton
      trackStudentButtonArr = new ArrayList<>();
      for(int i = 0; i < trackNameArr.size(); i++){//add into button as a _list, get studentName and status from databash 
         
         String output = String.format("%-40s", trackNameArr.get(i));
         trackStudentButtonArr.add(new JRadioButton(output)); 
      }
      for(int i = 0; i < trackStudentButtonArr.size(); i++){
         trackStudentButtonArr.get(i).setFont(new Font("Serif", Font.BOLD , 25));
         group.add( trackStudentButtonArr.get(i));// method for only click once of jradiobutton
         middleJP.add( trackStudentButtonArr.get(i));
      }
      rightContain.add(js, BorderLayout.CENTER);    
   
      TrackSpecInfoJB = new JButton("Specific Information");//button to to the specific information
      
      JPanel southsouth = new JPanel();//southsouth JPanel to contain 
     
      southsouth.add(TrackSpecInfoJB);
      TrackSpecInfoJB .addActionListener(this);//activity button
         
      rightContain.add(southsouth,BorderLayout.SOUTH);//add into content page with border layout south
      
   }
   String specificName;
   String specifiCapstoneid;
   String specificTitle;
   String specificGrade;
   String specificDate;
   String specificStatus;
   String specificDefenseDate;
   
   /*
    * to display the specific informaiton of a project
    * 
    *@ param
   */     
   public void specificInfo(){
   
      JPanel studentInfo = new JPanel(new GridLayout(7,1));//a container to contain all inforamtion
      studentInfo.setLayout(new BoxLayout(studentInfo, BoxLayout.Y_AXIS));
      studentInfo.setPreferredSize(new Dimension(570,450));//set the size if JPanel container             
      
      //JLabel for informaiton need to display                         
      JLabel something0 = new JLabel("<html><p>"+ specificName+ "</p><html>");
      JLabel something1 = new JLabel("<html><p>Capstone Id: "+ specifiCapstoneid + "</p><html>");
      JLabel something2 = new JLabel("<html><p>Title: "+ specificTitle+ "</p><html>");
      JLabel something3 = new JLabel("<html><p>Grade: "+ specificGrade+ "</p><html>");
      JLabel something4 = new JLabel("<html><p>Status: "+ specificStatus+ "</p><html>");
      JLabel something5 = new JLabel("<html><p>DefenseDate: "+ specificDefenseDate+ "</p><html>");
      
      
      //Container add all information need to display
      studentInfo.add(something0);   
      studentInfo.add(something1);
      studentInfo.add(something2);
      studentInfo.add(something3);
      studentInfo.add(something4);
      studentInfo.add(something5);
      
      //to set font style and size of informaiton
      something0.setFont(new Font("Serif", Font.BOLD, 25));
      something1.setFont(new Font("Serif", Font.ITALIC , 17));
      something2.setFont(new Font("Serif", Font.ITALIC , 17));
      something3.setFont(new Font("Serif", Font.ITALIC , 17));
      something4.setFont(new Font("Serif", Font.ITALIC , 17));
      something5.setFont(new Font("Serif", Font.ITALIC , 17));
      
      rightContain.add(studentInfo,BorderLayout.SOUTH);//add to content page south border layout
   }
   
   String studentHistoryUsername;
   /*
    * to display the all history of one project page
    *@ param
   */   
   public void StudentHis(){
   
      JPanel studentInfo = new JPanel(new GridLayout(7,1));//a container Jpanel to contain student informaiton
      studentInfo.setLayout(new BoxLayout(studentInfo, BoxLayout.Y_AXIS));
      studentInfo.setPreferredSize(new Dimension(570,450));//set the size of container
      
   
      ArrayList<JLabel> jlArr = new ArrayList<>();//Jlabel to store all history jlabel
      
      //call ViewHistory method from faculty class and store in 2D array lsit
      ArrayList<ArrayList<String>> list = st.ViewHistory(studentHistoryUsername);
      
      //get the value of each and add into Jlabel
      if(list.size()>0)
      {
         for (int x=1; x<list.size(); x++){
            for (int y=1; y<list.get(x).size(); y++)
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
         studentInfo.add(jlArr.get(i));//add jlables of a jlabel arraylist to jlabel
      }                          
      rightContain.add(studentInfo,BorderLayout.SOUTH);
      
   }

   
   ArrayList<JRadioButton> TrackMyList;   
   JTextField gradeJtf;
   JButton summitJb;
   
   /*
    *to display the GUI for enter grade of an project
    *@ param
   */   
   public void EnterGrade()
   {
      JPanel NorthContain = new JPanel();//display the title of north JPanel of content area
      JLabel northTitle = new JLabel("Grade");//the tile of a page
      northTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-location the project name 
      northTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
      //make a line to split the project name and content
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(595,2));
      NorthContain.setPreferredSize(new Dimension(590,77));
      NorthContain.add(northTitle, BorderLayout.NORTH);
      NorthContain.add(aLine, BorderLayout.CENTER);
     
     
      //to display the project title that need to enter grade 
      JLabel nameApp = new JLabel(enterGradeTitle);
      
      nameApp.setLayout(new FlowLayout(FlowLayout.LEFT));
      NorthContain.add(nameApp, BorderLayout.CENTER);
      nameApp.setFont(new Font("Serif", Font.ITALIC , 23));//set font size and style
   
      rightContain.add(NorthContain, BorderLayout.NORTH);      
   
      //for enter the  project description
      JTextArea tArea = new JTextArea(studentHistortyDesc);
      tArea.setEditable(false);//set enable to edit
      tArea.setFont(new Font("Serif", Font.ITALIC, 19));
      tArea.setLineWrap(true);       // wrap line
      tArea.setWrapStyleWord(true);  // wrap line at word boundary
      tArea.setBackground(new Color(204, 238, 241)); // light blue
      
      //for cotaine JTextArea
      JScrollPane scroll = new JScrollPane(tArea);
      scroll.setPreferredSize(new Dimension(495, 200));
      JPanel conta = new JPanel();
      conta.add(scroll);
      conta.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,83));
      rightContain.add(conta, BorderLayout.CENTER);      
      
      
    
      JPanel butonnContainer  = new JPanel();
      JPanel gradeContainer  = new JPanel();
      JLabel gradeJl = new JLabel("Grade");//JLabel for indicate where are enter gtade
      gradeJl.setFont(new Font("Serif", Font.BOLD, 16));
      gradeJtf = new JTextField(5);  //JTextField for enter the grade
      gradeContainer.add(gradeJtf);
      gradeContainer.add(gradeJl);
      summitJb = new JButton("Submit");
      JPanel conntainer1 = new JPanel(new GridLayout(2,1));
      conntainer1.add(gradeContainer);
      JPanel containButton = new JPanel();
      containButton.add(summitJb);
      conntainer1.add(containButton);
    
      butonnContainer.setBorder(BorderFactory.createEmptyBorder(-3, 0, 0,465));//re-locate the location
      containButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,35));   
    
      butonnContainer.add(conntainer1, BorderLayout.NORTH);
      rightContain.add(butonnContainer, BorderLayout.SOUTH);
      
      summitJb.addActionListener(this);//activity the button
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
   
   JButton StudentHisJB;
   JButton enterGradeJB;
      
   /*
    * display the to show the page of student 
    * dusplay the buttons need to work with
   */   
   public void studentList(){
   
      JPanel trackJP = new JPanel();//display the title of north JPanel of content area
      JLabel trackTitle = new JLabel("Student List");//the title of a page
      trackTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-location the project name 
      trackTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
      //make a line to split the project name and content
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      trackJP.setPreferredSize(new Dimension(590,44));
      
      //add into north borderlayout of content area
      trackJP.add(trackTitle, BorderLayout.NORTH);
      trackJP.add(aLine, BorderLayout.CENTER);
      rightContain.add(trackJP, BorderLayout.NORTH);  
      
      //call ViewStudent method from faculty class and store in 2D array lsit
      ArrayList<ArrayList<String>> list = st.ViewStudent();
      //arraylist to store the inforamtion of student project 
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
      
      //get the value of each and add into arraylist of each
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
               
            
               break;     
            }                  
         }
      }
   
   
      JPanel middleJP = new JPanel(new FlowLayout(FlowLayout.LEFT));//the panel for border layout,center of content JPanel
      middleJP.setPreferredSize(new Dimension(570,450));//resize the JPanel
      JScrollPane js = new JScrollPane(middleJP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      js.setPreferredSize(new Dimension(590,300));   
      ButtonGroup group = new ButtonGroup();// method for only click once of jradiobutton
      StudentListButonnArr = new ArrayList<>();
      
      for(int i = 1; i < listFullnameArr.size(); i++){//add into button as a _list, get studentName and status from databash 
         
         String output = String.format(listFullnameArr.get(i));
         
         if(!this.StudentListButonnArr.contains(new JRadioButton(output))) {
            StudentListButonnArr.add(new JRadioButton(output)); 
         }
      }
      
      
      for(int i = 0; i < StudentListButonnArr.size(); i++){
      
         StudentListButonnArr.get(i).setFont(new Font("Serif", Font.BOLD , 25));
         StudentListButonnArr.get(i).setPreferredSize(new Dimension(300,30));
         group.add( StudentListButonnArr.get(i));// method for only click once of jradiobutton
         middleJP.add( StudentListButonnArr.get(i));
      }
      rightContain.add(js, BorderLayout.CENTER);      
      
      specInfoJB = new JButton("Specific Information");
      StudentHisJB = new JButton("All History");
      enterGradeJB = new JButton("Enter Grade");
      JPanel southsouth = new JPanel(new GridLayout(1,3,10,10));
      southsouth .setBorder(BorderFactory.createEmptyBorder(0,120, 0,0));
      
      southsouth.add(StudentHisJB);
      southsouth.add(specInfoJB);
      southsouth.add(enterGradeJB);
      
      StudentHisJB.addActionListener(this);
      specInfoJB.addActionListener(this);
      enterGradeJB.addActionListener(this);
         
      rightContain.add(southsouth,BorderLayout.SOUTH);
   }
   
   JButton ApproveDenyJB;
   ArrayList<String> approvecapstoneidArr;
   ArrayList<String> approveSidArr;
   ArrayList<String> approvefullnameArr;
   ArrayList<String> approvetitleArr;
   ArrayList<String> approvenameArr;
   
   /*
    * to display the project need to approve
    * only Dr.Yu able to use this page
    *@ param
   */      
   public void selectApprove(){
      JPanel NorthContain = new JPanel();//display the title of north JPanel of content area
      JLabel northTitle = new JLabel("Approve List");// the page tile
      northTitle.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0,0));//re-location the project name
      northTitle.setFont(new Font("Serif", Font.BOLD , 25));//set font size and style
      
      //make a line to split the project name and content
      JPanel aLine = new JPanel();   
      aLine.setBackground(Color.black);
      aLine.setPreferredSize(new Dimension(590,2));
      NorthContain.setPreferredSize(new Dimension(590,44));
      
      NorthContain.add(northTitle, BorderLayout.NORTH);
      NorthContain.add(aLine, BorderLayout.CENTER);
     
      rightContain.add(NorthContain, BorderLayout.NORTH);// add the to content area Jpanel, border layout north      
   
      //middle JPanel to display the list of application who in this faculty
      JPanel middleJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
      middleJP.setPreferredSize(new Dimension(570,450));
      JScrollPane js = new JScrollPane(middleJP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      js.setPreferredSize(new Dimension(590,300));  
      
     //call proveList method from faculty class and store in 2D array lsit
      ArrayList<ArrayList<String>> list = st.proveList();
      
      approvecapstoneidArr = new  ArrayList<String>();
      approveSidArr = new  ArrayList<String>();
      approvefullnameArr = new  ArrayList<String>();
      approvetitleArr = new  ArrayList<String>();
      approvenameArr = new  ArrayList<String>();
   
      //get the value of each and add into arraylist of each
      if(list.size()>0)
      {
         for (int x=1; x<list.size(); x++){
            for (int y=0; y<list.get(x).size(); y++)
            {
               approvecapstoneidArr.add(list.get(x).get(0));
               approveSidArr.add(list.get(x).get(1));
               approvefullnameArr.add(list.get(x).get(2));
               approvetitleArr.add(list.get(x).get(3));
               approvenameArr.add(list.get(x).get(4));
               break;                
            }                     
         }
      }
      
      ButtonGroup group = new ButtonGroup();// method for only click once of jradiobutton
      myList = new ArrayList<>();
      for(int i = 0; i < approvetitleArr.size(); i++){//add into button as a _list, get studentName and status from databash 
         String output = String.format("%-40s", approvetitleArr.get(i));
         myList.add(new JRadioButton(output));//jradiobutton add into jradiobutton array list
      }
      //the style of radio button and add into button group
      for(int i = 0; i < myList.size(); i++){
      
         myList.get(i).setBackground(Color.red);
         myList.get(i).setFont(new Font("Serif", Font.BOLD , 25));
         group.add( myList.get(i));
         middleJP.add( myList.get(i));
      }
      rightContain.add(js, BorderLayout.CENTER);   //add into radio content page, border layout center      
      
      //for approve or deny, can jump to another pafe
      ApproveDenyJB = new JButton("Approve/Deny");
      
      
      /*
       *relocate the size Button and resize of button
      */
      JPanel southsouth = new JPanel(new GridLayout(1,2,10,10));
      southsouth .setBorder(BorderFactory.createEmptyBorder(0, 440, 0,0));
      southsouth.add(ApproveDenyJB);
      ApproveDenyJB .addActionListener(this);
         
      rightContain.add(southsouth,BorderLayout.SOUTH);//add to content page south border layout
   } 

   
   @Override
   /*
    * the method to add actions listener to each button
    *@param  e to get the source of buttons
   */
   public void actionPerformed(ActionEvent e) {
    
      /*
       *  the ApplicationListJB button to activity Application List page
      */
      if(e.getSource().equals(ApplicationListJB))
      {
         rightContain.removeAll();//remove all element of coente area page
         ApplicationList();
         rightContain.validate();//re-validate
         repaint();//update
      }
      /*
       *  the TrackStudentListJB button to activity tracking student List page
      */
      if(e.getSource().equals(TrackStudentListJB))
      {
         rightContain.removeAll();//remove all element of coente area page
         TrackStudentList();
         
         rightContain.validate();//re-validate
         repaint();//update
      }
      /*
       *  the summitJb button to activity sumbit
       */
      if(e.getSource().equals(summitJb))
      {
         rightContain.removeAll();//remove all element of coente area page
         st.enterFinalGrade(gradeJtf.getText(), StudentCaptonesID);
         studentList();
         rightContain.validate();//re-validate
         repaint();//update
      }
      
     /*
      *  the specInfoJB button to show specfic information
      */
      if(e.getSource().equals(specInfoJB))
      {
      
         for(int i = 0; i < StudentListButonnArr.size(); i++){
            
            if(StudentListButonnArr.get(i).isSelected())
            {
               rightContain.removeAll();//remove all element of coente area page
               
               specificName       =listFullnameArr   .get(i+1);
               specifiCapstoneid  =listCapstoneidArr .get(i+1);
               specificTitle      =listTitleArr      .get(i+1);
               specificGrade      =listGradeArr      .get(i+1);
               specificDate       =listDateArr       .get(i+1);
               specificStatus     =listNameArr       .get(i+1);
               specificDefenseDate=listDefenseDateArr.get(i+1);
               specificInfo();//activity informaiton page
               rightContain.validate();//re-validate
               repaint();//update
            }
         }
      } 
   
      /*
      *  the enterGrade button to show enter grade information
      */
      if(e.getSource().equals(enterGrade))
      {
         rightContain.removeAll();//remove all element of coente area page
         EnterGrade();
         rightContain.validate();//re-validate
         repaint();//update
      }
         
      /*
      *  the studentListJB button to show student list information
      */
      if(e.getSource().equals(studentListJB))
      {
         rightContain.removeAll();//remove all element of coente area page
         studentList();
         rightContain.validate();//re-validate
         repaint();//update
      }
      /*
      *  the ApplyDecideJB button to show decision information
      */
      if(e.getSource().equals(ApplyDecideJB))
      {
      
         for(int i = 0; i < myList.size(); i++){
                  
            if(myList.get(i).isSelected())
            {
               rightContain.removeAll();//remove all element of coente area page
               Applydesc = ApplydescArr.get(i);
               ApplyId = ApplycapstoneidArr.get(i);
               studentHisName = myList.get(i).getText();
               SelectApplication();
               rightContain.validate();//re-validate
               repaint();//update
            }
         }
      }   
      /*
      *  the acceptJb button to show accept 
      */
      if(e.getSource().equals(acceptJb))
      {
         rightContain.removeAll();//remove all element of coente area page
         st.decideCommittee(ApplyId, 1);
         ApplicationList();
         rightContain.validate();//re-validate
         repaint();//update
      }
      /*
      *  the rejectJb button to show reject 
      */
      if(e.getSource().equals(rejectJb))
      {
         rightContain.removeAll();//remove all element of coente area page
         st. decideCommittee(ApplyId, 0);
         ApplicationList();
         rightContain.validate();//re-validate
         repaint();//update
      }
      /*
      *  the enterGradeJB button to enter grade 
      */
      if(e.getSource().equals(enterGradeJB))
      {
         for(int i = 0; i < StudentListButonnArr.size(); i++){
            
            if(StudentListButonnArr.get(i).isSelected())
            {
               rightContain.removeAll();//remove all element of coente area page
               enterGradeTitle =  listTitleArr.get(i+1);
               studentHistortyDesc =  listDescArr.get(i+1);
               StudentCaptonesID =  listCapstoneidArr.get(i+1) ;
               EnterGrade();
               rightContain.validate();//re-validate
               repaint();//update
            }
         }
      }
      /*
      *  the StudentHisJB button to show all student history 
      */
      if(e.getSource().equals(StudentHisJB))
      {
        
         
         for(int i = 0; i < StudentListButonnArr.size(); i++){
            
            if(StudentListButonnArr.get(i).isSelected())
            {
               rightContain.removeAll();//remove all element of coente area page
             
               studentHistoryUsername = listUsernameArr.get(i+1);
               studentHistoryTitle = listTitleArr.get(i+1);
               StudentHis();
               rightContain.validate();//re-validate
               repaint();//update
            }
         }
      }
     /*
      *  the TrackSpecInfoJB button to show cuurent studeny history
      */
      if(e.getSource().equals(TrackSpecInfoJB))
      {
        
         for(int i = 0; i < trackStudentButtonArr.size(); i++){
            
            if(trackStudentButtonArr.get(i).isSelected())
            {
               rightContain.removeAll();//remove all element of coente area page
               
               specificName       =trackNameArr   .get(i);
               specifiCapstoneid  =trackCapstoneIdArr .get(i);
               specificTitle      =trackTitleArr      .get(i);
               specificGrade      =trackGradeArr      .get(i);
               specificDate       =trackDateArr       .get(i);
               specificStatus     =tracknameArr       .get(i);
               specificDefenseDate=trackDefenseDateArr.get(i);
               
               specificInfo();
               rightContain.validate();//re-validate
               repaint();//update
            }
         }
      }
     /*
      *  the updateApproveStatusJB button to update approve
      */
      if(e.getSource().equals(updateApproveStatusJB))
      {
         rightContain.removeAll();//remove all element of coente area page
      
         selectApprove();
         rightContain.validate();//re-validate
         repaint();//update
      
      }
     /*
      *  the ApproveDenyJB button to deny approve
      */
      if(e.getSource().equals(ApproveDenyJB))
      {
         for(int i = 0; i < myList.size(); i++){
            
            if(myList.get(i).isSelected())
            {
               rightContain.removeAll();//remove all element of coente area page
               
               approveCapstoneID      =approvecapstoneidArr      .get(i);
               approveFullname      =approvefullnameArr      .get(i);
               approveTitle      =approvetitleArr       .get(i);
               approveName       =approvenameArr       .get(i);
               approveSid = approveSidArr. get(i);   
               SelectApprove();
               rightContain.validate();//re-validate
               repaint();//update
            }
         }
      
      }   
    /*
      *  the ApproveStatusJB button to jump to aother page for approve
      */
      if(e.getSource().equals(ApproveStatusJB))
      {
         rightContain.removeAll();//remove all element of coente area page
               
             
         st.prove(approveSid,approveCapstoneID,1);
         selectApprove();
         rightContain.validate();//re-validate
         repaint();//update
           
      } 
     /*
      *  the denyStatusJB button to deny
      */
      if(e.getSource().equals(denyStatusJB))
      {
         rightContain.removeAll(); //remove all element of coente area page 
         st.prove(approveSid,approveCapstoneID,0);
         selectApprove();  
         rightContain.validate();//re-validate
         repaint(); //update
      }
   }

}