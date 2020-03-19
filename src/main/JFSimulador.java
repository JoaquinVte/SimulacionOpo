package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class JFSimulador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTemas;
	private JSpinner spinner;
	private JComboBox<Integer> comboBoxBolas;
	private JSlider slider;
	private JLabel label;
	private JButton btnSimular;
	private JTable table;
	private JTextPane textPane;

	/**
	 * Create the frame.
	 */
	public JFSimulador() {
		setTitle("Simulador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 763);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelSuperior = new JPanel();
		
		JPanel panelMedioInferior = new JPanel();
		
		JPanel panelInferior = new JPanel();
		
		JPanel panelMedioSuperior = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelMedioSuperior, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1173, Short.MAX_VALUE)
						.addComponent(panelMedioInferior, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1173, Short.MAX_VALUE)
						.addComponent(panelSuperior, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1173, Short.MAX_VALUE)
						.addComponent(panelInferior, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1173, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelSuperior, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMedioSuperior, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMedioInferior, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelInferior, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelMedioSuperior.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelMedioSuperior.add(scrollPane_1, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		panelSuperior.setLayout(new MigLayout("", "[][][][44.00][grow]", "[][]"));
		
		JLabel lblNumeroDeBolas = new JLabel("Numero de bolas");
		panelSuperior.add(lblNumeroDeBolas, "cell 0 0,alignx trailing");
		
		comboBoxBolas = new JComboBox<Integer>();
		comboBoxBolas.setMaximumRowCount(10);
		panelSuperior.add(comboBoxBolas, "cell 1 0,alignx left");
		
		JLabel lblTemasEstudiados = new JLabel("Temas estudiados");
		panelSuperior.add(lblTemasEstudiados, "cell 2 0,alignx right");
		
		textFieldTemas = new JTextField();
		textFieldTemas.setText("1, 2, 3, 4, 6, 7, 9, 10, 11, 12, 16, 17, 23, 24, 25, 26, 27, 28, 31, 32, 35, 36, 39, 61, 63, 64");
		textFieldTemas.setToolTipText("1,2,3,4,34,45");
		panelSuperior.add(textFieldTemas, "cell 3 0 2 1,growx");
		textFieldTemas.setColumns(10);
		
		JLabel lblBolasAExtraer = new JLabel("Bolas a extraer");
		panelSuperior.add(lblBolasAExtraer, "cell 0 1");
		
		spinner = new JSpinner();
		panelSuperior.add(spinner, "cell 1 1");
		
		JLabel lblSimulaciones = new JLabel("Simulaciones");
		panelSuperior.add(lblSimulaciones, "cell 2 1,alignx right");
		
		label = new JLabel("25");
		panelSuperior.add(label, "cell 3 1,alignx center");
		
		slider = new JSlider();
		slider.setValue(25);
		panelSuperior.add(slider, "cell 4 1,growx");
		panelMedioInferior.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelMedioInferior.add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnSimular = new JButton("Simular");
		panelInferior.add(btnSimular);
		contentPane.setLayout(gl_contentPane);
		
		inicializar();
	}	
	
	private void inicializar() {
		
		spinner.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		
		for(int i=0;i<=100;i++) {
			comboBoxBolas.addItem(i);
		}
		comboBoxBolas.setSelectedItem(74);
		
		slider.setPaintTicks(true); 
		slider.setMajorTickSpacing(25);
		slider.setMinorTickSpacing(5); 
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
				label.setText(String.valueOf(slider.getValue()));
			}
			
		});		
	}
	
	public JTextField getTextFieldTemas() {
		return textFieldTemas;
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public JComboBox<Integer> getComboBoxBolas() {
		return comboBoxBolas;
	}

	public JSlider getSlider() {
		return slider;
	}

	public JButton getBtnSimular() {
		return btnSimular;
	}

	public JTable getTable() {
		return table;
	}

	public JTextPane getTextPane() {
		return textPane;
	}
	
}
