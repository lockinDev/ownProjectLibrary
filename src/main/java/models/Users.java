package models;

import java.util.*;

// Adapter class for User
public class Users 
{
  /**
   * Returns all users.
   * @return All users
   */
  public List<User> getAll()
  {
    throw new UnsupportedOperationException();
  }
  

  public static User getUser(String email, String passwd)
  {
    throw new UnsupportedOperationException();
  }


  public User getUser(int id)
  {
    throw new UnsupportedOperationException();
  }


  public boolean insert(User user, String passwd)
  {
    throw new UnsupportedOperationException();
  }

  
  public boolean update(User user)
  {
    throw new UnsupportedOperationException();
  }

  
  public boolean update(User user, String passwd)
  {
    throw new UnsupportedOperationException();
  }
  

  public boolean delete(int id)
  {
    throw new UnsupportedOperationException();
  }


  public boolean exists(String email)
  {
    throw new UnsupportedOperationException();
  }


  public boolean changePassword(String email, String passwd)
  {
    throw new UnsupportedOperationException();
  }
}
