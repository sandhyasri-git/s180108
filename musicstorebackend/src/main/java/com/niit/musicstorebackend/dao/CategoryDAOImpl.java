package com.niit.musicstorebackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.musicstorebackend.model.Category;
import com.niit.musicstorebackend.model.User;

@Repository("categoryDAO")
public class CategoryDAOImpl implements ICategoryDAO {

		@Autowired
		private SessionFactory sessionFactory;
		
	public CategoryDAOImpl(SessionFactory sessionFactory) {
			super();
			this.sessionFactory = sessionFactory;
		}

	public void saveCategory(Category category) {
	
		Session s=sessionFactory.openSession();
		Transaction tx=s.beginTransaction();
		s.save(category);
		tx.commit();
		s.clear();
				
	}

	public List<Category> getCategories() {
		
		Session s=sessionFactory.openSession();
		Transaction tx=s.beginTransaction();
		Query query=s.createQuery("From Category");
		List<Category>cat=query.list();
		if(cat!=null)
		{
			return cat;
		}
		else
		{
			System.out.println("List empty");
			return null;
		}
	}

	@Transactional
	public boolean delete(Category category) {  //since object is given 
		// TODO Auto-generated method stub
		try{
			
			sessionFactory.getCurrentSession().delete(category);
			
			return true;
			}catch(Exception e){
				System.out.println("Exception occured in delete method" + e.getMessage());
				e.printStackTrace();
				return false;
			}
	}
	public Category get(int id) {
		/*try {
			return sessionFactory.getCurrentSession().createQuery("from Category where catid=:id",Category.class).setParameter("id", id).getSingleResult();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				return null;
			}*/
		String hql = "from Category where catid=" + id;

		// hibernate query

		Session s = sessionFactory.getCurrentSession();
		Transaction t = s.beginTransaction();

		Query query = s.createQuery(hql);

		//Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Category> list = query.list();
		// t.commit();
		if (list == null) {
			return null;

		} else {

			System.out.println("in Category in impl");

			return list.get(0);

		}


	}
	@Transactional
	public void deleteCategory(int id) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Category p = (Category) session.load(Category.class, new Integer(id));
		
		if(null != p){
			session.delete(p);
		}
		tx.commit();
		System.out.println("inside delete category");
	}
	
	@Transactional
	public boolean update(Category category) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			session.update(category);
			System.out.println("Inside category update");
			//logger.info("Person updated successfully, Person Details="+p);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
 