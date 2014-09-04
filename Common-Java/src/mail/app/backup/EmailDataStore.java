package mail.app.backup;

import mail.EmailData;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class EmailDataStore {

	private StorePath storePath;

	public EmailDataStore() {
		setStorePath(null);
	}

	public void setStorePath(StorePath storePath) {
		this.storePath = storePath;
		if (this.storePath == null) {
			this.storePath = StorePath.getDefaultStorePath();
		}
	}

	public void store(EmailData emailData) {
		ODB odb = null;
		try {
			odb = ODBFactory.open(storePath.getStorePath());
			odb.store(emailData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null) {
				odb.close();
			}
		}
	}

}
