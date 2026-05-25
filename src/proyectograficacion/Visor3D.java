package proyectograficacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Visor3D extends JFrame {

    private JFrame menuPrincipal;

    public Visor3D(String accionDelBoton, JFrame menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        
        setTitle("Visor 3D: " + accionDelBoton);
        setSize(950, 650); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Poliedro figuraOriginal = new Poliedro();
        figuraOriginal.cargarPrismaTriangular();
        Poliedro figuraTransformada = null;
        
        String vectorInfo = "";
        String vistaEjes = "Normal";

        // Lógica para elegir si aplica Inversa o Directa según el botón
        switch (accionDelBoton) {
            // --- INVERSAS ---
            case "TrasInv":
                figuraTransformada = Transformaciones3D.trasladarInversa(figuraOriginal, 2, 1, 3);
                vectorInfo = "T^-1(2, 1, 3) [Resta]";
                break;
            case "EscInv":
                figuraTransformada = Transformaciones3D.escalarInversa(figuraOriginal, 2, 2, 2);
                vectorInfo = "S^-1(2, 2, 2) [División]";
                break;
            case "RotXInv":
                figuraTransformada = Transformaciones3D.rotarXInversa(figuraOriginal, 30);
                vectorInfo = "Rx^-1(30°)";
                vistaEjes = "RotacionX";
                break;
            case "RotYInv":
                figuraTransformada = Transformaciones3D.rotarYInversa(figuraOriginal, 30);
                vectorInfo = "Ry^-1(30°)";
                vistaEjes = "RotacionY";
                break;
            case "RotZInv":
                figuraTransformada = Transformaciones3D.rotarZInversa(figuraOriginal, 30);
                vectorInfo = "Rz^-1(30°)";
                vistaEjes = "RotacionZ";
                break;

            // --- DIRECTAS ---
            case "TrasDir":
                figuraTransformada = Transformaciones3D.trasladar(figuraOriginal, 2, 1, 3);
                vectorInfo = "T(2, 1, 3) [Suma]";
                break;
            case "EscDir":
                figuraTransformada = Transformaciones3D.escalar(figuraOriginal, 2, 2, 2);
                vectorInfo = "S(2, 2, 2) [Producto]";
                break;
            case "RotXDir":
                figuraTransformada = Transformaciones3D.rotarX(figuraOriginal, 30);
                vectorInfo = "Rx(30°)";
                vistaEjes = "RotacionX";
                break;
            case "RotYDir":
                figuraTransformada = Transformaciones3D.rotarY(figuraOriginal, 30);
                vectorInfo = "Ry(30°)";
                vistaEjes = "RotacionY";
                break;
            case "RotZDir":
                figuraTransformada = Transformaciones3D.rotarZ(figuraOriginal, 30);
                vectorInfo = "Rz(30°)";
                vistaEjes = "RotacionZ";
                break;
        }

        Lienzo3D lienzo = new Lienzo3D();
        lienzo.tipoVista = vistaEjes; // Le pasamos la vista para que acomode los ejes
        lienzo.figuraOriginal = figuraOriginal;
        lienzo.figuraTransformada = figuraTransformada;
        add(lienzo, BorderLayout.CENTER);

        // --- PANEL LATERAL DERECHO CON LOS TEXTOS COMPLETOS ---
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setPreferredSize(new Dimension(300, 0)); 
        panelDerecho.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelDerecho.setBackground(new Color(240, 240, 240));

        JLabel tituloDatos = new JLabel("DATOS NUMÉRICOS");
        tituloDatos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tituloDatos.setHorizontalAlignment(SwingConstants.CENTER);
        panelDerecho.add(tituloDatos, BorderLayout.NORTH);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaTexto.setText(generarTextoDatos(figuraOriginal, figuraTransformada, vectorInfo));
        
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        panelDerecho.add(scrollPane, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);

        // --- PANEL DE ABAJO ---
        JPanel panelAbajo = new JPanel();
        JButton btnRegresar = new JButton("Regresar al Menú");
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegresar.addActionListener(e -> {
            menuPrincipal.setVisible(true);
            this.dispose();
        });
        panelAbajo.add(btnRegresar);
        add(panelAbajo, BorderLayout.SOUTH);
    }

    // EL MÉTODO COMPLETO (Sin recortes)
    private String generarTextoDatos(Poliedro orig, Poliedro trans, String vectorInfo) {
        StringBuilder sb = new StringBuilder();
        int[] indices = {0, 1, 2, 4, 5, 6}; 
        
        sb.append("--- PUNTOS ORIGINALES (Azul) ---\n");
        for (int i = 0; i < indices.length; i++) {
            Punto3D p = orig.vertices.get(indices[i]);
            sb.append(String.format("P%d: (%.2f, %.2f, %.2f)\n", (i+1), p.x, p.y, p.z));
        }
        
        sb.append("\n--- TRANSFORMADOS (Rojo) ---\n");
        sb.append("Fórmula: ").append(vectorInfo).append("\n");
        for (int i = 0; i < indices.length; i++) {
            Punto3D p = trans.vertices.get(indices[i]);
            sb.append(String.format("P'%d: (%.2f, %.2f, %.2f)\n", (i+1), p.x, p.y, p.z));
        }

        sb.append("\n--- CÓDIGO DE CIERRE (12 Pasos) ---\n");
        sb.append("1. P1 \n2. P2 \n3. P3 \n4. P1 (Cierra base)\n");
        sb.append("5. P4 (Poste 1)\n6. P5 \n7. P6 \n8. P4 (Cierra tapa)\n");
        sb.append("9. P2 (Salta)\n10. P5 (Poste 2)\n");
        sb.append("11. P3 (Salta)\n12. P6 (Poste 3)\n");

        return sb.toString();
    }
}