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
import Vista.EditarNombre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * 
 * Controlador encargado de gestionar la edición del nombre del usuario.
 * Actualiza la información en el DAO y notifica los cambios al perfil mediante
 * un listener, permitiendo que la vista del perfil muestre los cambios en 
 * tiempo real.
 *
*
 * @author Usuario
 */
public class ControladorEditar implements ActionListener{
    
    private EditarNombre vista;
    private DaoUsuario dao;
    private Usuarios usuarioLogueado;
    private PerfilLt perfilListener;
    
    public  ControladorEditar (EditarNombre vista,DaoUsuario dao, Usuarios usuarioLogueado,PerfilLt perfilListener){
        this.vista= vista;
        this.dao=dao;
        this.usuarioLogueado= usuarioLogueado;
        this.perfilListener = perfilListener;
      
        cargarNombre();
        
        this.vista.getbtnGuardar().addActionListener(this);
        this.vista.getbtnCancelar().addActionListener(this);
        
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==vista.getbtnGuardar()){
            guardarNombre();
        }else if(e.getSource()==vista.getbtnCancelar()){
            cancelar();
        }
    
    }
    
    private void cargarNombre(){
       vista.getTextNombre().setText(usuarioLogueado.getNombre());
    }
    
    private void guardarNombre(){
        String nombre= vista.getTextNombre().getText();
        if(!ValidadorRegistro.validarNombre(nombre)){
            Mensajes.mostrarError("El nombre ingresado no es válido.");
           return;
        }
        
        usuarioLogueado.setNombre(nombre);
        
        if(dao.editarNombre(usuarioLogueado)){
           Mensajes.mostrarExito("Se modificó con éxito");
            
            if (perfilListener != null) {
                perfilListener.onNombreActualizado(usuarioLogueado.getNombre());
            }

            vista.dispose();
        }
        
    
    }
    
    private void cancelar(){
      vista.dispose();
    }
    
}

