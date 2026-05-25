


package proyectograficacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Portada con Logo central superior e imagen interactiva de acceso.
 * Se aumentó el tamaño de fuente de todos los elementos de texto.
 */
public class VentanaPortada extends JFrame {

    public VentanaPortada() {
        // --- Setup Básico ---
        setTitle("TRANSFORMACIONES GEOMETRICAS 3D");
        setSize(750, 700); // Aumentado ligeramente para el nuevo tamaño de letra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // --- Panel Principal ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 30, 30, 30));

        // 1. LOGO CENTRAL SUPERIOR
        URL logoURL = getClass().getResource("/images/logo.png");
        JLabel logoLabel;
        if (logoURL != null) {
            logoLabel = new JLabel(new ImageIcon(new ImageIcon(logoURL).getImage().getScaledInstance(500, 60, Image.SCALE_SMOOTH)));
        } else {
            logoLabel = new JLabel("LOGO");
        }
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createVerticalStrut(25));

        // 2. TEXTOS INSTITUCIONALES (Fuentes aumentadas)
        JLabel instituto = new JLabel("INSTITUTO TECNOLÓGICO DE CIUDAD VICTORIA");
        instituto.setFont(new Font("Arial", Font.BOLD, 22)); // De 16 a 22
        instituto.setForeground(new Color(60, 120, 200));
        instituto.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel carrera = new JLabel("ING. EN SISTEMAS COMPUTACIONALES");
        carrera.setFont(new Font("Arial", Font.PLAIN, 18)); // De 14 a 18
        carrera.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel graficacion = new JLabel("GRAFICACIÓN");
        graficacion.setFont(new Font("Arial", Font.BOLD, 18)); // De 15 a 18
        graficacion.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tema = new JLabel("TRANSFORMACIONES GEOMETRICAS 3D");
        tema.setFont(new Font("Arial", Font.BOLD, 17)); // De 14 a 17
        tema.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(instituto);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(carrera);
        mainPanel.add(Box.createVerticalStrut(8));
        mainPanel.add(graficacion);
        mainPanel.add(Box.createVerticalStrut(8));
        mainPanel.add(tema);

        // 3. SECCIÓN DE TRANSFORMACIONES (Aquí está el cambio)
        mainPanel.add(Box.createVerticalStrut(30));
        JLabel figurasTitulo = new JLabel("TRANSFORMACIONES GEOMETRICAS 3D");
        figurasTitulo.setFont(new Font("Arial", Font.BOLD, 26)); // De 20 a 26
        figurasTitulo.setForeground(new Color(200, 60, 60));
        figurasTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel figurasSub = new JLabel("TRASLACIÓN, ROTACIÓN Y ESCALACIÓN");
        figurasSub.setFont(new Font("Arial", Font.PLAIN, 16)); // De 12 a 16
        figurasSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(figurasTitulo);
        mainPanel.add(figurasSub);

        // 4. IMAGEN INTERACTIVA (ACCESO)
        mainPanel.add(Box.createVerticalStrut(30));
        URL imgAccesoURL = getClass().getResource("/images/imagen_principal.png");
        JLabel accessImageLabel;

        if (imgAccesoURL != null) {
            accessImageLabel = new JLabel(new ImageIcon(new ImageIcon(imgAccesoURL).getImage().getScaledInstance(250, 180, Image.SCALE_SMOOTH)));
        } else {
            accessImageLabel = new JLabel("CLIC PARA ENTRAR");
            accessImageLabel.setFont(new Font("Arial", Font.BOLD, 14));
            accessImageLabel.setPreferredSize(new Dimension(250, 120));
            accessImageLabel.setOpaque(true);
            accessImageLabel.setBackground(new Color(245, 245, 245));
            accessImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }

        accessImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        accessImageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accessImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GraficadorApp().setVisible(true);
                dispose();
            }
        });

        mainPanel.add(accessImageLabel);
        mainPanel.add(Box.createVerticalStrut(35));

        // 5. ASESOR Y FECHA
        JLabel asesor = new JLabel("Asesor: Ing. José Lino Hernández Omaña");
        asesor.setFont(new Font("Arial", Font.PLAIN, 15)); // De 12 a 15
        asesor.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel fecha = new JLabel("Cd. Victoria Tamaulipas, Enero - Junio 2026");
        fecha.setFont(new Font("Arial", Font.ITALIC, 15)); // De 12 a 15
        fecha.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(asesor);
        mainPanel.add(fecha);

        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPortada().setVisible(true));
    }
}












//package proyectograficacion;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.net.URL;
//
///**
// * Portada con Logo central superior e imagen interactiva de acceso.
// * Se aumentó el tamaño de fuente de todos los elementos de texto.
// */
//public class VentanaPortada extends JFrame {
//
//    public VentanaPortada() {
//        // --- Setup Básico ---
//        setTitle("TRANSFORMACIONES GEOMETRICAS 2D");
//        setSize(750, 700); // Aumentado ligeramente para el nuevo tamaño de letra
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//        getContentPane().setBackground(Color.WHITE);
//
//        // --- Panel Principal ---
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        mainPanel.setBackground(Color.WHITE);
//        mainPanel.setBorder(new EmptyBorder(20, 30, 30, 30));
//
//        // 1. LOGO CENTRAL SUPERIOR
//        URL logoURL = getClass().getResource("/images/logo.png");
//        JLabel logoLabel;
//        if (logoURL != null) {
//            logoLabel = new JLabel(new ImageIcon(new ImageIcon(logoURL).getImage().getScaledInstance(500, 60, Image.SCALE_SMOOTH)));
//        } else {
//            logoLabel = new JLabel("LOGO");
//        }
//        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        mainPanel.add(logoLabel);
//        mainPanel.add(Box.createVerticalStrut(25));
//
//        // 2. TEXTOS INSTITUCIONALES (Fuentes aumentadas)
//        JLabel instituto = new JLabel("INSTITUTO TECNOLÓGICO DE CIUDAD VICTORIA");
//        instituto.setFont(new Font("Arial", Font.BOLD, 22)); // De 16 a 22
//        instituto.setForeground(new Color(60, 120, 200));
//        instituto.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JLabel carrera = new JLabel("ING. EN SISTEMAS COMPUTACIONALES");
//        carrera.setFont(new Font("Arial", Font.PLAIN, 18)); // De 14 a 18
//        carrera.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JLabel graficacion = new JLabel("GRAFICACIÓN");
//        graficacion.setFont(new Font("Arial", Font.BOLD, 18)); // De 15 a 18
//        graficacion.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JLabel tema = new JLabel("TRANSFORMACIONES GEOMETRICAS 2D");
//        tema.setFont(new Font("Arial", Font.BOLD, 17)); // De 14 a 17
//        tema.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        mainPanel.add(instituto);
//        mainPanel.add(Box.createVerticalStrut(15));
//        mainPanel.add(carrera);
//        mainPanel.add(Box.createVerticalStrut(8));
//        mainPanel.add(graficacion);
//        mainPanel.add(Box.createVerticalStrut(8));
//        mainPanel.add(tema);
//
//        // 3. SECCIÓN DE TRANSFORMACIONES (Aquí está el cambio)
//        mainPanel.add(Box.createVerticalStrut(30));
//        JLabel figurasTitulo = new JLabel("TRANSFORMACIONES GEOMETRICAS 2D");
//        figurasTitulo.setFont(new Font("Arial", Font.BOLD, 26)); // De 20 a 26
//        figurasTitulo.setForeground(new Color(200, 60, 60));
//        figurasTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JLabel figurasSub = new JLabel("TRASLACIÓN, ROTACIÓN Y ESCALACIÓN");
//        figurasSub.setFont(new Font("Arial", Font.PLAIN, 16)); // De 12 a 16
//        figurasSub.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        mainPanel.add(figurasTitulo);
//        mainPanel.add(figurasSub);
//
//        // 4. IMAGEN INTERACTIVA (ACCESO)
//        mainPanel.add(Box.createVerticalStrut(30));
//        URL imgAccesoURL = getClass().getResource("/images/imagen_principal.png");
//        JLabel accessImageLabel;
//
//        if (imgAccesoURL != null) {
//            accessImageLabel = new JLabel(new ImageIcon(new ImageIcon(imgAccesoURL).getImage().getScaledInstance(250, 180, Image.SCALE_SMOOTH)));
//        } else {
//            accessImageLabel = new JLabel("CLIC PARA ENTRAR");
//            accessImageLabel.setFont(new Font("Arial", Font.BOLD, 14));
//            accessImageLabel.setPreferredSize(new Dimension(250, 120));
//            accessImageLabel.setOpaque(true);
//            accessImageLabel.setBackground(new Color(245, 245, 245));
//            accessImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        }
//
//        accessImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        accessImageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        accessImageLabel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                new GraficadorApp().setVisible(true);
//                dispose();
//            }
//        });
//
//        mainPanel.add(accessImageLabel);
//        mainPanel.add(Box.createVerticalStrut(35));
//
//        // 5. ASESOR Y FECHA
//        JLabel asesor = new JLabel("Asesor: Ing. José Lino Hernández Omaña");
//        asesor.setFont(new Font("Arial", Font.PLAIN, 15)); // De 12 a 15
//        asesor.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JLabel fecha = new JLabel("Cd. Victoria Tamaulipas, Enero - Junio 2026");
//        fecha.setFont(new Font("Arial", Font.ITALIC, 15)); // De 12 a 15
//        fecha.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        mainPanel.add(asesor);
//        mainPanel.add(fecha);
//
//        add(mainPanel, BorderLayout.CENTER);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new VentanaPortada().setVisible(true));
//    }
//}