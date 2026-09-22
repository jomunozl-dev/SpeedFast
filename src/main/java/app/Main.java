/**
 *
 * @author Jorge Munoz Leon
 */

package app;

import gui.VentanaPrincipal;
import javax.swing.SwingUtilities;
import servicio.ZonaDeCarga;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ZonaDeCarga zona = new ZonaDeCarga();
            VentanaPrincipal ventana = new VentanaPrincipal(zona);
            ventana.setVisible(true);
        });
    }
}