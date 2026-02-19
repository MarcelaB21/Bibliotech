/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoUsuario;
import Modelo.Usuarios;
import Util.Mensajes;
import Validador.ValidadorRegistro;
import Vista.Login;
import Vista.Registrarse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Controlador para el registro de nuevos usuarios. Se encarga de validar los
 * datos ingresados, verificar si el usuario puede registrarse y coordinar la
 * creación de cuentas mediante el DAO correspondiente
 * @author Usuario
 */
public class ControladorRegistrar implements ActionListener {
    
    private Registrarse vista; 
    private DaoUsuario dao;
    private Usuarios usuario;
    
    
    public ControladorRegistrar(Registrarse vista,DaoUsuario dao){
        this.vista=vista;
        this.dao=dao;
        
        this.vista.getbtnRegistrar().addActionListener(this);
        
        this.vista.getbtnIniciar().addActionListener(this);
        
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == vista.getbtnRegistrar()){
         registrarUsuario(); 
      }else if(e.getSource() == vista.getbtnIniciar()){
        iniciarSesion();
      }
    }
    
     
    
    private void registrarUsuario(){
       

        
        String nombre = vista.getTextNombre().getText().trim();
        String celular = vista.getTextCelular().getText().trim();
        String correo = vista.getTextCorreo().getText().trim();
        String cargo = vista.getTextCargo().getText().trim();
        char[] passChars = vista.getTextPassword().getPassword(); 
   

        
        if (!ValidadorRegistro.camposCompletos(nombre, celular, correo, cargo, passChars)) {
                Mensajes.mostrarError("Todos los campos deben ser llenados.");
               return;
            }

        if(!ValidadorRegistro.validarNombre(nombre)) {
            Mensajes.mostrarError("El nombre debe tener entre 3 y 50 caracteres.");
            return;
        }
    
        if(!ValidadorRegistro.validarCelular(celular)){
            Mensajes.mostrarError("Número de celular no válido. Solo números y entre 7 y 10 dígitos.");
            return;
        }
    
        if(!ValidadorRegistro.validarCorreo(correo)){
            Mensajes.mostrarError("Correo no válido. Debe tener formato correcto y terminar en .com, .org, .net, etc.");
            return;
        }
    
        if(!ValidadorRegistro.validarCargo(cargo)){
            Mensajes.mostrarError("El cargo debe tener entre 2 y 30 caracteres.");
            return;
        }
    
        String password = new String(passChars);// Convertimos temporalmente la contraseña para validarla
     
        if(!ValidadorRegistro.validarSeguridadContraseña(password)){
            Mensajes.mostrarError("La contraseña no cumple con los requisitos de seguridad.\nDebe tener mínimo 8 caracteres, una mayúscula, una minúscula, un número y un símbolo.");
            Arrays.fill(passChars, ' '); // Limpiamos memoria por seguridad
            return;
        }
     
     
        usuario = new Usuarios(); 
        usuario.setNombre(nombre);
        usuario.setCelular(celular);
        usuario.setCorreo(correo);
        usuario.setCargo(cargo);
        usuario.setPassword(password);
        
        if(dao.usuarioExistente(nombre, correo)){
          Mensajes.mostrarAdvertencia("Ya existe un usuario con ese nombre o correo. Por favor, elige otros datos.");
          vista.getTextNombre().setText("");
          vista.getTextCorreo().setText("");
          vista.getTextNombre().requestFocus();
          return;
        
        }
    
        
        if(dao.registrarUsuario(usuario)){
            Mensajes.mostrarExito("Su registro fue exitoso");
        }else{
            Mensajes.mostrarError("No se pudo hacer el registro");
        }
     
       
        Arrays.fill(passChars, ' '); 
        password = null; 
        limpiarFormulario(); 
    }
    
    private void iniciarSesion(){
     Login login = new Login();
     ControladorLogin controladorLogin = new ControladorLogin(login, dao); 
     login.setVisible(true);
     vista.dispose(); 
    }
    
    private void limpiarFormulario() {
        vista.getTextNombre().setText("");
        vista.getTextCelular().setText("");
        vista.getTextCargo().setText("");
        vista.getTextCorreo().setText("");
        vista.getTextPassword().setText("");
    }
   
}