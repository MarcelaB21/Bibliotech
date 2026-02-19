/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Clase Libros que representa los libros registrados en el sistema.
 * Almacena datos como título, autor, editorial, año, ISBN, categoría
 * y disponibilidad. Forma parte del modelo de datos para la gestión 
 * de información y consultas dentro del sistema.
 * @author Usuario
 */
public class Libros {
    private int id_libro ;
    private String titulo;
    private String autor;
    private String editorial;
    private int anio;
    private String isbn;
    private int id_categoria;
    private boolean disponible;   
    private String nombreCategoria; 
      

    public Libros() {
    }

    public Libros(int id_libro, String titulo, String autor, String editorial, int anio, String isbn, int id_categoria, boolean disponible) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.isbn = isbn;
        this.id_categoria = id_categoria;
        this.disponible = disponible;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
     // Este método hace que el libro se muestre con su título en vez de la referencia de memoria
    @Override
    public String toString() {
        return titulo; 
    }
    
    
    
}
