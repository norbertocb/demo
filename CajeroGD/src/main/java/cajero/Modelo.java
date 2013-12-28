/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cajero;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author B938201
 */
public class Modelo {

    public void actualizarDb(ArrayList<Dinero> din) {

        ArrayList<Dinero> dine = new ArrayList<Dinero>();
        dine = din;
        Conexion_db actualiza = new Conexion_db();
        try {
            actualiza.conectar();
            Iterator it = dine.iterator();
            while (it.hasNext()) {
                Object temp = (Dinero) it.next();
                Dinero regUpdate = (Dinero) temp;
                String sql = "Update Dinero Set cantidad=? where Id=" + regUpdate.getId();
                actualiza.actualizar(sql, regUpdate.getCantidad());
            }
            actualiza.cerrar();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public double obtenerSaldoDb() {
        Conexion_db prueba = new Conexion_db();
        prueba.conectar();
        Conexion_db consulta = new Conexion_db();
        String montoTotal = "";
        try {
            consulta.conectar();
            ResultSet rs = consulta.consultar("SELECT SUM (Cantidad * Denominacion) as totalcajero from dbo.Dinero;");
            while (rs.next()) {
                montoTotal = rs.getString("totalcajero");
            }
            consulta.cerrar();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        double montoT = Double.parseDouble(montoTotal);
        return montoT;
    }

    public ArrayList<Dinero> monederoDigitalDb() {
        ArrayList<Dinero> listaOrignialDb = new ArrayList<Dinero>();

        Conexion_db prueba = new Conexion_db();
        prueba.conectar();

        Conexion_db consulta = new Conexion_db();
        try {
            consulta.conectar();
            ResultSet rs = consulta.consultar("select * From  dbo.Dinero Order By Denominacion DESC;");
            while (rs.next()) {
                Dinero money = new Dinero();
                money.setId(rs.getInt("Id"));
                money.setTipo(rs.getString("Tipo"));
                money.setCantidad(rs.getInt("Cantidad"));
                money.setDenominacion(rs.getFloat("Denominacion"));
                listaOrignialDb.add(money);
            }

            consulta.cerrar();
        } catch (Exception ex) {
            System.err.println(ex);
        }

        return listaOrignialDb;

    }
}
