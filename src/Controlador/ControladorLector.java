 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoLector;
import Modelo.Lectores;
import Modelo.Usuarios;
import Util.Mensajes;
import Util.PermisosUI;
import Validador.ValidadorLector;
import Vista.FrmLector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * Controlador para la gestión de lectores. Funciona de forma similar al
 * controlador de categorías, manejando los eventos del usuario y las 
 * operaciones CRUD correspondientes, adaptadas al módulo de lectores.
 *
 * @author Usuario
 */
public class ControladorLector implements ActionListener{
    private FrmLector vista;
    private DaoLector dao;
    private Lectores lector;
    private DefaultTableModel modelo;
    private Usuarios usuarioLogueado; 
    
    
    public ControladorLector(FrmLector vista,DaoLector dao,Usuarios usuarioLogueado){
        this.vista=vista;
        this.dao=dao;
        this.lector= new Lectores();
        this.usuarioLogueado = usuarioLogueado;
        
        PermisosUI.configurarPermisos(usuarioLogueado, vista.getbtnGuardar(), vista.getbtnEditar(), vista.getbtnEliminar());
        
        this.vista.getbtnGuardar().addActionListener(this);
        this.vista.getbtnEditar().addActionListener(this);
        this.vista.getbtnEliminar().addActionListener(this);
        this.vista.getbtnBuscar().addActionListener(this);
        
        this.vista.getTblLector().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
               cargarLectorSeleccionada();
            }
        });
        listarLector();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vista.getbtnGuardar()){
            guardarLector();
        }else if(e.getSource()== vista.getbtnEditar()){
            editarLector();
        }else if(e.getSource()== vista.getbtnEliminar()){
            eliminarLector();
        }else if(e.getSource()== vista.getbtnBuscar()) {
            buscarLector();
        }
    }
    
    private boolean validarCampos(String nombre, String identificacion, String correo , String contacto, String tipoUsuario, String registro){
    
        if(!ValidadorLector.camposCompletos(nombre, identificacion, correo, contacto, tipoUsuario, registro)){
            Mensajes.mostrarError("Todos los campos deben ser llenados.");
            return false;
        }
        
        if(!ValidadorLector.ValidarNombre(nombre)){
            Mensajes.mostrarError("El nombre debe tener entre 3 y 100 caracteres ");
            return false;
        }
        
        if(!ValidadorLector.ValidarIdentificacion(identificacion)){
            Mensajes.mostrarError("El número de identificación debe tener entre 7 y 10 dígitos.");
            return false;
        }
        
        if(!ValidadorLector.ValidarCorreo(correo)){
            Mensajes.mostrarError("Correo no válido. Debe tener formato correcto y terminar en .com  etc.");
            return false;
        }
        
        if(!ValidadorLector.ValidarContacto(contacto)){
            Mensajes.mostrarError("Número de celular no válido. Solo números y entre 7 y 10 dígitos.");
            return false;
        }
        
        if(!ValidadorLector.ValidarTipoUsuario(tipoUsuario)){
            Mensajes.mostrarError("Ingrese un tipo de usuario válido: solo letras y entre 3 y 50 caracteres.");
            return false;
        }
        
        if(!ValidadorLector.ValidarRegistro(registro)){
            Mensajes.mostrarError("Ingrese un valor de registro que tenga exactamente 12 caracteres.");
            return false;
        }
        
        return true;
    
    }
    
    private void listarLector(){
        modelo= (DefaultTableModel)vista.getTblLector().getModel();
        modelo.setRowCount(0);
        
        List<Lectores> lista= dao.Listar();
        for(Lectores lector : lista){
            modelo.addRow(new Object[]{
            lector.getConsecutivo(),
            lector.getNombre(),
            lector.getIdentificacion(),
            lector.getCorreo(),
            lector.getContacto(),
            lector.getTipodeusuario(),
            lector.getRegistro()
            });
            
        }
    }
    
    private void cargarLectorSeleccionada(){
        int fila = vista.getTblLector().getSelectedRow();
        
        if(fila != -1){
            vista.getTextConsecutivo().setText(vista.getTblLector().getValueAt(fila, 0).toString());
            vista.getTextNombre().setText(vista.getTblLector().getValueAt(fila, 1).toString());
            vista.getTextDocumento().setText(vista.getTblLector().getValueAt(fila, 2).toString());
            vista.getTextCorreo().setText(vista.getTblLector().getValueAt(fila, 3).toString());
            vista.getTextContacto().setText(vista.getTblLector().getValueAt(fila, 4).toString());
            vista.getTextTipo().setText(vista.getTblLector().getValueAt(fila, 5).toString());
            vista.getTextRegistro().setText(vista.getTblLector().getValueAt(fila, 6).toString());
        }
    }
    
    private void guardarLector(){
        String nombre = vista.getTextNombre().getText().trim();
        String identificacion = vista.getTextDocumento().getText().trim();
        String corre = vista.getTextCorreo().getText().trim();
        String contacto= vista.getTextContacto().getText().trim();
        String tiposUsuario= vista.getTextTipo().getText().trim();
        String registro = vista.getTextRegistro().getText().trim();
        
        if(!validarCampos(nombre, identificacion, corre, contacto, tiposUsuario, registro))return;
        
        lector = new Lectores();
        lector.setNombre(nombre);
        lector.setIdentificacion(identificacion);
        lector.setCorreo(corre);
        lector.setContacto(contacto);
        lector.setTipodeusuario(tiposUsuario);
        lector.setRegistro(registro);
        
        if(dao.verificarCedula(identificacion)){
           Mensajes.mostrarAdvertencia("Ya existe un usuario con esa cédula.");
           return;
        }
        if(dao.insertar(lector)){
            Mensajes.mostrarExito("Se registro  exitosamente.");
            listarLector();
            limpiarFormulario();
            
        }else{
            Mensajes.mostrarError("No se pudo registrar al usuario . Verifique que los datos no se dupliquen  o que no exceda el límite.");
        }
    }
    
    private void editarLector(){
        int fila= vista.getTblLector().getSelectedRow();
        
        if(fila == -1 || vista.getTextConsecutivo().getText().trim().isEmpty()){
            Mensajes.mostrarInfo("Seleccione una usuario  de la tabla para editar.");
            return;
        }
        
        String nombre= vista.getTextNombre().getText().trim();
        String identificacion = vista.getTextDocumento().getText().trim();
        String correo =vista.getTextCorreo().getText().trim();
        String contacto = vista.getTextContacto().getText().trim();
        String tiposUsuario= vista.getTextTipo().getText().trim();
        String registro = vista.getTextRegistro().getText().trim();
        
        if(!validarCampos(nombre, identificacion, correo, contacto, tiposUsuario, registro)) return;
        
        try {
            int idConsecutivo = Integer.parseInt(vista.getTextConsecutivo().getText().trim());
            lector=new Lectores();
            lector.setConsecutivo(idConsecutivo);
            lector.setNombre(nombre);
            lector.setIdentificacion(identificacion);
            lector.setCorreo(correo);
            lector.setContacto(contacto);
            lector.setTipodeusuario(tiposUsuario);
            lector.setRegistro(registro);
            
            if(dao.tieneDependencias(idConsecutivo)){
                 Mensajes.mostrarAdvertencia("No se puede editar este usuario porque ya está asignado a un prestamo.");
            }else{
                if(dao.editar(lector)){
                    Mensajes.mostrarExito("Usuario actualizado correctamente.");
                }else{
                    Mensajes.mostrarError("No se pudo actualizar al usuario.");
                }
            }
            listarLector();
            limpiarFormulario();
        } catch (NumberFormatException ex) {
            Mensajes.mostrarError("El ID del usuario no es válido.");
        }
    }
    
    private void eliminarLector(){
        String textoId = vista.getTextConsecutivo().getText().trim();
        
        if(!textoId.isEmpty()){
            try{
                int idConsecutivo = Integer.parseInt(textoId);
                if(dao.tieneDependencias(idConsecutivo)){
                    Mensajes.mostrarAdvertencia("No se puede eliminar este usuario porque ya está asignado a un prestamo");
                    return;
                }
                boolean confirmacion = Mensajes.confirmar("¿Estás seguro de eliminar el usuario?");
                    if(confirmacion){
                        lector= new Lectores();
                        lector.setConsecutivo(idConsecutivo);
                            
                        boolean eliminado = dao.eliminar(lector);
                            if(eliminado){
                                listarLector();
                                limpiarFormulario();
                                Mensajes.mostrarExito("Se eliminó exitosamente.");
                            }else{
                                Mensajes.mostrarError("Ocurrió un error al intentar eliminar el usuario.");
                            }
                    }
            }catch(NumberFormatException ex){
                Mensajes.mostrarError("El ID del usuario  no es válido.");
            }
        }else{
            Mensajes.mostrarInfo("Seleccione un usuario para eliminar.");
        }
    }
    
    private void buscarLector(){
        String idTexto= vista.getTextConsecutivo().getText().trim();
        
        if(!idTexto.isEmpty()){
            try{
                int idConsecutivo = Integer.parseInt(idTexto);
                lector.setConsecutivo(idConsecutivo);
                
                if(dao.buscar(lector)){
                    vista.getTextConsecutivo().setText(String.valueOf(lector.getConsecutivo()));
                    vista.getTextNombre().setText(lector.getNombre());
                    vista.getTextDocumento().setText(lector.getIdentificacion());
                    vista.getTextCorreo().setText(lector.getCorreo());
                    vista.getTextContacto().setText(lector.getContacto());
                    vista.getTextTipo().setText(lector.getTipodeusuario());
                    vista.getTextRegistro().setText(lector.getRegistro());
                    
                }else{
                    Mensajes.mostrarError("El usuario no se encontró.");
                }
            }catch(NumberFormatException e){
                Mensajes.mostrarError("El ID del usuario debe ser un número válido.");
            }
        }else{
            Mensajes.mostrarInfo("Debe ingresar un ID del usuario para realizar la búsqueda.");
        }
    
    }
    
    private void limpiarFormulario(){
       vista.getTextConsecutivo().setText(" ");
       vista.getTextNombre().setText(" ");
       vista.getTextDocumento().setText(" ");
       vista.getTextCorreo().setText(" ");
       vista.getTextContacto().setText(" ");
       vista.getTextTipo().setText(" ");
       vista.getTextRegistro().setText(" ");
    }
    
    
}
