package com.blogrecette.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import net.bytebuddy.asm.Advice.This;

public class HibernateUtils {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory==null || sessionFactory.isClosed()) {
			try {
				SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
				sessionFactory=sFactory;
				return sessionFactory;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
	
	public static void CloseSessionFactory() {
		getSessionFactory().close();
	}
}
