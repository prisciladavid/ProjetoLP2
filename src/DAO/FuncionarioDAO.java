package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import DTO.FuncionarioDTO;

public class FuncionarioDAO implements BasicDAO<FuncionarioDTO> {

    private ResultSet rs = null;
    private Statement stmt = null;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    private final String senheSaltValue = "pTWkHShuY6";

    public boolean inserir(FuncionarioDTO funcionario) {
        try {
            ConexaoDAO.conectDb();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "insert into funcionario (nome, cpf, dataNasc, senha) values " + "('"
                    + funcionario.getNome() + "'" + "," + "'" + funcionario.getCpf() + "'" + ", " + "to_date('"
                    + date.format(funcionario.getDataNasc()) + "', 'dd/mm/yyyy'), " + "'"
                    + HashSenhaDAO.generateSecurePassword(funcionario.getSenha(), senheSaltValue) + "'" + " )";
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception err) {
            System.out.println("Erro FuncionarioDAO.inserir: " + err.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDb();
        }
    }

    public int loginFuncionario(FuncionarioDTO funcionario) {
        int idFunc = 0;
        try {
            ConexaoDAO.conectDb();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "select idFunc, cpf, senha from funcionario where cpf = " + "'" + funcionario.getCpf() + "';";
            rs = stmt.executeQuery(comando.toUpperCase());
            while (rs.next()) {
                if (rs.getString("cpf").equals(funcionario.getCpf())) {
                    if (HashSenhaDAO.verifyUserPassword(funcionario.getSenha(), rs.getString("senha"),
                            this.senheSaltValue)) {
                    	idFunc = Integer.parseInt(rs.getString("idFunc"));
                    }
                }
            }
            return idFunc;
        } catch (Exception err) {
            System.out.println("Erro FuncionarioDAO.loginFuncionario: " + err.getMessage());
            return idFunc;
        } finally {
            ConexaoDAO.closeDb();
        }
    }

    public boolean alterar(FuncionarioDTO funcionario) {
        try {
            ConexaoDAO.conectDb();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "update funcionario set " + "nome = " + "'" + funcionario.getNome() + "'" + ", "
                    + "dataNasc = " + "'" + date.format(funcionario.getDataNasc()) + "'" + ", "
                    + "senha = " + "'" + HashSenhaDAO.generateSecurePassword(funcionario.getSenha(), senheSaltValue)
                    + "' where idFunc = " + funcionario.getIdFunc();
            if (funcionario.getSenha() == null || funcionario.getSenha().isEmpty()) {
                comando = "update funcionario set " + "nome = " + "'" + funcionario.getNome() + "'" + ", "
                        + "dataNasc = " + "'" + date.format(funcionario.getDataNasc())
                        + "' where idFunc = " + funcionario.getIdFunc();
            }
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception err) {
            System.out.println("Erro FuncionarioDAO.alterar: " + err.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDb();
        }
    }

    public ResultSet consultar(FuncionarioDTO t, int opcao) {
        try {
            ConexaoDAO.conectDb();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            switch (opcao) {
                case 1:
                    comando = "select f.* from funcionario f "
                            + "where nome ilike '" + t.getNome() + "%' "
                            + "order by f.nome";
                    break;
                case 2:
                    comando = "select f.* from funcionario f "
                            + "where f.idFunc = " + t.getIdFunc();
                    break;
                case 3:
                    comando = "select f.idFunc, f.nome from funcionario f";
                    break;
            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        } catch (Exception err) {
            System.out.println("Erro FuncionarioDAO.consultar: " + err.getMessage());
            return rs;
        }
    }

    public boolean deletar(FuncionarioDTO funcionario) {
        try {
            ConexaoDAO.conectDb();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "delete from funcionario where idFunc = " + funcionario.getIdFunc();
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception err) {
            System.out.println("Erro FuncionarioDAO.deletar: " + err.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDb();
        }
    }

}

