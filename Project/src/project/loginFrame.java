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
import project.customerInfo;
import project.employeeInfo;

public class loginFrame extends javax.swing.JFrame {

    private String username;

    /**
     * Creates new form loginFrame
     */
    public loginFrame() {
        initComponents();
        connect();
    }

    public String getUsername() {
        return username;
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
            Logger.getLogger(loginFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(loginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        userTypeComboBox = new javax.swing.JComboBox<>();
        regisBtn = new javax.swing.JButton();
        loginBtn = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Login");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Username: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Password:");

        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        userTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT", "Customer", "Employee", " " }));

        regisBtn.setText("Register");
        regisBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regisBtnActionPerformed(evt);
            }
        });

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(154, Short.MAX_VALUE)
                        .addComponent(loginBtn)
                        .addGap(18, 18, 18)
                        .addComponent(regisBtn)
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                            .addComponent(passwordField))))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(195, 195, 195))
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(userTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(userTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regisBtn)
                    .addComponent(loginBtn))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        String username = usernameField.getText(); // Lấy username từ JTextField
        String password = new String(passwordField.getPassword()); // Lấy password từ JPasswordField
        String userType = (String) userTypeComboBox.getSelectedItem(); // Lấy loại người dùng từ JComboBox

        if ("SELECT".equals(userType)) {
            JOptionPane.showMessageDialog(this, "Please select a user type.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (authenticate(username, password, userType)) {
            // Đăng nhập thành công, thực hiện điều gì đó (ví dụ: mở một cửa sổ mới)
            this.username = username;
            JOptionPane.showMessageDialog(getRootPane(), "Login successful!", "Information", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Đóng loginFrame
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    if ("Customer".equals(userType)) {
                        new customerInfo(username).setVisible(true);
                    } else if ("Employee".equals(userType)) {
                        new employeeInfo(username).setVisible(true);
                    }
                }
            });
        } else {
            // Đăng nhập thất bại, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Login failed. Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loginBtnActionPerformed

    private void regisBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regisBtnActionPerformed
        // TODO add your handling code here:
        String userType = (String) userTypeComboBox.getSelectedItem();
        // Kiểm tra giá trị của JComboBox và mở cửa sổ đăng ký tương ứng
        if ("SELECT".equals(userType)) {
            JOptionPane.showMessageDialog(getRootPane(), "Please select your field!", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if ("Employee".equals(userType)) {
            // Tắt cửa sổ đăng nhập
            this.dispose();
            // Mở cửa sổ đăng ký Employee
            new employeeRegistration().setVisible(true);
        } else if ("Customer".equals(userType)) {
            // Tắt cửa sổ đăng nhập
            this.dispose();
            // Mở cửa sổ đăng ký Customer
            new customerRegistration().setVisible(true);
        }
    }//GEN-LAST:event_regisBtnActionPerformed

    private boolean authenticate(String username, String password, String userType) {
        try {
            String tableName = ""; // Tên bảng dữ liệu tương ứng với loại người dùng
            String idColumnName = "";

            // Xác định bảng dữ liệu cần truy vấn dựa trên loại người dùng
            if ("Customer".equals(userType)) {
                tableName = "customer";
                idColumnName = "customer_id";
            } else if ("Employee".equals(userType)) {
                tableName = "employee";
                idColumnName = "employee_id";
            } else {
                // Loại người dùng không hợp lệ
                return false;
            }
            // Truy vấn CSDL để kiểm tra thông tin đăng nhập
            String query = "SELECT * FROM " + tableName + " WHERE " + idColumnName + " = ? AND dob = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            // Nếu có ít nhất một kết quả, đăng nhập thành công
            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(employeeRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Mặc định là false nếu có lỗi
    }

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
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton regisBtn;
    private javax.swing.JComboBox<String> userTypeComboBox;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
