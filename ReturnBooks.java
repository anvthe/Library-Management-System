import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ReturnBooks extends JFrame implements ActionListener
{
    JLabel userNameLabel,bookId;
    JTextField userNameTxt,bookIdTxt;
    JButton returnBook,back;
    
    String un;
    
    public ReturnBooks(String un)
    {
        super("Return Books");
        
        this.un = un;
        
        userNameLabel = new JLabel("Username: ");
        userNameLabel.setBounds(110,50,150,30);
        add(userNameLabel);
        
        userNameTxt = new JTextField();
        userNameTxt.setBounds(200,55,150,20);
        add(userNameTxt);
        
        bookId = new JLabel("Book Id: ");
        bookId.setBounds(115,80,150,30);
        add(bookId);
        
        bookIdTxt = new JTextField();
        bookIdTxt.setBounds(200,85,150,20);
        add(bookIdTxt);
        
        returnBook = new JButton("Return");
        returnBook.setBounds(250,150,100,30);
        returnBook.setBackground(Color.white);
        returnBook.setForeground(Color.black);
        add(returnBook);
        
        back = new JButton("Back");
        back.setBounds(100,150,100,30);
        back.setBackground(Color.white);
        back.setForeground(Color.black);
        add(back);
        
        back.addActionListener(this);
        returnBook.addActionListener(this);
        
        setLayout(null);
        setVisible(true);
        setSize(450,280);
        setResizable(false);
        setLocation(480,220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String s1 = userNameTxt.getText();
        String s2 = bookIdTxt.getText();
        String s3 = new String();
        String s4 = new String();
        String availabeCopy = new String();
        String availabeCopyAfter = new String();
        
        if(ae.getSource()==returnBook)
        {
            try
            {
                String query = "SELECT `Available_Copy` FROM `bookshelf` WHERE Book_ID = "+'"'+s2+'"';
                Connection c = DatabaseConnection.dbConnector();
                Statement st = c.createStatement();
                ResultSet r = st.executeQuery(query);
                
                while(r.next())
                {
                    availabeCopy = r.getString("Available_Copy");
                }
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
                System.out.println("Book not copy availabe");
            }
            
            try
            {
                availabeCopyAfter = availabeCopy + "+1";
                String query = "UPDATE `bookshelf` SET `Available_Copy`= "+availabeCopyAfter+" WHERE Book_ID = "+'"'+s2+'"';
                Connection c = DatabaseConnection.dbConnector();
                Statement st = c.createStatement();
                st.executeUpdate(query);
                
                System.out.println("Available copy effected");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
                System.out.println("Available copy not effected");
            }
            
            try
            {
                String query = "DELETE FROM `issue_books` WHERE Book_ID = "+'"'+s2+'"';
                Connection c = DatabaseConnection.dbConnector();
                Statement st = c.createStatement();
                st.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Book Returned");
                System.out.println("Book Returned");
            }
            catch(SQLException | HeadlessException e)
            {
                JOptionPane.showMessageDialog(null,e);
                System.out.println("Book not Returned");
            }
        }
        else if(ae.getSource()==back)
        {
            setVisible(false);
            new LibrarianView(this.un);
        }
    }
}
