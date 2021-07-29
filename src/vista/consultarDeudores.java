/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.controlador;
import controlador.controladorDeudores;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jeremi_Sanchez
 */
public class consultarDeudores extends JPanel{
    
    JButton btnVolver, btnImprimir;
    JScrollPane scroll;
    JTable tabla;
    DefaultTableModel modeloTabla;
    
    ImageIcon icoAtras, icoImprimir;
    
    public consultarDeudores(){
        inicializar();
        this.setLayout(new BorderLayout());
        this.add(scroll, BorderLayout.CENTER);
        this.add(pBotones(), BorderLayout.SOUTH);
        this.setBackground(Color.WHITE);
    }
    
    private JPanel pBotones(){
        JPanel p = new JPanel();
        //p.setLayout(new GridLayout(1,3,40,0));
        p.setOpaque(false);
        p.add(btnVolver);
        p.add(btnImprimir);
        p.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
        p.setOpaque(false);
        return p;
    }
    
    private ImageIcon cambiarTamañoIcono(ImageIcon icon, int ancho, int alto){
        Image img = icon.getImage();
        Image iconoGrande = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(iconoGrande);
    }
    
    public void limpiar(){
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.removeRow(i);
        }
        modeloTabla.setRowCount(0);
    }
    
    private void inicializar(){
        icoAtras = new ImageIcon(System.getProperty("user.dir")+"/img/icons/login/atras.png");
        icoImprimir = new ImageIcon(System.getProperty("user.dir")+"/img/icons/imprimir.png");
        
        btnVolver = new JButton(cambiarTamañoIcono(icoAtras,60,60));
        btnImprimir = new JButton(cambiarTamañoIcono(icoImprimir,60,60));
        
        btnVolver.setBackground(Color.WHITE);
        btnImprimir.setBackground(Color.WHITE);
        
        modeloTabla = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
            };
        modeloTabla.addColumn("No. credito");
        modeloTabla.addColumn("C.C. Cliente");
        modeloTabla.addColumn("Nombre Cliente");
        modeloTabla.addColumn("Dirección Casa");
        modeloTabla.addColumn("Teléfono Casa");
        modeloTabla.addColumn("Dirección Trabajo");
        modeloTabla.addColumn("Teléfono Trabajo");
        
        tabla = new JTable(modeloTabla);
        scroll = new JScrollPane(tabla);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }
    
    public void registrarEscuchas(controlador c, controladorDeudores cr){
        btnImprimir.addActionListener(cr);
        btnVolver.addActionListener(c);
    }
    
}
