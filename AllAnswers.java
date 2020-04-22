/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

/**
 *
 * @author amritha
 */
public class AllAnswers extends JFrame {
    int qid, uid;
    JTextField answerTextField = new javax.swing.JTextField();
    
    public AllAnswers(int qid, int uid) {
        //NormalUser user = new NormalUser(userID);
        this.uid = uid;
        this.qid = qid;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 800));
        setMinimumSize(new java.awt.Dimension(600, 800));
        setPreferredSize(new java.awt.Dimension(600, 800));
        getContentPane().setLayout(null);
        
        getContentPane().add(new Util(uid).getHeadingPanel("All Answers"));
        getContentPane().add(getQandA());
        getContentPane().add(getInputAnswer());
    }
    
    public JPanel getQandA() {
        
        JPanel qAndA = new JPanel();
        qAndA.setBackground(new java.awt.Color(0, 0, 0));
        qAndA.setLayout(null);
        qAndA.setBounds(0, 100, 600, 600);
        JLabel questionLabel = new JLabel();
        questionLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        questionLabel.setForeground(new java.awt.Color(255, 255, 255));
        questionLabel.setText("QUESTION: " + new AmmaDAO().getQuestion(qid));
        questionLabel.setBounds(0, 0, 600, 40); 
        qAndA.add(questionLabel);
        
        String[][] res = new AmmaDAO().getAllAnswers(qid);
        String[] colNames = {"Answer", "CreatedBy", "id"};  
        
        JTable answersTable = new JTable(res, colNames);
        answersTable.setCellSelectionEnabled(false);
        setColumnWidth(answersTable);
        answersTable.removeColumn(answersTable.getColumn("id")); 
        answersTable.setBounds(0, 50, 600, 650);
        add(new JScrollPane(answersTable));
        //answersTable.setEnabled(false);
        qAndA.add(answersTable);
        return qAndA;
    }
    
    public void setColumnWidth(JTable t) {
        TableColumn col = t.getColumn("Answer");
        col.setMaxWidth(400);
        col.setMinWidth(400);
        
        col = t.getColumn("CreatedBy");
        col.setMaxWidth(100);
        col.setMinWidth(100);
        
        col = t.getColumn("id");
        col.setMaxWidth(100);
        col.setMinWidth(100);
        
    }
    
    
    public JPanel getInputAnswer() {
        JPanel answerPanel = new JPanel();
        JLabel answerLabel = new JLabel();
        
        JButton postButton = new javax.swing.JButton();
        
        answerPanel.setBackground(new java.awt.Color(0, 0, 0));
        answerPanel.setLayout(null);
        answerPanel.setBounds(0, 700, 600, 100);
        answerLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        answerLabel.setForeground(new java.awt.Color(255, 255, 255));
        answerLabel.setText("My Answer: ");
        answerLabel.setBounds(0, 0, 600, 20); 
        answerPanel.add(answerLabel);
        answerTextField.setBounds(0, 30, 600, 60);
        answerPanel.add(answerTextField);
        postButton.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        postButton.setText("Post Answer");
        postButton.setBounds(250, 90, 200, 10);
        answerPanel.add(postButton);
        postButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
               postActionPerformed(e);
               answerTextField.setText("");
            }
               
        });
        return answerPanel;
    }
    
    private void postActionPerformed(ActionEvent e) {
        
        String a = answerTextField.getText();
        //int tags = getTags();

        String res = new AmmaDAO().postMyAnswer(a, qid, uid);
        
        System.out.println(res);
        
    }
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AllAnswers(6, 1).setVisible(true);
            }
        });
    
    }
}
