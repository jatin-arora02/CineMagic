
import com.mashape.unirest.http.*;
import com.mysql.cj.protocol.Resultset;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import moviedb.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class search_tv_shows extends javax.swing.JFrame implements ActionListener {

     
    public search_tv_shows() {
        initComponents();
        setSize(800,800);
        bt1.addActionListener(this);
        
        
    }
      void go(String name)
    {
        mainpanel2.removeAll();
         String api = "980d96176457a6e65b8bc282bcadccd4";
        try 
        {
           
            HttpResponse<String> res = Unirest.get("https://api.themoviedb.org/3/search/tv?api_key=980d96176457a6e65b8bc282bcadccd4&language=en-US&page=1&query="+name+"&include_adult=false").asString();
               
                    
            if(res.getStatus()==200)
            {
                
                String ans=res.getBody();
                JSONParser parser=new JSONParser();
                
                JSONObject mainobj=(JSONObject) parser.parse(ans);
                
                JSONArray array=(JSONArray) mainobj.get("results");
                
                 int x=10,y=10,k=0;
                 for(int i=0;i<array.size();i++)
                 {
                    k++;
                    JSONObject singleobj=(JSONObject)array.get(i);
                    String title=(String) singleobj.get("name");
                    long id=(long) singleobj.get("id");
                    int id2=(int) id;
                    String overview=(String) singleobj.get("overview");
                    String poster_path=(String) singleobj.get("poster_path");
                    
                     search_person_panel  panel=new search_person_panel();
                   
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
                               JOptionPane.showMessageDialog(mainpanel2, "removed successfully");
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
                                 JOptionPane.showMessageDialog(mainpanel2, "added successfully");
                            }
                        });
                        
                    } 
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                   
                    
                    panel.setBounds(x,y,210,450);
                    if(k%3==0)
                    {
                        x=10;
                        y=y+470;
                    }
                    else
                    {
                        x+=220;
                    }
                    
                    panel.lb1.setText("<html>"+title+"</html>");
                    panel.lb2.setText("rating "+singleobj.get("vote_average"));
                    ImageIcon im=new ImageIcon("src/pictures/war.jpeg");
                    Image img=im.getImage().getScaledInstance(panel.lb3.getWidth(), panel.lb3.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon im1=new ImageIcon(img);
                    panel.lb3.setIcon(im1);
                    panel.ta1.append(overview);
                    panel.ta1.setLineWrap(true);
                    mainpanel2.add(panel);
                    //mainpanel2.repaint();
                    panel.repaint(); 
                 }
                    
                    
                    
                
                mainpanel2.setPreferredSize(new Dimension(220*3,(k/2)*480));
            }
        }      
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        tf1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainpanel2 = new javax.swing.JPanel();
        bt1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lb1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lb1.setText("SEARCH TV SHOWS");
        getContentPane().add(lb1);
        lb1.setBounds(252, 14, 236, 39);
        getContentPane().add(tf1);
        tf1.setBounds(20, 60, 240, 30);

        javax.swing.GroupLayout mainpanel2Layout = new javax.swing.GroupLayout(mainpanel2);
        mainpanel2.setLayout(mainpanel2Layout);
        mainpanel2Layout.setHorizontalGroup(
            mainpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 688, Short.MAX_VALUE)
        );
        mainpanel2Layout.setVerticalGroup(
            mainpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(mainpanel2);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 120, 690, 500);

        bt1.setText("SEARCH");
        getContentPane().add(bt1);
        bt1.setBounds(290, 63, 90, 30);

        pack();
    }// </editor-fold>                        
        
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
            java.util.logging.Logger.getLogger(search_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(search_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(search_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(search_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       
    }

    // Variables declaration - do not modify                     
    public javax.swing.JButton bt1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb1;
    private static javax.swing.JPanel mainpanel2;
    public javax.swing.JTextField tf1;
    // End of variables declaration                   

    @Override
    public void actionPerformed(ActionEvent e)
    {
        
            String name1=tf1.getText();
             name1=name1.replace(" ","%20");
            tf1.setText("");
            tf1.requestFocus();
            go(name1);
        
        
    }
   
        
        
    
}
