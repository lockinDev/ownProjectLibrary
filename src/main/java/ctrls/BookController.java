package ctrls;

import java.util.*;
import models.*;
import util.*;

public class BookController 
{
  private Book book = null;
  private boolean inserted;
  private String message = null;
  
  public BookController(Book book)
  {
    this.book = book;
    inserted = book == null;
  }

  public void clear()
  {
    book = null;
  }
  
  public boolean isInserted()
  {
    return inserted;
  }
  
  public Book getBook()
  {
    return book;
  }
  
  public String getMessage()
  {
    return message;
  }

  public boolean update(String isbn13, String isbn10, String title, Integer edition, Integer year, String pages, String copies, Publisher publisher, Category category, List<Author> authors, String text)
  {
    isbn13 = isbn13.trim();
    isbn10 = isbn10.trim();
    title = title.trim();
    pages = pages.trim();
    copies = copies.trim();
    text = text.trim();
    message = null;
    if (isbn13.length() == 0) isbn13 = null;
    else if (!Tools.isbn13Ok(isbn13))
    {
      message = "Illegal ISBN-13";
      return false;
    }
    if (isbn10.length() == 0) isbn10 = null;
    else if (!Tools.isbn10Ok(isbn10))
    {
      message = "Illegal ISBN-13";
      return false;
    }
    if (title.length() == 0)
    {
      message = "Illegal title";
      return false;
    }
    if (edition != null && (edition < 1 || edition > 9))
    {
      message = "Illegal edition";
      return false;
    }
    if (year != null && (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR) + 1))
    {
      message = "Illegal publisher year";
      return false;
    }
    Integer page = null;
    if (pages.length() > 0)
    {
      try
      {
        page = Integer.parseInt(pages);
      }
      catch (Exception ex)
      {
        message = "Illegal number of pages";
        return false;
      }
      if (page < 1)
      {
        message = "Illegal number of pages";
        return false;
      }
    }
    Integer copy = null;
    if (copies.length() > 0)
    {
      try
      {
        copy = Integer.parseInt(copies);
      }
      catch (Exception ex)
      {
        message = "Illegal number of copies";
        return false;
      }
      if (copy < 1)
      {
        message = "Illegal number of copies";
        return false;
      }
    }
    if (text.length() == 0) text = null;
    if (book == null)
    {
      Book book = new Book(0, isbn13, isbn10, title, edition, year, page, copy, text, category, publisher);
      book.setAuthors(authors);
      if (!Books.insert(book))
      {
        message = "The book could not be updated";
        return false;
      }
      this.book = book;
      return true;
    }
    book.setIsbn13(isbn13);
    book.setIsbn10(isbn10);
    book.setTitle(title);
    book.setEdition(edition);
    book.setYear(year);
    book.setPages(page);
    book.setCopies(copy);
    book.setText(text);
    book.setPublisher(publisher);
    book.setCategory(category);
    book.setAuthors(authors);
    if (!Books.update(book))
    {
      message = "The book could not be created";
      return false;
    }
    return true;
  }
  
  public boolean remove()
  {
    message = null;
    if (book != null && Books.delete(book.getId())) return true;
    message = "The book could not be deleted";
    return false;
  }
}
