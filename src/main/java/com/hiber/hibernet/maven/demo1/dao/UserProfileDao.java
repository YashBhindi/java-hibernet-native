/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiber.hibernet.maven.demo1.dao;

import com.hiber.hibernet.maven.demo1.config.HibernateUtil;
import com.hiber.hibernet.maven.demo1.model.HelloWorld;
import com.hiber.hibernet.maven.demo1.model.UserProfile;
import java.util.List;
import java.util.Objects;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author yash
 */
public class UserProfileDao {
     public void saveUser(UserProfile userProfile) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the hello world object
            session.save(userProfile);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List < UserProfile > getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from UserProfile", UserProfile.class).list();
        }
    }
}
