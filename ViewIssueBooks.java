import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ViewIssueBooks extends JFrame implements ActionListener
{
    JLabel userNameLabel,bookId,borrowDate,ReturnDate;
    JTextField userNameTxt,bookIdTxt,borrowDateTxt,returnDateTxt;
    JButton show,back;
    
    String un;
    
    public ViewIssueBooks(String un)
    {
        super("Issued Book Check");
        
        this.un = un;
        
        userNameLabel = new JLabel("Username: ");
        userNameLabel.setBounds(100,35,100,30);
        add(userNameLabel);
        
        userNameTxt = new JTextField();
        userNameTxt.setBounds(210,40,150,20);
        add(userNameTxt);
        
        bookId = new JLabel("Book Id: ");
        bookId.setBounds(100,70,100,30);
        add(bookId);
        
        bookIdTxt = new JTextField();
        bookIdTxt.setBounds(210,75,150,20);
        add(bookIdTxt);
        
        borrowDate = new JLabel("Borrow Date:");
        borrowDate.setBounds(100,100,100,30);
        add(borrowDate);
        
        borrowDateTxt = new JTextField();
        borrowDateTxt.setBounds(210,105,150,20);
        add(borrowDateTxt);
        
        ReturnDate = new JLabel("Return Date:");
        ReturnDate.setBounds(100,140,150,10);
        add(ReturnDate);
        
        returnDateTxt = new JTextField();
        returnDateTxt.setBounds(210,135,150,20);
        add(returnDateTxt);
                
        show = new JButton("Check");
        show.setBounds(260,200,100,30);
        show.setBackground(Color.white);
        show.setForeground(Color.black);
        add(show);
        
        back = new JButton("Back");
        back.setBounds(100,200,100,30);
        back.setBackground(Color.white);
        back.setForeground(Color.black);
        add(back);
        
        back.addActionListener(this);
        show.addActionListener(this);
        
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setSize(450,320);
        setLocation(480,220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String s = userNameTxt.getText();
        
        if(ae.getSource()==show)
        {
            if(s.length()==0)
            {
                JOptionPane.showMessageDialog(null,"User name field is empty");
            }
            else
            {
                try
                {
                    String query = "SELECT * FROM `issue_books` WHERE User_Name = "+'"'+s+'"';
                    Connection c = DatabaseConnection.dbConnector();
                    Statement st = c.createStatement();
                    ResultSet r = st.executeQuery(query);
                    
                    int count = 0;
                    while(r.next())
                    {
                         count = count + 1;
                         if(count>=1)
                         {
                            bookIdTxt.setText(r.getString("Book_ID"));
                            borrowDateTxt.setText(r.getString("Borrow_Date"));
                            returnDateTxt.setText(r.getString("Return_Date"));
                        }
                    }
                    if(count<1)
                    {
                        System.out.println("Record not found");
                    } 
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error!!!");
                }
            }
            
        }
        else if(ae.getSource()==back)
        {
            setVisible(false);
            new LibrarianView(this.un);
        }
    }
}