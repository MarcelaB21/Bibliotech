/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 * Clase para gestionar intentos fallidos y bloquear temporalmente
 * funcionalidades sensibles como cambio de contraseña.
 * Maneja:
 * - Conteo de intentos.
 * - Tiempo y duración del bloqueo.
 * @author Usuario
 */
public class Intentos {
     private int intentosFallidos=0;
    private long tiempoBloqueo=0;
    private final int maxIntentos;
    private final long duracionBloqueo;

    public Intentos(int maxIntentos, long duracionBloqueo) {
        this.maxIntentos = maxIntentos;
        this.duracionBloqueo = duracionBloqueo;
    }
    
    public boolean estaBloqueado(){
        if(System.currentTimeMillis() < tiempoBloqueo){
            return true;
        }else{
            tiempoBloqueo=0;
            return false;
        }
    }
    
    public void registrarIntentosFallidos(){
     intentosFallidos++;
        if(intentosFallidos >= maxIntentos){
            tiempoBloqueo = System.currentTimeMillis() +duracionBloqueo;
            intentosFallidos=0;
        }
    }
    
    public int intentosRestantes(){
       return maxIntentos - intentosFallidos;
    }
    
    public void resetIntentos(){
      intentosFallidos=0;
      tiempoBloqueo=0;
    }
}
