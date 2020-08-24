import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LibrarianProfile extends JFrame implements ActionListener
{
    JLabel TittleLabel,firstName,lastName,userName,password,confirmPassword,emailId,contactNo;
    JTextField firstNameTxt,lastNameTxt,userNameTxt,passwordTxt,confirmPasswordTxt,emailIdTxt,contactNoTxt;
    JButton ok,update;
    
    String un;
    
    public LibrarianProfile(String un)
    {
        super("Librarian Profile");
        
        this.un = un;
		
    TittleLabel = new JLabel("Librarian Information");
    TittleLabel.setBounds(170,40,150,30);
    add(TittleLabel);
            
        
	         
	firstName = new JLabel("First Name:");
	firstName.setBounds(80,100,80,20);
	add(firstName);
        
	firstNameTxt=new JTextField();
	firstNameTxt.setBounds(200,100,150,20);
	firstNameTxt.setBackground(Color.white);
	add(firstNameTxt);
        
    lastName = new JLabel("Last Name:");
	lastName.setBounds(80,130,80,20);
	add(lastName);
		
	lastNameTxt = new JTextField();
	lastNameTxt.setBounds(200,130,150,20);
	lastNameTxt.setBackground(Color.white);
	add(lastNameTxt);	
		
	userName = new JLabel("User Name:");
	userName.setBounds(80,160,150,20);
	add(userName);
		
	userNameTxt=new JTextField();
	userNameTxt.setBounds(200,160,150,20);
	userNameTxt.setBackground(Color.white);
	add(userNameTxt);
		
	password = new JLabel("Passward:");
	password.setBounds(80,190,80,20);
	add(password);
		
	passwordTxt = new JTextField();
	passwordTxt.setBounds(200,190,150,20);
	passwordTxt.setBackground(Color.white);
	add(passwordTxt);
		
	confirmPassword = new JLabel("Confirm Passward:");
	confirmPassword.setBounds(80,220,110,20);
	add(confirmPassword);
		
	confirmPasswordTxt=new JTextField();
	confirmPasswordTxt.setBounds(200,220,150,20);
	confirmPasswordTxt.setBackground(Color.white);
	add(confirmPasswordTxt);
		
	emailId=new JLabel("Email Id:");
	emailId.setBounds(80,250,80,20);
	add(emailId);
		
	emailIdTxt = new JTextField();
	emailIdTxt.setBounds(200,250,150,20);
	emailIdTxt.setBackground(Color.white);
	add(emailIdTxt);
		
	contactNo = new JLabel("Contact No:");
	contactNo.setBounds(80,280,80,20);
	add(contactNo);
		
	contactNoTxt=new JTextField();
	contactNoTxt.setBounds(200,280,150,20);
	contactNoTxt.setBackground(Color.white);
	add(contactNoTxt);
	    
        update = new JButton("Save & Exit");
        update.setBounds(80,340,100,30);
        update.setBackground(Color.white);
        update.setForeground(Color.black);
        add(update);
	
        ok = new JButton("OK");
	    ok.setBounds(250,340,100,30);
	    ok.setBackground(Color.white);
        ok.setForeground(Color.black);
	    add(ok);
        
        update.addActionListener(this);
        ok.addActionListener(this);
        
        setLayout(null);
        setSize(450,450);
        setResizable(false);
        setLocation(480,220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==ok)
        {
            setVisible(false);
            new LibrarianView(this.un);
        }
        else if(ae.getSource()==update)
        {
            String s1 = firstNameTxt.getText();
            String s2 = lastNameTxt.getText();
            String s3 = passwordTxt.getText();
            String s4 = confirmPasswordTxt.getText();
            String s5 = emailIdTxt.getText();
            String s6 = contactNoTxt.getText();
            
            try
            {
                    String query = "UPDATE `librarian_information` SET `First_Name`="+'"'+s1+'"'+','+"`Last_Name`="+'"'+s2+'"'+','+"`Password`="+'"'+s3+'"'+','+"`Confirm_Password`="+'"'+s4+'"'+','+"`Email_ID`="+'"'+s5+'"'+','+"`Contact_No`="+'"'+s6+'"'+" WHERE User_Name ="+'"'+this.un+'"';
                    Connection c = DatabaseConnection.dbConnector();
                    Statement st = c.createStatement();
                    st.executeUpdate(query);   
                    
                    System.out.println("Librarian information updated");
            }
            catch(Exception e)
            {
                System.out.println("Librarian information not updated");
            }
        }
    }
}
