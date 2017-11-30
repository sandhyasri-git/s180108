package niit.colloborations.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import niit.colloborations.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	public UserDAOImpl() {
	
	
	}
	@Autowired
	private SessionFactory sessionFactory;


	public UserDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public List<User> list() {
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return list;
	}

	public void saveOrUpdate(User user) {
		Session s=sessionFactory.getCurrentSession();
		Transaction t=s.beginTransaction();
		System.out.println(user);
		s.saveOrUpdate(user);
		t.commit();
		//sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
	public User getUserById(int id) {
		System.out.println("Inside getUserById");
		Session session=this.sessionFactory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		User user=(User) session.load(User.class,id);
		System.out.println("data of user by id="+user);
		tx.commit();
		return user;	

	}
	/*public User getUserByName(String name) {
		System.out.println("getting data in dao based on name");
		Session session=this.sessionFactory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		User user=(User) session.get(User.class,name);
		tx.commit();
		System.out.println("data of user by name="+user);
		return user;	

	}*/
	@Transactional
	public User getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
                     System.out.println("I am in getuserbyusernamedao");
                     Transaction tx=session.beginTransaction();
		    List<User> users = session.createQuery("from User u where u.user_name='" + username + "'").list();
		    User user=users.get(0);
		    System.out.println("user object:"+user);
		    tx.commit();
		    return user;
		}
	
	@Transactional
	public void delete(int id) {
		User user = new User();
		
		sessionFactory.getCurrentSession().delete(user);
		System.out.println("Deleting the User");
	}
	public boolean login(String uname, String pass) {
		
boolean b=false;
		
		Session sess=sessionFactory.openSession();
		Criteria c=sess.createCriteria(User.class);
		c.add(Restrictions.and(Restrictions.eq("user_name", uname), Restrictions.eq("password", pass)));
		List<User>l=c.list();
		if(l.isEmpty())
			b=false;
		else
			b=true;
			
		return b;

	}
	}


