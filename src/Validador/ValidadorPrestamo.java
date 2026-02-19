/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validador;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 **La clase ValidadorPrestamo contiene métodos para validar y formatear 
 * los datos relacionados con un préstamo. 
 * Se encarga de verificar que los campos requeridos estén completos,
 * validar que el ID y el consecutivo sean números positivos,
 * comprobar que el estado ingresado sea uno de los permitidos,
 * dar formato legible a las fechas y convertirlas al tipo java.sql.Date
 * para su almacenamiento en la base de datos.
 * @author Usuario
 */
public class ValidadorPrestamo {
    
    public static boolean camposCompletos(String id_prestamo, Date fechaPrestamo, Date fechaDevolucion, String consecutivo, String estado) {
    return id_prestamo != null && !id_prestamo.trim().isEmpty() &&
           fechaPrestamo != null &&
           fechaDevolucion != null &&
           consecutivo != null && !consecutivo.trim().isEmpty() &&
           estado != null && !estado.trim().isEmpty();
    }
    
    public static boolean validadorId_prestamo (String id_prestamo){
        if(id_prestamo == null)return false;
        id_prestamo = id_prestamo.trim();
        return SintaxisGeneral.NumerosPositivos(id_prestamo);
    }
    
    public static boolean validarConsecutivo(String consecutivo) {
        return SintaxisGeneral.NumerosPositivos(consecutivo);
    }

    
    public static boolean validarEstado(String estado) {
        String[] permitidos = {"Activo", "Devuelto", "Retrasado"};
        for (String e : permitidos) {
            if (e.equalsIgnoreCase(estado)) {
                return true;
            }
        }
        return false;
    }


    
    public static String formatFecha(Date fecha) {
        if (fecha == null) return "Fecha no disponible";
        SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
        return formatter.format(fecha);
    }
    
    public static java.sql.Date convertirAFechaSQL(Date fechaUtil) {
        if (fechaUtil == null) {
            return null;  
        }
        return new java.sql.Date(fechaUtil.getTime());  
    }
    
}
