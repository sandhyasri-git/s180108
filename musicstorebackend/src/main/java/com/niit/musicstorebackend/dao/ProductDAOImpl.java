package com.niit.musicstorebackend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.musicstorebackend.model.Product;
@SuppressWarnings("deprecation")
@Repository("productDAO")
public class ProductDAOImpl implements IProductDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	

	public ProductDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}



	public void saveProduct(Product product) {
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		s.save(product);
		t.commit();
	}



	public boolean delete(Product product) {
		try{
			sessionFactory.openSession().delete(product);
			return true;
			
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}



	public Product get(int id) {
		/*try{
			return sessionFactory.openSession().createQuery("from Product where prodid=:id", Product.class).setParameter("id", id).getSingleResult();
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}*/
		
		String hql = "from Product where prodid=" + id;

		// hibernate query

		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();

		Query query = s.createQuery(hql);

		//Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Product> list = query.list();
		t.commit();
		if (list == null) {
			return null;

		} else {

			System.out.println("in Product in impl");

			return list.get(0);
		}
	}



	public List<Product> list() {
		try{
			return sessionFactory.openSession().createQuery("from Product", Product.class).getResultList();
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

}
