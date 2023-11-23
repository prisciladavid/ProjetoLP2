package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import DTO.ClienteDTO;

public class ClienteDAO implements BasicDAO<ClienteDTO> {

    private ResultSet rs = null;
    private Statement stmt = null;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");


    public boolean inserir(ClienteDTO clienteDTO) {
        try {
            ConexaoDAO.conectDb();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "insert into cliente "
                    + "(nome, cpf, telefone, dataNasc, idFunc) values (" + "'"
                    + clienteDTO.getNome() + "'" + ", " + "'" + clienteDTO.getCpf() + "'" + ", " + "'"
                    + clienteDTO.getTelefone() + "'" + ", " + "'"
                    + date.format(clienteDTO.getDataNasc()) + "'" + ", " + "'" + clienteDTO.getIdFunc() + "'" +")";
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ClienteDAO.inserir: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDb();
        }
    }

    public ResultSet consultar(ClienteDTO clienteDTO, int opcao) {
        try {
            ConexaoDAO.conectDb();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            switch (opcao) {
                case 1:
                    comando = "select c.* from cliente c "
                            + "where nome ilike '" + clienteDTO.getNome() + "%' "
                            + "order by c.nome";
                    break;
                case 2:
                    comando = "select c.* from cliente c "
                            + "where c.idCliente = " + clienteDTO.getIdCliente();
                    break;
                case 3:
                    comando = "select c.idCliente, c.nome from cliente c";
                    break;
            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ClienteDAO.consultar: " + e.getMessage());
            return rs;
        }
    }

    public boolean alterar(ClienteDTO clienteDTO) {
        try {
            ConexaoDAO.conectDb();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Update cliente set " + "nome = " + "'" + clienteDTO.getNome() + "'" + ", " + "telefone = "
                    + "'" + clienteDTO.getTelefone() + "'" + ", "
                    + "dataNasc = " + "'" + date.format(clienteDTO.getDataNasc()) + "' "
                    + "where idCliente = " + clienteDTO.getIdCliente();
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ClienteDAO.alterar: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDb();
        }
    }

    public boolean deletar(ClienteDTO clienteDTO) {
        try {
            ConexaoDAO.conectDb();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "delete from cliente where idCliente = " + clienteDTO.getIdCliente();
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ClienteDAO.deletar: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDb();
        }
    }

}

