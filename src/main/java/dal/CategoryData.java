package dal;

import java.util.*;
import java.sql.*;
import models.*;

public class CategoryData 
{
    
  public static List<Category> getCategories()
  {
    List<Category> list = new ArrayList();
    try (Connection conn = Db.getConnection())
    {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery("SELECT * FROM category ORDER BY name");
      while(res.next()) list.add(new Category(res.getInt("id"), res.getString("name")));
    }
    catch(SQLException ex)
    {
      list.clear();
    }
    return list;
  }
  
  // Returns true if there exists a category with the value name
  public static boolean exists(String name)
  {
    return Db.select("SELECT id FROM category WHERE name = '" + name + "'");
  }
  
  
  public static boolean update(Category category)
  {
    try (Connection conn = Db.getConnection())
    {
      return !Db.select(conn, "SELECT id FROM category WHERE name = '" + category.getName() + "' AND id != " + category.getId()) && Db.execute(conn, "UPDATE category SET name = ? WHERE id = ?", new Param(category.getName(), Types.VARCHAR), new Param(category.getId(), Types.INTEGER));
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
      Db.execute(conn, "UPDATE book SET catid = NULL WHERE catid = ?", new Param(id, Types.INTEGER));
      Db.execute(conn, "DELETE FROM category WHERE id = ?", new Param(id, Types.INTEGER));
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

  
  public static Category insert(String name)
  {
    Connection conn = null;
    try
    {
      conn = Db.getConnection();
      conn.setAutoCommit(false);
      if (!Db.select(conn, "SELECT id FROM category WHERE name = '" + name + "'") && Db.execute(conn, "INSERT INTO category (name) VALUES (?)", new Param(name, Types.VARCHAR)))
      {
        Category category = getCategory(conn, Db.getId(conn));
        if (category != null)
        {
          conn.commit();
          return category;
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

  // Help method, that for an open connection returns a Category object.
  static Category getCategory(Connection conn, int id)
  {
    try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM category WHERE id = ?"))
    {
      stmt.setInt(1, id);
      ResultSet res = stmt.executeQuery();
      if (res.next()) return new Category(res.getInt("id"), res.getString("name"));
    }
    catch(SQLException ex)
    {
    }
    return null;
  }
}
