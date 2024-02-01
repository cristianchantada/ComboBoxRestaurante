import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.util.Scanner;
import java.awt.*;

public class ComboBox extends JFrame {
	
	
	private final String PATH_TO_SRC = "C:\\Tomcat10.1\\webapps\\ROOT\\ComoBox\\src";
	private final String IMGS_DIR_NAME = "imgs";
	private final String MODEL_DIR_NAME = "imgs";
	
			
	private static final long serialVersionUID = 1L;
	private JPanel PanelContenido;
	private JComboBox comboBoxPrimeros;
	private JComboBox comboBoxSegundos;
	private JComboBox comboBoxPostre;
	private JComboBox comboBoxBebidas;
	private JLabel segundoImg;
	private JLabel postreImg;
	private JLabel bebidaImg;
	private JLabel backgroundImg;
	private JLabel restaurantTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboBox frame = new ComboBox();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ComboBox() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1114, 756);
		PanelContenido = new JPanel();
		PanelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(PanelContenido);
		PanelContenido.setLayout(null);
		
		JButton deliverButton = new JButton("Trae a comida ostia");
		deliverButton.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		deliverButton.setBorderPainted(false);
		deliverButton.setBackground(new Color(179, 89, 0));
		deliverButton.setFont(new Font("Ink Free", Font.BOLD, 30));
		deliverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deliverButton.setBounds(582, 598, 334, 58);
		PanelContenido.add(deliverButton);
		
		restaurantTitle = new JLabel("Taberna do Carallo");
		restaurantTitle.setBackground(new Color(0, 255, 0));
		restaurantTitle.setFont(new Font("Ink Free", Font.BOLD, 80));
		restaurantTitle.setBounds(192, 24, 752, 135);
		PanelContenido.add(restaurantTitle);
		
		JLabel primeroImg = new JLabel("");
		primeroImg.setBackground(new Color(0, 0, 255));
		primeroImg.setBounds(289, 171, 230, 160);
		PanelContenido.add(primeroImg);

		comboBoxPrimeros = new JComboBox();
		comboBoxPrimeros.addItem("-- PRIMEIROS PLATOS seleccione --");
		comboBoxPrimeros.addItem("Orella");
		comboBoxPrimeros.addItem("Tripón");
		comboBoxPrimeros.addItem("Pulpo de Carballiño");
		comboBoxPrimeros.addItem("Callos");
		comboBoxPrimeros.addItem("Cachucha");
		comboBoxPrimeros.addItem("Lacón con grelos");
		comboBoxPrimeros.addItem("Cachelos cocidos con pimentón picante");
		comboBoxPrimeros.addItem("Cesped vegano para Pablo");
		comboBoxPrimeros.setBounds(289, 134, 229, 26);
		PanelContenido.add(comboBoxPrimeros);

		comboBoxSegundos = new JComboBox();
		comboBoxSegundos.addItem("-- SEGUNDOS PLATOS seleccione --");
		comboBoxSegundos.addItem("Cocido galego");
		comboBoxSegundos.addItem("Zorza con patacas");
		comboBoxSegundos.addItem("Chourizos ao viño");
		comboBoxSegundos.addItem("Carne ao caldeiro");
		comboBoxSegundos.addItem("Carne richada");
		comboBoxSegundos.addItem("Herba seca vegana para Pablo");
		comboBoxSegundos.setBounds(598, 134, 229, 26);
		PanelContenido.add(comboBoxSegundos);

		comboBoxPostre = new JComboBox();
		comboBoxPostre.addItem("-- POSTRES seleccione --");
		comboBoxPostre.addItem("Tarta de Whiskey");
		comboBoxPostre.addItem("Tarta helada con ron");
		comboBoxPostre.addItem("Brazo de gitano con anís");
		comboBoxPostre.addItem("Toxo vegano para Pablo");
		comboBoxPostre.setBounds(289, 337, 229, 26);
		PanelContenido.add(comboBoxPostre);

		comboBoxBebidas = new JComboBox();
		comboBoxBebidas.addItem("-- BEBIDAS seleccione --");
		comboBoxBebidas.addItem("Licor de herbas");
		comboBoxBebidas.addItem("Licor café");
		comboBoxBebidas.addItem("Augardente");
		comboBoxBebidas.addItem("Viño da casa");
		comboBoxBebidas.addItem("Auga vegana para Pablo");
		comboBoxBebidas.setBounds(598, 337, 229, 26);
		PanelContenido.add(comboBoxBebidas);
		
		segundoImg = new JLabel("");
		segundoImg.setBackground(Color.BLUE);
		segundoImg.setBounds(598, 171, 230, 160);
		PanelContenido.add(segundoImg);
		
		postreImg = new JLabel("");
		postreImg.setBackground(Color.BLUE);
		postreImg.setBounds(288, 373, 230, 160);
		PanelContenido.add(postreImg);
		
		bebidaImg = new JLabel("");
		bebidaImg.setBackground(Color.BLUE);
		bebidaImg.setBounds(598, 374, 230, 160);
		PanelContenido.add(bebidaImg);
		
		backgroundImg = new JLabel(new ImageIcon(PATH_TO_SRC + "\\" + IMGS_DIR_NAME + "\\" + "tablaComida.png"));
		backgroundImg.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		backgroundImg.setBounds(10, 11, 1078, 695);
		PanelContenido.add(backgroundImg);
		
		
	}

	static void cargarOpciones(JComboBox primeros) {
		String ficheroPrimeros = new File ("").getAbsolutePath()+"\\src\\main\\java\\primeros.var";
		File fichero=new File(ficheroPrimeros);
		
		try {
			Scanner s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
				String linea = s.nextLine(); // Guardamos la linea en un String
				primeros.addItem(linea);
			}

			s.close();
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		}
	}


	static void verMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
}
