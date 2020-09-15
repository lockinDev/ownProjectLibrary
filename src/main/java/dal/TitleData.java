package dal;

import java.util.*;
import java.sql.*;
import models.*;


public abstract class TitleData 
{
  public static final String ADMINISTRATOR = "Administrator"; // the name of the supper user
  
  
  public static List<Title> getTitles()
  {
    List<Title> list = new ArrayList();
    try (Connection conn = Db.getConnection(); Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery("SELECT * FROM title ORDER BY name");
      while(res.next()) list.add(new Title(res.getInt("id"), res.getString("name")));
    }
    catch(SQLException ex)
    {
      list.clear();
    }
    return list;
  }
  
  
  public static Title getTitle(int id)
  {
    try (Connection conn = Db.getConnection())
    {
      return getTitle(conn, id);
    }
    catch (SQLException ex)
    {
      return null;
    }
  }
  
  
  public static boolean exists(String name)
  {
    return Db.select("SELECT id FROM title WHERE name = '" + name + "'");
  }
  
  
  public static boolean update(Title title)
  {
    if (title.getName().compareToIgnoreCase(ADMINISTRATOR) == 0) return false;
    try (Connection conn = Db.getConnection())
    {
      return !Db.select(conn, "SELECT id FROM title WHERE name = '" + title.getName() + "' AND name != 'Administrator' AND id != " + title.getId()) && Db.execute(conn, "UPDATE title SET name = ? WHERE id = ?", new Param(title.getName(), Types.VARCHAR), new Param(title.getId(), Types.INTEGER));
    }
    catch(SQLException ex)
    {
      return false;
    }
  }

  
  public static boolean delete(int id)
  {
    return Db.execute("DELETE FROM title WHERE id = ? AND name <> '" + ADMINISTRATOR + "'", new Param(id, Types.INTEGER));
  }

  
  public static Title insert(String name)
  {
    try (Connection conn = Db.getConnection())
    {
      if (!Db.select(conn, "SELECT id FROM title WHERE name = '" + name + "'") && Db.execute(conn, "INSERT INTO title (name) VALUES (?)", new Param(name, Types.VARCHAR)))
      {
        Title title = getTitle(conn, Db.getId(conn));
        if (title != null) return title;
      }
    }
    catch(SQLException ex)
    {
    }
    return null;
  }

  
  public static Title createAdmin()
  {
    try (Connection conn = Db.getConnection(); Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery("SELECT * FROM title WHERE name = '" + ADMINISTRATOR + "'");
      if (res.next()) return new Title(res.getInt("id"), res.getString("name"));
      if (Db.execute(conn, "INSERT INTO title (name) VALUES (?)", new Param(ADMINISTRATOR, Types.VARCHAR))) return getTitle(conn, Db.getId(conn));
    }
    catch(SQLException ex)
    {
    }
    return null;
  }
  
  static Title getTitle(Connection conn, int id)
  {
    try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM title WHERE id = ?"))
    {
      stmt.setInt(1, id);
      ResultSet res = stmt.executeQuery();
      if (res.next()) return new Title(res.getInt("id"), res.getString("name"));
    }
    catch(SQLException ex)
    {
    }
    return null;
  }
}
