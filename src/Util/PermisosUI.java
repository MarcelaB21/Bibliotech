/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Modelo.Usuarios;
import RSMaterialComponent.RSButtonMaterialIconDos;

/**
 *Clase encargada de aplicar las restricciones de interfaz según
 * el tipo de usuario. Controla qué botones u opciones son visibles
 * para cada rol.
 * @author Usuario
 */
public class PermisosUI {
    
    public static void configurarPermisos(Usuarios usuario, RSButtonMaterialIconDos... botones ){
            if(usuario.getTipo_usuario()== Usuarios.TipoUsuario.GENERAL){
                for(RSButtonMaterialIconDos boton : botones ){
                    boton.setVisible(false);
                }          
            }else{
                for(RSButtonMaterialIconDos boton : botones){
                     boton.setVisible(true);
                }
            }
    }
    
}
