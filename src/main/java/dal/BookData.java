package dal;

import java.util.*;
import java.sql.*;
import models.*;

public class BookData 
{
  
  public static List<Book> getAll()
  {
    return select("SELECT * FROM book ORDER BY title");
  }

  
  public static Book getBook(int id)
  {
    if (id <= 0) return null;
    try (Connection conn = Db.getConnection())
    {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery("SELECT * FROM book WHERE id = " + id);
      if (res.next()) return createBook(conn, res);
    }
    catch(SQLException ex)
    {
    }
    return null;
  }
  
  
  public static boolean insert(Book book)
  {
    Connection conn = null;
    try
    {
      conn = Db.getConnection();
      conn.setAutoCommit(false);
      if (Db.execute(conn, "INSERT INTO book (isbn13, isbn10, title, edition, year, pages, copies, catid, pubid, text) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new Param(book.getIsbn13(), Types.VARCHAR), new Param(book.getIsbn10(), Types.VARCHAR), new Param(book.getTitle(), Types.VARCHAR), new Param(book.getEdition(), Types.INTEGER), new Param(book.getYear(), Types.INTEGER), new Param(book.getPages(), Types.INTEGER), new Param(book.getCopies(), Types.INTEGER), new Param(book.getCategory() == null ? null : book.getCategory().getId(), Types.INTEGER), new Param(book.getPublisher() == null ? null : book.getPublisher().getId(), Types.INTEGER), new Param(book.getText(), Types.VARCHAR)))
      {
        int id = Db.getId(conn);
        for (Author a : book.getAuthors()) Db.execute(conn, "INSERT INTO written (bookid, autid) VALUES (?, ?)", new Param(id, Types.INTEGER), new Param(a.getId(), Types.INTEGER));
        book.setId(id);
      }
      conn.commit();
      return true;
    }
    catch(SQLException ex)
    {
      try
      {
        conn.rollback();
      }
      catch (Exception ex2)
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
  
  
  public static boolean update(Book book)
  {
    Connection conn = null;
    try
    {
      conn = Db.getConnection();
      conn.setAutoCommit(false);
      if (Db.execute(conn, "UPDATE book SET isbn13 = ?, isbn10 = ?, title = ?, edition = ?, year = ?, pages = ?, copies = ?, catid = ?, pubid = ?, text = ? WHERE id = ?", new Param(book.getIsbn13(), Types.VARCHAR), new Param(book.getIsbn10(), Types.VARCHAR), new Param(book.getTitle(), Types.VARCHAR), new Param(book.getEdition(), Types.INTEGER), new Param(book.getYear(), Types.INTEGER), new Param(book.getPages(), Types.INTEGER), new Param(book.getCopies(), Types.INTEGER), new Param(book.getCategory() == null ? null : book.getCategory().getId(), Types.INTEGER), new Param(book.getPublisher() == null ? null : book.getPublisher().getId(), Types.INTEGER), new Param(book.getText(), Types.VARCHAR), new Param(book.getId(), Types.INTEGER)))
      {
        Db.execute(conn, "DELETE FROM written WHERE bookid = ?", new Param(book.getId(), Types.INTEGER));
        for (Author a : book.getAuthors()) Db.execute(conn, "INSERT INTO written (bookid, autid) VALUES (?, ?)", new Param(book.getId(), Types.INTEGER), new Param(a.getId(), Types.INTEGER));
        conn.commit();
        return true;
      }
    }
    catch(Exception ex)
    {
      try
      {
        conn.rollback();
      }
      catch (Exception ex2)
      {
      }
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
    return false;
  }

  
  public static boolean delete(int id) 
  {
    Connection conn = null;
    try
    {
      conn = Db.getConnection();
      conn.setAutoCommit(false);
      Db.execute(conn, "DELETE FROM written WHERE bookid = ?", new Param(id, Types.INTEGER));
      Db.execute(conn, "DELETE FROM book WHERE id = ?", new Param(id, Types.INTEGER));
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
  
  // Executes a SQL SELECT on the the table book and returns a list of Book objects
  private static List<Book> select(String sql)
  {
    List<Book> list = new ArrayList();
    try (Connection conn = Db.getConnection())
    {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);
      while(res.next()) list.add(createBook(conn, res));
    }
    catch(SQLException ex)
    {
      list.clear();
    }
    return list;
  }

  // Help method, that for an open connection and a resultset creates a Book object.
  private static Book createBook(Connection conn, ResultSet res) throws SQLException
  {
    Book book = new Book(res.getInt("id"), res.getString("isbn13"), res.getString("isbn10"), res.getString("title"), res.getInt("edition"), res.getInt("year"), res.getInt("pages"), res.getInt("copies"), res.getString("text"));
    int catid = res.getInt("catid");
    if (catid != 0) book.setCategory(CategoryData.getCategory(conn, catid));
    int pubid = res.getInt("pubid");
    if (pubid != 0) book.setPublisher(PublisherData.getPublisher(conn, pubid));
    book.setAuthors(AuthorData.getAuthors(conn, res.getInt("id")));
    return book;
  }
}
