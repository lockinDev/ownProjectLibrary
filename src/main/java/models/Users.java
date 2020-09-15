package models;

import java.util.*;

import dal.*;

// Adapter class for User
public class Users 
{
  
  public static boolean adminCreated()
  {
    return UserData.adminCreated();
  }

  
  public static User getAdmin(String passwd)
  {
    return UserData.getAdmin(passwd);
  }
  
  
  public static User getUser(String email, String passwd)
  {
    return UserData.getUser(email, passwd);
  }

  
  public static User getUser(int id)
  {
    return UserData.getUser(id);
  }
  
  
  public static List<User> getAll()
  {
    return UserData.getUsers();
  }

  
  public static User insert(User user, String passwd)
  {
    trim(user);
    return UserData.insert(user, passwd);
  }

  
  public static boolean update(User user)
  {
    trim(user);
    return UserData.update(user);
  }

  
  public static boolean update(User user, String passwd)
  {
    trim(user);
    return UserData.update(user, passwd);
  }
  
  
  public static boolean delete(int id)
  {
    return UserData.delete(id);
  }

  
  public static boolean exists(String email)
  {
    return UserData.getUser(email) != null;
  }

  
  public static boolean changePassword(String email, String passwd)
  {
    return UserData.changePassword(email, passwd);
  }

  
  public static boolean found(String code)
  {
    return Zipcodes.getInstance().found(code);
  }

  
  public static String getRole(int role)
  {
    if (role == 1) return "Admin";
    else if (role == 2) return "Staf";
    else if (role == 3) return "Student";
    else return null;
  }
  
  
  public static int getRole(String name)
  {
    if (name.equals("Admin")) return 1;
    if (name.equals("Staf")) return 2;
    if (name.equals("Student")) return 3;
    return 0;
  }

  private static void trim(User user)
  {
    user.setEmail(Db.cut(user.getEmail(), User.EMAIL));
    user.setFirstname(Db.cut(user.getFirstname(), User.FIRSTNAME));
    user.setLastname(Db.cut(user.getLastname(), User.LASTNAME));
    user.setAddress(Db.cut(user.getAddress(), User.ADDRESS));
    user.setCode(Db.cut(user.getCode(), User.CODE));
    user.setPhone(Db.cut(user.getPhone(), User.PHONE));
  }
}
