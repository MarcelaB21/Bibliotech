/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
  * Clase Categorias que representa las categorías de libros dentro del sistema.
 * Almacena información como nombre, descripción y estado, y forma parte del
 * modelo de datos para organizar y clasificar los libros registrados.
 * @author Usuario
 */
public class Categorias {
    private int id_categoria ;
    private String nombre;
    private String descripcion;
    private String estado;

    public Categorias() {
    }

    public Categorias(int id_categoria, String nombre, String descripcion, String estado) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    
}
