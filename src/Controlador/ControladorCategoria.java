/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoCategoria;
import Modelo.Categorias;
import Modelo.Usuarios;
import Util.Mensajes;
import Util.PermisosUI;
import Validador.ValidadorCategoria;

import Vista.FrmCategoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *Controlador encargado de gestionar la vista de categorías y coordinar las
 * operaciones CRUD (listar, editar, buscar, guardar y eliminar). 
 * Maneja la interacción entre la vista, el modelo y el DAO, respondiendo a los
 * eventos generados por el usuario dentro del módulo de categorías.
 * @author Usuario
 */
public class ControladorCategoria implements ActionListener {
    private FrmCategoria vista;
    private DaoCategoria dao;
    private Categorias categoria;
    private DefaultTableModel modelo ; 
    private Usuarios usuarioLogueado;
    
    public ControladorCategoria(FrmCategoria vista ,DaoCategoria dao,Usuarios usuarioLogueado){
        this.vista= vista;
        this.dao=dao;
        this.categoria = new Categorias();
        this.usuarioLogueado= usuarioLogueado;
        
        PermisosUI.configurarPermisos(usuarioLogueado, vista.getbtnGuardar(),vista.getbtnEditar(),vista.getbtnEliminar());
          
        this.vista.getbtnGuardar().addActionListener(this);
        this.vista.getbtnEditar().addActionListener(this);
        this.vista.getbtnEliminar().addActionListener(this);
        this.vista.getbtnBuscar().addActionListener(this);
        
        this.vista.getTblCategoria().addMouseListener(new MouseAdapter(){
          @Override
            public void mouseClicked(MouseEvent e){
                cargarCategoriaSeleccionada();
            }
        });
             listarCategoria(); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource () == vista.getbtnGuardar()){
            guardarCategoria();
        }else if(e.getSource () == vista.getbtnEditar()){
            editarCategoria();
        }else if(e.getSource()== vista.getbtnEliminar()){
            eliminarCategoria();
        }else if(e.getSource()== vista.getbtnBuscar()){
            buscarCategoria();
        }
    }
    
    private boolean validarCampos(String nombre, String estado, String descripcion) {
    if (!ValidadorCategoria.camposCompletos(nombre, estado, descripcion)) {
        Mensajes.mostrarError("Todos los campos deben ser llenados.");
        return false;
    }

    if (!ValidadorCategoria.validarNombre(nombre)) {
        Mensajes.mostrarError("El nombre debe tener entre 3 y 50 caracteres y solo letras.");
        return false;
    }

    if (!ValidadorCategoria.validarEstado(estado)) {
        Mensajes.mostrarError("El estado debe ser 'activo' o 'inactivo'.");
        return false;
    }

    if (!ValidadorCategoria.validarDescripcion(descripcion)) {
        Mensajes.mostrarError("La descripción contiene caracteres no permitidos o es demasiado larga.");
        return false;
    }
    return true;
}
    
    private void listarCategoria(){
        modelo = (DefaultTableModel) vista.getTblCategoria().getModel();
        modelo.setRowCount(0);
         
      List<Categorias>lista = dao.Listar();
        for(Categorias categoria :lista){
          modelo.addRow(new Object[]{
              categoria.getId_categoria(),
              categoria.getNombre(),
              categoria.getDescripcion(),
              categoria.getEstado()
              
          
          });
      }
    }
    
    private void cargarCategoriaSeleccionada() {
        int fila = vista.getTblCategoria().getSelectedRow();

        if (fila != -1) {
            vista.getTextCategoria().setText(vista.getTblCategoria().getValueAt(fila, 0).toString());
            vista.getTextNombre().setText(vista.getTblCategoria().getValueAt(fila, 1).toString());
            vista.getTextDescripcion().setText(vista.getTblCategoria().getValueAt(fila, 2).toString());
            vista.getTextEstado().setText(vista.getTblCategoria().getValueAt(fila, 3).toString());
        }
    }
    
    private void guardarCategoria(){
        
        String nombre = vista.getTextNombre().getText().trim();
        String estado = vista.getTextEstado().getText().trim();
        String descripcion = vista.getTextDescripcion().getText().trim();

        if (!validarCampos(nombre, estado, descripcion)) return;

        
        categoria= new Categorias();
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        categoria.setEstado(estado);
        if(dao.insertar(categoria)){  
            Mensajes.mostrarExito("Se registro  exitosamente.");
            listarCategoria();
            limpiarFormulario();
            
        }else{
            Mensajes.mostrarError("No se pudo registrar la categoria . Verifique que los datos no se dupliquen  o que no exceda el límite.");
        }
    }
    
    private void editarCategoria() {
        int fila = vista.getTblCategoria().getSelectedRow();
        
        if (fila == -1 || vista.getTextCategoria().getText().trim().isEmpty()) {
            Mensajes.mostrarInfo("Seleccione una categoría de la tabla para editar.");
            return;
        }
        
        String nombre = vista.getTextNombre().getText().trim();
        String estado = vista.getTextEstado().getText().trim();
        String descripcion = vista.getTextDescripcion().getText().trim();

        if (!validarCampos(nombre, estado, descripcion)) return;

        try {
        
            int idCategoria = Integer.parseInt(vista.getTextCategoria().getText().trim());
            categoria = new Categorias();
            categoria.setId_categoria(idCategoria);  
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);
            categoria.setEstado(estado);

            if(dao.tieneDependencias(idCategoria)){
                   Mensajes.mostrarAdvertencia("No se puede editar esta categoria porque ya está asignada a un libro y/o prestamo.");
            }else{
                if (dao.editar(categoria)) {
                    Mensajes.mostrarExito("Categoría actualizada correctamente.");
                } else {
                    Mensajes.mostrarError("No se pudo actualizar la categoría.");
                }
            }
            listarCategoria();      
            limpiarFormulario(); 
        } catch (NumberFormatException ex) {
            Mensajes.mostrarError("El ID de la categoría no es válido.");
        }
    }
    
    private void eliminarCategoria() {
        String textoId = vista.getTextCategoria().getText().trim();

        if (!textoId.isEmpty()) {
            try {
                    int idCategoria = Integer.parseInt(textoId);
                    
                    if(dao.tieneDependencias(idCategoria)){
                       Mensajes.mostrarAdvertencia("No se puede eliminar esta categoria porque ya está asignada a un libro y/o prestamo");
                       return;
                    }
                    
                    boolean confirmacion = Mensajes.confirmar("¿Estás seguro de eliminar la categoría?");
                        if (confirmacion) {
                            categoria = new Categorias();
                            categoria.setId_categoria(idCategoria);

                            boolean eliminado = dao.eliminar(categoria);
                                if (eliminado) {
                                    listarCategoria();       
                                    limpiarFormulario();     
                                    Mensajes.mostrarExito("Se eliminó exitosamente.");
                                } else {
                                    Mensajes.mostrarError("Ocurrió un error al intentar eliminar la categoría.");
                                }
                        }
            } catch (NumberFormatException ex) {
                Mensajes.mostrarError("El ID de la categoría no es válido.");
            }
        } else {
            Mensajes.mostrarInfo("Seleccione una categoría para eliminar.");
        }
    }
    
    private void buscarCategoria() {
        String idTexto = vista.getTextCategoria().getText().trim();

        if (!idTexto.isEmpty()) {
            try {
                int idCategoria = Integer.parseInt(idTexto);

                categoria.setId_categoria(idCategoria);

                if (dao.buscar(categoria)) {
                    
                    vista.getTextCategoria().setText(String.valueOf(categoria.getId_categoria()));
                    vista.getTextNombre().setText(categoria.getNombre());
                    vista.getTextDescripcion().setText(categoria.getDescripcion());
                    vista.getTextEstado().setText(categoria.getEstado());
                } else {
                    Mensajes.mostrarError("La categoría no se encontró.");
                }
            } catch (NumberFormatException e) {
                Mensajes.mostrarError("El ID de la categoría debe ser un número válido.");
            }
        } else {
            Mensajes.mostrarInfo("Debe ingresar un ID de la categoría para realizar la búsqueda.");
        }
    }

    private void limpiarFormulario() {
        vista.getTextCategoria().setText("");
        vista.getTextNombre().setText("");
        vista.getTextDescripcion().setText("");
        vista.getTextEstado().setText("");
    }
}    
     
  
    




