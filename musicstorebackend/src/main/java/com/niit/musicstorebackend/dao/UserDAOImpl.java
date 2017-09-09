package com.niit.musicstorebackend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.musicstorebackend.model.User;
@Repository("userDAO")

public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addUser(User user) {
		Session s=sessionFactory.openSession();
		Transaction tx=s.beginTransaction();
		s.saveOrUpdate(user);
		tx.commit();
		return true;
	}
	@Transactional
	public boolean deleteUser(User user) {
		Session s=sessionFactory.openSession();
		Transaction tx=s.beginTransaction();
		s.delete(user);
		System.out.println("Deleted successfully");
		tx.commit();
		s.close();
		return true;
	}
	@Transactional
	public User get(String email) {
		try {
return	sessionFactory.openSession().createQuery("from User where emailid=:email",User.class).setParameter("email", email).getSingleResult();
			} catch (Exception e) {
				
				System.out.println(e);
				return null;
			}

	}

	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public List<User> getAllUsers() {
		Session s=sessionFactory.openSession();
		Transaction tx=s.beginTransaction();
		Query query=s.createQuery("From User");
		List<User>us=query.list();
		if(us!=null)
		{
			return us;
		}
		else
		{
			System.out.println("List empty");
			return null;
		}
		
	}

}
