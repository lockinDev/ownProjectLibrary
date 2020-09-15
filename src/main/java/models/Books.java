package models;

import java.util.*;
import dal.*;

// Adapter class for Book
public class Books 
{

  public static List<Book> getBooks()
  {
    return BookData.getAll();
  }

  
  public List<Book> search(String isbn13, String isbn10, String title, Integer edition, Integer year, String text, String author, Publisher publisher, Category category)
  {
    throw new UnsupportedOperationException();
  }

  
  public Book getBook(int id)
  {
    return BookData.getBook(id);
  }

  public static boolean insert(Book book)
  {
    trim(book);
    return BookData.insert(book);
  }


  public static boolean update(Book book)
  {
    trim(book);
    return BookData.update(book);
  }

  public static boolean delete(int id) 
  {
    return BookData.delete(id);
  }
  
  private static void trim(Book book)
  {
    book.setTitle(Db.cut(book.getTitle(), 255));
  }
}
