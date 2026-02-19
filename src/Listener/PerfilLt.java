package Listener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 *
 * Clase encargada de gestionar las funcionalidades del perfil del usuario.
 * Permite ver la información registrada, editar datos personales (como nombre,
 * correo o contraseña) y eliminar la cuenta si es un usuario general.
 *
 * Además, se diseñó para que escuche y responda a los cambios realizados en la interfaz de usuario,
 * facilitando la separación entre la lógica del perfil y el panel principal.
 *
 * 
 * @author Usuario
 */
public interface PerfilLt {
    
    void onNombreActualizado(String nuevoNombre);
    void onDireccionActualizado(String nuevaDireccion);
    void onCelularActualizado(String nuevoCelular);
    void onCargoActualizado(String nuevoCargo);
}
