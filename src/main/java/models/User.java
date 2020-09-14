package models;

import java.util.*;

// Represents an user
public class User 
{
  public static final int EMAIL = 100;
  public static final int FIRSTNAME = 50;
  public static final int LASTNAME = 30;
  public static final int ADDRESS = 50;
  private int id;                                 // the user's id
  private String email;                           // the user's email address
  private String firstname;                       // the user's forst name
  private String lastname;                        // the user's last name
  private String address;                         // the user's address
  private String code;                            // the user's zip code
  private String phone;                           // the user's phone number
  private Title title;                            // the user's title
  private int role;                               // tthe user's user role
  private List<Borrow> lends = new ArrayList();   // the books that this user has borrowed
}
