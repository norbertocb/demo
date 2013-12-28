/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import cajero.Conexion_db;
import cajero.Dinero;
import cajero.Retiro;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author B938201
 */
public class CajeroTest {
    
    @Test  
    public void testdb() {
        Conexion_db Db = new Conexion_db();
        Db.conectar();
        ResultSet rs = Db.consultar("SELECT * FROM dbo.Dinero;");
        Db.cerrar();
        Assert.assertNotNull(rs);
    }
    
    @Test
    public void testRetiro() {
        ArrayList Dinero= new ArrayList();
        Dinero money = new Dinero();
        int cantidad = 2;
        int denominacion = 500;
        double montoR = 1500;
        double montoT = (double)cantidad * denominacion;
        

        money.setId(1);
        money.setTipo("Billete");
        money.setCantidad(cantidad);
        money.setDenominacion(denominacion);
        Dinero.add(money);

        Retiro ret = new Retiro(Dinero, montoR);
        ret.retirar();
 
        double mayor = ret.getMontoRetirar();
        boolean val = false;
        
        if (mayor >= 0.0)
            val = true;

        Assert.assertTrue("El valor a retirar nunca puede ser negativo",val);
    }

    
}
