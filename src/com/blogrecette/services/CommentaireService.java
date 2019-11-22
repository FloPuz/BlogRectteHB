package com.blogrecette.services;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecette.model.Categorie;
import com.blogrecette.model.Commentaire;
import com.blogrecette.model.Recette;
import com.blogrecette.utils.HibernateUtils;

public class CommentaireService {

	public CommentaireService() {
		
	}
	
	public Commentaire getCommentaireFromId(int id) throws SQLException {
		Transaction transaction = null;
		Commentaire commentaire = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			commentaire = session.get(Commentaire.class, id);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return commentaire;
	}

	public Commentaire createCommentaire(Commentaire commentaire) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(commentaire);
			session.flush();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return commentaire;
	}

	public Commentaire removeCommentaire(Commentaire commentaire) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.delete(commentaire);
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

	public Commentaire updateCommentaire(Commentaire commentaire) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(commentaire);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return commentaire;
	}

	public List<Commentaire> getAllCommentaires() throws SQLException {
		Transaction transaction = null;
		List<Commentaire> allCommentaire = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			allCommentaire = session.createQuery("from Commentaire").getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	
		return allCommentaire;
	}

	public List<Commentaire> getCommentairesByIdRecette(int idRecette) {
		
		Transaction transaction = null;
		List<Commentaire> allCommentaires = null;

		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			Query<Commentaire> query = session.createQuery("from Commentaire C WHERE C.recette.id = :idRecette");
			query.setParameter("idRecette", idRecette);
			allCommentaires = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return allCommentaires;
	}
	
	
	
}
