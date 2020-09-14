package views.tables;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import views.*;

public class StringRenderer extends JLabel implements TableCellRenderer 
{
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
  {
    setText(value == null ? null : value.toString());
    setFont(Options.defaFont);
    setBorder(new EmptyBorder(0, 5, 0, 5));
    return this;
  }
}
