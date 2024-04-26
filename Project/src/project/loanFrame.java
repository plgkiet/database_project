/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project;

import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loanFrame extends javax.swing.JFrame {

    private String username;

    public loanFrame(String username) {
        initComponents();
        connect();
        this.username = username;
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
            Logger.getLogger(loanFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(loanFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean hasSavingsAccountInBranch(String branchID) {
        try {
            String query = "SELECT COUNT(*) AS count FROM savings_account WHERE account_number = ? AND branch_id = ?";
            st = con.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, branchIDComboBox.getSelectedItem().toString());
            rs = st.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0; // Returns true if the customer has a savings account in the specified branch
            }
        } catch (SQLException ex) {
            Logger.getLogger(loanFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void createLoan(String loanNumber, double loanAmount, String branchID) {
        try {
            // Insert the loan information into the database
            String insertLoanQuery = "INSERT INTO loan (loan_number, branch_id, loan_amount) VALUES (?, ?, ?)";
            st = con.prepareStatement(insertLoanQuery);
            st.setString(1, loanNumber);
            st.setString(2, branchID);
            st.setDouble(3, loanAmount);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(loanFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean validateLoanCreation(double loanAmount, String branchID) {
        // Check if the customer has a savings account in the same branch
        // (You need to have a method to check this in your database)

        // Validate the loan amount
        if (loanAmount < 500 || loanAmount > 10000) {
            JOptionPane.showMessageDialog(this, "Loan amount must be between $500 and $10,000", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private String generateRandomLoanNumber() {
        // Generate a random loan number (you can customize this based on your requirements)
        Random random = new Random();
        int loanNumber = 100000 + random.nextInt(900000); // Generates a 6-digit random number
        return "LN" + loanNumber;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        loanField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        createBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        branchIDComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("LOAN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("LOAN AMOUNT:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("BRANCH ID:");

        createBtn.setText("CREATE");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });

        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        branchIDComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT", "Ha101", "Ha102", "HCM101", "HCM102", "HCM103", "TDM101" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(loanField)
                                    .addComponent(branchIDComboBox, 0, 197, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(createBtn)
                                .addGap(48, 48, 48)
                                .addComponent(exitBtn)))))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(loanField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(branchIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createBtn)
                    .addComponent(exitBtn))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
        double loanAmount = Double.parseDouble(loanField.getText());
        String branchID = branchIDComboBox.getSelectedItem().toString();

        if (validateLoanCreation(loanAmount, branchID) && hasSavingsAccountInBranch(branchID)) {
            String loanNumber = generateRandomLoanNumber();
            createLoan(loanNumber, loanAmount, branchID);

            JOptionPane.showMessageDialog(this, "Loan created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }    }//GEN-LAST:event_createBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        this.dispose();
        new customerInfo(username).setVisible(true);    }//GEN-LAST:event_exitBtnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> branchIDComboBox;
    private javax.swing.JButton createBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField loanField;
    // End of variables declaration//GEN-END:variables
}
