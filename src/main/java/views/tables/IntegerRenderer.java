package views.tables;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import views.*;

public class IntegerRenderer extends JLabel implements TableCellRenderer 
{
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
  {
    setText(value == null || (Integer)value == 0 ? null : value.toString());
    setFont(Options.defaFont);
    setHorizontalAlignment(JLabel.RIGHT);
    setBorder(new EmptyBorder(0, 5, 0, 5));
    return this;
  }
}
