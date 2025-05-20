import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class ProcesadorTextoMejorado {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new EditorPrincipal().setVisible(true);
        });
    }
}

class EditorPrincipal extends JFrame {
    private final EditorPanel editorPanel;
    
    public EditorPrincipal() {
        setTitle("Editor de Texto Mejorado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        editorPanel = new EditorPanel();
        add(editorPanel);
        
        crearMenuPrincipal();
    }
    
    private void crearMenuPrincipal() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menú Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic('A');
        
        JMenuItem nuevoItem = new JMenuItem("Nuevo", 'N');
        JMenuItem abrirItem = new JMenuItem("Abrir...", 'A');
        JMenuItem guardarItem = new JMenuItem("Guardar", 'G');
        JMenuItem salirItem = new JMenuItem("Salir", 'S');
        
        menuArchivo.add(nuevoItem);
        menuArchivo.add(abrirItem);
        menuArchivo.add(guardarItem);
        menuArchivo.addSeparator();
        menuArchivo.add(salirItem);
        
        // Menú Edición
        JMenu menuEdicion = new JMenu("Edición");
        menuEdicion.setMnemonic('E');
        
        JMenuItem deshacerItem = new JMenuItem("Deshacer");
        JMenuItem rehacerItem = new JMenuItem("Rehacer");
        JMenuItem cortarItem = new JMenuItem("Cortar");
        JMenuItem copiarItem = new JMenuItem("Copiar");
        JMenuItem pegarItem = new JMenuItem("Pegar");
        
        menuEdicion.add(deshacerItem);
        menuEdicion.add(rehacerItem);
        menuEdicion.addSeparator();
        menuEdicion.add(cortarItem);
        menuEdicion.add(copiarItem);
        menuEdicion.add(pegarItem);
        
        // Configurar atajos
        nuevoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        abrirItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        guardarItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        salirItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        
        deshacerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        rehacerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        cortarItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        copiarItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        pegarItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        
        // Acciones
        salirItem.addActionListener(e -> System.exit(0));
        cortarItem.addActionListener(e -> editorPanel.cortar());
        copiarItem.addActionListener(e -> editorPanel.copiar());
        pegarItem.addActionListener(e -> editorPanel.pegar());
        
        menuBar.add(menuArchivo);
        menuBar.add(menuEdicion);
        menuBar.add(Box.createHorizontalGlue());
        
        setJMenuBar(menuBar);
    }
}

class EditorPanel extends JPanel {
    private final JTextPane areaTexto;
    private final JToolBar barraHerramientas;
    private final JLabel estado;
    private final Map<String, Action> acciones;
    
    public EditorPanel() {
        setLayout(new BorderLayout());
        
        // Configurar área de texto
        areaTexto = new JTextPane();
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Configurar barra de herramientas
        barraHerramientas = new JToolBar("Barra de herramientas");
        barraHerramientas.setFloatable(false);
        
        // Configurar barra de estado
        estado = new JLabel(" Listo");
        estado.setBorder(BorderFactory.createEtchedBorder());
        
        // Inicializar acciones
        acciones = crearAcciones();
        
        // Configurar componentes
        configurarBarraHerramientas();
        configurarMenuContextual();
        
        // Agregar componentes al panel
        add(barraHerramientas, BorderLayout.NORTH);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        add(estado, BorderLayout.SOUTH);
        
        // Configurar eventos
        configurarEventos();
    }
    
    private Map<String, Action> crearAcciones() {
        Map<String, Action> acciones = new HashMap<>();
        
        // Acciones de formato
        acciones.put("negrita", new StyledEditorKit.BoldAction());
        acciones.put("cursiva", new StyledEditorKit.ItalicAction());
        acciones.put("subrayado", new StyledEditorKit.UnderlineAction());
        
        // Acciones de alineación
        acciones.put("izquierda", new StyledEditorKit.AlignmentAction("Izquierda", StyleConstants.ALIGN_LEFT));
        acciones.put("centro", new StyledEditorKit.AlignmentAction("Centro", StyleConstants.ALIGN_CENTER));
        acciones.put("derecha", new StyledEditorKit.AlignmentAction("Derecha", StyleConstants.ALIGN_RIGHT));
        acciones.put("justificado", new StyledEditorKit.AlignmentAction("Justificado", StyleConstants.ALIGN_JUSTIFIED));
        
        // Acciones de color
        acciones.put("colorNegro", new StyledEditorKit.ForegroundAction("Negro", Color.BLACK));
        acciones.put("colorRojo", new StyledEditorKit.ForegroundAction("Rojo", Color.RED));
        acciones.put("colorAzul", new StyledEditorKit.ForegroundAction("Azul", Color.BLUE));
        acciones.put("colorVerde", new StyledEditorKit.ForegroundAction("Verde", Color.GREEN));
        
        return acciones;
    }
    
    private void configurarBarraHerramientas() {
        // Grupo de botones de estilo
        agregarBotonHerramienta("B", "Negrita", "negrita");
        agregarBotonHerramienta("I", "Cursiva", "cursiva");
        agregarBotonHerramienta("S", "Subrayado", "subrayado");
        
        barraHerramientas.addSeparator();
        
        // Grupo de botones de alineación
        agregarBotonHerramienta("◀", "Alinear a la izquierda", "izquierda");
        agregarBotonHerramienta("■", "Centrar", "centro");
        agregarBotonHerramienta("▶", "Alinear a la derecha", "derecha");
        agregarBotonHerramienta("◀▶", "Justificar", "justificado");
        
        barraHerramientas.addSeparator();
        
        // Selector de fuente
        String[] fuentes = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getAvailableFontFamilyNames();
        JComboBox<String> selectorFuente = new JComboBox<>(fuentes);
        selectorFuente.setSelectedItem("Arial");
        selectorFuente.addActionListener(e -> {
            String fuente = (String) selectorFuente.getSelectedItem();
            if (fuente != null) {
                new StyledEditorKit.FontFamilyAction("Fuente", fuente).actionPerformed(
                    new ActionEvent(areaTexto, ActionEvent.ACTION_PERFORMED, null));
            }
        });
        barraHerramientas.add(new JLabel(" Fuente: "));
        barraHerramientas.add(selectorFuente);
        
        // Selector de tamaño
        Integer[] tamanios = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};
        JComboBox<Integer> selectorTamanio = new JComboBox<>(tamanios);
        selectorTamanio.setSelectedItem(14);
        selectorTamanio.addActionListener(e -> {
            Integer tamanio = (Integer) selectorTamanio.getSelectedItem();
            if (tamanio != null) {
                new StyledEditorKit.FontSizeAction("Tamaño", tamanio).actionPerformed(
                    new ActionEvent(areaTexto, ActionEvent.ACTION_PERFORMED, null));
            }
        });
        barraHerramientas.add(new JLabel(" Tamaño: "));
        barraHerramientas.add(selectorTamanio);
        
        // Selector de color
        JButton botonColor = new JButton("Color");
        botonColor.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Seleccionar color", Color.BLACK);
            if (color != null) {
                new StyledEditorKit.ForegroundAction("Color", color).actionPerformed(
                    new ActionEvent(areaTexto, ActionEvent.ACTION_PERFORMED, null));
            }
        });
        barraHerramientas.add(botonColor);
    }
    
    private void agregarBotonHerramienta(String texto, String tooltip, String accion) {
        JButton boton = new JButton(texto);
        boton.setToolTipText(tooltip);
        boton.addActionListener(acciones.get(accion));
        barraHerramientas.add(boton);
    }
    
    private void configurarMenuContextual() {
        JPopupMenu menuContextual = new JPopupMenu();
        
        JMenuItem cortarItem = new JMenuItem("Cortar");
        JMenuItem copiarItem = new JMenuItem("Copiar");
        JMenuItem pegarItem = new JMenuItem("Pegar");
        
        cortarItem.addActionListener(e -> cortar());
        copiarItem.addActionListener(e -> copiar());
        pegarItem.addActionListener(e -> pegar());
        
        menuContextual.add(cortarItem);
        menuContextual.add(copiarItem);
        menuContextual.add(pegarItem);
        
        areaTexto.setComponentPopupMenu(menuContextual);
    }
    
    private void configurarEventos() {
        // Actualizar barra de estado con la posición del cursor
        areaTexto.addCaretListener(e -> {
            int posicion = areaTexto.getCaretPosition();
            int fila = 0;
            int columna = 0;
            
            try {
                int offset = areaTexto.getCaretPosition();
                int rowStart = Utilities.getRowStart(areaTexto, offset);
                int rowEnd = Utilities.getRowEnd(areaTexto, offset);
                
                fila = areaTexto.getText().substring(0, offset).split("\n").length;
                columna = offset - rowStart + 1;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            estado.setText(String.format(" Línea: %d, Columna: %d | Caracteres: %d", 
                fila, columna, areaTexto.getText().length()));
        });
    }
    
    // Métodos de utilidad
    public void cortar() {
        areaTexto.cut();
    }
    
    public void copiar() {
        areaTexto.copy();
    }
    
    public void pegar() {
        areaTexto.paste();
    }
}
