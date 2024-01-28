package exmpl.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Bookings;

/**
 * Dao class to create connection with database using Hibernate Entity class
 * which we are using here is mentioned here to perform the CRUD operations via
 * API
 * 
 * @author supriyasharma
 */
public class BookingDAO {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            /*
             * configuration related to DB for connection is mentioned in 
             * hibernate.cfg.xml file
             */
            configuration.configure("hibernate.cfg.xml");
            /*
             * add the entity so that we can use the operations on the same
             */
            configuration.addAnnotatedClass(Bookings.class);
            /*
             * session factory to open session to actually connect the db and
             * perform the operations
             */
            sessionFactory = configuration.buildSessionFactory();
//            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Method to the new data to DB, create method will call this
     * 
     * @param entity
     */
    public void save(Bookings entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
    }

    /**
     * Method to get the data from DB based on the id requested
     * 
     * @param id
     * @return
     */
    public Bookings get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Bookings.class, id);
        }
    }

    /**
     * Method to update the existing data in DB
     * 
     * @param entity
     */
    public void update(Bookings entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }
    }

    /**
     * Method to delete the existing data from DB, used for cancel operation in
     * this project
     * 
     * @param id
     */
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Bookings entity = session.get(Bookings.class, id);
            if (entity != null) {
                session.delete(entity);
            }
            transaction.commit();
        }
    }
}
