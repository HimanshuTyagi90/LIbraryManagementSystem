package Jframe;

import Adapters.dbhandler;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HIMANSHU TYAGI BCA SEM 5 ROLL NO 200954106032.
 */
public class Defaulters extends javax.swing.JFrame {

    
    
    private ResultSet rst;
    private static Connection conn;
    private PreparedStatement pst;
    private String sql,bookId, bookName, studentId, studentName, phoneNo, address;
    private java.sql.Date sqlDate;

    /**
     * Creates new form Defaulters
     */
    public Defaulters() {
         try {
            conn = dbhandler.getDbConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("resources/SCZ.png")));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDataTable();
    }

    // setting data into table from database
    private void setDataTable() {

        clearTable();
        try {

            // defaulter is showing +1 value then the actual no of defaulters.                          // debug.
             java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            sql = "SELECT books.bookId,books.bookName,students.studentId,students.studentName,students.fatherName,students.phoneNo,students.address FROM ( ( issuedbooks INNER JOIN books ON issuedbooks.bookId = books.bookId ) INNER JOIN students ON issuedbooks.studentId = students.studentId ) where `defaultDate` < '"+date+"' ;";
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            while (rst.next()) {
                bookId = rst.getString("bookId");
                bookName = rst.getString("bookName");
                studentId = rst.getString("studentId");
                studentName = rst.getString("studentName");
                phoneNo = rst.getString("phoneNo");
                address = rst.getString("address");

                Object[] obj = {bookId, bookName, studentId, studentName, phoneNo, address};
                DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                model.addRow(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearTable() {

        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        model.setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperPanel = new javax.swing.JPanel();
        defaulterDetailsLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        HomeBtn = new javax.swing.JLabel();
        lowerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new rojeru_san.complementos.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Defaulters");

        upperPanel.setBackground(new java.awt.Color(255, 51, 51));
        upperPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        defaulterDetailsLabel.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        defaulterDetailsLabel.setForeground(new java.awt.Color(255, 255, 255));
        defaulterDetailsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        defaulterDetailsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        defaulterDetailsLabel.setText("Defaulters");
        defaulterDetailsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                defaulterDetailsLabelMouseClicked(evt);
            }
        });
        upperPanel.add(defaulterDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 510, -1));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        upperPanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 460, 20));

        HomeBtn.setBackground(new java.awt.Color(255, 0, 51));
        HomeBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        HomeBtn.setForeground(new java.awt.Color(255, 255, 255));
        HomeBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HomeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        HomeBtn.setText("Home");
        HomeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HomeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeBtnMouseClicked(evt);
            }
        });
        upperPanel.add(HomeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 137, 33));

        lowerPanel.setBackground(new java.awt.Color(255, 255, 255));

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Book Id", "Book Name", "Student's Id", "Student's Name", "Phone No.", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dataTable.setColorBackgoundHead(new java.awt.Color(255, 51, 51));
        dataTable.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        dataTable.setColorFilasForeground2(new java.awt.Color(220, 0, 51));
        dataTable.setColorSelBackgound(new java.awt.Color(204, 204, 204));
        dataTable.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dataTable.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dataTable.setRowHeight(30);
        jScrollPane1.setViewportView(dataTable);
        if (dataTable.getColumnModel().getColumnCount() > 0) {
            dataTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            dataTable.getColumnModel().getColumn(2).setPreferredWidth(15);
            dataTable.getColumnModel().getColumn(4).setPreferredWidth(10);
            dataTable.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        javax.swing.GroupLayout lowerPanelLayout = new javax.swing.GroupLayout(lowerPanel);
        lowerPanel.setLayout(lowerPanelLayout);
        lowerPanelLayout.setHorizontalGroup(
            lowerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        lowerPanelLayout.setVerticalGroup(
            lowerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lowerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1354, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lowerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void HomeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeBtnMouseClicked

        ControlPanel next = new ControlPanel();
        next.setVisible(true);
        dispose();
    }//GEN-LAST:event_HomeBtnMouseClicked

    private void defaulterDetailsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_defaulterDetailsLabelMouseClicked

        setDataTable();
    }//GEN-LAST:event_defaulterDetailsLabelMouseClicked

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
            java.util.logging.Logger.getLogger(Defaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Defaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Defaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Defaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Defaulters().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HomeBtn;
    private rojeru_san.complementos.RSTableMetro dataTable;
    private javax.swing.JLabel defaulterDetailsLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel lowerPanel;
    private javax.swing.JPanel upperPanel;
    // End of variables declaration//GEN-END:variables
}
