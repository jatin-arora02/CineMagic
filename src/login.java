

import java.sql.*;
import moviedb.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class login extends JFrame implements ActionListener
{
    JTextField tf1;
    JPasswordField pf;
    JButton bt,bt1;
    JLabel lb1,lb2;
    
    login()
    {
        setLayout(null);
        
        
        tf1=new JTextField();
        pf=new JPasswordField();
        bt=new JButton("LOGIN");
        bt1=new JButton("SIGN UP");
        lb1=new JLabel("USERNAME");
        lb2=new JLabel("PASSWORD");
        
        tf1.setBounds(150,100,100,30);
        pf.setBounds(150,150,100,30);
        bt.setBounds(150,200,100,30);
       
        lb1.setBounds(50,100,100,30);
        lb2.setBounds(50,150,100,30);
        
        add(tf1);
        add(pf);
        add(bt);
        add(lb1);
        add(lb2);
        
        
        
        
        //register
        bt.addActionListener(this);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==bt)
        {
             try
        {
            String u=tf1.getText();
            String p=pf.getText();
        
            ResultSet rs=Dbloader.executeQuery("select * from login where username='"+u+"'and password='"+p+"'");
            if(rs.next())
            {
                JOptionPane.showMessageDialog(this, "login successfull");
               java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainpanel().setVisible(true);
            }
        });
            }
            else
            {
                JOptionPane.showMessageDialog(this, "login failed");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        }
        
       
    }
    
    
}
