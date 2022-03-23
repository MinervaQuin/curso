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

/**
 *
 * @author TESTER
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        imagen = new javax.swing.JLabel();
        welcome = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoEmail = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        textoContraseña = new javax.swing.JLabel();
        contraseña = new javax.swing.JPasswordField();
        Login = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/fondo_login.jpg"))); // NOI18N
        imagen.setText("jLabel1");

        welcome.setText("Welcome back");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Login to your account");

        textoEmail.setForeground(new java.awt.Color(153, 153, 153));
        textoEmail.setText("Email");

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        textoContraseña.setForeground(new java.awt.Color(153, 153, 153));
        textoContraseña.setText("Password");

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

        jLabel9.setText("Dont have an account? Join freee today");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 586, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(welcome)
                            .addComponent(jLabel5)
                            .addComponent(textoEmail)
                            .addComponent(textoContraseña)
                            .addComponent(email)
                            .addComponent(contraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(40, 40, 40))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(welcome)
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
                .addComponent(textoEmail)
                .addGap(18, 18, 18)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(textoContraseña)
                .addGap(18, 18, 18)
                .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Login)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {                                      
        String email_text= email.getText();
        String contraseña_text = String.valueOf(contraseña.getPassword());
        Pattern pat_email = Pattern.compile("[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,4}");
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
        }else{
            System.out.println("Email = " + email_text);
            System.out.println("Contraseña = " + contraseña_text);
            HerokuUsersSqlConnection conex = HerokuUsersSqlConnection.getInstance("jdbc:mysql://i54jns50s3z6gbjt.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/iy5jkxqnaep9jd04" + "?useSSL=false", "e3uv1gsfsu6r8v6j", "ud6g593tlbsh3knw");
            try {
                System.out.println("--- Entra en login ---");
                if(conex.login(email_text,contraseña_text)){
                    System.out.println("--- Sale de login ---");
                    this.setVisible(false);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }                                     

    private void contraseñaActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
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

    // Variables declaration - do not modify                     
    private javax.swing.JButton Login;
    private javax.swing.JPasswordField contraseña;
    private javax.swing.JTextField email;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel textoContraseña;
    private javax.swing.JLabel textoEmail;
    private javax.swing.JLabel welcome;
    // End of variables declaration                   
}