/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validador;

import java.util.regex.Pattern;

/**
 * Validador de sintaxis general utilizado por los validadores
 * específicos. Contiene reglas básicas para validar texto,
 * números y formatos comunes en toda la aplicación.
 * @author Usuario
 */
public class SintaxisGeneral {
    
    public static boolean Letras(String texto){
       return Pattern.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s\\-]+", texto);
    }
    
    
    public static boolean NumerosPositivos(String texto){
       return Pattern.matches("\\d+", texto);
    }
        
         
    public static boolean letrasyNumeros(String texto) {
        return Pattern.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\- ]+", texto);
    }
    
    
    

}
