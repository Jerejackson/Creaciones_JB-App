/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelo.consultas;
import modelo.formulas;
import vista.ventana;
import vista.ventanaConsultaCC;

/**
 *
 * @author Jeremi_Sanchez
 */
public class controlador implements ActionListener, Printable{

    ventana v;
    formulas f;
    consultas c;
    
    ventanaConsultaCC ventanaConsultaCC;
    
    File file;
    
    public controlador(ventana v, consultas c){
        this.v = v;
        this.c = c;
        f = new formulas();
        ventanaConsultaCC = new ventanaConsultaCC();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
    
        /*********************************************************************************
        ***********************************   LOGIN  *************************************
        **********************************************************************************/
        if(ae.getSource() == v.getLogin().getBtnIngresar()){
            validarInicioSesion();
        }
        
        if(ae.getSource() == v.getLogin().getBtnLimpiar()){
            v.getLogin().limpiar();
            //f.calcularRangoFechasDeudores("2020-11-16",19);
        }
        
        if(ae.getSource() == v.getLogin().getBtnCerrar()){
            System.exit(0);
        }
        
        /*********************************************************************************
        ***********************************   MENU  *************************************
        **********************************************************************************/
        
        if(ae.getSource() == v.getM().getBtnRegistrarCliente()){
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getRcli());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        if(ae.getSource() == v.getM().getBtnRegistrarCredito()){
            v.getRcre().getModeloCbb().removeAllElements();
            v.getRcre().getModeloCbb().addElement("Seleccionar No. Cuotas");
            Object o[][] = c.buscarDiferido();
            for (Object[] o1 : o) {
                v.getRcre().getModeloCbb().addElement(String.valueOf(o1[1]) + " al " + String.valueOf(o1[2]) + "%");
            }
            
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getRcre());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        if(ae.getSource() == v.getM().getBtnRegistrarEmpleado()){
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getRemp());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        if(ae.getSource() == v.getM().getBtnCambioCuotas()){
            ArrayList<Object[]> lista = new ArrayList(c.buscarInteresCuoats());
            for (int i = 0; i < lista.size(); i++) {
                v.getrIntC().getModeloTabla().addRow(new Object[]{lista.get(i)[1], lista.get(i)[2]});
            }
            
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getrIntC());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        if(ae.getSource() == v.getM().getBtnConsultarCredito()){
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getCcre());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        if(ae.getSource() == v.getM().getBtnConsultarDeudores()){
            buscarDeudores();
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getCdeu());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        if(ae.getSource() == v.getM().getBtnContratoVenta()){
            /*v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getCV());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();*/
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir")+"/documentos/");
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                try {
                    Desktop.getDesktop().print(file);
                } catch (IOException ex) {
                    Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if(ae.getSource() == v.getM().getBtnCobroJuridico()){
            /*v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getCJ());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();*/
            
        }
        if(ae.getSource() == v.getM().getBtnSalir()){
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getLogin());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        /*********************************************************************************
        *****************************   REGISTRAR CLIENTE  ********************************
        **********************************************************************************/
        
        if(ae.getSource() == v.getRcli().getBtnBuscar()){
            v.getRcli().habilitar();
            try{
                ArrayList<Object[]> lista = new ArrayList<>(c.buscarCliente(Integer.parseInt(v.getRcli().getTxtCc().getText())));
                try{
                    System.out.println(lista.get(0)[0]);
                }catch(IndexOutOfBoundsException ex){
                    Object i[] = {0};
                    lista.add(i);
                }

                if((Integer.parseInt(String.valueOf(lista.get(0)[0]))) == 0){
                    v.getRcli().setIdentificador(Integer.parseInt(String.valueOf(lista.get(0)[0])));
                    JOptionPane.showConfirmDialog(null, "Identificación No Encontrada", "Registro Usuario", JOptionPane.DEFAULT_OPTION);
                }else{

                    v.getRcli().setIdentificador(Integer.parseInt(String.valueOf(lista.get(0)[0])));
                    v.getRcli().getTxtNombre().setText(String.valueOf(lista.get(0)[2]));
                    v.getRcli().getTxtDireccionCasa().setText(String.valueOf(lista.get(0)[3]));
                    v.getRcli().getTxtDireccionTrabajo().setText(String.valueOf(lista.get(0)[4]));
                    v.getRcli().getTxtTelCasa().setText(String.valueOf(lista.get(0)[5]));
                    v.getRcli().getTxtCargo().setText(String.valueOf(lista.get(0)[6]));
                    v.getRcli().getTxtEmpresa().setText(String.valueOf(lista.get(0)[7]));
                    v.getRcli().getTxtTelTrabajo().setText(String.valueOf(lista.get(0)[8]));
                    
                    if("1".equals(String.valueOf(lista.get(0)[13]))){
                        v.getRcli().setIdPersonal(Integer.parseInt(String.valueOf(lista.get(0)[9])));
                        v.getRcli().getTxtCcRefPersonal().setText(String.valueOf(lista.get(0)[10]));
                        v.getRcli().getTxtNomRefPersonal().setText(String.valueOf(lista.get(0)[11]));
                        v.getRcli().getTxtTelCasaRefPersonal().setText(String.valueOf(lista.get(0)[14]));
                        v.getRcli().getTxtDireccionCasaRefPersonal().setText(String.valueOf(lista.get(0)[15]));
                        v.getRcli().getTxtDireccionTrabajoRefPersonal().setText(String.valueOf(lista.get(0)[16]));
                        v.getRcli().getTxtCargoRefPersonal().setText(String.valueOf(lista.get(0)[17]));
                        v.getRcli().getTxtEmpresaRefPersonal().setText(String.valueOf(lista.get(0)[18]));
                        v.getRcli().getTxtTelTrabajoRefPersonal().setText(String.valueOf(lista.get(0)[19]));
                        
                        v.getRcli().setIdFamiliar(Integer.parseInt(String.valueOf(lista.get(1)[9])));
                        v.getRcli().getTxtCcRefFamiliar().setText(String.valueOf(lista.get(1)[10]));
                        v.getRcli().getTxtNomRefFamiliar().setText(String.valueOf(lista.get(1)[11]));
                        v.getRcli().getTxtTelCasaRefFamiliar().setText(String.valueOf(lista.get(1)[14]));
                        v.getRcli().getTxtDireccionCasaRefFamiliar().setText(String.valueOf(lista.get(1)[15]));
                        v.getRcli().getTxtDireccionTrabajoRefFamiliar().setText(String.valueOf(lista.get(1)[16]));
                        v.getRcli().getTxtCargoRefFamiliar().setText(String.valueOf(lista.get(1)[17]));
                        v.getRcli().getTxtEmpresaRefFamiliar().setText(String.valueOf(lista.get(1)[18]));
                        v.getRcli().getTxtTelTrabajoRefFamiliar().setText(String.valueOf(lista.get(1)[19]));
                    }else{
                        v.getRcli().setIdPersonal(Integer.parseInt(String.valueOf(lista.get(1)[9])));
                        v.getRcli().getTxtCcRefPersonal().setText(String.valueOf(lista.get(1)[10]));
                        v.getRcli().getTxtNomRefPersonal().setText(String.valueOf(lista.get(1)[11]));
                        v.getRcli().getTxtTelCasaRefPersonal().setText(String.valueOf(lista.get(1)[14]));
                        v.getRcli().getTxtDireccionCasaRefPersonal().setText(String.valueOf(lista.get(1)[15]));
                        v.getRcli().getTxtDireccionTrabajoRefPersonal().setText(String.valueOf(lista.get(1)[16]));
                        v.getRcli().getTxtCargoRefPersonal().setText(String.valueOf(lista.get(1)[17]));
                        v.getRcli().getTxtEmpresaRefPersonal().setText(String.valueOf(lista.get(1)[18]));
                        v.getRcli().getTxtTelTrabajoRefPersonal().setText(String.valueOf(lista.get(1)[19]));
                        
                        v.getRcli().setIdFamiliar(Integer.parseInt(String.valueOf(lista.get(0)[9])));
                        v.getRcli().getTxtCcRefFamiliar().setText(String.valueOf(lista.get(0)[10]));
                        v.getRcli().getTxtNomRefFamiliar().setText(String.valueOf(lista.get(0)[11]));
                        v.getRcli().getTxtTelCasaRefFamiliar().setText(String.valueOf(lista.get(0)[14]));
                        v.getRcli().getTxtDireccionCasaRefFamiliar().setText(String.valueOf(lista.get(0)[15]));
                        v.getRcli().getTxtDireccionTrabajoRefFamiliar().setText(String.valueOf(lista.get(0)[16]));
                        v.getRcli().getTxtCargoRefFamiliar().setText(String.valueOf(lista.get(0)[17]));
                        v.getRcli().getTxtEmpresaRefFamiliar().setText(String.valueOf(lista.get(0)[18]));
                        v.getRcli().getTxtTelTrabajoRefFamiliar().setText(String.valueOf(lista.get(0)[19]));
                    }
                    
                }
                lista.clear();
            }catch(NumberFormatException ex){
                JOptionPane.showConfirmDialog(null, "Solo es posible registrar o buscar valores numéricos en la Identificación", "Busqueda de Identificación", JOptionPane.DEFAULT_OPTION);
            }
        }
        
        if(ae.getSource() == v.getRcli().getBtnGuardar()){
            if(v.getRcli().getTxtCc().getText().isEmpty() || v.getRcli().getTxtNombre().getText().isEmpty() || v.getRcli().getTxtDireccionCasa().getText().isEmpty() || v.getRcli().getTxtTelCasa().getText().isEmpty() ||
               v.getRcli().getTxtNomRefFamiliar().getText().isEmpty() || v.getRcli().getTxtTelCasaRefFamiliar().getText().isEmpty() || v.getRcli().getTxtCargoRefFamiliar().getText().isEmpty() || 
               v.getRcli().getTxtNomRefPersonal().getText().isEmpty() || v.getRcli().getTxtTelCasaRefPersonal().getText().isEmpty() || v.getRcli().getTxtCargoRefPersonal().getText().isEmpty() ){
                JOptionPane.showConfirmDialog(null, "Los Siguientes Campos deben estar Diligenciados: \n Cliente -> Identificación, Nombre, Direccion de Residencia y Telefono \n Referencias -> Nombre, Telefonos, Cargo", "Error de Registro", JOptionPane.DEFAULT_OPTION); 
            }else{
                try{
                    if(v.getRcli().getIdentificador() != 0){
                        switch(JOptionPane.showConfirmDialog(null,"La Identificación del Cliente ya Existe ¿Desea Actualizar sus valores?", "Actualizar Empleado", 
                                        JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null)){
                            case 0: {
                                
                                if(v.getRcli().getIdentificador() != Integer.parseInt(v.getRcli().getTxtCc().getText())){
                                    
                                }
                                c.editarCliente(v.getRcli().getIdentificador(), 
                                        Integer.parseInt(v.getRcli().getTxtCc().getText()), 
                                        v.getRcli().getTxtNombre().getText(), 
                                        v.getRcli().getTxtDireccionCasa().getText(),
                                        v.getRcli().getTxtDireccionTrabajo().getText(),
                                        v.getRcli().getTxtTelCasa().getText(),
                                        v.getRcli().getTxtCargo().getText(),
                                        v.getRcli().getTxtEmpresa().getText(),
                                        v.getRcli().getTxtTelTrabajo().getText(),
                                        
                                        v.getRcli().getIdPersonal(),
                                        Integer.parseInt(v.getRcli().getTxtCcRefPersonal().getText()),
                                        v.getRcli().getTxtNomRefPersonal().getText(),
                                        v.getRcli().getTxtDireccionCasaRefPersonal().getText(),
                                        v.getRcli().getTxtDireccionTrabajoRefPersonal().getText(),
                                        v.getRcli().getTxtTelCasaRefPersonal().getText(),
                                        v.getRcli().getTxtCargoRefPersonal().getText(),
                                        v.getRcli().getTxtEmpresaRefPersonal().getText(),
                                        v.getRcli().getTxtTelTrabajoRefPersonal().getText(),
                                        
                                        v.getRcli().getIdFamiliar(),
                                        Integer.parseInt(v.getRcli().getTxtCcRefFamiliar().getText()),
                                        v.getRcli().getTxtNomRefFamiliar().getText(),
                                        v.getRcli().getTxtDireccionCasaRefFamiliar().getText(),
                                        v.getRcli().getTxtDireccionTrabajoRefFamiliar().getText(),
                                        v.getRcli().getTxtTelCasaRefFamiliar().getText(),
                                        v.getRcli().getTxtCargoRefFamiliar().getText(),
                                        v.getRcli().getTxtEmpresaRefFamiliar().getText(),
                                        v.getRcli().getTxtTelTrabajoRefFamiliar().getText()
                                
                                );
                                JOptionPane.showConfirmDialog(null, "Datos Actualizados Correctamente", "Registro Usuario", JOptionPane.DEFAULT_OPTION); 
                                v.getRcli().limpiar();
                            }
                                break;
                        }
                    }
                    else{
                        
                        int result = c.guardarCliente(Integer.parseInt(v.getRcli().getTxtCc().getText()), 
                                        v.getRcli().getTxtNombre().getText(), 
                                        v.getRcli().getTxtDireccionCasa().getText(),
                                        v.getRcli().getTxtDireccionTrabajo().getText(),
                                        v.getRcli().getTxtTelCasa().getText(),
                                        v.getRcli().getTxtCargo().getText(),
                                        v.getRcli().getTxtEmpresa().getText(),
                                        v.getRcli().getTxtTelTrabajo().getText(),
                                        
                                        Integer.parseInt(v.getRcli().getTxtCcRefPersonal().getText()),
                                        v.getRcli().getTxtNomRefPersonal().getText(),
                                        v.getRcli().getTxtDireccionCasaRefPersonal().getText(),
                                        v.getRcli().getTxtDireccionTrabajoRefPersonal().getText(),
                                        v.getRcli().getTxtTelCasaRefPersonal().getText(),
                                        v.getRcli().getTxtCargoRefPersonal().getText(),
                                        v.getRcli().getTxtEmpresaRefPersonal().getText(),
                                        v.getRcli().getTxtTelTrabajoRefPersonal().getText(),
                                        
                                        Integer.parseInt(v.getRcli().getTxtCcRefFamiliar().getText()),
                                        v.getRcli().getTxtNomRefFamiliar().getText(),
                                        v.getRcli().getTxtDireccionCasaRefFamiliar().getText(),
                                        v.getRcli().getTxtDireccionTrabajoRefFamiliar().getText(),
                                        v.getRcli().getTxtTelCasaRefFamiliar().getText(),
                                        v.getRcli().getTxtCargoRefFamiliar().getText(),
                                        v.getRcli().getTxtEmpresaRefFamiliar().getText(),
                                        v.getRcli().getTxtTelTrabajoRefFamiliar().getText()
                                    );
                        switch (result) {
                            case 0:
                                JOptionPane.showConfirmDialog(null, "Registro Exitoso", "Registro Usuario", JOptionPane.DEFAULT_OPTION);
                                v.getRcli().limpiar();
                                break;
                            case 1:
                                JOptionPane.showConfirmDialog(null, "La Referencia ya Existe en la Base de Datos", "Error En El Registro", JOptionPane.DEFAULT_OPTION);
                                break; 
                            default:
                                JOptionPane.showConfirmDialog(null, "El Cliente ya Existe en la Base de Datos", "Error En El Registro", JOptionPane.DEFAULT_OPTION);
                                break;
                        }
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showConfirmDialog(null, "Las Cédulas deben ser Valores Numéricos", "Usuario", JOptionPane.DEFAULT_OPTION);
                    v.getRcli().getTxtCcRefFamiliar().setText("0");
                    v.getRcli().getTxtCcRefPersonal().setText("0");
                }
            }
        }
        
        
        if(ae.getSource() == v.getRcli().getBtnLimpiar()){
            v.getRcli().limpiar();
        }
        
        if(ae.getSource() == v.getRcli().getBtnCerrar()){
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getM());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        /*********************************************************************************
        ******************************   REGISTRAR CREDITO  ******************************
        **********************************************************************************/
        
        if(ae.getSource() == v.getRcre().getBtnBuscar()){
            try{
                ArrayList<Object[]> lista = new ArrayList<>(c.buscarCredito(Integer.parseInt(v.getRcre().getTxtCodCredito().getText())));
                try{
                    System.out.println(lista.get(0)[0]);
                }catch(IndexOutOfBoundsException ex){
                    Object i[] = {0};
                    lista.add(i);
                }
                
                if((Integer.parseInt(String.valueOf(lista.get(0)[0]))) == 0){
                    v.getRcre().setCodCredito(Integer.parseInt(String.valueOf(lista.get(0)[0])));
                    JOptionPane.showConfirmDialog(null, "No. de Credito No Encontrado", "Registro Credito", JOptionPane.DEFAULT_OPTION);
                    v.getRcre().getTxtCodCredito().setEditable(false);
                    v.getRcre().getTxtCodCredito().setText("");
                }else{

                    v.getRcre().setCodCredito(Integer.parseInt(String.valueOf(lista.get(0)[0])));
                    v.getRcre().getJcbDiferido().setSelectedIndex(Integer.parseInt(String.valueOf(lista.get(0)[2])));
                    v.getRcre().getTxtValorProducto().setText(String.valueOf(lista.get(0)[3]));
                    v.getRcre().getTxtNomProducto().setText(String.valueOf(lista.get(0)[4]));
                    v.getRcre().getTxtIngresoCc().setText(String.valueOf(lista.get(0)[7]));
                    
                    v.getRcre().getTxtCc().setText(String.valueOf(lista.get(0)[7]));
                    v.getRcre().getTxtNombre().setText(String.valueOf(lista.get(0)[8]));
                    v.getRcre().getTxtDireccionCasa().setText(String.valueOf(lista.get(0)[9]));
                    v.getRcre().getTxtDireccionTrabajo().setText(String.valueOf(lista.get(0)[10]));
                    v.getRcre().getTxtTelCasa().setText(String.valueOf(lista.get(0)[11]));
                    v.getRcre().getTxtCargo().setText(String.valueOf(lista.get(0)[12]));
                    v.getRcre().getTxtEmpresa().setText(String.valueOf(lista.get(0)[13]));
                    v.getRcre().getTxtTelTrabajo().setText(String.valueOf(lista.get(0)[14]));
                    
                }
                lista.clear();
            }catch(NumberFormatException ex){
                JOptionPane.showConfirmDialog(null, "Solo es posible registrar o buscar valores numéricos en el No. de Credito", "Busqueda de Identificación", JOptionPane.DEFAULT_OPTION);
            }
        }
        
        if(ae.getSource() == v.getRcre().getBtnGuardar()){
            try{
                Object a[][] = c.buscarCedulas().clone();
                boolean existenciaCedula = false;
                for (Object[] a1 : a) {
                    if (Integer.parseInt(String.valueOf(a1[0])) == Integer.parseInt(v.getRcre().getTxtIngresoCc().getText())) {
                        existenciaCedula = true;
                    }
                }
                if(v.getRcre().getTxtNomProducto().getText().isEmpty() || v.getRcre().getTxtValorProducto().getText().isEmpty() || v.getRcre().getJcbDiferido().getSelectedIndex() == 0 || v.getRcre().getTxtIngresoCc().getText().isEmpty()){
                    JOptionPane.showConfirmDialog(null, "Todos los Campos Deben Estar Diligenciados", "Error de Registro", JOptionPane.DEFAULT_OPTION); 
                }else{
                    if(existenciaCedula == false){
                        JOptionPane.showConfirmDialog(null, " El Cliente no se Encuentra Registrado \n Debe Registrar Primero el Cliente y Despues el Credito", "Error de Registro", JOptionPane.DEFAULT_OPTION); 
                        v.getRcre().getTxtIngresoCc().requestFocus();
                    }else{
                        if(v.getRcre().getCodCredito() != 0){
                            switch(JOptionPane.showConfirmDialog(null,"¿Realmente Desea Actualizar los valores?", "Actualizar Credito", 
                                            JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null)){
                                case 0: {
                                    c.editarCredito(
                                    v.getRcre().getCodCredito(), 
                                    Integer.parseInt(v.getRcre().getTxtIngresoCc().getText()), 
                                    v.getRcre().getJcbDiferido().getSelectedIndex(), 
                                    Integer.parseInt(v.getRcre().getTxtValorProducto().getText()),
                                    v.getRcre().getTxtNomProducto().getText());
                                    JOptionPane.showConfirmDialog(null, "Datos Actualizados Correctamente", "Registro Credito", JOptionPane.DEFAULT_OPTION); 
                                    v.getRcre().limpiar();
                                }
                                    break;
                            }
                        }
                        else{
                            c.guardarCredito(Integer.parseInt(v.getRcre().getTxtIngresoCc().getText()), 
                            v.getRcre().getJcbDiferido().getSelectedIndex(), 
                            Integer.parseInt(v.getRcre().getTxtValorProducto().getText()), 
                            v.getRcre().getTxtNomProducto().getText());
                            JOptionPane.showConfirmDialog(null, "Registro Exitoso", "Registro Credito", JOptionPane.DEFAULT_OPTION); 
                            v.getRcre().limpiar();
                        }
                    }
                
                }
            }catch(NumberFormatException ex){
                JOptionPane.showConfirmDialog(null, "No es posible Guardar o Actualizar los Datos del Credito con valores Vacíos", "Credito", JOptionPane.DEFAULT_OPTION);
            }
        }
        
        if(ae.getSource() == v.getRcre().getBtnLimpiar()){
            v.getRcre().limpiar();
        }
        
        if(ae.getSource() == v.getRcre().getBtnCerrar()){
            v.getRcre().limpiar();
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getM());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        /*********************************************************************************
        ******************************   REGISTRAR EMPLEADO  ******************************
        **********************************************************************************/
        if(ae.getSource() == v.getRemp().getBtnBuscar()){
            try{
                ArrayList<Object[]> lista = new ArrayList<>(c.buscarEmpleado(Integer.parseInt(v.getRemp().getTxtId().getText())));
                try{
                    System.out.println(lista.get(0)[0]);
                }catch(IndexOutOfBoundsException ex){
                    Object i[] = {0};
                    lista.add(i);
                }

                if((Integer.parseInt(String.valueOf(lista.get(0)[0]))) == 0){
                    v.getRemp().setIdentificador(Integer.parseInt(String.valueOf(lista.get(0)[0])));
                    JOptionPane.showConfirmDialog(null, "Identificación No Encontrada", "Registro Usuario", JOptionPane.DEFAULT_OPTION);
                }else{

                    v.getRemp().setIdentificador(Integer.parseInt(String.valueOf(lista.get(0)[0])));
                    v.getRemp().getTxtNombre().setText(String.valueOf(lista.get(0)[1]));
                    v.getRemp().getTxtPass().setText(String.valueOf(lista.get(0)[2]));
                    v.getRemp().getTxtConfirmPass().setText(String.valueOf(lista.get(0)[2]));
                }
                lista.clear();
            }catch(NumberFormatException ex){
                JOptionPane.showConfirmDialog(null, "Solo es posible registrar o buscar valores numéricos en la Identificación", "Busqueda de Identificación", JOptionPane.DEFAULT_OPTION);
            }
        }
        
        if(ae.getSource() == v.getRemp().getBtnGuardar()){
            if(v.getRemp().getTxtId().getText().isEmpty() || v.getRemp().getTxtNombre().getText().isEmpty() || v.getRemp().getTxtPass().getText().isEmpty() || v.getRemp().getTxtConfirmPass().getText().isEmpty()){
                JOptionPane.showConfirmDialog(null, "Todos los Campos Deben Estar Diligenciados", "Error de Registro", JOptionPane.DEFAULT_OPTION); 
            }else{
                if(v.getRemp().getTxtPass().getText().equals(v.getRemp().getTxtConfirmPass().getText())){
                    try{
                        if(v.getRemp().getIdentificador() != 0){
                            switch(JOptionPane.showConfirmDialog(null,"La Identificación del Empleado ya Existe ¿Desea Actualizar sus valores?", "Actualizar Empleado", 
                                            JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null)){
                                case 0: {
                                    c.editarEmpleado(v.getRemp().getIdentificador(), Integer.parseInt(v.getRemp().getTxtId().getText()), v.getRemp().getTxtNombre().getText(), v.getRemp().getTxtConfirmPass().getText());
                                    JOptionPane.showConfirmDialog(null, "Datos Actualizados Correctamente", "Registro Usuario", JOptionPane.DEFAULT_OPTION); 
                                    v.getRemp().limpiar();
                                }
                                    break;
                            }
                        }
                        else{
                            if(c.guardarEmpleado(Integer.parseInt(v.getRemp().getTxtId().getText()), v.getRemp().getTxtNombre().getText(), v.getRemp().getTxtConfirmPass().getText()) == 0){
                                JOptionPane.showConfirmDialog(null, "Registro Exitoso", "Registro Usuario", JOptionPane.DEFAULT_OPTION); 
                                v.getRemp().limpiar();
                            }else{
                                JOptionPane.showConfirmDialog(null, "El Empleado ya Existe en la Base de Datos", "Error En El Registro", JOptionPane.DEFAULT_OPTION); 
                            }
                        }
                    }catch(NumberFormatException ex){
                        JOptionPane.showConfirmDialog(null, "No es posible Guardar o Actualizar los Datos del Usuario con valores Vacíos", "Usuario", JOptionPane.DEFAULT_OPTION);
                    }
                }else{
                    JOptionPane.showConfirmDialog(null, "Las Contraseñas no Coinciden", "Error de Registro", JOptionPane.DEFAULT_OPTION); 
                }
            }
            
        }
        
        if(ae.getSource() == v.getRemp().getBtnLimpiar()){
            v.getRemp().limpiar();
        }
        
        if(ae.getSource() == v.getRemp().getBtnCerrar()){
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getM());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        /*********************************************************************************
        ******************************   CONSULTAR CREDITO  ******************************
        **********************************************************************************/
        
        if(ae.getSource() == v.getCcre().getBtnCambioCc()){
            v.getCcre().cambioCC();
        }
        
        if(ae.getSource() == v.getCcre().getBtnCambioCre()){
            v.getCcre().cambioCredito();
        }
        
        if(ae.getSource() == v.getCcre().getBtnBuscarCredito()){
            mostrarClienteCredito();
        }
        
        if(ae.getSource() == v.getCcre().getBtnBuscarCedula()){
            try{
                ArrayList<Object[]> lista = c.buscarCreditoClienteTabla(Integer.parseInt(v.getCcre().getTxtNumCedula().getText()));
                if("0".equals(lista.get(0)[0].toString())){
                    JOptionPane.showConfirmDialog(null, "La Identificación de Cliente NO Existe", "Error de Consulta", JOptionPane.DEFAULT_OPTION); 
                }else{
                    ventanaConsultaCC.setVisible(true);
                    ventanaConsultaCC.getModeloCbb().removeAllElements();
                    for(int i = 0; i < lista.size(); i++){
                        ventanaConsultaCC.getModeloCbb().addElement(lista.get(i)[0]);
                    }
                    ventanaConsultaCC.registrarEscuchas(this);
                    Object a[] = new Object[2];
                    for(int i = 0; i < lista.size(); i++){
                        ventanaConsultaCC.getModelo().addRow(lista.get(i));
                    }
                    v.getCcre().cambioCredito();
                }
            }catch(NumberFormatException ex){
                JOptionPane.showConfirmDialog(null, "La Cédula Solo Debe Contener Valores Numéricos", "Error de Consulta", JOptionPane.DEFAULT_OPTION); 
            }
        }
        
        if(ae.getSource() == v.getCcre().getBtnGuardar()){
            if(v.getCcre().getModelo().getRowCount() > 0){
                for (int i = 0; i < v.getCcre().getModelo().getRowCount(); i++) {
                    if(!"".equals(String.valueOf(v.getCcre().getModelo().getValueAt(i, 3)))){
                        int abono = 0;
                        if(v.getCcre().getModelo().getValueAt(i, 3).toString().length() < 3){
                            abono = Integer.parseInt(v.getCcre().getModelo().getValueAt(i, 3).toString());
                        }else{
                            if("$ ".equals(v.getCcre().getModelo().getValueAt(i, 3).toString().substring(0, 2))){
                                abono = Integer.parseInt(v.getCcre().getModelo().getValueAt(i, 3).toString().substring(2));
                            }
                            else{
                                abono = Integer.parseInt(v.getCcre().getModelo().getValueAt(i, 3).toString());
                            }
                        }
                        
                        SimpleDateFormat formato=new SimpleDateFormat("YYYY-MM-DD");
                        String fecha=formato.format(new java.util.Date());
                        
                        if("".equals(v.getCcre().getModelo().getValueAt(i, 2).toString())){

                            fecha = "2021-03-17";
                        }else{
                            //fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date(String.valueOf(v.getCcre().getModelo().getValueAt(i, 2))));
                            fecha = String.valueOf(v.getCcre().getModelo().getValueAt(i, 2));
                            
                        }
                        
                        if("".equals(String.valueOf(v.getCcre().getModelo().getValueAt(i, 1)))){
                            
                            c.registrarCuota(Integer.parseInt(v.getCcre().getLblCodCredito().getText()),
                            abono
                            );
                            System.out.println("codcre "+Integer.parseInt(v.getCcre().getLblCodCredito().getText()));
                            System.out.println("abono "+abono);
                            System.out.println("decha "+ fecha);
                        }else{
                            c.guardarCuota(Integer.parseInt(String.valueOf(v.getCcre().getModelo().getValueAt(i, 1))),
                            abono,
                            fecha
                            );
                        }
                    }
                }
            }else{
                JOptionPane.showConfirmDialog(null, "La Tabla de Cuotas Esta Vacía", "Error de Registro", JOptionPane.DEFAULT_OPTION); 
            }
            mostrarClienteCredito();
        }
        
        if(ae.getSource() == v.getCcre().getBtnImprimir()){
            v.getCcre().pBusquedaVisible(false);
             try {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(this);
                job.printDialog();
                job.print();
             } catch (PrinterException ex) {
                Logger.getLogger(PrinterName.class.getName()).log(Level.SEVERE, null, ex);
             }
             
            v.getCcre().pBusquedaVisible(true);
        }
        /***********************************************************************************************
         * **************************************Registrar Cuotas Tasa Interes**************************
         ***********************************************************************************************/
        
        if(ae.getSource() == v.getrIntC().getBtnAtras()){
            v.getrIntC().limpiar();
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getM());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        if(ae.getSource() == v.getrIntC().getBtnGuardar()){
            if(v.getrIntC().getTxtNCuotas().getText().isEmpty() || v.getrIntC().getTxtInteres().getText().isEmpty()){
                JOptionPane.showConfirmDialog(null, "Los Campos de Número de Cuotas y \nTasa de Interes No Deben Estar Vacios", "Error de Registro", JOptionPane.DEFAULT_OPTION); 
            }else{
                try{
                    c.agregarInteres(Integer.parseInt(v.getrIntC().getTxtNCuotas().getText()), Integer.parseInt(v.getrIntC().getTxtInteres().getText()));
                    v.getrIntC().limpiar();

                    ArrayList<Object[]> lista = new ArrayList(c.buscarInteresCuoats());
                    for (int i = 0; i < lista.size(); i++) {
                        v.getrIntC().getModeloTabla().addRow(new Object[]{lista.get(i)[1], lista.get(i)[2]});
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showConfirmDialog(null, "Debe Ingresar Solo Valores Numéricos", "Error de Registro", JOptionPane.DEFAULT_OPTION); 
                    v.getrIntC().getTxtInteres().setText("");
                    v.getrIntC().getTxtNCuotas().setText("");
                }
            }
        }
        
        if(ae.getSource() == ventanaConsultaCC.getAceptar()){
            v.getCcre().getTxtNumCredito().setText(String.valueOf(ventanaConsultaCC.getJcbLista().getSelectedItem()));
            ventanaConsultaCC.dispose();
            mostrarClienteCredito();
        }
        
        if(ae.getSource() == v.getCcre().getBtnLimpiar()){
            v.getCcre().limpiar();
        }
        
        if(ae.getSource() == v.getCcre().getBtnVolver()){
            v.getCcre().limpiar();
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getM());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        /*********************************************************************************
        ***************************** CONSULTAR DEUDORES ********************************
        **********************************************************************************/
        
        if(ae.getSource() == v.getCdeu().getBtnVolver()){
            v.getCdeu().limpiar();
            v.getpPrincipal().removeAll();
            v.getpPrincipal().add(v.getM());
            v.getpPrincipal().revalidate();
            v.getpPrincipal().repaint();
        }
        
        
    }
    
    private void validarInicioSesion(){
        if(!"".equals(v.getLogin().getTxtContrasena().getText()) && !"".equals(v.getLogin().getTxtUsuario().getText())){
            try{
                
            
                Object temp[] = new Object[3];
                temp = c.validarSesion(Integer.parseInt(v.getLogin().getTxtUsuario().getText()), v.getLogin().getTxtContrasena().getText()).clone();
                if(temp[2] == null){
                    JOptionPane.showConfirmDialog(null, "El Usuario No Se Encuentra Registrado \n Verifique que Ingresó Correctamente los Valores", "Inicio de Sesión", JOptionPane.DEFAULT_OPTION);
                }else{
                        f.getDatosUsuario()[0] = v.getLogin().getTxtUsuario().getText();
                        f.getDatosUsuario()[1] = String.valueOf(temp[1]);
                        f.getDatosUsuario()[2] = String.valueOf(temp[2]);

                        v.getpPrincipal().removeAll();
                        v.getpPrincipal().add(v.getM());
                        v.getpPrincipal().revalidate();
                        v.getpPrincipal().repaint();
                    }
            }catch(NumberFormatException ex){
                JOptionPane.showConfirmDialog(null, "El Capo Usuario Solo Permite Valores Numéricos", "Inicio de Sesión", JOptionPane.DEFAULT_OPTION);
                v.getLogin().getTxtUsuario().requestFocus();
            }
        }else{
            JOptionPane.showConfirmDialog(null, "El Campo Usuario y/o Contraseña Está Vacío", "Inicio de Sesión", JOptionPane.DEFAULT_OPTION);
            v.getLogin().getTxtUsuario().requestFocus();
        }
    }
    
    private void mostrarClienteCredito(){
        try{
            if("0".equals(c.buscarExistenciaCredito(Integer.parseInt(v.getCcre().getTxtNumCredito().getText())))){
                JOptionPane.showConfirmDialog(null, "El Número de Credito NO Existe", "Consultar Credito", JOptionPane.DEFAULT_OPTION);
            }else{
                ArrayList<Object[]> datos = new ArrayList(c.buscarCreditoCliente(Integer.parseInt(v.getCcre().getTxtNumCredito().getText())));
                v.getCcre().getLblCodCredito().setText(String.valueOf(datos.get(0)[0]));
                v.getCcre().getLblNombreCliente().setText(String.valueOf(datos.get(0)[5]));
                v.getCcre().getLblCcCliente().setText(String.valueOf(datos.get(0)[4]));
                v.getCcre().getLblEmpresa().setText(String.valueOf(datos.get(0)[10])); 
                v.getCcre().getLblDirTrabajo().setText(String.valueOf(datos.get(0)[7])); 

                v.getCcre().getLblCargo().setText(String.valueOf(datos.get(0)[9])); 
                v.getCcre().getLblTelTrabajo().setText(String.valueOf(datos.get(0)[11])); 
                v.getCcre().getLblDirCasa().setText(String.valueOf(datos.get(0)[6])); 
                v.getCcre().getLblTelCasa().setText(String.valueOf(datos.get(0)[8])); 
                v.getCcre().getLblNombreProducto().setText(String.valueOf(datos.get(0)[2])); 
                v.getCcre().getLblValorProducto().setText(String.valueOf("$ "+datos.get(0)[1])); 
                v.getCcre().getLblFechaInicio().setText(String.valueOf(datos.get(0)[3])); 

                v.getCcre().getLblValorProductoFinal().setText("$ "+String.valueOf(f.calcularValorTotal(datos.get(0)[1],datos.get(0)[13]))); 
                v.getCcre().getLblDiferido().setText(String.valueOf(datos.get(0)[12] + " - " + datos.get(0)[13] + "%")); 
                v.getCcre().getLblValorCuota().setText("$ "+String.valueOf(f.calcularValorCuota(f.calcularValorTotal(datos.get(0)[1],datos.get(0)[13]), Integer.parseInt(String.valueOf(datos.get(0)[12])) ))); 

                //Limpia el modelo de la tabla y asigna el conteo a 0

                while (v.getCcre().getModelo().getRowCount() > 0){
                v.getCcre().getModelo().removeRow(0);
                }
                v.getCcre().getModelo().setRowCount(0);

                //traer los datos de las cuotas
                ArrayList<Object[]> matrizCuotas = new ArrayList(c.buscarCuotas(Integer.parseInt(v.getCcre().getTxtNumCredito().getText())));
                ArrayList<Object[]> matrizTabla = new ArrayList();
                ArrayList<String> fechas = new ArrayList(f.calcularRangoFechas(datos.get(0)[3], datos.get(0)[12]));
                for(int i = 0; i < matrizCuotas.size(); i ++){
                    int a = i;
                    int saldo = f.calcularValorTotal(datos.get(0)[1],datos.get(0)[13]);
                    int acumulado = 0;
                    while(0 <= a){
                        acumulado = Integer.parseInt(String.valueOf(matrizCuotas.get(a)[2])) + acumulado;
                        a--;
                    }
                    saldo = f.calcularSaldo(acumulado,saldo);
                    matrizTabla.add(new Object[]{
                        fechas.get(i),
                        String.valueOf(matrizCuotas.get(i)[0]),
                        String.valueOf(matrizCuotas.get(i)[1]),
                        "$ "+String.valueOf(matrizCuotas.get(i)[2]),
                        "$ "+saldo
                    });
                }
                int cont = 0;
                if(!matrizCuotas.isEmpty()){
                    //cont = (Integer.parseInt(String.valueOf(datos.get(0)[12]))-matrizCuotas.size());
                    cont = matrizCuotas.size();
                }
                for(int i = 0; i < (Integer.parseInt(String.valueOf(datos.get(0)[12]))-matrizCuotas.size()); i++){

                    matrizTabla.add(new Object[]{
                        fechas.get(cont+i),
                        "",
                        "",
                        "",
                        ""
                    });
                }
                for(int i = 0; i < matrizTabla.size(); i++){
                    v.getCcre().getModelo().addRow(matrizTabla.get(i));
                }
            }
        }catch(NumberFormatException ex){
             JOptionPane.showConfirmDialog(null, "El Número de Credito Solo Debe Contener Valores Numéricos", "Consultar Credito", JOptionPane.DEFAULT_OPTION);
             v.getCcre().getTxtNumCredito().setText("");
        }
    }
    
    
    private void buscarDeudores(){
        ArrayList<Object[]> matrizCreditos = new ArrayList(c.buscarCreditos());
        for (int i = 0; i < matrizCreditos.size(); i++) {
            ArrayList<Object[]> listaAbonos = new ArrayList(c.buscarAbonos(Integer.parseInt(String.valueOf(matrizCreditos.get(i)[0]))));
            boolean deudor = f.verificarDeudor(listaAbonos, String.valueOf(matrizCreditos.get(i)[5]), Integer.parseInt(String.valueOf(matrizCreditos.get(i)[2])), matrizCreditos.get(i)[3], matrizCreditos.get(i)[6]);
            if(deudor == true){
                ArrayList<Object[]> listaCliente = new ArrayList(c.buscarClienteDeudor(Integer.parseInt(String.valueOf(matrizCreditos.get(i)[0]))));
                v.getCdeu().getModeloTabla().addRow(listaCliente.get(0));
            }
        }
        
        
    }

    @Override
    public int print(Graphics grphcs, PageFormat pf, int i) throws PrinterException {
        
        if (i > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
         }
        
          Graphics2D g2d = (Graphics2D)grphcs;
          g2d.translate(pf.getImageableX(), pf.getImageableY());

          g2d.scale(0.45,0.45);
          
          v.print(grphcs);

          return PAGE_EXISTS;
    }
    
}
