// Importes
package mx.unam.fi.die.poo.g7.src.Práctica_12.modelo;
import mx.unam.fi.die.poo.g7.src.Práctica_12.vista.Vista;

public class Mesero implements Runnable {

    private String nombre;
    private ColaPedidos colaCliente; // Cola de pedidos de los clientes
    private ColaPedidos colaCocinero; // Cola de pedidos ya validados y listos para el cocinero
    private Vista vista;
    private volatile boolean activo = true; // Volatile para que sea visible para todos los hilos inmediatamente

    public Mesero(String nombre, ColaPedidos colaCliente, ColaPedidos colaCocinero, Vista vista) {
        this.nombre = nombre;
        this.colaCliente = colaCliente;
        this.colaCocinero = colaCocinero;
        this.vista = vista;
    }

    public void descansar() {
        activo = false;
    }

    @Override
    public void run() {
        while (activo || colaCliente.obtenerTamaño() > 0) {
            try {
                Pedido pedido = colaCliente.tomarPedido(); // Tomamos el primer pedido de la cola
                vista.mostrar("MESERO", "Atendiendo pedido: " + nombre + " esta atendiendo a " + pedido.getCliente() + " con pedido " + pedido.getPlato());
                Thread.sleep((int) (Math.random() * 2000)); // Simular tiempo de atención
                colaCocinero.agregarPedido(pedido); // Enviamos el pedido a la cola del cocinero
                vista.mostrar("MESERO", "Pedido atendido: " + nombre + " tomó el pedido " + pedido.getPlato() + " de " + pedido.getCliente());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Pausamos el hilo si es interrumpido
            }
        }
    }
}