package dal;

import java.util.*;
import java.sql.*;
import models.*;

public class AuthorData 
{
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
