package ctrls;

import models.*;

public class AuthorController 
{
  private Author author = null;
  private String message = null;
  
  public String getMessage()
  {
    return message;
  }
  
  public Author getAuthor()
  {
    return author;
  }
  
  public void setAuthor(Author author)
  {
    this.author = author;
  }

  public boolean exists(String firstname, String lastname)
  {
    return Authors.find(firstname, lastname) != null;
  }
  
  public boolean update(String firstname, String lastname, String text)
  {
    message = null;
    firstname = firstname.trim();
    lastname = lastname.trim();
    text = text.trim();
    if (lastname.length() == 0)
    {
      message = "You must enter the author's last name";
      return false;
    }
    if (firstname.length() == 0) firstname = null;
    if (text.length() == 0) text = null;
    if (author != null)
    {
      author.setFirstname(firstname);
      author.setLastname(lastname);
      author.setText(text);
      if (!Authors.update(author))
      {
        message = "The author could not be updated";
        return false;
      }
    }
    else
    {
      if ((author = Authors.insert(firstname, lastname, text)) == null)
      {
        message = "The author could not be created";
        return false;
      }
    }
    return true;
  }
  
  public boolean delete()
  {
    message = null;
    if (author == null || !Authors.delete(author.getId()))
    {
      message = "The author could not be deleted";
      return false;
    }
    return true;
  }
}