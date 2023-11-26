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

    public GeraRelatorio() {
    	
    	// Estabelecer a conexão com o PostgreSQL
        //Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bancoParque", "postgres", "postdba");
    	Connection connection = Conexao.getInstancia().abrirConexao();
    	
    	File file = new File ("GeraRelatorio.java");
    	String pathAbsoluto =  file.getAbsolutePath();
    	//C:\Users\raiss\eclipse-workspace\ProjetoLP2\src\CTR\GeraRelatorio.java
    	String pathAbsolutoParcial = pathAbsoluto.substring(0, pathAbsoluto.lastIndexOf('\\'))+"\\relatorios\\RelatorioClientes.jrxm";
        
    	try {
        	// Compilar o arquivo JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport(pathAbsolutoParcial);

            // Preencher o relatório
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);

            // Visualizar o relatório
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);

            // Fechar a conexão
            Conexao.getInstancia().fecharConexao();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

