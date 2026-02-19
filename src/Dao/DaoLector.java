/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelo.Conexion;
import Modelo.Lectores;
import Util.LoggerConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;


/**
 *Clase DaoLector 
 * Encargada de gestionar todas las operaciones CRUD relacionadas con la entidad usarios_lector en la base de datos.
 * Incluye manejo de errores mediante Logger .
 * Las operaciones implementadas incluyen: insertar, verificar cedula , listar, editar, eliminar, buscar por ID, 
 * verificar si esta en uso  y validar dependencias con prestamos.
 * @author Usuario
 */
public class DaoLector {
    
    private static final Logger logger= LoggerConfig.getLogger(DaoLector.class);
    
    public boolean insertar(Lectores lt){
        String sql="INSERT INTO usuarios_lector(nombre,identificacion,correo,contacto,tipo_usuario,numero_registro)VALUES (?,?,?,?,?,?)";
        try(Connection con= Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql)){
            
            ps.setString(1, lt.getNombre());
            ps.setString(2, lt.getIdentificacion());
            ps.setString(3,lt.getCorreo());
            ps.setString(4, lt.getContacto());
            ps.setString(5,lt.getTipodeusuario());
            ps.setString(6, lt.getRegistro());
            
            return ps.executeUpdate()>0;
     
            
        }catch (SQLException  e){
              logger.log(Level.SEVERE,"Error sql al insertar al lector",e );
        }catch(Exception e){
              logger.log(Level.SEVERE,"Error general al insertar al lector", e);
        }
        return false;
        
    }
    
    public boolean verificarCedula(String cedula){
        String sql="SELECT COUNT(*) FROM usuarios_lector WHERE identificacion = ?";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql)){
            ps.setString(1,cedula);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
               return rs.getInt(1)>0;
            }
        }catch(SQLException e){
                logger.log(Level.SEVERE,"Error sql al vereficar si la cedula existe ", e);
        }catch(Exception e ){
                logger.log(Level.SEVERE,"Error general al verificar si la cedula existe",e);
        }
            return false; 
    }
    
    public List<Lectores> Listar(){
        List<Lectores> lista = new ArrayList<>();
        String sql="SELECT * FROM usuarios_lector ORDER BY consecutivo ASC";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Lectores lt = new Lectores();
                lt.setConsecutivo(rs.getInt("consecutivo"));
                lt.setNombre(rs.getString("nombre"));
                lt.setIdentificacion(rs.getString("identificacion"));
                lt.setCorreo(rs.getString("correo"));
                lt.setContacto(rs.getString("contacto"));
                lt.setTipodeusuario(rs.getString("tipo_usuario"));
                lt.setRegistro(rs.getString("numero_registro"));
                lista.add(lt);
            } 
        }catch(SQLException e){
            logger.log(Level.SEVERE, "Error sql al listar lector" , e);
        }catch(Exception e){
            logger.log(Level.SEVERE, "Error general al listar lector" , e);
        }
        return lista; 
    }
    
    public boolean editar(Lectores lt){
        String sql=" UPDATE usuarios_lector SET nombre = ? , identificacion = ? ,correo = ? ,contacto= ? ,tipo_usuario = ?,numero_registro = ? WHERE consecutivo = ? ";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1, lt.getNombre());
            ps.setString(2,lt.getIdentificacion());
            ps.setString(3, lt.getCorreo());
            ps.setString(4, lt.getContacto());
            ps.setString(5, lt.getTipodeusuario());
            ps.setString(6,lt.getRegistro());
            ps.setInt(7, lt.getConsecutivo());
            
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            logger.log(Level.SEVERE, "Error sql al editar lector", e);
        }catch(Exception e){
            logger.log(Level.SEVERE, "Error general al editar lector", e);
        }
        return false;
    }
    
    public boolean eliminar(Lectores lt){
        String sql= "DELETE FROM usuarios_lector WHERE consecutivo = ?";
        try(Connection con= Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,lt.getConsecutivo());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            logger.log(Level.SEVERE,"Error sql al eliminar lector ", e);
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error general  al eliminar lector ", e);
        }
        return false;
    }
    
    public boolean buscar(Lectores lt){
        String sql= "SELECT * FROM usuarios_lector WHERE  consecutivo = ? ";
        try(Connection con= Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql)){
            ps.setInt(1, lt.getConsecutivo());
            ResultSet rs= ps.executeQuery();
                if(rs.next()){
                    
                    lt.setNombre(rs.getString("nombre"));
                    lt.setIdentificacion(rs.getString("identificacion"));
                    lt.setCorreo(rs.getString("correo"));
                    lt.setContacto(rs.getString("contacto"));
                    lt.setTipodeusuario(rs.getString("tipo_usuario"));
                    lt.setRegistro(rs.getString("numero_registro"));
                    return true;
                    
                }
        }catch(SQLException e){
            logger.log(Level.SEVERE,"Error sql al buscar lector", e );
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error general al buscar lector", e );
        
        }
        return false;
    }
    
    public Lectores buscarPorConsecutivo(String consecutivo) {
        Lectores lector = null;
        String sql = "SELECT * FROM usuarios_lector WHERE consecutivo = ?";
        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, consecutivo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lector = new Lectores();
                lector.setConsecutivo(rs.getInt("consecutivo"));
                lector.setNombre(rs.getString("nombre"));
                lector.setIdentificacion(rs.getString("identificacion"));
                lector.setCorreo(rs.getString("correo"));
                lector.setContacto(rs.getString("contacto"));
                lector.setTipodeusuario(rs.getString("tipo_usuario"));
                lector.setRegistro(rs.getString("numero_registro"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al buscar lector por consecutivo", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al buscar lector por consecutivo", e);
        }
            return lector;
    } 

    public boolean tieneDependencias(int idLector) {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE id_lector = ?";
    
        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idLector);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al verificar dependencias de lector", e);
        }
            return false;
    }

} 