import java.sql.*;
  public class DataCon{
     public static Connection con;
     static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
                  String url="jdbc:mysql://localhost:3306/clg";
                   con=DriverManager.getConnection(url,"root","root");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
     }
  }