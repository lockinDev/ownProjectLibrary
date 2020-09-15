package models;

import java.util.*;

// Represents a user
public class User 
{
  public static final int EMAIL = 100;
  public static final int FIRSTNAME = 50;
  public static final int LASTNAME = 30;
  public static final int ADDRESS = 50;
  public static final int CODE = 4;
  public static final int PHONE = 20;
  private int id;                                  // the user's id
  private String email;                            // the user's email address
  private String firstname;                        // the user's forst name
  private String lastname;                         // the user's last name
  private String address;                          // the user's address
  private String code;                             // the user's zip code
  private String phone;                            // the user's phone number
  private Title title;                             // the user's title
  private int role;                                // tthe user's user role
  private List<Borrow> borrows = new ArrayList();  // the books that this user has borrowed

  public User(int id, String email, String firstname, String lastname, String address, String code, String phone, Title title)
  {
    this(id, email, firstname, lastname, address, code, phone, title, 3);
  }

  public User(int id, String email, String firstname, String lastname, String address, String code, String phone, Title title, int role)
  {
    this.id = id;
    this.email = email;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.code = code;
    this.phone = phone;
    this.title = title;
    this.role = role;
  }
  
  public int getId()
  {
    return id;
  }
  
  public String getEmail()
  {
    return email;
  }
  
  public String getFirstname()
  {
    return firstname;
  }
  
  public String getLastname()
  {
    return lastname;
  }
  
  public String getAddress()
  {
    return address;
  }
  
  public String getCode()
  {
    return code;
  }
  
  public Title getTitle()
  {
    return title;
  }
  
  public String getPhone()
  {
    return phone;
  }

  public int getRole()
  {
    return role;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public void setTitle(Title title)
  {
    this.title = title;
  }
  
  public void setFirstname(String firstname)
  {
    this.firstname = firstname;
  }
  
  public void setLastname(String lastname)
  {
    this.lastname = lastname;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  public void setRole(int role)
  {
    this.role = role;
  }

  @Override
  public String toString()
  {
    return firstname + " " + lastname + ", " + title;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null) return false;
    if (getClass() == obj.getClass()) return id == ((User)obj).id;
    return false;
  }
  
  @Override
  public int hashCode()
  {
    return id;
  }
}
