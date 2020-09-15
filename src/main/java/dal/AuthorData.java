package dal;

import java.util.*;
import java.sql.*;
import models.*;

public class AuthorData 
{
  
  public static List<Author> getAuthor(String firstname, String lastname)
  {
    return select("SELECT * FROM author WHERE firstname = '" + firstname + "' AND lastname = '" + lastname + "'");
  }

  
  public static List<Author> getAuthors()
  {
    return select("SELECT * FROM author ORDER BY lastname, firstname");
  }

  
  public static Author getAuthor(int id)
  {
    List<Author> list = select("SELECT * FROM author WHERE id = " + id);
    return list.size() > 0 ? list.get(0) : null;
  }

  
  public static Author insert(String firstname, String lastname, String text)
  {
    Connection conn = null;
    try
    {
      conn = Db.getConnection();
      conn.setAutoCommit(false);
      if (Db.execute(conn, "INSERT INTO author (firstname, lastname, text) VALUES (?, ?, ?)", new Param(firstname, Types.VARCHAR), new Param(lastname, Types.VARCHAR), new Param(text, Types.VARCHAR)))
      {
        int id = Db.getId(conn);
        Author author = getAuthor(conn, id);
        if (author != null)
        {
          conn.commit();
          return author;
        }
        else conn.rollback();
      }
    }
    catch(SQLException ex)
    {
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
    return null;
  }

  
  public static boolean update(Author author)
  {
    return Db.execute("UPDATE author SET firstname = ?, lastname = ?, text = ? WHERE id = ?", new Param(author.getFirstname(), Types.VARCHAR), new Param(author.getLastname(), Types.VARCHAR), new Param(author.getText(), Types.VARCHAR), new Param(author.getId(), Types.INTEGER));
  }

  
  public static boolean delete(int id)
  {
    return Db.execute("DELETE FROM author WHERE id = ?", new Param(id, Types.INTEGER));
  }

  // Help method that for an open connection returns an author with a particular primary key.
  static Author getAuthor(Connection conn, int id)
  {
    try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM author WHERE id = ?"))
    {
      stmt.setInt(1, id);
      ResultSet res = stmt.executeQuery();
      if (res.next()) return new Author(res.getInt("id"), res.getString("firstname"), res.getString("lastname"), res.getString("text"));
    }
    catch(SQLException ex)
    {
    }
    return null;
  }

  private static List<Author> select(String sql)
  {
    List<Author> list = new ArrayList();
    try (Connection conn = Db.getConnection())
    {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);
      while(res.next()) list.add(new Author(res.getInt("id"), res.getString("firstname"), res.getString("lastname"), res.getString("text")));
    }
    catch(SQLException ex)
    {
      list.clear();
    }
    return list;
  }

  // Help method that for an open connection returns a list with all Author objects for the book with the primary key bookid.
  static List<Author> getAuthors(Connection conn, int bookid)
  {
    List<Author> list = new ArrayList();
    try (Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery("SELECT * FROM author, written WHERE bookid = " + bookid + " AND autid = id ORDER BY lastname, firstname");
      while(res.next()) list.add(new Author(res.getInt("id"), res.getString("firstname"), res.getString("lastname"), res.getString("text")));
    }
    catch(SQLException ex)
    {
      list.clear();
    }
    return list;
  }
}
