/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Jeremi_Sanchez
 */
public class formulas {
    
    String datosUsuario[] = new String[3];

    public String[] getDatosUsuario() {
        return datosUsuario;
    }
    
    public int calcularValorTotal(Object valor, Object interes){
        return Integer.parseInt(String.valueOf(valor)) + (Integer.parseInt(String.valueOf(valor)) * Integer.parseInt(String.valueOf(interes)))/100;        
    }
    
    public int calcularValorCuota(int valor, int cuotas){
        return (valor/cuotas);
    }
    
    public int calcularSaldo(int abono, int total){
        if((total - abono) < 100){
            return 0;
        }else{
            return total - abono;
        }
    }
    
    public ArrayList<String> calcularRangoFechas(Object fecha, Object cuotas){
        ArrayList<String> arreglo = new ArrayList();
        int anio = Integer.parseInt(fecha.toString().substring(0,4));
        int mes  = Integer.parseInt(fecha.toString().substring(5, 7));
        int dia  = Integer.parseInt(fecha.toString().substring(8, 10));
        String diaP;
        String mesP;
        String anioP;
        int i = 0;
        while(i < Integer.parseInt(String.valueOf(cuotas))){
            //if(dia > 1 && 16 > dia){
            if(dia > 8 && 23 > dia){
                diaP = "30";
                dia = 30;
            }
            else{
                diaP = "15";
                dia = 15;
                mes = mes + 1;
                if(mes > 12){
                    anio = anio + 1;
                    mes = 01;
                }
            }
            if(String.valueOf(mes).length() == 1){
                mesP = "0"+String.valueOf(mes);
            }else{
                mesP = String.valueOf(mes);
            }
            anioP = String.valueOf(anio);

            arreglo.add(anioP+"-"+mesP+"-"+diaP);
            i++;
        }
        
        return arreglo;
    }
    
    
    public int calcularRangoFechasDeudores(String fecha, int cuotas){
        Calendar fech = new GregorianCalendar();
        int diaActual = fech.get(Calendar.DAY_OF_MONTH);
        int mesActual = fech.get(Calendar.MONTH)+1;
        int anioActual = fech.get(Calendar.YEAR);
        
        int ultimoDia, ultimoMes, ultimoAnio;
        String fechaComparar = "";
        
        ArrayList<String> listaFechas = new ArrayList(calcularRangoFechas(fecha,cuotas));
        for (int i = 0; i < listaFechas.size(); i++) {
            //System.out.println("fecha: "+listaFechas.get(i));
            int anio = Integer.parseInt(listaFechas.get(i).substring(0,4));
            int mes  = Integer.parseInt(listaFechas.get(i).substring(5, 7));
            int dia  = Integer.parseInt(listaFechas.get(i).substring(8, 10));
            
            ultimoDia = dia - diaActual;
            ultimoMes = mes - mesActual;
            ultimoAnio = anio - anioActual;
            //System.out.println("dia: "+ ultimoDia+" mes: "+ultimoMes+" anio: "+ultimoAnio);
            
            if(ultimoMes == 0 && ultimoDia <= 0 && ultimoAnio == 0){
                //System.out.println("fecha Cuota actual: "+anio+"-"+mes+"-"+dia);
                if(String.valueOf(mes).length() == 1){
                    fechaComparar = String.valueOf(anio)+"-0"+String.valueOf(mes)+"-"+String.valueOf(dia);
                }else{
                    fechaComparar = String.valueOf(anio)+"-"+String.valueOf(mes)+"-"+String.valueOf(dia);
                }
                break;
            }else{
                if(i == listaFechas.size()-1){
                    fechaComparar = listaFechas.get(listaFechas.size()-1);
                }
            }
            
        }
        int numCuota = 0;
        for (int i = 0; i < listaFechas.size(); i++) {
            if (fechaComparar == null ? listaFechas.get(i) == null : fechaComparar.equals(listaFechas.get(i))) {
                numCuota = i;
                //System.out.println(". "+fechaComparar+ " - "+listaFechas.get(i));
            }
            //System.out.println("asdfsdf "+fechaComparar+ " - "+listaFechas.get(i));
        }
        //System.out.println("cuota: "+numCuota);
        return numCuota;
    }
    
    public int sumaCuotas(ArrayList<Object[]> lista){
        int total = 0;
        for (int i = 0; i < lista.size(); i++) {
            total = total + Integer.parseInt(String.valueOf(lista.get(i)[0]));
        }
        return total;
    }
    
     public boolean verificarDeudor(ArrayList<Object[]> listaAbonos, String fecha, int cuotas, Object valor, Object interes){
         boolean es = false;
         
         int totalAbonos = sumaCuotas(listaAbonos);
         System.out.println("total abonos "+totalAbonos);
         int nCuotas = calcularRangoFechasDeudores(fecha, cuotas);
         System.out.println("nCuotas" +nCuotas);
         int valorCuota = calcularValorCuota(calcularValorTotal(valor,interes), cuotas);
         System.out.println("valorCuota "+valorCuota);
         if(totalAbonos <((nCuotas+1)*valorCuota)){
             es = true;
         }
         return es;
     }
}