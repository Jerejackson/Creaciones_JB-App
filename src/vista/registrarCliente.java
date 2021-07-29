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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Jeremi_Sanchez
 */
public class registrarCliente extends JPanel{
    
    JTextField txtCc, txtNombre, txtDireccionCasa, txtTelCasa, txtEmpresa, txtCargo, txtDireccionTrabajo, txtTelTrabajo,
            txtCcRefPersonal, txtNomRefPersonal, txtTelCasaRefPersonal, txtDireccionCasaRefPersonal, txtDireccionTrabajoRefPersonal, txtCargoRefPersonal, txtEmpresaRefPersonal, txtTelTrabajoRefPersonal,
            txtCcRefFamiliar, txtNomRefFamiliar, txtTelCasaRefFamiliar, txtDireccionCasaRefFamiliar, txtDireccionTrabajoRefFamiliar, txtCargoRefFamiliar, txtEmpresaRefFamiliar, txtTelTrabajoRefFamiliar;
    ImageIcon background, icoLimpiar, icoIngresar, icoCerrar, icoBuscar;
    
    JButton btnGuardar, btnLimpiar, btnCerrar, btnBuscar;
    
    int identificador = 0;
    int idPersonal = 0;
    int idFamiliar = 0;
    
    public registrarCliente(){
        inicializar();
        this.setLayout(new BorderLayout());
        this.add(pCedula(), BorderLayout.NORTH);
        this.add(pFormulario(), BorderLayout.CENTER);
        this.add(pBotones(), BorderLayout.SOUTH);
        this.setBackground(Color.WHITE);
    }
    
    private JPanel pCedula(){
        JPanel p = new JPanel();
        p.add(new JLabel("Cédula de Cliente: ")); p.add(txtCc); p.add(btnBuscar);
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pFormulario(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,1,10,30));
        p.add(pCliente());
        p.add(pReferencias());
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pCliente(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(7,2));
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
    
    private JPanel pReferencias(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,2));
        p.add(pReferenciaPersonal());
        p.add(pReferenciaFamiliar());
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pReferenciaPersonal(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(8,2));
        p.add(new JLabel("Cédula: ")); p.add(txtCcRefPersonal);
        p.add(new JLabel("Nombre: ")); p.add(txtNomRefPersonal);
        p.add(new JLabel("Dirección de Residencia: ")); p.add(txtDireccionCasaRefPersonal);
        p.add(new JLabel("Teléfono de Residencia: ")); p.add(txtTelCasaRefPersonal);
        
        p.add(new JLabel("Empresa Laborando: ")); p.add(txtEmpresaRefPersonal);
        p.add(new JLabel("Cargo Laboral: ")); p.add(txtCargoRefPersonal);
        p.add(new JLabel("Dirección de Trabajo: ")); p.add(txtDireccionTrabajoRefPersonal);
        p.add(new JLabel("Teléfono de Trabajo: ")); p.add(txtTelTrabajoRefPersonal);
        p.setBorder(new TitledBorder(null, "Datos Referencia Personal"));
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pReferenciaFamiliar(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(8,2));
        p.add(new JLabel("Cédula: ")); p.add(txtCcRefFamiliar);
        p.add(new JLabel("Nombre: ")); p.add(txtNomRefFamiliar);
        p.add(new JLabel("Dirección de Residencia: ")); p.add(txtDireccionCasaRefFamiliar);
        p.add(new JLabel("Teléfono de Residencia: ")); p.add(txtTelCasaRefFamiliar);
        
        p.add(new JLabel("Empresa Laborando: ")); p.add(txtEmpresaRefFamiliar);
        p.add(new JLabel("Cargo Laboral: ")); p.add(txtCargoRefFamiliar);
        p.add(new JLabel("Dirección de Trabajo: ")); p.add(txtDireccionTrabajoRefFamiliar);
        p.add(new JLabel("Teléfono de Trabajo: ")); p.add(txtTelTrabajoRefFamiliar);
        
        p.setBorder(new TitledBorder(null, "Datos Referencia Familiar"));
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pBotones(){
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.add(btnCerrar);
        p.add(btnLimpiar);
        p.add(btnGuardar);
        p.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        p.setOpaque(false);
        return p;
    }
    
    private ImageIcon cambiarTamañoIcono(ImageIcon icon, int ancho, int alto){
        Image img = icon.getImage();
        Image iconoGrande = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(iconoGrande);
    }
    
    private void inicializar(){
        txtCc = new JTextField(20);
        txtNombre = new JTextField();
        txtDireccionCasa = new JTextField();
        txtDireccionTrabajo = new JTextField();
        txtTelCasa = new JTextField();
        txtTelTrabajo = new JTextField();
        txtEmpresa = new JTextField();
        txtCargo = new JTextField();
        
        txtCcRefPersonal = new JTextField();
        txtNomRefPersonal = new JTextField();
        txtTelCasaRefPersonal = new JTextField();
        txtDireccionCasaRefPersonal = new JTextField();
        txtDireccionTrabajoRefPersonal = new JTextField();
        txtCargoRefPersonal = new JTextField();
        txtEmpresaRefPersonal = new JTextField();
        txtTelTrabajoRefPersonal = new JTextField();
        
        txtEmpresaRefFamiliar = new JTextField();
        txtTelTrabajoRefFamiliar = new JTextField();
        txtCcRefFamiliar = new JTextField();
        txtNomRefFamiliar = new JTextField();
        txtTelCasaRefFamiliar = new JTextField();
        txtDireccionCasaRefFamiliar = new JTextField();
        txtDireccionTrabajoRefFamiliar = new JTextField();
        txtCargoRefFamiliar = new JTextField();
        
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
        deshabilitar();
    }
    
    public void limpiar(){
        txtCc.setText("");
        txtNombre.setText("");
        txtDireccionCasa.setText("");
        txtTelCasa.setText("");
        txtEmpresa.setText("");
        txtCargo.setText("");
        txtDireccionTrabajo.setText("");
        txtTelTrabajo.setText("");
        txtCcRefPersonal.setText("");
        txtNomRefPersonal.setText("");
        txtTelCasaRefPersonal.setText("");
        txtDireccionCasaRefPersonal.setText("");
        txtDireccionTrabajoRefPersonal.setText("");
        txtCargoRefPersonal.setText("");
        txtEmpresaRefPersonal.setText("");
        txtTelTrabajoRefPersonal.setText("");
        txtCcRefFamiliar.setText("");
        txtNomRefFamiliar.setText("");
        txtTelCasaRefFamiliar.setText("");
        txtDireccionCasaRefFamiliar.setText("");
        txtDireccionTrabajoRefFamiliar.setText("");
        txtCargoRefFamiliar.setText("");
        txtEmpresaRefFamiliar.setText("");
        txtTelTrabajoRefFamiliar.setText("");
        identificador = 0;
        idPersonal = 0;
        idFamiliar = 0;
    }
    
    public void habilitar(){
        txtNombre.setEditable(true);
        txtDireccionCasa.setEditable(true);
        txtTelCasa.setEditable(true);
        txtEmpresa.setEditable(true);
        txtCargo.setEditable(true);
        txtDireccionTrabajo.setEditable(true);
        txtTelTrabajo.setEditable(true);
        txtCcRefPersonal.setEditable(true);
        txtNomRefPersonal.setEditable(true);
        txtTelCasaRefPersonal.setEditable(true);
        txtDireccionCasaRefPersonal.setEditable(true);
        txtDireccionTrabajoRefPersonal.setEditable(true);
        txtCargoRefPersonal.setEditable(true);
        txtEmpresaRefPersonal.setEditable(true);
        txtTelTrabajoRefPersonal.setEditable(true);
        txtCcRefFamiliar.setEditable(true);
        txtNomRefFamiliar.setEditable(true);
        txtTelCasaRefFamiliar.setEditable(true);
        txtDireccionCasaRefFamiliar.setEditable(true);
        txtDireccionTrabajoRefFamiliar.setEditable(true);
        txtCargoRefFamiliar.setEditable(true);
        txtEmpresaRefFamiliar.setEditable(true);
        txtTelTrabajoRefFamiliar.setEditable(true);
    }
    public void deshabilitar(){
        txtNombre.setEditable(false);
        txtDireccionCasa.setEditable(false);
        txtTelCasa.setEditable(false);
        txtEmpresa.setEditable(false);
        txtCargo.setEditable(false);
        txtDireccionTrabajo.setEditable(false);
        txtTelTrabajo.setEditable(false);
        txtCcRefPersonal.setEditable(false);
        txtNomRefPersonal.setEditable(false);
        txtTelCasaRefPersonal.setEditable(false);
        txtDireccionCasaRefPersonal.setEditable(false);
        txtDireccionTrabajoRefPersonal.setEditable(false);
        txtCargoRefPersonal.setEditable(false);
        txtEmpresaRefPersonal.setEditable(false);
        txtTelTrabajoRefPersonal.setEditable(false);
        txtCcRefFamiliar.setEditable(false);
        txtNomRefFamiliar.setEditable(false);
        txtTelCasaRefFamiliar.setEditable(false);
        txtDireccionCasaRefFamiliar.setEditable(false);
        txtDireccionTrabajoRefFamiliar.setEditable(false);
        txtCargoRefFamiliar.setEditable(false);
        txtEmpresaRefFamiliar.setEditable(false);
        txtTelTrabajoRefFamiliar.setEditable(false);
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

    public JTextField getTxtDireccionTrabajo() {
        return txtDireccionTrabajo;
    }

    public void setTxtDireccionTrabajo(JTextField txtDireccionTrabajo) {
        this.txtDireccionTrabajo = txtDireccionTrabajo;
    }

    public JTextField getTxtTelTrabajo() {
        return txtTelTrabajo;
    }

    public void setTxtTelTrabajo(JTextField txtTelTrabajo) {
        this.txtTelTrabajo = txtTelTrabajo;
    }

    public JTextField getTxtCcRefPersonal() {
        return txtCcRefPersonal;
    }

    public void setTxtCcRefPersonal(JTextField txtCcRefPersonal) {
        this.txtCcRefPersonal = txtCcRefPersonal;
    }

    public JTextField getTxtNomRefPersonal() {
        return txtNomRefPersonal;
    }

    public void setTxtNomRefPersonal(JTextField txtNomRefPersonal) {
        this.txtNomRefPersonal = txtNomRefPersonal;
    }

    public JTextField getTxtTelCasaRefPersonal() {
        return txtTelCasaRefPersonal;
    }

    public void setTxtTelCasaRefPersonal(JTextField txtTelCasaRefPersonal) {
        this.txtTelCasaRefPersonal = txtTelCasaRefPersonal;
    }

    public JTextField getTxtDireccionCasaRefPersonal() {
        return txtDireccionCasaRefPersonal;
    }

    public void setTxtDireccionCasaRefPersonal(JTextField txtDireccionCasaRefPersonal) {
        this.txtDireccionCasaRefPersonal = txtDireccionCasaRefPersonal;
    }

    public JTextField getTxtDireccionTrabajoRefPersonal() {
        return txtDireccionTrabajoRefPersonal;
    }

    public void setTxtDireccionTrabajoRefPersonal(JTextField txtDireccionTrabajoRefPersonal) {
        this.txtDireccionTrabajoRefPersonal = txtDireccionTrabajoRefPersonal;
    }

    public JTextField getTxtCargoRefPersonal() {
        return txtCargoRefPersonal;
    }

    public void setTxtCargoRefPersonal(JTextField txtCargoRefPersonal) {
        this.txtCargoRefPersonal = txtCargoRefPersonal;
    }

    public JTextField getTxtEmpresaRefPersonal() {
        return txtEmpresaRefPersonal;
    }

    public void setTxtEmpresaRefPersonal(JTextField txtEmpresaRefPersonal) {
        this.txtEmpresaRefPersonal = txtEmpresaRefPersonal;
    }

    public JTextField getTxtTelTrabajoRefPersonal() {
        return txtTelTrabajoRefPersonal;
    }

    public void setTxtTelTrabajoRefPersonal(JTextField txtTelTrabajoRefPersonal) {
        this.txtTelTrabajoRefPersonal = txtTelTrabajoRefPersonal;
    }

    public JTextField getTxtCcRefFamiliar() {
        return txtCcRefFamiliar;
    }

    public void setTxtCcRefFamiliar(JTextField txtCcRefFamiliar) {
        this.txtCcRefFamiliar = txtCcRefFamiliar;
    }

    public JTextField getTxtNomRefFamiliar() {
        return txtNomRefFamiliar;
    }

    public void setTxtNomRefFamiliar(JTextField txtNomRefFamiliar) {
        this.txtNomRefFamiliar = txtNomRefFamiliar;
    }

    public JTextField getTxtTelCasaRefFamiliar() {
        return txtTelCasaRefFamiliar;
    }

    public void setTxtTelCasaRefFamiliar(JTextField txtTelCasaRefFamiliar) {
        this.txtTelCasaRefFamiliar = txtTelCasaRefFamiliar;
    }

    public JTextField getTxtDireccionCasaRefFamiliar() {
        return txtDireccionCasaRefFamiliar;
    }

    public void setTxtDireccionCasaRefFamiliar(JTextField txtDireccionCasaRefFamiliar) {
        this.txtDireccionCasaRefFamiliar = txtDireccionCasaRefFamiliar;
    }

    public JTextField getTxtDireccionTrabajoRefFamiliar() {
        return txtDireccionTrabajoRefFamiliar;
    }

    public void setTxtDireccionTrabajoRefFamiliar(JTextField txtDireccionTrabajoRefFamiliar) {
        this.txtDireccionTrabajoRefFamiliar = txtDireccionTrabajoRefFamiliar;
    }

    public JTextField getTxtCargoRefFamiliar() {
        return txtCargoRefFamiliar;
    }

    public void setTxtCargoRefFamiliar(JTextField txtCargoRefFamiliar) {
        this.txtCargoRefFamiliar = txtCargoRefFamiliar;
    }

    public JTextField getTxtEmpresaRefFamiliar() {
        return txtEmpresaRefFamiliar;
    }

    public void setTxtEmpresaRefFamiliar(JTextField txtEmpresaRefFamiliar) {
        this.txtEmpresaRefFamiliar = txtEmpresaRefFamiliar;
    }

    public JTextField getTxtTelTrabajoRefFamiliar() {
        return txtTelTrabajoRefFamiliar;
    }

    public void setTxtTelTrabajoRefFamiliar(JTextField txtTelTrabajoRefFamiliar) {
        this.txtTelTrabajoRefFamiliar = txtTelTrabajoRefFamiliar;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(int idFamiliar) {
        this.idFamiliar = idFamiliar;
    }
    
    public void registrarEscuchas(controlador c){
        btnGuardar.addActionListener(c);
        btnLimpiar.addActionListener(c);
        btnCerrar.addActionListener(c);
        btnBuscar.addActionListener(c);
    }
}