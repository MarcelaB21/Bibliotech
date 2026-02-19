/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vault;

/**
 *La clase Utilidades se usa para cifrar y descifrar datos sensibles usando AES y Base64.
 * Se utiliza para proteger la información al conectarse con la base de datos.
 * @author Usuario
 */
import Util.LoggerConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class Utilidades {
    private static final Logger logger = LoggerConfig.getLogger(Utilidades.class);
    
    private static final String LLAVE_AES = cargarValorEncapsulado();
    
    private static String cargarValorEncapsulado(){
        try {
            Properties props = new Properties();
            File file = new File("resources/dsr.properties");
                if(!file.exists()){
                    logger.log(Level.SEVERE,"Archivo de seguridad no encontrado.");
                    return null;
                }
             
            props.load (new  FileInputStream(file));
            String valor = props.getProperty("u9y");
                
                if(valor ==null){
                   logger.log(Level.WARNING,"La propiedad 'u9y' no fue encontrada en el archivo.");
                   return null;
                }
            return valor;    
                
                     
        }catch(IOException e ){
            logger.log(Level.SEVERE, "Error al cargar el valor  desde el archivo.", e);
            return null;
        }
    }
    
    public static String cifrar(String texto) {
        try {
            
            SecretKeySpec clave = new SecretKeySpec(LLAVE_AES.getBytes(), "AES");

            Cipher cifrado = Cipher.getInstance("AES");
            cifrado.init(Cipher.ENCRYPT_MODE, clave);
            byte[] bytesCifrados = cifrado.doFinal(texto.getBytes());

            return Base64.getEncoder().encodeToString(bytesCifrados);
        } catch (Exception e) {
            
            logger.log(Level.SEVERE,"Error al cifrar"  ,e );
            return null;
        }
    }
    
    public static String descifrar(String textoCifrado){
        try{
            SecretKeySpec clave = new SecretKeySpec(LLAVE_AES.getBytes(),"AES");
            
            Cipher descifrado = Cipher.getInstance("AES");
            
            descifrado.init(Cipher.DECRYPT_MODE,clave);
            
            byte[] bytesDescifrados = descifrado.doFinal(Base64.getDecoder().decode(textoCifrado));
            
             return new String(bytesDescifrados);
        }catch (Exception e) {
            logger.log(Level.SEVERE,"Error al descifrar:   "  ,e );
            return null;
        }
    }

  
}
