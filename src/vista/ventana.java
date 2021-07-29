/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jeremi_Sanchez
 */
public class ventana extends JFrame{
    login login;
    registrarCliente rcli;
    registrarCredito rcre;
    registrarEmpleado remp;
    consultarCredito ccre;
    menu m;
    registroInteresNCuotas rIntC;
    consultarDeudores cdeu;
    
    JPanel pPrincipal;
    
    public ventana(){
        login = new login();
        rcli = new registrarCliente();
        rcre = new registrarCredito();
        remp = new registrarEmpleado();
        ccre = new consultarCredito();
        m = new menu();
        rIntC = new registroInteresNCuotas();
        cdeu = new consultarDeudores();
        pPrincipal = new JPanel();
        pPrincipal.setLayout(new GridLayout(1,1));
        pPrincipal.add(login);
        getContentPane().add(pPrincipal);
        setVisible(true);
        setTitle("Otto Credito");
        setSize(1100,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Image image = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"/img/logo.jpg");
        setIconImage(image);
    }
    
    public login getLogin() {
        return login;
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public registrarCliente getRcli() {
        return rcli;
    }

    public registrarCredito getRcre() {
        return rcre;
    }

    public consultarCredito getCcre() {
        return ccre;
    }

    public registrarEmpleado getRemp() {
        return remp;
    }

    public menu getM() {
        return m;
    }

    public registroInteresNCuotas getrIntC() {
        return rIntC;
    }

    public consultarDeudores getCdeu() {
        return cdeu;
    }
}