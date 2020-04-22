
package com.amma;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author amritha
 */
public class AmmaDAO {
    
    String DB_PASSWORD = "";
    String DB_USERNAME = "root";
    String DB_URL = "jdbc:mysql://localhost:3306/Package5thSem";

    public int Login(String uname, String pwd) {
    
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        int uid = 0;
        String query = "SELECT ID FROM USERS1 WHERE NAME = ? AND USERPASSWORD = ?";

        // Authenticate b 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = con.prepareStatement(query);
            stmt.setString(1, uname);
            stmt.setString(2, pwd);

            rs = stmt.executeQuery();
            while (rs.next()) {
                uid = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return uid;
    }
    
    public String[][] getAllQuestions() {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String [][] res = new String[12][4];
        String query = "select description, no_of_answers, id, created_by from questions1 order by created_on";
         
        // Authenticate  
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = con.prepareStatement(query);
            
            
            rs = stmt.executeQuery();
            int x = 0;
            while (rs.next()) {
                res[x][0] = rs.getString("description");
                res[x][1] = Integer.toString(rs.getInt("no_of_answers"));  
                res[x][2] = Integer.toString(rs.getInt("id"));
                res[x++][3] = Integer.toString(rs.getInt("created_by"));
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return res;
   
    }
    
    
    
    public String[][] getAllMyQuestions(int uid) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String [][] res = new String[12][3];
        String query = "select description, no_of_answers, id from questions1 where " +
                "created_by = ? order by created_on desc";
        
        // Authenticate  
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = con.prepareStatement(query);
            stmt.setInt(1, uid);
            
            rs = stmt.executeQuery();
            int x = 0;
            while (rs.next()) {
                res[x][0] = rs.getString("description");
                res[x][1] = Integer.toString(rs.getInt("no_of_answers"));  
                res[x++][2] = Integer.toString(rs.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return res;
   
    }
    
    public String[][] getMyQuestions(int uid) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String [][] res = new String[3][3];
        String query = "select description, no_of_answers, id from questions1 where " +
                "created_by = ? order by created_on desc limit 3";
         
        // Authenticate  
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = con.prepareStatement(query);
            stmt.setInt(1, uid);
            
            rs = stmt.executeQuery();
            int x = 0;
            while (rs.next()) {
                res[x][0] = rs.getString("description");
                res[x][1] = Integer.toString(rs.getInt("no_of_answers"));  
                res[x++][2] = Integer.toString(rs.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return res;
   
    }
    
    public String postMyQuestion(String question, int uid) {
        
        Connection con = null;
        int rs;
        PreparedStatement stmt = null;
        String query = "insert into questions1 (description, created_by, created_on) values (?, ?, sysdate())";
        
        // Authenticate
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = con.prepareStatement(query);
            stmt.setString(1, question);
            stmt.setInt(2, uid);
            
            rs = stmt.executeUpdate();
            
            System.out.println("saved " + rs);
            
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            closeConnection(con, stmt, null);
        }
        
        return "";
    }
    
    public String postMyAnswer(String answer, int qid, int uid) {
        
        Connection con = null;
        int rs;
        PreparedStatement stmt = null;
        String query = "insert into answers (description, question_id, created_by, created_on) values (?, ?, ?, sysdate())";
        
        // Authenticate
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = con.prepareStatement(query);
            stmt.setString(1, answer);
            stmt.setInt(2, qid);
            stmt.setInt(3, uid);
            
            rs = stmt.executeUpdate();
            
            System.out.println("saved " + rs);
            
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            closeConnection(con, stmt, null);
        }
        
        return "";
    }
    
    
    private void closeConnection(Connection con, Statement stmt, ResultSet rs) {
        
        try {
            if (stmt != null) 
                stmt.close();
            if (rs != null)
                rs.close();
            if (con != null)
                con.close();
        } catch(Exception e) {
            // no need to handle the exception.
        }
    }
    
    public String getQuestion(int qid) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String query = "select description from questions1 where " + "id = ?";
        String question = "";
         
        // Authenticate  
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = con.prepareStatement(query);
            stmt.setInt(1, qid);
            
            rs = stmt.executeQuery();
            int x = 0;
            while (rs.next()) {
                question = rs.getString("description");
            }
        
        }
        catch(Exception e) {
            
        }
        return question;
    }
    
    public String[][] getAllAnswers(int qid) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String [][] res = new String[12][3];
        String query = "select description, created_by, id from answers where " +
                "question_id = ? order by created_on desc";
        
        // Authenticate  
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = con.prepareStatement(query);
            stmt.setInt(1, qid);
            
            rs = stmt.executeQuery();
            int x = 0;
            while (rs.next()) {
                res[x][0] = rs.getString("description");
                res[x][1] = Integer.toString(rs.getInt("created_by"));  
                res[x++][2] = Integer.toString(rs.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return res;
   
    }
    
    String getName(int uid) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String query = "select name from users1 where " +
                "ID = ?";
        String name = "";
        // Authenticate  
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = con.prepareStatement(query);
            stmt.setInt(1, uid);
            
            rs = stmt.executeQuery();
            int x = 0;
            while (rs.next()) {
                name = rs.getString("name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return name;
    }
}
