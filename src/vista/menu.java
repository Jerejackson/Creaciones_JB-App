/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jeremi_Sanchez
 */
public class menu extends JPanel{
    
    ImageIcon icoRegistrarCliente, icoRegistrarCredito, icoConsultarCredito, icoContratoVenta, icoCobroJuridico, icoRegistrarEmpleado,
            icoSalir, icoCambioCuotas, icoConsultarDeudores, background;
    
    JButton btnRegistrarCliente, btnRegistrarCredito, btnConsultarCredito, btnContratoVenta, btnCobroJuridico, btnRegistrarEmpleado,
            btnSalir, btnCambioCuotas, btnConsultarDeudores;
    
    public menu(){
        inicializar();
        this.setLayout(new BorderLayout());
        this.add(menuCentro(), BorderLayout.CENTER);
        this.add(btnSalir, BorderLayout.SOUTH);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }
    
    private JPanel menuCentro(){
        JPanel p = new JPanel();
        p.setLayout( new GridLayout(2,4,30,30));
        p.add(btnRegistrarCliente); 
        p.add(btnRegistrarCredito);
        p.add(btnRegistrarEmpleado);
        p.add(btnCambioCuotas);
        p.add(btnConsultarCredito);
        p.add(btnConsultarDeudores); 
        p.add(btnContratoVenta); 
        //p.add(btnCobroJuridico);
        p.setOpaque(false);
        p.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        return p;
    }
    
    private ImageIcon cambiarTamañoIcono(ImageIcon icon, int ancho, int alto){
        Image img = icon.getImage();
        Image iconoGrande = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(iconoGrande);
    }
    
    @Override 
     public void paintComponent(Graphics g ){
        super.paintComponent(g);
        background = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/fondo.jpg").getImage());
        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
       
     }

    private void inicializar(){
        
        icoRegistrarCliente = new ImageIcon(System.getProperty("user.dir")+"/img/menu/usuario.png");
        icoRegistrarCredito = new ImageIcon(System.getProperty("user.dir")+"/img/menu/crearcredito.png");
        icoConsultarCredito = new ImageIcon(System.getProperty("user.dir")+"/img/menu/buscarcredito.png");
        icoContratoVenta = new ImageIcon(System.getProperty("user.dir")+"/img/menu/contrato.png");
        icoCobroJuridico = new ImageIcon(System.getProperty("user.dir")+"/img/menu/cobrojuridico.png");
        icoSalir = new ImageIcon(System.getProperty("user.dir")+"/img/icons/login/cancelar.png");
        icoRegistrarEmpleado = new ImageIcon(System.getProperty("user.dir")+"/img/menu/empleado.png");
        icoCambioCuotas = new ImageIcon(System.getProperty("user.dir")+"/img/menu/cuotas.png");
        icoConsultarDeudores = new ImageIcon(System.getProperty("user.dir")+"/img/menu/deudores.png");
        
        btnConsultarDeudores = new JButton(cambiarTamañoIcono(icoConsultarDeudores,100,100));
        btnCambioCuotas = new JButton(cambiarTamañoIcono(icoCambioCuotas,100,100));
        btnRegistrarEmpleado = new JButton(cambiarTamañoIcono(icoRegistrarEmpleado,100,100));
        btnRegistrarCliente = new JButton(cambiarTamañoIcono(icoRegistrarCliente,100,100));
        btnRegistrarCredito = new JButton(cambiarTamañoIcono(icoRegistrarCredito,100,100));
        btnConsultarCredito = new JButton(cambiarTamañoIcono(icoConsultarCredito,100,100));
        btnContratoVenta = new JButton(cambiarTamañoIcono(icoContratoVenta,100,100));
        btnCobroJuridico = new JButton(cambiarTamañoIcono(icoCobroJuridico,100,100));
        btnSalir = new JButton(cambiarTamañoIcono(icoSalir,100,100));
        
        btnConsultarDeudores.setBackground(Color.WHITE);
        btnCambioCuotas.setBackground(Color.WHITE);
        btnRegistrarEmpleado.setBackground(Color.WHITE);
        btnRegistrarCliente.setBackground(Color.WHITE);
        btnRegistrarCredito.setBackground(Color.WHITE);
        btnConsultarCredito.setBackground(Color.WHITE);
        btnContratoVenta.setBackground(Color.WHITE);
        btnCobroJuridico.setBackground(Color.WHITE);
        btnSalir.setBackground(Color.WHITE);
        
        btnRegistrarCliente.setText(String.valueOf("Registrar Cliente"));
        btnRegistrarCliente.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnRegistrarCliente.setHorizontalTextPosition(SwingConstants.CENTER);
        
        btnRegistrarEmpleado.setText(String.valueOf("Registrar Empleado"));
        btnRegistrarEmpleado.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnRegistrarEmpleado.setHorizontalTextPosition(SwingConstants.CENTER);
        
        btnRegistrarCredito.setText(String.valueOf("Registrar Credito"));
        btnRegistrarCredito.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnRegistrarCredito.setHorizontalTextPosition(SwingConstants.CENTER);
        
        btnCambioCuotas.setText(String.valueOf("Cambio # Cuotas \no Tasa Interés"));
        btnCambioCuotas.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCambioCuotas.setHorizontalTextPosition(SwingConstants.CENTER);
        
        btnConsultarDeudores.setText(String.valueOf("Consultar Deudores"));
        btnConsultarDeudores.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnConsultarDeudores.setHorizontalTextPosition(SwingConstants.CENTER);
        
        btnConsultarCredito.setText(String.valueOf("Consultar Credito"));
        btnConsultarCredito.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnConsultarCredito.setHorizontalTextPosition(SwingConstants.CENTER);
        
        btnContratoVenta.setText(String.valueOf("Imprimir Documento"));
        btnContratoVenta.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnContratoVenta.setHorizontalTextPosition(SwingConstants.CENTER);
        
        btnCobroJuridico.setText(String.valueOf("Cobro Jurídico"));
        btnCobroJuridico.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCobroJuridico.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    public JButton getBtnCambioCuotas() {
        return btnCambioCuotas;
    }

    public JButton getBtnRegistrarCliente() {
        return btnRegistrarCliente;
    }

    public JButton getBtnRegistrarCredito() {
        return btnRegistrarCredito;
    }

    public JButton getBtnConsultarCredito() {
        return btnConsultarCredito;
    }

    public JButton getBtnContratoVenta() {
        return btnContratoVenta;
    }

    public JButton getBtnCobroJuridico() {
        return btnCobroJuridico;
    }

    public JButton getBtnRegistrarEmpleado() {
        return btnRegistrarEmpleado;
    }
    
    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnConsultarDeudores() {
        return btnConsultarDeudores;
    }
    
    public void registrarEscuchas(controlador c){
        btnRegistrarEmpleado.addActionListener(c);
        btnRegistrarCliente.addActionListener(c);
        btnRegistrarCredito.addActionListener(c);
        btnConsultarCredito.addActionListener(c);
        btnConsultarDeudores.addActionListener(c);
        btnContratoVenta.addActionListener(c);
        btnCobroJuridico.addActionListener(c);
        btnSalir.addActionListener(c);
        btnCambioCuotas.addActionListener(c);
    }
}