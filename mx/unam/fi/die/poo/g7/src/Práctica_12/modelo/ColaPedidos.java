// Importes
package mx.unam.fi.die.poo.g7.src.Práctica_12.modelo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ColaPedidos {
    
    private BlockingQueue<Pedido> cola;

    public ColaPedidos(int capacidadMaxima) {
        this.cola = new LinkedBlockingQueue<>(capacidadMaxima);
    }

    public void agregarPedido(Pedido pedido) throws InterruptedException {
        cola.put(pedido);
    }

    public Pedido tomarPedido() throws InterruptedException {
        return cola.take();
    }

    public void esperarAColaVacia() throws InterruptedException {
        while (cola.size() > 0) {
            Thread.sleep(100);
        }
    }

    public int obtenerTamaño() {
        return cola.size();
    }
}