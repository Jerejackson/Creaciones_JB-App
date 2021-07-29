/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Conexion.conexion;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jeremi_Sanchez
 */
public class consultas {
    
    /**********************************************************************************
     ************************************ LOGIN ***************************************
     * @param id
     * @param pass
     * @return 
    **********************************************************************************/
    
    public Object[] validarSesion(int id, String pass){
        ResultSet rs = conexion.ejecutarConsulta("SELECT * FROM `empleado` WHERE id = "+ id +" and contrasena = '"+pass+"'");
        Object n[] =new Object[3];
        try {
                
                while(rs.next()){
                    n[0] = rs.getObject(1); n[1] = rs.getObject(2); n[2] = rs.getObject(3);
                }
            } catch (SQLException ex) {
                System.out.println("Error de consulta Login: "+ ex);
            }
            return n;
    }
    /**********************************************************************************
     ************************************ CRUD EMPLEADO ***************************************
     * @param id
     * @return 
    **********************************************************************************/
    
    public ArrayList<Object[]> buscarEmpleado(int id){
        return  guardarListaArreglo(conexion.ejecutarConsulta("CALL buscarEmpleado("+id+")"));
    }
    
    public void editarEmpleado(int idOld,int idNew, String nombre, String contrasena){
        conexion.ejecutarActualizacion("UPDATE empleado SET id = "+idNew+", nombre = '"+nombre+"', contrasena = '"+contrasena+"' WHERE id = "+idOld);
    }
    
    public int guardarEmpleado(int id, String nombre, String pass){
        int i = 0;
        ResultSet rs = conexion.ejecutarConsulta("select registrarEmpleado("+id+",'"+nombre+"','"+pass+"')");
        try{
            while(rs.next()){
                i = rs.getInt(1);
            }
        }catch(SQLException e){
            
        }
        return i;
    }
        
        
    /**********************************************************************************
     ************************************ CRUD CLIENTE ***************************************
     * @param id
     * @return 
    **********************************************************************************/
    
    
    public ArrayList<Object[]> buscarCliente(int id){
        return  guardarListaArreglo(conexion.ejecutarConsulta("CALL buscarCliente("+id+")"));
    }    
        
    public void editarCliente(int codC, int ccC, String nomC, String dirCasaC , String dirTraC, String telCasaC,String cargoC, String empresaC, String telTraC,
                                int codP, int ccP, String nomP, String dirCasaP, String dirTraP, String telCasaP, String cargoP, String empresaP, String telTraP,
                                int codF, int ccF, String nomF, String dirCasaF, String dirTraF, String telCasaF, String cargoF, String empresaF, String telTraF){
        conexion.ejecutarActualizacion("CALL editarCliente("+codC+", "+ccC+", '"+nomC+"', '"+dirCasaC+"', '"+dirTraC+"', '"+telCasaC+"','"+cargoC+"', '"+empresaC+"', '"+telTraC+"'," +
                                        codP+", "+ccP+", '"+nomP+"', '"+dirCasaP+"', '"+dirTraP+"', '"+telCasaP+"', '"+cargoP+"', '"+empresaP+"', '"+telTraP+"',"+
                                        codF+", "+ccF+", '"+nomF+"', '"+dirCasaF+"', '"+dirTraF+"', '"+telCasaF+"', '"+cargoF+"', '"+empresaF+"', '"+telTraF+"')");
    }
    
    public int guardarCliente(int ccC, String nomC, String dirCasaC , String dirTraC, String telCasaC, String cargoC, String empresaC, String telTraC,
                            int ccP, String nomP, String dirCasaP, String dirTraP, String telCasaP, String cargoP, String empresaP, String telTraP,
                            int ccF, String nomF, String dirCasaF, String dirTraF, String telCasaF, String cargoF, String empresaF, String telTraF){
        int i = 0;
        ResultSet rs = conexion.ejecutarConsulta("select registrarCliente("+ccC+", '"+nomC+"', '"+dirCasaC+"', '"+dirTraC+"', '"+telCasaC+"', '"+cargoC+"', '"+empresaC+"', '"+telTraC+"', " +
                                                    ccP+", '"+nomP+"', '"+dirCasaP+"', '"+dirTraP+"', '"+telCasaP+"', '"+cargoP+"', '"+empresaP+"', '"+telTraP+"', "+
                                                    ccF+", '"+nomF+"', '"+dirCasaF+"', '"+dirTraF+"', '"+telCasaF+"', '"+cargoF+"', '"+empresaF+"', '"+telTraF+"') as buscar");

        try{
            while(rs.next()){
                i = rs.getInt(1);
            }
        }catch(SQLException e){
            
        }
        return i;
    }
    
    /**********************************************************************************
     ******************************* CRUD CREDITO **************************************
     * @return 
    **********************************************************************************/

    public Object[][] buscarDiferido(){
        return guardarArreglo(conexion.ejecutarConsulta("Select * from diferido"));
    }
    
    public ArrayList<Object[]> buscarCredito(int cod){
        return  guardarListaArreglo(conexion.ejecutarConsulta("CALL buscarCredito("+cod+")"));
    }
    
    public void editarCredito(int codCredito,int ccCliente, int diferido, int valor, String nomProducto){
        conexion.ejecutarActualizacion("CALL editarCredito("+codCredito+","+ccCliente+","+diferido+","+valor+",'"+nomProducto+"')");
    }
    
    public void guardarCredito(int ccCliente, int diferido, int valor, String nombreP){
        conexion.ejecutarActualizacion("CALL registrarCredito("+ccCliente+","+diferido+","+valor+",'"+nombreP+"')");
    }
    
    public Object[][] buscarCedulas(){
        return guardarArreglo(conexion.ejecutarConsulta("Select cedula from cliente"));
    }
    
    
    /**********************************************************************************
     **************************** CONSULTAR CREDITO ***********************************
     * @param cod
     * @return 
    **********************************************************************************/
    
    public ArrayList<Object[]> buscarCreditoCliente(int cod){
        return  guardarListaArreglo(conexion.ejecutarConsulta("SELECT cre.codCredito, cre.valorProducto, cre.nombreProducto, cre.fechaInicio, cli.cedula, cli.nombre, cli.direccionCasa, cli.direccionTrabajo, cli.telefono, cli.cargoTrabajo, cli.empresa, cli.telTrabajo, d.quincenas, d.porcentInteres from credito cre JOIN cliente cli JOIN diferido d ON cre.codCliente = cli.codCliente and cre.diferido = d.codigo WHERE cre.codCredito = "+cod));
    }
    
    public String buscarExistenciaCredito(int cod){
        int i = 0;
        ResultSet rs = conexion.ejecutarConsulta("select codCredito from credito where codCredito = "+cod);
        try{
            while(rs.next()){
                i = rs.getInt(1);
            }
        }catch(SQLException e){
            
        }
        return String.valueOf(i);
    }
    
    public ArrayList<Object[]> buscarCreditoClienteTabla(int cc){
        return  guardarListaArreglo(conexion.ejecutarConsulta("CALL buscarClienteTabla("+cc+")"));
    }
    
    public ArrayList<Object[]> buscarCuotas(int cod){
        return  guardarListaArreglo(conexion.ejecutarConsulta("SELECT * FROM cuota WHERE codCredito = "+cod));
    }
    
    public void guardarCuota(int codCuota, int abono, String fecha){
        conexion.ejecutarActualizacion("CALL registrarCuotas("+codCuota+","+abono+",\""+fecha+"\")");
    }
    
    public void registrarCuota(int codCredito, int abono){
        conexion.ejecutarActualizacion("Insert into cuota value(null,now(),"+abono+","+codCredito+")");
    }
    
    /**********************************************************************************
     **************************** CONSULTAR CREDITO ***********************************
     * @return 
    **********************************************************************************/
    
    public ArrayList<Object[]> buscarInteresCuoats(){
        return guardarListaArreglo(conexion.ejecutarConsulta("Select * From diferido"));
    }
    
    public void agregarInteres(int cuotas, int tasa){
        conexion.ejecutarActualizacion("INSERT INTO diferido VALUE(null,"+cuotas+","+tasa+")");
    }
    
    
    /**********************************************************************************
     **************************** CONSULTAR DEUDORES **********************************
     * @return 
    **********************************************************************************/
    
    public ArrayList<Object[]> buscarCreditos(){
        return guardarListaArreglo(conexion.ejecutarConsulta("Select c.codCredito, c.codCliente, d.quincenas, c.valorProducto, c.nombreProducto, c.fechaInicio, d.porcentInteres From credito c join diferido d on c.diferido = d.codigo"));
    }
    
    public ArrayList<Object[]> buscarAbonos(int codCredito){
        return guardarListaArreglo(conexion.ejecutarConsulta("select valorPago from cuota WHERE codCredito = "+ codCredito));
    } 
    
    public ArrayList<Object[]> buscarClienteDeudor(int codCredito){
        return guardarListaArreglo(conexion.ejecutarConsulta("select cr.codCredito, cl.cedula, cl.nombre, cl.direccionCasa, cl.telefono, cl.direccionTrabajo, cl.telTrabajo from cliente cl join credito cr on cl.codCliente = cr.codCliente WHERE codCredito = "+ codCredito));
    } 
    
    
    
    private Object[][] guardarArreglo(ResultSet rs){
        Object obj[][]=null;
        try{
            rs.last();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            int numFils =rs.getRow();
            obj=new Object[numFils][numCols];
            int j = 0;
            rs.beforeFirst();
            while (rs.next()){
                for (int i=0;i<numCols;i++){
                    obj[j][i]=rs.getObject(i+1);
                }
                j++;
            }
        }
        catch(SQLException ex){
            System.out.println("Error al consultar la base de datos: " + ex);
        }
        return obj;
    }
    
    private ArrayList<Object[]> guardarListaArreglo(ResultSet rs){
        ArrayList<Object[]> lista = new ArrayList<>();
        Object obj[][]=null;
        try{
            rs.last();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            int numFils =rs.getRow();
            obj=new Object[numFils][numCols];
            int j = 0;
            rs.beforeFirst();
            while (rs.next()){
                for (int i=0;i<numCols;i++){
                    obj[j][i]=rs.getObject(i+1);
                    //System.out.println(rs.getObject(i+1));
                }
                lista.add(obj[j]);
                j++;
            }
        }
        catch(SQLException ex){
            System.out.println("Error al consultar la base de datos: " + ex);
        }
        return lista;
    }
}
