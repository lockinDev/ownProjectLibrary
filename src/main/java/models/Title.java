package models;

// Represents a user's title
public class Title 
{
  public static final int NAME = 50;
  private int id;         // the title's id
  private String name;    // the title's name
  
  public Title(int id, String name)
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
    if (getClass() == obj.getClass()) return id == ((Title)obj).id;
    return false;
  }
  
  @Override
  public int hashCode()
  {
    return id;
  }
}
