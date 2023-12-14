package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import presenter.Presenter;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

public class PantallaJuego {

	private JFrame lightsOut;
	private JTextField tituloJuego;
	private JTextField resultado;
	private JPanel seccionTurnos;
	private JLabel etiquetaTurnos;
	private JPanel contenerdorTurnos;
	private JTextField contenedorDeTurnos;
	private JPanel panelJuego;
	private Button[][] celdas;
	private Presenter presenter;
	private JButton botonMenu;
	// private int nivelSeleccionado;
	private String nombreUsuario;
	private GestorInterfaz gestorInterfaz;
	private int contadorTurnos;
	private int recordHistorico;
	private String sonidoParaBotones = "src/sonidos/cartoon-jump-6462.wav";
	private String sonidoCuandoGana = "src/sonidos/success-fanfare-trumpets-6185.wav";
	private final Color VERDE_ENCENDIDO = new Color(47, 195, 79), VERDE_APAGADO = new Color(43, 71, 39),
			VERDE_INTERMEDIO1 = new Color(43, 131, 39), VERDE_INTERMEDIO2 = new Color(43, 157, 39), 
			FONDO_OSCURO = new Color(40, 40, 40), FONDO_TABLERO = new Color(65, 119, 64);
	private final Font FUENTE_TITULO = new Font("JetBrains Mono NL Medium", Font.BOLD | Font.ITALIC, 18),
			FUENTE_ETIQUETA = new Font("JetBrains Mono Light", Font.PLAIN, 14),
			FUENTE_CONTENEDOR = new Font("JetBrains Mono", Font.PLAIN, 14),
			FUENTE_ESTADO = new Font("Monospaced", Font.BOLD | Font.ITALIC, 15),
			FUENTE_MENU = new Font("JetBrains Mono Light", Font.PLAIN, 16);
	private JPanel seccionRecord;
	private JLabel etiquetaRecord;
	private JPanel contenedorRecord;
	private JTextField contenedorRecordActual;

	
	/**
	 * @wbp.parser.constructor
	 */
	
	// Primera vez que se crea
	public PantallaJuego(String nombreUsuario, GestorInterfaz gtrInterfaz) {
		construirPantallaJuego(nombreUsuario, gtrInterfaz);
	}

	// A partir de la segunda
	public PantallaJuego(String nombreUsuario, GestorInterfaz gtrInterfaz, Presenter presenter) {
		this.presenter = presenter;
		construirPantallaJuego(nombreUsuario, gtrInterfaz);
	}

	private void construirPantallaJuego(String nombreUsuario, GestorInterfaz gtrInterfaz) {
		this.nombreUsuario = nombreUsuario;
		this.contadorTurnos = 0;
		this.recordHistorico = presenter.consultarRecordHistorico();
		gestorInterfaz = gtrInterfaz;
		initialize();
	}

	public void reproducirSonido(String rutaArchivoSonido) {
		try {
			// Cargar el archivo de sonido
			File archivoSonido = new File(rutaArchivoSonido);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoSonido);
			
			// Crear un Clip para reproducir el sonido
			Clip clip = AudioSystem.getClip();
			
			// Abrir el flujo de audio y cargar el Clip
			clip.open(audioInputStream);
			
			// Reproducir el sonido
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		presenter.estadoDelTablero();

		lightsOut = new JFrame();
		getLightsOut().getContentPane().setForeground(new Color(0, 0, 0));
		getLightsOut().setBackground(Color.WHITE);
		getLightsOut().setForeground(Color.WHITE);
		getLightsOut().setBounds(100, 100, 700, 500);
		getLightsOut().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getLightsOut().getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel contenedorTitulo = new JPanel();
		contenedorTitulo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getLightsOut().getContentPane().add(contenedorTitulo, BorderLayout.NORTH);
		contenedorTitulo.setLayout(new BorderLayout(0, 0));

		tituloJuego = new JTextField();
		tituloJuego.setColumns(10);
		tituloJuego.setEditable(false);
		tituloJuego.setForeground(Color.WHITE);
		tituloJuego.setHorizontalAlignment(SwingConstants.CENTER);
		tituloJuego.setBackground(FONDO_OSCURO);
		tituloJuego.setFont(FUENTE_TITULO);
		tituloJuego.setText("Lights out");
		contenedorTitulo.add(tituloJuego, BorderLayout.CENTER);

		seccionTurnos = new JPanel();
		FlowLayout flowLayout = (FlowLayout) seccionTurnos.getLayout();
		seccionTurnos.setBorder(null);
		contenedorTitulo.add(seccionTurnos, BorderLayout.EAST);

		etiquetaTurnos = new JLabel("Turnos: ");
		etiquetaTurnos.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaTurnos.setFont(FUENTE_ETIQUETA);
		seccionTurnos.add(etiquetaTurnos);

		contenerdorTurnos = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) contenerdorTurnos.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		contenerdorTurnos.setBorder(null);
		seccionTurnos.add(contenerdorTurnos);

		contenedorDeTurnos = new JTextField("" + contadorTurnos);
		contenedorDeTurnos.setHorizontalAlignment(SwingConstants.CENTER);
		contenedorDeTurnos.setForeground(Color.WHITE);
		etiquetaTurnos.setLabelFor(contenedorDeTurnos);
		contenedorDeTurnos.setBackground(FONDO_OSCURO);
		contenedorDeTurnos.setFont(FUENTE_CONTENEDOR);
		contenedorDeTurnos.setEditable(false);
		contenedorDeTurnos.setColumns(5);
		contenerdorTurnos.add(contenedorDeTurnos);

		seccionRecord = new JPanel();
		contenedorTitulo.add(seccionRecord, BorderLayout.WEST);

		etiquetaRecord = new JLabel("Record: ");
		etiquetaRecord.setFont(FUENTE_ETIQUETA);
		etiquetaRecord.setHorizontalAlignment(SwingConstants.CENTER);
		seccionRecord.add(etiquetaRecord);

		contenedorRecord = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) contenedorRecord.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		seccionRecord.add(contenedorRecord);

		contenedorRecordActual = new JTextField("" + recordHistorico);
		etiquetaRecord.setLabelFor(contenedorRecordActual);

		contenedorRecordActual.setHorizontalAlignment(SwingConstants.CENTER);
		contenedorRecordActual.setForeground(Color.WHITE);
		contenedorRecordActual.setBackground(FONDO_OSCURO);
		contenedorRecordActual.setFont(FUENTE_CONTENEDOR);
		contenedorRecordActual.setEditable(false);
		contenedorRecordActual.setColumns(5);
		contenedorRecord.add(contenedorRecordActual);

		JPanel estado = new JPanel();
		estado.setBorder(new LineBorder(Color.BLACK, 8));
		getLightsOut().getContentPane().add(estado, BorderLayout.SOUTH);
		estado.setLayout(new BoxLayout(estado, BoxLayout.X_AXIS));
		resultado = new JTextField("¡ ¡ ¡ Ánimo " + nombreUsuario + " ! ! !");
		resultado.setEditable(false);
		resultado.setFont(FUENTE_ESTADO);
		resultado.setForeground(Color.WHITE);
		resultado.setHorizontalAlignment(SwingConstants.CENTER);
		resultado.setBackground(FONDO_OSCURO);
		estado.add(resultado);
		resultado.setColumns(10);

		botonMenu = new JButton("Menú");

		// Boton que simula una partida ganada.
		botonMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gestorInterfaz.irAMenu(getPantallaJuego(), presenter);
			}
		});

		botonMenu.setFont(FUENTE_MENU);
		estado.add(botonMenu);
		
		panelJuego = new JPanel();
		panelJuego.setBackground(FONDO_TABLERO);
		panelJuego.setBorder(new EmptyBorder(25, 25, 25, 25));
		getLightsOut().getContentPane().add(panelJuego, BorderLayout.CENTER);
		if (presenter.largoDelTablero()!=0)
			panelJuego.setLayout(new GridLayout(presenter.largoDelTablero(), presenter.largoDelTablero(), 20, 20));

		crearTableroConCeldas();
	}
	
	private void crearTableroConCeldas() {
		// se inicializa con el tamaño del tablero
		celdas = new Button[presenter.largoDelTablero()][presenter.largoDelTablero()];
		
		for (int i = 0; i < celdas.length; i++) {
			int fila = i;
			for (int j = 0; j < celdas[i].length; j++) {
				int columna = j;
				celdas[fila][columna] = new Button();				
				colorearBotonSegunElEstadoRandom(fila, columna);
				asignarEventosDeCelda(fila, columna);
				panelJuego.add(celdas[fila][columna]);
			}
		}
	}

	/* Colorea los botones de forma aleatoria al inicio de la partida */
	private void colorearBotonSegunElEstadoRandom(int fila, int columna) {
		if (presenter.estaEncendida(fila, columna))
			colorearBoton(celdas[fila][columna], VERDE_ENCENDIDO);
		else
			colorearBoton(celdas[fila][columna], VERDE_APAGADO);
	}
	
	private void colorearBoton(Button boton, Color color) {
		boton.setBackground(color);
	}
	
	private void asignarEventosDeCelda(int fila, int columna) {
		
		celdas[fila][columna].addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				reproducirSonido(sonidoParaBotones);
				// Bloqueador de tablero
				if (presenter.verificaEstadoDelTablero())
					resultado.setText("!!! Felicidades, acabás de ganar el juego ¡¡¡");
				else {
					colorearVecinos(fila, columna);
					contenedorDeTurnos.setText("" + ++contadorTurnos);
					presenter.estadoDelTablero();
					// Verificador de victoria
					if (presenter.verificaEstadoDelTablero()) {
						resultado.setText("!!! Felicidades, acabás de ganar el juego ¡¡¡");
						reproducirSonido(sonidoCuandoGana);
						botonMenu.setText("VOLVER A JUGAR");
						// New record - Actualiza el record 
						if (contadorTurnos < recordHistorico || recordHistorico == 0)
							presenter.registrarRecord(contadorTurnos);
					return ;
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (celdas[fila][columna].getBackground().equals(VERDE_ENCENDIDO))
					colorearBoton(celdas[fila][columna], VERDE_INTERMEDIO1);
				else if (celdas[fila][columna].getBackground().equals(VERDE_APAGADO))
					colorearBoton(celdas[fila][columna], VERDE_INTERMEDIO2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (celdas[fila][columna].getBackground().equals(VERDE_INTERMEDIO2))
					colorearBoton(celdas[fila][columna], VERDE_APAGADO);
				else if (celdas[fila][columna].getBackground().equals(VERDE_INTERMEDIO1))
					colorearBoton(celdas[fila][columna], VERDE_ENCENDIDO);
			}
		});
	}
	
	private void colorearVecinos(int fila, int col) {
		colorearVecinosFila(col);
		colorearVecinosColumna(fila, col);
	}

	private void colorearVecinosFila(int col) {
		for (int row = 0; row < celdas.length; row++) {
			if (presenter.estaEncendida(row, col))
				colorearBoton(celdas[row][col], VERDE_APAGADO);
			else
				colorearBoton(celdas[row][col], VERDE_ENCENDIDO);
			presenter.cambiarEstado(row, col);
		}
	}

	private void colorearVecinosColumna(int fila, int colum) {
		for (int col = 0; col < celdas.length; col++) {
			if (col != colum) {
				if (presenter.estaEncendida(fila, col))
					colorearBoton(celdas[fila][col], VERDE_APAGADO);
				else
					colorearBoton(celdas[fila][col], VERDE_ENCENDIDO);
				presenter.cambiarEstado(fila, col);
			}
		}
	}
	
	
	protected JFrame getLightsOut() {
		return lightsOut;
	}

	private PantallaJuego getPantallaJuego() {
		return this;
	}

}
