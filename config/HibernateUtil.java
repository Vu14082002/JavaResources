package util;



import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Dialect;
import org.hibernate.service.ServiceRegistry;

import demo.Assignment;
import demo.AssignmentId;
import demo.Department;
import demo.Dependent;
import demo.DependentId;
import demo.Employee;
import demo.Profile;
import demo.Project;

public class HibernateUtil {
	private final static SessionFactory FACTORY;
	static {
		Configuration conf = new Configuration();
		Properties prop = new Properties();
		prop.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
		prop.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		prop.put(Environment.URL, "jdbc:sqlserver://localhost:1433;DatabaseName=TestDB;trustServerCertificate=true");
		prop.put(Environment.USER, "sa");
		prop.put(Environment.PASS, "sa");
		prop.put(Environment.SHOW_SQL, "true");
		prop.put(Environment.FORMAT_SQL, "true");
		prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		prop.put(Environment.HBM2DDL_AUTO, "create-drop");
		conf.setProperties(prop);
		conf.addAnnotatedClass(Employee.class)
		.addAnnotatedClass(Profile.class)
		.addAnnotatedClass(Dependent.class)
		.addAnnotatedClass(DependentId.class)
		.addAnnotatedClass(Department.class)
		.addAnnotatedClass(Assignment.class)
		.addAnnotatedClass(Project.class)
		.addAnnotatedClass(AssignmentId.class);
		
		
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		FACTORY = conf.buildSessionFactory(registry);
	}
	public static SessionFactory getSessionFactory() {
		return FACTORY;
	}
}
