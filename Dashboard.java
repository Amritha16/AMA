package com.amma;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton; 
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

public class Dashboard extends javax.swing.JFrame {
    
    int uid;
    JTextField questionTextField;
    JToggleButton cppToggleButton;
    JToggleButton cToggleButton;
    JToggleButton javaToggleButton;
    JToggleButton pythonToggleButton;
    JToggleButton sqlToggleButton;
    JToggleButton othersToggleButton;

    public Dashboard(int uid) {
        //NormalUser user = new NormalUser(userID);
        this.uid = uid;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 800));
        setMinimumSize(new java.awt.Dimension(600, 800));
        setPreferredSize(new java.awt.Dimension(600, 800));
        getContentPane().setLayout(null);
        
        getContentPane().add(new Util(uid).getHeadingPanel("DASHBOARD"));
        getContentPane().add(getMyQuestionsPanel());
        getContentPane().add(getPostQuestionPanel());
        getContentPane().add(getViewAllPanel());
        
        pack();
        getContentPane().setVisible(true);
    }
    
    public JPanel getMyQuestionsPanel() {
        JLabel myQuestionsLabel = new javax.swing.JLabel();
        JLabel allMyQuestionsHyperlink = new javax.swing.JLabel();
        JPanel myQuestionsPanel = new javax.swing.JPanel();
        
        myQuestionsPanel.setBackground(new java.awt.Color(255, 255, 255));
        myQuestionsPanel.setLayout(null);
        
        myQuestionsLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        myQuestionsLabel.setForeground(new java.awt.Color(255, 255, 255));
        myQuestionsLabel.setText("My Questions");
        myQuestionsLabel.setBounds(0, 10, 500, 30);
        myQuestionsPanel.add(myQuestionsLabel);
        
        String[][] res = new AmmaDAO().getMyQuestions(uid);
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
        
        setColumnWidth(questionsTable);
        questionsTable.removeColumn(questionsTable.getColumn("id"));
        questionsTable.setBounds(0, 50, 600, 250);
    
        myQuestionsPanel.add(questionsTable);
        
        allMyQuestionsHyperlink.setForeground(new java.awt.Color(0, 0, 255));
        allMyQuestionsHyperlink.setFont(new java.awt.Font("Times New Roman", 1, 20)); 
        allMyQuestionsHyperlink.setBounds(0, 310, 400, 20);
        allMyQuestionsHyperlink.setText("All My Questions");
        allMyQuestionsHyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        allMyQuestionsHyperlink.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        new MyQuestions("All My Questions", uid).setVisible(true);
                    }
                    catch(Exception ex) { }
            }
 
            @Override
                public void mouseEntered(MouseEvent e) {
                    allMyQuestionsHyperlink.setText("<html><a href=''>All My Questions</a></html>");            
                }
 
            @Override
                public void mouseExited(MouseEvent e) {
                    allMyQuestionsHyperlink.setText("All My Questions");
            }
        });
        myQuestionsPanel.add(allMyQuestionsHyperlink);
        
        myQuestionsPanel.setBounds(0, 100, 600, 350);
        return myQuestionsPanel;
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
    
    public JPanel getPostQuestionPanel() {
        JPanel postQuestionPanel = new javax.swing.JPanel();
        JLabel postQuestionLabel = new javax.swing.JLabel();
        questionTextField = new javax.swing.JTextField();
        JButton postButton = new javax.swing.JButton();
        
        postQuestionPanel.setBackground(new java.awt.Color(0, 255, 255));
        postQuestionPanel.setLayout(null);
        postQuestionPanel.setBounds(0, 450, 600, 250);
        
        postQuestionLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        postQuestionLabel.setForeground(new java.awt.Color(0, 0, 0));
        postQuestionLabel.setText("Post My Question");
        postQuestionLabel.setBounds(0, 0, 600, 30); 
        postQuestionPanel.add(postQuestionLabel);

        questionTextField.setBounds(0, 50, 500, 100);
        postQuestionPanel.add(questionTextField);
         
        /*cppToggleButton = new JToggleButton("C++"); 
        cppToggleButton.setBounds(10, 160, 60, 30);
        postQuestionPanel.add(cppToggleButton);
        
        cToggleButton = new JToggleButton("C");
        cToggleButton.setBounds(70, 160, 60, 30);
        postQuestionPanel.add(cToggleButton);
  
        javaToggleButton = new JToggleButton("Java");
        javaToggleButton.setBounds(130, 160, 60, 30);
        postQuestionPanel.add(javaToggleButton);
        
        sqlToggleButton = new JToggleButton("SQL");   
        sqlToggleButton.setBounds(190, 160, 60, 30);
        postQuestionPanel.add(sqlToggleButton);
        
        othersToggleButton = new JToggleButton("Others");
        othersToggleButton.setBounds(250, 160, 100, 30);
        postQuestionPanel.add(othersToggleButton);*/

        postButton.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        postButton.setText("Post Question");
        postButton.addActionListener(new ActionListener() {
           
           public void actionPerformed(ActionEvent e) {
               postActionPerformed(e);
               //JDialog postedQuestion = new JDialog(null, "Question Posted Successfully");
               questionTextField.setText("");
           }
               
        });
             
        postButton.setBounds(10, 200, 200, 30);
        postQuestionPanel.add(postButton);
        return postQuestionPanel;
    }
    
    private void postActionPerformed(ActionEvent e) {
        
        String q = questionTextField.getText();
        //int tags = getTags();

        String res = new AmmaDAO().postMyQuestion(q, uid);
        
        System.out.println(res);
        
    }
    
    /*private int getTags() {
        
        // C++, C, Java, Python, SQL, Others 
        int b = 0;
        
        b = b | (othersToggleButton.isSelected() ? 1 : 0);
        b = b | (sqlToggleButton.isSelected() ? 2 : 0);
//        b = b | (pythonToggleButton.isSelected() ? 4 : 0);
        b = b | (javaToggleButton.isSelected() ? 8 : 0);
        b = b | (cToggleButton.isSelected() ? 16 : 0);
        b = b | (cppToggleButton.isSelected() ? 32 : 0);
        
        return b;
    }
    
    private void setTags(int b) {
        
        othersToggleButton.setSelected(b & 1);
        sqlToggleButton.setSelected(b & 2);
        
        
    
    }*/

    public JPanel getViewAllPanel() {
        JPanel viewAllPanel = new javax.swing.JPanel();
        JLabel viewAllQuestionsHyperlink = new javax.swing.JLabel();
        JTextArea postQuestion = new JTextArea();
        
        viewAllPanel.setBackground(new java.awt.Color(0, 255, 255));
        viewAllPanel.setLayout(null);
        viewAllPanel.setBounds(0, 700, 600, 100);
        
        viewAllQuestionsHyperlink.setForeground(new java.awt.Color(0, 0, 255));
        viewAllQuestionsHyperlink.setBackground(new java.awt.Color(0 ,0 ,0));
        viewAllQuestionsHyperlink.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        viewAllQuestionsHyperlink.setText("View All Questions");
        viewAllQuestionsHyperlink.setBounds(0, 10, 200, 40);
        viewAllQuestionsHyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewAllQuestionsHyperlink.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        new MyQuestions("View All Questions", uid).setVisible(true);
                    }
                    catch(Exception ex) { }
            }
 
            @Override
                public void mouseEntered(MouseEvent e) {
                    viewAllQuestionsHyperlink.setText("<html><a href=''>View All Questions</a></html>");            
                }
 
            @Override
                public void mouseExited(MouseEvent e) {
                    viewAllQuestionsHyperlink.setText("View All Questions");
            }
        });
        viewAllPanel.add(viewAllQuestionsHyperlink);
        return viewAllPanel;
    }
    
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard(1).setVisible(true);
            }
        });
    }
  
}
