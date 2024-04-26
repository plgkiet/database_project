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

public class paymentFrame extends javax.swing.JFrame {

    private String username;

    public paymentFrame(String username) {
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
            Logger.getLogger(paymentFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(paymentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        paymentField = new javax.swing.JTextField();
        payBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        loanNumberField = new javax.swing.JTextField();
        exitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("PAYMENT");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("AMOUNT:");

        payBtn.setText("PAY");
        payBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("LOAN NUMBER:");

        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(payBtn)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paymentField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loanNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exitBtn))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(loanNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(paymentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payBtn)
                    .addComponent(exitBtn))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBtnActionPerformed
        try {
            double paymentAmount = Double.parseDouble(paymentField.getText());

            // Fetch the current balance from savings_account
            String selectQuery = "SELECT balance FROM savings_account WHERE account_number = ?";
            st = con.prepareStatement(selectQuery);
            st.setString(1, username);
            rs = st.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");

                // Check if the account has sufficient balance for the payment
                if (currentBalance >= paymentAmount) {
                    // Deduct the payment amount from the balance
                    double updatedBalance = currentBalance - paymentAmount;

                    // Update the balance in the database
                    String updateQuery = "UPDATE savings_account SET balance = ? WHERE account_number = ?";
                    st = con.prepareStatement(updateQuery);
                    st.setDouble(1, updatedBalance);
                    st.setString(2, username);
                    st.executeUpdate();

                    // Insert payment information into the Payment table
                    insertPaymentInformation(paymentAmount);
                    updateLoanAmount(loanNumberField.getText(), paymentAmount); // New method to update loan_amount

                    JOptionPane.showMessageDialog(this, "Payment successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(paymentFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error during payment", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_payBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        this.dispose();
        new customerInfo(username).setVisible(true);    }//GEN-LAST:event_exitBtnActionPerformed
   
    private void insertPaymentInformation(double paymentAmount) throws SQLException {
        // Generate a unique loan_payment_number
        int loanPaymentNumber = generateLoanPaymentNumber();

        // Get the current date
        String currentDate = getCurrentDate();

        // Insert payment information into the Payment table
        String insertQuery = "INSERT INTO Payment (loan_number, loan_payment_number, payment_date, amount) VALUES (?, ?, ?, ?)";
        st = con.prepareStatement(insertQuery);
        st.setString(1, loanNumberField.getText()); // Replace with the actual loan_number
        st.setInt(2, loanPaymentNumber);
        st.setString(3, currentDate);
        st.setDouble(4, paymentAmount);
        st.executeUpdate();
    }

    private int generateLoanPaymentNumber() throws SQLException {
        // Fetch the current loan payment number for the specified loan_number
        String selectQuery = "SELECT MAX(loan_payment_number) FROM Payment WHERE loan_number = ?";
        st = con.prepareStatement(selectQuery);
        st.setString(1, loanNumberField.getText()); // Replace with the actual loan_number
        rs = st.executeQuery();

        if (rs.next()) {
            // Increment the existing loan payment number
            return rs.getInt(1) + 1;
        } else {
            // Start with 1 if there are no existing payments
            return 1;
        }
    }

    private void updateLoanAmount(String loanNumber, double paymentAmount) throws SQLException {
        // Fetch the current loan amount
        String selectQuery = "SELECT loan_amount FROM loan WHERE loan_number = ?";
        st = con.prepareStatement(selectQuery);
        st.setString(1, loanNumberField.getText());
        rs = st.executeQuery();

        if (rs.next()) {
            double currentLoanAmount = rs.getDouble("loan_amount");

            // Calculate the new loan amount after deducting the payment amount
            double updatedLoanAmount = currentLoanAmount - paymentAmount;

            // Update the loan amount in the loan table
            String updateQuery = "UPDATE loan SET loan_amount = ? WHERE loan_number = ?";
            st = con.prepareStatement(updateQuery);
            st.setDouble(1, updatedLoanAmount);
            st.setString(2, loanNumber);
            st.executeUpdate();
        } else {
            throw new SQLException("Error fetching current loan amount");
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField loanNumberField;
    private javax.swing.JButton payBtn;
    private javax.swing.JTextField paymentField;
    // End of variables declaration//GEN-END:variables
}
