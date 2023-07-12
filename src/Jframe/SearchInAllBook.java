package Jframe;

import Adapters.dbhandler;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author HIMANSHU TYAGI BCA SEM 5 ROLL NO 200954106032.
 */
public class SearchInAllBook extends javax.swing.JFrame {

    String bookId, bookName, author, publisher, supplier, cost, subject;
    private static ResultSet rst;
    private static Connection conn;
    private static PreparedStatement pst;
    private static String sql;
    DefaultTableModel model;

    /**
     * Creates new form SearchStudent
     */
    public SearchInAllBook() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("resources/SCZ.png")));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDataTable();
    }

    public SearchInAllBook(String studentId) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("resources/SCZ.png")));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDataTable();
    }

    // setting data into table from database
    private void setDataTable() {

        clearTable();
        try {

            // GETTING DATA FROM STUDENT TABLE
            conn = dbhandler.getDbConnection();
            sql = "SELECT * FROM `books` ;";
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            while (rst.next()) {
                bookId = rst.getString("bookId");
                bookName = rst.getString("bookName");
                author = rst.getString("authorName");
                publisher = rst.getString("publisherName");
                supplier = rst.getString("supplierName");
                cost = rst.getString("costOfBook");
                subject = rst.getString("subjectOfBook");

                Object[] obj = {bookId, bookName, author, publisher, supplier, cost, subject};
                model = (DefaultTableModel) dataTable.getModel();
                model.addRow(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void searchData(String keyword) {

        model = (DefaultTableModel) dataTable.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        dataTable.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(keyword));

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
        viewAllDetailsLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        HomeBtn = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        searchInput = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new rojeru_san.complementos.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        upperPanel.setBackground(new java.awt.Color(153, 51, 255));
        upperPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewAllDetailsLabel.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        viewAllDetailsLabel.setForeground(new java.awt.Color(255, 255, 255));
        viewAllDetailsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewAllDetailsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        viewAllDetailsLabel.setText("Search Book");
        viewAllDetailsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewAllDetailsLabelMouseClicked(evt);
            }
        });
        upperPanel.add(viewAllDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 510, -1));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        upperPanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 460, 20));

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search Keyword  :");
        upperPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        searchInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInputActionPerformed(evt);
            }
        });
        searchInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchInputKeyReleased(evt);
            }
        });
        upperPanel.add(searchInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 480, -1));

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Book Name", "Author ", "Publisher", "Supplier", "Cost", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dataTable.setColorBackgoundHead(new java.awt.Color(153, 51, 255));
        dataTable.setColorFilasForeground1(new java.awt.Color(255, 100, 170));
        dataTable.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        dataTable.setColorSelBackgound(new java.awt.Color(204, 204, 204));
        dataTable.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dataTable.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dataTable.setRowHeight(30);
        jScrollPane1.setViewportView(dataTable);
        if (dataTable.getColumnModel().getColumnCount() > 0) {
            dataTable.getColumnModel().getColumn(0).setPreferredWidth(15);
            dataTable.getColumnModel().getColumn(2).setPreferredWidth(15);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(upperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1382, 688));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void viewAllDetailsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewAllDetailsLabelMouseClicked

        setDataTable();
    }//GEN-LAST:event_viewAllDetailsLabelMouseClicked

    private void HomeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeBtnMouseClicked

        ControlPanel next = new ControlPanel();
        next.setVisible(true);
        dispose();
    }//GEN-LAST:event_HomeBtnMouseClicked

    private void searchInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInputActionPerformed

    private void searchInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInputKeyReleased

        String keyword = searchInput.getText().toUpperCase();
        searchData(keyword);
    }//GEN-LAST:event_searchInputKeyReleased

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
            java.util.logging.Logger.getLogger(SearchInAllBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchInAllBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchInAllBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchInAllBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchInAllBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HomeBtn;
    private rojeru_san.complementos.RSTableMetro dataTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField searchInput;
    private javax.swing.JPanel upperPanel;
    private javax.swing.JLabel viewAllDetailsLabel;
    // End of variables declaration//GEN-END:variables
}
