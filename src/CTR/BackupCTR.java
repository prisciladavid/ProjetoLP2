package CTR;

import DAO.BackupDAO;


public class BackupCTR {
	BackupDAO backupDAO = new BackupDAO();

	public void confirmaBackup() {
		try {
			this.backupDAO.confirmaBackup();
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}

	public void confirmaRestaurarBackup() {
		try {
			this.backupDAO.confirmaRestaurarBackup();
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}

}