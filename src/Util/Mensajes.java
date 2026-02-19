/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import javax.swing.JOptionPane;

/**
 **
 * Proporciona mensajes genéricos reutilizables para las interfaces del sistema.
 * Mejora la consistencia y centraliza el contenido mostrado al usuario.
 * @author Usuario
 */
public class Mensajes {
    
    public static void mostrarInfo(String mensaje){
      JOptionPane.showMessageDialog(null, mensaje,"Información" ,JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void mostrarError(String mensaje){
      JOptionPane.showMessageDialog(null, mensaje,"Error",JOptionPane.ERROR_MESSAGE);
    }
    
    public static void mostrarExito(String mensaje){
      JOptionPane.showMessageDialog(null, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarAdvertencia(String mensaje){
      JOptionPane.showMessageDialog(null, mensaje,"Advertencia",JOptionPane.WARNING_MESSAGE);
    }
    
    public static boolean confirmar(String mensaje){
      int resultado = JOptionPane.showConfirmDialog(null, mensaje,"Confirmación",JOptionPane.YES_NO_OPTION);
      return resultado == JOptionPane.YES_NO_OPTION;
    }
}   

