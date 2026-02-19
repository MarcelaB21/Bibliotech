/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ControladorCategoria;
import Controlador.ControladorHome;
import Controlador.ControladorLector;
import Controlador.ControladorLibro;
import Controlador.ControladorPerfil;
import Controlador.ControladorPrestamo;
import Dao.DaoCategoria;
import Dao.DaoLector;
import Dao.DaoLibro;
import Dao.DaoPrestamo;
import Dao.DaoUsuario;
import Modelo.Usuarios;
import java.awt.BorderLayout;


/**
 *
 * @author Usuario
 */
public class MenuPrincipal extends javax.swing.JFrame {
    private Usuarios usuarioLogueado; 
   
    public MenuPrincipal( Usuarios usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.usuarioLogueado = usuario; 
        
        FrmHome vistaHome =new FrmHome();
        ControladorHome controladorHome= new  ControladorHome(vistaHome, usuarioLogueado);
        vistaHome .setSize(739, 566);
        vistaHome .setLocation(0,0);
        
        contenedor.removeAll();
        contenedor.add(vistaHome ,BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();
    }
    
    public Usuarios getUsuarioLogueado() {
        return usuarioLogueado;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnHome = new RSMaterialComponent.RSButtonMaterialIconDos();
        btnCategoria = new RSMaterialComponent.RSButtonMaterialIconDos();
        btnPrestamo = new RSMaterialComponent.RSButtonMaterialIconDos();
        btnLector = new RSMaterialComponent.RSButtonMaterialIconDos();
        btnLibro = new RSMaterialComponent.RSButtonMaterialIconDos();
        btnPerfil = new RSMaterialComponent.RSButtonMaterialIconDos();
        btnSalir = new RSMaterialComponent.RSButtonMaterialIconDos();
        jLabel1 = new javax.swing.JLabel();
        contenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanelMenu.setBackground(new java.awt.Color(3, 10, 23));

        btnHome.setBackground(new java.awt.Color(3, 10, 23));
        btnHome.setText("Home");
        btnHome.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HOME);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnCategoria.setBackground(new java.awt.Color(3, 10, 23));
        btnCategoria.setText("Categoria");
        btnCategoria.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.VIEW_LIST);
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });

        btnPrestamo.setBackground(new java.awt.Color(3, 10, 23));
        btnPrestamo.setText("Prestamo");
        btnPrestamo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DESCRIPTION);
        btnPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestamoActionPerformed(evt);
            }
        });

        btnLector.setBackground(new java.awt.Color(3, 10, 23));
        btnLector.setText("Lector");
        btnLector.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PEOPLE);
        btnLector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLectorActionPerformed(evt);
            }
        });

        btnLibro.setBackground(new java.awt.Color(3, 10, 23));
        btnLibro.setText("Libro");
        btnLibro.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLibroActionPerformed(evt);
            }
        });

        btnPerfil.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PERSON);
        btnPerfil.setRound(50);
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(3, 10, 23));
        btnSalir.setText("Salir");
        btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ImagenMenu.JPG"))); // NOI18N

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnLector, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(37, 37, 37))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        contenedor.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 725, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
         
        btnHome.setSelected(true);
        btnLibro.setSelected(false);
        btnCategoria.setSelected(false);
        btnLector.setSelected(false);
        btnPrestamo.setSelected(false);
        btnPerfil.setSelected(false);
        
        
        FrmHome vistaHome =new FrmHome();
        ControladorHome controladorHome= new  ControladorHome(vistaHome, usuarioLogueado);
        vistaHome.setSize(739, 566);
        vistaHome.setLocation(0,0);
        
        contenedor.removeAll();
        contenedor.add(vistaHome,BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();
       
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnLectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLectorActionPerformed
        
        btnLector.setSelected(true);
        btnHome.setSelected(false);
        btnLibro.setSelected(false);
        btnCategoria.setSelected(false);
        btnPrestamo.setSelected(false);
        btnPerfil.setSelected(false);
        
        FrmLector vistaLector= new FrmLector();
        DaoLector daolector = new DaoLector();
        ControladorLector controladorLector = new ControladorLector(vistaLector, daolector, usuarioLogueado);
        vistaLector.setSize(739, 566);
        vistaLector.setLocation(0,0);
        
        contenedor.removeAll();
        contenedor.add(vistaLector,BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();  
    }//GEN-LAST:event_btnLectorActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        
        btnCategoria.setSelected(true);
        btnLector.setSelected(false);
        btnHome.setSelected(false);
        btnLibro.setSelected(false);
        btnPrestamo.setSelected(false);
        btnPerfil.setSelected(false);
        
        
        FrmCategoria vistaCategoria = new FrmCategoria();
        DaoCategoria daoCategoria = new DaoCategoria();
        ControladorCategoria controladorCategoria = new ControladorCategoria(vistaCategoria, daoCategoria,usuarioLogueado);
        vistaCategoria .setSize(739, 566);
        vistaCategoria .setLocation(0,0);
        
        contenedor.removeAll();
        contenedor.add(  vistaCategoria, BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void btnLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLibroActionPerformed
        btnLibro.setSelected(true);
        btnCategoria.setSelected(false);
        btnLector.setSelected(false);
        btnHome.setSelected(false);
        btnPrestamo.setSelected(false);
        btnPerfil.setSelected(false);
        
        
        FrmLibro vistaLibro=new FrmLibro();
        DaoLibro daoLibro = new DaoLibro();
        DaoCategoria daocategoria = new DaoCategoria();
        ControladorLibro contraladorLibro = new ControladorLibro(vistaLibro, daoLibro, daocategoria,usuarioLogueado);
        vistaLibro.setSize(739, 566);
        vistaLibro.setLocation(0,0);
        
        contenedor.removeAll();
        contenedor.add(vistaLibro,BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btnLibroActionPerformed

    private void btnPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestamoActionPerformed
        btnPrestamo.setSelected(true);
        btnLibro.setSelected(false);
        btnCategoria.setSelected(false);
        btnLector.setSelected(false);
        btnHome.setSelected(false);
        btnPerfil.setSelected(false);
        
        FrmPrestamo vistaPrestamo= new FrmPrestamo();
        DaoPrestamo daoprestamo = new DaoPrestamo();
        DaoLector daolector= new DaoLector();
        DaoLibro daolibro = new DaoLibro();
        ControladorPrestamo controladorPrestamo= new ControladorPrestamo(vistaPrestamo, daoprestamo, daolector, daolibro,usuarioLogueado);
        vistaPrestamo.setSize(739, 566);
        vistaPrestamo.setLocation(0,0);
        
        contenedor.removeAll();
        contenedor.add(vistaPrestamo,BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btnPrestamoActionPerformed

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        btnPerfil.setSelected(true);
        btnPrestamo.setSelected(false);
        btnLibro.setSelected(false);
        btnCategoria.setSelected(false);
        btnLector.setSelected(false);
        btnHome.setSelected(false);
        
        FrmPerfil vistaPerfil=new FrmPerfil();
        DaoUsuario daousuario = new DaoUsuario ();
        ControladorPerfil controladorPerfil = new ControladorPerfil(vistaPerfil, daousuario, usuarioLogueado);
        vistaPerfil.setSize(739, 566);
        vistaPerfil.setLocation(0,0);
        
        contenedor.removeAll();
        contenedor.add(vistaPerfil,BorderLayout.CENTER );
        contenedor.revalidate();
        contenedor.repaint();
       
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
     
    
    
    
    /**
     * @param args the command line arguments
     */
   
    
   /*public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MenuPrincipal().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconDos btnCategoria;
    private RSMaterialComponent.RSButtonMaterialIconDos btnHome;
    private RSMaterialComponent.RSButtonMaterialIconDos btnLector;
    private RSMaterialComponent.RSButtonMaterialIconDos btnLibro;
    private RSMaterialComponent.RSButtonMaterialIconDos btnPerfil;
    private RSMaterialComponent.RSButtonMaterialIconDos btnPrestamo;
    private RSMaterialComponent.RSButtonMaterialIconDos btnSalir;
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
