package com.niit.musicstorebackend.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.musicstorebackend.model.Category;
import com.niit.musicstorebackend.model.Supplier;

@Repository("supplierDAO")
@Transactional
public class SupplierDAOImpl implements ISupplierDAO {
	
	@Autowired
	SessionFactory sessionFactory;


	public SupplierDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public boolean saveorUpdate(Supplier supplier) {
		try {
			sessionFactory.openSession().saveOrUpdate(supplier);
		return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}

		
	}

	public boolean delete(Supplier supplier) {
		try {
			sessionFactory.openSession().delete(supplier);
		return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}

	}

	public Supplier get(int id) {
		/*try {
			return	sessionFactory.openSession().createQuery("from Supplier where supplierid=:id",Supplier.class).setParameter("id", id).getSingleResult();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				return null;
			}*/
		System.out.println("In get of supplier");
		String hql = "from Supplier where supplierid=" + id;

		// hibernate query

		Session s = sessionFactory.getCurrentSession();
		Transaction t = s.beginTransaction();

		Query query = s.createQuery(hql);

		//Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Supplier> list = query.list();
		s.close();
		if (list == null) {
			return null;

		} else {

			System.out.println("in supplier in impl");

			return list.get(0);

		}


	}

	public List<Supplier> list() {
		/*try {
			return sessionFactory.openSession().createQuery("from Supplier",Supplier.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}*/
		Session s=sessionFactory.openSession();
		Transaction tx=s.beginTransaction();
		Query query=s.createQuery("From Supplier");
		List<Supplier>cat=query.list();
		tx.commit();
		s.close();
		
		if(cat!=null)
		{
			System.out.println(cat);
			return cat;
		}
		else
		{
			System.out.println("List empty");
			return null;
		}

	}

	public boolean update(Supplier supplier) {
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		s.update(supplier);
		Query query=s.createQuery("From Supplier");
		
		List<Supplier>cat=query.list();
		//t.commit();
		//s.close();
		
		if(cat!=null)
		{
			System.out.println(cat);
			Iterator<Supplier>s1=cat.iterator();
			while(s1.hasNext())
			{
				System.out.println(s1.next().getAddress());
			}
		}
		else
		{
			System.out.println("List empty");
			
		}
		t.commit();
		s.close();
		return true;
	}

}
