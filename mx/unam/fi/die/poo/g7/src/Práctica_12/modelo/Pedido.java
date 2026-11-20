// Importes
package mx.unam.fi.die.poo.g7.src.Práctica_12.modelo;

public class Pedido {

    private String cliente;
    private String plato;

    public Pedido(String cliente, String plato) {
        this.cliente = cliente;
        this.plato = plato;
    }

    public String getCliente() {
        return cliente;
    }

    public String getPlato() {
        return plato;
    }

    @Override 
    public String toString() {
        return "Cliente: " + cliente + ", Plato: " + plato;
    }
}