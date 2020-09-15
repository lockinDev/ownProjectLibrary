package dal;

import java.util.*;
import java.sql.*;

public class Zipcodes 
{
  private static Zipcodes instance = null;
 
  private List<String> list = new ArrayList();
  
  private Zipcodes()
  {
    try (Connection conn = Db.getConnection(); Statement stmt = conn.createStatement())
    {
      ResultSet res = stmt.executeQuery("SELECT code FROM zipcode");
      while(res.next()) list.add(res.getString("code"));
    }
    catch(SQLException ex)
    {
    }
  }

  public static Zipcodes getInstance() 
  {
		if (instance == null) 
    {
			synchronized (Zipcodes.class) 
      {
				if (instance == null) instance = new Zipcodes();
			}
		}
		return instance;
	}

  public boolean found(String code)
  {
    return list.contains(code);
  }
}
