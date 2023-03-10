package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class TestConnection {
	public static void main(String[] args) {
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		
		Metadata metadata = new MetadataSources(registry)
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Profile.class)
				.addAnnotatedClass(Dependent.class)
				.addAnnotatedClass(DependentId.class)
				.addAnnotatedClass(Department.class)
				.addAnnotatedClass(Assignment.class)
				.addAnnotatedClass(Project.class)
				.addAnnotatedClass(AssignmentId.class)
				.getMetadataBuilder()
				.build();
		
		SessionFactory sessionFactory = metadata
				.getSessionFactoryBuilder()
				.build();
		
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
			
			tr.commit();
		}catch (Exception e) {
			tr.rollback();
		}
		
	}
}
