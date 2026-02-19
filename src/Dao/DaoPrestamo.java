/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;


import Modelo.Conexion;
import Modelo.Lectores;
import Modelo.Libros;
import Modelo.Prestamos;
import Util.LoggerConfig;
import Validador.ValidadorPrestamo;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *DaoPrestamo gestiona todas las operaciones relacionadas con los préstamos:
 * - Generación de informes DOCX basados en una plantilla.
 * - CRUD en la base de datos.
 * - Manejo de relaciones préstamo–libro en tabla intermedia.
 * - Apertura y verificación del archivo generado.
 *
 * @author Usuario
 */


public class DaoPrestamo {
    
    private static final Logger logger = LoggerConfig.getLogger(DaoPrestamo.class);
    
    private static final String BASE_PATH = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Prestamos-bibliotech";
    private static final String PLANTILLA_PATH = BASE_PATH + File.separator + "plantilla_informe_prestamo.docx";
    private static final String DESTINO_DIR = BASE_PATH + File.separator + "Informes_Generados" + File.separator;


    /**
     * Genera un informe .docx basado en una plantilla.
     * Reemplaza marcadores del documento con los datos del préstamo y del lector.
     * También inserta los libros asociados en la tabla del documento.
     *
     * @param prestamo Datos del préstamo.
     * @param lector   Datos del lector.
     * @return Ruta del archivo creado o null si ocurre un error.
     */
    public String crearInformePrestamo(Prestamos prestamo,Lectores lector){
        
        String fechaPrestamoFormateada = ValidadorPrestamo.formatFecha(prestamo.getFecha_prestamo());
        String fechaDevolucionFormateada = ValidadorPrestamo.formatFecha(prestamo.getFecha_devolucion());
        
        String destinoPath = DESTINO_DIR + "Informe_Prestamo_" + prestamo.getId_prestamo() + ".docx"; 
        
        File plantilla = new File(PLANTILLA_PATH); 
        if(!plantilla.exists()){
           System.err.println("❌ No se encontró la plantilla en: " + PLANTILLA_PATH);
           return null; 
        }
        
        try(FileInputStream fis = new FileInputStream(plantilla);
            XWPFDocument doc = new XWPFDocument(fis)){
            
            
            for(XWPFParagraph p : doc.getParagraphs()){
                for(XWPFRun run : p.getRuns()){
                  String text= run.getText(0);
                  if(text != null){
                     text = text.replace("{NOMBRE}",lector.getNombre())
                             .replace("{CEDULA}", lector.getIdentificacion())
                             .replace("{CONSECUTIVO}", String.valueOf(lector.getConsecutivo()))
                             .replace("{TIPO_USUARIO}", lector.getTipodeusuario())
                             .replace("{CONTACTO}",lector.getContacto())
                             .replace("{CORREO}", lector.getCorreo())
                             .replace("{REGISTRO}", lector.getRegistro())
                             .replace("{ID_PRESTAMO}", String.valueOf(prestamo.getId_prestamo()))
                             .replace("{FECHA_PRESTAMO}", fechaPrestamoFormateada)
                             .replace("{FECHA_DEVOLUCION}", fechaDevolucionFormateada)
                             .replace("{ESTADO}",prestamo.getEstado());
                        run.setText(text,0);
                  }
                }
            }
            
            List<XWPFTable> tablas = doc.getTables();
            if (!tablas.isEmpty()) {
                
                XWPFTable tablaLibros = tablas.get(0); 

                List<Libros> libros = prestamo.getLibrosSeleccionados();
                if (!libros.isEmpty()) {
                   
                    for (Libros libro : libros) {
                        XWPFTableRow fila = tablaLibros.createRow();

                        
                        for (int i = 0; i < 6; i++) {
                            if (fila.getCell(i) == null) {
                                fila.addNewTableCell();
                            }
                        }

                        
                        fila.getCell(0).setText(libro.getTitulo());
                        fila.getCell(1).setText(libro.getAutor());
                        fila.getCell(2).setText(libro.getNombreCategoria());
                        fila.getCell(3).setText(libro.getEditorial());
                        fila.getCell(4).setText(String.valueOf(libro.getAnio()));
                        fila.getCell(5).setText(libro.getIsbn());
                    }
                } else {
                    
                    XWPFTableRow filaVacia = tablaLibros.createRow();
                    for (int i = 0; i < 6; i++) {
                        if (filaVacia.getCell(i) == null) filaVacia.addNewTableCell();
                        filaVacia.getCell(i).setText("Sin libros registrados");
                    }
                }
            }

            
            File destino = new File(destinoPath);
            destino.getParentFile().mkdirs();
            
            try(FileOutputStream fos = new FileOutputStream(destino)){
                doc.write(fos);
            }
            
            abrirInforme(destinoPath);
            return destinoPath;
            
        
        }catch(IOException e){
           logger.log(Level.SEVERE, "Error al reemplazar marcadores", e);
            return null;

        }
    }
    
    public void abrirInforme(String ruta){
        try{
            File archivo = new File(ruta);
            if(archivo.exists()){
              Desktop.getDesktop().open(archivo);
            }
          
        }catch (IOException e) {
            System.err.println("⚠️ No se pudo abrir el informe: " + e.getMessage());
        }
    }
    
    public boolean insertar(Prestamos p ){
          String  sql = "INSERT INTO prestamos(id_prestamo, consecutivo, fecha_prestamo, fecha_devolucion, estado, url_archivo) VALUES (?, ?, ?, ?, ?, ?)";
          try(Connection con= Conexion.Conectar();
              PreparedStatement ps= con.prepareStatement(sql)){
              
              ps.setInt(1,p.getId_prestamo());
              ps.setInt(2,p.getConsecutivo());
              ps.setDate(3,p.getFecha_prestamo());
              ps.setDate(4,p.getFecha_devolucion());
              ps.setString(5,p.getEstado());
              ps.setString(6,p.getUrl());
              
              int filas= ps.executeUpdate();
              if(filas > 0 ){
                insertarRelacionLibros(p);
                return true;
              }
              
               
          }catch(SQLException e){
              logger.log(Level.SEVERE, "Error sql al insertar Prestamo");
                  

          }catch(Exception e ){
              logger.log(Level.SEVERE, "Error general  al insertar Prestamo");
          }
          return false;
    }
     /**
     * Inserta los libros asociados al préstamo en la tabla intermedia.
     * Evita duplicados usando un Set.
     */
    private boolean insertarRelacionLibros (Prestamos p ){
        String sql = "INSERT IGNORE INTO prestamo_libro (id_prestamo, id_libro)VALUES (?,?)";
        try(Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            Set<Integer>idsUnicos= new HashSet<>();
            for(Libros l : p.getLibrosSeleccionados()){
                if(idsUnicos.add(l.getId_libro())){
                   ps.setInt(1, p.getId_prestamo());
                   ps.setInt(2,l.getId_libro());
                   ps.addBatch();
                }         
            }
            
            int[] result = ps.executeBatch();
            return result.length == idsUnicos.size();
        
        }catch(SQLException e){
            logger.log(Level.SEVERE, "Error SQL al insertar relación préstamo-libros", e);
        }catch(Exception e){
            logger.log(Level.SEVERE, "Error general al insertar relación préstamo-libros", e);
        }
        return false;
    }
    
    public List<Prestamos> listar() {
        List<Prestamos> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos ORDER BY id_prestamo ASC";

        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Prestamos p = new Prestamos();
                p.setId_prestamo(rs.getInt("id_prestamo"));
                p.setConsecutivo(rs.getInt("consecutivo"));
                p.setFecha_prestamo(rs.getDate("fecha_prestamo"));
                p.setFecha_devolucion(rs.getDate("fecha_devolucion"));
                p.setEstado(rs.getString("estado"));
                p.setUrl(rs.getString("url_archivo"));

                
                p.setLibrosSeleccionados(obtenerLibrosPorPrestamo(p.getId_prestamo()));

                lista.add(p);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al listar préstamos", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al listar préstamos", e);
        }
        return lista;
   }
   
    /**
     * Obtiene los libros asociados a un préstamo desde la tabla intermedia.
     */
    
    public List<Libros> obtenerLibrosPorPrestamo(int idPrestamo) {
        List<Libros> libros = new ArrayList<>();
        String sql = 
            "SELECT l.id_libro, l.titulo, l.autor, l.editorial, l.anio, l.isbn, c.nombre AS categoria " +
            "FROM libros l " +
            "INNER JOIN prestamo_libro pl ON l.id_libro = pl.id_libro " +
            "INNER JOIN categorias c ON l.id_categoria = c.id_categoria " +
            "WHERE pl.id_prestamo = ?";

        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPrestamo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Libros libro = new Libros();
                    libro.setId_libro(rs.getInt("id_libro"));
                    libro.setTitulo(rs.getString("titulo"));
                    libro.setAutor(rs.getString("autor"));
                    libro.setEditorial(rs.getString("editorial"));
                    libro.setAnio(rs.getInt("anio"));
                    libro.setIsbn(rs.getString("isbn"));
                    libro.setNombreCategoria(rs.getString("categoria"));
                    libros.add(libro);
                }
            }
        }catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al obtener libros por préstamo", e);
        }catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al obtener libros por préstamo", e);
        }
            return libros;
    }


    
     // Indica si el archivo todavía existe en el sistema tras eliminar el registro BD. 
    private boolean archivoNoEliminado= false;
    
    public boolean  isArchivoNoEliminado(){
    return archivoNoEliminado; 
    }
    public boolean eliminar(Prestamos p) {
        archivoNoEliminado = false; 
        try (Connection con = Conexion.Conectar()) {
            con.setAutoCommit(false); 

            
            if (!eliminarRelacionLibros(p.getId_prestamo(), con)) {
                con.rollback();
                return false;
            }

           
            String sqlPrestamo = "DELETE FROM prestamos WHERE id_prestamo = ?";
                try (PreparedStatement ps = con.prepareStatement(sqlPrestamo)) {
                    ps.setInt(1, p.getId_prestamo());
                        int filas = ps.executeUpdate();

                    if (filas > 0) {
                        con.commit(); 
                        
                
                String rutaArchivo = generarRutaContrato(p);
                File archivo = new File(rutaArchivo);

                if (archivo.exists()) {
                    archivoNoEliminado = true; 
                    
                }

                        return true;
                    } else {
                        con.rollback();
                    }
                }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al eliminar préstamo", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al eliminar préstamo", e);
        }
            return false;
    }
    
    private String generarRutaContrato(Prestamos prestamo) {
        return DESTINO_DIR + "Informe_Prestamo_" + prestamo.getId_prestamo() + ".docx";
    }
 

    /**
     * Elimina relaciones préstamo–libro dentro de una transacción.
     */
    private boolean eliminarRelacionLibros(int idPrestamo, Connection con) {
        String sql = "DELETE FROM prestamo_libro WHERE id_prestamo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPrestamo);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al eliminar relación préstamo-libro", e);
        }
            return false;
    }
    
    public boolean buscar(Prestamos p) {
        String sql = "SELECT id_prestamo, consecutivo, fecha_prestamo, fecha_devolucion, estado, url_archivo "
               + "FROM prestamos WHERE id_prestamo = ?";
        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getId_prestamo());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p.setId_prestamo(rs.getInt("id_prestamo"));
                    p.setConsecutivo(rs.getInt("consecutivo"));
                    p.setFecha_prestamo(rs.getDate("fecha_prestamo"));
                    p.setFecha_devolucion(rs.getDate("fecha_devolucion"));
                    p.setEstado(rs.getString("estado"));
                    p.setUrl(rs.getString("url_archivo"));
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al buscar préstamo", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al buscar préstamo", e);
        }
        return false;
    }
    
    public List<Prestamos> buscarPorFechas(Date prestamo, Date devolucion) {
        List<Prestamos> lista = new ArrayList<>();
        String sql = "SELECT id_prestamo, consecutivo, fecha_prestamo, fecha_devolucion, estado, url_archivo "
               + "FROM prestamos WHERE fecha_prestamo BETWEEN ? AND ?";
        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(prestamo.getTime()));
            ps.setDate(2, new java.sql.Date(devolucion.getTime()));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamos p = new Prestamos();
                    p.setId_prestamo(rs.getInt("id_prestamo"));
                    p.setConsecutivo(rs.getInt("consecutivo"));
                    p.setFecha_prestamo(rs.getDate("fecha_prestamo"));
                    p.setFecha_devolucion(rs.getDate("fecha_devolucion"));
                    p.setEstado(rs.getString("estado"));
                    p.setUrl(rs.getString("url_archivo"));
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al buscar préstamo por fecha.", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al buscar préstamo por fecha.", e);
        }
        return lista;
    }
    
    
    public List<Prestamos> buscarPorNombre(String nombre) {
        List<Prestamos> lista = new ArrayList<>();
        String sql = "SELECT p.id_prestamo, p.consecutivo, p.fecha_prestamo, p.fecha_devolucion, p.estado, p.url_archivo "
               + "FROM prestamos p "
               + "JOIN usuarios_lector u ON p.consecutivo = u.consecutivo "
               + "WHERE u.nombre = ?";
        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamos p = new Prestamos();
                    p.setId_prestamo(rs.getInt("id_prestamo"));
                    p.setConsecutivo(rs.getInt("consecutivo"));
                    p.setFecha_prestamo(rs.getDate("fecha_prestamo"));
                    p.setFecha_devolucion(rs.getDate("fecha_devolucion"));
                    p.setEstado(rs.getString("estado"));
                    p.setUrl(rs.getString("url_archivo"));
                    lista.add(p);
               }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al buscar préstamo por nombre.", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al buscar préstamo por nombre.", e);
        }
        return lista;
    }

    public String obtenerRutaInformeBD(String criterio, String valorBusqueda) {
        String ruta = null;
        String sql = "";

        if (criterio.equalsIgnoreCase("nombre")) {
            sql = "SELECT p.url_archivo " +
                  "FROM prestamos p " +
                  "JOIN usuarios_lector u ON p.consecutivo = u.consecutivo " +
                  "WHERE u.nombre = ?";
        } else if (criterio.equalsIgnoreCase("prestamo")) {
            sql = "SELECT url_archivo FROM prestamos WHERE id_prestamo = ?";
        } else {
            return null;
        }

        try (Connection con = Conexion.Conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, valorBusqueda);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ruta = rs.getString("url_archivo");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error SQL al obtener la ruta del informe.", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general al obtener la ruta del informe.", e);
        }

            return ruta;
    }
}
