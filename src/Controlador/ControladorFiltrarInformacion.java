/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoPrestamo;
import Modelo.Prestamos;
import Util.Mensajes;
import Vista.FiltrarInformacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *  Controlador encargado de aplicar filtros sobre los informes de préstamos.
 * Permite realizar búsquedas por nombre, fecha o ID, actualizando la tabla de
 * resultados según los criterios ingresados por el usuario.
 * @author Usuario
 */
public class ControladorFiltrarInformacion implements ActionListener{
    
       private FiltrarInformacion vista;
       private DaoPrestamo dao;
       private DefaultTableModel modelo;
       private String tipoBusqueda;
       
        public ControladorFiltrarInformacion(FiltrarInformacion vista,DaoPrestamo dao,String tipoBusqueda){
            this.vista=vista;
            this.dao=dao;
            this.tipoBusqueda =tipoBusqueda;
            
            
            modelo= (DefaultTableModel)vista.getTblBuscarPorFiltro().getModel();
            modelo.setColumnIdentifiers(new String []{"ID", "Consecutivo", "Fecha Préstamo", "Fecha Devolución", "Estado", "URL"});
            
            
            vista.configurarPanel(tipoBusqueda);
            
            
            this.vista.getbtnBuscarPorFiltro().addActionListener(this);
            this.vista.getbtnAbrirInforme().addActionListener(this);
            this.vista.getbtnCerrar().addActionListener(this);
        }
        
        @Override
        public void actionPerformed(ActionEvent  e ){
           if(e.getSource()== vista.getbtnBuscarPorFiltro()){
                relizarBusqueda();
           }else if(e.getSource()== vista.getbtnAbrirInforme()){
                abrirInformeSeleccionado();
           }else if(e.getSource()== vista.getbtnCerrar()){
                cerrar();
            }
        }
      
        
        
        private void relizarBusqueda(){
            modelo.setRowCount(0);
            
            try {
                
                if(tipoBusqueda.equalsIgnoreCase("Préstamo")){
                    String valor = vista.getTextBuscar().getText().trim();
                    
                    if(valor.isEmpty()){
                        Mensajes.mostrarInfo("Ingresa el ID del préstamo a buscar.");
                        return;
                    }
                    
                    Prestamos p = new Prestamos();
                    p.setId_prestamo(Integer.parseInt(valor));
                    if(dao.buscar(p)){
                        modelo.addRow(new Object[]{
                            p.getId_prestamo(),
                            p.getConsecutivo(),
                            p.getFecha_prestamo(),
                            p.getFecha_devolucion(),
                            p.getEstado(),
                            p.getUrl()
                    
                        });
                        
                       
                        
                    }else {
                        Mensajes.mostrarInfo("No se encontró préstamo con ese ID.");
                    }
                }else if(tipoBusqueda.equalsIgnoreCase("Nombre")){
                    String nombre= vista.getTextBuscar().getText().trim();
                    List<Prestamos> lista= dao.buscarPorNombre(nombre);
                    
                    if(lista.isEmpty()){
                        Mensajes.mostrarInfo("No se encontraron préstamos para ese lector.");
                    }else{
                        for(Prestamos p : lista){
                            modelo.addRow(new Object[]{
                                p.getId_prestamo(),
                                p.getConsecutivo(),
                                p.getFecha_prestamo(),
                                p.getFecha_devolucion(),
                                p.getEstado(),
                                p.getUrl()
                            });
                        }
                    }
                }else if(tipoBusqueda.equalsIgnoreCase("Fechas")){
                    java.util.Date prestamo = vista.getDatePrestamo().getDate();
                    java.util.Date devolucion=vista.getDateDevolucion().getDate();
                    
                    if(prestamo ==null || devolucion ==null){
                       Mensajes.mostrarError("Debes seleccionar ambas fechas.");
                       return;
                    
                    }
                    
                    List<Prestamos>lista= dao.buscarPorFechas(prestamo, devolucion);
                    if(lista.isEmpty()){
                        Mensajes.mostrarInfo("No se encontraron préstamos en ese rango de fechas.");
                    }else {
                        for (Prestamos p : lista) {
                            modelo.addRow(new Object[]{
                                p.getId_prestamo(),
                                p.getConsecutivo(),
                                p.getFecha_prestamo(),
                                p.getFecha_devolucion(),
                                p.getEstado(),
                                p.getUrl()
                            });
                        }
                    }
                }
            } catch (Exception ex) {
                 Mensajes.mostrarError("Error al realizar la búsqueda: " + ex.getMessage());
                 
            }
        }
        
        private void abrirInformeSeleccionado(){
            int fila = vista.getTblBuscarPorFiltro().getSelectedRow();
            if(fila == -1 ){
                Mensajes.mostrarInfo("Selecciona un préstamo para abrir su informe.");
                return;
            }
            
            String idPrestamo= vista.getTblBuscarPorFiltro().getValueAt(fila, 0).toString();
            String ruta = dao.obtenerRutaInformeBD("prestamo",idPrestamo);
            
            if(ruta !=null && !ruta.isEmpty()){
                dao.abrirInforme(ruta);
            }else{
                Mensajes.mostrarError("No se encontró el archivo del informe.");
            }  
        }
        
        private void cerrar(){
            vista.dispose();
        }
        

}
