package com.blogrecette.services;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecette.model.Ingredient;
import com.blogrecette.model.Membre;
import com.blogrecette.model.Recette;
import com.blogrecette.utils.HibernateUtils;

public class MembreService {

	
	public MembreService() {}
	
	
	public Membre addMembre (Membre membre) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(membre);
			session.flush();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return membre;
	}
	
	public Membre deleteMembre (Membre membre) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (membre != null) {
				session.delete(membre);
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
	
	public Membre updateMembre(Membre membre) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(membre);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return membre;
	}

	public Membre getMembreById(int idMembre) throws Exception {
		Transaction transaction = null;
		Membre membre = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			membre = session.get(Membre.class, idMembre);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return membre;
	}
		
	public List<Membre> getAllMembres() throws Exception{
		Transaction transaction = null;
		List<Membre> allMembres = null;

		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			allMembres = session.createQuery("from Membre").getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return allMembres;
	}


	public Membre selectMembreByPseudoMdp(String pseudo, String mdp) {
		Membre membre = new Membre();
		Transaction transaction=null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			Query query = session.createQuery("from Membre M WHERE "
					+ "M.pseudo = :pseudo AND "
					+ "M.mdp = :mdp");
			query.setParameter("pseudo", pseudo);
			query.setParameter("mdp", mdp);
			List list = query.getResultList();
			membre = (Membre)list.toArray()[0];
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		
		
		
		return membre;
	}
	
	//public boolean checkIfPseudoAndMailExists(String pseudo, String mail) throws SQLException{}
	
	//public Membre selectMembreByPseudoMdp(String pseudo, String mdp) throws SQLException{}
}
