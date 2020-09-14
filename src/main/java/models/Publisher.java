package models;

// Represents a publisher
public class Publisher 
{
  public static final int NAME = 50;
  private int id;         // the publisher's id
  private String name;    // the publisher's name
  
  public Publisher(int id, String name)
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
    if (getClass() == obj.getClass()) return id == ((Publisher)obj).getId();
    return false;
  }

  @Override
  public int hashCode()
  {
    return id;
  }
}