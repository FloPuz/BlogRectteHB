package com.blogrecette.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.blogrecette.model.Ingredient;
import com.blogrecette.model.Recette;

public class TestIngredientHibernate {

	public static void main(String[] args) {
		 SessionFactory sf = new Configuration().configure().buildSessionFactory();
	        SessionFactory sessionFactory = sf;
	        Session session = sessionFactory.openSession();
	        
	      //  Ingredient ingredientTEST = new Ingredient("PremierIngredientTEST", 10, "U", recettes);
	        Transaction tx = null;
	        try {
	            tx = session.beginTransaction();

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
