/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoUsuario;
import Listener.PerfilLt;
import Modelo.Usuarios;
import Util.Mensajes;
import Validador.ValidadorRegistro;
import Vista.EditarInformacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Controlador usado para editar los datos personales del usuario. 
 * Actualiza la información en el almacenamiento y sincroniza los cambios con
 * la vista del perfil a través de un listener.
 *
* @author Usuario
 */
public class ControladorEditarInformacion implements ActionListener{
    
    private EditarInformacion vista;
    private DaoUsuario dao;
    private Usuarios usuarioLogueado;
    private PerfilLt perfilListener;
    
    public ControladorEditarInformacion(EditarInformacion vista,DaoUsuario dao,Usuarios usuarioLogueado,PerfilLt perfilListener){
        this.vista= vista;
        this.dao= dao;
        this.usuarioLogueado= usuarioLogueado;
        this.perfilListener = perfilListener;
        
        cargarInformacion();
        this.vista.getbtnGuardar().addActionListener(this);
        this.vista.getbtnCancelar().addActionListener(this);
    }
    
    @Override
    
    public void actionPerformed(ActionEvent e){
    
        if(e.getSource()== vista.getbtnGuardar()){
            guardarDatos();
        }else if(e.getSource() == vista.getbtnCancelar()){
            cancelar();
        }
    }
    
    private void cargarInformacion(){
        vista.getTextEmail().setText(usuarioLogueado.getCorreo());
        vista.getTextContacto().setText(usuarioLogueado.getCelular());
        vista.getTextCargo().setText(usuarioLogueado.getCargo());
    }
    
    private void guardarDatos(){
        String correo = vista.getTextEmail().getText();
        String celular = vista.getTextContacto().getText();
        String cargo = vista.getTextCargo().getText();
        
        if(!ValidadorRegistro.validarCorreo(correo)){
           Mensajes.mostrarError("El correo ingresado no es válido");
           return;
        }
        
        if(!ValidadorRegistro.validarCelular(celular)){
           Mensajes.mostrarError("Numero de celular no valido ");
           return ; 
        }
        
        if(!ValidadorRegistro.validarCargo(cargo)){
           Mensajes.mostrarError("El cargo ingresado no es valido ");
           return;
        }
        
        usuarioLogueado.setCorreo(correo);
        usuarioLogueado.setCelular(celular);
        usuarioLogueado.setCargo(cargo);
        
        if(dao.editarDatos(usuarioLogueado)){
           Mensajes.mostrarExito("Se modificó con éxito");
            if (perfilListener != null) {
                perfilListener.onDireccionActualizado(usuarioLogueado .getCorreo());
                perfilListener.onCelularActualizado(usuarioLogueado.getCelular());
                perfilListener.onCargoActualizado(usuarioLogueado.getCargo());
            }
            vista.dispose();
        }
        
    }
    
    private void cancelar(){
       vista.dispose();
    }
   
}
