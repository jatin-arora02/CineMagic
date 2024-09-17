

import java.sql.*;
import moviedb.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class signup extends JFrame implements ActionListener
{
    JTextField tf1;
    JPasswordField pf;
    JButton bt,bt1;
    JLabel lb1,lb2;
    
    signup()
    {
        setLayout(null);
        
        
        tf1=new JTextField();
        pf=new JPasswordField();
       
        bt1=new JButton("SIGN UP");
        lb1=new JLabel("USERNAME");
        lb2=new JLabel("PASSWORD");
        
        tf1.setBounds(150,100,100,30);
        pf.setBounds(150,150,100,30);
        bt1.setBounds(150,200,100,30);
        
        lb1.setBounds(50,100,100,30);
        lb2.setBounds(50,150,100,30);
        
        add(tf1);
        add(pf);
     
        add(lb1);
        add(lb2);
        add(bt1);
        
        
        
        //register
        bt1.addActionListener(this);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        
            try
            {
                String u=tf1.getText();
                String p=pf.getText();
        
                ResultSet rs=Dbloader.executeQuery("select * from login where username='"+u+"'");
                if(rs.next())
                {
                     JOptionPane.showMessageDialog(this, "username already exists");
                }
                else
                {
                    rs.moveToInsertRow();
                    rs.updateString("username", u);
                    rs.updateString("password", p);
                    rs.insertRow();
                    JOptionPane.showMessageDialog(this, "signed up successfully");
                    login obj1=new login(); 
                }
            
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
             
        
      
    }
    
    public static void main(String[] args) 
    {
        signup obj=new signup();
    }
}
