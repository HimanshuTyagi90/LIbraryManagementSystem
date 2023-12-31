package Jframe;

import Adapters.dbhandler;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author HIMANSHU TYAGI BCA SEM 5 ROLL NO 200954106032.
 *
 */
public class LoginPage extends javax.swing.JFrame {
    
    private static Connection conn;
    private static PreparedStatement pst;
    private static String sql, Username, Password, isLoggedIn;
    
    public void showmessage(String message) {
        
        ResultMessageLabel1.setText(message);
        Timer timer = new Timer(3000, (ActionEvent arg0) -> {
            ResultMessageLabel1.setText(" ");
        });
        timer.setRepeats(false); // Only execute once
        timer.start();
        
    }
    
    private Boolean getData() {
        
        Username = usernameEditText.getText();
        Password = String.valueOf(passwordEditText.getPassword());        
        return true;
    }

    /**
     * Creates new form SignUpPage
     */
    public LoginPage() {
         try {
            conn = dbhandler.getDbConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("resources/SCZ.png")));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LeftPanel = new org.jdesktop.swing.animation.rendering.JRendererPanel();
        jRendererPanel1 = new org.jdesktop.swing.animation.rendering.JRendererPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        developerName = new javax.swing.JLabel();
        leftPanelimage = new rojerusan.RSLabelImage();
        rightPanel = new org.jdesktop.swing.animation.rendering.JRendererPanel();
        jLabel4 = new javax.swing.JLabel();
        usernameicon = new rojerusan.RSLabelImage();
        usernameEditText = new app.bolivia.swing.JCTextField();
        signUpBtn = new rojerusan.RSMaterialButtonRectangle();
        loginBtn = new rojerusan.RSMaterialButtonRectangle();
        jLabel6 = new javax.swing.JLabel();
        ResultMessageLabel1 = new javax.swing.JLabel();
        BrandNameContainerLeftandRight = new javax.swing.JPanel();
        brandnameLeft = new javax.swing.JLabel();
        brandnameRight = new javax.swing.JLabel();
        passwordIcon1 = new rojerusan.RSLabelImage();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        passwordEditText = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Page");
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setName("LoginFrame"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LeftPanel.setBackground(new java.awt.Color(255, 255, 255));
        LeftPanel.setPreferredSize(new java.awt.Dimension(990, 700));
        LeftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jRendererPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("D.A.V. COLLEGE MUZAFFARNAGAR");
        jRendererPanel1.add(jLabel2);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LIBRARY MANAGEMENT SYSTEM");
        jRendererPanel1.add(jLabel1);

        LeftPanel.add(jRendererPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 470, 80));

        developerName.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        developerName.setText("Designed  by Rohan Developed By Himanshu Tyagi");
        LeftPanel.add(developerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 690, 450, 60));

        leftPanelimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/library-2.png"))); // NOI18N
        LeftPanel.add(leftPanelimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 860, 640));

        getContentPane().add(LeftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 950, 750));

        rightPanel.setBackground(new java.awt.Color(153, 51, 255));
        rightPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("@Username");
        rightPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 130, -1));

        usernameicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Account_50px.png"))); // NOI18N
        rightPanel.add(usernameicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 40, 40));

        usernameEditText.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        usernameEditText.setPlaceholder("Enter Username here");
        usernameEditText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameEditTextActionPerformed(evt);
            }
        });
        rightPanel.add(usernameEditText, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 260, 40));

        signUpBtn.setBackground(new java.awt.Color(102, 255, 102));
        signUpBtn.setText("SignUp");
        signUpBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpBtnActionPerformed(evt);
            }
        });
        rightPanel.add(signUpBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, -1, 40));

        loginBtn.setBackground(new java.awt.Color(102, 204, 255));
        loginBtn.setText("Login");
        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        rightPanel.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, -1, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Login Here");
        rightPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 390, 30));

        ResultMessageLabel1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        ResultMessageLabel1.setForeground(new java.awt.Color(255, 255, 255));
        ResultMessageLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rightPanel.add(ResultMessageLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 420, 30));

        BrandNameContainerLeftandRight.setBackground(new java.awt.Color(153, 51, 255));
        BrandNameContainerLeftandRight.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BrandNameContainerLeftandRight.setName("BrandNamePanel"); // NOI18N

        brandnameLeft.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        brandnameLeft.setForeground(new java.awt.Color(255, 51, 51));
        brandnameLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        brandnameLeft.setText("SuperCruZe");
        BrandNameContainerLeftandRight.add(brandnameLeft);

        brandnameRight.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        brandnameRight.setForeground(new java.awt.Color(255, 255, 255));
        brandnameRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        brandnameRight.setText("Technologies");
        BrandNameContainerLeftandRight.add(brandnameRight);

        rightPanel.add(BrandNameContainerLeftandRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 370, 40));

        passwordIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Secure_50px.png"))); // NOI18N
        rightPanel.add(passwordIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 40, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("@Password");
        rightPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 130, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Control  Panel");
        rightPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 240, 30));

        passwordEditText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordEditTextFocusLost(evt);
            }
        });
        passwordEditText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordEditTextActionPerformed(evt);
            }
        });
        rightPanel.add(passwordEditText, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 332, 260, 40));

        getContentPane().add(rightPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 450, 750));

        setSize(new java.awt.Dimension(1366, 758));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCTextField1ActionPerformed

    private void jCTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCTextField2ActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        if (getData()) {
            try {
                
                sql = "SELECT * FROM `users` WHERE `username` LIKE '" + Username + "' AND `password` LIKE '" + Password + "';";
                pst = conn.prepareStatement(sql);
                ResultSet result = pst.executeQuery();
                if (result.next()) {
                    
                    
                    sql = "update `masterdata` set `userLoggedIn` = ? , `isLoggedIn` = ?;";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, Username);
                    pst.setString(2, "true");
                    pst.executeUpdate();
                    ControlPanel next = new ControlPanel();
                    next.setVisible(true);
                    Timer timer = new Timer(2000,(e) -> {
                    dispose();    
                    });
                    timer.setRepeats(false); // Only execute once
                    timer.start();
                    
                    
                } else {
                    
                    showmessage("O O ! Wrong details, try again " + usernameEditText.getText());
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_loginBtnActionPerformed

    private void signUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpBtnActionPerformed
        
        SignUpPage next = new SignUpPage();
        next.setVisible(true);
        dispose();

    }//GEN-LAST:event_signUpBtnActionPerformed

    private void usernameEditTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameEditTextActionPerformed
        
        if (String.valueOf(usernameEditText.getText()).isEmpty()) {
            showmessage("Username shouldn't be Empty!");
        }else{

        Pattern namePattern = Pattern.compile("^[a-zA-Z ]{4,16}$");
        Matcher nameMatcher = namePattern.matcher(usernameEditText.getText());
        if (!nameMatcher.matches()) {

            showmessage("Username should not cotain any number! ");
            usernameEditText.requestFocus();
        }
        passwordEditText.requestFocus();
        }
    }//GEN-LAST:event_usernameEditTextActionPerformed

    private void passwordEditTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordEditTextFocusLost

       
    }//GEN-LAST:event_passwordEditTextFocusLost

    private void passwordEditTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordEditTextActionPerformed
        
         if (String.valueOf(passwordEditText.getPassword()).isEmpty()) {
            showmessage("Password shouldn't be Empty!");
        }else{

        String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern w = Pattern.compile(regex);
        Matcher a = w.matcher(String.valueOf(passwordEditText.getPassword()));
        if (!a.matches() ) {
            showmessage("Password must be Alphanumeric and contain @!");
            passwordEditText.requestFocus();
        }else{
            loginBtn.doClick();
        }
        }
    }//GEN-LAST:event_passwordEditTextActionPerformed

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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
           new LoginPage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BrandNameContainerLeftandRight;
    private org.jdesktop.swing.animation.rendering.JRendererPanel LeftPanel;
    private javax.swing.JLabel ResultMessageLabel1;
    private javax.swing.JLabel brandnameLeft;
    private javax.swing.JLabel brandnameRight;
    private javax.swing.JLabel developerName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private org.jdesktop.swing.animation.rendering.JRendererPanel jRendererPanel1;
    private rojerusan.RSLabelImage leftPanelimage;
    private rojerusan.RSMaterialButtonRectangle loginBtn;
    private javax.swing.JPasswordField passwordEditText;
    private rojerusan.RSLabelImage passwordIcon1;
    private org.jdesktop.swing.animation.rendering.JRendererPanel rightPanel;
    private rojerusan.RSMaterialButtonRectangle signUpBtn;
    private app.bolivia.swing.JCTextField usernameEditText;
    private rojerusan.RSLabelImage usernameicon;
    // End of variables declaration//GEN-END:variables
}
