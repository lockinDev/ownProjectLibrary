package views;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

import views.tables.*;

public class MainView extends JFrame implements FilterListener
{
  private JTable table;
  private BooksTable model;
  private TableRowSorter<BooksTable> sorter;
  private JTextField txtTitle;
  private JTextField txtYear;
  private JTextField txtPublisher;
  private JTextField txtCategory;
  private JMenu userMenu;
  private JMenu stafMenu;
  private JMenu adminMenu;
  
  public MainView()
  {
    super("Library");
    setSize(1400, 800);
    setLocationRelativeTo(null);
    createView();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  private void createView()
  {
    setJMenuBar(createMenubar());
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));
    model = new BooksTable();
    sorter = new TableRowSorter(model);
    table = new JTable(model);
    table.setRowSorter(sorter);
    table.setDefaultRenderer(String.class, new StringRenderer());
    table.setDefaultRenderer(Integer.class, new IntegerRenderer());
    table.setRowHeight(25);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setRowSelectionAllowed(true);
    table.setColumnSelectionAllowed(false);
    table.setEnabled(false);
    GUI.setWidth(table.getColumnModel().getColumn(0), 0);
    GUI.setWidth(table.getColumnModel().getColumn(2), 60);
    GUI.setWidth(table.getColumnModel().getColumn(3), 300, 500);
    GUI.setWidth(table.getColumnModel().getColumn(4), 300, 500);
    table.getTableHeader().setFont(Options.headFont);
    table.setEnabled(true);
    panel.add(new JScrollPane(table));
    panel.add(createBottom(), BorderLayout.SOUTH);
    add(panel);
  }

  private JPanel createBottom()
  {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    panel.setBorder(new EmptyBorder(20, 0, 10, 0));
    panel.add(GUI.createLabel("Title:", Component.CENTER_ALIGNMENT));
    panel.add(Box.createRigidArea(new Dimension(10, 0)));
    panel.add(txtTitle = GUI.createField(300, 200, 500, 25, Component.CENTER_ALIGNMENT, this));
    panel.add(Box.createRigidArea(new Dimension(20, 0)));
    panel.add(GUI.createLabel("Year:", Component.CENTER_ALIGNMENT));
    panel.add(Box.createRigidArea(new Dimension(10, 0)));
    panel.add(txtYear = GUI.createField(50, 50, 50, 25, Component.CENTER_ALIGNMENT, this));
    panel.add(Box.createRigidArea(new Dimension(20, 0)));
    panel.add(GUI.createLabel("Publisher:", Component.CENTER_ALIGNMENT));
    panel.add(Box.createRigidArea(new Dimension(10, 0)));
    panel.add(txtPublisher = GUI.createField(200, 100, 300, 25, Component.CENTER_ALIGNMENT, this));
    panel.add(Box.createRigidArea(new Dimension(20, 0)));
    panel.add(GUI.createLabel("Category:", Component.CENTER_ALIGNMENT));
    panel.add(Box.createRigidArea(new Dimension(10, 0)));
    panel.add(txtCategory = GUI.createField(200, 100, 300, 25, Component.CENTER_ALIGNMENT, this));
    panel.add(Box.createRigidArea(new Dimension(10, 0)));
    panel.add(GUI.createButton("Clear", 70, 25, true, this::clear));
    return panel;
  }
  
  private JMenuBar createMenubar()
  {
    JMenuBar bar = new JMenuBar();
    bar.add(createFunc());
    bar.add(userMenu = createUsers());
    bar.add(stafMenu = createBibliotekar());
    bar.add(adminMenu = createAdmin());
    return bar;
  }
  
  private JMenu createFunc()
  {
    JMenu menu = new JMenu("General");
    menu.setFont(Options.menuFont);
    menu.add(createItem("Log in", null));
    menu.addSeparator();
    menu.add(createItem("Exit", e -> System.exit(0)));
    return menu;
  }
  
  private JMenu createUsers()
  {
    JMenu menu = new JMenu("Users");
    menu.setFont(Options.menuFont);
    menu.add(createItem("Search", null));
    menu.add(createItem("New password", null));
    menu.addSeparator();
    menu.add(createItem("Log out", null));
    return menu;
  }
  
  private JMenu createBibliotekar()
  {
    JMenu menu = new JMenu("Librarians");
    menu.setFont(Options.menuFont);
    menu.add(createItem("Maintenance of publishers", null));
    menu.add(createItem("Maintenance of categories", null));
    menu.add(createItem("Maintenance of authorrs", null));
    menu.add(createItem("Create book", null));
    return menu;
  }
  
  private JMenu createAdmin()
  {
    JMenu menu = new JMenu("Administrator");
    menu.setFont(Options.menuFont);
    menu.add(createItem("Reccall", null));
    menu.add(createItem("Maintaining users", null));
    return menu;
  }
  
  private JMenuItem createItem(String text, ActionListener listener)
  {
    JMenuItem item = new JMenuItem(text);
    item.addActionListener(listener);
    item.setFont(Options.menuFont);
    return item;
  }

  private void clear(ActionEvent e)
  {
    txtTitle.setText("");
    txtYear.setText("");
    txtPublisher.setText("");
    txtCategory.setText("");
  }
  
  public void createFilter() 
  {
    try 
    {
      java.util.List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
      filters.add(RowFilter.regexFilter(txtTitle.getText(), 1));
      filters.add(RowFilter.regexFilter("^" + txtYear.getText(), 2));
      filters.add(RowFilter.regexFilter(txtPublisher.getText(), 3));
      filters.add(RowFilter.regexFilter(txtCategory.getText(), 4));
      sorter.setRowFilter(RowFilter.andFilter(filters));
    } 
    catch (java.util.regex.PatternSyntaxException ex) 
    {
    }
  }
}
