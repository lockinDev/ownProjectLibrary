package models;

import java.util.*;

import dal.*;

// Adapter class for Category
public class Categories 
{

  public static List<Category> getAll()
  {
    return CategoryData.getCategories();
  }
  
  
  public static Category getCategory(int id)
  {
    throw new UnsupportedOperationException();
  }

  
  public static boolean exists(String name)
  {
    return CategoryData.exists(name);
  }

  
  public static Category insert(String name)
  {
    name = Db.cut(name, Category.NAME);
    return CategoryData.insert(name);
  }
  
  
  public static boolean update(Category category)
  {
    category.setName(Db.cut(category.getName(), Category.NAME));
    return CategoryData.update(category);
  }
  
  
  public static boolean delete(int id)
  {
    return CategoryData.delete(id);
  }
}

