/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design;

import SqlDatabase.HerokuUsersSqlConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author TESTER
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public User userSignedIn;
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagen = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        contraseña = new javax.swing.JPasswordField();
        Login = new javax.swing.JButton();
        SignUp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/imagenes/fondo_login.jpg"))); // NOI18N
        imagen.setText("jLabel1");

        jLabel2.setText("Welcome back");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Login to your account");

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Email");

        email.setToolTipText("");
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Password");

        contraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contraseñaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                contraseñaFocusLost(evt);
            }
        });
        contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contraseñaActionPerformed(evt);
            }
        });

        Login.setText("Login now");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        SignUp.setText("Dont have an account? Join free today");
        SignUp.setBorderPainted(false);
        SignUp.setContentAreaFilled(false);
        SignUp.setFocusPainted(false);
        SignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 658, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(contraseña)
                            .addComponent(email))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SignUp)
                        .addGap(75, 75, 75))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(Login)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SignUp)
                .addContainerGap(379, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed

    }//GEN-LAST:event_emailActionPerformed

    private void SignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpActionPerformed
        Sign_up su = new Sign_up();
        su.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_SignUpActionPerformed

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        String email_text= email.getText();
        String contraseña_text = String.valueOf(contraseña.getPassword());
        User userSignedIn = new User();
        
        /*Pattern pat_email = Pattern.compile("[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,4}");
        Matcher mat_email = pat_email.matcher(email_text);
        Pattern pat_password = Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,15}$");
        Matcher mat_password = pat_password.matcher(contraseña_text);
        if(email_text.isEmpty()){
            JOptionPane.showMessageDialog(null, "El correo es obligatorio");
            System.out.println("El correo es obligatorio");
        }else if(!mat_email.matches()){
            JOptionPane.showMessageDialog(null, "Se esperaba una dirección de correo electrónico");
            System.out.println("Se esperaba una dirección de correo electrónico");
        }else if(contraseña_text.isEmpty()){
            JOptionPane.showMessageDialog(null, "La contraseña es obligatoria");
            System.out.println("La contraseña es obligatoria");
        }else if(contraseña_text.length() < 8){
            JOptionPane.showMessageDialog(null, "La contraseña debe tener más de 8 dígitos");
            System.out.println("La contraseña debe tener más de 8 dígitos");
        }else if(contraseña_text.length() > 15){
            JOptionPane.showMessageDialog(null, "La contraseña debe tener menos de 15 dígitos");
            System.out.println("La contraseña debe tener menos de 15 dígitos");
        }else if(!mat_password.matches()){
            JOptionPane.showMessageDialog(null, "Se esperaba una contraseña con mínimo una minúscula, una masyúscula, un dígito numérico y sin espacios");
            System.out.println("Se esperaba una contraseña con mínimo una minúscula, una masyúscula, un dígito numérico y sin espacios");
        }else{*/
            System.out.println("Email = " + email_text);
            System.out.println("Contraseña = " + contraseña_text);
            HerokuUsersSqlConnection conex_us = HerokuUsersSqlConnection.getInstance();
            try {
                userSignedIn.setEmail(email_text);
                userSignedIn.setPwd(contraseña_text);
                userSignedIn.setId(conex_us.getUserIdByEmail(email_text));
                userSignedIn.toString();
                if(conex_us.login(userSignedIn)){
                    JOptionPane.showMessageDialog(null, "Login correcto");
                    System.out.println("Login correcto");
                    MainPage mp = new MainPage();
                    mp.userSignedUp=userSignedIn;
                    mp.setVisible(true);
                    this.setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null, "Login incorrecto");
                    System.out.println("Login incorrecto");                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
       //  }
    }//GEN-LAST:event_LoginActionPerformed

    private void contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contraseñaActionPerformed
        
    }//GEN-LAST:event_contraseñaActionPerformed

    private void emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusGained
        /* if(email.getText().equals("Write your email")){
            email.setText("");
        }*/
    }//GEN-LAST:event_emailFocusGained

    private void contraseñaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contraseñaFocusGained
        /* if(contraseña.getPassword().equals("jPasswordField1")){
            contraseña.setText("");
        } */
    }//GEN-LAST:event_contraseñaFocusGained

    private void emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusLost
        /* if(email.getText().equals("")){
            email.setText("Write your email");
        } */
    }//GEN-LAST:event_emailFocusLost

    private void contraseñaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contraseñaFocusLost
        /* if(contraseña.getPassword().equals("")){
            contraseña.setText("jPasswordField1");
        } */
    }//GEN-LAST:event_contraseñaFocusLost
    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Login;
    private javax.swing.JButton SignUp;
    private javax.swing.JPasswordField contraseña;
    private javax.swing.JTextField email;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}