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

public class GeraRelatorioFunc {

    public GeraRelatorioFunc() {
    	
    	Connection connection = Conexao.getInstancia().abrirConexao();
    	
    	File file = new File ("GeraRelatorioFunc.java");
    	String pathAbsoluto =  file.getAbsolutePath();
    	String pathAbsolutoParcial = pathAbsoluto.substring(0, pathAbsoluto.lastIndexOf('\\'))+"\\relatorios\\RelatorioFuncionario.jrxml";
        
    	try {
        	JasperReport jasperReport = JasperCompileManager.compileReport(pathAbsolutoParcial);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);

            Conexao.getInstancia().fecharConexao();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

