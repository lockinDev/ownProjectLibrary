package views.tables;

import javax.swing.event.*;

public class Filter implements DocumentListener
{
  private final FilterListener listener;

  public Filter(FilterListener listener)
  {
    this.listener = listener;
  }

  @Override
  public void changedUpdate(DocumentEvent e) 
  {
    listener.createFilter();
  }

  @Override
  public void insertUpdate(DocumentEvent e) 
  {
    listener.createFilter();
  }

  @Override
  public void removeUpdate(DocumentEvent e) 
  {
    listener.createFilter();
  }
}
