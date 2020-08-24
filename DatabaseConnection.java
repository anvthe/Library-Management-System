import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection
{
    Connection con = null;
    public static Connection dbConnector()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarygo2", "root", "");
            return con;
        }
        
        catch(ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Cannot connect to database...");
            System.out.println("Try again");
            
            return null;
        }
    }
}