package Dal;

import Model.Account;
import Model.Lecturer;
import Model.Student;
import Model.Course;
import Model.Class;
import java.sql.Connection;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAO extends DBContext{
Connection connector;
private Vector <Account> account;
private Vector <Student> student;
private Vector <Lecturer> lecturer;
private Vector <Course> course;
private Vector <Class> classes;
private String status = "yes";
public String test;
public DAO()
{
    try
    {
        connector = new DBContext().getConnection();
        System.out.println("Connected");
        test = "Connected";
    }
    catch(Exception e)
    {
        System.out.println("Not connected");
        test = " Not Connected";
    }
}




public void loadStudent()
{
    student = new Vector();
    String sql = "select * from role";
    try
        {
            PreparedStatement ps = connector.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                {
                    

                }
        }
    catch(Exception e)
        {
            status = "Error at get Student "+ e.getMessage();
        }
}

public Account getAccountLogin(String email)
{
    String sql = "select * from account where Email = ?";
    try
        {
//            if(email.compareTo("nampthe171400@fpt.edu.vn")==0)
//                {
//                    System.out.println("Equals");
//                //email ="thinhnvhe163468";
//               // email = "nampthe171400@fpt.edu.vn";
//            }
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                {
                    test= rs.getString(4);
                    return new Account(rs.getString(1), 
                            rs.getString(2), 
                            rs.getString(3), 
                            rs.getString(4), 
                            rs.getInt(5), 
                            rs.getInt(6), 
                            rs.getFloat(7),
                            rs.getInt(8));
                }
        }
    catch(Exception e)
        {
            status = "Error at get Account "+ e.getMessage();
        }
    return null;
}

public void why()
        {
            try{
        if (connector != null) 
        {
                    System.out.println("Connected");
                    DatabaseMetaData dm = (DatabaseMetaData) connector.getMetaData();
                    System.out.println("Driver name: " + dm.getDriverName());
                    System.out.println("Driver version: " + dm.getDriverVersion());
                    System.out.println("Product name: " +
                    dm.getDatabaseProductName());
                    System.out.println("Product version: " +
                    dm.getDatabaseProductVersion());
                    PreparedStatement ps = connector.prepareStatement("select * from account");
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    System.out.println(rs.getString(2));
                }
        }
    }catch(SQLException e)
        {
            
        }
        }
    
public static void main(String[] args) 
{
    DAO d = new DAO();
    d.why();
    d.getAccountLogin("nampthe171400@fpt.edu.vn");

    System.out.println(d.test+"why");
   
        
}


}
