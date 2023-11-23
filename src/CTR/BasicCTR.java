package CTR;

import java.sql.ResultSet;

public interface BasicCTR<T> {

    public String inserir(T t);

    public ResultSet consultar(T t, int opcao);

    public String alterar(T t);

    public String deletar(T t);

    public void CloseDB();
}

