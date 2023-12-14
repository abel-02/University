package view;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;

import presenter.Presenter;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingConstants;


public class PantallaInicio {
	private GestorInterfaz gestorInterfaz;
	private JFrame pantallaDeInicio;
	private JTextField inputNombre;
	private int nivelSeleccionado;
	private String nombreUsuario;
	private Presenter presenter;
	private String rutaLogoVerdeEncendido = "src/logo/VERDE_ENCENDIDO.png";
	private String rutaLogoVerdeApagado = "src/logo/VERDE_APAGADO.png";
	private final Color VERDE_ENCENDIDO = new Color(47, 195, 79), VERDE_APAGADO = new Color(43, 71, 39);
	private ImageIcon imageIcon;
	
	
	
	/**
	 * Create the application.
	 */
	
    /**
     * @wbp.parser.constructor
     */
	public PantallaInicio() {
		initialize();
	}
	
	public PantallaInicio(Presenter presenter) {
		this.presenter = presenter;
		initialize();
	}
	
	// Método para cargar una imagen desde un archivo
    private ImageIcon scaleImage(String imagePath, int width, int height) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		pantallaDeInicio = new JFrame();
		pantallaDeInicio.getContentPane().setBackground(new Color(47, 195, 79));
		pantallaDeInicio.setBounds(100, 100, 450, 300);
		pantallaDeInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantallaDeInicio.getContentPane().setLayout(null);
		pantallaDeInicio.setVisible(true);
		
		gestorInterfaz = new GestorInterfaz();
		
		inputNombre = new JTextField();
		inputNombre.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
		inputNombre.setBounds(243, 85, 170, 20);
		pantallaDeInicio.getContentPane().add(inputNombre);
		inputNombre.setColumns(10);
		
		JLabel avisoValidacionNombre = new JLabel("Por favor ingrese su nombre");
		avisoValidacionNombre.setHorizontalAlignment(SwingConstants.CENTER);
		avisoValidacionNombre.setBounds(243, 115, 170, 13);
		pantallaDeInicio.getContentPane().add(avisoValidacionNombre);
		avisoValidacionNombre.setVisible(false);
		
		Font fuente = avisoValidacionNombre.getFont(); // Obtiene la fuente actual
		fuente = fuente.deriveFont(10f); // Cambia el tamaño de la fuente a 14 puntos
		avisoValidacionNombre.setFont(new Font("JetBrains Mono", Font.PLAIN, 10)); // Aplica la nueva fuente al JLabel
		
		JLabel etiquetaNombre = new JLabel("Ingrese su nombre:");
		etiquetaNombre.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaNombre.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
		etiquetaNombre.setBounds(10, 89, 223, 13);
		pantallaDeInicio.getContentPane().add(etiquetaNombre);
		
		JLabel etiquetaDificultad = new JLabel("Elija el nivel de dificultad:");
		etiquetaDificultad.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaDificultad.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
		etiquetaDificultad.setBounds(10, 145, 223, 13);
		pantallaDeInicio.getContentPane().add(etiquetaDificultad);
		
		JComboBox inputComboBoxNivel = new JComboBox();
		inputComboBoxNivel.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
		inputComboBoxNivel.setBounds(243, 139, 170, 25);
		pantallaDeInicio.getContentPane().add(inputComboBoxNivel);
		
		inputComboBoxNivel.setModel(new DefaultComboBoxModel(new String [] {"Fácil (3x3)", "Normal (4x4)", "Difícil (5x5)", "Imposible (7x7)"}));
		// 3x3 , 4x4 , 5x5 ,7x7
		
		 JLabel imageLabel = new JLabel();
	     imageLabel.setSize(100, 75);
	     imageLabel.setLocation(165, 10);
	     imageIcon = scaleImage(rutaLogoVerdeEncendido, 120, 120);
	     imageLabel.setIcon(imageIcon);
	     pantallaDeInicio.getContentPane().add(imageLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Lights Out");
		lblNewLabel_2.setBounds(183, 38, 80, 15);
		pantallaDeInicio.getContentPane().add(lblNewLabel_2);
		

		pantallaDeInicio.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pantallaDeInicio.getContentPane().getBackground().equals(VERDE_ENCENDIDO)) {
					imageIcon = scaleImage(rutaLogoVerdeApagado, 120, 120);
				    imageLabel.setIcon(imageIcon);
					pantallaDeInicio.getContentPane().setBackground(VERDE_APAGADO);
					etiquetaNombre.setForeground(Color.WHITE);
					etiquetaDificultad.setForeground(Color.WHITE);
				}
				else if (pantallaDeInicio.getContentPane().getBackground().equals(VERDE_APAGADO)){
					imageIcon = scaleImage(rutaLogoVerdeEncendido, 120, 120);
					imageLabel.setIcon(imageIcon);
					pantallaDeInicio.getContentPane().setBackground(VERDE_ENCENDIDO);
					etiquetaNombre.setForeground(Color.BLACK);
					etiquetaDificultad.setForeground(Color.BLACK);
				}
			}
		});
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
		btnConfirmar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombreIngresado = inputNombre.getText().trim(); 
		        
		        if (nombreIngresado.length()<3) {
		        	avisoValidacionNombre.setForeground(Color.RED);
		        	avisoValidacionNombre.setOpaque(true);
		            avisoValidacionNombre.setVisible(true);
		        } else {
		            nivelSeleccionado = inputComboBoxNivel.getSelectedIndex();
		            nombreUsuario = nombreIngresado; 
		            presenter.iniciarJuego(nivelSeleccionado);
		            gestorInterfaz.abrirJuego(nombreUsuario, presenter);
		            
		            pantallaDeInicio.setVisible(false);
		        }

		        System.out.println("El nivel seleccionado fue: " + nivelSeleccionado);
		        System.out.println("El nombre del usuario es: " + nombreUsuario);
		    }
		});

		
		
		btnConfirmar.setBounds(165, 200, 120, 30);
		pantallaDeInicio.getContentPane().add(btnConfirmar);
		

	}
	
}
