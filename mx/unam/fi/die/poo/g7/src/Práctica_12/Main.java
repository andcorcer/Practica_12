// importes 
package mx.unam.fi.die.poo.g7.src.Práctica_12;
import mx.unam.fi.die.poo.g7.src.Práctica_12.vista.Vista;
import mx.unam.fi.die.poo.g7.src.Práctica_12.controlador.ControladorRestaurante;

public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        ControladorRestaurante controlador = new ControladorRestaurante(vista);
        controlador.simular(5, 2, 3);
    }
}