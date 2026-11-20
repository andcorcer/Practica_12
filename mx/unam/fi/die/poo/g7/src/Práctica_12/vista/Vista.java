// Importes
package mx.unam.fi.die.poo.g7.src.Práctica_12.vista;

public class Vista {
    public synchronized void mostrar(String funcion, String mensaje) {
        System.out.println(Thread.currentThread().getName() + " -> " + funcion + " -> " + mensaje);
    }
}