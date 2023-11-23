package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import CTR.FuncionarioCTR;
import DTO.FuncionarioDTO;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginVIEW extends JFrame {
    /**
	 * 
	 */
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR();
	private static final long serialVersionUID = 1L;
	private JTextField cpf;
    private JPasswordField senha;

    public LoginVIEW() {
    	
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 262);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(25, 13, 284, 199);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblUsuario = new JLabel("CPF:");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsuario.setBounds(44, 68, 35, 14);
        panel.add(lblUsuario);

        cpf = new JTextField();
        cpf.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (loginFuncionario()) {
                        new PrincipalVIEW().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "CPF ou Senha incorretos");
                    }
                }
        	}
        });
        cpf.setBounds(85, 67, 160, 20);
        panel.add(cpf);
        cpf.setColumns(10);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSenha.setBounds(33, 104, 46, 14);
        panel.add(lblSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (loginFuncionario()) {
                        new PrincipalVIEW().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "CPF ou Senha incorretos");
                    }
                }
        	}
        });
        btnEntrar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
                if (loginFuncionario()) {
                    new PrincipalVIEW().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou Senha incorretos");
                }
        	}
        });
        btnEntrar.setIcon(new ImageIcon(LoginVIEW.class.getResource("/VIEW/img/Entrar.png")));
        btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEntrar.setBounds(38, 153, 107, 23);
        panel.add(btnEntrar);
        
        senha = new JPasswordField();
        senha.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (loginFuncionario()) {
                        new PrincipalVIEW().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "CPF ou Senha incorretos");
                    }
                }
        	}
        });
        senha.setBounds(85, 103, 158, 20);
        panel.add(senha);
        
        JButton btnSair = new JButton("Sair");
        btnSair.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		dispose();
        	}
        });
        btnSair.setIcon(new ImageIcon(LoginVIEW.class.getResource("/VIEW/img/sair.png")));
        btnSair.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSair.setBounds(155, 153, 94, 23);
        panel.add(btnSair);
        
        JLabel lblNewLabel_1 = new JLabel("Login Funcionário");
        lblNewLabel_1.setBounds(54, 11, 181, 40);
        panel.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_1.setIcon(new ImageIcon(LoginVIEW.class.getResource("/VIEW/img/funcionario2.png")));
    }
    
    private boolean loginFuncionario() {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setCpf(cpf.getText());
        funcionarioDTO.setSenha(senha.getText());
        int idFuncionarioLogado = funcionarioCTR.loginFuncionario(funcionarioDTO);
        if (idFuncionarioLogado == 0) {
            return false;
        }
        FuncionarioDTO.idFuncionarioLogado = idFuncionarioLogado;
        return true;
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginVIEW().setVisible(true);
            }
        });
    }
}
