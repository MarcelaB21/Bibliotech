/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import Util.LoggerConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.*;
import vault.Utilidades;
/**
 *Clase encargada de establecer conexión a la base de datos
 * @author Usuario
 */
public class Conexion {
    
    private static final Logger logger= LoggerConfig.getLogger(Conexion.class);
    
    public static Connection Conectar(){
        Connection cn = null;
        try{
            
            File archivo = new File("resources/config.properties");
            
            if(!archivo.exists()){
              logger.log(Level.SEVERE ,"No se encontró el archivo config.properties en : " + archivo.getAbsolutePath());
            }
            
            try(InputStream input = new FileInputStream(archivo)){
                Properties props = new Properties();
                props.load(input);
                
                String url= props.getProperty("db.url");
                String user= props.getProperty("db.user");
                String passwordCifrada= props.getProperty("db.password");
                String password=Utilidades.descifrar(passwordCifrada);
                
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                cn= DriverManager.getConnection(url, user, password);  
                
            }
            
           
        } catch (ClassNotFoundException e) {
             logger.log(Level.SEVERE, "No se encontró el driver JDBC: " + e.getMessage(), e);
        } catch (SQLException e) {
             logger.log(Level.SEVERE, "Error de conexión a la base de datos: " + e.getMessage() , e);
        } catch (Exception e) {
             logger.log(Level.SEVERE, "Error general: " + e.getMessage(), e);
        }

        return cn;
    }
    
}
