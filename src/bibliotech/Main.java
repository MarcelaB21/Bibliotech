/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bibliotech;

import Controlador.ControladorLogin;
import Dao.DaoUsuario;
import Vista.Login;

/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Login login= new Login();
        DaoUsuario dao= new DaoUsuario();
        ControladorLogin conLogin = new ControladorLogin(login, dao);
        login.setVisible(true);
    }
    
}
