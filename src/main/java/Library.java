
import javax.swing.SwingUtilities;
import views.MainView;

public class Library 
{
  public static void main(String[] args) 
  {
    SwingUtilities.invokeLater(() -> new MainView());
  }
}
