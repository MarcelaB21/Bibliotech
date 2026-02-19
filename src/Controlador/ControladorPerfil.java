/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoUsuario;
import Listener.PerfilLt;
import Modelo.Usuarios;
import Util.Mensajes;
import Vista.EditarInformacion;
import Vista.EditarNombre;
import Vista.EditarPs;
import Vista.FrmPerfil;
import Vista.Login;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

/**
 * Controlador encargado de mostrar la información del usuario en el módulo
 * de perfil. Permite acceder a las opciones de edición de datos personales
 * como nombre, contraseña y demás información registrada.
 * @author Usuario
 */
public class ControladorPerfil  implements ActionListener , PerfilLt {
    
    private FrmPerfil vista;
    private DaoUsuario  daousuario;
    private Usuarios usuarioLogueado;
    
    public ControladorPerfil (FrmPerfil vista ,DaoUsuario  daousuario,Usuarios usuarioLogueado){
       this.vista= vista;
       this.daousuario= daousuario;
       this.usuarioLogueado = usuarioLogueado;
       
       
        cargarDatos();
        
        if (usuarioLogueado.getTipo_usuario() != Usuarios.TipoUsuario.GENERAL) {
            vista.getbtnEliminar().setVisible(false);
        }
       
        this.vista.getbtnPass().addActionListener(this);
        this.vista.getbtnEditarDatos().addActionListener(this);
        this.vista.getbtnEditar().addActionListener(this);
        this.vista.getbtnEliminar().addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()== vista.getbtnEditar()){
            editarNombre();
        }else if(e.getSource()==vista.getbtnPass()){
            editarPass();
        }else if(e.getSource()== vista.getbtnEditarDatos()){
            editarInformacion();
        }else if(e.getSource()== vista.getbtnEliminar()){
            eliminarUsuario();
        }
    }
    
    @Override
    public void onNombreActualizado(String nuevoNombre) {
        vista.getTextNombre().setText(nuevoNombre);
     }

    @Override
    public void onDireccionActualizado(String nuevaDireccion ) {
        vista.getTextEmail().setText(nuevaDireccion);
    }
    
    @Override
    public void onCelularActualizado(String nuevoCelular) {
        vista.getTextContacto().setText(nuevoCelular);
    }
    
    @Override
    public void onCargoActualizado(String nuevoCargo ) {
        vista.getTextCargo().setText(nuevoCargo);
    }
    
    
    private void cargarDatos(){
        
        if(usuarioLogueado != null) {
            vista.getTextNombre().setText(usuarioLogueado.getNombre());
            vista.getTextEmail().setText(usuarioLogueado.getCorreo());
            vista.getTextContacto().setText(usuarioLogueado.getCelular());
            vista.getTextCargo().setText(usuarioLogueado.getCargo());
            vista.getTextNivel().setText(usuarioLogueado.getTipo_usuario().toString());
        }
    }
    
    private void editarPass(){
       EditarPs editarpass= new EditarPs (); 
       DaoUsuario daousuario = new DaoUsuario();
       ControladorEditarPs controladorpass = new ControladorEditarPs(editarpass, daousuario, usuarioLogueado);
       editarpass.setVisible(true);
    }
    
    private void editarInformacion(){
       EditarInformacion  editarinformacion= new EditarInformacion();
       DaoUsuario  daousuario = new DaoUsuario();
       ControladorEditarInformacion controladorinformacion= new ControladorEditarInformacion(editarinformacion, daousuario, usuarioLogueado, this);
       editarinformacion.setVisible(true);
    }
    
    private void editarNombre(){
       EditarNombre editarnombre= new EditarNombre();
       DaoUsuario daousuario = new DaoUsuario();
       ControladorEditar controladoreditar = new ControladorEditar(editarnombre, daousuario, usuarioLogueado, this);
       editarnombre.setVisible(true);
    }
    
    private void eliminarUsuario() {
        if (usuarioLogueado.getTipo_usuario() == Usuarios.TipoUsuario.GENERAL) {
            String correo = usuarioLogueado.getCorreo();
            boolean confirmacion = Mensajes.confirmar("¿Está seguro que desea eliminar su cuenta? Esta acción no se puede deshacer.");
        
            if (confirmacion) {
                boolean eliminado = daousuario.eliminarUsuario(correo);
            
                if (eliminado) {
                    Mensajes.mostrarExito("Cuenta eliminada con éxito.");
                
                    // Cierra la ventana que contiene el panel
                    Window window = SwingUtilities.getWindowAncestor(vista);
                    if (window != null) {
                        window.dispose();
                    }

                    //  Abre nuevamente el login
                    Login login = new Login();
                    login.setVisible(true);
                } else {
                    Mensajes.mostrarError("Hubo un error al eliminar la cuenta.");
                }
            }
        } else {
            Mensajes.mostrarError("Solo los usuarios generales pueden eliminar su cuenta.");
        }
    }
    
}
