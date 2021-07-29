/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jeremi_Sanchez
 */
public class ventanaConsultaCC extends JFrame{
    
    JTable tabla;
    DefaultTableModel modelo;
    JScrollPane scrollTabla;
    
    JComboBox jcbLista;
    DefaultComboBoxModel modeloCbb;
    
    JButton aceptar;
    
    public ventanaConsultaCC(){
        inicializar();
        getContentPane().add(contenedor());
        setTitle("Consultar Número de credito");
        setSize(400,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private JPanel contenedor(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(scrollTabla, BorderLayout.CENTER);
        p.add(pSur(), BorderLayout.SOUTH);
        p.setBackground(Color.WHITE);
        return p;
    }
    
    private JPanel pSur(){
        JPanel p = new JPanel();
        p.add(new JLabel("Seleccionar No. de Credito"));
        p.add(jcbLista);
        p.add(aceptar);
        p.setOpaque(false);
        return p;
    }
    
    private void inicializar(){
        modelo = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
            };
        modelo.addColumn("No. Credito");
        modelo.addColumn("Nombre Producto");
        modelo.addColumn("Valor Producto");
        modelo.addColumn("No. Cuotas");
        modelo.addColumn("Tasa de Interés (%)");
        modelo.addColumn("Fecha Inicio");
        
        tabla = new JTable(modelo);
        scrollTabla = new JScrollPane(tabla);
        
        modeloCbb = new DefaultComboBoxModel();
        jcbLista = new JComboBox(modeloCbb);
        jcbLista.setBackground(Color.white);
        
        aceptar = new JButton("Aceptar");
    }

    public JButton getAceptar() {
        return aceptar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultComboBoxModel getModeloCbb() {
        return modeloCbb;
    }

    public void setModeloCbb(DefaultComboBoxModel modeloCbb) {
        this.modeloCbb = modeloCbb;
    }

    public JComboBox getJcbLista() {
        return jcbLista;
    }

    public void setJcbLista(JComboBox jcbLista) {
        this.jcbLista = jcbLista;
    }
    
    public void registrarEscuchas(controlador c){
        aceptar.addActionListener(c);
    }
    
}