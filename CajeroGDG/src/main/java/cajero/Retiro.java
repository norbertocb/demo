
package cajero;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;


public class Retiro {
    
    private double montoRetirar;
    public double getMontoRetirar() {
        return montoRetirar;
    }
    public void setMontoRetirar(double montoRetirar) {
        this.montoRetirar = montoRetirar;
    }
    
    private double montoRecalculado=0;
    private ArrayList<Dinero> listaDineroO;
    private ArrayList<Dinero> listaDineroDb = new ArrayList<Dinero>();
    private ArrayList<Dinero> listaDineroDespachar = new ArrayList<Dinero>();
    private boolean valida = false;

    public boolean isValida() {
        return valida;
    }

    private void setValida(boolean valida) {
        this.valida = valida;
    }

    public ArrayList<Dinero> getListaDineroDespachar() {
        return listaDineroDespachar;
    }

    public ArrayList<Dinero> getListaDineroDb() {
        return listaDineroDb;
    }
    
    public Retiro(){
        
    }
    
    public Retiro(ArrayList<Dinero> din, double monto){
        this.listaDineroO = din;
        this.montoRetirar = monto;
    }
    
    public void retirar(){
        Iterator it = listaDineroO.iterator();
        
        while(it.hasNext()){
            if(getMontoRetirar()==0.0)
                break;
            
            Object temp = (Dinero)it.next();
            Dinero din = (Dinero) temp;
            Dinero don = new Dinero();
            boolean b = false;
            int i=0;
            
            while(getMontoRetirar() >= din.getDenominacion() && din.getCantidad() > 0){           
                din.setCantidad(din.getCantidad()-1);       //DAMOS 1 BILLETE DE LA DENOMINACION ACTUAL Y LO RESTAMOS DEL TOTAL DE BILLETES
//                montoRecalculado += din.getDenominacion();  //QUITAMOS BILLETE DE LA DENOMINACION ACTUAL DE LA LISTA
                setMontoRetirar(getMontoRetirar() - din.getDenominacion());      //Recalcula la cantidad a dar(Monto - billete actual)
                b=true;
                i ++;
                don.setCantidad(i);
                don.setDenominacion(din.getDenominacion());
                don.setId(din.getId());
                don.setTipo(din.getTipo());
            }
            if(b){
                listaDineroDb.add(din);                      //LISTA PARA LA BASE DE DATOS
                listaDineroDespachar.add(don);                      //LISTA PARA ENTREGAR EN EL 
                din.muestra();
                don.muestra();
            }
        }
        if (getMontoRetirar() > 0){
            //EXISTE SALDO PERO NO HAY CAMBIO PARA DAR
            setValida(false);
        }else{
            //LA OPERACION FUE SATISFACTORIA
            setValida(true);
        }
        System.out.println("ITERACION");
    }
    

    
}
