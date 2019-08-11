package demo.application.web.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtil {

	private static final String PERSISTENCE_UNIT_NAME = "example";
	
	private static EntityManagerFactory emf = null;

    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {        	
            throw new ExceptionInInitializerError(ex);
        }
    }
    
 
    public static EntityManager getEntityManager() {
    	if (!emf.isOpen())
    		throw new RuntimeException("EntityManagerFactory is closed!");
    	return emf.createEntityManager();
    }
    
   
    public static void closeEntityManagerFactory() {
    	if (emf.isOpen()) {
    		emf.close();
    	}
    }
	
}
