/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validador;



/**
 * Validador específico para categorías. Verifica que los campos
 * estén completos y que el nombre , descripción ,estado  cumplan las
 * reglas básicas de sintaxis.
 * @author Usuario
 */
public class ValidadorCategoria {

    public static boolean camposCompletos(String nombre, String estado, String descripcion) {
        return nombre != null && !nombre.trim().isEmpty() &&
               estado != null && !estado.trim().isEmpty() &&
               descripcion != null && !descripcion.trim().isEmpty();
    }

    public static boolean validarNombre(String nombre) {
        if (nombre == null) return false;
        nombre = nombre.trim();
        return nombre.length() >= 3 && nombre.length() <= 50 && SintaxisGeneral.Letras(nombre);
    }

    public static boolean validarEstado(String estado) {
        if (estado == null) return false;
        estado = estado.trim().toLowerCase();
        return estado.equals("activo") || estado.equals("inactivo");
    }

    public static boolean validarDescripcion(String descripcion) {
        if (descripcion == null) return false;
        descripcion = descripcion.trim();
        return descripcion.length() <= 200 && 
               descripcion.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s\\-.,:()]+");
    }
}
