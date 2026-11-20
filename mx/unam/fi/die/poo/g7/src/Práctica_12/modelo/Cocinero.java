// Importes
package mx.unam.fi.die.poo.g7.src.Práctica_12.modelo;
import mx.unam.fi.die.poo.g7.src.Práctica_12.vista.Vista;

public class Cocinero implements Runnable {

    private String nombre;
    private ColaPedidos colaCocinero; // Cola de pedidos ya validados y listos para el cocinero
    private Vista vista;
    private volatile boolean activo = true; // Volatile para que sea visible para todos los hilos inmediatamente

    public Cocinero(String nombre, ColaPedidos colaCocinero, Vista vista) {
        this.nombre = nombre;
        this.colaCocinero = colaCocinero;
        this.vista = vista;
    }

    public void descansar() {
        activo = false;
    }

    @Override
    public void run() {
        while (activo || colaCocinero.obtenerTamaño() > 0) {
            try {
                Pedido pedido = colaCocinero.tomarPedido(); // Tomamos el primer pedido de la cola del cocinero
                vista.mostrar("COCINERO", "Cocinando pedido: " + nombre + " esta cocinando el pedido " + pedido.getPlato() + " de " + pedido.getCliente());
                Thread.sleep((int) (Math.random() * 2000)); // Simular tiempo de cocinado
                vista.mostrar("COCINERO", "Pedido cocinado: " + nombre + " terminó el pedido " + pedido.getPlato() + " de " + pedido.getCliente());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Pausamos el hilo si es interrumpido
            }
        }
    }
}