package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.ComponentOrientation;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import CTR.FuncionarioCTR;
import DTO.FuncionarioDTO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;

public class FuncionarioVIEW extends JFrame {
    FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR();

    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    DefaultTableModel modelo_jtl_consultar_funcionario;
    int gravar_alterar;
    ResultSet rs;
    
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField senhaFuncionario;
	private JFormattedTextField cpfFuncionario;
	private JTextField nomeFuncionario;
	private JFormattedTextField dataNascFuncionario;
	private JTextField nomePesquisar;
	private JTable jtl_consultar_func;
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
					FuncionarioVIEW frame = new FuncionarioVIEW();
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
	public FuncionarioVIEW() {
		initComponents();
        this.gravar_alterar = 1;
        modelo_jtl_consultar_funcionario = (DefaultTableModel) this.jtl_consultar_func.getModel();
	}
		private void initComponents() {
		setTitle("Gerenciar Funcionarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        //Define as máscaras
        MaskFormatter mascaraCpf = null;
        MaskFormatter mascaraData = null;

        try{
               mascaraCpf = new MaskFormatter("#########-##");
               mascaraData = new MaskFormatter("##/##/####");
               mascaraCpf.setPlaceholderCharacter('_');
               mascaraData.setPlaceholderCharacter('_');
        }
        catch(ParseException excp) {
            System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
        }
        
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBounds(10, 11, 587, 452);
		contentPane_1.setLayout(null);
		contentPane_1.setMaximumSize(new Dimension(32000, 32767));
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cadastrar Funcionario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 12, 582, 201);
		contentPane_1.add(panel);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 41, 84, 14);
		panel.add(lblNome);
		
		senhaFuncionario = new JTextField();
		senhaFuncionario.setEnabled(false);
		senhaFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		senhaFuncionario.setColumns(10);
		senhaFuncionario.setBounds(54, 80, 184, 23);
		panel.add(senhaFuncionario);
		
		cpfFuncionario = new JFormattedTextField(mascaraCpf);
		cpfFuncionario.setEnabled(false);
		cpfFuncionario.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		cpfFuncionario.setColumns(10);
		cpfFuncionario.setBounds(339, 39, 158, 23);
		panel.add(cpfFuncionario);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(305, 41, 35, 14);
		panel.add(lblCpf);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(10, 84, 56, 14);
		panel.add(lblSenha);
		
		nomeFuncionario = new JTextField();
		nomeFuncionario.setEnabled(false);
		nomeFuncionario.setText("");
		nomeFuncionario.setColumns(10);
		nomeFuncionario.setBounds(54, 37, 230, 23);
		panel.add(nomeFuncionario);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(266, 84, 135, 14);
		panel.add(lblDataDeNascimento);
		
		dataNascFuncionario = new JFormattedTextField(mascaraData);
		dataNascFuncionario.setEnabled(false);
		dataNascFuncionario.setColumns(10);
		dataNascFuncionario.setBounds(398, 82, 99, 23);
		panel.add(dataNascFuncionario);
		
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
		btnSalvar.setIcon(new ImageIcon(FuncionarioVIEW.class.getResource("/VIEW/img/gravar.png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBounds(10, 140, 105, 30);
		panel.add(btnSalvar);
		
		btnNovo = new JButton("Novo");
		btnNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				nomeFuncionario.setEnabled(true);
				cpfFuncionario.setEnabled(true);
				senhaFuncionario.setEnabled(true);
				dataNascFuncionario.setEnabled(true);
				btnSalvar.setEnabled(true);
				btnNovo.setEnabled(false);
				btnCancelar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}
		});
		btnNovo.setIcon(new ImageIcon(FuncionarioVIEW.class.getResource("/VIEW/img/novo.png")));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNovo.setBounds(124, 140, 99, 30);
		panel.add(btnNovo);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        deletar();
		        limpaCampos();
		        modelo_jtl_consultar_funcionario.setNumRows(0);
		        gravar_alterar = 1;
		        }
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(FuncionarioVIEW.class.getResource("/VIEW/img/excluir.png")));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExcluir.setBounds(241, 140, 105, 30);
		panel.add(btnExcluir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			       limpaCampos();
			       modelo_jtl_consultar_funcionario.setNumRows(0);
			       gravar_alterar = 1;
				}
			});
		btnCancelar.setEnabled(false);
		btnCancelar.setIcon(new ImageIcon(FuncionarioVIEW.class.getResource("/VIEW/img/cancelar.png")));
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
		btnSair.setIcon(new ImageIcon(FuncionarioVIEW.class.getResource("/VIEW/img/sair.png")));
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSair.setBounds(357, 140, 91, 30);
		panel.add(btnSair);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Pesquisar Funcionario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(104, 225, 381, 212);
		contentPane_1.add(panel_1);
		
		JLabel lblNomePesquisa = new JLabel("Nome:");
		lblNomePesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomePesquisa.setBounds(21, 32, 46, 14);
		panel_1.add(lblNomePesquisa);
		
		nomePesquisar = new JTextField();
		nomePesquisar.setColumns(10);
		nomePesquisar.setBounds(69, 31, 190, 20);
		panel_1.add(nomePesquisar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        preencheTabela(nomePesquisar.getText().toUpperCase());}
		});
		btnBuscar.setIcon(new ImageIcon(FuncionarioVIEW.class.getResource("/VIEW/img/Pesquisar.png")));
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscar.setBounds(269, 25, 91, 30);
		panel_1.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 68, 295, 117);
		panel_1.add(scrollPane);
		
		jtl_consultar_func = new JTable();
		jtl_consultar_func.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        preencheCampos(Integer.parseInt(String.valueOf(
		                jtl_consultar_func.getValueAt(jtl_consultar_func.getSelectedRow(), 0))
		        ));
		        }
		});
		scrollPane.setViewportView(jtl_consultar_func);
		jtl_consultar_func.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome"
			}
		));
	}

	    public void gravar() {
	        try {
	            funcionarioDTO.setNome(nomeFuncionario.getText());
	            System.out.println(funcionarioDTO.getNome());
	            funcionarioDTO.setCpf(cpfFuncionario.getText());
	            funcionarioDTO.setSenha(senhaFuncionario.getText());
	            funcionarioDTO.setDataNasc(date.parse(dataNascFuncionario.getText()));
	            JOptionPane.showMessageDialog(null, funcionarioCTR.inserir(funcionarioDTO));
	        } catch (Exception e) {
	            System.out.println("Erro ao Gravar" + e.getMessage());
	        }
	    }

	    public void alterar() {
	        try {
	            funcionarioDTO.setNome(nomeFuncionario.getText());
	            funcionarioDTO.setSenha(senhaFuncionario.getText());
	            funcionarioDTO.setDataNasc(date.parse(dataNascFuncionario.getText()));
	            JOptionPane.showMessageDialog(null, funcionarioCTR.alterar(funcionarioDTO));
	        } catch (Exception e) {
	            funcionarioCTR.alterar(funcionarioDTO);
	        }
	    }

	    private void limpaCampos() {
	        nomeFuncionario.setText(null);
	        cpfFuncionario.setText(null);
	        senhaFuncionario.setText(null);
	        dataNascFuncionario.setText(null);
	    }

	    private void preencheCampos(int idFunc) {
	        try {
	            funcionarioDTO.setIdFunc(idFunc);
	            rs = funcionarioCTR.consultar(funcionarioDTO, 2);
	            if (rs.next()) {
	                limpaCampos();
	                nomeFuncionario.setText(rs.getString("nome"));
	                cpfFuncionario.setText(rs.getString("cpf"));
	                String anoMesDia[] = new String[3];
	                anoMesDia = rs.getString("dataNasc").split("-");
	                this.dataNascFuncionario.setText(anoMesDia[2] + "/" + anoMesDia[1] + "/" + anoMesDia[0]);
	                this.gravar_alterar = 2;
	            }

	        } catch (Exception e) {
	            System.out.println("VIEW.FuncionarioVIEW.preencheCampos(): " + e.getMessage());
	        }
	    }

	    private void deletar() {
	        if (JOptionPane.showConfirmDialog(null, "deseja realmente deletar o funcionario?", "Aviso",
	                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	            JOptionPane.showMessageDialog(null,
	                    funcionarioCTR.deletar(funcionarioDTO)
	            );
	        }
	    }

	    private void preencheTabela(String nome) {
	        try {
	            modelo_jtl_consultar_funcionario.setNumRows(0);
	            funcionarioDTO.setNome(nome);
	            rs = funcionarioCTR.consultar(funcionarioDTO, 1);
	            while (rs.next()) {
	                modelo_jtl_consultar_funcionario.addRow(new Object[]{
	                    rs.getString("idFunc"),
	                    rs.getString("nome"),});
	            }
	        } catch (Exception erTab) {
	            System.out.println("Erro SQL: " + erTab);
	        } finally {
	            funcionarioCTR.CloseDB();
	        }
	    }

}
