/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.base.ControladorSeleccionBase;
import Dao.DaoCategoria;

import Vista.FrmLibro;
import Vista.SeleccionarDatos;
import Modelo.Categorias;
import Util.Mensajes;
import java.util.List;


/**
 * Controlador especializado en la selección de categorías desde una vista
 * común de búsqueda. Extiende la lógica de un controlador base, cargando la
 * lista de categorías disponibles y permitiendo seleccionar una para asignarla
 * a un libro.
 * @author Usuario
 */
public class ControladorSeleccionCategoria extends ControladorSeleccionBase<Categorias>{
    
    
    private DaoCategoria dao;
    private FrmLibro vista;
    
    
    
    public ControladorSeleccionCategoria (SeleccionarDatos vistaSeleccion,DaoCategoria dao,FrmLibro vista){
      super(vistaSeleccion);  
      this.dao=dao;
      this.vista=vista;
      
      
    }  
      
    @Override
    protected void configurarTabla(){
        modelo = new javax.swing.table.DefaultTableModel();
        modelo.setRowCount(0);
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Estado");
        
        vistaSeleccion.getTblBuscar().setModel(modelo);
    }
    
    @Override
    protected void cargarDatos() {
        List<Categorias> lista = dao.Listar();
        modelo.setRowCount(0);
        for (Categorias c : lista) {
            modelo.addRow(new Object[]{
                c.getId_categoria(),
                c.getNombre(),
                c.getDescripcion(),
                c.getEstado()
            });
        }
    }
    
    @Override
    protected void filaSeleccionada (){
        
        int fila= vistaSeleccion.getTblBuscar().getSelectedRow();
        if(fila >=0 ){
            vista.getTextIdCategoria().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 0).toString());
            vista.getTextNombreCategoria().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 1).toString());
            vista.getTextDescripcionCategoria().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 2).toString());
            vista.getTextEstadoCategoria().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 3).toString());
        
        }
    }
    
    @Override
    protected void enviarSeleccion() {
        if (vista.getTextIdCategoria().getText().trim().isEmpty()) {
            Mensajes.mostrarInfo("Seleccione una categoría primero");
            return;
        }
        
        String estado = vista.getTextEstadoCategoria().getText().trim();
            if (estado.equalsIgnoreCase("INACTIVO")) {
                Mensajes.mostrarAdvertencia("No se puede seleccionar una categoría inactiva.");
                return;
            }
            
            int id = Integer.parseInt(vista.getTextIdCategoria().getText().trim());

            String nombre = vista.getTextNombreCategoria().getText().trim();
            String descripcion = vista.getTextDescripcionCategoria().getText().trim();
            
            vista.setCategoriaSeleccionada(id, nombre, descripcion, estado);
            
            cerrar();
    }
    

}
