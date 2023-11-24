package CTR;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GeraRelatorio {

    public static void main(String[] args) {
    	
    	File file = new File ("GeraRelatorio.java");
    	String pathAbsoluto =  file.getAbsolutePath();
    	//C:\Users\raiss\eclipse-workspace\ProjetoLP2\src\CTR\GeraRelatorio.java
    	String pathAbsolutoParcial = pathAbsoluto.substring(0, pathAbsoluto.lastIndexOf('\\'))+"\\relatórios\\RelatorioClientes.jrxm";
        try {
            // Carregar o driver JDBC
            Class.forName("org.postgresql.Driver");

            // Estabelecer a conexão com o PostgreSQL
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bancoParque", "postgres", "postdba");

            // Compilar o arquivo JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\raiss\\eclipse-workspace\\ProjetoLP2\\relatórios\\RelatorioClientes.jrxm");

            // Parâmetros do relatório, se houver
            Map<String, Object> parameters = new HashMap<>();

            // Preencher o relatório
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            // Visualizar o relatório
            JasperViewer.viewReport(jasperPrint, false);

            // Fechar a conexão
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

