package proyectograficacion;

import java.awt.Point;

public class Proyecciones {

    // Ahora recibe el "tipoVista" para saber cómo acomodar los ejes
    public static Point proyectar(Punto3D p, int centroX, int centroY, int escala, String tipoVista) {
        
        double anguloZ = Math.toRadians(45);
        double cos = Math.cos(anguloZ);
        double sin = Math.sin(anguloZ);
        
        double screenX = 0;
        double screenY = 0;

        // Permutación de ejes según el libro
        if ("RotacionY".equals(tipoVista)) {
            // Eje X arriba, Eje Z derecha, Eje Y hacia abajo-izq
            screenX = p.z - (p.y * cos);
            screenY = p.x - (p.y * sin);
        } else if ("RotacionZ".equals(tipoVista)) {
            // Eje Y arriba, Eje X derecha, Eje Z hacia abajo-izq
            screenX = p.x - (p.z * cos);
            screenY = p.y - (p.z * sin);
        } else {
            // NORMAL, Traslación, Escalación y Rotación X
            // Eje Z arriba, Eje Y derecha, Eje X hacia abajo-izq
            screenX = p.y - (p.x * cos);
            screenY = p.z - (p.x * sin);
        }

        int xPantalla = (int) Math.round(centroX + (screenX * escala));
        // Restamos porque la Y en Java crece hacia abajo en la pantalla
        int yPantalla = (int) Math.round(centroY - (screenY * escala)); 
        
        return new Point(xPantalla, yPantalla);
    }
}