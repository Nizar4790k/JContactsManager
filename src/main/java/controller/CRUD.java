package controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;






public class  CRUD<T> {
	
	
	
	public static Connection getConnection() {   // Probando la conexion a la DB
	
		System.out.println("Testing Connection");
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jcontactsmanager?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "root12");
			System.out.println("Connection Successfull");
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	}

	
	public static <T> void saveObject(T object) throws SQLException{ 
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(object.getClass()).buildSessionFactory();

       Session session = factory.getCurrentSession();

       try{

           session.beginTransaction();
           session.save(object);
           session.getTransaction().commit();
           

          
           
          
          
           
       }finally {
           session.close();
    	   factory.close();
           

       }
		
	}
	
	
	public static <T> void borrarObjeto(int id, Class c) throws SQLException {
		
		  T object;  
		  SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(c).buildSessionFactory();

	      Session session = factory.getCurrentSession();
	
	     
	
	      try {
	    	  
	    	  
	    
			object = getObject(id,c);
	    	
	    	 
	    	 session = factory.getCurrentSession();
	    	 session.beginTransaction();
	    	 session.delete(object);   // Borrando el objeto
	    	 session.getTransaction().commit();
	    	    	
	    	 
	    	  
	      }finally {
	    	  session.close();
	    	  factory.close();
	    
	      }
	
	
	
	      
	
	}
	
	
	public static <T> List<T> queryObject(Class c) throws SQLException{
	
		 SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(c).buildSessionFactory();

		 Session session = factory.getCurrentSession();
		
		
		try {
		
			session.beginTransaction();
			List<T> queryList= session.createQuery("from " + c.getName() ).getResultList();
			session.getTransaction().commit();
			
			
			return queryList;
		
		}finally {
			 session.close();
			factory.close();
			
		}
		
	
		
	}
	
	
	
	/*
	@SuppressWarnings("deprecation")
	public static String[] getColumnNames(Class c) {
	
		SessionFactory factory = null; 
		
		try {
			factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(c).buildSessionFactory();
		 
		 return factory.getClassMetadata(c).getPropertyNames();  // getColumnNames.
		 } 
		
		finally {
			 factory.close();
			 
		 }
	}
	
	*/ 
	
	public static ArrayList<String> getColumnNames(String tableName) throws SQLException {
		
		Connection connection = getConnection();
		
		DatabaseMetaData metaData  = connection.getMetaData(); 
		
		ArrayList<String> columnNames = new ArrayList<>();
		
		
		ResultSet resultset = metaData.getColumns(null, null,tableName, null);

				while(resultset.next()) {
			
				columnNames.add(resultset.getString("COLUMN_NAME"));
			
			
			
		}
		
		
		return columnNames;
	}
	
	public static <T> void updateObject(T object) throws SQLException
	{
		
		
		 SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(object.getClass()).buildSessionFactory();

		 Session session = factory.getCurrentSession();
		 
		 try {
			 session.beginTransaction();
			 session.update(object);
			 session.getTransaction().commit();
		 
		
		
		 }catch(Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
			 
		 }finally {
		 
			 factory.close();
		 }
	
			
	}
		 

	
		
		public static <T> List<T> executeQuery(String Query,Class c) {
			
			
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(c).buildSessionFactory();

		    Session session = factory.getCurrentSession();
		    
		    List <T> list;
		    try {
		    	  
		    	  
		    	 session.beginTransaction();  
		   
		    	 list =  session.createQuery(Query).getResultList();
		    	 
		    	 session.getTransaction().commit();
			
		      } finally {
		    	  
		    	  factory.close();
		      }
			
		    return list;
		    
			
		}
	
	
	
	
	public static <T> T getObject(int id,Class c){
		T object;
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(c).buildSessionFactory();

	    Session session = factory.getCurrentSession();
	
	     
	
	      try {
	    	  
	    	  
	    	 session.beginTransaction();  
	    	 object =   (T) session.get(c,id);// Recuperando el objeto dada la clase y el id
	    	 session.getTransaction().commit();
		
	      } finally {
	    	  
	    	  factory.close();
	      }
		return object;
	}
	
}
	
