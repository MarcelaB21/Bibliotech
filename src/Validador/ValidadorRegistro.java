/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validador;



/**
 * Validador específico para los formularios de registro y edición de perfil.
 * Aplica validaciones más rigurosas según el contexto.
 * @author Usuario
 */
public class ValidadorRegistro {
    
    public static boolean camposCompletos(String nombre, String celular, String correo, String cargo, char[] password) {
    return nombre != null && !nombre.trim().isEmpty() &&
           celular != null && !celular.trim().isEmpty() &&
           correo != null && !correo.trim().isEmpty() &&
           cargo != null && !cargo.trim().isEmpty() &&
           password != null && password.length > 0;
    }

    
    public static boolean validarNombre(String nombre){
     return nombre !=null && nombre.length() >=3 && nombre.length()<=100 && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s\\-]+");
    }
    
    public static boolean validarCelular(String celular){
      return celular != null &&  celular.matches("^\\d{7,12}$"); 
    }
    
    public static boolean validarCorreo(String correo){
     return correo !=null && correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+(com|org|net|edu|gov|co)$");
    } 
    
    public static boolean validarCargo(String cargo){
     return cargo !=null && cargo.length()>=2 && cargo.length()<=50 && cargo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s\\-]+");
    }
    
    public static boolean validarSeguridadContraseña(String password){
     return password !=null &&
            password.length()>=8 &&
            password.matches(".*[A-Z].*")&&
            password.matches(".*[a-z].*")&&
            password.matches(".*\\d.*")&&
            password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")&&
            !password.matches(" ")&&
            !password.matches(".*[\\\\/].*");
             
    }
    
}
