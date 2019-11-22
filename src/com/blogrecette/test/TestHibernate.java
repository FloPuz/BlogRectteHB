package com.blogrecette.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

class TestHibernate {

    protected Session session;
    protected SessionFactory sessionFactory;

    public static void main(String args[]) throws Exception {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        SessionFactory sessionFactory = sf;
        Session session = sessionFactory.openSession();

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
