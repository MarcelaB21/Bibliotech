/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDateTime;

/**
 ** Clase Usuario que representa la entidad Usuario dentro del sistema.
 * Contiene los atributos necesarios para almacenar información relacionada
 * con los usuarios del sistema.
 *
 * Esta clase forma parte del modelo de datos y se utiliza para transferir
 * información entre la base de datos, los controladores y las interfaces gráficas.
 * @author Usuario
 */
public class Usuarios {
    
    private int id_usuario;
    private String nombre;
    private String celular;
    private String correo;
    private String cargo;
    private TipoUsuario tipo_usuario;
    private String password;
    private String recuperarToken;
    private LocalDateTime tokenFecha;
    
    public enum TipoUsuario{
    ADMINISTRADOR,
    GENERAL
    }

    public Usuarios() {
    }

    public Usuarios(int id_usuario, String nombre, String celular, String correo, String cargo, TipoUsuario tipo_usuario, String password, String recuperarToken, LocalDateTime tokenFecha) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.celular = celular;
        this.correo = correo;
        this.cargo = cargo;
        this.tipo_usuario = tipo_usuario;
        this.password = password;
        this.recuperarToken = recuperarToken;
        this.tokenFecha = tokenFecha;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public TipoUsuario getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(TipoUsuario tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecuperarToken() {
        return recuperarToken;
    }

    public void setRecuperarToken(String recuperarToken) {
        this.recuperarToken = recuperarToken;
    }

    public LocalDateTime getTokenFecha() {
        return tokenFecha;
    }

    public void setTokenFecha(LocalDateTime tokenFecha) {
        this.tokenFecha = tokenFecha;
    }
    
    
    
}
