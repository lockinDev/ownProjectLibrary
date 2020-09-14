package dal;

import java.sql.*;

public class Db 
{
  public static Connection getConnection() throws SQLException
  {
    String usr = "**";
    String pwd = "*******";
    return DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", usr, pwd);
  }
}
