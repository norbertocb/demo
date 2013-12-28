package cajero; 

/**Clase para conectar a la base de datos 
 * @author Israel, Norberto
 */

import java.sql.*;
import java.util.ArrayList;

public class Conexion_db {
    
    /**Parametros de configuracion para conexion de base de datos en SQL Server
     * @param esta clase no recibe ningun parametro
     * @return esta clase no tiene algun valor de retorno
     * 
     */

    private Statement stat;
    private Connection con;
    private ResultSet rs;
    private String usuario = "user=SOPTEC;";
    private String contrasena = "password=SOPTEC;";
    private String ruta = "jdbc:jtds:sqlserver://10.51.241.146:1433;";
    private String nombre_db = "databaseName=Cajero_Exa;";
    private PreparedStatement ps = null;
    
    public void conectar() {
        boolean a = false;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection(ruta + nombre_db + usuario + contrasena);
            stat = con.createStatement();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public ResultSet consultar(String sql) { //FATLA 
        try {
            rs = stat.executeQuery(sql);
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return rs;
    }

    public void cerrar() {
        try {

            if (rs!=null)
                rs.close();
            if (ps!=null)
                ps.close();
            stat.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void actualizar(String sql, int cant) {

        try {
            ps = con.prepareStatement(sql);
            //rs=stat.executeUpdate(sql);
            ps.setInt(1, cant);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex);
        }


    }
}
