package fer.shop.resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateSessionManager {

	private static SessionFactory sessionFactory;

	public static void setSessionFactory(SessionFactory sessionFactory) {
		System.out.println("creating session manager");
		HibernateSessionManager.sessionFactory = sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session beginTransaction() {
		Session hibernateSession = HibernateSessionManager.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}
	
	public static void commitTransaction() {
		HibernateSessionManager.getSession().getTransaction().commit();
	}

	public static void rollbackTransaction() {
		HibernateSessionManager.getSession().getTransaction().rollback();
	}

	public static void closeSession() {
		HibernateSessionManager.getSession().close();
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	public static Session getSession() {
		Session hibernateSession = sessionFactory.getCurrentSession();
		return hibernateSession;
	}

}