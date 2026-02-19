/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Control.Intentos;
import Dao.DaoUsuario;
import Modelo.Usuarios;
import Util.Mensajes;
import Validador.ValidadorRegistro;
import Vista.EditarPs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import org.mindrot.jbcrypt.BCrypt;

/**
 *Controlador destinado a modificar la contraseña del usuario. 
 * Valida la información ingresada, actualiza los datos mediante el DAO y
 * asegura que los cambios se reflejen correctamente en la sesión activa.
 * @author Usuario
 */
public class ControladorEditarPs implements ActionListener {
    private EditarPs vista;
    private DaoUsuario dao;
    private Usuarios usuarioLogueado;
    private Intentos controlIntentos;
    
    public ControladorEditarPs (EditarPs vista,DaoUsuario dao,Usuarios usuarioLogueado){
        this.vista= vista;
        this.dao=dao;
        this.usuarioLogueado=usuarioLogueado;
        this.controlIntentos = new Intentos(3, 30000); 
        
        this.vista.getbtnGuardar().addActionListener(this);
        this.vista.getbtnCancelar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent  e ){
        if(e.getSource()== vista.getbtnGuardar()){
            guardarPassword();
        }else if(e.getSource()==vista.getbtnCancelar()){
            cancelar();
        }
    }
    
    private void guardarPassword(){
        char[] passAt = vista.getTextActual().getPassword();
        char[] nvPass = vista.getTextNuevo().getPassword();
        char[] vfPass = vista.getTextConfirmar().getPassword();
        
        try{
            if(controlIntentos.estaBloqueado()){
                Mensajes.mostrarError( "Demasiados intentos fallidos. Intenta más tarde.");
                return;
            }
            
            try{
                if(!BCrypt.checkpw(new String(passAt), usuarioLogueado.getPassword())){
                   controlIntentos.registrarIntentosFallidos();
                    
                    if(controlIntentos.estaBloqueado()){
                      Mensajes.mostrarError("La contraseña actual es incorrecta. Has sido bloqueado por 30 segundos.");
                    }else{
                        Mensajes.mostrarInfo("Contraseña incorrecta. Intentos restantes: " + controlIntentos.intentosRestantes());
                    }
                       return;
                }     
            }catch (IllegalArgumentException ex) {
                    Mensajes.mostrarError("La contraseña no puede ser verificada. Intenta más tarde.");
                    return;
            } 
            
            controlIntentos.resetIntentos();
            
            if(!Arrays.equals(nvPass,vfPass)){
                Mensajes.mostrarError("La nueva contraseña y su confirmación no coinciden");
                    return;
            }
            
            if(!ValidadorRegistro.validarSeguridadContraseña(new String(nvPass))){
                Mensajes.mostrarError("La nueva contraseña no cumple con los requisitos de seguridad");
                return;
            
            }
            
            usuarioLogueado.setPassword(new String(nvPass));
            
            if(dao.editarPass(usuarioLogueado)){
                Mensajes.mostrarExito("Contraseña actualizada correctamente");
                vista.dispose();
            }else{
                      Mensajes.mostrarError("Error al actualizar la contraseña");
                }
        }finally {
        
            Arrays.fill(passAt, ' ');
            Arrays.fill(nvPass, ' ');
            Arrays.fill(vfPass, ' ');
        }   
    }
    
    private void cancelar(){
       vista.dispose();
    }
}
