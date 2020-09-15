package models;

import java.util.*;

// Represents a borrow of a book
public class Borrow 
{
  private Calendar date;    // the date for the borrow
  private Book book;        // the book that has been borrowed

  public Borrow(Book book, Calendar date)
  {
    this.book = book;
    this.date = date;
  }
  
  public Book getBook()
  {
    return book;
  }
  
  public Calendar getDate()
  {
    return date;
  }
}
