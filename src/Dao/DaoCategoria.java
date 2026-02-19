/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelo.Categorias;
import Modelo.Conexion;
import Util.LoggerConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.*;


/**
 * Clase DaoCategoria 
 * Encargada de gestionar todas las operaciones CRUD relacionadas con la entidad categoria  en la base de datos.
 * Incluye manejo de errores mediante Logger .
 * Las operaciones implementadas incluyen: insertar, listar, editar, eliminar, buscar por ID, 
 * verificar si esta en uso  y validar dependencias con libros.
 * @author Usuario
 */
public class DaoCategoria {
    
    private static final Logger logger = LoggerConfig.getLogger(DaoCategoria.class);
    
    public boolean insertar(Categorias c){
        String sql = "INSERT INTO categorias (nombre, descripcion,estado)VALUE (?,?,?)";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setString(3, c.getEstado());
            return ps.executeUpdate()> 0;
        }catch (SQLException  e){
              logger.log(Level.SEVERE,"Error sql al insertar la categoria",e );
        }catch(Exception e){
              logger.log(Level.SEVERE,"Error general al insertar categoria", e);
        }
        return false;
    }
    
    public List<Categorias> Listar(){
        List<Categorias> lista = new ArrayList<> ();
        String sql= "SELECT * FROM categorias ORDER BY id_categoria ASC";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery()){
            while(rs.next()){
                Categorias c = new Categorias();
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setEstado(rs.getString("estado"));
                lista.add(c);
            
            }
        }catch(SQLException e){
            logger.log(Level.SEVERE ,"Error sql al listar categoria" , e);
        }catch (Exception e ){
            logger.log(Level.SEVERE ,"Error general al listar categoria" , e);
        }
        return lista;
    }
    
    public boolean editar (Categorias c ){
        String sql= " UPDATE categorias SET nombre = ? ,descripcion = ? ,estado = ?  WHERE id_categoria = ? ";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,c.getNombre());
            ps.setString(2,c.getDescripcion());
            ps.setString(3,c.getEstado());
            ps.setInt(4, c.getId_categoria()); // ✅ ¡No olvides este!
            return ps.executeUpdate()> 0 ;
        
        }catch(SQLException e){
            logger.log(Level.SEVERE,"Error sql al editar categoria", e);
        }catch(Exception e ){
            logger.log(Level.SEVERE,"Error general al editar categoria", e);
        }
        return false ;
    }
    
    public boolean eliminar (Categorias c){
        String sql = "DELETE FROM categorias WHERE id_categoria = ?";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, c.getId_categoria());
            return ps.executeUpdate() > 0 ;
        }catch(SQLException e){
            logger.log(Level.SEVERE,"Error sql al eliminar categoria ", e);
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error general al eliminar categoria", e);
        }
        return false;
    }
    
    public boolean buscar(Categorias c) {
        String sql = "SELECT * FROM categorias WHERE id_categoria = ?";
        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
        
            ps.setInt(1, c.getId_categoria());
            ResultSet rs = ps.executeQuery();
        
            if (rs.next()) {
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setEstado(rs.getString("estado"));
                return true;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error sql al buscar categoria", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al buscar categoria", e);
        }
            return false;
    }
    
    public Categorias buscarPorNombre(String nombreCategoria) {
        Categorias cat = null;
        String sql = "SELECT * FROM categorias WHERE nombre = ?";
    
        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
        
            ps.setString(1, nombreCategoria);
            ResultSet rs = ps.executeQuery();
        
            if (rs.next()) {
                cat = new Categorias();
                cat.setId_categoria(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("nombre"));
                cat.setDescripcion(rs.getString("descripcion"));
                cat.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoCategoria.class.getName()).log(Level.SEVERE, "Error SQL al buscar categoría por nombre", e);
        } catch (Exception e) {
            Logger.getLogger(DaoCategoria.class.getName()).log(Level.SEVERE, "Error general al buscar categoría por nombre", e);
        }
            return cat;
    }
    
    public boolean tieneDependencias(int idCategoria) {
        String sql = "SELECT COUNT(*) FROM libros WHERE id_categoria = ?";
    
        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al verificar dependencias de categoría", e);
        }
            return false;
    }
}