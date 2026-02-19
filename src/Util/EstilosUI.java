/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import RSMaterialComponent.RSButtonMaterialIconDos;
import RSMaterialComponent.RSButtonMaterialUno;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;

/**
 *Clase utilitaria que centraliza estilos visuales para la interfaz:
 * fuentes, bordes y estilos generales de tablas y paneles.
 * Permite mantener la apariencia uniforme en todo el sistema.
 * @author Usuario
 */
public class EstilosUI {
    
    public static void aplicarFuentes(Component[] componentes){
        CargadorDeFuentes.loadFonts();
        
        for(Component c: componentes){
            if(c instanceof JLabel){
                ((JLabel) c ).setFont(CargadorDeFuentes.getRobotoMedium(12f));
            }else if(c instanceof  RSButtonMaterialIconDos){
                (( RSButtonMaterialIconDos ) c).setFont(CargadorDeFuentes.getRobotoBold(14f));
            }else if(c instanceof  RSButtonMaterialUno ){
                (( RSButtonMaterialUno  ) c).setFont(CargadorDeFuentes.getRobotoBold(12f));
            }else if(c instanceof JTextField){
                ((JTextField)c).setFont(CargadorDeFuentes.getRobotoRegular(12f));
            }else if(c instanceof JTable){
                ((JTable)c).setFont(CargadorDeFuentes.getRobotoRegular(12f));
            }else if(c instanceof JTextArea){
                ((JTextArea)c).setFont(CargadorDeFuentes.getRobotoRegular(12f));
            }else if(c instanceof JPasswordField ){
                ((JPasswordField)c).setFont(CargadorDeFuentes.getRobotoRegular(12f));
            }else if(c instanceof JDateChooser){
                ((JDateChooser) c).setFont(CargadorDeFuentes.getRobotoRegular(12f));
            }else if(c instanceof JComboBox){
                ((JComboBox) c).setFont(CargadorDeFuentes.getRobotoRegular(12f));
            }
        }
    }
    
    public static void aplicarEstiloTabla(JTable tabla){
        tabla.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());
        
        JTableHeader header = tabla.getTableHeader();
        header.setFont(CargadorDeFuentes.getRobotoBold(13f));
        header.setBackground(new Color(220,230,240));
        header.setForeground(Color.BLACK);
        
    }
    
    static class AlternatingRowColorRenderer extends DefaultTableCellRenderer{
        private final Color evenColor = new Color(230,240,250);
        private final Color oddColor = Color.WHITE;
        
        @Override
        public Component getTableCellRendererComponent(JTable table ,Object value , boolean isSelected , boolean hasFocus ,int row , int column){
            Component cell = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
            if(!isSelected){
                cell.setBackground(row % 2 == 0 ? evenColor :oddColor);
            }
            return cell;
        }
          
          
    }
    
    public static void desactivarEdicion(Component[] componentes) {
    for (Component c : componentes) {

        if (c instanceof JTextComponent) {  // <-- ESTA ES LA CLAVE
            JTextComponent tc = (JTextComponent) c;
            tc.setEditable(false);
            tc.setBackground(new Color(240, 240, 240));
        }
    }
}



    public static void aplicarBordePanel(JPanel panel) {
        panel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
    }
    
    // -------- Métodos específicos --------
    public static void aplicarBorderPanelHome(JPanel panel, JLabel lblBienvenida, JLabel lblMedio, JLabel lblPeque) {
        
        panel.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, new Color(180, 180, 255)));

        lblBienvenida.setForeground(new Color(50, 50, 90));
        lblBienvenida.setHorizontalAlignment(JLabel.CENTER);

        
        lblMedio.setForeground(new Color(70, 70, 100));
        lblMedio.setHorizontalAlignment(JLabel.CENTER);

        
        lblPeque.setForeground(new Color(90, 90, 120));
        lblPeque.setHorizontalAlignment(JLabel.CENTER);

        
        panel.setOpaque(false);
    }
    
}
