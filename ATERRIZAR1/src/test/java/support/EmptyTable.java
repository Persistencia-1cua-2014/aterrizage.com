package support;


import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class EmptyTable implements Operation<Boolean> {
	private String table_name;

	public EmptyTable(String table_name) {
		this.table_name = table_name;
	}

	@Override
	public Boolean execute() {
		Session s = SessionManager.getSession();
		SQLQuery q = s.createSQLQuery("DELETE FROM aterrizage_test." + table_name);
		q.executeUpdate();
		return true;
	}
}
