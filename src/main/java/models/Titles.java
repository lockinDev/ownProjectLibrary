package models;

import java.util.*;

import dal.*;

// Adapter class  for Title
public class Titles 
{
  
  public static List<Title> getAll()
  {
    return TitleData.getTitles();
  }
  
  
  public static Title getTitle(int id)
  {
    throw new UnsupportedOperationException();
  }

    
  public static boolean exists(String name)
  {
    return TitleData.exists(name);
  }

  
  public static Title insert(String name)
  {
    name = Db.cut(name, Title.NAME);
    return TitleData.insert(name);
  }
  
  
  public static boolean update(Title title)
  {
    title.setName(Db.cut(title.getName(), Title.NAME));
    return TitleData.update(title);
  }
  
  
  public static boolean delete(int id)
  {
    return TitleData.delete(id);
  }
  
  
  public static Title createAdmin()
  {
    return TitleData.createAdmin();
  }
}
