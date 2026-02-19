/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import RSMaterialComponent.RSButtonMaterialUno;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class Registrarse extends javax.swing.JFrame {

    
    public Registrarse() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public RSButtonMaterialUno getbtnRegistrar(){
        return btnRegistrar; 
    }
    
    public RSButtonMaterialUno  getbtnIniciar(){
        return btnIniciar;
    }
    public JTextField getTextNombre(){
        return TextNombre;
    }
    
    public JTextField getTextCorreo(){
        return TextCorreo;
    } 
    
    public JTextField getTextCargo(){
        return TextCargo; 
    }
    
    public JTextField getTextCelular(){
        return TextCelular;
    }
    
    public  JPasswordField getTextPassword(){
        return TextPassword;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelencabezado = new javax.swing.JLabel();
        jLabelnombre = new javax.swing.JLabel();
        jLabelcorreo = new javax.swing.JLabel();
        jLabelcargo = new javax.swing.JLabel();
        jLabelpass = new javax.swing.JLabel();
        jLabelcelular = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        TextCelular = new javax.swing.JTextField();
        TextCorreo = new javax.swing.JTextField();
        TextCargo = new javax.swing.JTextField();
        btnIniciar = new RSMaterialComponent.RSButtonMaterialUno();
        btnRegistrar = new RSMaterialComponent.RSButtonMaterialUno();
        jLabel2 = new javax.swing.JLabel();
        TextPassword = new javax.swing.JPasswordField();
        TextNombre = new javax.swing.JTextField();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(3, 10, 23));

        jLabelencabezado.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabelencabezado.setForeground(new java.awt.Color(255, 255, 255));
        jLabelencabezado.setText("Crea tu usuario ");

        jLabelnombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabelnombre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelnombre.setText("Nombre Completo");

        jLabelcorreo.setBackground(new java.awt.Color(255, 255, 255));
        jLabelcorreo.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabelcorreo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelcorreo.setText("Correo");

        jLabelcargo.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabelcargo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelcargo.setText("Cargo");

        jLabelpass.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabelpass.setForeground(new java.awt.Color(255, 255, 255));
        jLabelpass.setText("Contraseña ");

        jLabelcelular.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabelcelular.setForeground(new java.awt.Color(255, 255, 255));
        jLabelcelular.setText("Celular");

        jLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel.setForeground(new java.awt.Color(153, 153, 153));
        jLabel.setText("¿Ya tienes una cuenta ?");

        TextCelular.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N

        TextCorreo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N

        TextCargo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N

        btnIniciar.setBackground(new java.awt.Color(3, 10, 23));
        btnIniciar.setBorder(null);
        btnIniciar.setText("Iniciar sesión");
        btnIniciar.setRound(15);

        btnRegistrar.setText("Registrarse");
        btnRegistrar.setRound(15);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ImagenLogo.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelencabezado)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextCargo)
                                    .addComponent(TextCorreo)
                                    .addComponent(TextCelular)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelnombre)
                                            .addComponent(jLabelcorreo)
                                            .addComponent(jLabelcargo)
                                            .addComponent(jLabelpass)
                                            .addComponent(jLabelcelular))
                                        .addGap(0, 230, Short.MAX_VALUE))
                                    .addComponent(TextPassword)
                                    .addComponent(TextNombre))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabelencabezado)
                .addGap(18, 18, 18)
                .addComponent(jLabelnombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabelcorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabelcargo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelcelular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelpass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(17, 17, 17)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel))
                .addGap(69, 69, 69))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrarse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TextCargo;
    private javax.swing.JTextField TextCelular;
    private javax.swing.JTextField TextCorreo;
    private javax.swing.JTextField TextNombre;
    private javax.swing.JPasswordField TextPassword;
    private RSMaterialComponent.RSButtonMaterialUno btnIniciar;
    private RSMaterialComponent.RSButtonMaterialUno btnRegistrar;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelcargo;
    private javax.swing.JLabel jLabelcelular;
    private javax.swing.JLabel jLabelcorreo;
    private javax.swing.JLabel jLabelencabezado;
    private javax.swing.JLabel jLabelnombre;
    private javax.swing.JLabel jLabelpass;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
