/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Clase Prestamos que representa un préstamo de libros dentro del sistema.
 * Almacena el ID, consecutivo, libros seleccionados, fechas y estado del préstamo,
 * además de una URL asociada. Forma parte del modelo de datos para la gestión
 * y transferencia de información entre la base de datos y las interfaces.
 *
 * @author Usuario
 */
public class Prestamos {
    private int id_prestamo ;
    private int consecutivo;
    private List<Libros> librosSeleccionados= new ArrayList<>();
    private Date fecha_prestamo;
    private Date fecha_devolucion;
    private String estado ; 
    private String url;

    public Prestamos() {
    }

    public Prestamos(int id_prestamo, int consecutivo, List<Libros> librosSeleccionados,Date fecha_prestamo, Date fecha_devolucion, String estado, String url) {
        this.id_prestamo = id_prestamo;
        this.consecutivo = consecutivo;
         this.librosSeleccionados= librosSeleccionados;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.estado = estado;
        this.url = url;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public List<Libros> getLibrosSeleccionados() {
        return librosSeleccionados;
    }

    public void setLibrosSeleccionados(List<Libros> librosSeleccionados) {
        this.librosSeleccionados = librosSeleccionados;
    }

    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
    

}
