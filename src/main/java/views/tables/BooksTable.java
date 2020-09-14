package views.tables;

import java.util.*;
import javax.swing.table.*;

import models.*;

/*
* Model class for this JTabel, that MainView uses for an overview over all books.
*/
public class BooksTable extends AbstractTableModel
{
  private String[] colNavne = { "", "Title", "Year", "Publisher", "Category" };
  private List<Book> list = Books.getBooks();

  @Override
  public int getColumnCount() 
  {
    return colNavne.length;
  }

  @Override
  public int getRowCount() 
  {
    return list.size();
  }

  @Override
  public String getColumnName(int col) 
  {
    return colNavne[col];
  }

  @Override
  public Object getValueAt(int row, int col) 
  {
    Book book = list.get(row);
    switch (col)
    {
      case 0: return book.getId();
      case 1: return book.getTitle();
      case 2: return book.getYear();
      case 3: return book.getPublisher() == null ? null : book.getPublisher().getName();
      case 4: return book.getCategory() == null ? null : book.getCategory().getName();
      default: return null;
    }
  }

  @Override
  public Class getColumnClass(int col) 
  {
    return col == 0 || col == 2 ? Integer.class : String.class;
  }

  @Override
  public boolean isCellEditable(int row, int col) 
  {
    return false;
  }
}
