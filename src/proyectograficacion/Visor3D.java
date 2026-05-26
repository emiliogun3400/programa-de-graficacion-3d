package proyectograficacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Visor3D extends JFrame {

    private JFrame menuPrincipal;
    private Poliedro figuraOriginal;
    private Poliedro figuraTransformada;
    private String accionDelBoton;
    
    // Controles de la interfaz que necesitamos actualizar dinámicamente
    private Lienzo3D lienzo;
    private JTable tablaOrig;
    private JTable tablaTrans;

    public Visor3D(String accionDelBoton, JFrame menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        this.accionDelBoton = accionDelBoton;
        
        setTitle("Visor 3D: " + accionDelBoton);
        setSize(1100, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Al iniciar la ventana, SIEMPRE se carga el prisma original de tu libreta
        figuraOriginal = new Poliedro();
        figuraOriginal.cargarPrismaTriangular();
        
        String vectorInfo = "";
        String vistaEjes = "Normal";
        String nombreMetodo = ""; 

        switch (accionDelBoton) {
            case "TrasInv": vectorInfo = "T(-2, -1, -3)"; nombreMetodo = "Traslación Inversa"; break;
            case "EscInv": vectorInfo = "S(0.5, 0.5, 0.5)"; nombreMetodo = "Escalación Inversa"; break;
            case "RotXInv": vectorInfo = "Rx^-1(30°)"; vistaEjes = "RotacionX"; nombreMetodo = "Rotación en X Inversa"; break;
            case "RotYInv": vectorInfo = "Ry^-1(30°)"; vistaEjes = "RotacionY"; nombreMetodo = "Rotación en Y Inversa"; break;
            case "RotZInv": vectorInfo = "Rz^-1(30°)"; vistaEjes = "RotacionZ"; nombreMetodo = "Rotación en Z Inversa"; break;
            case "TrasDir": vectorInfo = "T(2, 1, 3)"; nombreMetodo = "Traslación Básica"; break;
            case "EscDir": vectorInfo = "S(2, 2, 2)"; nombreMetodo = "Escalación Básica"; break;
            case "RotXDir": vectorInfo = "Rx(30°)"; vistaEjes = "RotacionX"; nombreMetodo = "Rotación en X Básica"; break;
            case "RotYDir": vectorInfo = "Ry(30°)"; vistaEjes = "RotacionY"; nombreMetodo = "Rotación en Y Básica"; break;
            case "RotZDir": vectorInfo = "Rz(30°)"; vistaEjes = "RotacionZ"; nombreMetodo = "Rotación en Z Básica"; break;
        }

        // Hacemos el cálculo inicial
        actualizarTransformaciones();

        // --- PANEL SUPERIOR ---
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
        panelNorte.setBackground(new Color(245, 245, 245));
        panelNorte.setBorder(new EmptyBorder(15, 0, 15, 0));

        JLabel lblTitulo = new JLabel("GRAFICACIÓN 3D");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(ITC_BLUE());
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMetodo = new JLabel(nombreMetodo);
        lblMetodo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblMetodo.setForeground(Color.DARK_GRAY);
        lblMetodo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelNorte.add(lblTitulo);
        panelNorte.add(Box.createRigidArea(new Dimension(0, 5))); 
        panelNorte.add(lblMetodo);
        add(panelNorte, BorderLayout.NORTH); 

        // --- PANEL IZQUIERDO (Originales) ---
        tablaOrig = new JTable();
        // CAMBIO: Cambiamos el 'true' a 'false' para que muestre todos los puntos de cierre (los 12 pasos)
        tablaOrig.setModel(crearModelo(figuraOriginal, "P", false)); 
        tablaOrig.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaOrig.setRowHeight(20);
        JScrollPane scrollOrig = new JScrollPane(tablaOrig);
        scrollOrig.setPreferredSize(new Dimension(220, 0));
        scrollOrig.setBorder(BorderFactory.createTitledBorder(null, "Puntos Originales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12), ITC_BLUE()));
        add(scrollOrig, BorderLayout.WEST);

        // --- LIENZO CENTRAL ---
        lienzo = new Lienzo3D();
        lienzo.tipoVista = vistaEjes;
        lienzo.figuraOriginal = figuraOriginal;
        lienzo.figuraTransformada = figuraTransformada;
        add(lienzo, BorderLayout.CENTER);

        // --- PANEL DERECHO (Transformados) ---
        tablaTrans = new JTable();
        // Este ya estaba en 'false' para mostrar todos
        tablaTrans.setModel(crearModelo(figuraTransformada, "P'", false)); 
        tablaTrans.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaTrans.setRowHeight(20);
        JScrollPane scrollTrans = new JScrollPane(tablaTrans);
        scrollTrans.setPreferredSize(new Dimension(220, 0));
        scrollTrans.setBorder(BorderFactory.createTitledBorder(null, "Transformados: " + vectorInfo, javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12), Color.RED));
        add(scrollTrans, BorderLayout.EAST);

        // --- PANEL INFERIOR CON HERRAMIENTAS DINÁMICAS ---
        JPanel panelAbajoBase = new JPanel(new BorderLayout());
        panelAbajoBase.setBackground(new Color(245, 245, 245));

        // Sub-panel para las herramientas de edición de puntos
        JPanel panelHerramientas = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelHerramientas.setBackground(new Color(245, 245, 245));
        
        JButton btnAgregar = new JButton("Agregar Punto");
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnAgregar.setBackground(new Color(40, 160, 80)); // Verde
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());

        JButton btnBorrar = new JButton("Borrar Último");
        btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnBorrar.setBackground(new Color(220, 120, 40)); // Naranja
        btnBorrar.setForeground(Color.WHITE);
        btnBorrar.addActionListener(e -> {
            if (!figuraOriginal.vertices.isEmpty()) {
                figuraOriginal.vertices.remove(figuraOriginal.vertices.size() - 1);
                refrescarTodo();
            }
        });

        JButton btnLimpiar = new JButton("Limpiar Figura");
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLimpiar.setBackground(new Color(200, 50, 50)); // Rojo
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.addActionListener(e -> {
            figuraOriginal.vertices.clear();
            refrescarTodo();
        });

        panelHerramientas.add(btnAgregar);
        panelHerramientas.add(btnBorrar);
        panelHerramientas.add(btnLimpiar);

        // Sub-panel para el botón de regresar (Abajo de las herramientas)
        JPanel panelRegresar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelRegresar.setBackground(new Color(245, 245, 245));
        JButton btnRegresar = new JButton("Regresar al Menú");
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegresar.addActionListener(e -> {
            menuPrincipal.setVisible(true);
            this.dispose(); // Cierra esta ventana (reseteando los datos para la próxima vez)
        });
        panelRegresar.add(btnRegresar);

        panelAbajoBase.add(panelHerramientas, BorderLayout.CENTER);
        panelAbajoBase.add(panelRegresar, BorderLayout.SOUTH);
        add(panelAbajoBase, BorderLayout.SOUTH);
    }

    // Actualiza la matriz matemática dependiendo del método elegido
    private void actualizarTransformaciones() {
        // Si no hay puntos, evitamos procesar matrices para ahorrar memoria
        if (figuraOriginal.vertices.isEmpty()) {
            figuraTransformada = new Poliedro();
            return;
        }

        switch (accionDelBoton) {
            case "TrasInv": figuraTransformada = Transformaciones3D.trasladarInversa(figuraOriginal, 2, 1, 3); break;
            case "EscInv": figuraTransformada = Transformaciones3D.escalarInversa(figuraOriginal, 2, 2, 2); break;
            case "RotXInv": figuraTransformada = Transformaciones3D.rotarXInversa(figuraOriginal, 30); break;
            case "RotYInv": figuraTransformada = Transformaciones3D.rotarYInversa(figuraOriginal, 30); break;
            case "RotZInv": figuraTransformada = Transformaciones3D.rotarZInversa(figuraOriginal, 30); break;
            case "TrasDir": figuraTransformada = Transformaciones3D.trasladar(figuraOriginal, 2, 1, 3); break;
            case "EscDir": figuraTransformada = Transformaciones3D.escalar(figuraOriginal, 2, 2, 2); break;
            case "RotXDir": figuraTransformada = Transformaciones3D.rotarX(figuraOriginal, 30); break;
            case "RotYDir": figuraTransformada = Transformaciones3D.rotarY(figuraOriginal, 30); break;
            case "RotZDir": figuraTransformada = Transformaciones3D.rotarZ(figuraOriginal, 30); break;
        }
    }

    // Método central para recalcular y redibujar cuando cambian los puntos
    private void refrescarTodo() {
        actualizarTransformaciones();
        // CAMBIO: Al refrescar, también le pasamos 'false' para que siga mostrando todos los puntos
        tablaOrig.setModel(crearModelo(figuraOriginal, "P", false));
        tablaTrans.setModel(crearModelo(figuraTransformada, "P'", false));
        lienzo.figuraOriginal = figuraOriginal;
        lienzo.figuraTransformada = figuraTransformada;
        lienzo.repaint();
    }

    // Dialogo para capturar un nuevo punto
    private void mostrarDialogoAgregar() {
        JTextField txtX = new JTextField(5);
        JTextField txtY = new JTextField(5);
        JTextField txtZ = new JTextField(5);
        JTextField txtCod = new JTextField(5);
        txtCod.setText("1"); // 1 por defecto (Bajar lápiz)

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("X:")); myPanel.add(txtX);
        myPanel.add(Box.createHorizontalStrut(5));
        myPanel.add(new JLabel("Y:")); myPanel.add(txtY);
        myPanel.add(Box.createHorizontalStrut(5));
        myPanel.add(new JLabel("Z:")); myPanel.add(txtZ);
        myPanel.add(Box.createHorizontalStrut(5));
        myPanel.add(new JLabel("Cód (0 o 1):")); myPanel.add(txtCod);

        int result = JOptionPane.showConfirmDialog(this, myPanel, 
                 "Ingresa las coordenadas del nuevo punto", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                double x = Double.parseDouble(txtX.getText());
                double y = Double.parseDouble(txtY.getText());
                double z = Double.parseDouble(txtZ.getText());
                int cod = Integer.parseInt(txtCod.getText());
                
                figuraOriginal.agregarPunto(x, y, z, cod);
                refrescarTodo(); // Recalcula la matriz y dibuja en pantalla
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa solo números.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Generador de la tabla con la lógica de las etiquetas intacta
    private DefaultTableModel crearModelo(Poliedro pol, String prefijo, boolean soloSeis) {
        String[] columnas = {"Punt.", "X", "Y", "Z", "Cod"};
        int numPuntosMostrar = soloSeis ? Math.min(6, pol.vertices.size()) : pol.vertices.size();
        Object[][] datos = new Object[numPuntosMostrar][5];
        
        // Las etiquetas de tu libreta (si el usuario mete más puntos, usará números consecutivos)
        String[] etiquetasLibreta = {"1", "2", "3", "4", "5", "6", "4", "1", "3", "6", "2", "5"};
        
        for (int i = 0; i < numPuntosMostrar; i++) {
            Punto3D p = pol.vertices.get(i);
            
            String etiq = (i < etiquetasLibreta.length) ? etiquetasLibreta[i] : String.valueOf(i + 1);
            
            datos[i][0] = prefijo + etiq; 
            datos[i][1] = String.format("%.3f", p.x); 
            datos[i][2] = String.format("%.3f", p.y);
            datos[i][3] = String.format("%.3f", p.z);
            datos[i][4] = p.codigo;
        }
        
        return new DefaultTableModel(datos, columnas) {
            @Override 
            public boolean isCellEditable(int row, int column) { return false; }
        };
    }
    
    private Color ITC_BLUE() {
        return new Color(0, 51, 102);
    }
}