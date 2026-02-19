/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validador;

/**
 *Validador para datos de lectores. Comprueba campos completos
 * y valida nombre, identificacion, correo, contacto tipo de usuario y registro según
 * las reglas definidas.
 * @author Usuario
 */
public class ValidadorLector {
    
    public static boolean camposCompletos(String nombre, String identificacion, String correo , String contacto, String tipoUsuario, String registro){
        return nombre !=null && !nombre.trim().isEmpty()&&
               identificacion !=null && !identificacion.trim().isEmpty()&&
               correo !=null && !correo.trim().isEmpty()&&
               contacto !=null && !contacto.trim().isEmpty()&&
               tipoUsuario !=null && !tipoUsuario.trim().isEmpty()&&
               registro !=null && !registro.trim().isEmpty();
    }
    
    public static boolean ValidarNombre(String nombre){
        if(nombre == null)return false;
        nombre= nombre.trim();
        return nombre.length()>=3 && nombre.length()<=100 && SintaxisGeneral.Letras(nombre);
    }
    
    public static boolean ValidarIdentificacion( String identificacion){
        if(identificacion == null)return false;
        identificacion= identificacion.trim();
        return identificacion.matches("^\\d{7,10}$") ;
    }
    
    public static boolean ValidarCorreo(String correo){
        if(correo == null)return false;
        correo= correo.trim();
        return  correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+(com|edu|co)$");
         
    }
    
    public static boolean ValidarContacto(String contacto){
        if(contacto == null)return false;
        contacto= contacto.trim();
        return contacto.matches("^\\d{7,10}$") ;
    }
    
    public static boolean ValidarTipoUsuario(String tipoUsuario){
        if(tipoUsuario == null)return false;
        tipoUsuario= tipoUsuario.trim();
        return tipoUsuario.length()>=3 && tipoUsuario.length()<=50 && SintaxisGeneral.Letras(tipoUsuario);
    }
    
    public static boolean ValidarRegistro(String registro){
       if(registro == null)return false;
       registro= registro.trim();
       return registro.matches("^\\d{12}$");
       

    }
    
    
}
