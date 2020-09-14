package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import views.tables.*;

public class GUI 
{
  public static JTextField createField(int width, int min, int max, int height, float alignment, FilterListener listener)
  {
    JTextField field = new JTextField();
    field.getDocument().addDocumentListener(new Filter(listener));
    field.setAlignmentY(alignment);
    field.setFont(Options.menuFont);
    field.setPreferredSize(new Dimension(width, height));
    field.setMinimumSize(new Dimension(min, height));
    field.setMaximumSize(new Dimension(max, height));
    return field;
  }
  
  public static JLabel createLabel(String text, float alignment)
  {
    JLabel label = new JLabel(text);
    label.setAlignmentY(alignment);
    label.setFont(Options.menuFont);
    return label;
  }
  
  public static JButton createButton(String text, int width, int height, boolean enabled, ActionListener listener)
  {
    JButton cmd = new JButton(text);
    cmd.setFont(Options.menuFont);
    cmd.setMargin(new Insets(0, 0, 0, 0));
    cmd.setPreferredSize(new Dimension(width, height));
    cmd.setEnabled(enabled);
    cmd.addActionListener(listener);
    return cmd;
  }
  
  public static void setWidth(TableColumn col, int width)
  {
    col.setMinWidth(width);
    col.setMaxWidth(width);
  }

  public static void setWidth(TableColumn col, int min, int max)
  {
    col.setMinWidth(min);
    col.setMaxWidth(max);
  }
}
