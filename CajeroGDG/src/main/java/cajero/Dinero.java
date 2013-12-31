
package cajero;

public class Dinero {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(float denominacion) {
        this.denominacion = denominacion;
    }
    
    public void muestra(){
        //System.out.println("Tipo\t Cantidad\t Denominacion");
        System.out.println(tipo + "\t" + cantidad + "\t\t" + denominacion);
    }
    private String tipo;
    private int cantidad;
    private float denominacion;
    
    
}
