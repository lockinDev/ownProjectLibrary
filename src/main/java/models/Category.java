package models;

// Represents a category
public class Category 
{
  public static final int NAME = 50;
  private int id;         // the cattegory's id
  private String name;    // the category's name
  
  
  public Category(int id, String name)
  {
    this.id = id;
    this.name = name;
  }
  
  public int getId()
  {
    return id;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  @Override
  public String toString()
  {
    return name;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == null) return false;
    if (getClass() == obj.getClass()) return id == ((Category)obj).id;
    return false;
  }

  @Override
  public int hashCode()
  {
    return id;
  }
}