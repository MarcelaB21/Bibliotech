/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelo.Conexion;
import Modelo.Libros;
import Util.LoggerConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.*;

/**
 *
 * Clase DaoLibro
 * Gestiona las operaciones CRUD de la entidad "libros" en la base de datos.
 * Incluye inserción, listado, edición, eliminación y búsqueda por ID.
 * También verifica si un libro tiene dependencias o está en uso mediante préstamos.
 * Utiliza consultas preparadas y manejo de errores con Logger.
 *
 * @author Usuario
 */
public class DaoLibro {
    
    private static final Logger logger = LoggerConfig.getLogger(DaoLibro.class);
    
    public boolean insertar (Libros lb){
        String sql="INSERT INTO libros (titulo, autor, editorial, anio, isbn, id_categoria, disponible) VALUES (?,?,?,?,?,?,?) ";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql)){
            ps.setString(1, lb.getTitulo());
            ps.setString(2,lb.getAutor());
            ps.setString(3,lb.getEditorial());
            ps.setInt(4,lb.getAnio());
            ps.setString(5, lb.getIsbn());
            ps.setInt(6, lb.getId_categoria());
            ps.setBoolean(7, lb.isDisponible());
            
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            logger.log(Level.SEVERE, "Error sql al insertar el libro" ,e );
        }catch( Exception e){
            logger.log(Level.SEVERE, "Error general al insertar el libro" ,e );
        }
        return false;
    }
    
    public List<Libros> Listar() {
        List<Libros> lista = new ArrayList<>();
        String sql = "SELECT l.*, c.nombre  FROM libros l INNER JOIN categorias  c ON l.id_categoria = c.id_categoria ORDER BY l.id_libro ASC";

        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Libros lb = new Libros();
                lb.setId_libro(rs.getInt("id_libro"));
                lb.setTitulo(rs.getString("titulo"));
                lb.setAutor(rs.getString("autor"));
                lb.setEditorial(rs.getString("editorial"));
                lb.setAnio(rs.getInt("anio"));
                lb.setIsbn(rs.getString("isbn"));
                lb.setId_categoria(rs.getInt("id_categoria"));
                lb.setNombreCategoria(rs.getString("nombre")); 
                lb.setDisponible(rs.getBoolean("disponible"));
                lista.add(lb);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al listar libro", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al listar libro", e);
        }
           return lista;
    }

    public boolean editar(Libros lb){
        String sql="UPDATE libros SET titulo =? , autor=?, editorial= ?, anio=?, isbn=?, id_categoria=?, disponible=? WHERE id_libro =? ";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,lb.getTitulo());
            ps.setString(2,lb.getAutor());
            ps.setString(3,lb.getEditorial());
            ps.setInt(4,lb.getAnio());
            ps.setString(5, lb.getIsbn());
            ps.setInt(6,lb.getId_categoria());
            ps.setBoolean(7, lb.isDisponible());
            ps.setInt(8, lb.getId_libro());
            
            return ps.executeUpdate() > 0 ;
        }catch(SQLException e){
            logger.log(Level.SEVERE,"Error sql al editar libro", e);
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error general al editar libro", e);
        }
        return false; 
    }
    
    public boolean eliminar(Libros lb){
        String sql= "DELETE FROM libros WHERE id_libro =?";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql)){
            ps.setInt(1, lb.getId_libro());
            return ps.executeUpdate()> 0;
        }catch( SQLException e){
            logger.log(Level.SEVERE,"Error sql al eliminar libro ", e);
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error general al eliminar libro ", e);
        }
        return false;
    }
    
    public boolean buscar(Libros lb){
        String sql= "SELECT * FROM  libros WHERE id_libro = ?";
        try(Connection con= Conexion.Conectar();
            PreparedStatement ps= con.prepareStatement(sql)){
            ps.setInt(1, lb.getId_libro());
            ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    
                    lb.setTitulo(rs.getString("titulo"));
                    lb.setAutor(rs.getString("autor"));
                    lb.setEditorial(rs.getString("editorial"));
                    lb.setAnio(rs.getInt("anio"));
                    lb.setIsbn(rs.getString("isbn"));
                    lb.setId_categoria(rs.getInt("id_categoria"));
                    lb.setDisponible(rs.getBoolean("disponible"));
                    return true;
                }
            
        }catch(SQLException e){
            logger.log(Level.SEVERE,"Error sql al buscar libro", e );
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error general al buscar libro", e );
        }
        return false;
    }
    
   /**
    * Verifica si un libro tiene dependencias (si está asociado a algún préstamo).
    * @param idLibro ID del libro a verificar
    * @return true si el libro está en uso, false si no tiene dependencias
    */
    public boolean tieneDependencias(int idLibro) {
        String sql = "SELECT COUNT(*) FROM prestamo_libro WHERE id_libro = ?";
    
        try (Connection con = Conexion.Conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
        
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
        
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al verificar dependencias de libro", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al verificar dependencias de libro", e);
        }
    
            return false;
        }
}
