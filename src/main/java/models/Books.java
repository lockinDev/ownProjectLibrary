package models;

import java.util.*;
import dal.*;

// Adapter class for Book
public class Books 
{
  /**
   * Returns all books.
   * @return All books
   */
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
    throw new UnsupportedOperationException();
  }


  public static boolean insert(Book book)
  {
    throw new UnsupportedOperationException();
  }


  public static boolean update(Book book)
  {
    throw new UnsupportedOperationException();
  }


  public static boolean delete(int id) 
  {
    throw new UnsupportedOperationException();
  }
}
