/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelo.Conexion;
import Modelo.Usuarios;
import Modelo.Usuarios.TipoUsuario;
import Util.LoggerConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vault.Seguridad;

import java.util.logging.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 **Clase Usuario
 * Encargada de gestionar  las operaciones relacionadas con la entidad Usuarios en la base de datos.
 * Incluye manejo de errores mediante Logger .
 * Las operaciones implementadas incluyen: autenticar,registrar ,verificar usuarios registrado, editar(nombre,datos ,contraseña )y eliminar (aplica para el rol general ).
 *
 * @author Usuario
 */
public class DaoUsuario {
   
   private static final Logger logger= LoggerConfig.getLogger(DaoUsuario.class);
   
    public Usuarios autenticarUsuario(String correo, String passwordIngresada) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";

        try (Connection con = Conexion.Conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {
         ps.setString(1, correo);
         ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            
            String passwordBD = rs.getString("password");
            
            boolean verificado = Seguridad.verificarPassword(passwordIngresada, passwordBD);
                if (verificado) {
                    
                    TipoUsuario tipo = TipoUsuario.valueOf(rs.getString("tipo_usuario").toUpperCase());

                    return new Usuarios(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("celular"),
                        rs.getString("correo"),
                        rs.getString("cargo"),
                        tipo,
                        passwordBD,
                        null,
                        null
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al autenticar usuario (SQL)" ,e );
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al autenticar usuario " ,e );
        }
           return null; 
    }

    public boolean registrarUsuario(Usuarios us){
        String sql= "INSERT INTO usuarios( nombre,celular,correo,cargo,password )VALUES (?,?,?,?,?)";
    
        try(Connection  con = Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql)){
            ps.setString(1, us.getNombre());
            ps.setString(2, us.getCelular());
            ps.setString(3, us.getCorreo());
            ps.setString(4,us.getCargo());
            ps.setString(5,Seguridad.cifrarPassword(us.getPassword()));
            
            return ps.executeUpdate()> 0; 
        }catch(SQLException e){
            logger.log(Level.SEVERE, "Error sql al registrar usuario " ,e);
        }catch(Exception e){
            logger.log(Level.SEVERE, "Error sql al registrar usuario ",e);
        }
            return false; 
    }
    
    public boolean usuarioExistente(String nombre,String correo){
       String sql = "SELECT * FROM usuarios WHERE nombre= ? OR correo=? ";
       try(Connection con= Conexion.Conectar();
           PreparedStatement ps= con.prepareStatement(sql)){
           ps.setString(1,nombre);
           ps.setString(2, correo);
           ResultSet rs= ps.executeQuery();
       }catch(SQLException e){
            logger.log(Level.SEVERE,"Error sql al verificicar usuario");
        }catch(Exception E){
            logger.log(Level.SEVERE,"Error general al verificar usuario");
        }
     return false ; 
    }
   
    public boolean editarNombre(Usuarios us ){
        String sql="UPDATE usuarios SET nombre=? WHERE id_usuario = ? ";
        
        try(Connection con= Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql)){
            ps.setString(1, us.getNombre());
            ps.setInt(2, us.getId_usuario());
                return ps.executeUpdate()>0;
        }catch(SQLException e){
            logger.log(Level.SEVERE,"Error sql al editar nombre " , e);
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error general  al editar nombre ", e);
        }
            return false;
    }
    
    public boolean editarDatos(Usuarios us){
        String sql = "UPDATE usuarios SET  correo = ? ,celular =?, cargo=? WHERE id_usuario = ? ";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql)){
            
            ps.setString(1,us.getCorreo());
            ps.setString(2,us.getCelular());
            ps.setString(3,us.getCargo());
            ps.setInt(4,us.getId_usuario());
            
            return ps.executeUpdate()>0;
        }catch(SQLException e){
         logger.log(Level.SEVERE,"Error sql al editar campos " , e);
        }catch(Exception e){
         logger.log(Level.SEVERE,"Error general  al editar  campos ", e);
        }
     return false;
    }
    
    public boolean editarPass (Usuarios  us){
        String sql = " UPDATE usuarios SET password = ? WHERE id_usuario = ?";
     
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps=con.prepareStatement(sql)){
            String hsdpass=BCrypt.hashpw(us.getPassword(),BCrypt.gensalt());
            ps.setString(1,hsdpass);
            ps.setInt(2,us.getId_usuario());
            return ps.executeUpdate() > 0;
        
        }catch(SQLException e){
            logger.log(Level.SEVERE,"Error sql al editar contraseña " , e);
        
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error general  al editar contraseña ", e);
        }
            return false;
    }
    
    public boolean eliminarUsuario(String correo){
        String sql="DELETE FROM usuarios WHERE correo=?";
        try(Connection con=Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, correo);
            return ps.executeUpdate()>0;
        
        }catch(SQLException e ){
            logger.log(Level.SEVERE,"Error sql al eliminar usuario",e);
        }catch( Exception e ){
            logger.log(Level.SEVERE,"Error general al eliminar usuario",e);
        }
            return false;
    }
   
}
