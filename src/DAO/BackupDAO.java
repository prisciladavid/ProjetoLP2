package DAO;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Classe para realizar o backup de dados
 */
public class BackupDAO {

    private static final Logger log = Logger.getLogger(BackupDAO.class.getName());

    /**
     * Fun��o para executar o comando no terminal
     *
     * @param command Comando para ser executado no terminal
     */
    public static void executeCommand(final String command) throws IOException {
        final ArrayList<String> commands = new ArrayList<String>();
        if (System.getProperty("os.name").toLowerCase().indexOf("win") < 0) {
            commands.add("/bin/bash");
            commands.add("-c");
        } else {
            commands.add("powershell.exe");
        }
        commands.add(command);
        BufferedReader br = null;
        try {
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Retorno do comando = [" + line + "]");
            }
        } catch (IOException ioe) {
            log.severe("Erro ao executar comando shell" + ioe.getMessage());
            throw ioe;
        } finally {
            secureClose(br);
        }
    }

    /**
     * Fun��o para realizar um fechamento seguro
     *
     * @param resource Parametro para fechar a edi��o
     */
    private static void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            log.severe("Erro = " + ex.getMessage());
        }
    }

    /**
     * Fun��o para confirmar backup
     */
    public void confirmaBackup() throws IOException {
        JDialog.setDefaultLookAndFeelDecorated(false);
        int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Realizar o Backup?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.getDefaultLocale();
        } else if (response == JOptionPane.YES_OPTION) {
            System.out.println(System.getProperty("os.name"));
            if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
                BackupDAO.executeCommand("Compress-Archive -Path C:/parqueAquatico/ -DestinationPath C:/parqueAquatico/parqueAquatico.zip");
            } else {
                BackupDAO.executeCommand("zip -r /parqueAquatico/parqueAquatico.zip /parqueAquatico/");
            }
            JOptionPane.showMessageDialog(null, "Backup gerado com sucesso");
            JOptionPane.getDefaultLocale();
        }
    }

    /**
     * Fun��o para confimar a restaura��o do backup
     */
    public void confirmaRestaurarBackup() throws IOException {
        JDialog.setDefaultLookAndFeelDecorated(false);
        int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Restaurar o Backup?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.getDefaultLocale();
        } else if (response == JOptionPane.YES_OPTION) {
            if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
                BackupDAO.executeCommand("Expand-Archive -Path C:/parqueAquatico/parqueAquatico.zip -DestinationPath C:/parqueAquatico");
            } else {
                BackupDAO.executeCommand("unzip -o /parqueAquatico/parqueAquatico.zip -d /");
            }
            JOptionPane.showMessageDialog(null, "Backup restaurado com sucesso");
            JOptionPane.getDefaultLocale();
        }
    }
}