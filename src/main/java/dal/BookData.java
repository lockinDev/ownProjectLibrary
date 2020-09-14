package dal;

import java.util.*;
import java.sql.*;
import models.*;

public class BookData 
{
  /**
   * @return All books in the table book order by title
   */
  public static List<Book> getAll()
  {
    return select("SELECT * FROM book ORDER BY title");
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
