package CTR;

import java.sql.ResultSet;

import DAO.ConexaoDAO;
import DAO.FuncionarioDAO;
import DTO.FuncionarioDTO;

public class FuncionarioCTR implements BasicCTR<FuncionarioDTO> {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @Override
    public String inserir(FuncionarioDTO t) {
        try {
            if (funcionarioDAO.inserir(t)) {
                return "Funcionário cadastrado com sucesso!";
            }
            return "Funcionario NÃO cadastrado, tente novamente!";
        } catch (Exception err) {
            System.out.println("Erro FuncionarioCTR.inserir: " + err.getMessage());
            return "Funcionario NÃO cadastrado, tente novamente!";
        }
    }

    @Override
    public ResultSet consultar(FuncionarioDTO t, int opcao) {
        ResultSet rs = funcionarioDAO.consultar(t, opcao);
        return rs;
    }


    @Override
    public String alterar(FuncionarioDTO t) {
        try {
            if (funcionarioDAO.alterar(t)) {
                return "Funcionário alterado com sucesso!";
            }
            return "Funcionario NÃO alterado, tente novamente!";
        } catch (Exception err) {
            System.out.println("Erro FuncionarioCTR.alterar: " + err.getMessage());
            return "Funcionario NÃO alterado, tente novamente!";
        }
    }

    @Override
    public String deletar(FuncionarioDTO t) {
        try {
            if (funcionarioDAO.deletar(t)) {
                return "Funcionário deletado com sucesso!";
            }
            return "Funcionario NÃO deletado, tente novamente!";
        } catch (Exception err) {
            System.out.println("Erro FuncionarioCTR.deletar: " + err.getMessage());
            return "Funcionario NÃO deletado, tente novamente!";
        }
    }

    public int loginFuncionario(FuncionarioDTO t) {
        return funcionarioDAO.loginFuncionario(t);
    }

    public void CloseDB() {
        ConexaoDAO.closeDb();
    }

}
