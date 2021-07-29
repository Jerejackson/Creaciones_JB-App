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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jeremi_Sanchez
 */
public class registroInteresNCuotas extends JPanel{
    
    JTable tabla;
    DefaultTableModel modeloTabla;
    JScrollPane scroll;
    
    ImageIcon icoGuardar, icoAtras;
    JButton btnGuardar, btnAtras;
    
    JTextField txtNCuotas, txtInteres;
    
    public registroInteresNCuotas(){
        inicializar();
        this.setLayout(new BorderLayout());
        this.add(scroll, BorderLayout.CENTER);
        this.add(pSur(), BorderLayout.SOUTH);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
    }
    private JPanel pSur(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,1));
        p.add(pRegistro());
        p.add(pBotones());
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pRegistro(){
        JPanel p = new JPanel();
        p.add(new JLabel("No. de Cuotas"));
        p.add(txtNCuotas);
        p.add(new JLabel("% Interes(Solo el Número)"));
        p.add(txtInteres);
        p.setOpaque(false);
        return p;
    }
    
    private JPanel pBotones(){
        JPanel p = new JPanel();
        p.add(btnAtras);
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
    
    public void limpiar(){
        txtInteres.setText("");
        txtNCuotas.setText("");
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.removeRow(i);
        }
        modeloTabla.setRowCount(0);
    }
    
    private void inicializar(){
        modeloTabla = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
            };
        modeloTabla.addColumn("No. Cuotas");
        modeloTabla.addColumn("Tasa de Interés");
        tabla = new JTable(modeloTabla);
        scroll = new JScrollPane(tabla);
        
        txtNCuotas = new JTextField(20);
        txtInteres = new JTextField(20);
    
        icoAtras = new ImageIcon(System.getProperty("user.dir")+"/img/icons/login/atras.png");
        icoGuardar = new ImageIcon(System.getProperty("user.dir")+"/img/icons/disquete.png");
        
        btnGuardar = new JButton(cambiarTamañoIcono(icoGuardar,100,100));
        btnAtras = new JButton(cambiarTamañoIcono(icoAtras,100,100));
        
        btnGuardar.setBackground(Color.WHITE);
        btnAtras.setBackground(Color.WHITE);
    }
    
    public void registrarEscuchas(controlador c){
        btnGuardar.addActionListener(c);
        btnAtras.addActionListener(c);
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnAtras() {
        return btnAtras;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public JTextField getTxtNCuotas() {
        return txtNCuotas;
    }

    public void setTxtNCuotas(JTextField txtNCuotas) {
        this.txtNCuotas = txtNCuotas;
    }

    public JTextField getTxtInteres() {
        return txtInteres;
    }

    public void setTxtInteres(JTextField txtInteres) {
        this.txtInteres = txtInteres;
    }
    
    
}
