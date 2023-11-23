package CTR;

import java.sql.ResultSet;

import DAO.ClienteDAO;
import DAO.ConexaoDAO;
import DTO.ClienteDTO;

public class ClienteCTR {
	private ClienteDAO clienteDAO = new ClienteDAO();

	public String inserir(ClienteDTO t) {
		try {
			if (clienteDAO.inserir(t)) {
				return "Cliente inserido com sucesso!";
			}
			return "Cliente NÃO inserido, tente novamente!";
		} catch (Exception err) {
			System.out.println("Erro ClienteCTR.inserir: " + err.getMessage());
			return "Cliente NÃO inserido, tente novamente!";
		}
	}

	public ResultSet consultar(ClienteDTO t, int opcao) {
		ResultSet rs = clienteDAO.consultar(t, opcao);
		return rs;
	}


	public String alterar(ClienteDTO t) {
		try {
			if (clienteDAO.alterar(t)) {
				return "Cliente alterado com sucesso!";
			}
			return "Cliente NÃO alterado, tente novamente!";
		} catch (Exception err) {
			System.out.println("Erro ClienteCTR.alterar: " + err.getMessage());
			return "Cliente NÃO alterado, tente novamente!";
		}
	}


	public String deletar(ClienteDTO t) {
		try {
			if (clienteDAO.deletar(t)) {
				return "Cliente deletado com sucesso!";
			}
			return "Cliente NÃO deletado, tente novamente!";
		} catch (Exception err) {
			System.out.println("Erro ClienteCTR.deletar: " + err.getMessage());
			return "Cliente NÃO deletado, tente novamente!";
		}
	}

        public void CloseDB() {
            ConexaoDAO.closeDb();
        }
        
}

