package CTR;

import DAO.BackupDAO;

/**
 * Classe para conecatar ao backupDAO
 */
public class BackupCTR {
	BackupDAO backupDAO = new BackupDAO();

	/**
	 * Fun��o para realizar backup no backupDAO
	 */
	public void confirmaBackup() {
		try {
			this.backupDAO.confirmaBackup();
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}

	/**
	 * Função que restaura o backup no backupDAO
	 */
	public void confirmaRestaurarBackup() {
		try {
			this.backupDAO.confirmaRestaurarBackup();
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}

}