/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoUsuario;
import Modelo.Usuarios;
import Vista.Login;
import Vista.MenuPrincipal;
import Vista.Registrarse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 * Controlador encargado del proceso de inicio de sesión.
 * Valida las credenciales ingresadas, gestiona la autenticación del usuario
 * y permite el acceso al sistema solo si los datos son correctos.
 * También maneja la navegación hacia la ventana de registro y la salida del sistema.
 * @author Usuario
 */


public class ControladorLogin implements ActionListener {
    
     private Login vista;
     private DaoUsuario dao;
     
     public ControladorLogin(Login vista ,DaoUsuario dao ){
         this.vista=vista;
         this.dao= dao;
         
         
         this.vista.getbtnIniciar().addActionListener(this);
         this.vista.getbtnRegistro().addActionListener(this);
         
         this.vista.getjLabelExit().addMouseListener(new MouseAdapter(){
           @Override 
           public void mouseClicked(MouseEvent e){
            salir();
           }
         
         });
    }
     
    @Override 
        public void actionPerformed(ActionEvent e ){
             if( e.getSource()== vista.getbtnIniciar()){
               autenticarUsuario();
             }else if( e.getSource()== vista.getbtnRegistro()){
               registrarUsuario();
             }
        }
        
    private void autenticarUsuario(){
        String correo = vista.getTextEmail().getText();
        String pass = new String(vista.getTextPassword().getPassword());

        Usuarios usuario = dao.autenticarUsuario(correo, pass);

        if(usuario != null){
            JOptionPane.showMessageDialog(null,"Bienvenido " + usuario.getNombre());
            abrirmenuPrincipal(usuario); 
        } else {
            JOptionPane.showMessageDialog(null,"Correo o contraseña incorrectos");
        }
    }

    
    private void abrirmenuPrincipal(Usuarios usuarioLogueado){
        MenuPrincipal menu = new MenuPrincipal(usuarioLogueado);  
        menu.setVisible(true);
       vista.dispose(); 
    }
        
    private void registrarUsuario(){
        Registrarse vistaRegistro = new Registrarse(); 
        ControladorRegistrar controlador = new ControladorRegistrar(vistaRegistro, dao); 
        vistaRegistro.setVisible(true); 
        vista.dispose(); 
    }
    
    private void salir(){
          vista.dispose();
    }
        
}