package VIEW;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipalVIEW extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalVIEW frame = new PrincipalVIEW();
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
	public PrincipalVIEW() {
		setTitle("Sistema Parque de Diversões");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 633);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGerenciar = new JMenu("Gerenciar");
		menuBar.add(mnGerenciar);
		
		JMenuItem mnitemCliente = new JMenuItem("Cliente");
		mnitemCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        ClienteVIEW clienteVIEW = new ClienteVIEW();
		        clienteVIEW.setVisible(true);
			}
		});
		mnGerenciar.add(mnitemCliente);
		
		JMenuItem mnitemFuncionario = new JMenuItem("Funcionário");
		mnitemFuncionario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        FuncionarioVIEW funcionarioVIEW = new FuncionarioVIEW();
		        funcionarioVIEW.setVisible(true);
			}
		});
		mnGerenciar.add(mnitemFuncionario);
		
		JMenu mnRelatorio = new JMenu("Relatório");
		mnRelatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RelatorioVIEW relatorioVIEW = new RelatorioVIEW();
		        relatorioVIEW.setVisible(true);
			}
		});
		
		JMenu mnLogin = new JMenu("Login");
		mnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				LoginVIEW loginVIEW = new LoginVIEW();
		        loginVIEW.setVisible(true);
			}
		});
		menuBar.add(mnLogin);
		menuBar.add(mnRelatorio);
		
		JMenu mnBackup = new JMenu("Backup");
		menuBar.add(mnBackup);
		
		JMenuItem mnitemGerar = new JMenuItem("Gerar");
		mnitemGerar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {				
		        JOptionPane.showMessageDialog(null, "Backup executado com sucesso!");
		}});
		mnBackup.add(mnitemGerar);
		
		JMenuItem mnitemRestaurar = new JMenuItem("Restaurar");
		mnitemRestaurar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        JOptionPane.showMessageDialog(null, "Backup restaurado com sucesso!");
			}
		});
		mnBackup.add(mnitemRestaurar);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        Object[] options = {"Sair", "Cancelar"};
		        if (JOptionPane.showOptionDialog(null, "Deseja mesmo sair do sistema?", "Atenção",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0) {
		            System.exit(0);
		        }
			}
		});
		menuBar.add(mnSair);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrincipalVIEW.class.getResource("/VIEW/img/parque.jpg")));
		lblNewLabel.setBounds(0, 0, 1033, 580);
		getContentPane().add(lblNewLabel);
		
		
	}
}
