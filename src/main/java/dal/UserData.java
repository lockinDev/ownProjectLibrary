package dal;

import java.util.*;
import java.sql.*;
import models.*;


public abstract class UserData 
{
  
  public static boolean adminCreated()
  {
    return Db.select("SELECT id FROM user WHERE role = 0");
  }

  
  public static User getAdmin(String passwd)
  {
    try (Connection conn = Db.getConnection(); Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery("SELECT * FROM user, title WHERE user.role = 0 AND passwd = '" + passwd + "' AND user.titid = title.id");
      if (res.next()) return createUser(conn, res);
    }
    catch(SQLException ex)
    {
    }
    return null;
  }
  
  
  public static User getUser(String email, String passwd)
  {
    if (email == null) return null;
    List<User> list = getUsers("SELECT * FROM user, title WHERE user.email = '" + email + "' AND user.passwd = '" + passwd + "' AND user.titid = title.id");
    return list.size() > 0 ? list.get(0) : null;
  }

  
  public static User getUser(int id)
  {
    try (Connection conn = Db.getConnection(); Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery("SELECT * FROM user, title WHERE user.id = " + id + " AND user.titid = title.id");
      if (res.next()) return createUser(conn, res);
    }
    catch(SQLException ex)
    {
    }
    return null;
  }

  
  public static User getUser(String email)
  {
    try (Connection conn = Db.getConnection(); Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery("SELECT * FROM user, title WHERE user.email = '" + email + "' AND user.titid = title.id");
      if (res.next()) return createUser(conn, res);
    }
    catch(SQLException ex)
    {
    }
    return null;
  }

  
  public static List<User> getUsers()
  {
    return getUsers("SELECT * FROM user, title WHERE user.titid = title.id AND role > 0 ORDER BY user.lastname, user.firstname");
  }

  
  public static boolean changePassword(String email, String passwd)
  {
    return Db.execute("UPDATE user SET passwd = ? WHERE email = ?", new Param(passwd, Types.VARCHAR), new Param(email, Types.VARCHAR));
  }
  
  
  public static User insert(User user, String passwd)
  {
    try (Connection conn = Db.getConnection())
    {
      if (!Db.select(conn, "SELECT id FROM user WHERE email = '" + user.getEmail() + "'") && Db.execute("INSERT INTO user (email, passwd, firstname, lastname, address, code, phone, titid, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", new Param(user.getEmail(), Types.VARCHAR), new Param(passwd, Types.VARCHAR), new Param(user.getFirstname(), Types.VARCHAR), new Param(user.getLastname(), Types.VARCHAR), new Param(user.getAddress(), Types.VARCHAR), new Param(user.getCode(), Types.VARCHAR), new Param(user.getPhone(), Types.VARCHAR), new Param(user.getTitle().getId(), Types.INTEGER), new Param(user.getRole(), Types.INTEGER)))
      {
        User usr = getUser(conn, user.getEmail());
        if (usr != null) return usr;
      }
    }
    catch(SQLException ex)
    {
    }
    return null;
  }

  
  public static boolean update(User user)
  {
    return Db.execute("UPDATE user SET firstname = ?, lastname = ?, address = ?, code = ?, phone = ?, titid = ?, role = ? WHERE id = ?", new Param(user.getFirstname(), Types.VARCHAR), new Param(user.getLastname(), Types.VARCHAR), new Param(user.getAddress(), Types.VARCHAR), new Param(user.getCode(), Types.VARCHAR), new Param(user.getPhone(), Types.VARCHAR), new Param(user.getTitle().getId(), Types.INTEGER), new Param(user.getRole(), Types.INTEGER), new Param(user.getId(), Types.INTEGER));
  }
  
  
  public static boolean update(User user, String passwd)
  {
    return Db.execute("UPDATE user SET passwd = ?, firstname = ?, lastname = ?, address = ?, code = ?, phone = ?, titid = ?, role = ? WHERE id = ?", new Param(passwd, Types.VARCHAR), new Param(user.getFirstname(), Types.VARCHAR), new Param(user.getLastname(), Types.VARCHAR), new Param(user.getAddress(), Types.VARCHAR), new Param(user.getCode(), Types.VARCHAR), new Param(user.getPhone(), Types.VARCHAR), new Param(user.getTitle().getId(), Types.INTEGER), new Param(user.getRole(), Types.INTEGER), new Param(user.getId(), Types.INTEGER));
  }

  
  public static boolean delete(int id) 
  {
    return Db.execute("DELETE FROM user WHERE id = ?", new Param(id, Types.INTEGER));
  }

  private static List<User> getUsers(String sql)
  {
    List<User> list = new ArrayList();
    try (Connection conn = Db.getConnection(); Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery(sql);
      while(res.next()) list.add(createUser(conn, res));
    }
    catch(SQLException ex)
    {
      list.clear();
    }
    return list;
  }

  private static User createUser(Connection conn, ResultSet res) throws SQLException
  {
    int id = res.getInt("id");
    User user = new User(id, res.getString("user.email"), res.getString("user.firstname"), res.getString("user.lastname"), res.getString("user.address"), res.getString("user.code"), res.getString("user.phone"), new Title(res.getInt("user.titid"), res.getString("title.name")), res.getInt("user.role"));
    return user;
  }

  private static User getUser(Connection conn, String email)
  {
    try (Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery("SELECT * FROM user, title WHERE user.email = '" + email + "' AND user.titid = title.id");
      if (res.next()) return createUser(conn, res);
    }
    catch(SQLException ex)
    {
    }
    return null;
  }

  static User getUser(Connection conn, int id)
  {
    try (Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery("SELECT * FROM user, title WHERE user.id = " + id + " AND user.titid = title.id");
      if (res.next()) return createUser(conn, res);
    }
    catch(SQLException ex)
    {
    }
    return null;
  }
}
