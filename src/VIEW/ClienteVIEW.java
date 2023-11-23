package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import CTR.ClienteCTR;
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClienteVIEW extends JFrame {

    ClienteDTO clienteDTO = new ClienteDTO();
    ClienteCTR clienteCTR = new ClienteCTR();

    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    DefaultTableModel modeloJtlConsultarCliente;
    int gravar_alterar;
    ResultSet rs;
    
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField telefone;
	private JFormattedTextField cpf;
	private JTextField nome;
	private JFormattedTextField dtaNasc;
	private JTextField nomeConsulta;
	private JTable jtl_consultar_cliente;
	private static JButton btnExcluir;
	private static JButton btnSalvar;
	private static JButton btnCancelar;
	private static JButton btnNovo;
	private static JButton btnSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteVIEW frame = new ClienteVIEW();
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
	public ClienteVIEW() {
        initComponents();
        this.gravar_alterar = 1;
        modeloJtlConsultarCliente = (DefaultTableModel) this.jtl_consultar_cliente.getModel();
    }
	
	    private void initComponents() {	    
		setTitle("Gerenciar Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 505);
		contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(32000, 32767));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        //Define as máscaras
        MaskFormatter mascaraTel = null;
        MaskFormatter mascaraCpf = null;
        MaskFormatter mascaraData = null;

        try{
               mascaraTel = new MaskFormatter("(##)####-####");
               mascaraCpf = new MaskFormatter("#########-##");
               mascaraData = new MaskFormatter("##/##/####");
               mascaraTel.setPlaceholderCharacter('_');
               mascaraCpf.setPlaceholderCharacter('_');
               mascaraData.setPlaceholderCharacter('_');
        }
        catch(ParseException excp) {
               System.err.println("Erro na formatação: " + excp.getMessage());
               System.exit(-1);
        }
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cadastrar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 12, 582, 201);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 41, 84, 14);
		panel.add(lblNome);
		
		telefone = new JFormattedTextField(mascaraTel);
		telefone.setEnabled(false);
		telefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telefone.setBounds(86, 80, 143, 23);
		panel.add(telefone);
		telefone.setColumns(10);
		
		cpf = new JFormattedTextField(mascaraCpf);
		cpf.setEnabled(false);
		cpf.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		cpf.setBounds(339, 39, 167, 23);
		panel.add(cpf);
		cpf.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(294, 41, 35, 14);
		panel.add(lblCpf);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(10, 84, 71, 14);
		panel.add(lblTelefone);
		
		nome = new JTextField();
		nome.setEnabled(false);
		nome.setText("");
		nome.setBounds(54, 37, 230, 23);
		panel.add(nome);
		nome.setColumns(10);
		
		JLabel lblDataNasc = new JLabel("Data de Nascimento:");
		lblDataNasc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataNasc.setBounds(274, 84, 152, 14);
		panel.add(lblDataNasc);
		
		dtaNasc = new JFormattedTextField(mascaraData);
		dtaNasc.setEnabled(false);
		dtaNasc.setBounds(420, 80, 86, 23);
		panel.add(dtaNasc);
		dtaNasc.setColumns(10);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        if (gravar_alterar == 1) {
		            gravar();
		            gravar_alterar = 0;
		        } else if (gravar_alterar == 2) {
		            alterar();
		            gravar_alterar = 0;
		        } else {
		            JOptionPane.showMessageDialog(null, "Erro no sistema, tente novamente.");
		        }

		        limpaCampos();
			}
		});
		btnSalvar.setEnabled(false);
		btnSalvar.setIcon(new ImageIcon(ClienteVIEW.class.getResource("/VIEW/img/gravar.png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBounds(10, 140, 105, 30);
		panel.add(btnSalvar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        excluir();
		        limpaCampos();
		        modeloJtlConsultarCliente.setNumRows(0);
		        gravar_alterar = 1;
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(ClienteVIEW.class.getResource("/VIEW/img/excluir.png")));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExcluir.setBounds(241, 140, 105, 30);
		panel.add(btnExcluir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		       limpaCampos();
		       modeloJtlConsultarCliente.setNumRows(0);
		       gravar_alterar = 1;
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setIcon(new ImageIcon(ClienteVIEW.class.getResource("/VIEW/img/cancelar.png")));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(453, 140, 117, 30);
		panel.add(btnCancelar);
		
		btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(ClienteVIEW.class.getResource("/VIEW/img/sair.png")));
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSair.setBounds(357, 140, 91, 30);
		panel.add(btnSair);
		
		btnNovo = new JButton("Novo");
		btnNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				nome.setEnabled(true);
				cpf.setEnabled(true);
				telefone.setEnabled(true);
				dtaNasc.setEnabled(true);
				btnSalvar.setEnabled(true);
				btnNovo.setEnabled(false);
				btnCancelar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}
		});
		
		
		btnNovo.setIcon(new ImageIcon(ClienteVIEW.class.getResource("/VIEW/img/novo.png")));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNovo.setBounds(124, 140, 105, 30);
		panel.add(btnNovo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Pesquisar Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(129, 225, 381, 223);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNomePesquisa = new JLabel("Nome:");
		lblNomePesquisa.setBounds(21, 32, 46, 14);
		lblNomePesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblNomePesquisa);
		
		nomeConsulta = new JTextField();
		nomeConsulta.setBounds(69, 31, 190, 20);
		panel_1.add(nomeConsulta);
		nomeConsulta.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		     preencherTabela(nomeConsulta.getText().toUpperCase());
			}
		});
		btnBuscar.setBounds(269, 25, 91, 30);
		panel_1.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setIcon(new ImageIcon(ClienteVIEW.class.getResource("/VIEW/img/Pesquisar.png")));
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 66, 302, 135);
		panel_1.add(scrollPane);
		
		jtl_consultar_cliente = new JTable();
		jtl_consultar_cliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        preencherCampos(Integer.parseInt(String.valueOf(
		                jtl_consultar_cliente.getValueAt(jtl_consultar_cliente.getSelectedRow(), 0))
		        ));
			}
		});
		scrollPane.setViewportView(jtl_consultar_cliente);
		jtl_consultar_cliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome"
			}
		));		
	}
	
    private void alterar() {
        try {
            clienteDTO.setNome(this.nome.getText());
            clienteDTO.setCpf(this.cpf.getText());
            clienteDTO.setTelefone(this.telefone.getText());
            clienteDTO.setDataNasc(date.parse(this.dtaNasc.getText()));

            JOptionPane.showMessageDialog(null,
                    this.clienteCTR.alterar(clienteDTO));
        } catch (Exception e) {
            System.out.println("Erro alterar()" + e.getMessage());
        }
    }

    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o cliente?", "Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, this.clienteCTR.deletar(clienteDTO));
        }
    }

    private void preencherTabela(String nome) {
        try {
            this.modeloJtlConsultarCliente.setNumRows(0);

            clienteDTO.setNome(nome);
            rs = clienteCTR.consultar(clienteDTO, 1);

            while (rs.next()) {
                this.modeloJtlConsultarCliente.addRow(new Object[]{
                    rs.getString("idCliente"),
                    rs.getString("nome")
                });
            }
        } catch (Exception e) {
            System.out.println("Erro preencherTabela(): " + e.getMessage());
        } finally {
            clienteCTR.CloseDB();
        }
    }

    private void preencherCampos(int idCliente) {
        try {
            this.clienteDTO.setIdCliente(idCliente);
            rs = this.clienteCTR.consultar(clienteDTO, 2);
            if (rs.next()) {
                this.limpaCampos();

                this.nome.setText(rs.getString("nome"));
                this.cpf.setText(rs.getString("cpf"));
                this.telefone.setText(rs.getString("telefone"));                
                String anoMesDia[] = new String[3];
                anoMesDia = rs.getString("dataNasc").split("-");
                this.dtaNasc.setText(anoMesDia[2] + "/" + anoMesDia[1] + "/" + anoMesDia[0]);

                this.clienteDTO.setIdCliente(Integer.parseInt(rs.getString("idCliente")));

                this.gravar_alterar = 2;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            this.clienteCTR.CloseDB();
        }
    }

    private void gravar() {
        try {
            clienteDTO.setNome(this.nome.getText());
            clienteDTO.setCpf(this.cpf.getText());
            clienteDTO.setTelefone(this.telefone.getText());
            clienteDTO.setDataNasc(date.parse(dtaNasc.getText()));
            clienteDTO.setIdFunc(FuncionarioDTO.idFuncionarioLogado);
            JOptionPane.showMessageDialog(null, clienteCTR.inserir(clienteDTO));
        } catch (Exception e) {
            System.out.println("Erro gravar(): " + e.getMessage());
        }
    }


    private void limpaCampos() {
        this.nome.setText("");
        this.cpf.setText("");
        this.telefone.setText("");
        this.dtaNasc.setText("");
    }

}
