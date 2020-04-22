/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amma;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author amritha
 */
public class Util {
    
    int uid;

    public Util(int uid) {
        this.uid = uid;
    }

    
    
    public JPanel getHeadingPanel(String pageName) {
        
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(new java.awt.Color(0, 255, 255));
        headingPanel.setLayout(null);
        headingPanel.setBounds(0, 0, 600, 100);
        
        JLabel homeLabel = new JLabel();
        homeLabel.setFont(new java.awt.Font("Times New Roman", 1, 20)); 
        homeLabel.setForeground(new java.awt.Color(0, 0, 0));
        homeLabel.setBounds(300, 10, 100, 20);
        homeLabel.setText("Home");
        homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeLabel.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        new Dashboard(uid).setVisible(true);
                    }
                    catch(Exception ex) { }
            }
 
            @Override
                public void mouseEntered(MouseEvent e) {
                    homeLabel.setText("<html><a href=''>Home</a></html>");            
                }
 
            @Override
                public void mouseExited(MouseEvent e) {
                    homeLabel.setText("Home");
            }
        });
        headingPanel.add(homeLabel);
        
        JLabel usernameLabel = new JLabel();
        usernameLabel.setFont(new java.awt.Font("Times New Roman", 1, 20)); 
        usernameLabel.setForeground(new java.awt.Color(0, 0, 0));
        usernameLabel.setText(new AmmaDAO().getName(uid));
        usernameLabel.setBounds(400, 10, 100, 20);
        headingPanel.add(usernameLabel);
        
        JLabel logoutHyperlink = new JLabel(); 
        logoutHyperlink.setFont(new java.awt.Font("Times New Roman", 1, 20)); 
        logoutHyperlink.setForeground(new java.awt.Color(0, 0, 0));
        logoutHyperlink.setBounds(500, 10, 100, 20);
        logoutHyperlink.setText("Logout");
        logoutHyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutHyperlink.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        
                        new login().setVisible(true);
                    }
                    catch(Exception ex) { }
            }
 
            @Override
                public void mouseEntered(MouseEvent e) {
                    logoutHyperlink.setText("<html><a href=''>Logout</a></html>");            
                }
 
            @Override
                public void mouseExited(MouseEvent e) {
                    logoutHyperlink.setText("Logout");
            }
        });
        headingPanel.add(logoutHyperlink);
        
        JLabel headingLabel = new JLabel();
        headingLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        headingLabel.setForeground(new java.awt.Color(0, 0, 0));
        headingLabel.setText(pageName);
        headingLabel.setBounds(10, 30, 500, 70);
        headingPanel.add(headingLabel);
        
        return headingPanel;
    }
    
}
