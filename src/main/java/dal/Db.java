package dal;

import java.sql.*;

public class Db 
{
  public static Connection getConnection() throws SQLException
  {
    String usr = "pa";
    String pwd = "Volmer_1234";
    return DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", usr, pwd);
  }
  
  // Executes a SQL SELECT and returns true, if the query returns at least one row.
  public static boolean select(String sql)
  {
    try (Connection conn = Db.getConnection())
    {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);
      return res.next();
    }
    catch(SQLException ex)
    {
      return false;
    }
  }
  
  
  // Executes a SQL SELECT on an open connection and returns true, if the query returns at least one row.
  public static boolean select(Connection conn, String sql)
  {
    try (Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery(sql);
      return res.next();
    }
    catch(SQLException ex)
    {
      return false;
    }
  }

  // Executes a prepared statement
  public static boolean execute(String sql, Param ... params)
  {
    try (Connection conn = Db.getConnection())
    {
      PreparedStatement stmt = conn.prepareStatement(sql);
      for (int i = 0; i < params.length; ++i) params[i].addValue(stmt, i + 1);
      return stmt.executeUpdate() == 1;
    }
    catch(SQLException ex)
    {
      return false;
    }
  }

  // Executes a prepared statement on an open connection
  public static boolean execute(Connection conn, String sql, Param ... params) throws SQLException
  {
    try (PreparedStatement stmt = conn.prepareStatement(sql))
    {
      for (int i = 0; i < params.length; ++i) params[i].addValue(stmt, i + 1);
      return stmt.executeUpdate() == 1;
    }
    catch(SQLException ex)
    {
      throw ex;
    }
  }
 
  public static String cut(String text, int width)
  {
    if (text == null) return null;
    if (text.length() > width) return text.substring(0, width);
    return text;
  }
  
  public static int getId(Connection conn) throws SQLException
  {
    Statement stmt = null;
    stmt = conn.createStatement();
    ResultSet res = stmt.executeQuery("SELECT LAST_INSERT_ID()");
    res.next();
    return res.getInt(1);
  }
}

class Param
{
  public Object value;
  public int type;
  
  public Param(Object value, int type)
  {
    this.value = value;
    this.type = type;
  }
  
  public void addValue(PreparedStatement stmt, int n) throws SQLException
  {
    switch (type)
    {
      case Types.INTEGER:
        if (value == null) stmt.setNull(n, type); else stmt.setInt(n, (Integer)value);
        break;
      case Types.VARCHAR:
        if (value == null) stmt.setNull(n, type); else stmt.setString(n, (String)value);
        break;
      case Types.BINARY:
        if (value == null) stmt.setNull(n, type); else stmt.setBytes(n, (byte[])value);
        break;
      case Types.BOOLEAN:
        if (value == null) stmt.setNull(n, type); else stmt.setBoolean(n, (Boolean)value);
        break;
      case Types.DATE:
        if (value == null) stmt.setNull(n, type); else stmt.setDate(n, (java.sql.Date)value);
        break;
    }
  }
}
