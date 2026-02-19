 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoCategoria;
import Dao.DaoLibro;
import Modelo.Categorias;
import Modelo.Libros;
import Modelo.Usuarios;
import Util.Mensajes;
import Util.PermisosUI;
import Validador.ValidadorLibro;
import Vista.FrmLibro;
import Vista.SeleccionarDatos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * Controlador responsable de administrar la vista de libros, incluyendo las
 * operaciones CRUD y la selección de categorías asociadas. Extiende la lógica
 * base de los controladores generales, añadiendo el manejo de relaciones entre
 * libros y categorías.
 *
 * @author Usuario
 */
public class ControladorLibro implements ActionListener{
    
    private FrmLibro vista;
    private DaoLibro dao;
    private Libros libro;
    private DefaultTableModel modelo;
    private Categorias categoria;
    private DaoCategoria daocategoria;
    private Usuarios usuarioLogueado;
    
    public ControladorLibro(FrmLibro vista,DaoLibro dao,DaoCategoria daocategoria,Usuarios usuarioLogueado)  {
        this.vista=vista;
        this.dao= dao;
        this.daocategoria=daocategoria;
        this.libro = new Libros();
        this.categoria = new Categorias();
        this.usuarioLogueado = usuarioLogueado;
        
        PermisosUI.configurarPermisos(usuarioLogueado, vista.getbtnGuardar(),vista.getbtnEditar(),vista.getbtnEliminar(),vista.getbtnBuscarCategoria());
        
        
        this.vista.getbtnGuardar().addActionListener(this);
        this.vista.getbtnEditar().addActionListener(this);
        this.vista.getbtnEliminar().addActionListener(this);
        this.vista.getbtnBuscar().addActionListener(this);
        this.vista.getbtnBuscarCategoria().addActionListener(this);
    
        this.vista.getTblLibro().addMouseListener(new MouseAdapter(){
        @Override
            public void mouseClicked(MouseEvent e){
                cargarLibroSeleccionado();
            }
        }); 
            listarLibro();
        
    }
                
    
    @Override
    public void actionPerformed (ActionEvent e ){
        if(e.getSource() == vista.getbtnGuardar()){
            guardarLibros();
        }else if(e.getSource()== vista.getbtnEditar()){
            editarLibros();
        }else if(e.getSource()==vista.getbtnEliminar()){
            eliminarLibros();
        }else if(e.getSource() == vista.getbtnBuscar()){
            buscarLibros();
        }else if(e.getSource()== vista.getbtnBuscarCategoria()){
            buscarCategoria();
        }
        
    }
    
    private boolean validarCampos( String titulo , String autor , String editorial ,String  anio ,String isbn ,String  categoria,String disponible){
        if(!ValidadorLibro.camposCompletos(titulo, autor, editorial, anio, isbn, categoria)){
            Mensajes.mostrarError("Todos los campos deben ser llenados.");
            return false;
        }
        
        if(!ValidadorLibro.validarTitulo(titulo)){
            Mensajes.mostrarError("El titulo debe tener entre 3 y 100 caracteres ");
            return false;
            
        }
        
        if(!ValidadorLibro.validarAutor(autor)){
            Mensajes.mostrarError("El titulo debe tener entre 3 y 100 caracteres y solo letras  ");
            return false;
        }
        
        if(!ValidadorLibro.validarEditorial(editorial)){
            Mensajes.mostrarError("La editorial debe tener entre 3 y 100 caracteres ");
        }
        
        if (!ValidadorLibro.validarAniodesdetexto(anio)) {
            Mensajes.mostrarError("El año ingresado no es válido. Debe ser un número entre 1000 y 9999.");
            return false;
        }
        if(!ValidadorLibro.validarISBN(isbn)){
           Mensajes.mostrarError("El isbn debe tener entre 10 y 20 caracteres ");
           return false;
        }
        
        if (!ValidadorLibro.validarDisponible(disponible)) {
            Mensajes.mostrarError("El valor debe ser 'activo' o 'inactivo'");
            return false;
        }
        return true;
    }
    
    private void listarLibro(){
        modelo= (DefaultTableModel)vista.getTblLibro().getModel();
        modelo.setRowCount(0);
        List<Libros> lista = dao.Listar();
            for(Libros libro: lista){
           modelo.addRow(new Object[]{
                libro.getId_libro(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getEditorial(),
                libro.getAnio(),
                libro.getIsbn(),
                libro.getNombreCategoria(), 
                libro.isDisponible()? "disponible" : "no disponible",
            });
            }
     
    }
    
    private void cargarLibroSeleccionado() {
    int fila = vista.getTblLibro().getSelectedRow();
    
    if (fila == -1) {
        Mensajes.mostrarInfo("Por favor, selecciona un libro de la tabla.");
        return;
    }

    try {
        vista.getTextLibro().setText(vista.getTblLibro().getValueAt(fila, 0).toString());
        vista.getTextTitulo().setText(vista.getTblLibro().getValueAt(fila, 1).toString());
        vista.getTextAutor().setText(vista.getTblLibro().getValueAt(fila, 2).toString());
        vista.getTextEditorial().setText(vista.getTblLibro().getValueAt(fila, 3).toString());
        vista.getTextAño().setText(vista.getTblLibro().getValueAt(fila, 4).toString());
        vista.getTextIsbn().setText(vista.getTblLibro().getValueAt(fila, 5).toString());
        vista.getTextNombreCategoria().setText(vista.getTblLibro().getValueAt(fila, 6).toString());
        vista.getBoxDisponible().setSelectedItem(vista.getTblLibro().getValueAt(fila, 7).toString());
        
        
        String nombreCategoria = vista.getTextNombreCategoria().getText();
        Categorias categoria = daocategoria.buscarPorNombre(nombreCategoria);
        
        if (categoria != null) {
            vista.getTextIdCategoria().setText(String.valueOf(categoria.getId_categoria()));
            vista.getTextDescripcionCategoria().setText(categoria.getDescripcion());
            vista.getTextEstadoCategoria().setText(categoria.getEstado());
        } else {
            Mensajes.mostrarInfo("La categoría no fue encontrada en la base de datos.");
        }
    } catch (Exception e) {
        Mensajes.mostrarError("Ocurrió un error al cargar el libro: " + e.getMessage());
    }
}
    
    private void guardarLibros(){
        String titulo = vista.getTextTitulo().getText().trim();
        String autor = vista.getTextAutor().getText().trim();
        String editorial = vista.getTextEditorial().getText().trim();
        String anioStr = vista.getTextAño().getText().trim();
        String isbn = vista.getTextIsbn().getText().trim();
        String categoriaStr = vista.getTextIdCategoria().getText().trim();
        String disponible = vista.getBoxDisponible().getSelectedItem().toString().trim();
         
        
        if(!validarCampos(titulo, autor, editorial, anioStr, isbn, categoriaStr, disponible))return;
    
         
        int anio = Integer.parseInt(anioStr);
        int categoria = Integer.parseInt(categoriaStr);
        boolean estado = ValidadorLibro.convertirDisponible(disponible);
        
        
        
        libro = new Libros();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setAnio(anio);
        libro.setIsbn(isbn);
        libro.setId_categoria(categoria);
        libro.setDisponible(estado);
        
        if(dao.insertar(libro)){
            Mensajes.mostrarExito("Se registro  exitosamente");
            listarLibro();
            limpiarFormulario();
            
        }else{
            Mensajes.mostrarError("No se pudo registrar el libro  . Verifique que los datos no se dupliquen  o que no exceda el límite.");
        }
    }
    
    private void editarLibros(){
        int fila= vista.getTblLibro().getSelectedRow();
        
        if(fila ==-1 || vista.getTextIdCategoria().getText().trim().isEmpty()){
            Mensajes.mostrarInfo("Seleccione un libro  de la tabla para editar.");
            return;
        }
        
        String titulo = vista.getTextTitulo().getText().trim();
        String autor = vista.getTextAutor().getText().trim();
        String editorial = vista.getTextEditorial().getText().trim();
        String anioStr = vista.getTextAño().getText().trim();
        String isbn = vista.getTextIsbn().getText().trim();
        String categoriaStr = vista.getTextIdCategoria().getText().trim();
        String disponible = vista.getBoxDisponible().getSelectedItem().toString().trim();
         
        
        if(!validarCampos(titulo, autor, editorial, anioStr, isbn, categoriaStr, disponible))return; 
        
        try{
            
            int anio = Integer.parseInt(anioStr);
            int categoria = Integer.parseInt(categoriaStr);
            boolean estado = ValidadorLibro.convertirDisponible(disponible);
            int idLibro= Integer.parseInt(vista.getTextLibro().getText().trim());
            
            libro = new Libros();
            libro.setId_libro(idLibro);
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setAnio(anio);
            libro.setIsbn(isbn);
            libro.setId_categoria(categoria);
            libro.setDisponible(estado);
            
            if(dao.tieneDependencias(idLibro)){
                  Mensajes.mostrarAdvertencia("No se puede editar este libro  porque ya está asignado a un prestamo.");
            }else{
                if(dao.editar(libro)){
                    Mensajes.mostrarExito("Libro actualizado correctamente.");
                }else{
                    Mensajes.mostrarError("No se pudo actualizar el libro .");
                }
            }
            listarLibro();
            limpiarFormulario();
        }catch (NumberFormatException ex) {
            Mensajes.mostrarError("El ID del libro no es válido.");
        }
    }
    
    private void eliminarLibros(){
        String textoId= vista.getTextLibro().getText().trim();
        
        if(!textoId.isEmpty()){
            try {
                
                int idLibro= Integer.parseInt(textoId);
                if(dao.tieneDependencias(idLibro)){
                    Mensajes.mostrarAdvertencia("No se puede eliminar este libro porque ya está asignado a un prestamo");
                    return;
                }
                boolean confirmacion= Mensajes.confirmar("¿Estás seguro de eliminar el libro?");
                    if(confirmacion){
                        libro = new Libros();
                        libro.setId_libro(idLibro);
                        
                        boolean eliminado= dao.eliminar(libro);
                        
                        if(eliminado){
                            listarLibro();
                            limpiarFormulario();
                            Mensajes.mostrarInfo("Se eliminó exitosamente.");
                        
                        }else{
                        Mensajes.mostrarError("Ocurrió un error al intentar eliminar el libro .");
                        }
                    }
                
            } catch (NumberFormatException ex) {
                Mensajes.mostrarError("El ID del libro no es válido.");
            }
        }else{
            Mensajes.mostrarInfo("Seleccione un libro para eliminar.");
        }
    }
    
    private void buscarLibros(){
        String idTexto= vista.getTextLibro().getText().trim();

        if(!idTexto.isEmpty()){
            try{
                int idLibro= Integer.parseInt(idTexto);
                libro.setId_libro(idLibro);

                if(dao.buscar(libro)){
                    vista.getTextLibro().setText(String.valueOf(libro.getId_libro()));
                    vista.getTextTitulo().setText(libro.getTitulo());
                    vista.getTextAutor().setText(libro.getAutor());
                    vista.getTextEditorial().setText(libro.getEditorial());
                    vista.getTextAño().setText(String.valueOf(libro.getAnio()));
                    vista.getTextIsbn().setText(libro.getIsbn());
                    vista.getTextIdCategoria().setText(String.valueOf(libro.getId_categoria()));

                    String estadoTexto = libro.isDisponible() ? "disponible" : "no disponible";
                    vista.getBoxDisponible().setSelectedItem(estadoTexto);

                    String categoriaIdTexto = vista.getTextIdCategoria().getText().trim();
                        if(!categoriaIdTexto.isEmpty()){
                            categoria.setId_categoria(Integer.parseInt(categoriaIdTexto));
                            if(daocategoria.buscar(categoria)){
                                vista.getTextNombreCategoria().setText(categoria.getNombre());
                                vista.getTextDescripcionCategoria().setText(categoria.getDescripcion());
                                vista.getTextEstadoCategoria().setText(categoria.getEstado());
                            } else {
                                Mensajes.mostrarError("La categoría no fue encontrada.");
                            }
                        } else {
                            Mensajes.mostrarInfo("La categoría no puede estar vacía.");
                        }
                } else {
                   Mensajes.mostrarError("El libro no se encontró.");
                }
            } catch(NumberFormatException e){
                Mensajes.mostrarError("El ID del libro debe ser un número válido.");
            }
        } else {
            Mensajes.mostrarInfo("Debe ingresar un ID del libro para realizar la búsqueda.");
        }
    }

    private void buscarCategoria() {
        
        SeleccionarDatos seleccionardatos = new SeleccionarDatos();
        DaoCategoria daoCategoria = new DaoCategoria();
        ControladorSeleccionCategoria controlador = new ControladorSeleccionCategoria(seleccionardatos, daoCategoria, vista);
        
        controlador.mostrar();
    }
    
    private void limpiarFormulario(){
        vista.getTextLibro().setText("");
        vista.getTextTitulo().setText("");
        vista.getTextAutor().setText("");
        vista.getTextEditorial().setText("");
        vista.getTextAño().setText("");
        vista.getTextIsbn().setText("");
        vista.getTextIdCategoria().setText("");
        vista.getTextNombreCategoria().setText("");
        vista.getTextDescripcionCategoria().setText("");
        vista.getTextEstadoCategoria().setText("");
        vista.getBoxDisponible().setSelectedIndex(-1);
    }
    
}
