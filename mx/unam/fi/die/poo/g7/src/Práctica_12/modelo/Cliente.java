// Importes
package mx.unam.fi.die.poo.g7.src.Práctica_12.modelo;
import mx.unam.fi.die.poo.g7.src.Práctica_12.vista.Vista;

public class Cliente implements Runnable {

    private String nombre;
    private ColaPedidos cola;
    private Vista vista;
    private String[] platos = {"Taco", "Enchilada", "Quesadilla", "Tamal", "Sopes"};

    public Cliente(String nombre, ColaPedidos cola, Vista vista) {
        this.nombre = nombre;
        this.cola = cola;
        this.vista = vista;
    }

    @Override
    public void run() {
        try {
            String plato = platos[(int) (Math.random() * platos.length)];
            vista.mostrar("CLIENTE", "Agregando pedido: " + nombre + " hizo un pedido de " + plato);
            cola.agregarPedido(new Pedido(nombre, plato));
            vista.mostrar("COLA", "Pedido agregado: " + " se registró el pedido  " + plato + " de " + nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Pausamos el hilo si es interrumpido
        }
    }
}