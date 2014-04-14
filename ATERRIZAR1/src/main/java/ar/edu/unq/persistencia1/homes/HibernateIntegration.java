package ar.edu.unq.persistencia1.homes;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateIntegration {

    private static SessionFactory sessionFactory;

    protected static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            Configuration cfg = new Configuration();
            cfg.configure();

            sessionFactory = cfg.buildSessionFactory();
        }

        return sessionFactory;
    }

    /**
     * Get a session instance.
     * Example:
     *
     * Session session = HibernateIntegration.getInstance().getSession();
     * Jugador j = (Jugador) session.get(Jugador.class, 1);
     * j.setApellido("asd");
     * session.saveOrUpdate(j);
     * session.close();
     *
     */
    public static Session getSession(){
        return getSessionFactory().openSession();
    }
}
