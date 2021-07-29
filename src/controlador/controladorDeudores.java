/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.PrinterName;
import vista.ventana;

/**
 *
 * @author Jeremi_Sanchez
 */
public class controladorDeudores implements ActionListener, Printable{
    
    ventana v;
    
    public controladorDeudores(ventana v){
        this.v = v;
    }
    
    @Override
    public int print(Graphics grphcs, PageFormat pf, int i) throws PrinterException {
        
        if (i > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
         }
        
          Graphics2D g2d = (Graphics2D)grphcs;
          g2d.translate(pf.getImageableX(), pf.getImageableY());

          g2d.scale(0.55,0.55);
          
          v.print(grphcs);

          return PAGE_EXISTS;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       if(ae.getSource() == v.getCdeu().getBtnImprimir()){
            try {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(this);
                job.printDialog();
                job.print();
             } catch (PrinterException ex) {
                Logger.getLogger(PrinterName.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
}
