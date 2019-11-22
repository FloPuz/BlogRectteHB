package com.blogrecette.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.blogrecette.model.Categorie;

public class TestCategorieHibernate {

	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Categorie categorieTESTCategorie = new Categorie("PremièreCategorie");
			session.save(categorieTESTCategorie);
			session.flush();
			session.remove(categorieTESTCategorie);
			
			
			
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}

		sessionFactory.close();
	}

}
