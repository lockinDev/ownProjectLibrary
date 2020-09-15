package models;

import java.util.*;

import dal.*;

// Adapter class for author's
public class Authors 
{

  public static List<Author> getAll()
  {
    return AuthorData.getAuthors();
  }
  

  public static Author getAuthor(int id)
  {
    return AuthorData.getAuthor(id);
  }


  public static List<Author> find(String firstname, String lastname)
  {
    return AuthorData.getAuthor(firstname, lastname);
  }
  

  public static Author insert(String firstname, String lastname, String text)
  {
    if (firstname != null) firstname = Db.cut(firstname, Author.FIRSTNAME);
    if (lastname != null) lastname = Db.cut(lastname, Author.LASTNAME);
    if (text != null) text = Db.cut(text, Author.TEXT);
    return AuthorData.insert(firstname, lastname, text);
  }


  public static boolean update(Author author)
  {
    if (author.getFirstname() != null) author.setFirstname(Db.cut(author.getFirstname(), Author.FIRSTNAME));
    if (author.getLastname() != null) author.setLastname(Db.cut(author.getLastname(), Author.LASTNAME));
    if (author.getText() != null) author.setText(Db.cut(author.getText(), Author.TEXT));
    return AuthorData.update(author);
  }


  public static boolean delete(int id)
  {
    return AuthorData.delete(id);
  }
}
