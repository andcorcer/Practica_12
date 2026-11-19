// Importes
package mx.unam.fi.die.poo.g7.src.Práctica_12.controlador;
import mx.unam.fi.die.poo.g7.src.Práctica_12.vista.Vista;
import mx.unam.fi.die.poo.g7.src.Práctica_12.modelo.ColaPedidos;
import mx.unam.fi.die.poo.g7.src.Práctica_12.modelo.Mesero;
import mx.unam.fi.die.poo.g7.src.Práctica_12.modelo.Cliente;
import mx.unam.fi.die.poo.g7.src.Práctica_12.modelo.Cocinero;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;

public class ControladorRestaurante {

    private Vista vista;

    public ControladorRestaurante(Vista vista) {
        this.vista = vista;
    }

    public void simular(int numClientes, int numMeseros, int numCocineros) {
        ColaPedidos colaClientes = new ColaPedidos(numClientes);
        ColaPedidos colaCocineros = new ColaPedidos(numCocineros);

        List<Mesero> listaMeseros = new ArrayList<>();
        List<Cocinero> listaCocineros = new ArrayList<>();

        ExecutorService clientes = Executors.newFixedThreadPool(Math.max(1, numClientes));
        ExecutorService meseros = Executors.newFixedThreadPool(Math.max(1, numMeseros));
        ExecutorService cocineros = Executors.newFixedThreadPool(Math.max(1, numCocineros));

        vista.mostrar("SISTEMA", "Simulación iniciada");

        for (int i = 1; i <= numMeseros; i++) {
            Mesero mesero = new Mesero("Mesero #" + i, colaClientes, colaCocineros, vista);
            listaMeseros.add(mesero);
            meseros.submit(mesero);
        }

        for (int i = 1; i <= numCocineros; i++) {
            Cocinero cocinero = new Cocinero("Cocinero #" + i, colaCocineros, vista);
            listaCocineros.add(cocinero);
            cocineros.submit(cocinero);
        }

        for (int i = 1; i <= numClientes; i++) {
            Cliente cliente = new Cliente("Cliente #" + i, colaClientes, vista);
            clientes.submit(cliente);
        }

        try {
            clientes.shutdown();
            clientes.awaitTermination(30, TimeUnit.SECONDS);

            for (Mesero mesero : listaMeseros) {
                mesero.descansar();
            }
            colaClientes.esperarAColaVacia();
            Thread.sleep(3000); // Damos un tiempo para que los meseros terminen de atender los pedidos pendientes
            meseros.shutdownNow();
            meseros.awaitTermination(30, TimeUnit.SECONDS);

            for (Cocinero cocinero : listaCocineros) {
                cocinero.descansar();
            }
            colaCocineros.esperarAColaVacia();
            Thread.sleep(3000); // Damos un tiempo para que los cocineros terminen de atender los pedidos pendientes
            cocineros.shutdownNow();
            cocineros.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            clientes.shutdownNow();
            meseros.shutdownNow();
            cocineros.shutdownNow();
            vista.mostrar("SISTEMA", "Simulación interrumpida");
        }
    }
}
