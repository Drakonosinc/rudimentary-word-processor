import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;

public class Procesador_de_texto3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Marco_procesador_texto3 M_P_T3=new Marco_procesador_texto3();
		
		M_P_T3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
class Marco_procesador_texto3 extends JFrame{
	
	public Marco_procesador_texto3() {
		
		Lamina_procesador_texto3 L_P_T3=new Lamina_procesador_texto3();
		
		setVisible(true);
		
		setBounds(400,200,600,415);
		
		setTitle("Procesador de texto rudimentario 3");
		
		add(L_P_T3);
	}
}
class Lamina_procesador_texto3 extends JPanel{
	
	public Lamina_procesador_texto3() {
		
		setLayout(new BorderLayout());
		
		texto=new JTextPane();
		
		JPanel lamina2=new JPanel();

		mi_lamina_barras2=new JScrollPane(texto);
		
		JMenuBar mi_menu=new JMenuBar();
		
		fuente=new JMenu("Fuente");
		
		estilo=new JMenu("Estilo");

		tamaño=new JMenu("Tamaño");
						
		mi_menu.add(fuente);
		
		mi_menu.add(estilo);
		
		mi_menu.add(tamaño);
		
		Menu_items2("Calibri","fuente","calibri",9,"");
		
		Menu_items2("Arial","fuente","arial",9,"");

		Menu_items2("Cambria","fuente","cambria",9,"");
		
		Menu_items2("Negrita","estilo","",Font.BOLD,"src/graficos/negrita.jpg");

		Menu_items2("Cursiva","estilo","",Font.ITALIC,"src/graficos/cursiva.jpg");
		
		ButtonGroup mi_grupo=new ButtonGroup();
		
		JRadioButtonMenuItem doce=new JRadioButtonMenuItem("12");
		
		JRadioButtonMenuItem dieciseis=new JRadioButtonMenuItem("16");

		JRadioButtonMenuItem veinte=new JRadioButtonMenuItem("20");

		JRadioButtonMenuItem veinticinco=new JRadioButtonMenuItem("25");

		mi_grupo.add(doce);
		
		mi_grupo.add(dieciseis);

		mi_grupo.add(veinte);

		mi_grupo.add(veinticinco);

		lamina2.add(mi_menu);
		
		doce.addActionListener(new StyledEditorKit.FontSizeAction("Cambia tamaño",12));
		
		dieciseis.addActionListener(new StyledEditorKit.FontSizeAction("Cambia tamaño",16));

		veinte.addActionListener(new StyledEditorKit.FontSizeAction("Cambia tamaño",20));
		
		veinticinco.addActionListener(new StyledEditorKit.FontSizeAction("Cambia tamaño",25));
		
		JPopupMenu emergente=new JPopupMenu();

        JMenuItem opcion1=new JMenuItem("Negrita");
	
		JMenuItem opcion2=new JMenuItem("Cursiva");

		opcion1.addActionListener(new StyledEditorKit.BoldAction());
		
		opcion2.addActionListener(new StyledEditorKit.ItalicAction());
				
		emergente.add(opcion1);
		
		emergente.add(opcion2);
			
		texto.setComponentPopupMenu(emergente);

		add(doce);
		
		add(dieciseis);
		
		add(veinte);
		
		add(veinticinco);
		
		tamaño.add(doce);
		
		tamaño.add(dieciseis);
		
		tamaño.add(veinte);
		
		tamaño.add(veinticinco);
		
		add(lamina2,BorderLayout.NORTH);
		
		add(mi_lamina_barras2);
			
		barra=new JToolBar();
		
		Configura_barra("src/graficos/negrita.jpg").addActionListener(new StyledEditorKit.BoldAction());

		Configura_barra("src/graficos/cursiva.jpg").addActionListener(new StyledEditorKit.ItalicAction());

		Configura_barra("src/graficos/subrayado.jpg").addActionListener(new StyledEditorKit.UnderlineAction());

		barra.addSeparator();
		
		Configura_barra("src/graficos/amarrillo.jpg").addActionListener(new StyledEditorKit.ForegroundAction("amarrillo",Color.YELLOW));

		Configura_barra("src/graficos/azul.jpg").addActionListener(new StyledEditorKit.ForegroundAction("azul",Color.BLUE));

		Configura_barra("src/graficos/rojo.jpg").addActionListener(new StyledEditorKit.ForegroundAction("rojo",Color.RED));

		barra.addSeparator();
		
		Configura_barra("src/graficos/izquierda.jpg").addActionListener(new StyledEditorKit.AlignmentAction("centrado", 0));

		Configura_barra("src/graficos/centrado.jpg").addActionListener(new StyledEditorKit.AlignmentAction("centrado", 1));

		Configura_barra("src/graficos/derecha.jpg").addActionListener(new StyledEditorKit.AlignmentAction("centrado", 2));

		Configura_barra("src/graficos/justificado.jpg").addActionListener(new StyledEditorKit.AlignmentAction("centrado", 3));
		
		barra.setOrientation(1);
		
		add(new JPanel().add(barra),BorderLayout.WEST);
	}

	public void Menu_items2(String nombre,String menu,String tipo_letra,int estilo1,String ruta_icono) {
		
		JMenuItem menu_item2=new JMenuItem(nombre,new ImageIcon(ruta_icono));//ruta icono sirve para ponerle un icono a los menus yo no les pongo porque no tengo los iconos.
				
		if(menu=="fuente") {
			
			fuente.add(menu_item2);
			
			if(tipo_letra=="calibri") {
				
				menu_item2.addActionListener(new StyledEditorKit.FontFamilyAction("Cambia letra", "calibri"));
			}
			else if(tipo_letra=="arial") {
				
				menu_item2.addActionListener(new StyledEditorKit.FontFamilyAction("Cambia letra", "arial"));
			}
			else if(tipo_letra=="cambria") {
				
				menu_item2.addActionListener(new StyledEditorKit.FontFamilyAction("Cambia letra", "cambria"));
			}
		}else if(menu=="estilo") {	
			
			estilo.add(menu_item2);
			
			if(estilo1==Font.BOLD) {
				
				menu_item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
				
				menu_item2.addActionListener(new StyledEditorKit.BoldAction());
			}
			else if(estilo1==Font.ITALIC) {
				
				menu_item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));
				
				menu_item2.addActionListener(new StyledEditorKit.ItalicAction());
			}
		}
	}
	public JButton Configura_barra (String ruta) {
		
		JButton boton=new JButton(new ImageIcon(ruta)); 
		
		barra.add(boton);
		
		return boton;
	}
	private JToolBar barra;
	
	private JScrollPane mi_lamina_barras2;
	
	private JTextPane texto;
	
	private JMenu fuente, estilo, tamaño;
	
	private JButton negrita,cursiva,sub_barra,amarrillo,azul,rojo,a_izquierda,a_derecha,a_centrado,a_justificado;
	}