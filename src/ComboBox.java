import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ComboBox extends JFrame implements ActionListener {
	
	
	private final String PATH_TO_SRC = "C:\\Users\\Usuario\\Desktop\\ComboBoxRestaurante\\src";
	private final String IMGS_DIR_NAME = "imgs";
	private final String MODEL_DIR_NAME = "model";
			
	private static final long serialVersionUID = 1L;
	private JPanel PanelContenido;
	private JComboBox<String> comboBoxPrimeros;
	private JComboBox<String> comboBoxSegundos;
	private JComboBox<String> comboBoxPostres;
	private JComboBox<String> comboBoxBebidas;
	private JLabel primeroImg = new JLabel();
	private JLabel segundoImg = new JLabel();
	private JLabel postreImg = new JLabel();
	private JLabel bebidaImg = new JLabel();
	private JLabel backgroundImg;
	private JLabel restaurantTitle;
	private FileWriter comanda;
	JButton deliverButton;


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
		//setBounds(100, 100, 1232, 795);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		PanelContenido = new JPanel();
		PanelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(PanelContenido);
		PanelContenido.setLayout(null);
		PanelContenido.setBounds(0, 0, getWidth(), getHeight());
		
				comboBoxPrimeros = new JComboBox<String>();
				comboBoxPrimeros.setBounds(83, 146, 229, 26);
				comboBoxPrimeros.addActionListener(this);
				
				comboBoxSegundos = new JComboBox<String>();
				comboBoxSegundos.setBounds(355, 146, 229, 26);
				comboBoxSegundos.addActionListener(this);
				
						comboBoxPostres = new JComboBox<String>();
						comboBoxPostres.setBounds(289, 337, 229, 26);
						comboBoxPostres.addActionListener(this);
						
								comboBoxBebidas = new JComboBox<String>();
								comboBoxBebidas.setBounds(598, 337, 229, 26);
								comboBoxBebidas.addActionListener(this);
								
								deliverButton = new JButton("Trae a comida ostia");
								deliverButton.setBorderPainted(false);
								deliverButton.setBackground(new Color(179, 89, 0));
								deliverButton.setFont(new Font("Ink Free", Font.BOLD, 30));
								
								deliverButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(e.getSource() == deliverButton) {
											deliverButton.setEnabled(false);
											deliverButton.setVisible(false);
											try {
												// Una comanda.txt para cada pedido
												LocalDateTime now = LocalDateTime.now();
												DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
												String fechaFormateada = now.format(formatter);
												String nombreArchivo = "comanda-" + fechaFormateada + ".txt";
												comanda = new FileWriter(PATH_TO_SRC + "\\" + MODEL_DIR_NAME + "\\" + nombreArchivo);
												
												
												DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
												String fechaFormateada2 = now.format(formatter2);
												
												comanda.write("Comanda con fecha " + fechaFormateada2 + "\n" +
														"\tPrimero: " + comboBoxPrimeros.getSelectedItem() + "\n" +
														"\tSegundo: " + comboBoxSegundos.getSelectedItem() + "\n" +
														"\tBebida: " + comboBoxPostres.getSelectedItem() + "\n" +
														"\tPostre: " + comboBoxBebidas.getSelectedItem() + "\n"
												);
												comanda.close();
												verMensaje("Tu pedido:\n " +
														"	Primero: " + comboBoxPrimeros.getSelectedItem() + "\n" +
														"	Segundo: " + comboBoxSegundos.getSelectedItem() + "\n" +
														"	Bebida: " + comboBoxPostres.getSelectedItem() + "\n" +
														"	Postre: " + comboBoxBebidas.getSelectedItem() + "\n");
												dispose();
												main(null);
												
											} catch (IOException e1) {
												e1.printStackTrace();
											}
										}
									}
								});
								
								restaurantTitle = new JLabel("Taberna do Carallo");
								restaurantTitle.setBackground(new Color(0, 255, 0));
								restaurantTitle.setFont(new Font("Ink Free", Font.BOLD, 80));
								restaurantTitle.setBounds(242, 115, 752, 135);
								PanelContenido.add(restaurantTitle);
								
								deliverButton.setBounds(582, 598, 334, 58);
								PanelContenido.add(deliverButton);
								PanelContenido.add(comboBoxBebidas);
						PanelContenido.add(comboBoxPostres);
				PanelContenido.add(comboBoxSegundos);
				PanelContenido.add(comboBoxPrimeros);
		
		cargarOpciones();

		backgroundImg = new JLabel(new ImageIcon(PATH_TO_SRC + "\\" + IMGS_DIR_NAME + "\\" + "tablaComida.png"));
		backgroundImg.setBounds(0, 0, getWidth(), getHeight());
		PanelContenido.add(backgroundImg);
		
	}

	public void cargarOpciones() {
		String pathFicheroPrimeros = new File ("").getAbsolutePath()+"\\src\\model\\primeros.txt";
		String pathFicheroSegundos = new File ("").getAbsolutePath()+"\\src\\model\\segundos.txt";
		String pathFicheroPostres = new File ("").getAbsolutePath()+"\\src\\model\\postres.txt";
		String pathFicheroBebidas = new File ("").getAbsolutePath()+"\\src\\model\\bebidas.txt";
		
		File ficheroPrimeros = new File(pathFicheroPrimeros);
		File ficheroSegundos = new File(pathFicheroSegundos);
		File ficheroPostres = new File(pathFicheroPostres);
		File ficheroBebidas = new File(pathFicheroBebidas);
		
		try {
			Scanner s1 = new Scanner(ficheroPrimeros);
			Scanner s2 = new Scanner(ficheroSegundos);
			Scanner sb = new Scanner(ficheroPostres);
			Scanner sp = new Scanner(ficheroBebidas);
			
			while (s1.hasNextLine()) {
				String linea = s1.nextLine();
				comboBoxPrimeros.addItem(linea);
			}
			
			while (s2.hasNextLine()) {
				String linea = s2.nextLine();
				comboBoxSegundos.addItem(linea);
			}
			
			while (sp.hasNextLine()) {
				String linea = sp.nextLine();
				comboBoxPostres.addItem(linea);
			}
			
			while (sb.hasNextLine()) {
				String linea = sb.nextLine();
				comboBoxBebidas.addItem(linea);
			}
						
			s1.close();
			s2.close();
			sp.close();
			sb.close();
			
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void verMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
		    if (comboBoxPrimeros.getSelectedIndex() != 0) {
		        String selectedItem = (String) comboBoxPrimeros.getSelectedItem();
		        if (selectedItem != null) {
		            primeroImg.setIcon(new ImageIcon(PATH_TO_SRC + "\\" + IMGS_DIR_NAME + "\\" +
		                    selectedItem.replaceAll("\\s", "") + ".png"));
		            primeroImg.setBackground(new Color(0, 0, 255));
		            primeroImg.setBounds(289, 171, 230, 160);       
		            PanelContenido.add(primeroImg);
		            PanelContenido.setComponentZOrder(primeroImg, 0);
		        }
		    } 
		    
		    if (comboBoxSegundos.getSelectedIndex() != 0) {
		        String selectedItem = (String) comboBoxSegundos.getSelectedItem();
		        if (selectedItem != null) {
		            segundoImg.setIcon(new ImageIcon(PATH_TO_SRC + "\\" + IMGS_DIR_NAME + "\\" +
		                    selectedItem.replaceAll("\\s", "") + ".png")
		            );
		            segundoImg.setBounds(598, 171, 230, 160);
		            PanelContenido.add(segundoImg);
		            PanelContenido.setComponentZOrder(segundoImg, 0);
		        }
		    } 
		    
		    if (comboBoxPostres.getSelectedIndex() != 0) {
		        String selectedItem = (String) comboBoxPostres.getSelectedItem();
		        if (selectedItem != null) {
		            postreImg.setIcon(new ImageIcon(PATH_TO_SRC + "\\" + IMGS_DIR_NAME + "\\" +
		                    selectedItem.replaceAll("\\s", "") + ".png")
		            );
		            postreImg.setBounds(288, 373, 230, 160);
		            PanelContenido.add(postreImg);
		            PanelContenido.setComponentZOrder(postreImg, 0);
		        }
		    } 
		    
		    if (comboBoxBebidas.getSelectedIndex() != 0) {
		        String selectedItem = (String) comboBoxBebidas.getSelectedItem();
		        if (selectedItem != null) {
		            bebidaImg.setIcon(new ImageIcon(PATH_TO_SRC + "\\" + IMGS_DIR_NAME + "\\" +
		                    selectedItem.replaceAll("\\s", "") + ".png")
		            );
		            bebidaImg.setBounds(598, 374, 230, 160);
		            PanelContenido.add(bebidaImg);
		            PanelContenido.setComponentZOrder(bebidaImg, 0);
		        }
		    }
		    
		    
		    
		} catch (Exception exc) {
		    System.err.println("Error durante la creación de la imagen.");
		    exc.printStackTrace();
		}


	}
}
