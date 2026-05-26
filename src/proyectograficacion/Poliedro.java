package proyectograficacion;

import java.util.ArrayList;

public class Poliedro {
    public ArrayList<Punto3D> vertices;

    public Poliedro() {
        vertices = new ArrayList<>();
    }

    public void agregarPunto(double x, double y, double z, int codigo) {
        vertices.add(new Punto3D(x, y, z, codigo));
    }

    public void cargarPrismaTriangular() {
        vertices.clear(); 
        // --- LOS PRIMEROS 6 PUNTOS ---
        agregarPunto(2, 1, 3, 0); // 1: P1
        agregarPunto(8, 1, 3, 1); // 2: P2
        agregarPunto(2, 5, 3, 1); // 3: P3
        agregarPunto(2, 1, 6, 0); // 4: P4
        agregarPunto(8, 1, 6, 1); // 5: P5
        agregarPunto(2, 5, 6, 1); // 6: P6
        
        // --- LOS PUNTOS REPETIDOS DE CIERRE ---
        agregarPunto(2, 1, 6, 1); // 7: P4
        agregarPunto(2, 1, 3, 1); // 8: P1
        agregarPunto(2, 5, 3, 1); // 9: P3
        agregarPunto(2, 5, 6, 1); // 10: P6
        agregarPunto(8, 1, 3, 0); // 11: P2
        agregarPunto(8, 1, 6, 1); // 12: P5
    }
}