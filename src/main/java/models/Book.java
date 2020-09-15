package models;

import java.util.*;

// Represents a book
public class Book 
{
  public static final int ISBN13 = 17;
  public static final int ISBN10 = 13;
  public static final int TITLE = 255;
  private int id;                                     // the book's id
  private String isbn13;                              // the book's ISBN-13
  private String isbn10;                              // the book's ISBN-10
  private String title;                               // the book's title
  private Integer edition;                            // the book's edition
  private Integer year;                               // the book's publisher year
  private Integer pages;                              // the book's number og pages
  private Integer copies;                             // number of copies og this book
  private String text;                                // a description of the book
  private Category category;                          // the book's category
  private Publisher publisher;                        // the book's publisher
  private List<Author> authors = new ArrayList();     // the book's authors
  
  public Book(int id, String title)
  {
    this(id, null, null, title, 0, 0, 0, 0, null, null, null);
  }
  
  public Book(int id, String isbn13, String isbn10, String title, Integer edition, Integer year, Integer pages, Integer copies, String text)
  {
    this(id, isbn13, isbn10, title, edition, year, pages, copies, text, null, null);
  }
  
  public Book(int id, String isbn13, String isbn10, String title, Integer edition, Integer year, Integer pages, Integer copies, String text, Category category, Publisher publisher)
  {
    this.id = id;
    this.isbn13 = isbn13;
    this.isbn10 = isbn10;
    this.title = title;
    this.edition = edition;
    this.year = year;
    this.pages = pages;
    this.copies = copies;
    this.text = text;
    this.category = category;
    this.publisher = publisher;
  }
  
  public int getId()
  {
    return id;
  }
  
  public String getIsbn13()
  {
    return isbn13;
  }
  
  public String getIsbn10()
  {
    return isbn10;
  }
  
  public String getTitle()
  {
    return title;
  }
  
  public String getText()
  {
    return text;
  }
  
  public Integer getEdition()
  {
    return edition;
  }
  
  public Integer getYear()
  {
    return year;
  }
  
  public Integer getPages()
  {
    return pages;
  }
  
  public Integer getCopies()
  {
    return copies;
  }
  
  public Publisher getPublisher()
  {
    return publisher;
  }
  
  public Category getCategory()
  {
    return category;
  }
  
  public List<Author> getAuthors()
  {
    return authors;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public void setIsbn13(String isbn13)
  {
    this.isbn13 = isbn13;
  }
  
  public void setIsbn10(String isbn10)
  {
    this.isbn10 = isbn10;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public void setText(String text)
  {
    this.text = text;
  }
  
  public void setEdition(Integer edition)
  {
    this.edition = edition;
  }
  
  public void setYear(Integer year)
  {
    this.year = year;
  }
  
  public void setPages(Integer pages)
  {
    this.pages = pages;
  }
  
  public void setCopies(Integer copies)
  {
    this.copies = copies;
  }
  
  public void setPublisher(Publisher publisher)
  {
    this.publisher = publisher;
  }
  
  public void setCategory(Category category)
  {
    this.category = category;
  }
  
  public void setAuthors(List<Author> list)
  {
    authors = list;
  }
  
  @Override
  public String toString()
  {
    return title;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == null) return false;
    if (getClass() == obj.getClass()) return id == ((Book)obj).id;
    return false;
  }

  @Override
  public int hashCode()
  {
    return id;
  }
}
