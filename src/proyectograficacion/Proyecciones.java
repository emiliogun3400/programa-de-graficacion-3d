package proyectograficacion;

import java.awt.Point;

public class Proyecciones {

    public static Point proyectar(Punto3D p, int centroX, int centroY, int escalaPixeles, String tipoVista) {
        
        double anguloZ = Math.toRadians(45);
        double cos = Math.cos(anguloZ);
        double sin = Math.sin(anguloZ);

        double screenX = 0;
        double screenY = 0;

        if ("RotacionY".equals(tipoVista)) {
            screenX = p.z - (p.y * cos);
            screenY = p.x - (p.y * sin);
        } else if ("RotacionZ".equals(tipoVista)) {
            screenX = p.x - (p.z * cos);
            screenY = p.y - (p.z * sin);
        } else {
            screenX = p.y - (p.x * cos);
            screenY = p.z - (p.x * sin);
        }

        int xPantalla = (int) Math.round(centroX + (screenX * escalaPixeles));
        int yPantalla = (int) Math.round(centroY - (screenY * escalaPixeles)); 
        
        return new Point(xPantalla, yPantalla);
    }
}