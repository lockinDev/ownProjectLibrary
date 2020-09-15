package ctrls;

import models.*;
import util.*;

public class LoginController 
{
  private String message = null;
  private User user = null;
  
  public String getMessage()
  {
    return message;
  }

  public User getUser()
  {
    return user;
  }
  
  public boolean logon(String usr, char[] pwd)
  {
    try
    {
      message = null;
      user = Users.getUser(usr, Tools.encrypt(pwd));
      if (user == null)
      {
        message = "Illegal user name or password";
        return false;
      }
      CurrentUser.getInstance().setUser(user);
      return true;
    }
    catch (Exception ex)
    {
      message = ex.toString();
      return false;
    }
  }
}
