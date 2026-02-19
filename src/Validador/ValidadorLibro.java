/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validador;

/**
 * Validador para libros. Revisa campos obligatorios y valida
 * título, autor, editorial, año e ISBN siguiendo criterios
 * de formato y contenido.
 * @author Usuario
 */
public class ValidadorLibro {
    
    public static boolean camposCompletos(String titulo, String autor, String editorial, String  anio, String isbn, String  categoria) {
    return titulo != null && !titulo.trim().isEmpty() &&
           autor != null && !autor.trim().isEmpty() &&
           editorial != null && !editorial.trim().isEmpty() &&
           isbn != null && !isbn.trim().isEmpty() &&
           anio != null && !anio.trim().isEmpty() &&
           categoria != null && !categoria .trim().isEmpty()  ;
}

    public static boolean validarTitulo(String titulo) {
        if(titulo == null)return false;
        titulo = titulo.trim();
        return titulo.length() >= 3 && titulo.length() <= 100
                && SintaxisGeneral.letrasyNumeros(titulo);
    }

    public static boolean validarAutor(String autor) {
        if(autor == null)return false;
        autor= autor.trim();
        return autor.length() >= 3 && autor.length() <= 100
                && SintaxisGeneral.Letras(autor);
    }

    public static boolean validarEditorial(String editorial) {
        if(editorial == null)return false;
        editorial = editorial.trim();
        return editorial.length() >= 3 && editorial.length() <= 100
                && SintaxisGeneral.letrasyNumeros(editorial);
    }

    public static boolean validarAnio(int anio) {
        return anio >= 1000 && anio <= 9999;
    }
    
    public static boolean validarAniodesdetexto(String texto) {
        if (!SintaxisGeneral.NumerosPositivos(texto)) {
            return false;
        }

        try {
            int anio = Integer.parseInt(texto);
                return validarAnio(anio); 
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean validarISBN(String isbn) {
        if(isbn == null)return false;
        isbn= isbn.trim();
        return isbn.length() >= 10 && isbn.length() <= 20
                && isbn.matches("[0-9\\-]+"); 
    }

    

    public static boolean validarDisponible(String disponible) {
        if (disponible == null) return false;
        disponible = disponible.trim().toLowerCase();
        return disponible.equals("disponible") || disponible.equals("no disponible");
    }

    public static boolean convertirDisponible(String disponible) {
        return disponible.trim().equalsIgnoreCase("disponible");
    }
}
