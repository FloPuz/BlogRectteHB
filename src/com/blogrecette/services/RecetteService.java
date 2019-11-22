package com.blogrecette.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import com.blogrecette.model.Membre;
import com.blogrecette.model.Recette;
import com.blogrecette.utils.HibernateUtils;

public class RecetteService {

	public RecetteService() {}
	
	public Recette addRecette (Recette recette) throws IllegalStateException {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(recette);
			session.flush();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return recette;
	}
	
	public Recette deleteRecette (Recette recette) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.delete(recette);
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
	
	public Recette updateRecette(Recette recette) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(recette);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return recette;
	}

	public Recette getRecetteById(int idRecette) {
		Transaction transaction = null;
		Recette recette = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			recette = session.get(Recette.class, idRecette);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	
		return recette;
	}
		
	public List<Recette> getAllRecette() throws SQLException{
		Transaction transaction = null;
		List<Recette> allRecettes = null;

		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			allRecettes = session.createQuery("from Recette").getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		for (Recette recette : allRecettes) {
			recette.setMoyenneNote(this.moyNoteRecetteByRecette(recette.getIdRecette()));
		}
		return allRecettes;
	}
	
	
	
	public List<Recette> selectAllRecettesByCategories(int idCategorie)throws SQLException{
		Transaction transaction = null;
		List<Recette> allRecettes = null;

		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			Query query = session.createQuery("from Recette R WHERE R.categorie.id = :idCategorie");
			query.setParameter("idCategorie", idCategorie);
			allRecettes = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		for (Recette recette : allRecettes) {
			recette.setMoyenneNote(this.moyNoteRecetteByRecette(recette.getIdRecette()));
		}
		return allRecettes;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Recette> selectAllRecettesByMembre(int idMembre){
	
		List<Recette> allRecettes = null;

		try (Session session = HibernateUtils.getSessionFactory().openSession()) {

			// get an user object
			Query query = session.createQuery("select R from Recette R join R.membre M WHERE M.id = :id");
			query.setParameter("id", idMembre);
			allRecettes = (List<Recette>) query.getResultList();
		
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return allRecettes;
		
	}
	
	public int moyNoteRecetteByRecette(int idRecette) throws SQLException {
		int moyenne = 0;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {

			// get an user object
			Query query = session.createQuery("select floor(avg(C.note)) from Recette R join R.commentaires C WHERE R.id = :id");
			query.setParameter("id", idRecette);
			moyenne = (int) query.getSingleResult();
		
		} catch (Exception e) {

			e.printStackTrace();
		}
		return moyenne;
		
		
		
	}
	
	
	
	
	
	
}
