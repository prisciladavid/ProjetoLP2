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
import CTR.GeraRelatorioFunc;

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
		setBounds(100, 100, 544, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 508, 214);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRelatorio = new JLabel("Relatório");
		lblRelatorio.setIcon(new ImageIcon(RelatorioVIEW.class.getResource("/VIEW/img/relatorio.png")));
		lblRelatorio.setBounds(179, 9, 157, 32);
		lblRelatorio.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel.add(lblRelatorio);
		
		JButton btnGerarCliente = new JButton("Gerar Relatório Cliente");
		btnGerarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GeraRelatorio();
			}
		});
		btnGerarCliente.setIcon(new ImageIcon(RelatorioVIEW.class.getResource("/VIEW/img/report.png")));
		btnGerarCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGerarCliente.setBounds(163, 127, 203, 34);
		panel.add(btnGerarCliente);
		
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
		btnSair.setBounds(223, 172, 93, 33);
		panel.add(btnSair);
		
		JButton btnGerarFunc = new JButton("Gerar Relatório Funcionário");
		btnGerarFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GeraRelatorioFunc();
			}
		});
		btnGerarFunc.setIcon(new ImageIcon(RelatorioVIEW.class.getResource("/VIEW/img/report.png")));
		btnGerarFunc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGerarFunc.setBounds(151, 82, 224, 34);
		panel.add(btnGerarFunc);
	}
}
