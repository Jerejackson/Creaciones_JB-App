/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacionesjb;

import Conexion.conexion;
import controlador.controlador;
import controlador.controladorDeudores;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.consultas;
import vista.ventana;

/**
 *
 * @author Jeremi_Sanchez
 */
public class CreacionesJB {

    CreacionesJB(){
        String url = "jdbc:mysql://localhost:3306/creacionesjb";
        String usuario = "root";
        String password = "";
        conexion.getConexion(url, usuario, password);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CreacionesJB creacionesJB = new CreacionesJB();
        // TODO code application logic here
        ventana v = new ventana();
        consultas con = new consultas();
        controlador c = new controlador(v,con);
        controladorDeudores cr = new controladorDeudores(v);
        
        v.getCcre().registrarEscuchas(c);
        v.getLogin().registrarEscuchas(c);
        v.getM().registrarEscuchas(c);
        v.getRcli().registrarEscuchas(c);
        v.getRcre().registrarEscuchas(c);
        v.getRemp().registrarEscuchas(c);
        v.getrIntC().registrarEscuchas(c);
        v.getCdeu().registrarEscuchas(c,cr);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CreacionesJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
