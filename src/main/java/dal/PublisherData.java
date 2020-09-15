package dal;

import java.util.*;
import java.sql.*;
import models.*;

public class PublisherData 
{
  
  public static List<Publisher> getPublishers()
  {
    List<Publisher> list = new ArrayList();
    try (Connection conn = Db.getConnection())
    {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery("SELECT * FROM publisher ORDER BY name");
      while(res.next()) list.add(new Publisher(res.getInt("id"), res.getString("name")));
    }
    catch(SQLException ex)
    {
      list.clear();
    }
    return list;
  }
  
  // Returns true if there exists a publisher with name
  public static boolean exists(String name)
  {
    return Db.select("SELECT id FROM publisher WHERE name = '" + name + "'");
  }

  
  public static Publisher insert(String name)
  {
    Connection conn = null;
    try
    {
      conn = Db.getConnection();
      conn.setAutoCommit(false);
      if (!Db.select(conn, "SELECT id FROM publisher WHERE name = '" + name + "'") && Db.execute(conn, "INSERT INTO publisher (name) VALUES (?)", new Param(name, Types.VARCHAR)))
      {
        Publisher publisher = getPublisher(conn, Db.getId(conn));
        if (publisher != null)
        {
          conn.commit();
          return publisher;
        }
      }
    }
    catch(SQLException ex)
    {
    }
    finally
    {
      try
      {
        if (conn != null) conn.rollback();
      }
      catch (SQLException ex)
      {
      }
      try
      {
        if (conn != null) conn.close();
      }
      catch (SQLException ex)
      {
      }
    }
    return null;
  }
  
  
  public static boolean update(Publisher publisher)
  {
    try (Connection conn = Db.getConnection())
    {
      return !Db.select(conn, "SELECT id FROM publisher WHERE name = '" + publisher.getName() + "' AND id != " + publisher.getId()) && Db.execute(conn, "UPDATE publisher SET name = ? WHERE id = ?", new Param(publisher.getName(), Types.VARCHAR), new Param(publisher.getId(), Types.INTEGER));
    }
    catch(SQLException ex)
    {
    }
    return false;
  }
  
  
  public static boolean delete(int id)
  {
    Connection conn = null;
    try
    {
      conn = Db.getConnection();
      conn.setAutoCommit(false);
      Db.execute(conn, "UPDATE book SET pubid = NULL WHERE pubid = ?", new Param(id, Types.INTEGER));
      Db.execute(conn, "DELETE FROM publisher WHERE id = ?", new Param(id, Types.INTEGER));
      conn.commit();
      return true;
    }
    catch(SQLException ex)
    {
      try
      {
        conn.rollback();
      }
      catch (Exception e)
      {
      }
      return false;
    }
    finally
    {
      try
      {
        if (conn != null) conn.close();
      }
      catch (SQLException ex)
      {
      }
    }
  }

  // Help method that for an open connection returns a Publisher object.
  static Publisher getPublisher(Connection conn, int id)
  {
    try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM publisher WHERE id = ?"))
    {
      stmt.setInt(1, id);
      ResultSet res = stmt.executeQuery();
      if (res.next()) return new Publisher(res.getInt("id"), res.getString("name"));
    }
    catch(SQLException ex)
    {
    }
    return null;
  }
}
