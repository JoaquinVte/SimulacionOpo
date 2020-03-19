package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Simulacion implements ActionListener {

	private JFSimulador frame;
	private int cantidadBolas;
	private int[] preparados;
	private int iteraciones;
	private int totalTemas;
	private Map<Integer, Boolean> bolas;
	private Map<Integer, Boolean> temas;

	public static void main(String[] args) {

		Simulacion s = new Simulacion();
		s.start();
	}

	public Simulacion() {

		// Maps para contabilizar los temas
		bolas = new HashMap<>();
		temas = new HashMap<>();

		frame = new JFSimulador();

		inicializar();
	}

	private void inicializar() {
		frame.getBtnSimular().addActionListener(this);
	}

	private void start() {
		frame.setVisible(true);
	}

	private void configurarTabla() {
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		DefaultTableModel dtm = new DefaultTableModel();
		
		dtm.addColumn("Simulacion nº");
		
		for (int i = 0; i < cantidadBolas; i++) {
			dtm.addColumn("Bola " + (i + 1));
			
		}		
		dtm.addColumn("Exito");
		dtm.addColumn("Nº aciertos");
		frame.getTable().setModel(dtm);
		
		for (int i = 0; i < cantidadBolas+3; i++) {
			frame.getTable().getColumnModel().getColumn(i).setCellRenderer(tcr);
		}
	
	}

	private void obtenerValoresDeFrame() {
		String[] preparadosAsString = frame.getTextFieldTemas().getText().replaceAll(" ", "").split(",");
		preparados = new int[preparadosAsString.length];

		for (int i = 0; i < preparadosAsString.length; i++)
			preparados[i] = Integer.parseInt(preparadosAsString[i]);

		// Numero de simulaciones
		iteraciones = frame.getSlider().getValue();

		// Cantidad de bolas
		cantidadBolas = (int) frame.getSpinner().getValue();

		// Cantidad de temas
		totalTemas = (int) frame.getComboBoxBolas().getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		obtenerValoresDeFrame();
		configurarTabla();

		frame.getTextPane().setText("");

		DefaultTableModel dtm = (DefaultTableModel) frame.getTable().getModel();

		int bolaAletoria;
		float global = 0;

		for (int j = 0; j < iteraciones; j++) {

			Vector<Object> v = new Vector<Object>();
			
			v.add(j+1);

			// Inicializamos los Map
			for (int i = 1; i <= totalTemas; i++) {
				bolas.put(i, false);
				temas.put(i, false);
			}

			// Marcamos los temas aprendidos
			for (int tema : preparados)
				temas.put(tema, true);

			// Inicializamos variables
			int bolasSacadas = 0, tocados = 0;
			boolean exito = false;

			while (bolasSacadas < cantidadBolas) {

				// Calculamos una bola aleatoria
				bolaAletoria = (int) (Math.random() * totalTemas + 1);

				// Comprobamos si ya ha salido y procedemos segun el caso
				if (bolas.get(bolaAletoria) == false) {

					bolas.put(bolaAletoria, true);

					if (temas.get(bolaAletoria)) {
						exito = true;
						tocados++;
					}

					v.add(bolaAletoria);
					bolasSacadas++;
				}
			}

			// Contabilizamos por iteracion si ha habido exito, y en su caso cuantos temas
			// han salido.
			if(exito)
				global++;
			
			v.add(exito);
			v.add(tocados);

			dtm.addRow(v);
		}

		// Mostramos estadisticas
		float porcentaje = (global / iteraciones) * 100;

		frame.getTextPane()
				.setText("\nIteraciones: " + iteraciones + "  Exito: " + global + "  Fracaso: " + (iteraciones - global)
						+ "  Numero de temas:" + totalTemas + "  Temas estudiados: " + preparados.length);

		frame.getTextPane().setText(frame.getTextPane().getText() + "\nExito: " +porcentaje + "%     Fracaso: " + (100 -  porcentaje));

	}
}