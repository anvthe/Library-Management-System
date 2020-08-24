import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeleteLibrarian extends JFrame implements ActionListener
{
    JLabel TittleLabel,userNameLabel,textMessage;
    JTextField userNameTxt;
    JButton deleteLibrarian,back;
    
    String un;
   
    public DeleteLibrarian(String un)
    {
        
		super("Delete Librarian");
		
		this.un = un;
		
		TittleLabel = new JLabel("Delete Librarian Account");
        TittleLabel.setBounds(170,30,150,30);
        add(TittleLabel);
        
        
        userNameLabel = new JLabel("Librarian UserName :");
        userNameLabel.setBounds(80,70,150,30);
        add(userNameLabel);
        
        userNameTxt = new JTextField();
        userNameTxt.setBounds(240,75,150,20);
        add(userNameTxt);
        
        textMessage = new JLabel(">> Textfield must be filled-up with a valid Librarian Username <<");
        textMessage.setBounds(60,120,400,30);
        add(textMessage);
        
        deleteLibrarian = new JButton("Delete Librarian");
        deleteLibrarian.setBounds(260,180,150,30);
        deleteLibrarian.setBackground(Color.white);
        deleteLibrarian.setForeground(Color.black);
        add(deleteLibrarian);
        
        back = new JButton("Back");
        back.setBounds(60,180,150,30);
        back.setBackground(Color.white);
        add(back);
        
        back.addActionListener(this);
        deleteLibrarian.addActionListener(this);
        
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setSize(460,280);
        setLocation(480,220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String u = userNameTxt.getText();
        
        if(ae.getSource()==deleteLibrarian)
        {
            if(u.length()==0)
            {
                JOptionPane.showMessageDialog(null,"You must provide a Valid Username");
            }
            else
            {
                try
                {
                    String query = "DELETE FROM `librarian_information` WHERE User_Name = "+'"'+u+'"';
                    Connection c = DatabaseConnection.dbConnector();
                    Statement st = c.createStatement();
                    st.executeUpdate(query);

                    JOptionPane.showMessageDialog(null,"Librarian deleted...");
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e);
                    System.out.println("Librarian not deleted for connection problem");
                }
            }
        }
        else if(ae.getSource()==back)
        {
            setVisible(false);
            new AdminView(this.un);
        }
    }
}
