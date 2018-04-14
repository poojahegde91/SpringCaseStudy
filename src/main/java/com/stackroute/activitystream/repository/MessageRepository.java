package com.stackroute.activitystream.repository;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.stackroute.activitystream.config.HibernateUtil;
import com.stackroute.activitystream.model.Message;


/*
 * This class contains the code for database interactions and methods 
 * of this class will be used by other parts of the applications such
 * as Controllers and Test Cases
 * */
public class MessageRepository {
	
	SessionFactory sessionFactory = null;
	Session session = null;
	
	public MessageRepository() {
		/*
		 * create a hibernate session from HibernateUtil
		 */
		
		sessionFactory =  HibernateUtil.getSessionFactory();
	}
	
	/*
	 * This method is used to save messages in database
	 */ 
	public boolean sendMessage(Message message) {
		
		
		//session = HibernateUtil.getSessionFactory().openSession();
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(message);//insert query gets fired
		session.getTransaction().commit();
		
		session.close();
		
		return true;

		
	}
	
	/*
	 * This method is used to retrieve all messages in database
	 */
	public List<Message> getAllMessages(){
		//session = HibernateUtil.getSessionFactory().openSession();
		
		session = sessionFactory.openSession();
		
		Query query = session.createQuery("from Message order by postedDate desc");
		
		
		List<Message> list = query.list();
		System.out.println("list data " +list);
		
		session.close();
	
		
		return list;
	}
}
