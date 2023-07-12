package Adapters;

import Jframe.SignUpPage;
import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author HIMANSHU TYAGI BCA 2020 BATCH .
 */
public class dbhandler {
    
    private static Connection conn;
    private static PreparedStatement  pst;
    private static String sql;
    private static Boolean dbcreated;
//    private static ResultSet dbcreated;
    
    
    public static Connection getDbConnection() throws SQLException{
    
        
        if(conn == null){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS","root","");
            
            
            
//                creating table "users"
//                 "CREATE TABLE IF NOT EXISTS `LMS`.`USERS` (`userid` INT NULL AUTO_INCREMENT ,`username` VARCHAR(50) NULL ,"
//                        + " `password` VARCHAR(30) NULL , PRIMARY KEY (`userid`)) ENGINE = InnoDB;


                sql = "CREATE TABLE IF NOT EXISTS `LMS`.`USERS` (`userid` INT NULL AUTO_INCREMENT ,`username` VARCHAR(50) NULL ,"
                        + " `password` VARCHAR(30) NULL , PRIMARY KEY (`userid`)) ENGINE = InnoDB;";
                pst= conn.prepareStatement(sql);
                dbcreated = pst.execute();
                
                
//                creating table "masterdata"
//                 CREATE TABLE IF NOT EXISTS `LMS`.`masterdata` (`masterPassword` VARCHAR(100) NOT NULL ) ENGINE = InnoDB;

                    sql = " CREATE TABLE IF NOT EXISTS `LMS`.`masterdata` (`masterPassword` VARCHAR(100) NOT NULL ) ENGINE = InnoDB;";
                                   pst= conn.prepareStatement(sql);
                                   dbcreated = pst.execute();
                                   



//                creating table "books"
//                 CREATE TABLE IF NOT EXISTS `LMS`.`books` (`bookId` VARCHAR(100) NOT NULL , `bookName` VARCHAR(100) NOT NULL , `authorName` VARCHAR(100) NOT NULL , `publisherName` VARCHAR(100) NOT NULL , `supplierName` VARCHAR(100) NOT NULL , `costOfBook` DOUBLE NOT NULL , `billNumber` VARCHAR(100) NOT NULL , `remarks` VARCHAR(100) NOT NULL , `subjectOfBook` VARCHAR(100) NOT NULL , `edition` VARCHAR(20) NOT NULL , `publishYear` BIGINT(20) NOT NULL , `pages` INT(11) NOT NULL , `dateOfSupply` DATE NOT NULL ) ENGINE = InnoDB;

                    sql = " CREATE TABLE IF NOT EXISTS `LMS`.`books` (`bookId` VARCHAR(100) NOT NULL , `bookName` VARCHAR(100) NOT NULL ,"
                            + "     `authorName` VARCHAR(100) NOT NULL , `publisherName` VARCHAR(100) NOT NULL , `supplierName` VARCHAR(100) NOT NULL ,"
                            + "          `costOfBook` DOUBLE NOT NULL , `billNumber` VARCHAR(100) NOT NULL , `remarks` VARCHAR(100) NOT NULL , `subjectOfBook` VARCHAR(100) NOT NULL ,"
                            + "              `edition` VARCHAR(20) NOT NULL , `publishYear` BIGINT(20) NOT NULL ,"
                            + "                  `pages` INT(11) NOT NULL , `dateOfSupply` DATE NOT NULL , `issuedToStudId` VARCHAR(100) NOT NULL) ENGINE = InnoDB;";
                                   pst= conn.prepareStatement(sql);
                                   dbcreated = pst.execute();
                                  
//                  creating table "students"
//                  CREATE TABLE `lms`.`students` (`studentId` VARCHAR(100) NOT NULL , `studentName` VARCHAR(100) NOT NULL , `fatherName` VARCHAR(100) NOT NULL , `address` VARCHAR(100) NOT NULL , `phoneNo` VARCHAR(100) NOT NULL , `course` VARCHAR(100) NOT NULL , `rollNumber` VARCHAR(100) NOT NULL , `studentStatus` VARCHAR(100) NOT NULL , `booksIssued` VARCHAR(100) NOT NULL , `courseTerm` VARCHAR(20) NOT NULL , `currentYear` VARCHAR(20) NOT NULL , `runningSem` VARCHAR(11) NOT NULL , `dateOfBirth` DATE NOT NULL ) ENGINE = InnoDB;
                
                sql = " CREATE TABLE IF NOT EXISTS `lms`.`students` (`studentId` VARCHAR(100) NOT NULL , `studentName` VARCHAR(100) NOT NULL , "
                        + "`fatherName` VARCHAR(100) NOT NULL , "
                        + "`address` VARCHAR(100) NOT NULL , `phoneNo` VARCHAR(100) NOT NULL , `course` VARCHAR(100) NOT NULL , "
                        + "`rollNumber` VARCHAR(100) NOT NULL , "
                        + "`studentStatus` VARCHAR(100) NOT NULL , `booksIssued` VARCHAR(100) NOT NULL , `courseTerm` VARCHAR(20) NOT NULL , "
                        + "`currentYear` VARCHAR(20) NOT NULL , `runningSem` VARCHAR(11) NOT NULL , `dateOfBirth` DATE NOT NULL ) ENGINE = InnoDB;";
                                   pst= conn.prepareStatement(sql);
                                   dbcreated = pst.execute();
                                 
                                   
  //                  creating table "issuedBooks"
//                   CREATE TABLE `lms`.`issuedbooks` (`bookId` VARCHAR(50) NOT NULL , `studentId` VARCHAR(50) NOT NULL , `issueDate` DATE NOT NULL , `returnDate` DATE NOT NULL ) ENGINE = InnoDB;
               sql = " CREATE TABLE IF NOT EXISTS `lms`.`issuedbooks` (`bookId` VARCHAR(50) NOT NULL , `studentId` VARCHAR(50) NOT NULL ,"
                       + " `issueDate` DATE NOT NULL , `returnDate` DATE NOT NULL ) ENGINE = InnoDB;";
                                   pst= conn.prepareStatement(sql);
                                   dbcreated = pst.execute();
                                   
        }catch(ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbhandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         return conn;
}
     
}
