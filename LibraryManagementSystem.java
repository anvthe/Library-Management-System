import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LibraryManagementSystem extends JFrame implements ActionListener
{
    JFrame frame;
    JLabel Label;
    JButton adminLogin,librarianLogin,userLogIn,exit;
    ImageIcon image;
    
    public LibraryManagementSystem()
    {
        super("Library Management System");
        
		  image = new ImageIcon(getClass().getResource("lib.jpg"));
          Label = new JLabel(image);
        
        adminLogin = new JButton("Admin Login");
        adminLogin.setBounds(200,50,150,50);
        adminLogin.setBackground(Color.LIGHT_GRAY);
		adminLogin.setForeground(Color.black);
        add(adminLogin);
        
        librarianLogin = new JButton("Librarian Login");
        librarianLogin.setBounds(200,150,150,50);
        librarianLogin.setBackground(Color.LIGHT_GRAY);
		librarianLogin.setForeground(Color.black);
        add(librarianLogin);
        
        userLogIn = new JButton("User Login");
        userLogIn.setBounds(200,250,150,50);
        userLogIn.setBackground(Color.LIGHT_GRAY);
        userLogIn.setForeground(Color.black);
        add(userLogIn);
        
        exit = new JButton("Exit");
        exit.setBounds(200,350,150,50);
        exit.setBackground(Color.LIGHT_GRAY);
        exit.setForeground(Color.black);
        add(exit);
        
        add(Label);
		
        pack();
        
        adminLogin.addActionListener(this);
        librarianLogin.addActionListener(this);
        exit.addActionListener(this);
        userLogIn.addActionListener(this);
        
        setSize(550,450);
        setLocation(400,120);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==adminLogin)
        {
            setVisible(false);
            new AdminLogIn();
        }
        else if(ae.getSource()==librarianLogin)
        {
            setVisible(false);
            new LibrarianLogIn();
        }
        else if(ae.getSource()==userLogIn)
        {
            setVisible(false);
            new UserLogIn();
        }
        else if(ae.getSource()==exit)
        {
            System.exit(0);
        }
    }
}