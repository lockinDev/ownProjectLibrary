package ctrls;

/*
* Controller to CategoryView
*/

import models.*;

public class CategoryController 
{
  private Category category = null;
  private String message = null;
  
  public String getMessage()
  {
    return message;
  }
  
  public Category getCategory()
  {
    return category;
  }
  
  public void setCategory(Category category)
  {
    this.category = category;
  }
  
  public boolean update(String name)
  {
    message = null;
    name = name.trim();
    if (name.length() == 0)
    {
      message = "You must enter the category's name";
      return false;
    }
    if (category == null && Categories.exists(name))
    {
      message = "There is already a category with this name";
      return false;
    }
    if (category != null)
    {
      category.setName(name);
      if (!Categories.update(category))
      {
        message = "The category could not be updated";
        return false;
      }
    }
    else
    {
      if ((category = Categories.insert(name)) == null)
      {
        message = "The category could not be created";
        return false;
      }
    }
    return true;
  }
  
  public boolean delete()
  {
    message = null;
    if (category == null || !Categories.delete(category.getId()))
    {
      message = "The category could not be deleted";
      return false;
    }
    return true;
  }
}