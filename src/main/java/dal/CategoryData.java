package dal;

import java.sql.*;
import models.*;

public class CategoryData 
{
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
