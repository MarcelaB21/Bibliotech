/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.base;

import Vista.SeleccionarDatos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

/**
 *Clase base abstracta para controladores de ventanas de selección.
 * 
 * Esta clase encapsula toda la lógica común que comparten diferentes
 * controladores encargados de manejar una ventana de tipo SeleccionarDatos.
 * Su propósito es evitar duplicar código y permitir que cada controlador
 * concreto solo implemente la lógica específica de su caso.
 *
 * @author Usuario
 */
public abstract class ControladorSeleccionBase <T> implements ActionListener {
    
    protected SeleccionarDatos vistaSeleccion;
    protected DefaultTableModel modelo;
    
    public ControladorSeleccionBase(SeleccionarDatos vistaSeleccion){
        this.vistaSeleccion = vistaSeleccion;
        
        
        
        this.vistaSeleccion.getbtnEnviar().addActionListener(this);
        this.vistaSeleccion.getbtnCerrar().addActionListener(this);
        
        this.vistaSeleccion.getTblBuscar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filaSeleccionada();
            }
        });
    }
    
    public void mostrar() {
        configurarTabla();
        cargarDatos();
        vistaSeleccion.setVisible(true);
    }
    
    @Override
    public void actionPerformed( ActionEvent e){
        if(e.getSource() == vistaSeleccion.getbtnEnviar()){
            enviarSeleccion();
        }else if(e.getSource()== vistaSeleccion.getbtnCerrar()){
            cerrar();
        
        }
    
    }
    
    protected void cerrar(){
       vistaSeleccion.dispose();
    }
    
    
    // ====== Métodos abstractos ======
    
    protected abstract void configurarTabla();
    protected abstract void cargarDatos();
    protected abstract void filaSeleccionada();
    protected abstract void enviarSeleccion();
    
}
