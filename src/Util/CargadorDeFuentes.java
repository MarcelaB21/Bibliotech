/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 * Clase utilitaria para cargar y aplicar fuentes personalizadas
 * en las interfaces gráficas del sistema.
 *
 * @author Usuario
 */
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.logging.*;

public class CargadorDeFuentes {
    
    private static final Logger logger = LoggerConfig.getLogger(CargadorDeFuentes.class);
    private static Font robotoRegular;
    private static Font robotoMedium;
    private static Font robotoBold;
    
    public static void loadFonts(){
        try{
            
        robotoRegular = Font.createFont(Font.TRUETYPE_FONT,
                CargadorDeFuentes.class.getResourceAsStream("/Fonts/Roboto-Regular.ttf"));
        robotoMedium = Font.createFont(Font.TRUETYPE_FONT,
                CargadorDeFuentes.class.getResourceAsStream("/Fonts/Roboto-Medium.ttf"));
        robotoBold = Font.createFont(Font.TRUETYPE_FONT,
                CargadorDeFuentes.class.getResourceAsStream("/Fonts/Roboto-Bold.ttf"));

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(robotoRegular);
        ge.registerFont(robotoMedium);
        ge.registerFont(robotoBold);
    } catch (FontFormatException e) {
        logger.log(Level.SEVERE, "Error de formato en la fuente: " + e.getMessage(), e);
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error al leer el archivo de fuente: " + e.getMessage(), e);
    }
    }
    
    
    public static Font getRobotoRegular(float size){
        return robotoRegular.deriveFont(size);
    }
    
    public static Font getRobotoMedium(float size){
        return robotoMedium.deriveFont(size);
    }
    
    public static Font getRobotoBold(float size){
        return robotoBold.deriveFont(size);
    }
}
