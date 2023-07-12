package Jframe;

import Adapters.dbhandler;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author HIMANSHU TYAGI BCA SEM 5 ROLL NO 200954106032.
 */
public class ReturnBooks extends javax.swing.JFrame {

    private ResultSet rst;
    private static Connection conn;
    private PreparedStatement pst;
    private String bookId, subject, studentId, afterReturnId, studentStatus, stdid, sql;
    private int booksIssuedTillNow;

    /**
     * Creates new form IssueBooks
     */
    public ReturnBooks() {
        try {
            conn = dbhandler.getDbConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("resources/SCZ.png")));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

    }

    private void getData() {

        bookId = bookIdEditText.getText();
        studentId = studentIdEditText.getText();

    }

    private void getBookDetails() {

        bookId = bookIdEditText.getText();

        try {

            pst = conn.prepareStatement("Select * from `books` where bookId = '" + bookId + "';");
            rst = pst.executeQuery();
            if (rst.next()) {

                bookId = rst.getString("bookId");
                setBookIdLabel.setText(bookId);
                setBookNamelabel.setText(rst.getString("bookName"));
                setAuthorLabel.setText(rst.getString("authorName"));
                studentId = rst.getString("issuedToStudId");// here
                setIssuedToLabel.setText(studentId);
                subject = rst.getString("subjectOfBook");
                setSubjectLabel.setText(subject);
                afterReturnId = studentId;
            } else {
                BooksResultBox.setForeground(Color.WHITE);
                BooksResultBox.setText(" No such Book found in DataBase.");
                Timer timer = new Timer(3000, (ActionEvent arg0) -> {
                    BooksResultBox.setText("");
                });
                timer.setRepeats(false); // Only execute once
                timer.start();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void doclick(Boolean afterReturn, Boolean firstUsage) {


        if (firstUsage) {
            getBookDetails();
            getStudentDetails(afterReturn, true);
        } else {
            getStudentDetails(afterReturn, false);
            getBookDetails();
        }

    }

    private void performDelete() {

        try {

            // checking if book is alreday issued or not, if not then throw error .
            sql = " select * from `issuedbooks` where bookId Like '" + bookId + "';";
            pst = conn.prepareStatement(sql);
            Boolean result = pst.execute();
            if (!result) {

                resultBoxLabel.setForeground(Color.white);
                resultBoxLabel.setText("NO SUCH BOOK IS ISSUED TILL NOW! ");
                Timer timer = new Timer(3000, (ActionEvent arg0) -> {
                    resultBoxLabel.setText("");
                });
                timer.setRepeats(false); // Only execute once
                timer.start();
            } else {

//                if book exist in issued table then procede.
                sql = "Delete  from `issuedbooks` where bookId Like '" + bookId + "';";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();

                pst = conn.prepareStatement("Update `books` SET `issuedToStudId` = 'None' where bookId = '" + bookId + "';");
                pst.executeUpdate();

                // checking data in student table
                pst = conn.prepareStatement("select * from `students` where `studentId` = ?;");
                pst.setString(1, studentId);
                rst = pst.executeQuery();
                while (rst.next()) {

                    booksIssuedTillNow = rst.getInt("booksIssued");
                    studentStatus = rst.getString("studentStatus");
//                    System.out.println(booksIssuedTillNow);
//                    System.out.println(studentStatus);
                }

                // decreasing issued book value by -1
                // UPDATE `students` SET `booksIssued`= booksIssued-1  WHERE studentId = 'bca6a32';
                // fetch issuedbooks from student table decreement it and after that update the table student again .
                booksIssuedTillNow--;

                pst = conn.prepareStatement("UPDATE `students` SET `booksIssued` = ? , `studentStatus` = ? where `studentId` = ?;");
                pst.setInt(1, booksIssuedTillNow);
                if (booksIssuedTillNow <= 0) {
                    studentStatus = "FINAL";
                    System.out.println(booksIssuedTillNow);
                    System.out.println(studentStatus);
                }
                pst.setString(2, studentStatus);
                pst.setString(3, studentId);
                int updateRowCount = pst.executeUpdate();
                if (updateRowCount > 0) {
                    resultBoxLabel.setText(" Book successfully returned !");
                    resultBoxLabel.setForeground(Color.white);
                    Timer timer = new Timer(3000, (ActionEvent arg0) -> {
                        resultBoxLabel.setText(" ");
                    });
                    timer.setRepeats(false); // Only execute once
                    timer.start();

                    doclick(true, false);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void getStudentDetails(Boolean afterReturn, Boolean findByBookDetail) {

        try {

            if (afterReturn) {
                studentId = afterReturnId;
            } else {
                if (findByBookDetail) {
                    studentId = setIssuedToLabel.getText();
                } else {
                    studentId = setStudentIdLabel.getText();
                }
            }
            pst = conn.prepareStatement("Select * from `students` where `studentId` = '" + studentId + "';");
            rst = pst.executeQuery();
            while (rst.next()) {
                setStudentIdLabel.setText(rst.getString("studentId"));
                setStudentNamelabel.setText(rst.getString("studentName"));
                setCourseLabel.setText(rst.getString("course"));
                setIssuedBooksLabel.setText(String.valueOf(rst.getInt("booksIssued")));
                setStudentStatusLabel.setText(rst.getString("studentStatus"));
                System.out.println(" inside while student details block");
            }

            pst = conn.prepareStatement("Select * from `students` where `studentId` = '" + studentId + "';");
            Boolean reslt = pst.execute();
            if (!reslt) {
                studentResultBox.setForeground(Color.CYAN);
                studentResultBox.setText(" No such student found in DataBase.");
                Timer timer = new Timer(3000, (ActionEvent arg0) -> {
                    studentResultBox.setText("");
                });
                timer.setRepeats(false); // Only execute once
                timer.start();

            }
        } catch (SQLException ex) {
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

        bottomPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        booksDetailsLabel = new javax.swing.JLabel();
        HomeBtn = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        bookNamelabel = new javax.swing.JLabel();
        bookIdLabel = new javax.swing.JLabel();
        subjectLabel = new javax.swing.JLabel();
        authorLabel = new javax.swing.JLabel();
        issuedToLabel = new javax.swing.JLabel();
        setBookIdLabel = new javax.swing.JLabel();
        setBookNamelabel = new javax.swing.JLabel();
        setAuthorLabel = new javax.swing.JLabel();
        setIssuedToLabel = new javax.swing.JLabel();
        setSubjectLabel = new javax.swing.JLabel();
        BooksResultBox = new javax.swing.JLabel();
        RightPanel = new javax.swing.JPanel();
        issueBookLabel = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        issueStudentIdlabel = new javax.swing.JLabel();
        issueBookIdLabel = new javax.swing.JLabel();
        studentIdEditText = new app.bolivia.swing.JCTextField();
        bookIdEditText = new app.bolivia.swing.JCTextField();
        searchStudentDataBtn = new javax.swing.JButton();
        returnBookBtn = new javax.swing.JButton();
        issueBookIdLabel1 = new javax.swing.JLabel();
        resultBoxLabel = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        studentDetailsLabel = new javax.swing.JLabel();
        setIssuedBooksLabel = new javax.swing.JLabel();
        setCourseLabel = new javax.swing.JLabel();
        setStudentNamelabel = new javax.swing.JLabel();
        studentStatusLabel = new javax.swing.JLabel();
        setStudentStatusLabel = new javax.swing.JLabel();
        studentIdLabel = new javax.swing.JLabel();
        studentNamelabel = new javax.swing.JLabel();
        booksIssuedLabel = new javax.swing.JLabel();
        setStudentIdLabel = new javax.swing.JLabel();
        courseLabel = new javax.swing.JLabel();
        studentResultBox = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Return Books");
        setMinimumSize(new java.awt.Dimension(1366, 770));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bottomPanel.setBackground(new java.awt.Color(255, 255, 255));
        bottomPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        leftPanel.setBackground(new java.awt.Color(255, 51, 51));
        leftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        booksDetailsLabel.setFont(new java.awt.Font("Segoe UI", 3, 28)); // NOI18N
        booksDetailsLabel.setForeground(new java.awt.Color(255, 255, 255));
        booksDetailsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        booksDetailsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        booksDetailsLabel.setText("Books Details");
        leftPanel.add(booksDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 290, 100));

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
        leftPanel.add(HomeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 137, 33));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        leftPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 203, 260, 10));

        bookNamelabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        bookNamelabel.setForeground(new java.awt.Color(255, 255, 255));
        bookNamelabel.setText("Name :");
        leftPanel.add(bookNamelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        bookIdLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        bookIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        bookIdLabel.setText("Acc. No :");
        leftPanel.add(bookIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        subjectLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        subjectLabel.setForeground(new java.awt.Color(255, 255, 255));
        subjectLabel.setText("Subject :");
        leftPanel.add(subjectLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 90, -1));

        authorLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        authorLabel.setForeground(new java.awt.Color(255, 255, 255));
        authorLabel.setText("Author :");
        leftPanel.add(authorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        issuedToLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        issuedToLabel.setForeground(new java.awt.Color(255, 255, 255));
        issuedToLabel.setText("Issued To  :");
        leftPanel.add(issuedToLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 110, -1));

        setBookIdLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        setBookIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        setBookIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftPanel.add(setBookIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 220, 30));

        setBookNamelabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        setBookNamelabel.setForeground(new java.awt.Color(255, 255, 255));
        setBookNamelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftPanel.add(setBookNamelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 270, 30));

        setAuthorLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        setAuthorLabel.setForeground(new java.awt.Color(255, 255, 255));
        setAuthorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftPanel.add(setAuthorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 230, 30));

        setIssuedToLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        setIssuedToLabel.setForeground(new java.awt.Color(255, 255, 255));
        setIssuedToLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftPanel.add(setIssuedToLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 240, 30));

        setSubjectLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        setSubjectLabel.setForeground(new java.awt.Color(255, 255, 255));
        setSubjectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftPanel.add(setSubjectLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 240, 30));

        BooksResultBox.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        BooksResultBox.setForeground(new java.awt.Color(255, 255, 255));
        BooksResultBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftPanel.add(BooksResultBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 370, 30));

        bottomPanel.add(leftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 700));

        RightPanel.setBackground(new java.awt.Color(102, 255, 204));
        RightPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        issueBookLabel.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        issueBookLabel.setForeground(new java.awt.Color(255, 255, 255));
        issueBookLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        issueBookLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        issueBookLabel.setText("Return Book");
        RightPanel.add(issueBookLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 270, 110));

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        RightPanel.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 260, 10));

        issueStudentIdlabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        issueStudentIdlabel.setForeground(new java.awt.Color(255, 255, 255));
        issueStudentIdlabel.setText("Student Id :");
        RightPanel.add(issueStudentIdlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        issueBookIdLabel.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        issueBookIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        issueBookIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        issueBookIdLabel.setText("OR");
        RightPanel.add(issueBookIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 170, -1));

        studentIdEditText.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        studentIdEditText.setPlaceholder("Enter Student Id");
        studentIdEditText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                studentIdEditTextFocusLost(evt);
            }
        });
        studentIdEditText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentIdEditTextActionPerformed(evt);
            }
        });
        RightPanel.add(studentIdEditText, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 300, 40));

        bookIdEditText.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        bookIdEditText.setPlaceholder("Enter Accession  Number");
        bookIdEditText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                bookIdEditTextFocusLost(evt);
            }
        });
        bookIdEditText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookIdEditTextActionPerformed(evt);
            }
        });
        RightPanel.add(bookIdEditText, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 300, 40));

        searchStudentDataBtn.setBackground(new java.awt.Color(204, 255, 153));
        searchStudentDataBtn.setFont(new java.awt.Font("Segoe Print", 3, 24)); // NOI18N
        searchStudentDataBtn.setForeground(new java.awt.Color(255, 51, 51));
        searchStudentDataBtn.setText("Search Student Data");
        searchStudentDataBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        searchStudentDataBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchStudentDataBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchStudentDataBtnMouseClicked(evt);
            }
        });
        searchStudentDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStudentDataBtnActionPerformed(evt);
            }
        });
        RightPanel.add(searchStudentDataBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 290, 30));

        returnBookBtn.setBackground(new java.awt.Color(204, 255, 153));
        returnBookBtn.setFont(new java.awt.Font("Segoe Print", 3, 24)); // NOI18N
        returnBookBtn.setForeground(new java.awt.Color(255, 51, 51));
        returnBookBtn.setText("Return Book");
        returnBookBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        returnBookBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        returnBookBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnBookBtnMouseClicked(evt);
            }
        });
        returnBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBookBtnActionPerformed(evt);
            }
        });
        RightPanel.add(returnBookBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 210, 30));

        issueBookIdLabel1.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        issueBookIdLabel1.setForeground(new java.awt.Color(255, 255, 255));
        issueBookIdLabel1.setText(" Book's Acc. No. :");
        RightPanel.add(issueBookIdLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 170, -1));

        resultBoxLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        resultBoxLabel.setForeground(new java.awt.Color(255, 255, 255));
        resultBoxLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RightPanel.add(resultBoxLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 540, 30));

        bottomPanel.add(RightPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 540, 700));

        centerPanel.setBackground(new java.awt.Color(153, 51, 255));
        centerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        centerPanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 290, 10));

        studentDetailsLabel.setFont(new java.awt.Font("Segoe UI", 3, 28)); // NOI18N
        studentDetailsLabel.setForeground(new java.awt.Color(255, 255, 255));
        studentDetailsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentDetailsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        studentDetailsLabel.setText("Student's Details");
        centerPanel.add(studentDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 330, -1));

        setIssuedBooksLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        setIssuedBooksLabel.setForeground(new java.awt.Color(255, 255, 255));
        setIssuedBooksLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        centerPanel.add(setIssuedBooksLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, 220, 30));

        setCourseLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        setCourseLabel.setForeground(new java.awt.Color(255, 255, 255));
        setCourseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        centerPanel.add(setCourseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 220, 30));

        setStudentNamelabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        setStudentNamelabel.setForeground(new java.awt.Color(255, 255, 255));
        setStudentNamelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        centerPanel.add(setStudentNamelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 220, 30));

        studentStatusLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        studentStatusLabel.setForeground(new java.awt.Color(255, 255, 255));
        studentStatusLabel.setText("Student Status :");
        centerPanel.add(studentStatusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 160, -1));

        setStudentStatusLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        setStudentStatusLabel.setForeground(new java.awt.Color(255, 255, 255));
        setStudentStatusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        centerPanel.add(setStudentStatusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 520, 220, 30));

        studentIdLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        studentIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        studentIdLabel.setText("Student Id :");
        centerPanel.add(studentIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        studentNamelabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        studentNamelabel.setForeground(new java.awt.Color(255, 255, 255));
        studentNamelabel.setText("Name :");
        centerPanel.add(studentNamelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        booksIssuedLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        booksIssuedLabel.setForeground(new java.awt.Color(255, 255, 255));
        booksIssuedLabel.setText("Books Issued :");
        centerPanel.add(booksIssuedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 140, -1));

        setStudentIdLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        setStudentIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        setStudentIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        centerPanel.add(setStudentIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 220, 30));

        courseLabel.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        courseLabel.setForeground(new java.awt.Color(255, 255, 255));
        courseLabel.setText("Course :");
        centerPanel.add(courseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 80, -1));

        studentResultBox.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        studentResultBox.setForeground(new java.awt.Color(255, 255, 255));
        studentResultBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        centerPanel.add(studentResultBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 380, 30));

        bottomPanel.add(centerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 400, 700));

        getContentPane().add(bottomPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 720));

        setSize(new java.awt.Dimension(1382, 728));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void HomeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeBtnMouseClicked

        ControlPanel next = new ControlPanel();
        next.setVisible(true);
        dispose();
    }//GEN-LAST:event_HomeBtnMouseClicked

    private void studentIdEditTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentIdEditTextActionPerformed

        stdid = studentIdEditText.getText().toUpperCase();
        new SearchStudent(stdid).setVisible(true);
        dispose();

    }//GEN-LAST:event_studentIdEditTextActionPerformed

    private void bookIdEditTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookIdEditTextActionPerformed

        doclick(false, true);
        returnBookBtn.requestFocus();
    }//GEN-LAST:event_bookIdEditTextActionPerformed

    private void searchStudentDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStudentDataBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchStudentDataBtnActionPerformed

    private void searchStudentDataBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchStudentDataBtnMouseClicked

// WE ARE SWITCHING USER TO ANOTHER FRAME CALLED SEARCH STUDENT  :
        new SearchStudent().setVisible(true);
        dispose();
    }//GEN-LAST:event_searchStudentDataBtnMouseClicked

    private void bookIdEditTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bookIdEditTextFocusLost


    }//GEN-LAST:event_bookIdEditTextFocusLost

    private void studentIdEditTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentIdEditTextFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_studentIdEditTextFocusLost

    private void returnBookBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnBookBtnMouseClicked

        performDelete();
    }//GEN-LAST:event_returnBookBtnMouseClicked

    private void returnBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBookBtnActionPerformed
        // TODO add your handling code here:

        performDelete();

    }//GEN-LAST:event_returnBookBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ReturnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ReturnBooks().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BooksResultBox;
    private javax.swing.JLabel HomeBtn;
    private javax.swing.JPanel RightPanel;
    private javax.swing.JLabel authorLabel;
    private app.bolivia.swing.JCTextField bookIdEditText;
    private javax.swing.JLabel bookIdLabel;
    private javax.swing.JLabel bookNamelabel;
    private javax.swing.JLabel booksDetailsLabel;
    private javax.swing.JLabel booksIssuedLabel;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel courseLabel;
    private javax.swing.JLabel issueBookIdLabel;
    private javax.swing.JLabel issueBookIdLabel1;
    private javax.swing.JLabel issueBookLabel;
    private javax.swing.JLabel issueStudentIdlabel;
    private javax.swing.JLabel issuedToLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel resultBoxLabel;
    private javax.swing.JButton returnBookBtn;
    private javax.swing.JButton searchStudentDataBtn;
    private javax.swing.JLabel setAuthorLabel;
    private javax.swing.JLabel setBookIdLabel;
    private javax.swing.JLabel setBookNamelabel;
    private javax.swing.JLabel setCourseLabel;
    private javax.swing.JLabel setIssuedBooksLabel;
    private javax.swing.JLabel setIssuedToLabel;
    private javax.swing.JLabel setStudentIdLabel;
    private javax.swing.JLabel setStudentNamelabel;
    private javax.swing.JLabel setStudentStatusLabel;
    private javax.swing.JLabel setSubjectLabel;
    private javax.swing.JLabel studentDetailsLabel;
    private app.bolivia.swing.JCTextField studentIdEditText;
    private javax.swing.JLabel studentIdLabel;
    private javax.swing.JLabel studentNamelabel;
    private javax.swing.JLabel studentResultBox;
    private javax.swing.JLabel studentStatusLabel;
    private javax.swing.JLabel subjectLabel;
    // End of variables declaration//GEN-END:variables
}
