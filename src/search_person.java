
import com.mashape.unirest.http.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import moviedb.Dbloader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author risha
 */
public class search_person extends javax.swing.JFrame implements ActionListener {

     
    public search_person() {
        initComponents();
        setSize(800,800);
        bt1.addActionListener(this);
    }
    static void go(String name)
    {
        mainpanel1.removeAll();
         String api = "980d96176457a6e65b8bc282bcadccd4";
        try 
        {
           
            HttpResponse<String> res = Unirest.get("https://api.themoviedb.org/3/search/person?api_key=980d96176457a6e65b8bc282bcadccd4&language=en-US&query="+name+"&page=1&include_adult=false").asString();
               
                    
            if(res.getStatus()==200)
            {
                String ans=res.getBody();
                JSONParser parser=new JSONParser();
                
                JSONObject mainobj=(JSONObject) parser.parse(ans);
                
                JSONArray array=(JSONArray) mainobj.get("results");
                int x=10,y=10,k=0;
                for(int i=0;i<array.size();i++)
                {
                    JSONObject singleobj=(JSONObject)array.get(i);
                    JSONArray myarray=(JSONArray) singleobj.get("known_for");
                    for(int j=0;j<myarray.size();j++)
                    {
                    k++;
                    JSONObject singleobj1=(JSONObject)myarray.get(j);
                    String title=(String) singleobj1.get("title");
                    String name1=(String) singleobj1.get("name");
                    long id=(long) singleobj1.get("id");
                    int id2=(int) id;
                    String overview=(String)singleobj1.get("overview");
                    String poster_path=(String) singleobj1.get("poster_path");
                    search_person_panel panel=new search_person_panel();
                    try
                    {
                       ResultSet rs=Dbloader.executeQuery("select * from favmovies where id="+id);
                       if(rs.next())
                    {
                        panel.bt1.setText("remove from fav");
                        panel.bt1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                try 
                                {
                                    
                                    rs.deleteRow();
                                } 
                                catch (SQLException ex) 
                                {
                                    ex.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(mainpanel1, "removed successfully");
                            }
                        });
                    }
                    else
                    {
                        panel.bt1.setText("add to fav");
                        panel.bt1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                try
                                {
                                    
                                    rs.moveToInsertRow();
                                    rs.updateInt("id", id2);
                                    rs.updateString("name", title);
                                    rs.updateString("description", overview);
                                    rs.updateString("photo", poster_path);
                                    rs.insertRow();
                                } 
                                catch (SQLException ex)
                                {
                                    ex.printStackTrace();
                                }
                                 JOptionPane.showMessageDialog(mainpanel1, "added successfully");
                            }
                        });
                        
                    } 
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    panel.setBounds(x,y,200,450);
                    if(k%3==0)
                    {
                        x=10;
                        y=y+470;
                    }
                    else
                    {
                        x+=220;
                    }
                   
                    if(title!=null)
                    {
                        panel.lb1.setText("<html>"+title+"</html>");
                    }
                    else
                    {
                        panel.lb1.setText("<html>"+name1+"</html>");
                    }
                    
                    panel.lb2.setText("rating "+singleobj1.get("vote_average"));
                     ImageIcon im=new ImageIcon("src/pictures/war.jpeg");
                    Image img=im.getImage().getScaledInstance(panel.lb3.getWidth(), panel.lb3.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon im1=new ImageIcon(img);
                    panel.lb3.setIcon(im1);
                    panel.ta1.setText(overview);
                    panel.ta1.setLineWrap(true);
                    mainpanel1.add(panel);
                    mainpanel1.repaint();
                    panel.repaint();
                    
                    }
                }
                mainpanel1.setPreferredSize(new Dimension(220*3,(k/2)*300));
            }
        }      
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        tf1 = new javax.swing.JTextField();
        bt1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainpanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lb1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lb1.setText("SEARCH ACTOR");
        getContentPane().add(lb1);
        lb1.setBounds(210, 10, 220, 30);

        tf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf1ActionPerformed(evt);
            }
        });
        getContentPane().add(tf1);
        tf1.setBounds(20, 60, 300, 30);

        bt1.setText("SEARCH");
        getContentPane().add(bt1);
        bt1.setBounds(350, 60, 90, 30);

        javax.swing.GroupLayout mainpanel1Layout = new javax.swing.GroupLayout(mainpanel1);
        mainpanel1.setLayout(mainpanel1Layout);
        mainpanel1Layout.setHorizontalGroup(
            mainpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 688, Short.MAX_VALUE)
        );
        mainpanel1Layout.setVerticalGroup(
            mainpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(mainpanel1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 110, 690, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf1ActionPerformed
        String name=tf1.getText();
        String name1=name.replace(" ","%20");
        //go(name1);
    }//GEN-LAST:event_tf1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(search_person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(search_person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(search_person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(search_person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb1;
    private static javax.swing.JPanel mainpanel1;
    private javax.swing.JTextField tf1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e)
    {
         String name=tf1.getText();
        String name1=name.replace(" ","%20");
        tf1.setText("");
        tf1.requestFocus();
        go(name1);
    }
}
