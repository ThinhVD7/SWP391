package Dal;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBContext {
    private final String DB_URL = "jdbc:mysql://localhost3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "qqqqqqqq";
    private static final String DATABASE = "quiz9.5";
    public Connection getConnection()throws Exception 
            
    {         
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DATABASE,USER,PASSWORD);
    }
    
    public static void main(String[] args) 
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz7",USER,PASSWORD);
            if (conn != null) {
                System.out.println("Connected");
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " +
                dm.getDatabaseProductName());
                System.out.println("Product version: " +
                dm.getDatabaseProductVersion());
                PreparedStatement ps = conn.prepareStatement("select * from account");
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    System.out.println(rs.getString(1));
                }
            }
        }catch (SQLException ex)
        {
            System.err.println("Cannot connect database, " + ex);
        }
    }
}
