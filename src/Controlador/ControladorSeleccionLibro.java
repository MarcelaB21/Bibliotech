/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.base.ControladorSeleccionBase;
import Dao.DaoLibro;
import Modelo.Libros;
import Util.Mensajes;
import Vista.FrmPrestamo;
import Vista.SeleccionarDatos;
import java.util.List;

/**
 *Controlador utilizado para la selección de libros desde una vista común.
 * Implementa la estructura del controlador base y envía la información del libro
 * seleccionado a la vista correspondiente, normalmente dentro del módulo de 
 * préstamos.
 * @author Usuario
 */
public class ControladorSeleccionLibro  extends ControladorSeleccionBase<Libros>{
    private DaoLibro dao;
    private FrmPrestamo vista;
    private ControladorPrestamo controladorPrincipal; 
    
    public ControladorSeleccionLibro(SeleccionarDatos vistaSeleccion,DaoLibro dao,FrmPrestamo vista,ControladorPrestamo controladorPrincipal){
        super(vistaSeleccion);
        this.dao= dao;
        this.vista= vista;
        this.controladorPrincipal = controladorPrincipal; 
        
    
    }
    
    @Override
    protected void configurarTabla(){
       modelo= new javax.swing.table.DefaultTableModel();
       modelo.addColumn("ID");
       modelo.addColumn("Titulo");
       modelo.addColumn("Autor");
       modelo.addColumn("Editorial");
       modelo.addColumn("Año");
       modelo.addColumn("ISBN");
       modelo.addColumn("Categoria");
       modelo.addColumn("Disponible");
       vistaSeleccion.getTblBuscar().setModel(modelo);
             
    }
    
    @Override
    protected void cargarDatos(){
        List<Libros> lista= dao.Listar();
        modelo.setRowCount(0);
        for(Libros lb: lista){
            modelo.addRow(new Object[]{
                lb.getId_libro(),
                lb.getTitulo(),
                lb.getAutor(), 
                lb.getEditorial(), 
                lb.getAnio(),
                lb.getIsbn(), 
                lb.getNombreCategoria(), 
                lb.isDisponible()
            });
         }
    }
    
    @Override
    protected void filaSeleccionada(){
        int fila = vistaSeleccion.getTblBuscar().getSelectedRow();
        
        if(fila>=0){
            String id= vistaSeleccion.getTblBuscar().getValueAt(fila, 0).toString();
            String titulo= vistaSeleccion.getTblBuscar().getValueAt(fila, 1).toString();
            String autor= vistaSeleccion.getTblBuscar().getValueAt(fila, 2).toString();
            String editorial = vistaSeleccion.getTblBuscar().getValueAt(fila,3).toString();
            String anio= vistaSeleccion.getTblBuscar().getValueAt(fila, 4).toString();
            String isbn = vistaSeleccion.getTblBuscar().getValueAt(fila ,5 ).toString();
            String categoria = vistaSeleccion.getTblBuscar().getValueAt(fila, 6).toString();
            String disponible = vistaSeleccion.getTblBuscar().getValueAt(fila, 7).toString();
            
            
            String infoLibro = String.format(
            " ID: %s\nTítulo: %s\nAutor: %s\nEditorial: %s\nAño: %s\nISBN: %s\nCategoría: %s\nDisponible: %s",
            id, titulo, autor, editorial, anio, isbn, categoria, disponible
            );
            
            vista.getTextLibro().setText(infoLibro);
             
        }
    }
    
    @Override
    protected void enviarSeleccion(){
        int fila= vistaSeleccion.getTblBuscar().getSelectedRow();
        if(fila < 0 ){
            Mensajes.mostrarInfo("Seleccione un libro primero");
            return ;
        }
        
        String disponible = vistaSeleccion.getTblBuscar().getValueAt(fila , 7).toString();
        if(disponible .equalsIgnoreCase("no disponible")){
            Mensajes.mostrarAdvertencia("El libro seleccionado no está disponible para préstamo");
            return;
        }
        
        
        Libros libro = new Libros();
        libro.setId_libro(Integer.parseInt(vistaSeleccion.getTblBuscar().getValueAt(fila, 0).toString()));
        libro.setTitulo(vistaSeleccion.getTblBuscar().getValueAt(fila, 1).toString());
        libro.setAutor(vistaSeleccion.getTblBuscar().getValueAt(fila, 2).toString());
        libro.setEditorial(vistaSeleccion.getTblBuscar().getValueAt(fila, 3).toString());
        libro.setAnio(Integer.parseInt(vistaSeleccion.getTblBuscar().getValueAt(fila, 4).toString()));
        libro.setIsbn(vistaSeleccion.getTblBuscar().getValueAt(fila, 5).toString());
        libro.setNombreCategoria(vistaSeleccion.getTblBuscar().getValueAt(fila, 6).toString());

       
        libro.setDisponible(disponible.equalsIgnoreCase("disponible"));

        
        controladorPrincipal.agregarLibroSeleccionado(libro);

       
        cerrar(); 
    }
   
}
