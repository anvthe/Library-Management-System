import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ForLibrarianBookShelf extends JFrame implements ActionListener
{
    JLabel serach,bookId,bookName,authorName,shelfNo,addCopy;
    JTextField serachTxt,bookIdTxt,bookNameTxt,authorNameTxt,shelfNoTxt,addCopyTxt;
    JButton find,insert,delete,back;
    JComboBox <String> bookShelfNo;
    
    String un;
    
    public ForLibrarianBookShelf(String un)
    {
        super("Bookshelf");
        
        this.un = un;
        
        serach = new JLabel("Search | Insert | Delete Books");
	    serach.setBounds(150,35,200,20);
	    add(serach);
        
        find = new JButton("Search Book");
        find.setBounds(310,260,120,25);
        find.setForeground(Color.black);
        find.setBackground(Color.white);
        add(find);
        
        insert = new JButton("Insert Book");
        insert.setBounds(170,260,120,25);
        insert.setForeground(Color.black);
        insert.setBackground(Color.white);
        add(insert);
        
        delete = new JButton("Delete Book");
        delete.setBounds(30,260,120,25);
        delete.setForeground(Color.black);
        delete.setBackground(Color.white);
        add(delete);
        
        back = new JButton("Back");
        back.setBounds(380,10,80,25);
        back.setBackground(Color.red);
        back.setForeground(Color.white);
        add(back);
        
    bookId = new JLabel("Book ID: ");
	bookId.setBounds(80,80,80,20);
	add(bookId);
		
	bookIdTxt = new JTextField();
	bookIdTxt.setBounds(230,80,150,20);
	bookIdTxt.setBackground(Color.white);
	add(bookIdTxt);	
		
	bookName = new JLabel("Book Name: ");
	bookName.setBounds(80,110,150,20);
	add(bookName);
		
	bookNameTxt = new JTextField();
	bookNameTxt.setBounds(230,110,150,20);
	bookNameTxt.setBackground(Color.white);
	add(bookNameTxt);
        
    authorName = new JLabel("Author name(s): ");
	authorName.setBounds(80,140,150,20);
	add(authorName);
		
	authorNameTxt=new JTextField();
	authorNameTxt.setBounds(230,140,150,20);
	authorNameTxt.setBackground(Color.white);
	add(authorNameTxt);
        
    shelfNo = new JLabel("Shelf(s): ");
	shelfNo.setBounds(80,170,170,20);
	add(shelfNo);
		
	bookShelfNo = new JComboBox<String>();
        bookShelfNo.addItem("1A");
        bookShelfNo.addItem("2B");
        bookShelfNo.addItem("3C");
        bookShelfNo.addItem("4D");
        bookShelfNo.addItem("5E");
        bookShelfNo.setBounds(230,170,150,20);
        bookShelfNo.setBackground(Color.white);
        add(bookShelfNo);
        addCopyTxt = new JTextField();
        addCopyTxt.setBounds(230,200,150,20);
        add(addCopyTxt);
        
        addCopy = new JLabel("Add Copy: ");
        addCopy.setBounds(80,200,150,20);
        add(addCopy);
        
        
        setLayout(null);
        
        insert.addActionListener(this);
        delete.addActionListener(this);
        find.addActionListener(this);
        back.addActionListener(this);
        
        
        setSize(480,370);
        setLocation(480,220);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String s1 = bookIdTxt.getText();
        String s2 = bookNameTxt.getText();
        String s3 = authorNameTxt.getText();
        String s4 = String.valueOf(bookShelfNo.getSelectedItem());
        String s5 = addCopyTxt.getText();
        
        if(ae.getSource()==insert)
        {
            try{
                if(s1.length()==0)
                {
                    JOptionPane.showMessageDialog(null,"You must provide a book id.");
                }
                else if(s2.length()==0)
                {
                    JOptionPane.showMessageDialog(null,"You must provide a book name.");
                }
                else if(s3.length()==0)
                {
                    JOptionPane.showMessageDialog(null,"You must provide an author name.");
                }
                else if(s5.length()==0)
                {
                    JOptionPane.showMessageDialog(null,"Please give number of copies");
                }
                else
                {
                    String query = "INSERT INTO `bookshelf`(`Book_ID`, `Book_Name`, `Author_Name`, `Shelf_No` , `Total_Copy`, `Available_Copy`) VALUES ("+'"'+s1+'"'+','+'"'+s2+'"'+','+'"'+s3+'"'+','+'"'+s4+'"'+','+'"'+s5+'"'+','+'"'+s5+'"'+')';
                    Connection c = DatabaseConnection.dbConnector();
                    Statement st = c.createStatement();
                    st.executeUpdate(query);
                    
                    JOptionPane.showMessageDialog(null,"Book inserted");
                    c.close();
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Book already inserted!!");
            }
        }
        
        
        else if(ae.getSource()==delete)
        {
            if(s1.length()==0)
            {
                JOptionPane.showMessageDialog(null,"You must provide a book id.");
            }
            else
            {
                try
                {
                    String query = "DELETE FROM `bookshelf` WHERE book_id = "+'"'+s1+'"';
                    Connection c = DatabaseConnection.dbConnector();
                    Statement st = c.createStatement();
                    st.executeUpdate(query);

                    JOptionPane.showMessageDialog(null,"Book deleted");
                    
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e);
                }
            }
        }
        
        else if(ae.getSource()==find)
        {
            if(s1.length()==0)
            {
                JOptionPane.showMessageDialog(null,"You must provide a book id");
            }
            else
            {
                try
                {
                    String query = "SELECT * FROM `bookshelf` WHERE book_id = "+'"'+s1+'"';
                    Connection c = DatabaseConnection.dbConnector();
                    Statement st = c.createStatement();
                    ResultSet r = st.executeQuery(query);
                    
                    int count = 0;
                    while(r.next())
                    {
                        count = count+1;
                        if(count>=1)
                        {
                            bookNameTxt.setText(r.getString("Book_Name"));
                            authorNameTxt.setText(r.getString("Author_Name"));
                            bookShelfNo.setSelectedItem(r.getString("Shelf_No"));
                            
                            addCopy.setVisible(false);
                            
                            addCopy = new JLabel("Available Copy");
                            addCopy.setBounds(80,170,150,20);
                            this.add(addCopy);
                            addCopy.setLayout(null);
                            addCopyTxt.setText(r.getString("Available_Copy"));
                        }
                    }
                    if(count<1)
                    {
                        JOptionPane.showMessageDialog(null,"Book not found..");
                        bookNameTxt.setText(null);
                        authorNameTxt.setText(null);
                        bookShelfNo.setSelectedItem("1A");
                        addCopyTxt.setText(null);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Book found..");
                        r.close();
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e);
                }
            }
        }
        
        else if(ae.getSource()==back)
        {
            new LibrarianView(this.un);
            setVisible(false);
        }
    }
}
