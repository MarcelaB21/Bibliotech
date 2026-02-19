/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.base.ControladorSeleccionBase;
import Dao.DaoLector;
import Modelo.Lectores;
import Util.Mensajes;
import Vista.FrmPrestamo;
import Vista.SeleccionarDatos;
import java.util.List;

/**
 * Controlador encargado de seleccionar un lector desde la vista de prestamo.
 * Reutiliza la lógica compartida del controlador base y permite enviar la 
 * selección al módulo de préstamos .
 * @author Usuario
 */
public class ControladorSeleccionLector extends ControladorSeleccionBase <Lectores> {
    private DaoLector dao;
    private FrmPrestamo vista;
    
    public ControladorSeleccionLector(SeleccionarDatos vistaSeleccion,DaoLector dao,FrmPrestamo vista){
        super(vistaSeleccion);
        this.dao=dao;
        this.vista=vista;
        
        
    }
    
    @Override
    protected void configurarTabla(){
        modelo = new javax.swing.table.DefaultTableModel();
        modelo.addColumn("Consecutivo");
        modelo.addColumn("Nombre"); 
        modelo.addColumn("Identificacion");
        modelo.addColumn("Correo");
        modelo.addColumn("Contacto");
        modelo.addColumn("Tipo Usuario");
        modelo.addColumn("Registro");
        vistaSeleccion.getTblBuscar().setModel(modelo);
    }
    
    @Override
    protected void cargarDatos(){
        List<Lectores> lista = dao.Listar();
        modelo.setRowCount(0);
        for(Lectores lt:lista){
            modelo.addRow(new Object[]{
                lt.getConsecutivo(),
                lt.getNombre(),
                lt.getIdentificacion(),
                lt.getCorreo(),
                lt.getContacto(),
                lt.getTipodeusuario(),
                lt.getRegistro()
            });
        }
    }
    
    @Override
    protected void filaSeleccionada(){
        int fila = vistaSeleccion.getTblBuscar().getSelectedRow();
        if(fila >=0 ){
           vista.getTextConsecutivoLector().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 0).toString());
           vista.getTextNombreLector().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 1).toString());
           vista.getTextDocumentoLector().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 2).toString());
           vista.getTextCorreoLector().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 3).toString());
           vista.getTextContactoLector().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 4).toString());
           vista.getTextTipoLector().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 5).toString());
           vista.getTextRegistroLector().setText(vistaSeleccion.getTblBuscar().getValueAt(fila, 6).toString());
        }
    
    }
    
    
    @Override
    protected void enviarSeleccion(){
        if(vista.getTextConsecutivoLector().getText().trim().isEmpty()){
            Mensajes.mostrarInfo("Seleccione un lector primero.");
            return;
        }
        
        vista.setLectorSeleccionado(
                Integer.parseInt(vista.getTextConsecutivoLector().getText().trim()),
                vista.getTextNombreLector().getText().trim(),
                vista.getTextDocumentoLector().getText().trim(),
                vista.getTextCorreoLector().getText().trim(),
                vista.getTextContactoLector().getText().trim(),
                vista.getTextTipoLector().getText().trim(),
                vista.getTextRegistroLector().getText().trim()
    
        );
        
        cerrar();
    }
   
}
