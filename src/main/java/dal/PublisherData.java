package dal;

import java.sql.*;
import models.*;

public class PublisherData 
{

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
