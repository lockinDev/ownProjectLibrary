package models;

public class CurrentUser 
{
  private static CurrentUser instance = null;
  
  private User user = null;
  
  private CurrentUser()
  {
  }

  public static CurrentUser getInstance() 
  {
		if (instance == null) 
    {
			synchronized (CurrentUser.class) 
      {
				if (instance == null) instance = new CurrentUser();
			}
		}
		return instance;
	}
  
  public User getUser()
  {
    return user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }

  public boolean isGuest()
  {
    return user == null;
  }
  
  public boolean isStudent()
  {
    return user != null && user.getRole() == 3;
  }
  
  public boolean isStaf()
  {
    return user != null && user.getRole() == 2;
  }
  
  public boolean isAdmin()
  {
    return user != null && (user.getRole() == 0 || user.getRole() == 1);
  }
}
