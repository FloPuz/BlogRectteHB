package com.blogrecette.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.loader.plan.exec.process.spi.ReturnReader;

import com.blogrecette.model.Commentaire;
import com.blogrecette.model.Ingredient;
import com.blogrecette.utils.HibernateUtils;

public class IngredientService {

	public IngredientService() {
	}




	public Ingredient addIngredient(Ingredient ingredient) throws SQLException{
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(ingredient);
			session.flush();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return ingredient;
	}

	public Ingredient deleteIngredient(Ingredient ingredient) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.delete(ingredient);
				session.flush();
				transaction.commit();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}


	public Ingredient updateIngredient(Ingredient ingredient) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(ingredient);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingredient;
	}



	public Ingredient getIngredientById(int idIngredient) throws SQLException{
		Transaction transaction = null;
		Ingredient ingredient = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ingredient = session.get(Ingredient.class, idIngredient);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return ingredient;
	}

	public List<Ingredient> selectAllIngredients() throws SQLException{
		Transaction transaction = null;
		List<Ingredient>allIngredients = null;

		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			allIngredients = session.createQuery("from Ingredient").getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return allIngredients;
	}




	public List<Ingredient> selectIngredientByIdRecette(int idRecette) {

		Transaction transaction = null;
		List<Ingredient> allIngredients = null;

		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			Query<Ingredient> query = session.createQuery("from Ingredient I WHERE I.recette.id = :idRecette");
			query.setParameter("idRecette", idRecette);
			allIngredients = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return allIngredients;
	}
}
