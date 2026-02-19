/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Clase Lectores que representa a los usuarios registrados en el sistema.
 * Almacena información personal como nombre, identificación, contacto
 * y tipo de usuario. Forma parte del modelo de datos para la gestión
 * y transferencia de información entre la base de datos y las interfaces.
 * @author Usuario
 */
public class Lectores {
    private int consecutivo;
    private String nombre;
    private String identificacion;
    private String correo;
    private String contacto;
    private String tipodeusuario;
    private String registro;

    public Lectores() {
    }

    public Lectores(int consecutivo, String nombre, String identificacion, String correo, String contacto, String tipodeusuario, String registro) {
        this.consecutivo = consecutivo;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.contacto = contacto;
        this.tipodeusuario = tipodeusuario;
        this.registro = registro;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTipodeusuario() {
        return tipodeusuario;
    }

    public void setTipodeusuario(String tipodeusuario) {
        this.tipodeusuario = tipodeusuario;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

   
    
    
}
