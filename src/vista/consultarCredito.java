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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jeremi_Sanchez
 */
public class consultarCredito extends JPanel{
    
    JLabel lblCredito, lblCedula,
            lblLogo,
            lblCodCredito, lblNombreProducto,  lblValorProducto, lblDiferido, 
            lblValorProductoFinal, lblFechaInicio, 
            lblCodCuota, lblFechaAPagar, lblValorAPagar, lblValorCuota,
            lblCcCliente, lblNombreCliente, lblDirCasa, lblTelCasa, lblDirTrabajo, lblCargo, lblEmpresa, lblTelTrabajo;
    
    JTextField txtFechaPago, txtValorPago, txtNumCredito, txtNumCedula;
    
    ImageIcon background, icoLimpiar, icoImprimir, icoVolver, icoBuscar, icoGuardar;
    
    JButton btnImprimir, btnLimpiar, btnVolver, btnBuscarCredito, btnBuscarCedula, btnCambioCc, btnCambioCre,btnGuardar;
    
    JTable tabla;
    DefaultTableModel modelo;
    JScrollPane scrollTabla;
    
    public consultarCredito(){
        inicializar();
        this.setLayout( new BorderLayout());
        this.add(pBusqueda(), BorderLayout.NORTH);
        this.add(tarjeta(), BorderLayout.CENTER);
        this.add(pBotones(), BorderLayout.SOUTH);
        this.setBackground(Color.WHITE);
        //this.add(tarjeta());
    }
    /*
    JPanel cc(){
        JPanel p =new JPanel();
        p.setLayout( new GridLayout(2,1));
        p.add(encabezado());
        p.add(tarjeta());
        return p;
    }
    */
    
    
    public JPanel pBusqueda(){
        JPanel p = new JPanel();
        p.add(lblCredito); p.add(txtNumCredito); p.add(btnBuscarCredito); p.add(btnCambioCc);
        p.add(lblCedula); p.add(txtNumCedula); p.add(btnBuscarCedula); p.add(btnCambioCre);
        p.setOpaque(false);
        return p;
    }
    
    public JPanel tarjeta(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5,1));
        p.add(encabezado());
        p.add(datosTrabajo());
        p.add(datosCasa());
        p.add(datosCredito());
        p.add(scrollTabla);
        p.setOpaque(false);
        return p;
    }
    
    private JPanel encabezado(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,2));
        p.add(pLogo());  p.add(encabezadoFactura());
        p.add(encabezadoNombre());  p.add(encabezadoCedula());
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pLogo(){
        JPanel p = new JPanel();
        p.add(lblLogo);
        p.setOpaque(false);
        return p;
    }
    
    private JPanel encabezadoFactura(){
        JPanel p = new JPanel();
        p.add(lblCodCredito);
        p.setBorder(new TitledBorder(null, "FACTURA DE VENTA"));
        p.setOpaque(false);
        return p;
    }
    private JPanel encabezadoNombre(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,1));
        p.add(new JLabel("CLIENTE:"));
        p.add(lblNombreCliente);
        p.setOpaque(false);
        return p;
    }
    
    private JPanel encabezadoCedula(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,2));
        p.add(new JLabel("C.C.#:"));
        p.add(lblCcCliente);
        p.setOpaque(false);
        return p;
    } 
    
    
    private JPanel datosTrabajo(){
        JPanel p = new JPanel();
        p.setBorder(new TitledBorder(null, "INFORMACIÓN UBICACIÓN LABORAL"));
        p.setLayout(new GridLayout(2,4));
        p.add(new JLabel("Empresa: "));    p.add(lblEmpresa);    p.add(new JLabel("Cargo: "));    p.add(lblCargo);
        p.add(new JLabel("Dirección: "));    p.add(lblDirTrabajo);    p.add(new JLabel("Teléfono: "));    p.add(lblTelTrabajo);
        p.setOpaque(false);
        return p;
    }
    
    private JPanel datosCasa(){
        JPanel p = new JPanel();
        p.setBorder(new TitledBorder(null, "INFORMACIÓN LUGAR DE RESIDENCIA"));
        p.setLayout(new GridLayout(1,4));
        p.add(new JLabel("Dirección: "));    p.add(lblDirCasa);    p.add(new JLabel("Teléfono: "));    p.add(lblTelCasa);
        p.setOpaque(false);
        return p;
    }
    
    private JPanel datosCredito(){
        JPanel p = new JPanel();
        p.setBorder(new TitledBorder(null, "INFORMACIÓN DEL CREDITO"));
        p.setLayout(new GridLayout(3,4));
        p.add(new JLabel("Nombre Producto:"));  p.add(lblNombreProducto);  p.add(new JLabel("Valor a Contado: "));  p.add(lblValorProducto); 
        p.add(new JLabel("Fecha Inicio: "));  p.add(lblFechaInicio);  p.add(new JLabel("Valor a Crédito:"));  p.add(lblValorProductoFinal);  
        p.add(new JLabel("# Cuotas - % interes: "));  p.add(lblDiferido); p.add(new JLabel("Valor Cuota"));  p.add(lblValorCuota);
        p.setOpaque(false);
        return p;
    }
    
    public JPanel pBotones(){
        JPanel p = new JPanel();
        //p.setLayout(new GridLayout(1,3,40,0));
        p.setOpaque(false);
        p.add(btnVolver);
        p.add(btnLimpiar);
        p.add(btnGuardar);
        p.add(btnImprimir);
        p.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
        p.setOpaque(false);
        return p;
    }
    
    public void pBusquedaVisible(boolean is){
        if(is == true){
            cambioCredito();
            btnVolver.setVisible(is);
            btnLimpiar.setVisible(is);
            btnGuardar.setVisible(is);
            btnImprimir.setVisible(is);
        }else{
            lblCredito.setVisible(is);
            txtNumCredito.setVisible(is);
            btnBuscarCredito.setVisible(is);
            btnCambioCc.setVisible(is);
            lblCedula.setVisible(is);
            txtNumCedula.setVisible(is);
            btnBuscarCedula.setVisible(is);
            btnCambioCre.setVisible(is);
            btnVolver.setVisible(is);
            btnLimpiar.setVisible(is);
            btnGuardar.setVisible(is);
            btnImprimir.setVisible(is);
        }
    }
    
    private ImageIcon cambiarTamañoIcono(ImageIcon icon, int ancho, int alto){
        Image img = icon.getImage();
        Image iconoGrande = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(iconoGrande);
    }
    
    public void cambioCC(){
        lblCredito.setVisible(false);
        txtNumCredito.setVisible(false);
        btnBuscarCredito.setVisible(false);
        btnCambioCc.setVisible(false);
        
        lblCedula.setVisible(true);
        txtNumCedula.setVisible(true);
        btnBuscarCedula.setVisible(true);
        btnCambioCre.setVisible(true);
    }

    public void cambioCredito(){
        lblCredito.setVisible(true);
        txtNumCredito.setVisible(true);
        btnBuscarCredito.setVisible(true);
        btnCambioCc.setVisible(true);
        
        lblCedula.setVisible(false);
        txtNumCedula.setVisible(false);
        btnBuscarCedula.setVisible(false);
        btnCambioCre.setVisible(false);
    }
    
    private void inicializar(){
        lblLogo = new JLabel();
        lblLogo.setIcon(cambiarTamañoIcono(new ImageIcon(System.getProperty("user.dir")+"/img/logo1.png"),48,48));
        lblCodCredito = new JLabel("12");
        lblNombreProducto = new JLabel("");
        lblValorProducto = new JLabel("");
        lblDiferido = new JLabel("");
        lblValorProductoFinal = new JLabel("");
        lblFechaInicio = new JLabel("");
        lblCodCuota = new JLabel("");
        lblFechaAPagar = new JLabel("");
        lblValorAPagar = new JLabel("");
        lblCcCliente = new JLabel("");
        lblNombreCliente = new JLabel("");
        lblDirCasa = new JLabel("");
        lblDirTrabajo = new JLabel("");
        lblCargo = new JLabel("");
        lblEmpresa = new JLabel("");
        lblTelTrabajo = new JLabel("");
        lblTelCasa = new JLabel("");
        lblValorCuota = new JLabel("");
        lblCredito = new JLabel("No. de Credito");
        lblCedula = new JLabel("No. de Cédula");
        
        icoLimpiar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/login/limpiar.png");
        icoImprimir = new ImageIcon(System.getProperty("user.dir")+"/img/icons/imprimir.png");
        icoVolver = new ImageIcon(System.getProperty("user.dir")+"/img/icons/login/atras.png");
        icoBuscar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/buscar.png");
        icoGuardar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/disquete.png");
        
        
        btnGuardar = new JButton(cambiarTamañoIcono(icoGuardar,60,60));
        btnImprimir = new JButton(cambiarTamañoIcono(icoImprimir,60,60));
        btnLimpiar = new JButton(cambiarTamañoIcono(icoLimpiar,60,60));
        btnVolver = new JButton(cambiarTamañoIcono(icoVolver,60,60));
        btnBuscarCredito = new JButton(cambiarTamañoIcono(icoBuscar,30,30));
        btnBuscarCedula = new JButton(cambiarTamañoIcono(icoBuscar,30,30));
        btnCambioCc = new JButton("Buscar por No. Cédula");
        btnCambioCre = new JButton("Buscar por No. Credito");
        
        btnCambioCc.setBackground(Color.WHITE);
        btnCambioCre.setBackground(Color.WHITE);
        btnImprimir.setBackground(Color.WHITE);
        btnLimpiar.setBackground(Color.WHITE);
        btnVolver.setBackground(Color.WHITE);
        btnBuscarCredito.setBackground(Color.WHITE);
        btnBuscarCedula.setBackground(Color.WHITE);
        
        txtFechaPago = new JTextField();
        txtValorPago = new JTextField();
        txtNumCredito = new JTextField(20);
        txtNumCedula = new JTextField(20);
        
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
                if (col == 0 || col == 1 || col == 4)
                return false;
                return super.isCellEditable(row, col);
            }
        };
        
        modelo.addColumn("FECHAS DE PAGO");
        modelo.addColumn("RECIBO No.");
        modelo.addColumn("FECHA ABONO");
        modelo.addColumn("ABONO");
        modelo.addColumn("SALDO");
        
        
        tabla = new JTable(modelo);
        tabla.getTableHeader().setReorderingAllowed(false);
        scrollTabla = new JScrollPane(tabla);
        cambioCredito();
    }
    
    public void limpiar(){
        txtFechaPago.setText("");
        txtValorPago.setText("");
        txtNumCredito.setText("");
        txtNumCedula.setText("");
        
        lblCodCredito.setText("");
        lblNombreProducto.setText("");
        lblValorProducto.setText("");
        lblDiferido.setText(""); 
        lblValorProductoFinal.setText("");
        lblFechaInicio.setText(""); 
        lblCodCuota.setText("");
        lblFechaAPagar.setText("");
        lblValorAPagar.setText("");
        lblCcCliente.setText("");
        lblNombreCliente.setText("");
        lblDirCasa.setText("");
        lblTelCasa.setText("");
        lblDirTrabajo.setText("");
        lblCargo.setText("");
        lblEmpresa.setText("");
        lblTelTrabajo.setText("");
        lblValorCuota.setText("");
        
        while (modelo.getRowCount() > 0){
            modelo.removeRow(0);
            }
            modelo.setRowCount(0);
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnBuscarCredito() {
        return btnBuscarCredito;
    }

    public JButton getBtnBuscarCedula() {
        return btnBuscarCedula;
    }

    public JButton getBtnCambioCc() {
        return btnCambioCc;
    }

    public JButton getBtnCambioCre() {
        return btnCambioCre;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }
    
    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JLabel getLblCredito() {
        return lblCredito;
    }

    public void setLblCredito(JLabel lblCredito) {
        this.lblCredito = lblCredito;
    }

    public JLabel getLblCedula() {
        return lblCedula;
    }

    public void setLblCedula(JLabel lblCedula) {
        this.lblCedula = lblCedula;
    }

    public JLabel getLblCodCredito() {
        return lblCodCredito;
    }

    public void setLblCodCredito(JLabel lblCodCredito) {
        this.lblCodCredito = lblCodCredito;
    }

    public JLabel getLblNombreProducto() {
        return lblNombreProducto;
    }

    public void setLblNombreProducto(JLabel lblNombreProducto) {
        this.lblNombreProducto = lblNombreProducto;
    }

    public JLabel getLblValorProducto() {
        return lblValorProducto;
    }

    public void setLblValorProducto(JLabel lblValorProducto) {
        this.lblValorProducto = lblValorProducto;
    }

    public JLabel getLblDiferido() {
        return lblDiferido;
    }

    public void setLblDiferido(JLabel lblDiferido) {
        this.lblDiferido = lblDiferido;
    }

    public JLabel getLblValorProductoFinal() {
        return lblValorProductoFinal;
    }

    public void setLblValorProductoFinal(JLabel lblValorProductoFinal) {
        this.lblValorProductoFinal = lblValorProductoFinal;
    }

    public JLabel getLblFechaInicio() {
        return lblFechaInicio;
    }

    public void setLblFechaInicio(JLabel lblFechaInicio) {
        this.lblFechaInicio = lblFechaInicio;
    }

    public JLabel getLblCodCuota() {
        return lblCodCuota;
    }

    public JLabel getLblValorCuota() {
        return lblValorCuota;
    }

    public void setLblValorCuota(JLabel lblValorCuota) {
        this.lblValorCuota = lblValorCuota;
    }
    
    
    public void setLblCodCuota(JLabel lblCodCuota) {
        this.lblCodCuota = lblCodCuota;
    }

    public JLabel getLblFechaAPagar() {
        return lblFechaAPagar;
    }

    public void setLblFechaAPagar(JLabel lblFechaAPagar) {
        this.lblFechaAPagar = lblFechaAPagar;
    }

    public JLabel getLblValorAPagar() {
        return lblValorAPagar;
    }

    public void setLblValorAPagar(JLabel lblValorAPagar) {
        this.lblValorAPagar = lblValorAPagar;
    }

    public JLabel getLblCcCliente() {
        return lblCcCliente;
    }

    public void setLblCcCliente(JLabel lblCcCliente) {
        this.lblCcCliente = lblCcCliente;
    }

    public JLabel getLblNombreCliente() {
        return lblNombreCliente;
    }

    public void setLblNombreCliente(JLabel lblNombreCliente) {
        this.lblNombreCliente = lblNombreCliente;
    }

    public JLabel getLblDirCasa() {
        return lblDirCasa;
    }

    public void setLblDirCasa(JLabel lblDirCasa) {
        this.lblDirCasa = lblDirCasa;
    }

    public JLabel getLblTelCasa() {
        return lblTelCasa;
    }

    public void setLblTelCasa(JLabel lblTelCasa) {
        this.lblTelCasa = lblTelCasa;
    }

    public JLabel getLblDirTrabajo() {
        return lblDirTrabajo;
    }

    public void setLblDirTrabajo(JLabel lblDirTrabajo) {
        this.lblDirTrabajo = lblDirTrabajo;
    }

    public JLabel getLblCargo() {
        return lblCargo;
    }

    public void setLblCargo(JLabel lblCargo) {
        this.lblCargo = lblCargo;
    }

    public JLabel getLblEmpresa() {
        return lblEmpresa;
    }

    public void setLblEmpresa(JLabel lblEmpresa) {
        this.lblEmpresa = lblEmpresa;
    }

    public JLabel getLblTelTrabajo() {
        return lblTelTrabajo;
    }

    public void setLblTelTrabajo(JLabel lblTelTrabajo) {
        this.lblTelTrabajo = lblTelTrabajo;
    }

    public JTextField getTxtFechaPago() {
        return txtFechaPago;
    }

    public void setTxtFechaPago(JTextField txtFechaPago) {
        this.txtFechaPago = txtFechaPago;
    }

    public JTextField getTxtValorPago() {
        return txtValorPago;
    }

    public void setTxtValorPago(JTextField txtValorPago) {
        this.txtValorPago = txtValorPago;
    }

    public JTextField getTxtNumCredito() {
        return txtNumCredito;
    }

    public void setTxtNumCredito(JTextField txtNumCredito) {
        this.txtNumCredito = txtNumCredito;
    }

    public JTextField getTxtNumCedula() {
        return txtNumCedula;
    }

    public void setTxtNumCedula(JTextField txtNumCedula) {
        this.txtNumCedula = txtNumCedula;
    }
    
    
    public void registrarEscuchas(controlador c){
        btnImprimir.addActionListener(c);
        btnLimpiar.addActionListener(c);
        btnVolver.addActionListener(c);
        btnBuscarCredito.addActionListener(c);
        btnBuscarCedula.addActionListener(c);
        btnCambioCc.addActionListener(c);
        btnCambioCre.addActionListener(c);
        btnGuardar.addActionListener(c);
    }
}