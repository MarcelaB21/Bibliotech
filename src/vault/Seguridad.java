/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vault;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Clase encargada del cifrado de contraseñas usando BCrypt.
 * Asegura el almacenamiento seguro de las contraseñas de los usuarios.
 *
 * @author Usuario
 */
public class Seguridad {
    public static String cifrarPassword(String password){
      return BCrypt.hashpw(password,BCrypt.gensalt());
    }
    
    public static boolean verificarPassword(String passwordIngresada,String passwordBD){
     return BCrypt.checkpw(passwordIngresada, passwordBD);
    }
    
}
