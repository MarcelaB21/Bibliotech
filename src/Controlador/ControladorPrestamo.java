/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoLector;
import Dao.DaoLibro;

import Dao.DaoPrestamo;
import Modelo.Lectores;
import Modelo.Libros;

import Modelo.Prestamos;
import Modelo.Usuarios;
import Util.Mensajes;
import Util.PermisosUI;
import Validador.ValidadorPrestamo;
import Vista.FiltrarInformacion;
import Vista.FrmPrestamo;
import Vista.SeleccionarDatos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

/**
 *
 * Controlador del módulo de préstamos. Gestiona las operaciones de listar,
 * guardar y eliminar préstamos, además de permitir la selección de libros y
 * lectores asociados. También se encarga de generar y filtrar informes de
 * préstamos.
 *
 * @author Usuario
 */
public class ControladorPrestamo implements ActionListener {
    private FrmPrestamo vista;
    private DaoPrestamo dao; 
    private Prestamos prestamo;
    private DefaultTableModel modelo;
    private Lectores lector;
    private DaoLector daolectores; 
    private Libros libro;
    private DaoLibro daolibros;
    private List<Libros> librosSeleccionados = new ArrayList<>(); 
    private Usuarios usuarioLogueado;

    
    
    private SeleccionarDatos vistaSeleccionLector;
    private SeleccionarDatos vistaSeleccionLibro;
    
    public ControladorPrestamo(FrmPrestamo vista,DaoPrestamo dao,DaoLector daolectores,DaoLibro daolibros,Usuarios usuarioLogueado){
        this.vista=vista;
        this.dao=dao;
        this.prestamo= new Prestamos(); 
        this.daolectores=daolectores;
        this.lector = new Lectores();
        this.daolibros= daolibros;
        this.libro = new Libros();
        this.usuarioLogueado = usuarioLogueado;
        
        PermisosUI.configurarPermisos(usuarioLogueado, vista.getbtnGuardar(),vista.getbtnCrearInforme(),vista.getbtnEliminar(),vista.getbtnSeleccionarLector(),vista.getbtnSeleccionarLibro());
        
        
        this.vistaSeleccionLector = new SeleccionarDatos();
        this.vistaSeleccionLibro = new SeleccionarDatos();
        
        this.vista.getbtnGuardar().addActionListener(this);
        this.vista.getbtnEliminar().addActionListener(this);
        this.vista.getbtnCrearInforme().addActionListener(this);
        this.vista.getbtnSeleccionarLector().addActionListener(this);
        this.vista.getbtnSeleccionarLibro().addActionListener(this);
        this.vista.getbtnFiltrarInformes().addActionListener(this);
    
        this.vista.getTblPrestamo().addMouseListener(new MouseAdapter(){
          @Override
            public void mouseClicked(MouseEvent e){
             cargarPrestamoSeleccionado();
             
             }
        });
            listarPrestamo();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== vista.getbtnCrearInforme()){
            generarInformePrestamo();
        }else if(e.getSource()==vista.getbtnGuardar()){
            guardarPrestamo();
        }else if(e.getSource()== vista.getbtnEliminar()){
            eliminarPrestamo();
        }else if(e.getSource()== vista.getbtnSeleccionarLector()){
           buscarLector();
        }else if(e.getSource()== vista.getbtnSeleccionarLibro()){
           buscarLibro();      
        }else if(e.getSource()== vista.getbtnFiltrarInformes()){
           filtrar();
        }
    }
    
    private void listarPrestamo(){
        modelo=(DefaultTableModel)vista.getTblPrestamo().getModel();
        modelo.setRowCount(0);
        List<Prestamos> lista =dao.listar();
        
        for (Prestamos prestamo : lista) {
        
            StringBuilder librosTexto = new StringBuilder();
            for (Libros libro : prestamo.getLibrosSeleccionados()) {
                if (librosTexto.length() > 0) librosTexto.append(", ");
                    librosTexto.append(libro.getTitulo());
            }
        
            modelo.addRow(new Object[]{
                prestamo.getId_prestamo(),
                prestamo.getConsecutivo(),
                librosTexto.toString(), 
                prestamo.getFecha_prestamo(),
                prestamo.getFecha_devolucion(),
                prestamo.getEstado(),
                prestamo.getUrl()
                
            });
        }
            
    }
    
    private void cargarPrestamoSeleccionado() {
        int fila = vista.getTblPrestamo().getSelectedRow();

        if (fila == -1) {
            Mensajes.mostrarInfo("Por favor selecciona un préstamo de la tabla.");
            return;
        }

        try {
            
            String idPrestamoStr = vista.getTblPrestamo().getValueAt(fila, 0).toString();
            String consecutivoStr = vista.getTblPrestamo().getValueAt(fila, 1).toString();
            String fechaPrestamoStr = vista.getTblPrestamo().getValueAt(fila, 3).toString();
            String fechaDevolucionStr = vista.getTblPrestamo().getValueAt(fila, 4).toString();
            String estado = vista.getTblPrestamo().getValueAt(fila, 5).toString();
            String url = vista.getTblPrestamo().getValueAt(fila, 6).toString();

            vista.getTextPrestamo().setText(idPrestamoStr);
            vista.getTextConsecutivoLector().setText(consecutivoStr);
            vista.getBoxEstado().setSelectedItem(estado);
            vista.getTextUrl().setText(url);

            
            try {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaPrestamo = formato.parse(fechaPrestamoStr);
                Date fechaDevolucion = formato.parse(fechaDevolucionStr);

                vista.getDateFechaPrestamo().setDate(fechaPrestamo);
                vista.getDateFechaDevolucion().setDate(fechaDevolucion);
            } catch (ParseException ex) {
                Mensajes.mostrarError("Error al convertir las fechas del préstamo.");
            }

            
            Lectores lector = daolectores.buscarPorConsecutivo(consecutivoStr);
            if (lector != null) {
                vista.getTextNombreLector().setText(lector.getNombre());
                vista.getTextDocumentoLector().setText(lector.getIdentificacion());
                vista.getTextCorreoLector().setText(lector.getCorreo());
                vista.getTextContactoLector().setText(lector.getContacto());
                vista.getTextTipoLector().setText(lector.getTipodeusuario());
                vista.getTextRegistroLector().setText(lector.getRegistro());
            }else {
                Mensajes.mostrarInfo("El lector no fue encontrado en la base de datos.");
            }

            
            int idPrestamo = Integer.parseInt(idPrestamoStr);
            librosSeleccionados = dao.obtenerLibrosPorPrestamo(idPrestamo);

            if (librosSeleccionados != null && !librosSeleccionados.isEmpty()) {
                String listaLibros = librosSeleccionados.stream()
                    .map(Libros::getTitulo)
                    .collect(Collectors.joining("\n"));
                vista.getTextLibro().setText(listaLibros);
            } else {
                vista.getTextLibro().setText("Sin libros asignados");
            }
        } catch (Exception e) {
            Mensajes.mostrarError("Ocurrió un error al cargar el préstamo: " + e.getMessage());
            
        }
   }

    private boolean validarCampos(String id_prestamo, Date fechaPrestamo, Date fechaDevolucion, String consecutivo, String estado ){
        
        if(!ValidadorPrestamo.camposCompletos(id_prestamo, fechaPrestamo, fechaDevolucion, consecutivo, estado)){
           Mensajes.mostrarError("El ID del préstamo debe ser un número entero positivo.");
           return false;
        }
        
        if (!ValidadorPrestamo.validadorId_prestamo(id_prestamo)) {
            Mensajes.mostrarError("El ID del préstamo debe ser un número entero positivo.");
           return false;
        }

    
        if (!ValidadorPrestamo.validarConsecutivo(consecutivo)) {
            Mensajes.mostrarError("El consecutivo debe contener solo números.");
            return false;
        }

    
        if (!ValidadorPrestamo.validarEstado(estado)) {
            Mensajes.mostrarError("El estado ingresado no es válido. Solo se permite: Activo, Devuelto o Retrasado.");
            return false;
        }
        return true; 
       
    }
    
    public void agregarLibroSeleccionado(Libros libro) {
        librosSeleccionados.add(libro);
        System.out.println("✅ Libro agregado al préstamo: " + libro.getTitulo());
    }
    
    private void generarInformePrestamo() {
        try {
            
            String id_prestamo = vista.getTextPrestamo().getText().trim();
            String consecutivo = vista.getTextConsecutivoLector().getText().trim();
            Date fechaPrestamo = vista.getDateFechaPrestamo().getDate();
            Date fechaDevolucion = vista.getDateFechaDevolucion().getDate();
            String estado = vista.getBoxEstado().getSelectedItem().toString().trim();

        
            if (!validarCampos( id_prestamo ,fechaPrestamo , fechaDevolucion, consecutivo ,estado ))return; 

            java.sql.Date sqlFechaPrestamo = ValidadorPrestamo.convertirAFechaSQL(fechaPrestamo);
            java.sql.Date sqlFechaDevolucion = ValidadorPrestamo.convertirAFechaSQL(fechaDevolucion);

        
            prestamo = new Prestamos();
            prestamo.setId_prestamo(Integer.parseInt(id_prestamo));
            prestamo.setConsecutivo(Integer.parseInt(consecutivo));
            prestamo.setFecha_prestamo(sqlFechaPrestamo);
            prestamo.setFecha_devolucion(sqlFechaDevolucion);
            prestamo.setEstado(estado);
            prestamo.setLibrosSeleccionados(librosSeleccionados); 
   

       
            Lectores lector = daolectores.buscarPorConsecutivo(consecutivo);
            if (lector == null) {
                Mensajes.mostrarError("No se encontró el lector con consecutivo " + consecutivo);
                return;
            }

        
            String urlInforme = dao.crearInformePrestamo(prestamo, lector);

            if (urlInforme != null) {
                vista.getTextUrl().setText(urlInforme);
                Mensajes.mostrarExito("Informe de préstamo generado correctamente.");
            } else {
                Mensajes.mostrarError("Error al generar el informe del préstamo.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Mensajes.mostrarError("Error inesperado al generar el informe del préstamo.");
        }
    }
    
    private void guardarPrestamo(){
        String id_prestamoStr = vista.getTextPrestamo().getText().trim();
        String consecutivoStr = vista.getTextConsecutivoLector().getText().trim();
        Date fechaPrestamo = vista.getDateFechaPrestamo().getDate();
        Date fechaDevolucion = vista.getDateFechaDevolucion().getDate();
        String estado = vista.getBoxEstado().getSelectedItem().toString().trim();
        String urlArchivo = vista.getTextUrl().getText();
        
        if (!validarCampos( id_prestamoStr ,fechaPrestamo , fechaDevolucion, consecutivoStr  ,estado ))return; 
         
        LocalDate localDateFechaPrestamo = vista.getDateFechaPrestamo().getDate().toInstant()
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalDate();
        LocalDate localDateFechaDevolucion= vista.getDateFechaDevolucion().getDate().toInstant()
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalDate();
         
        int dFechaPrestamo = localDateFechaPrestamo.getDayOfMonth();
        int mFechaPrestamo = localDateFechaPrestamo.getMonthValue();
        int aFechaPrestamo = localDateFechaPrestamo.getYear();
        
        int dFechaDevolucion = localDateFechaDevolucion .getDayOfMonth();
        int mFechaDevolucion = localDateFechaDevolucion .getMonthValue();
        int aFechaDevolucion = localDateFechaDevolucion .getYear();
       
        int id= Integer.parseInt(id_prestamoStr);
        int consecutivo= Integer.parseInt(consecutivoStr);
        boolean estadoPrestamo= ValidadorPrestamo.validarEstado(estado);
        
        
        prestamo = new Prestamos();
        prestamo.setId_prestamo(id);
        prestamo.setConsecutivo(consecutivo);
        prestamo.setFecha_prestamo(java.sql.Date.valueOf(localDateFechaPrestamo)); 
        prestamo.setFecha_devolucion(java.sql.Date.valueOf(localDateFechaDevolucion));
        prestamo.setEstado(estado);
        prestamo.setLibrosSeleccionados(librosSeleccionados); 
        prestamo.setUrl(urlArchivo);
        
        if(dao.insertar(prestamo)){
            Mensajes.mostrarExito("Préstamo guardado correctamente.");
            listarPrestamo();
            limpiarFormulario();
        } else {
            Mensajes.mostrarError("No se pudo guardar el préstamo.");
        }

    
        
    }

    private void eliminarPrestamo(){
        String textoId= vista.getTextPrestamo().getText().trim();
        
        if(!textoId.isEmpty()){
            try{
                int idPrestamo= Integer.parseInt(textoId);
                
                boolean confirmacion = Mensajes.confirmar("¿Estás seguro de eliminar el prestamo?");
                if(confirmacion){
                   prestamo = new Prestamos();
                   prestamo.setId_prestamo(idPrestamo);
                   
                   boolean eliminado = dao.eliminar(prestamo);
                   
                    if(eliminado){
                      listarPrestamo();
                      limpiarFormulario();
                      Mensajes.mostrarInfo("Se eliminó exitosamente.");
                      
                        if (dao.isArchivoNoEliminado()) {
                            Mensajes.mostrarInfo("El informe del préstamo aún existe en tu PC. Puedes conservarlo o eliminarlo manualmente.");
                        }
                    
                    }else{
                        Mensajes.mostrarError("Ocurrió un error al intentar eliminar el prestamo .");
                    }
                   
                }
            }catch (NumberFormatException ex) {
                Mensajes.mostrarError("El ID del prestamo no es válido.");
            }
        }else{
            Mensajes.mostrarInfo("Seleccione un prestamo para eliminar.");
        }
    
    }
    
    private void buscarLector() {
        
        if (vistaSeleccionLector.isVisible()) {
            vistaSeleccionLector.toFront();
            return;
        }

        
        ControladorSeleccionLector controladorLector = new ControladorSeleccionLector(vistaSeleccionLector, daolectores, vista);
        controladorLector.mostrar(); 
    }

    private void buscarLibro() {
        
            if (vistaSeleccionLibro.isVisible()) {
            vistaSeleccionLibro.toFront();
            return;
            }
      
            ControladorSeleccionLibro controladorLibro = new ControladorSeleccionLibro(vistaSeleccionLibro, daolibros, vista,this );
            controladorLibro.mostrar(); 
    }
    
    private void filtrar(){
        String tipoBusqueda= vista.getComboTipoBusqueda().getSelectedItem().toString();
        
        if(tipoBusqueda == null || tipoBusqueda.isEmpty() || tipoBusqueda.equals("Seleccionar")){
            Mensajes.mostrarInfo("No seleccionaste ningún parámetro de búsqueda.");
            return;
        }
        
        FiltrarInformacion ventanaFiltrar = new FiltrarInformacion();
        ControladorFiltrarInformacion controlador = new  ControladorFiltrarInformacion(ventanaFiltrar, dao, tipoBusqueda);
        ventanaFiltrar.setVisible(true);
    
    }
    
    private void limpiarFormulario(){
        vista.getTextPrestamo().setText("");
        vista.getDateFechaDevolucion().setDate(null);
        vista.getDateFechaPrestamo().setDate(null);
        vista.getTextUrl().setText("");
        vista.getBoxEstado().setSelectedIndex(-1);
        vista.getTextConsecutivoLector().setText("");
        vista.getTextNombreLector().setText("");
        vista.getTextDocumentoLector().setText("");
        vista.getTextContactoLector().setText("");
        vista.getTextCorreoLector().setText("");
        vista.getTextTipoLector().setText("");
        vista.getTextRegistroLector().setText("");
        vista.getTextLibro().setText("");
                
        
    }
    
   
}
