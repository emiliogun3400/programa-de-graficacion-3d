package proyectograficacion;

public class Transformaciones3D {

    // =========================================================
    // 1. MÉTODOS DIRECTOS (NORMALES)
    // =========================================================
    
    public static Poliedro trasladar(Poliedro figura, double tx, double ty, double tz) {
        Poliedro resultado = new Poliedro();
        for (Punto3D p : figura.vertices) {
            resultado.agregarPunto(p.x + tx, p.y + ty, p.z + tz, p.codigo);
        }
        return resultado;
    }

    public static Poliedro escalar(Poliedro figura, double sx, double sy, double sz) {
        Poliedro resultado = new Poliedro();
        for (Punto3D p : figura.vertices) {
            resultado.agregarPunto(p.x * sx, p.y * sy, p.z * sz, p.codigo);
        }
        return resultado;
    }

    public static Poliedro rotarX(Poliedro figura, double anguloGrados) {
        Poliedro resultado = new Poliedro();
        double rad = Math.toRadians(anguloGrados);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        for (Punto3D p : figura.vertices) {
            double nuevoY = (p.y * cos) - (p.z * sin);
            double nuevoZ = (p.y * sin) + (p.z * cos);
            resultado.agregarPunto(p.x, nuevoY, nuevoZ, p.codigo);
        }
        return resultado;
    }

    public static Poliedro rotarY(Poliedro figura, double anguloGrados) {
        Poliedro resultado = new Poliedro();
        double rad = Math.toRadians(anguloGrados);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        for (Punto3D p : figura.vertices) {
            double nuevoX = (p.x * cos) + (p.z * sin);
            double nuevoZ = -(p.x * sin) + (p.z * cos);
            resultado.agregarPunto(nuevoX, p.y, nuevoZ, p.codigo);
        }
        return resultado;
    }

    public static Poliedro rotarZ(Poliedro figura, double anguloGrados) {
        Poliedro resultado = new Poliedro();
        double rad = Math.toRadians(anguloGrados);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        for (Punto3D p : figura.vertices) {
            double nuevoX = (p.x * cos) - (p.y * sin);
            double nuevoY = (p.x * sin) + (p.y * cos);
            resultado.agregarPunto(nuevoX, nuevoY, p.z, p.codigo);
        }
        return resultado;
    }

    // =========================================================
    // 2. MÉTODOS INVERSOS (FORMALES)
    // =========================================================
    
    // Traslación Inversa: Matriz T^-1 (Resta los desplazamientos)
    public static Poliedro trasladarInversa(Poliedro figura, double tx, double ty, double tz) {
        Poliedro resultado = new Poliedro();
        for (Punto3D p : figura.vertices) {
            resultado.agregarPunto(p.x - tx, p.y - ty, p.z - tz, p.codigo);
        }
        return resultado;
    }

    // Escalación Inversa: Matriz S^-1 (Divide entre los factores)
    public static Poliedro escalarInversa(Poliedro figura, double sx, double sy, double sz) {
        Poliedro resultado = new Poliedro();
        for (Punto3D p : figura.vertices) {
            double nx = (sx != 0) ? p.x / sx : p.x;
            double ny = (sy != 0) ? p.y / sy : p.y;
            double nz = (sz != 0) ? p.z / sz : p.z;
            resultado.agregarPunto(nx, ny, nz, p.codigo);
        }
        return resultado;
    }

    // Rotación Inversa en X: Matriz Rx^-1 (Invierte el sentido del ángulo)
    public static Poliedro rotarXInversa(Poliedro figura, double anguloGrados) {
        return rotarX(figura, -anguloGrados);
    }

    // Rotación Inversa en Y: Matriz Ry^-1
    public static Poliedro rotarYInversa(Poliedro figura, double anguloGrados) {
        return rotarY(figura, -anguloGrados);
    }

    // Rotación Inversa en Z: Matriz Rz^-1
    public static Poliedro rotarZInversa(Poliedro figura, double anguloGrados) {
        return rotarZ(figura, -anguloGrados);
    }
}