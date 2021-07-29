/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Jeremi_Sanchez
 */
public class registrarCredito extends JPanel{
    
    JTextField txtCodCredito,
            txtValorProducto, txtNomProducto, txtIngresoCc,
            txtCc, txtNombre, txtDireccionCasa, txtDireccionTrabajo, txtTelCasa, txtEmpresa, txtCargo, txtTelTrabajo;
    
    JComboBox jcbDiferido;
    DefaultComboBoxModel modeloCbb;
    
    ImageIcon background, icoLimpiar, icoIngresar, icoCerrar, icoBuscar;
    
    JButton btnGuardar, btnLimpiar, btnCerrar, btnBuscar;
    
    int codCredito = 0;
    
    public registrarCredito(){
        inicializar();
        this.setLayout(new BorderLayout());
        this.add(pCedula(), BorderLayout.NORTH);
        this.add(formulario(), BorderLayout.CENTER);
        this.add(pBotones(), BorderLayout.SOUTH);
        this.setBackground(Color.WHITE);
    }
    
    private JPanel pCedula(){
        JPanel p = new JPanel();
        p.add(new JLabel("No. de Credito: ")); p.add(txtCodCredito); p.add(btnBuscar);
        p.setOpaque(false);
        return p;
    }
    
    private JPanel formulario(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,1,10,5));
        p.add(pCredito());
        p.add(pCliente());
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pCredito(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5,2));
        p.add(new JLabel()); p.add(new JLabel());
        p.add(new JLabel("Nombre del Producto: ")); p.add(txtNomProducto);
        p.add(new JLabel("Valor del Producto: ")); p.add(txtValorProducto);
        p.add(new JLabel("Número de Cuotas: ")); p.add(jcbDiferido);
        p.add(new JLabel("C.C. Cliente : ")); p.add(txtIngresoCc);
        p.setBorder(new TitledBorder(null, "Datos Credito"));
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pCliente(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(8,2));
        p.add(new JLabel("Cédula: ")); p.add(txtCc);
        p.add(new JLabel("Nombre: ")); p.add(txtNombre);
        p.add(new JLabel("Dirección de Residencia: ")); p.add(txtDireccionCasa);
        p.add(new JLabel("Teléfono de Residencia: ")); p.add(txtTelCasa);
        
        p.add(new JLabel("Empresa Laborando: ")); p.add(txtEmpresa);
        p.add(new JLabel("Cargo Laboral: ")); p.add(txtCargo);
        p.add(new JLabel("Dirección de Trabajo: ")); p.add(txtDireccionTrabajo);
        p.add(new JLabel("Teléfono de Trabajo: ")); p.add(txtTelTrabajo);
        
        p.setBorder(new TitledBorder(null, "Datos Cliente"));
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
        
        txtCc = new JTextField();
        txtNombre = new JTextField();
        txtDireccionCasa = new JTextField();
        txtDireccionTrabajo = new JTextField();
        txtTelCasa = new JTextField();
        txtEmpresa = new JTextField();
        txtCargo = new JTextField();
        txtTelTrabajo = new JTextField();
        
        txtCc.setEditable(false);
        txtNombre.setEditable(false);
        txtDireccionCasa.setEditable(false);
        txtDireccionTrabajo.setEditable(false);
        txtTelCasa.setEditable(false);
        txtCargo.setEditable(false);
        txtEmpresa.setEditable(false);
        txtTelTrabajo.setEditable(false); 
        
        txtCodCredito = new JTextField(20);
        txtValorProducto = new JTextField();
        txtNomProducto = new JTextField();
        txtIngresoCc = new JTextField();
        
        modeloCbb = new DefaultComboBoxModel();
        jcbDiferido = new JComboBox(modeloCbb);
        jcbDiferido.setBackground(Color.white);
        
        icoLimpiar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/login/limpiar.png");
        icoIngresar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/disquete.png");
        icoCerrar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/login/atras.png");
        icoBuscar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/buscar.png");
        
        btnGuardar = new JButton(cambiarTamañoIcono(icoIngresar,100,100));
        btnLimpiar = new JButton(cambiarTamañoIcono(icoLimpiar,100,100));
        btnCerrar = new JButton(cambiarTamañoIcono(icoCerrar,100,100));
        btnBuscar = new JButton(cambiarTamañoIcono(icoBuscar,30,30));
        
        btnGuardar.setBackground(Color.WHITE);
        btnLimpiar.setBackground(Color.WHITE);
        btnCerrar.setBackground(Color.WHITE);
        btnBuscar.setBackground(Color.WHITE);
    }
    
    public void limpiar(){
        txtCodCredito.setText("");
        txtCodCredito.setEditable(true);
        txtValorProducto.setText("");
        txtNomProducto.setText("");
        txtIngresoCc.setText("");
        txtCc.setText("");
        txtNombre.setText("");
        txtDireccionCasa.setText("");
        txtDireccionTrabajo.setText("");
        txtTelCasa.setText("");
        txtEmpresa.setText("");
        txtCargo.setText("");
        txtTelTrabajo.setText("");
        
        jcbDiferido.setSelectedIndex(0);
    }

    public JComboBox getJcbDiferido() {
        return jcbDiferido;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTextField getTxtCodCredito() {
        return txtCodCredito;
    }

    public void setTxtCodCredito(JTextField txtCodCredito) {
        this.txtCodCredito = txtCodCredito;
    }
    
    public JTextField getTxtValorProducto() {
        return txtValorProducto;
    }

    public void setTxtValorProducto(JTextField txtValorProducto) {
        this.txtValorProducto = txtValorProducto;
    }

    public JTextField getTxtNomProducto() {
        return txtNomProducto;
    }

    public void setTxtNomProducto(JTextField txtNomProducto) {
        this.txtNomProducto = txtNomProducto;
    }

    public JTextField getTxtCc() {
        return txtCc;
    }

    public void setTxtCc(JTextField txtCc) {
        this.txtCc = txtCc;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtDireccionCasa() {
        return txtDireccionCasa;
    }

    public void setTxtDireccionCasa(JTextField txtDireccionCasa) {
        this.txtDireccionCasa = txtDireccionCasa;
    }

    public JTextField getTxtDireccionTrabajo() {
        return txtDireccionTrabajo;
    }

    public void setTxtDireccionTrabajo(JTextField txtDireccionTrabajo) {
        this.txtDireccionTrabajo = txtDireccionTrabajo;
    }

    public JTextField getTxtTelCasa() {
        return txtTelCasa;
    }

    public void setTxtTelCasa(JTextField txtTelCasa) {
        this.txtTelCasa = txtTelCasa;
    }

    public JTextField getTxtEmpresa() {
        return txtEmpresa;
    }

    public void setTxtEmpresa(JTextField txtEmpresa) {
        this.txtEmpresa = txtEmpresa;
    }

    public JTextField getTxtCargo() {
        return txtCargo;
    }

    public void setTxtCargo(JTextField txtCargo) {
        this.txtCargo = txtCargo;
    }

    public JTextField getTxtTelTrabajo() {
        return txtTelTrabajo;
    }

    public void setTxtTelTrabajo(JTextField txtTelTrabajo) {
        this.txtTelTrabajo = txtTelTrabajo;
    }

    public JTextField getTxtIngresoCc() {
        return txtIngresoCc;
    }

    public void setTxtIngresoCc(JTextField txtIngresoCc) {
        this.txtIngresoCc = txtIngresoCc;
    }
    
    public DefaultComboBoxModel getModeloCbb() {
        return modeloCbb;
    }

    public void setModeloCbb(DefaultComboBoxModel modeloCbb) {
        this.modeloCbb = modeloCbb;
    }

    public int getCodCredito() {
        return codCredito;
    }

    public void setCodCredito(int codCredito) {
        this.codCredito = codCredito;
    }
    
    public void registrarEscuchas(controlador c){
        btnGuardar.addActionListener(c);
        btnLimpiar.addActionListener(c);
        btnCerrar.addActionListener(c);
        btnBuscar.addActionListener(c);
    }
}
