
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class UserView extends JFrame implements ActionListener
{
    String un;
    
    JButton profile,viewBorrowHistory,logout;
	JLabel TittleLabel;
    
    public UserView(String un)
    {
        super("User Page");
        this.un = un;
		
		TittleLabel = new JLabel("Admin View Page");
        TittleLabel.setBounds(180,40,150,30);
        add(TittleLabel);
        
        
        profile = new JButton("Profile");
        profile.setBounds(155,90,150,30);
        profile.setBackground(Color.white);
        profile.setForeground(Color.black);
        add(profile);
        
        viewBorrowHistory = new JButton("View Borrow History");
        viewBorrowHistory.setBounds(155,140,150,30);
        viewBorrowHistory.setBackground(Color.white);
        viewBorrowHistory.setForeground(Color.black);
        add(viewBorrowHistory);
        
        logout = new JButton("Logout");
        logout.setBounds(350,10,80,25);
        logout.setBackground(Color.red);
        logout.setForeground(Color.white);
        add(logout);
        
        profile.addActionListener(this);
        viewBorrowHistory.addActionListener(this);
        logout.addActionListener(this);
        
        setLayout(null);
        setVisible(true);
        setSize(450,270);
        setResizable(false);
        setLocation(480,240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==profile)
        {
            setVisible(false);
            UserProfile up = new UserProfile(this.un);
            try
            {
                String query = "Select * from user_information where User_Name = "+'"'+this.un+'"';
                Connection c = DatabaseConnection.dbConnector();
		        Statement st = c.createStatement();
		        ResultSet r = st.executeQuery(query);
                
                while(r.next())
                {
                    up.firstNameTxt.setText(r.getString("First_Name"));
                    up.lastNameTxt.setText(r.getString("Last_Name"));
                    up.userNameTxt.setText(r.getString("User_Name"));
                    up.passwordTxt.setText(r.getString("Password"));
                    up.confirmPasswordTxt.setText(r.getString("Confirm_Password"));
                    up.emailIdTxt.setText(r.getString("Email_ID"));
                    up.contactNoTxt.setText(r.getString("Contact_No"));
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
        else if(ae.getSource()==viewBorrowHistory)
        {
            setVisible(false);
            new ViewBorrowHistory(this.un);
        }
        else if(ae.getSource()==logout)
        {
            this.setVisible(false);
            new LibraryManagementSystem();
        }
    }
}
