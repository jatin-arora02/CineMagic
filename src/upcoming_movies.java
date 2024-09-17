
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
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
public class upcoming_movies extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public upcoming_movies() {
        initComponents();
        setSize(800,800);
        go();
    }
    static void go()
    {
        String api = "980d96176457a6e65b8bc282bcadccd4";
        try 
        {
            HttpResponse<String> res = Unirest.get("https://api.themoviedb.org/3/movie/upcoming?api_key="+api+"&language=en-US&page=1").asString();
              
                    
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
                    String title=(String) singleobj.get("title");
                    String release_date=(String) singleobj.get("release_date");
                    long id=(long) singleobj.get("id");
                    int id2=(int) id;
                     String poster_path=(String) singleobj.get("poster_path");
                    String overview=(String) singleobj.get("overview");
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
                                 JOptionPane.showMessageDialog(panel, "removed successfully");
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
                                 JOptionPane.showMessageDialog(panel, "removed successfully");
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
                    panel.lb2.setText(release_date);
                     ImageIcon im=new ImageIcon("src/pictures/war.jpeg");
                    Image img=im.getImage().getScaledInstance(panel.lb3.getWidth(), panel.lb3.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon im1=new ImageIcon(img);
                    panel.lb3.setIcon(im1);
                    panel.ta1.setText(overview);
                    panel.ta1.setLineWrap(true);
                    mainpanel.add(panel);
                    //mainpanel.repaint();
                    //panel.repaint();
                    
                    
                }
                mainpanel.setPreferredSize(new Dimension(220*3,(k/2)*230));
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        mainpanel = new javax.swing.JPanel();
        lb1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainpanelLayout.setVerticalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        lb1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb1.setText("upcoming movies");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(262, Short.MAX_VALUE)
                .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 10, 700, 580);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(upcoming_movies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(upcoming_movies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(upcoming_movies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(upcoming_movies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb1;
    private static javax.swing.JPanel mainpanel;
    // End of variables declaration//GEN-END:variables
}
