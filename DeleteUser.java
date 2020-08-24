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

public class DeleteUser extends JFrame implements ActionListener
{
    JLabel TittleLabel,userNameLabel,textMessage;
    JTextField userNameTxt;
    JButton deleteUser,back;
    
    String un;
   
    public DeleteUser(String un)
    {
		super("Delete User");
		
        this.un = un;
		
		TittleLabel = new JLabel("Delete User Account");
        TittleLabel.setBounds(170,30,150,30);
        add(TittleLabel);
        
        userNameLabel = new JLabel("UserName :");
        userNameLabel.setBounds(80,70,150,30);
        add(userNameLabel);
        
        userNameTxt = new JTextField();
        userNameTxt.setBounds(240,75,150,20);
        add(userNameTxt);
        
        textMessage = new JLabel(">> Textfield must be filled-up with a valid Username <<");
        textMessage.setBounds(60,120,400,30);
        add(textMessage);
        
        deleteUser = new JButton("Delete User");
        deleteUser.setBounds(260,180,150,30);
        deleteUser.setBackground(Color.white);
        deleteUser.setForeground(Color.black);
        add(deleteUser);
        
        back = new JButton("Back");
        back.setBounds(60,180,150,30);
        back.setBackground(Color.white);
        add(back);
        
        back.addActionListener(this);
        deleteUser.addActionListener(this);
        
        setLayout(null);
        setVisible(true);
        setSize(460,280);
        setResizable(false);
        setLocation(480,220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String u = userNameTxt.getText();
        
        if(ae.getSource()==deleteUser)
        {
            if(u.length()==0)
            {
                JOptionPane.showMessageDialog(null,"You must provide a Valid Username");
            }
            else
            {
                try
                {
                    String query = "DELETE FROM `user_information` WHERE User_Name = "+'"'+u+'"';
                    Connection c = DatabaseConnection.dbConnector();
                    Statement st = c.createStatement();
                    st.executeUpdate(query);

                    JOptionPane.showMessageDialog(null,"User deleted...");
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e);
                    System.out.println("User not deleted for connection problem");
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
