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

    // Cargamos los 12 pasos exactos para trazar y cerrar el prisma
    public void cargarPrismaTriangular() {
        vertices.clear(); 
        agregarPunto(2, 1, 3, 0); // 0: P1 (Inicia base)
        agregarPunto(8, 1, 3, 1); // 1: P2
        agregarPunto(2, 5, 3, 1); // 2: P3
        agregarPunto(2, 1, 3, 1); // 3: P1 (Cierra base)
        agregarPunto(2, 1, 6, 1); // 4: P4 (Sube poste 1)
        agregarPunto(8, 1, 6, 1); // 5: P5
        agregarPunto(2, 5, 6, 1); // 6: P6
        agregarPunto(2, 1, 6, 1); // 7: P4 (Cierra tapa)
        agregarPunto(8, 1, 3, 0); // 8: P2 (Salta)
        agregarPunto(8, 1, 6, 1); // 9: P5 (Sube poste 2)
        agregarPunto(2, 5, 3, 0); // 10: P3 (Salta)
        agregarPunto(2, 5, 6, 1); // 11: P6 (Sube poste 3)
    }
}