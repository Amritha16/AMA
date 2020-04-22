package com.amma;

import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;


public class MyQuestions extends JFrame {
    
    String message;
    int uid;
    public MyQuestions(String message, int uid) {
        this.uid = uid;
        this.message = message;
        
        //NormalUser user = new NormalUser(userID);
        initComponents();
    } 
    
    @SuppressWarnings("unchecked")

    private void initComponents() {
     
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 800));
        setMinimumSize(new java.awt.Dimension(600, 800));
        setPreferredSize(new java.awt.Dimension(600, 800));
        getContentPane().setLayout(null);
        
        getContentPane().add(new Util(uid).getHeadingPanel(message));
        
        getContentPane().add(getQuestionsTable());
        
        
        pack();
        
        
    }
    
    public JPanel getQuestionsTable() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 100, 600, 700);
        JTable questionsTable;
        if(message == "View All Questions")
            questionsTable = viewAllQuestions();
        else
            questionsTable = viewAllMyQuestions();
        
        setColumnWidth(questionsTable);
        questionsTable.setBounds(10, 50, 580, 650);
        panel.add(questionsTable);
        return panel;
    }
    
    private void setColumnWidth(JTable qTable) {
        
        TableColumn col = qTable.getColumn("Question");
        col.setMaxWidth(400);
        col.setMinWidth(400);
        
        col = qTable.getColumn("No of Answers");
        col.setMaxWidth(100);
        col.setMinWidth(100);
        
        col = qTable.getColumn("id");
        col.setMaxWidth(100);
        col.setMinWidth(100);
    }
    
    private void questionsTableMouseClicked(java.awt.event.MouseEvent evt, JTable questionsTable) {                                          
       // get the selected row
       if (evt.getClickCount() == 2) {
            int index = questionsTable.getSelectedRow();
            int qid = Integer.parseInt((String)questionsTable.getModel().getValueAt(index, 2));
            new AllAnswers(qid, uid).setVisible(true);
       }
        
    }

    public JTable viewAllQuestions() {
        String[][] res = new AmmaDAO().getAllQuestions();
        String[] colNames = {"Question", "No of Answers", "id", "createdBy"};  
        
        JTable questionsTable = new JTable(res, colNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        questionsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                questionsTableMouseClicked(evt, questionsTable);
            }
        });
        return questionsTable;
    }
    
    public JTable viewAllMyQuestions() {
        String[][] res = new AmmaDAO().getAllMyQuestions(uid);
        String[] colNames = {"Question", "No of Answers", "id"};  
        
        JTable questionsTable = new JTable(res, colNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        questionsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                questionsTableMouseClicked(evt, questionsTable);
            }
        });
        return questionsTable;
    }
    
    
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyQuestions("View All Questions", 1).setVisible(true);
            }
        });
    
    }
    
    

    
}
