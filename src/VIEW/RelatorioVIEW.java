package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CTR.GeraRelatorio;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RelatorioVIEW extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numeroTotalClientes;
	private JTextField numeroTotalFuncionarios;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioVIEW frame = new RelatorioVIEW();
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
	public RelatorioVIEW() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 508, 356);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRelatorio = new JLabel("Relatório");
		lblRelatorio.setIcon(new ImageIcon(RelatorioVIEW.class.getResource("/VIEW/img/relatorio.png")));
		lblRelatorio.setBounds(179, 9, 157, 32);
		lblRelatorio.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel.add(lblRelatorio);
		
		JLabel lblNewLabel = new JLabel("Total de Clientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(116, 54, 138, 22);
		panel.add(lblNewLabel);
		
		numeroTotalClientes = new JTextField();
		numeroTotalClientes.setBounds(264, 55, 96, 26);
		panel.add(numeroTotalClientes);
		numeroTotalClientes.setColumns(10);
		
		numeroTotalFuncionarios = new JTextField();
		numeroTotalFuncionarios.setText("");
		numeroTotalFuncionarios.setBounds(264, 107, 96, 26);
		panel.add(numeroTotalFuncionarios);
		numeroTotalFuncionarios.setColumns(10);
		
		JLabel lblTotalDeFuncionarios = new JLabel("Total de Funcionarios");
		lblTotalDeFuncionarios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotalDeFuncionarios.setBounds(75, 106, 169, 22);
		panel.add(lblTotalDeFuncionarios);
		
		JLabel lblFuncCli = new JLabel("Clientes por Funcionário");
		lblFuncCli.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncCli.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFuncCli.setBounds(27, 169, 210, 22);
		panel.add(lblFuncCli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 202, 174, 88);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Funcion\u00E1rio", "Qtd. Clientes"
			}
		));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(291, 202, 174, 88);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data", "Qtd. de Clientes"
			}
		));
		
		JLabel lblDataCli = new JLabel("Clientes por Mês e Ano");
		lblDataCli.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataCli.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDataCli.setBounds(280, 171, 195, 22);
		panel.add(lblDataCli);
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GeraRelatorio();
			}
		});
		btnGerar.setIcon(new ImageIcon(RelatorioVIEW.class.getResource("/VIEW/img/report.png")));
		btnGerar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGerar.setBounds(168, 302, 92, 34);
		panel.add(btnGerar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PrincipalVIEW principal = new PrincipalVIEW();
				principal.setVisible(true);
			}
		});
		btnSair.setIcon(new ImageIcon(RelatorioVIEW.class.getResource("/VIEW/img/sair.png")));
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSair.setBounds(271, 303, 93, 33);
		panel.add(btnSair);
	}
}
