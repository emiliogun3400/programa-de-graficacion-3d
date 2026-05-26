package proyectograficacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;

public class GraficadorApp extends JFrame {

    private static final Color ITC_BLUE = new Color(0, 51, 102);
    private static final Color NETBEANS_CUBE_COLOR = new Color(180, 200, 255);

    public GraficadorApp() {
        // Actualizamos el título de la ventana
        setTitle("MENÚ 3D - BÁSICAS E INVERSAS");
        setSize(700, 680); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel labelMenu = new JLabel("MENÚ");
        labelMenu.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelMenu.setForeground(Color.DARK_GRAY);
        labelMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(labelMenu);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel title = new JLabel("TRANSFORMACIONES GEOMÉTRICAS 3D");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(200, 60, 60));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel gridPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        gridPanel.setBackground(new Color(245, 245, 245));
        gridPanel.setMaximumSize(new Dimension(600, 340)); 

        // COLUMNA IZQUIERDA: INVERSAS (Intacta)
        JPanel panelInversas = new JPanel(new GridLayout(5, 1, 0, 10));
        panelInversas.setBackground(new Color(245, 245, 245));
        panelInversas.setBorder(BorderFactory.createTitledBorder(null, "MÉTODOS INVERSOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12), Color.DARK_GRAY));

        panelInversas.add(crearBoton("1. Traslación Inv.", "TrasInv", new Color(40, 180, 100)));
        panelInversas.add(crearBoton("2. Escalación Inv.", "EscInv", new Color(200, 100, 0)));
        panelInversas.add(crearBoton("3. Rotación X Inv.", "RotXInv", new Color(100, 60, 200)));
        panelInversas.add(crearBoton("4. Rotación Y Inv.", "RotYInv", new Color(100, 60, 200)));
        panelInversas.add(crearBoton("5. Rotación Z Inv.", "RotZInv", new Color(100, 60, 200)));

        // --- COLUMNA DERECHA: BÁSICAS (Textos actualizados) ---
        JPanel panelBasicas = new JPanel(new GridLayout(5, 1, 0, 10));
        panelBasicas.setBackground(new Color(245, 245, 245));
        panelBasicas.setBorder(BorderFactory.createTitledBorder(null, "MÉTODOS BÁSICOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12), Color.DARK_GRAY));

        panelBasicas.add(crearBoton("6. Traslación Básica", "TrasDir", new Color(60, 140, 200)));
        panelBasicas.add(crearBoton("7. Escalación Básica", "EscDir", new Color(220, 120, 40)));
        panelBasicas.add(crearBoton("8. Rotación X Básica", "RotXDir", new Color(140, 100, 220)));
        panelBasicas.add(crearBoton("9. Rotación Y Básica", "RotYDir", new Color(140, 100, 220)));
        panelBasicas.add(crearBoton("10. Rotación Z Básica", "RotZDir", new Color(140, 100, 220)));

        gridPanel.add(panelInversas);
        gridPanel.add(panelBasicas);

        mainPanel.add(gridPanel);
        mainPanel.add(Box.createVerticalGlue()); 
        add(mainPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(new Color(245, 245, 245));

        JButton btnRegresar = new JButton("Regresar a la Portada");
        btnRegresar.addActionListener(e -> {
            new VentanaPortada().setVisible(true);
            dispose();
        });

        JButton btnCreditos = new JButton("Créditos");
        btnCreditos.addActionListener(e -> {
            Object[] opt = {"Cerrar"}; 
            JOptionPane.showOptionDialog(this, createCreditosPanel(), "Créditos", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opt, opt[0]);
        });

        bottomPanel.add(btnRegresar);
        bottomPanel.add(btnCreditos);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, String accion, Color colorFondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> {
            new Visor3D(accion, this).setVisible(true);
            this.setVisible(false);
        });
        return btn;
    }

    private JPanel createCreditosPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setPreferredSize(new Dimension(460, 420));
        
        JLabel title = new JLabel("Software para Transformaciones Geométricas 3D");
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));
        title.setForeground(ITC_BLUE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));
        
        JPanel idePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        idePanel.setBackground(Color.WHITE);
        JLabel ideLabel = new JLabel("NetBeans IDE");
        ideLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        ideLabel.setForeground(Color.GRAY);
        idePanel.add(ideLabel);
        
        URL netbeansIconURL = getClass().getResource("/images/netbeans_cube.png");
        JLabel netbeansIcon = createIconPlaceholder(netbeansIconURL, 40, 40, NETBEANS_CUBE_COLOR);
        idePanel.add(netbeansIcon);
        panel.add(idePanel);
        panel.add(Box.createVerticalStrut(20));
        
        JLabel teamTitle = new JLabel("INTEGRANTES DEL EQUIPO:");
        teamTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        teamTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(teamTitle);
        panel.add(Box.createVerticalStrut(5));
        
        JPanel teamGrid = new JPanel(new GridBagLayout());
        teamGrid.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        String[] nombres = {"Emilio Gomez Garcia"};
        String[] controles = {"23380392"};
        
        for (int i = 0; i < nombres.length; i++) {
            gbc.gridy = i;
            gbc.gridx = 0;
            JLabel ctrlLabel = new JLabel(controles[i] + " -");
            ctrlLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            teamGrid.add(ctrlLabel, gbc);
            gbc.gridx = 1;
            JLabel nameLabel = new JLabel(nombres[i]);
            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            teamGrid.add(nameLabel, gbc);
        }
        panel.add(teamGrid);
        panel.add(Box.createVerticalStrut(20));
        
        String legalText = "<html><div style='text-align: justify; font-family: Segoe UI; font-size: 9px;'>"
                + "Este software es una obra intelectual desarrollada por alumnos de la carrera de <b>Ing. en Sistemas Computacionales</b> "
                + "del Instituto Tecnológico de Ciudad Victoria. Prohibida su reproducción total o parcial sin consentimiento de los autores. "
                + "Los autores de este tipo de producto no se hacen responsables por el uso indebido de esta información. "
                + "Prohibida su comercialización, ya que es un software de uso académico diseñado exclusivamente con fines educativos y de aprendizaje. "
                + "Cualquier distribución fuera del entorno institucional deberá contar con la autorización previa de los desarrolladores.<br><br>"
                + "<center><b>Copyright:</b> Software desarrollado por alumnos del ITCV. Prohibida su reproducción.</center></div></html>";

        JLabel copyrightLabel = new JLabel(legalText);
        copyrightLabel.setForeground(Color.DARK_GRAY);
        copyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(copyrightLabel);

        return panel;
    }

    private JLabel createIconPlaceholder(URL url, int width, int height, Color placeholderColor) {
        JLabel label;
        if (url != null) {
            label = new JLabel(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        } else {
            label = new JLabel();
            label.setPreferredSize(new Dimension(width, height));
            label.setOpaque(true);
            label.setBackground(placeholderColor);
            label.setBorder(new LineBorder(Color.LIGHT_GRAY));
        }
        return label;
    }
}