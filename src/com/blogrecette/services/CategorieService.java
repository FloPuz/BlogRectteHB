package com.blogrecette.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.blogrecette.model.Categorie;
import com.blogrecette.utils.HibernateUtils;

public class CategorieService {

	public CategorieService() {
		//A voir pour ouvrir la session ici
	}

	public Categorie getCategorieFromId(int id) throws SQLException {
		Transaction transaction = null;
		Categorie categorie = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			categorie = session.get(Categorie.class, id);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return categorie;
	}

	public Categorie createCategorie(Categorie categorie) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(categorie);
			session.flush();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return categorie;
	}

	public Categorie removeCategorie(Categorie categorie) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (categorie != null) {
				session.delete(categorie);
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

	public Categorie updateCategorie(Categorie categorie) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(categorie);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorie;
	}

	public List<Categorie> getAllCategories() throws SQLException {
		Transaction transaction = null;
		List<Categorie> allCategories = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			allCategories = session.createQuery("from Categorie").getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return allCategories;
	}

}
