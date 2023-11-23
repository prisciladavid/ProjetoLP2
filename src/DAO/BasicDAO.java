package DAO;

import java.sql.ResultSet;

public interface BasicDAO<T> {

	public boolean inserir(T t);


	public ResultSet consultar(T t, int opcao);

	public boolean alterar(T t);

	public boolean deletar(T t);
}

