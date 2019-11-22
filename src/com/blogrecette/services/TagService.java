package com.blogrecette.services;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecette.model.Recette;
import com.blogrecette.model.Tag;
import com.blogrecette.utils.HibernateUtils;

public class TagService {
	
	public Tag createTag(Tag tag) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (tag != null) {
				session.save(tag);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return tag;

	}

	public ArrayList<Tag> getAllTags() {
		ArrayList<Tag>allTags = new ArrayList<Tag>();
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			String hql = "from Tag";
			Query query = session.createQuery(hql);
			allTags = (ArrayList<Tag>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allTags;

	}

	public void deleteTag(Tag tag) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (tag != null) {
				session.remove(tag);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public Tag getTagById(int id) throws Exception{//TESTED
		Tag tag = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			tag = session.get(Tag.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}


	
}
