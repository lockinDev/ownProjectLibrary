package models;

// Represtents an author
public class Author 
{
  public static final int FIRSTNAME = 50;
  public static final int LASTNAME = 30;
  public static final int TEXT = 200;
  private int id;               // the author's id
  private String firstname;     // the author's first name
  private String lastname;      // the author's last name
  private String text;         
  
  public Author(int id, String firstname, String lastname)
  {
    this(id, firstname, lastname, null);
  }
  
  public Author(int id, String firstname, String lastname, String text)
  {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.text = text;
  }
  
  public int getId()
  {
    return id;
  }
  
  public String getFirstname()
  {
    return firstname;
  }
  
  public String getLastname()
  {
    return lastname;
  }
  
  public String getText()
  {
    return text;
  }
  
  public void setFirstname(String firstname)
  {
    this.firstname = firstname;
  }
  
  public void setLastname(String lastname)
  {
    this.lastname = lastname;
  }
  
  public void setText(String text)
  {
    this.text = text;
  }
  
  @Override
  public String toString()
  {
    return firstname + " " + lastname;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == null) return false;
    if (getClass() == obj.getClass()) return id == ((Author)obj).id;
    return false;
  }
  
  @Override
  public int hashCode()
  {
    return id;
  }
}
