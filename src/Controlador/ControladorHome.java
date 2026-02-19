/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuarios;
import Vista.FrmHome;

/**
 *Controlador del panel principal (Home). Su función es mostrar el mensaje
 * de bienvenida y cargar la información inicial para el usuario logueado.
 * @author Usuario
 */
public class ControladorHome {
    private FrmHome vista;
    private Usuarios usuarioLogueado;
    
    public ControladorHome (FrmHome vista,Usuarios usuarioLogueado){
        this.vista= vista;
        this.usuarioLogueado = usuarioLogueado;
        
        configurarMensajes();
    
    }
    
    private void configurarMensajes(){
      vista.getLblBienvenida().setText(
            "Bienvenido(a), " + usuarioLogueado.getNombre()
        );

        vista.getLblMensajeMediano().setText(
            "<html><div style='text-align:center;'>Administra fácilmente tus<br>libros, lectores y préstamos.</div></html>"
        );

        vista.getLblMensajePequeño().setText(
            "<html><div style='text-align:center;'>Selecciona una opción en el menú<br>para comenzar.</div></html>"
        );
    
    }
}
