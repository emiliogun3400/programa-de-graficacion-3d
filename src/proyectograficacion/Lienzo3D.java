package proyectograficacion;

import java.awt.*;
import javax.swing.JPanel;

public class Lienzo3D extends JPanel {
    
    public Poliedro figuraOriginal;
    public Poliedro figuraTransformada;
    public String tipoVista = "Normal";

    public Lienzo3D() {
        this.setBackground(Color.WHITE); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = this.getWidth() / 2;
        int cy = this.getHeight() / 2;
        
        // --- ZOOM ALEJADO PARA VER MÁS PUNTOS (25 en lugar de 35) ---
        int escala = 25; 

        g2d.setColor(new Color(235, 235, 235)); 
        for (int x = cx % escala; x < this.getWidth(); x += escala) g2d.drawLine(x, 0, x, this.getHeight());
        for (int y = cy % escala; y < this.getHeight(); y += escala) g2d.drawLine(0, y, this.getWidth(), y);

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawLine(cx, cy, cx, 0); 
        g2d.drawLine(cx, cy, this.getWidth(), cy); 
        int diagX = cx - (int)(400 * Math.cos(Math.toRadians(45)));
        int diagY = cy + (int)(400 * Math.sin(Math.toRadians(45)));
        g2d.drawLine(cx, cy, diagX, diagY); 

        g2d.setFont(new Font("Arial", Font.PLAIN, 10)); 
        
        // Ahora dibujamos hasta el 14 para aprovechar el nuevo zoom
        for (int i = 1; i <= 14; i++) {
            Point ptX = Proyecciones.proyectar(new Punto3D(i, 0, 0, 0), cx, cy, escala, tipoVista);
            g2d.setColor(Color.DARK_GRAY); g2d.fillOval(ptX.x - 2, ptX.y - 2, 4, 4); 
            g2d.setColor(Color.GRAY); g2d.drawString(String.valueOf(i), ptX.x + 5, ptX.y + 12);

            Point ptY = Proyecciones.proyectar(new Punto3D(0, i, 0, 0), cx, cy, escala, tipoVista);
            g2d.setColor(Color.DARK_GRAY); g2d.fillOval(ptY.x - 2, ptY.y - 2, 4, 4);
            g2d.setColor(Color.GRAY); g2d.drawString(String.valueOf(i), ptY.x + 5, ptY.y + 12);

            Point ptZ = Proyecciones.proyectar(new Punto3D(0, 0, i, 0), cx, cy, escala, tipoVista);
            g2d.setColor(Color.DARK_GRAY); g2d.fillOval(ptZ.x - 2, ptZ.y - 2, 4, 4);
            g2d.setColor(Color.GRAY); g2d.drawString(String.valueOf(i), ptZ.x + 5, ptZ.y + 12);
        }

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        String labelArriba = "Z", labelDerecha = "Y", labelDiagonal = "X";
        if ("RotacionY".equals(tipoVista)) { labelArriba = "X"; labelDerecha = "Z"; labelDiagonal = "Y"; } 
        else if ("RotacionZ".equals(tipoVista)) { labelArriba = "Y"; labelDerecha = "X"; labelDiagonal = "Z"; }
        g2d.drawString(labelArriba, cx - 20, 20);
        g2d.drawString(labelDerecha, this.getWidth() - 25, cy - 10);
        g2d.drawString(labelDiagonal, diagX - 20, diagY + 20);

        dibujarLineasPunteadas(g2d, figuraOriginal, figuraTransformada, cx, cy, escala);
        if (figuraOriginal != null) dibujarPoliedro(g2d, figuraOriginal, cx, cy, escala, new Color(0, 0, 255, 180), "P");
        if (figuraTransformada != null) dibujarPoliedro(g2d, figuraTransformada, cx, cy, escala, new Color(255, 0, 0, 180), "P'");
    }

    private void dibujarLineasPunteadas(Graphics2D g2d, Poliedro orig, Poliedro trans, int cx, int cy, int escala) {
        if (orig == null || trans == null) return;
        Stroke trazoOriginal = g2d.getStroke();
        float[] dash = {3.0f, 3.0f};
        g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
        g2d.setColor(Color.GRAY);
        int[] indicesUnicos = {0, 1, 2, 3, 4, 5}; 
        for (int i : indicesUnicos) {
            if (i < orig.vertices.size() && i < trans.vertices.size()) {
                Point p1 = Proyecciones.proyectar(orig.vertices.get(i), cx, cy, escala, tipoVista);
                Point p2 = Proyecciones.proyectar(trans.vertices.get(i), cx, cy, escala, tipoVista);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
        g2d.setStroke(trazoOriginal); 
    }

    private void dibujarPoliedro(Graphics2D g2d, Poliedro pol, int cx, int cy, int escala, Color colorLinea, String sufijo) {
        g2d.setColor(colorLinea);
        g2d.setStroke(new BasicStroke(1.5f));
        Point puntoAnterior = null;
        for (Punto3D p : pol.vertices) {
            Point puntoActual = Proyecciones.proyectar(p, cx, cy, escala, tipoVista);
            if (p.codigo == 1 && puntoAnterior != null) {
                g2d.drawLine(puntoAnterior.x, puntoAnterior.y, puntoActual.x, puntoActual.y);
            }
            puntoAnterior = puntoActual;
        }
        
        int[] indicesUnicos = {0, 1, 2, 3, 4, 5};
        int numPunto = 1;
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.BLACK);
        for (int i : indicesUnicos) {
            if (i < pol.vertices.size()) {
                Point pt = Proyecciones.proyectar(pol.vertices.get(i), cx, cy, escala, tipoVista);
                g2d.drawString(sufijo + numPunto, pt.x + 5, pt.y - 5);
                numPunto++;
            }
        }
    }
}