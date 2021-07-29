/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.controlador;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jeremi_Sanchez
 */
public class registrarEmpleado extends JPanel{
    
    ImageIcon icoCerrar, icoLimpiar, icoGuardar, icoBuscar;
    JButton btnCerrar, btnLimpiar, btnGuardar, btnBuscar;
    JTextField txtId, txtNombre, txtPass, txtConfirmPass;
    int identificador = 0;
    
    registrarEmpleado(){
       inicializar();
       this.setLayout(new GridLayout(4,1));
       this.add(relleno());
       this.add(pCentro());
       this.add(relleno());
       this.add(pBotones());
       this.setBackground(Color.WHITE);
    }
    
    private JPanel relleno(){
        JPanel p = new JPanel();
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pCentro(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4,2,0,10));
        p.add(new JLabel("N. de Identificación: "));    p.add(pBuscar());
        p.add(new JLabel("Nombre: "));  p.add(txtNombre);
        p.add(new JLabel("Contraseña: "));  p.add(txtPass);
        p.add(new JLabel("Confirmar Contraseña: "));    p.add(txtConfirmPass);
        p.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pBuscar(){
        JPanel p = new JPanel();
        p.add(txtId);    p.add(btnBuscar);
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pBotones(){
        JPanel p = new JPanel();
        p.add(btnCerrar);
        p.add(btnLimpiar);
        p.add(btnGuardar);
        p.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        p.setOpaque(false);
        return p;
    }
    
    private ImageIcon cambiarTamañoIcono(ImageIcon icon, int ancho, int alto){
        Image img = icon.getImage();
        Image iconoGrande = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(iconoGrande);
    }
    
    private void inicializar(){
        icoCerrar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/login/atras.png");
        icoLimpiar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/login/limpiar.png");
        icoGuardar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/disquete.png");
        icoBuscar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/buscar.png");
        
        btnCerrar = new JButton(cambiarTamañoIcono(icoCerrar,100,100));
        btnLimpiar = new JButton(cambiarTamañoIcono(icoLimpiar,100,100));
        btnGuardar = new JButton(cambiarTamañoIcono(icoGuardar,100,100));
        btnBuscar = new JButton(cambiarTamañoIcono(icoBuscar,15,15));
        
        btnCerrar.setBackground(Color.WHITE);
        btnLimpiar.setBackground(Color.WHITE);
        btnGuardar.setBackground(Color.WHITE);
        btnBuscar.setBackground(Color.WHITE);
        
        txtId = new JTextField(34);
        txtNombre = new JTextField(20);
        txtPass = new JTextField(20);
        txtConfirmPass = new JTextField(20);
    }
    
    public void registrarEscuchas(controlador c){
        btnCerrar.addActionListener(c);
        btnLimpiar.addActionListener(c);
        btnGuardar.addActionListener(c);
        btnBuscar.addActionListener(c);
    }
    
    public void limpiar(){
        txtId.setText("");
        txtNombre.setText("");
        txtPass.setText("");
        txtConfirmPass.setText("");
        identificador = 0;
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }
    
    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtPass() {
        return txtPass;
    }

    public void setTxtPass(JTextField txtPass) {
        this.txtPass = txtPass;
    }

    public JTextField getTxtConfirmPass() {
        return txtConfirmPass;
    }

    public void setTxtConfirmPass(JTextField txtConfirmPass) {
        this.txtConfirmPass = txtConfirmPass;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
}