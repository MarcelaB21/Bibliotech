/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * Esta clase configura el logger para todo el proyecto.
 * Su propósito es centralizar la configuración del sistema de logs,
 * de modo que cualquier clase que necesite registrar mensajes de log,
 * simplemente lo obtenga desde aquí sin repetir la configuración.
 * @author Usuario
 */
public class LoggerConfig {
    
    static {
            setupLogger();
    }
    
     
    
    private static void setupLogger(){
     try{
         
            String logDirectory = "logs";

            
            File dir = new File(logDirectory);
            if (!dir.exists()) {
                dir.mkdir(); 
            }

            
            FileHandler fileHandler = new FileHandler(logDirectory + "/app_log.log", true);

            
            fileHandler.setFormatter(new SimpleFormatter());

            
            Logger rootLogger = Logger.getLogger("");

            
            if (rootLogger.getHandlers().length == 0) {
                rootLogger.addHandler(fileHandler);
            }
     
     } catch (IOException e) {
            
                Logger.getAnonymousLogger().log(Level.SEVERE, "Error al configurar el logger", e);
        }
    
    }
    
    public static Logger getLogger(Class<?> clazz) {
        return Logger.getLogger(clazz.getName());
    }
}
