/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project;

import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class account extends javax.swing.JFrame {

    private String username;

    /**
     * Creates new form account
     */
    public account(String username) {
        initComponents();
        connect();
        this.username = username;
        setSavingAccountInfo();
        setCurrentAccountInfo();
        transBtn1.addActionListener((e) -> transferFromSavings());
        transBtn2.addActionListener((e) -> transferToSavings());

    }

    Connection con; // Declare the connection variable.
    PreparedStatement st; // Declare the PreparedStatement variable.
    ResultSet rs; // Declare the ResultSet variable.
    // To perform JDBC connection define a method with name connect()

    public void connect() {
        try {
            // loading mysql driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            // creating connection with database
            con
                    = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "301103");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setSavingAccountInfo() {
        try {
            String query = "SELECT balance, branch_id FROM savings_account WHERE account_number=?";
            st = con.prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();

            if (rs.next()) {
                getBalance1.setText(rs.getString("balance"));
                getBranchID1.setText(rs.getString("branch_id"));
            } else {
                // Handle the case where no data is found for savings account
                JOptionPane.showMessageDialog(this, "No savings account information found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setCurrentAccountInfo() {
        try {
            String query = "SELECT balance, branch_id FROM current_account WHERE account_number=?";
            st = con.prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();

            if (rs.next()) {
                getBalance2.setText(rs.getString("balance"));
                getBranchID2.setText(rs.getString("branch_id"));
            } else {
                // Handle the case where no data is found for current account
                JOptionPane.showMessageDialog(this, "No current account information found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void transferFromSavings() {
        try {
            double transferAmountValue = Double.parseDouble(transferAmount.getText());

            // Check if the savings amount is greater than or equal to $50
            // Check if the savings account balance is sufficient
            double savingsBalance = Double.parseDouble(getBalance1.getText());

            if (savingsBalance < 50) {
                JOptionPane.showMessageDialog(this, "Savings amount cannot be less than $50", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (savingsBalance - transferAmountValue < 200) {
                JOptionPane.showMessageDialog(this, "Transfer not allowed. Savings balance should be at least $200 after transfer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Perform the transfer and update the database
            String updateSavingsQuery = "UPDATE savings_account SET balance = balance - ? WHERE account_number = ?";
            st = con.prepareStatement(updateSavingsQuery);
            st.setDouble(1, transferAmountValue);
            st.setString(2, username);
            st.executeUpdate();

            String updateCurrentQuery = "UPDATE current_account SET balance = balance + ? WHERE account_number = ?";
            st = con.prepareStatement(updateCurrentQuery);
            st.setDouble(1, transferAmountValue);
            st.setString(2, username);
            st.executeUpdate();

            setSavingAccountInfo(); // Update displayed information
            setCurrentAccountInfo(); // Update displayed information

            JOptionPane.showMessageDialog(this, "Transfer successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error during transfer", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void transferToSavings() {
        try {
            double transferAmountValue = Double.parseDouble(transferAmount2.getText());

            // Perform the transfer and update the database
            String updateSavingsQuery = "UPDATE savings_account SET balance = balance + ? WHERE account_number = ?";
            st = con.prepareStatement(updateSavingsQuery);
            st.setDouble(1, transferAmountValue);
            st.setString(2, username);
            st.executeUpdate();

            String updateCurrentQuery = "UPDATE current_account SET balance = balance - ? WHERE account_number = ?";
            st = con.prepareStatement(updateCurrentQuery);
            st.setDouble(1, transferAmountValue);
            st.setString(2, username);
            st.executeUpdate();

            setSavingAccountInfo(); // Update displayed information
            setCurrentAccountInfo(); // Update displayed information

            JOptionPane.showMessageDialog(this, "Transfer to savings successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error during transfer to savings", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ... (your existing code)
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        transBtn2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        transferAmount2 = new javax.swing.JTextField();
        getBalance2 = new javax.swing.JLabel();
        getBranchID2 = new javax.swing.JLabel();
        exitBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        getBranchID1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        getBalance1 = new javax.swing.JLabel();
        transBtn1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        transferAmount = new javax.swing.JTextField();
        ExitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("CURENT ACCOUNT");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("YOUR BALANCE:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setText("BRANCH ID:");

        transBtn2.setText("TRANSFER");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel10.setText("TRANSFER AMOUNT");

        getBalance2.setText("None");

        getBranchID2.setText("None");

        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(getBalance2)
                                    .addComponent(getBranchID2)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(transferAmount2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(transBtn2)
                        .addGap(18, 18, 18)
                        .addComponent(exitBtn)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(getBalance2))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(getBranchID2))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(transferAmount2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transBtn2)
                    .addComponent(exitBtn))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CURRENT ACCOUNT", jPanel2);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("SAVING ACCOUNT");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("YOUR BALANCE");

        getBranchID1.setText("None");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("BRANCH ID");

        getBalance1.setText("None");

        transBtn1.setText("TRANSFER");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setText("TRANSFER AMOUNT");

        ExitBtn.setText("EXIT");
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(transBtn1)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(getBranchID1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getBalance1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ExitBtn)))
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(getBalance1))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(getBranchID1))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transBtn1)
                    .addComponent(ExitBtn))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SAVING ACCOUNT", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed
        this.dispose();
        new customerInfo(username).setVisible(true);    }//GEN-LAST:event_ExitBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        this.dispose();
        new customerInfo(username).setVisible(true);     }//GEN-LAST:event_exitBtnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel getBalance1;
    private javax.swing.JLabel getBalance2;
    private javax.swing.JLabel getBranchID1;
    private javax.swing.JLabel getBranchID2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton transBtn1;
    private javax.swing.JButton transBtn2;
    private javax.swing.JTextField transferAmount;
    private javax.swing.JTextField transferAmount2;
    // End of variables declaration//GEN-END:variables
}
