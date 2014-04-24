package ar.edu.unq.persistencia1.services;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class SessionManager {
	
	private static SessionFactory sessionFactory;
	private static ThreadLocal<Session> tlSession = new ThreadLocal<Session>();
	
	public synchronized static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration cfg = new Configuration();
			cfg.configure();

			sessionFactory = cfg.buildSessionFactory();
		}

		return sessionFactory;
	}
	
	public static <T> T runInSession(Operation<T> cmd){
		SessionFactory sessionFactory = SessionManager.getSessionFactory();
		Transaction transaction = null;
		T result = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			tlSession.set(session);
			
			result = cmd.execute();

			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null)
				session.close();
			tlSession.set(null);
		}
		
		return result;
	}

	public static Session getSession() {
		return tlSession.get();
	}

}
