/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author emili
 */
package proyectograficacion;

public class Punto3D {
    public double x;
    public double y;
    public double z;
    public double w;      // Coordenada homogénea (el famoso 1)
    public int codigo;    // 0 para mover (levantar lápiz), 1 para dibujar

    // Constructor
    public Punto3D(double x, double y, double z, int codigo) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1.0; // Siempre lo inicializamos en 1 por regla matemática
        this.codigo = codigo;
    }
}