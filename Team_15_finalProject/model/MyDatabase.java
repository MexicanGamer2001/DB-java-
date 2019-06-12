/**
 * File: mydatabase.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 3 April, 2018
 *
 * Database layer class
 */

package model;


import java.sql.*;
import java.util.ArrayList;

public class MyDatabase {

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Connection conn=null;
    String url="jdbc:mysql://localhost/mydb?autoReconnect=true&useSSL=false";
    String user="root";
    String baduser="roo1t";
    String password="student";
    String driver ="com.mysql.jdbc.Driver";
    ArrayList<ArrayList<String>> mytable=new ArrayList<ArrayList<String>>();


    /**
     * Connect to the database
     * @return true on success
     */
    public boolean connection()
    {
        try
        {
            Class.forName(driver);
            conn= DriverManager.getConnection(url, user, password);
            return true;
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
            return false;
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.toString());

            return false;
        }
        catch(NullPointerException npe){
            System.out.println(npe.toString());
            return false;

        }
        catch (Exception sql)
        {
            sql.printStackTrace();
            return false;
        }

    }


    /**
     * Close the database
     * @return true on success
     */
    public boolean close()
    {
        try
        {
            conn.close();
            return true;
        }
        catch(SQLException sql)
        {

            System.out.println(sql.toString());
            return false;
        }
        catch(NullPointerException npe){

            System.out.println(npe.toString());

            return false;
        }
        catch (Exception sql)
        {
            sql.printStackTrace();
            return false;
        }

    }


    /**
     * Prepare statement and bind passed values
     * @param SQL prepare statement
     * @param value values for statement
     * @return prepared statement
     */
    public PreparedStatement prepare(String SQL, ArrayList<String> value)
    {
        try
        {
            PreparedStatement stmnt =conn.prepareStatement(SQL);

            for (int i=1; i<=value.size(); i++)
            {
                stmnt.setString(i,value.get(i-1));
            }
            return stmnt;
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle.toString());
            return null;

        }
        catch (Exception sql)
        {
            sql.printStackTrace();
            return null;

        }


    }


    /**
     * Retrieve data from database with prepared statement
     * @param SQL base query statement
     * @param value values to bind to statement
     * @return ArrayList results on success, else null
     */
    public ArrayList<ArrayList<String>> getData(String SQL, ArrayList<String> value)
    {

        connection();

        mytable=new ArrayList<ArrayList<String>>();

        try{
            PreparedStatement stmnt=prepare(SQL,value);
            ResultSet rs= stmnt.executeQuery();
            ResultSetMetaData rsmd= rs.getMetaData();
            int numCols=rsmd.getColumnCount();
            ArrayList<String> numberofrowName=new ArrayList<String>();
            for (int x=1; x<=numCols; x++)
            {
                numberofrowName.add(rsmd.getColumnName(x));

            }
            mytable.add(numberofrowName);
            while (rs.next()){
                ArrayList<String> numberofrow=new ArrayList<String>();
                for (int i=1; i<=numCols; i++){
                    numberofrow.add(rs.getString(i));
                }
                mytable.add(numberofrow);
            }

            close();
            return mytable;
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle.toString());
            return null;
        }
        catch (Exception sql)
        {
            sql.printStackTrace();
            return null;
        }

    }


    /**
     * Retrieve data from database
     * @param SQL query string
     * @return results as ArrayList on success
     */
    public ArrayList<ArrayList<String>> getData(String SQL)
    {
         
        connection();
          mytable=new ArrayList<ArrayList<String>>();
        try{
            Statement stmnt= conn.createStatement();
            ResultSet rs= stmnt.executeQuery(SQL);
            ResultSetMetaData rsmd= rs.getMetaData();
            int numCols=rsmd.getColumnCount();
               ArrayList<String> numberofrowName=new ArrayList<String>();
            for (int x=1; x<=numCols; x++)
            {
                numberofrowName.add(rsmd.getColumnName(x));

            }
            mytable.add(numberofrowName);

            while (rs.next()){
                ArrayList<String> numberofrow=new ArrayList<String>();
                for (int i=1; i<=numCols; i++){
                    numberofrow.add(rs.getString(i));
                }
                mytable.add(numberofrow);
            }

        }
        catch (SQLException sqle)
        {
            System.out.println(sqle.toString());
        }

        catch(NullPointerException npe){

            System.out.println(npe.toString());


        }
        catch (Exception sql)
        {
            sql.printStackTrace();
        }
        close();
        return mytable;
    }


    /**
     * Perform update queries on database
     * @param SQL query string
     * @return number of rows affected, -1 on failure
     */
    public int setData(String SQL)
    {
        connection();
        try
        {
            Statement stmnt= conn.createStatement();
            int rc =stmnt.executeUpdate(SQL);
            close();
            return rc;
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
            return -1;

        }
        catch(NullPointerException npe){
            return -1;
        }
        catch (Exception sql)
        {
            return -1;
        }

    }


    /**
     * Perform update queries with prepared statement
     * @param SQL query string
     * @param value values to bind to query
     * @return number of rows affected, -1 on failure
     */
    public boolean setData(String SQL, ArrayList<String> value)
    {

        if(excuteStmt(SQL, value) <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }

    }


    /**
     * Executes prepared statement
     * @param SQL query string
     * @param value values to bind to query string
     * @return number of rows affected, -1 on failure
     */
    public int excuteStmt(String SQL, ArrayList<String> value)
    {
        try
        {
            connection();
            PreparedStatement stmnt=prepare(SQL,value);
            int rc =stmnt.executeUpdate();
            close();
            return rc;
        }
        catch(SQLException sqle)
        {
            return -1;
        }
        catch(NullPointerException npe){
            return -1;
        }
        catch (Exception sql)
        {
            return -1;
        }
    }


    /**
     * Begin transaction
     */
    public void startTrans()
    {
        connection();
        try{
            conn.setAutoCommit(false);
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        close();
    }

    /**
     * End and commit transaction
     */
    public void endTrans()
    {
        connection();
        try
        {
            conn.setAutoCommit(true);
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        close();
    }


    /**
     * Rollback an existing transaction
     */
    public void rollbackTrans()

    {
        connection();
        try
        {
            conn.rollback();
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        close();
    }


}